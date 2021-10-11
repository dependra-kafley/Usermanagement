package com.dependra.restfullearning.restfullearning.dto;

import com.dependra.restfullearning.restfullearning.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseGetDto {
    private  int id;
    private String courseDetail;
    private String courseName;
    private List<UserGetDto> userList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<UserGetDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserGetDto> userList) {
        this.userList = userList;
    }
}
