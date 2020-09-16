package com.sw.service.impl;

import com.sw.dao.CourseDao;
import com.sw.dao.StudentDao;
import com.sw.service.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("method")
public class MethodImpl implements Method {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private CourseDao courseDao;

    @Override
    public boolean ExistSid(String s_id) {
        int num = studentDao.countBy();

        String[] sid = new String[num];
        List<String> sids = studentDao.findIdAll();
        int i = 0;
        for(String a:sids) {
            sid[i] = a;
            i++;
        }

        // 查询s_id是否存在于数据库
        boolean exist = false;
        for(int a=0;a < num;a++) {
            if(s_id.equals(sid[a])) {
                exist = true;
            }
        }
        return exist;
    }

    @Override
    public boolean ExistCid(String c_id) {
        Integer num = courseDao.countBy();

        String[] cid = new String[num];
        List<String> cids = courseDao.findByIdAll();
        int i = 0;
        for(String a:cids) {
            cid[i] = a;
            i++;
        }

        // 查询s_id是否存在于数据库
        boolean exist = false;
        for(int a=0;a < num;a++) {
            if(c_id.equals(cid[a])) {
                exist = true;
            }
        }
        return exist;
    }

    @Override
    public String GetCname(int c_id) {
        if(c_id==101) {
            return "java";
        }else if(c_id==102) {
            return "c++";
        }else if(c_id==103) {
            return "c语言";
        }else if(c_id==104) {
            return "python";
        }else {
            return "error";
        }
    }
}
