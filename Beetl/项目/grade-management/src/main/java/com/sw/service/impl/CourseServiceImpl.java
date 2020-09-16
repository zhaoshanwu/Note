package com.sw.service.impl;

import com.sw.dao.CourseDao;
import com.sw.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public String NameById(String c_id) {
        return courseDao.NameById(c_id);
    }

    @Override
    public List<String> findByIdAll() {
        return courseDao.findByIdAll();
    }

    @Override
    public Integer countBy() {
        return courseDao.countBy();
    }
}
