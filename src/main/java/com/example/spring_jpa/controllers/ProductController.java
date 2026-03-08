package com.example.spring_jpa.controllers;

import com.example.spring_jpa.model.Product;
import com.example.spring_jpa.payload.AddProductRequest;
import com.example.spring_jpa.payload.RemoveProductRequest;
import com.example.spring_jpa.payload.UpdateProductRequest;
import com.example.spring_jpa.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<Product>products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest request){

        try{

            Product added =  productService.addProduct(request);
            return ResponseEntity.ok(added);
        } catch (IllegalStateException e)
        {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {

        productService.removeProduct(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @PutMapping("/update")
    public ResponseEntity<?>updateProduct(@RequestBody UpdateProductRequest request){

        try {
            Product updated = productService.updateProduct(request);
            return ResponseEntity.status(200).body(updated);
        } catch (Exception e){

            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
