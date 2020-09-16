package com.sw.entity;

import java.io.Serializable;
import java.util.List;

public class Sc implements Serializable {
    private String s_id;
    private String c_id;
    private Integer grade;

    private List<Student> students;
    private List<Course> courses;

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courcss) {
        this.courses = courcss;
    }

    @Override
    public String toString() {
        return "Sc{" +
                "s_id='" + s_id + '\'' +
                ", c_id='" + c_id + '\'' +
                ", grade='" + grade + '\'' +
                ", students=" + students +
                ", courcss=" + courses +
                '}';
    }
}
