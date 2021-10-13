package com.dependra.restfullearning.restfullearning.service;

import com.dependra.restfullearning.restfullearning.dao.UserCourseRepository;
import com.dependra.restfullearning.restfullearning.model.UserCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCourseService {
    @Autowired
    UserCourseRepository userCourseRepository;

    public UserCourse addCourseUser(UserCourse userCourse){
       return userCourseRepository.save(userCourse);
    }

    public void deleteUserCourse(int id){
        userCourseRepository.deleteById(id);
    }

    public List<UserCourse> getAllUserCourse(){
        return userCourseRepository.findAll();
    }
}
