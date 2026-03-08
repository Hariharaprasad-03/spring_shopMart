package com.example.spring_jpa.services;

import com.example.spring_jpa.dto.OrderDto;
import com.example.spring_jpa.dto.UserDto;
import com.example.spring_jpa.exception.UserNotExistException;
import com.example.spring_jpa.mapper.OrderDtoMapper;
import com.example.spring_jpa.mapper.UserDtoMapper;
import com.example.spring_jpa.model.Cart;
import com.example.spring_jpa.model.Order;
import com.example.spring_jpa.model.User;
import com.example.spring_jpa.repository.UserRepository;
import com.example.spring_jpa.util.IdGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository ;
    @Autowired
    IdGeneratorService idGeneratorService;
    @Autowired
    OrderDtoMapper mapper ;
    @Autowired
    UserDtoMapper userDtoMapper;



    public User addUser(User user){

        Cart userCart = new Cart();
        userCart.setCartId(idGeneratorService.generateId("CART"));
        userCart.setUser(user);
        user.setCart(userCart);
        return userRepository.save(user);

    }


    public User getUserbyId(String id ) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()){
            throw new UserNotExistException("BadRequest User Not exist ");
        }
        return userOptional.get();
    }


    public List<OrderDto> getAllOrders(String userId){

        List<OrderDto> dtos = new ArrayList<>();
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            User userPresent = user.get();
            for(Order order : userPresent.getOrders()){
                dtos.add(mapper.toDto(order));
            }
        }
        return dtos;
    }

    public List<UserDto> getAllUsers(){
        return userDtoMapper.userlistTOUserDtoList(userRepository.findAll());
    }


}



