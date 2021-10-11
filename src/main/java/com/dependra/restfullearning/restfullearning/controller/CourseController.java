package com.dependra.restfullearning.restfullearning.controller;

import com.dependra.restfullearning.restfullearning.dto.*;
import com.dependra.restfullearning.restfullearning.exceptions.CourseNotFoundException;
import com.dependra.restfullearning.restfullearning.exceptions.UserNotFoundException;
import com.dependra.restfullearning.restfullearning.model.Courses;
import com.dependra.restfullearning.restfullearning.model.User;
import com.dependra.restfullearning.restfullearning.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {
    @Autowired
    CoursesService coursesService;

    @Autowired
    CourseConverter courseConverter;

    @Autowired
    UserConverter userConverter;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseGetDto>> getAllCourses(){
        List<Courses> coursesList=coursesService.getAllCourses();
        if(coursesList==null){
            throw new CourseNotFoundException("No course Found");
        }
        else {

            return ResponseEntity.of(Optional.of(courseConverter.entityToDto(coursesList)));
        }
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseGetDto> addNewCourse(@Valid @RequestBody CoursePostDto coursePostDto){
        Courses courses=courseConverter.dtoToUser(coursePostDto);
        Courses courses1=coursesService.addCourse(courses);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(courses1.getCourseId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }

    @PostMapping("/courses/{id}")
    public ResponseEntity<CourseGetDto> addNewUserForcourse(@PathVariable int id,@RequestBody UserPostDto userPostDto){
        Courses courses=coursesService.getCourseById(id);
        User user=userConverter.dtoToUser(userPostDto);

        courses.getUser().add(user);
        Courses SavedCourses= coursesService.updateCourses(courses,id);
        CourseGetDto courseGetDto=courseConverter.entityToDto(SavedCourses);
        return ResponseEntity.status(HttpStatus.OK).body(courseGetDto);
    }



    @DeleteMapping  ("/courses/{id}")
    public ResponseEntity deleteCourse(@PathVariable int id){
        if(coursesService.getCourseById(id)==null){
            throw new UserNotFoundException("Uer not found");
        }
        else{
            coursesService.deleteCourse(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
