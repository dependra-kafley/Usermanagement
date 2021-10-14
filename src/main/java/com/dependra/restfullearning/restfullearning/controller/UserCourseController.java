package com.dependra.restfullearning.restfullearning.controller;
import com.dependra.restfullearning.restfullearning.dto.UserCourseDto;
import com.dependra.restfullearning.restfullearning.exceptions.CourseNotFoundException;
import com.dependra.restfullearning.restfullearning.exceptions.UserNotFoundException;
import com.dependra.restfullearning.restfullearning.model.UserCourse;
import com.dependra.restfullearning.restfullearning.service.CoursesService;
import com.dependra.restfullearning.restfullearning.service.UserCourseService;
import com.dependra.restfullearning.restfullearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserCourseController {


    @Autowired
    UserCourseService userCourseService;

    @Autowired
    UserService userService;

    @Autowired
    CoursesService coursesService;

    UserCourseDto userCourseDto=new UserCourseDto();

    @GetMapping("/usersCourses")
    public ResponseEntity<List<UserCourseDto>> getAll() {
        List<UserCourse> courses = userCourseService.getAllUserCourse();
        return ResponseEntity.of(Optional.of(new UserCourseDto().entityToDto(courses)));

    }

    @PostMapping("/usersCourses")
    public ResponseEntity addUserCourse(@RequestBody UserCourseDto userCourseDto) {

        try {
            UserCourse userCourse = new UserCourse();
            System.out.println("userId" + userService.getUserById(userCourseDto.getUserId()));
            System.out.println("courseId" + userCourseDto.getCourseId());
            if (userService.getUserById(userCourseDto.getUserId()) == null) {
                throw new UserNotFoundException("User does not Exists ");
            } else if (coursesService.getCourseById(userCourseDto.getCourseId()) == null) {
                throw new CourseNotFoundException("Course Does not exists");
            } else {
                userCourse.setUser(userService.getUserById(userCourseDto.getUserId()));
                userCourse.setCourses(coursesService.getCourseById(userCourseDto.getCourseId()));
                userCourseService.addCourseUser(userCourse);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }

        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("/usersCourses/{id}")
    public ResponseEntity deleteUserCourse(@PathVariable int id) {
        userCourseService.deleteUserCourse(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
