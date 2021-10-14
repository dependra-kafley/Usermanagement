package com.dependra.restfullearning.restfullearning.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;
    @NotNull
    private String courseName;
    @NotNull
    private String courseDetail;
    @OneToMany(mappedBy = "courses")
    @JsonIgnore
    Set<UserCourse> userCourse;


    public Courses(int courseId, String courseName, String courseDetail, Set<User> user) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDetail = courseDetail;

    }

    public Courses() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    public Set<UserCourse> getUserCourse() {
        return userCourse;
    }

    public void setUserCourse(Set<UserCourse> userCourse) {
        this.userCourse = userCourse;
    }
}
