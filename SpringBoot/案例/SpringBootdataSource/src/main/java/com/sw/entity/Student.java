package com.sw.entity;

import lombok.Data;

@Data
public class Student {
    private String s_id;
    private String s_name;
    private String s_sex;
    private Integer s_age;

    public Student(String s_id,String s_name,String s_sex,Integer s_age) {
        this.s_id = s_id;
        this.s_name = s_name;
        this.s_sex = s_sex;
        this.s_age = s_age;
    }
    @Override
    public String toString() {
        return "Student{" +
                "s_id='" + s_id + '\'' +
                ", s_name='" + s_name + '\'' +
                ", s_sex='" + s_sex + '\'' +
                ", s_age=" + s_age +
                '}';
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_sex() {
        return s_sex;
    }

    public void setS_sex(String s_sex) {
        this.s_sex = s_sex;
    }

    public Integer getS_age() {
        return s_age;
    }

    public void setS_age(Integer s_age) {
        this.s_age = s_age;
    }
}
