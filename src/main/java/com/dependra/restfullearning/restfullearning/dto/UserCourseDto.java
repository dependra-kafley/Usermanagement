package com.dependra.restfullearning.restfullearning.dto;

import com.dependra.restfullearning.restfullearning.model.Courses;
import com.dependra.restfullearning.restfullearning.model.User;
import com.dependra.restfullearning.restfullearning.model.UserCourse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class UserCourseDto {
   private int id;
   @JsonProperty(access = JsonProperty.Access.READ_ONLY)
   private User user;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
   private Courses courses;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int courseId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public UserCourseDto entityToDto(UserCourse courses){
        UserCourseDto courseGetDto=new UserCourseDto();
        courseGetDto.setId(courses.getId());
        courseGetDto.setCourses(courses.getCourses());
        courseGetDto.setUser(courses.getUser());

        //courseGetDto.setUserList(userConverter.entityToDto(courses.getUser()));

        return  courseGetDto;
    }

//    public UserCourse dtoToUser(UserCourseDto coursePostDto){
//        UserCourse courses=new UserCourse();
//        courses.setUser();
//        courses.setId(coursePostDto.getId());
//        return  courses;
//    }

    public List<UserCourseDto> entityToDto(List<UserCourse> courses){
        return courses.stream().map(courses1 -> entityToDto(courses1)).collect(Collectors.toList());
    }
}
