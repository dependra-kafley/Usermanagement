package com.dependra.restfullearning.restfullearning.model;

import javax.persistence.*;

@Entity
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "user_id",nullable = false)

    private User user;

    @ManyToOne()
    @JoinColumn(name = "course_id",nullable = false)

    private Courses courses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }
}
