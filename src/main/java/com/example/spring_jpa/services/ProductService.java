package com.example.spring_jpa.services;

import com.example.spring_jpa.exception.NoProductExistException;
import com.example.spring_jpa.model.Product;
import com.example.spring_jpa.payload.UpdateProductRequest;
import com.example.spring_jpa.repository.ProductRepository;
import com.example.spring_jpa.payload.AddProductRequest;

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


    public Product addProduct(AddProductRequest request){

            String name = request.getProductName();
            List<String> names = productRepository.getAllProductName();

            Optional<String> exist = names.stream()
                    .filter(item-> item.equalsIgnoreCase(name))
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
            System.out.println(newProduct);
            return productRepository.save(newProduct);

    }

    public void removeProduct(String productId){

        Optional<Product> productOptinal = productRepository.findById(productId);

        if (productOptinal.isPresent()){

            productRepository.removeProductBYId(productId);
        }else {
            throw new NoProductExistException("No product Available in product Id " + productId);
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


    public Product updateProduct(UpdateProductRequest request) {

        Product product = productRepository.findById(request.productId())
                .orElseThrow(() ->
                        new NoProductExistException("No product exists with that ID"));

        int rows = productRepository.updateProductDetails(
                request.price(),
                request.discount(),
                request.quantity(),
                request.productId()
        );

        if (rows == 0) {
            throw new RuntimeException("Update failed");
        }

        return productRepository.findById(request.productId()).get();
    }


}
