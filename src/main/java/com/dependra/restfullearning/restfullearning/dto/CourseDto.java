package com.dependra.restfullearning.restfullearning.dto;
import com.dependra.restfullearning.restfullearning.model.Courses;
import java.util.Set;
import java.util.stream.Collectors;


public class CourseDto {
    private  int id;
    private String courseDetail;
    private String courseName;


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

    public CourseDto entityToDto(Courses courses){
        CourseDto courseGetDto=new CourseDto();
        courseGetDto.setCourseDetail(courses.getCourseDetail());
        courseGetDto.setCourseName(courses.getCourseName());
        courseGetDto.setId(courses.getCourseId());

        //courseGetDto.setUserList(userConverter.entityToDto(courses.getUser()));

        return  courseGetDto;
    }

    public Courses dtoToUser(CourseDto coursePostDto){
        Courses courses=new Courses();
        courses.setCourseDetail(coursePostDto.getCourseDetail());
        courses.setCourseName(coursePostDto.getCourseName());
        courses.setCourseId(coursePostDto.getId());
        return  courses;
    }

    public Set<CourseDto> entityToDto(Set<Courses> courses){
        return courses.stream().map(this::entityToDto).collect(Collectors.toSet());
    }

}
