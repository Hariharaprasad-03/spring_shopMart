package com.example.spring_jpa.payload;

import java.io.Serializable;

public record UpdateProductRequest(

        String productId ,
        int quantity ,
        double price ,
        double discount
) implements Serializable {


}
