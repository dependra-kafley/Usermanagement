package com.dependra.restfullearning.restfullearning.dao;

import com.dependra.restfullearning.restfullearning.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

    public Courses findById(int id);
}
