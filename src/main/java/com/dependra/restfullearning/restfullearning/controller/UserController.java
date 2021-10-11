package com.dependra.restfullearning.restfullearning.controller;
import com.dependra.restfullearning.restfullearning.dto.*;
import com.dependra.restfullearning.restfullearning.exceptions.CustomizedResponseEntityExceptionHandler;
import com.dependra.restfullearning.restfullearning.model.Courses;
import com.dependra.restfullearning.restfullearning.model.User;
import com.dependra.restfullearning.restfullearning.service.CoursesService;
import com.dependra.restfullearning.restfullearning.service.UserService;
import com.dependra.restfullearning.restfullearning.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CoursesService coursesService;

    @Autowired
    UserConverter userConverter;

    @Autowired
    CourseConverter courseConverter;

    @GetMapping("/users")
    public ResponseEntity<List<UserGetDto>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        if (userList == null) {
            throw new UserNotFoundException("No users Found");
        } else {
            return ResponseEntity.of(Optional.of(userConverter.entityToDto(userList)));
        }
    }

    @GetMapping("/usersSorted/{field}")
    public ResponseEntity<List<UserGetDto>> getSortedUser(@PathVariable String field) {
        List<User> userList = userService.getSortedUser(field);
        if (userList == null) {
            throw new UserNotFoundException("No users Found");
        } else {
            return ResponseEntity.of(Optional.of(userConverter.entityToDto(userList)));
        }
    }

    @GetMapping("/usersPaginates/{page}")
    public ResponseEntity<Page<UserGetDto>> getPaginatedUsers(@PathVariable int page){
        Pageable pageable= PageRequest.of(page,2);
        Page<User> users=userService.getPaginatedUser(pageable);
        Page<UserGetDto> userGetDtos=users.map(user -> {
            UserGetDto userGetDto=new UserGetDto();
            userGetDto.setName(user.getName());
            userGetDto.setEmail(user.getEmail());
            userGetDto.setId(user.getId());
            userGetDto.setCoursesList(user.getCoursesList());
            userGetDto.setPhoneNo(user.getPhoneNo());
            userGetDto.setAddress(user.getAddress());
            return userGetDto;
        });
        return ResponseEntity.of(Optional.of(userGetDtos));

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserGetDto> getUserById(@PathVariable int id) {
        User user = this.userService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id does not exist");
        } else {
            return ResponseEntity.of(Optional.of(userConverter.entityToDto(user)));
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> adduser(@Valid @RequestBody UserPostDto userPostDto) {
        User user=userConverter.dtoToUser(userPostDto);
        System.out.println(user.toString());
        User savedUser = null;

        if (user.getName() == null || user.getAddress() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            try {
                savedUser = userService.addUser(user);
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId()).toUri();
                return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
        }

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        try {
            this.userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody User user) {
        try {
            if (this.userService.getUserById(id) == null) {
                throw new UserNotFoundException("User Not Found");
            }

            this.userService.updateUser(user, id);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/{id}/courses")
    public ResponseEntity<List<Courses>> getCoursesEnrolled(@PathVariable int id) {
        User user = this.userService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id does not exist");
        } else {
            List<Courses> coursesList = user.getCoursesList();
            return ResponseEntity.of(Optional.of(coursesList));
        }
    }

    @PostMapping("/users/{id}/courses")
    public ResponseEntity<User> addCourse(@PathVariable int id, @Valid @RequestBody CoursePostDto coursePostDto) {
        User user = this.userService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id does not exist");
        } else {
            Courses courses=courseConverter.dtoToUser(coursePostDto);
            Courses courses1=coursesService.getCourseById(courses.getCourseId());
            courses1.getUser().add(user);
            Courses saveduser=coursesService.updateCourses(courses1,courses1.getCourseId());
            return ResponseEntity.ok().build();
        }
    }

//    @DeleteMapping("/users/{userid}")
//    public ResponseEntity deleteCourse(@PathVariable int userid) {
//        try {
//            userService.deleteUser(userid);
//            return ResponseEntity.status(HttpStatus.OK).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//    }

//    @PutMapping("/users/{userid}/courses/{courseid}")
//    public ResponseEntity<Courses> updateCourses(@PathVariable int userid, @PathVariable int courseid,
//                                                 @RequestBody Courses courses) {
//        try {
//            if (this.userService.getUserById(userid) == null) {
//                throw new UserNotFoundException("User Not Found");
//            }
//
//            this.coursesService.updateCourses(courses, courseid);
//            return ResponseEntity.ok().body(courses);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }

}
