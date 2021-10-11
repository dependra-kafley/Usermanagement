package com.dependra.restfullearning.restfullearning.dto;

import com.dependra.restfullearning.restfullearning.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public UserGetDto entityToDto(User user){
        UserGetDto userGetDto=new UserGetDto();
        userGetDto.setEmail(user.getEmail());
        userGetDto.setName(user.getName());
        userGetDto.setId(user.getId());
        userGetDto.setPhoneNo(user.getPhoneNo());
        userGetDto.setCoursesList(user.getCoursesList());
        userGetDto.setAddress(user.getAddress());

        return  userGetDto;
    }

    public User dtoToUser(UserPostDto userPostDto){
        User user=new User();
        user.setEmail(userPostDto.getEmail());
        user.setName(userPostDto.getName());
        user.setId(userPostDto.getId());
        user.setPassword(userPostDto.getPassword());
        user.setPhoneNo(userPostDto.getPhoneNo());
        user.setAddress(userPostDto.getAddress());

        return  user;
    }

    public List<UserGetDto> entityToDto(List<User> users){
        return users.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
