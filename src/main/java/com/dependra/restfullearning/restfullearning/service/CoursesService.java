package com.dependra.restfullearning.restfullearning.service;

import com.dependra.restfullearning.restfullearning.dao.CoursesRepository;
import com.dependra.restfullearning.restfullearning.model.Courses;
import com.dependra.restfullearning.restfullearning.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {

    @Autowired
    CoursesRepository coursesRepository;

    public Courses addCourse(Courses course){
        return this.coursesRepository.save(course);
    }

    public void deleteCourse(int id){
        coursesRepository.deleteById(id);
    }

    public Courses updateCourses(Courses courses, int id){
        courses.setCourseId(id);
        return coursesRepository.save(courses);

    }

    public List<Courses> getAllCourses(){
        return coursesRepository.findAll();
    }

    public Courses getCourseById(int id){
        return coursesRepository.findById(id);
    }
}
