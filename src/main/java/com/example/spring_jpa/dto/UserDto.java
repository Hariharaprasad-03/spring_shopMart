package com.example.spring_jpa.dto;

import com.example.spring_jpa.model.User;

import java.io.Serializable;

public record UserDto (String userName ,
                       String mobileNumer ,
                       String emailAddress) implements Serializable {




}
