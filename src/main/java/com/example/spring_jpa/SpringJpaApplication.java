package com.example.spring_jpa;


import com.example.spring_jpa.model.*;

import com.example.spring_jpa.payload.UpdateProductRequest;
import com.example.spring_jpa.repository.CartRepository;

import com.example.spring_jpa.payload.AddCartItemRequest;
import com.example.spring_jpa.payload.AddProductRequest;
import com.example.spring_jpa.payload.CartCheckOutRequest;
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

import java.util.Arrays;
import java.util.List;
import java.util.Random;


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
				Product added = productService.addProduct(request);

				AddProductRequest request2 = new AddProductRequest(
						"orange",
						"fruits",
						100.0,
						100,
						10
				);
				Product orange =productService.addProduct(request2);

				AddProductRequest request_banana = new AddProductRequest(
						"banana",
						"fruits",
						10.0,
						100,
						0
				);
				Product banana = productService.addProduct(request_banana);

				AddProductRequest request_mango = new AddProductRequest(
						"mango",
						"fruits",
						150.0,
						40,
						0
				);
				Product mango = productService.addProduct(request_mango);

				AddProductRequest request_pineapple = new AddProductRequest(
						"pineapple",
						"fruits",
						90.0,
						30,
						0
				);
				Product papple = productService.addProduct(request_pineapple);

				AddProductRequest request_grapes = new AddProductRequest(
						"grapes",
						"fruits",
						70.0,
						45,
						0
				);
				Product grape = productService.addProduct(request_grapes);

				AddProductRequest request_watermelon = new AddProductRequest(
						"watermelon",
						"fruits",
						60.0,
						25,
						0
				);
				Product water = productService.addProduct(request_watermelon);

				AddProductRequest request_papaya = new AddProductRequest(
						"papaya",
						"fruits",
						55.0,
						35,
						0
				);
				Product pappaya = productService.addProduct(request_papaya);

				AddProductRequest request_guava = new AddProductRequest(
						"guava",
						"fruits",
						65.0,
						40,
						0
				);
				Product gauva = productService.addProduct(request_guava);

				AddProductRequest request_tomato = new AddProductRequest(
						"tomato",
						"vegetables",
						30.0,
						100,
						0
				);
				Product tomoto = productService.addProduct(request_tomato);

				AddProductRequest request_potato = new AddProductRequest(
						"potato",
						"vegetables",
						25.0,
						150,
						0
				);
				Product pototo = productService.addProduct(request_potato);

				AddProductRequest request_onion = new AddProductRequest(
						"onion",
						"vegetables",
						28.0,
						120,
						0
				);
				Product onion = productService.addProduct(request_onion);

				AddProductRequest request_carrot = new AddProductRequest(
						"carrot",
						"vegetables",
						40.0,
						70,
						0
				);
				Product carrot = productService.addProduct(request_carrot);

				AddProductRequest request_cabbage = new AddProductRequest(
						"cabbage",
						"vegetables",
						35.0,
						50,
						0
				);
				Product cabbage = productService.addProduct(request_cabbage);

				AddProductRequest request_broccoli = new AddProductRequest(
						"broccoli",
						"vegetables",
						90.0,
						30,
						0
				);
				Product bracooli = productService.addProduct(request_broccoli);

				AddProductRequest request_capsicum = new AddProductRequest(
						"capsicum",
						"vegetables",
						85.0,
						40,
						0
				);
				Product capsicum = productService.addProduct(request_capsicum);

				AddProductRequest request_almonds = new AddProductRequest(
						"almonds",
						"dry_fruits",
						800.0,
						20,
						0
				);
				Product almods = productService.addProduct(request_almonds);

				AddProductRequest request_cashews = new AddProductRequest(
						"cashews",
						"dry_fruits",
						900.0,
						15,
						0
				);
				Product caashew = productService.addProduct(request_cashews);

				AddProductRequest request_raisins = new AddProductRequest(
						"raisins",
						"dry_fruits",
						450.0,
						25,
						0
				);
				Product rasins  = productService.addProduct(request_raisins);


				AddProductRequest request_pistachios = new AddProductRequest(
						"pistachios",
						"dry_fruits",
						950.0,
						10,
						0
				);
				Product p = productService.addProduct(request_pistachios);

				AddProductRequest request_walnuts = new AddProductRequest(
						"walnuts",
						"dry_fruits",
						850.0,
						12,
						0
				);
				Product wn = productService.addProduct(request_walnuts);


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

				UpdateProductRequest upRequest = new UpdateProductRequest("PROD00001" ,50,120,5);
				productService.updateProduct(upRequest);

				User hari = new User();
				hari.setName("Hari");
				hari.setId("9994545923");
				hari.setEmail("hariharaprasad03@gamil.com");
				hari.setPassword("hari@123");

				userv.addUser(hari);

				User user2 = new User();
				user2.setId("10002");
				user2.setName("rahul");
				user2.setEmail("rahul@gmail.com");
				user2.setPassword("password2");
				userv.addUser(user2);

				User user3 = new User();
				user3.setId("10003");
				user3.setName("arun");
				user3.setEmail("arun@gmail.com");
				user3.setPassword("password3");
				userv.addUser(user3);

				User user4 = new User();
				user4.setId("10004");
				user4.setName("vijay");
				user4.setEmail("vijay@gmail.com");
				user4.setPassword("password4");
				userv.addUser(user4);

				User user5 = new User();
				user5.setId("10005");
				user5.setName("karthik");
				user5.setEmail("karthik@gmail.com");
				user5.setPassword("password5");
				userv.addUser(user5);

				User user6 = new User();
				user6.setId("10006");
				user6.setName("praveen");
				user6.setEmail("praveen@gmail.com");
				user6.setPassword("password6");
				userv.addUser(user6);

				User user7 = new User();
				user7.setId("10007");
				user7.setName("naveen");
				user7.setEmail("naveen@gmail.com");
				user7.setPassword("password7");
				userv.addUser(user7);

				User user8 = new User();
				user8.setId("10008");
				user8.setName("deepak");
				user8.setEmail("deepak@gmail.com");
				user8.setPassword("password8");
				userv.addUser(user8);

				User user9 = new User();
				user9.setId("10009");
				user9.setName("surya");
				user9.setEmail("surya@gmail.com");
				user9.setPassword("password9");
				userv.addUser(user9);

				User user10 = new User();
				user10.setId("10010");
				user10.setName("ram");
				user10.setEmail("ram@gmail.com");
				user10.setPassword("password10");
				userv.addUser(user10);

				User user11 = new User();
				user11.setId("10011");
				user11.setName("suresh");
				user11.setEmail("suresh@gmail.com");
				user11.setPassword("password11");
				userv.addUser(user11);

				User user12 = new User();
				user12.setId("10012");
				user12.setName("ramesh");
				user12.setEmail("ramesh@gmail.com");
				user12.setPassword("password12");
				userv.addUser(user12);

				User user13 = new User();
				user13.setId("10013");
				user13.setName("hari");
				user13.setEmail("hari@gmail.com");
				user13.setPassword("password13");
				userv.addUser(user13);

				User user14 = new User();
				user14.setId("10014");
				user14.setName("ajay");
				user14.setEmail("ajay@gmail.com");
				user14.setPassword("password14");
				userv.addUser(user14);

				User user15 = new User();
				user15.setId("10015");
				user15.setName("manoj");
				user15.setEmail("manoj@gmail.com");
				user15.setPassword("password15");
				userv.addUser(user15);

				User user16 = new User();
				user16.setId("10016");
				user16.setName("kiran");
				user16.setEmail("kiran@gmail.com");
				user16.setPassword("password16");
				userv.addUser(user16);

				User user17 = new User();
				user17.setId("10017");
				user17.setName("vinay");
				user17.setEmail("vinay@gmail.com");
				user17.setPassword("password17");
				userv.addUser(user17);

				User user18 = new User();
				user18.setId("10018");
				user18.setName("rohit");
				user18.setEmail("rohit@gmail.com");
				user18.setPassword("password18");
				userv.addUser(user18);

				User user19 = new User();
				user19.setId("10019");
				user19.setName("anil");
				user19.setEmail("anil@gmail.com");
				user19.setPassword("password19");
				userv.addUser(user19);

				User user20 = new User();
				user20.setId("10020");
				user20.setName("varun");
				user20.setEmail("varun@gmail.com");
				user20.setPassword("password20");
				userv.addUser(user20);

				User user21 = new User();
				user21.setId("10021");
				user21.setName("akash");
				user21.setEmail("akash@gmail.com");
				user21.setPassword("password21");
				userv.addUser(user21);

				User user22 = new User();
				user22.setId("10022");
				user22.setName("tarun");
				user22.setEmail("tarun@gmail.com");
				user22.setPassword("password22");
				userv.addUser(user22);

				User user23 = new User();
				user23.setId("10023");
				user23.setName("ravi");
				user23.setEmail("ravi@gmail.com");
				user23.setPassword("password23");
				userv.addUser(user23);

				User user24 = new User();
				user24.setId("10024");
				user24.setName("sathish");
				user24.setEmail("sathish@gmail.com");
				user24.setPassword("password24");
				userv.addUser(user24);

				User user25 = new User();
				user25.setId("10025");
				user25.setName("gokul");
				user25.setEmail("gokul@gmail.com");
				user25.setPassword("password25");
				userv.addUser(user25);

				Random random = new Random();

				List<User> users = Arrays.asList(
						hari,user2,user3,user4,user5,user6,user7,user8,user9,user10,
						user11,user12,user13,user14,user15,user16,user17,user18,user19,user20,
						user21,user22,user23,user24,user25
				);

				List<String> products = Arrays.asList(
						"PROD00001","PROD00002","PROD00003","PROD00004","PROD00005",
						"PROD00006","PROD00007","PROD00008","PROD00009","PROD00010",
						"PROD00011","PROD00012","PROD00013","PROD00014","PROD00015",
						"PROD00016","PROD00017","PROD00018","PROD00019","PROD00020"
				);

				String[] addresses = {
						"193/2 Vinayagar Street Arni",
						"45 Gandhi Nagar Chennai",
						"12 Anna Street Vellore",
						"90 Lake Road Bangalore",
						"55 MG Road Coimbatore"
				};

				for(int i = 0; i < 20; i++) {

					User user = users.get(random.nextInt(users.size()));

					int productCount = 1 + random.nextInt(3); // 1–3 products

					for(int j = 0; j < productCount; j++) {

						String productId = products.get(random.nextInt(products.size()));
						int quantity = 1 + random.nextInt(4);

						AddCartItemRequest request12 =
								new AddCartItemRequest(
										user.getCart().getCartId(),
										productId,
										quantity,
										user.getId()
								);

						cartService.addCartItem(request12);
					}

					CartCheckOutRequest checkout = new CartCheckOutRequest();
					checkout.setCartId(user.getCart().getCartId());
					checkout.setUserId(user.getId());
					checkout.setPaymentType(random.nextBoolean() ? "Cash_On_Delivery" : "UPI");
					checkout.setDelivaryAddress(addresses[random.nextInt(addresses.length)]);

					orderService.checkOut(checkout);
				}

			} catch (IllegalStateException e){
				System.out.println(e.getMessage());
			} catch (Exception e){
				e.printStackTrace();
			}



		};

	}
}
