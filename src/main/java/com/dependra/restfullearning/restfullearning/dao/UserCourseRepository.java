package com.dependra.restfullearning.restfullearning.dao;

import com.dependra.restfullearning.restfullearning.model.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse,Integer> {
    public UserCourseRepository findById(int id);

    @Query(value ="Select * from user_course where user_id =:id",nativeQuery = true)
    public List<UserCourse> getByUserId(@Param("id")int id);

}
