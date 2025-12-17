package com.example.spring_jpa.services;

import com.example.spring_jpa.dto.CartDto;
import com.example.spring_jpa.exception.CartNotAvailableException;
import com.example.spring_jpa.exception.NoProductExistException;
import com.example.spring_jpa.mapper.CartMapper;
import com.example.spring_jpa.model.Cart;
import com.example.spring_jpa.model.CartItem;
import com.example.spring_jpa.model.Product;
import com.example.spring_jpa.model.User;
import com.example.spring_jpa.repository.CartItemRepository;
import com.example.spring_jpa.repository.CartRepository;
import com.example.spring_jpa.repository.ProductRepository;
import com.example.spring_jpa.payload.AddCartItemRequest;
import com.example.spring_jpa.payload.RemoveCartItemRequest;

import com.example.spring_jpa.util.IdGeneratorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class CartService {


    @Autowired
    CartRepository cartRepository ;
    @Autowired
    ProductRepository productRepository ;
    @Autowired
    CartMapper mapper ;
    @Autowired
    IdGeneratorService idGenerator ;
    @Autowired
    private CartItemRepository cartItemRepository;

    public CartDto addCartToUser(User user){
        Cart userCart = new Cart();
        userCart.setCartId(idGenerator.generateId("CART"));
        userCart.setUser(user);
        return mapper.toCartDto(userCart);

    }

    @Transactional
    public CartDto addCartItem(AddCartItemRequest request) {

        Optional<Product> productOptional = productRepository.findById(request.getProductId());
        if (productOptional.isEmpty()) {
            throw new NoProductExistException("no produt Available int such product Id");
        }

        Optional<Cart> cartOptional = cartRepository.findById(request.getCartId());
        if (cartOptional.isEmpty()) {
            throw new CartNotAvailableException("No cart Exist int that Id");
        }
        Cart cart = cartOptional.get();
        Product product = productOptional.get();

        Optional<CartItem> existing = cart.getItems().stream()
                .filter(ci -> product.getId().equals(ci.getProduct().getId()))
                .findFirst();

        if (existing.isPresent()) {
            CartItem ci = existing.get();
            ci.setQuantity(ci.getQuantity() + request.getQuantity());

        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(request.getQuantity());
            cart.addItem(newItem);

        }
        Cart saved = cartRepository.save(cart);
        return mapper.toCartDto(saved);
    }

    @Transactional
    public CartDto removeItemFromCart(RemoveCartItemRequest request) {

        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new CartNotAvailableException("Cart not found"));


        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct() != null
                        && request.getProductId().equals(item.getProduct().getId()))
                .findFirst()
                .orElseThrow(() -> new CartNotAvailableException("Product not found in cart"));

        int removeQty = Math.max(0, request.getQuantity());

        if (removeQty >= cartItem.getQuantity()) {

            cart.removeItem(cartItem);
        } else {

            cartItem.setQuantity(cartItem.getQuantity() - removeQty);
        }


        Cart saved = cartRepository.save(cart);

        return mapper.toCartDto(saved);
    }

    @Transactional
    public void clearUserCart(User user) {

        String cartId = user.getCart().getCartId();
        cartItemRepository.deleteCartItemByCartId(cartId);
    }
}
