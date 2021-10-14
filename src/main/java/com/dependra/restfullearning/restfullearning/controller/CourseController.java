package com.dependra.restfullearning.restfullearning.controller;

import com.dependra.restfullearning.restfullearning.dto.*;
import com.dependra.restfullearning.restfullearning.exceptions.CourseNotFoundException;
import com.dependra.restfullearning.restfullearning.exceptions.UserNotFoundException;
import com.dependra.restfullearning.restfullearning.model.Courses;
import com.dependra.restfullearning.restfullearning.model.User;
import com.dependra.restfullearning.restfullearning.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CourseController {
    @Autowired
    CoursesService coursesService;


   CourseDto courseDto = new CourseDto();



    @GetMapping("/courses")
    public ResponseEntity<List<Courses>> getAllCourses(){
        List<Courses> coursesList=coursesService.getAllCourses();
        if(coursesList==null){
            throw new CourseNotFoundException("No course Found");
        }
        else {
            List<Courses> coursesList1=coursesList.stream().collect(Collectors.toList());
            return ResponseEntity.of(Optional.of(coursesList1));
        }
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseDto> addNewCourse(@Valid @RequestBody CourseDto coursePostDto){
        Courses courses=courseDto.dtoToUser(coursePostDto);
        Courses courses1=coursesService.addCourse(courses);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(courses1.getCourseId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/courses/{id}")
    public ResponseEntity<CourseDto> addNewUserForcourse(@PathVariable int id, @RequestBody UserDto userPostDto){
        Courses courses=coursesService.getCourseById(id);
        User user=userPostDto.dtoToUser(userPostDto);

        //courses.getUser().add(user);
        Courses SavedCourses= coursesService.updateCourses(courses,id);
        CourseDto courseGetDto=courseDto.entityToDto(SavedCourses);

        return ResponseEntity.status(HttpStatus.OK).body(courseGetDto);
    }

    @GetMapping("/coursePageable")
    public ResponseEntity getPageable(Pageable pageable){
          Page<Courses> courses= coursesService.getAllPageableCourses(pageable);
            return ResponseEntity.of(Optional.of(courses));
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
