package com.example.spring_jpa.mapper;

import com.example.spring_jpa.dto.UserDto;
import com.example.spring_jpa.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoMapper {

    public UserDto toUserDto(User user){

        return new UserDto(user.getName(),
                user.getId(),
                user.getEmail());
    }

    public List<UserDto> userlistTOUserDtoList(List<User> users){

        return users.stream().map(this::toUserDto)
                .collect(Collectors.toList());
    }
}
