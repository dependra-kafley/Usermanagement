package com.dependra.restfullearning.restfullearning.dao;

import com.dependra.restfullearning.restfullearning.model.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseRepository extends JpaRepository<UserCourse,Integer> {
    public UserCourseRepository findById(int id);
}
