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

    @GetMapping("/all")
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

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeProduct( @RequestBody RemoveProductRequest request){

        try
        {
            productService.removeProduct(request);
            String message = "{ \"message \" : \"success\" }";

            return ResponseEntity.status(200).body(message);
        } catch (Exception e) {

            return ResponseEntity.status(400).body(e.getMessage());
        }
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
