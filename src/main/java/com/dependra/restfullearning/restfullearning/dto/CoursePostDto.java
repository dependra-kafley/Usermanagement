package com.dependra.restfullearning.restfullearning.dto;

import org.springframework.stereotype.Component;

@Component
public class CoursePostDto {
    private  int id;
    private  String courseName;
    private String courseDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
