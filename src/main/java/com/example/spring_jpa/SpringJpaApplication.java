package com.example.spring_jpa;


import com.example.spring_jpa.model.*;

import com.example.spring_jpa.repository.CartRepository;

import com.example.spring_jpa.requests.AddCartItemRequest;
import com.example.spring_jpa.requests.AddProductRequest;
import com.example.spring_jpa.requests.CartCheckOutRequest;
import com.example.spring_jpa.requests.RemoveProductRequest;
import com.example.spring_jpa.services.CartService;
import com.example.spring_jpa.services.OrderServices;
import com.example.spring_jpa.services.ProductService;
import com.example.spring_jpa.services.UserService;
import com.example.spring_jpa.util.IdGeneratorService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner demo(OrderServices orderService, UserService userv , ProductService productService,
								  CartService cartService , IdGeneratorService idServ , CartRepository cartrepo ) {
		return (args) -> {
			System.out.println("Application started... Adding Order now.");

			try {
				AddProductRequest request = new AddProductRequest(
						"apple",
						"fruits",
						100.0,
						100,
						10
				);
				AddProductRequest request2 = new AddProductRequest(
						"orange",
						"fruits",
						100.0,
						100,
						10
				);
				AddProductRequest request_banana = new AddProductRequest(
						"banana",
						"fruits",
						10.0,
						100,
						0
				);

				Product added = productService.addProduct(request);
				Product orange =productService.addProduct(request2);
				Product banana = productService.addProduct(request_banana);

				System.out.println("Product Added Here");

				User sanjay = new User();
				sanjay.setId("6374202368");
				sanjay.setName("sanjay");
				sanjay.setEmail("san@gmail.com");
				sanjay.setPassword("sanjayThanPassword");

				userv.addUser(sanjay);

				CartItem item1 = new CartItem();
				item1.setQuantity(2);
				AddCartItemRequest request1 = new AddCartItemRequest(sanjay.getCart().getCartId(),"PROD00001" ,2,"6374202368");
				AddCartItemRequest request3 = new AddCartItemRequest(sanjay.getCart().getCartId(),"PROD00002" ,2,"6374202368");
				cartService.addCartItem(request1);
				cartService.addCartItem(request3);

				CartCheckOutRequest checout = new CartCheckOutRequest();
				checout.setCartId(sanjay.getCart().getCartId());
				checout.setUserId(sanjay.getId());
				checout.setPaymentType("cash_On Delivery");
				checout.setDelivaryAddress("193/2 vinayagar street pulavanpadi Arni");

				orderService.checkOut(checout);

				request1.setQuantity(5);
				AddCartItemRequest request4 = new AddCartItemRequest(sanjay.getCart().getCartId(),"PROD00003" ,2,"6374202368");

				cartService.addCartItem(request1);

				cartService.addCartItem(request3);
				cartService.addCartItem(request4);

				orderService.checkOut(checout);





			} catch (IllegalStateException e){
				System.out.println(e.getMessage());
			} catch (Exception e){
				e.printStackTrace();
			}



		};

	}
}
