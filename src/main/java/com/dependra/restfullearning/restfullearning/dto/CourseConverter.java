package com.dependra.restfullearning.restfullearning.dto;

import com.dependra.restfullearning.restfullearning.model.Courses;
import com.dependra.restfullearning.restfullearning.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseConverter {

    @Autowired
    UserConverter userConverter;

    public CourseGetDto entityToDto(Courses courses){
        CourseGetDto courseGetDto=new CourseGetDto();
        courseGetDto.setCourseDetail(courses.getCourseDetail());
        courseGetDto.setCourseName(courses.getCourseName());
        courseGetDto.setId(courses.getCourseId());

        courseGetDto.setUserList(userConverter.entityToDto(courses.getUser()));

        return  courseGetDto;
    }

    public Courses dtoToUser(CoursePostDto coursePostDto){
        Courses courses=new Courses();
        courses.setCourseDetail(coursePostDto.getCourseDetail());
        courses.setCourseName(coursePostDto.getCourseName());
        courses.setCourseId(coursePostDto.getId());
        return  courses;
    }

    public List<CourseGetDto> entityToDto(List<Courses> courses){
        return courses.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
