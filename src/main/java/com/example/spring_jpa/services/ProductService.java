package com.example.spring_jpa.services;

import com.example.spring_jpa.exception.NoProductExistException;
import com.example.spring_jpa.model.Product;
import com.example.spring_jpa.repository.ProductRepository;
import com.example.spring_jpa.requests.AddProductRequest;
import com.example.spring_jpa.requests.RemoveProductRequest;
import com.example.spring_jpa.util.IdGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    IdGeneratorService idGererator;

    private long id = 10 ;

    public Product addProduct(AddProductRequest request){

            String name = request.getProductName();
            List<String> names = productRepository.getAllProductName();

            Optional<String> exist = names.stream()
                    .filter(item->item.toLowerCase().equals(name.toLowerCase()))
                    .findFirst();

            if (exist.isPresent()){
                throw new IllegalStateException("Product AlreadyExist");
            }

            Product newProduct = Product.builder()
                    .productId(idGererator.generateId("PROD"))
                    .productName(request.getProductName())
                    .productType(request.getProductType())
                    .price(request.getPrice())
                    .discount(request.getDiscount())
                    .stock(request.getStock())
                    .build();
            Product saved = productRepository.save(newProduct);
            return saved ;

    }

    public void removeProduct(RemoveProductRequest request){

        Optional<Product> productOptinal = productRepository.findById(request.getProductId());

        if (productOptinal.isPresent()){

            productRepository.removeProductBYId(request.getProductId());
        }else {
            throw new NoProductExistException("No product Available in product Id " + request.getProductId());
        }
    }

    public int decreaseStock(String productId , int quantity){
        return productRepository.decreaseStockIfAvailable(productId,quantity);
    }

    public int getStock(String productId) {

        return productRepository.getproductStockByid(productId);
    }

    public boolean isProductExist(String id ,String productName){

        Optional<Product> productOprional  = productRepository.findById(id);
        return productOprional.isPresent();
    }

    public Product getProductyId(String id ){
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


}
