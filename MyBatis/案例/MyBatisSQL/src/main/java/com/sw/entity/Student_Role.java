package com.sw.entity;

import java.util.List;

public class Student_Role {
    private Integer sid;
    private Integer rid;

    private List<Student> students;
    private List<Role> roles;

    @Override
    public String toString() {
        return "Student_Role{" +
                "sid=" + sid +
                ", rid=" + rid +
                ", students=" + students +
                ", roles=" + roles +
                '}';
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
