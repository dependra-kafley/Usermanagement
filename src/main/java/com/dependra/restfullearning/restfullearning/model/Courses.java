package com.dependra.restfullearning.restfullearning.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;
    @NotNull
    private String courseName;
    @NotNull
    private String courseDetail;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_course",
            joinColumns = @JoinColumn(name="course_id"),

            inverseJoinColumns = @JoinColumn(name = "user_id")

    )
    @JsonIgnore
        private List <User> user;

    public Courses(int courseId, String courseName, String courseDetail, List<User> user) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDetail = courseDetail;
        this.user = user;
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

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }


}
