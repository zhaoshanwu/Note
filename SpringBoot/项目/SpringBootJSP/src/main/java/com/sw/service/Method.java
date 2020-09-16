package com.sw.service;

public interface Method {
    // 查看s_id是否存在于数据库
    public boolean ExistSid(String s_id);

    // 查看c_id是否存在于数据库
    public boolean ExistCid(String c_id);

    // 根据课程号查询课程名
    public String GetCname(int c_id);
}
