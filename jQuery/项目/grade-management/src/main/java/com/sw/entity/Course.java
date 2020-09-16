package com.sw.entity;

import java.io.Serializable;

public class Course implements Serializable {
    private String c_id;
    private String c_name;
    private String c_teacher;

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_teacher() {
        return c_teacher;
    }

    public void setC_teacher(String t_teacher) {
        this.c_teacher = t_teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "c_id='" + c_id + '\'' +
                ", c_name='" + c_name + '\'' +
                ", t_teacher='" + c_teacher + '\'' +
                '}';
    }
}
