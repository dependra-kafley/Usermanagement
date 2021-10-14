package com.dependra.restfullearning.restfullearning.dto;

import com.dependra.restfullearning.restfullearning.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


public class UserDto {
    private int id;
    private String name;
    private  String email;
    private String phoneNo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String address;

    public UserDto(String name, String email, String phoneNo, String address) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public UserDto() {
    }

    public UserDto(int id, String name, String email, String phoneNo, String password, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.address = address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserDto entityToDto(User user){
        UserDto  userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setId(user.getId());
        userDto.setPhoneNo(user.getPhoneNo());
        //userGetDto.setCoursesList(user.getCoursesList());
        userDto.setAddress(user.getAddress());

        return  userDto;
    }

    public User dtoToUser(UserDto userPostDto){
        User user=new User();
        user.setEmail(userPostDto.getEmail());
        user.setName(userPostDto.getName());
        user.setId(userPostDto.getId());
        user.setPassword(userPostDto.getPassword());
        user.setPhoneNo(userPostDto.getPhoneNo());
        user.setAddress(userPostDto.getAddress());

        return  user;
    }

    public List<UserDto> entityToDto(List<User> users){
        return users.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
