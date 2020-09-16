package com.sw.service.impl;

import com.sw.dao.ScDao;
import com.sw.dao.StudentDao;
import com.sw.entity.Sc;
import com.sw.entity.Student;
import com.sw.service.ScService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("scService")
public class ScServiceImpl implements ScService {

    @Autowired
    private ScDao scDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public Integer ByCourseMax(String c_id) {
        return scDao.ByCourseMax(c_id);
    }

    @Override
    public Integer ByCourseMin(String c_id) {
        return scDao.ByCourseMin(c_id);
    }

    @Override
    public Double ByCourseAvg(String c_id) {
        return scDao.ByCourseAvg(c_id);
    }

    @Override
    public List<Sc> ByGrade(String s_id) {
        return scDao.ByGrade(s_id);
    }

    @Override
    public Integer countRank(String s_id, String c_id) {
        return scDao.countRank(s_id,c_id);
    }

    @Override
    public Double ByGradeMinus(String s_id, String c_id) {
        return scDao.ByGradeMinus(s_id,c_id);
    }

    @Override
    public void insertGrade(Sc sc) {
        scDao.insertGrade(sc);
    }

    @Override
    public void updateGrade(Sc sc) {
        scDao.updateGrade(sc);
    }

    @Override
    public void insertSG(String s_id, String s_name, String s_sex, Integer s_age, Integer java, Integer c2, Integer c, Integer python) {
        System.out.println("开始执行-------");
        Sc sc1 = new Sc();
        Sc sc2 = new Sc();
        Sc sc3 = new Sc();
        Sc sc4 = new Sc();

        sc1.setS_id(s_id);
        sc2.setS_id(s_id);
        sc3.setS_id(s_id);
        sc4.setS_id(s_id);

        sc1.setC_id("101");
        sc2.setC_id("102");
        sc3.setC_id("103");
        sc4.setC_id("104");

        sc1.setGrade(java);
        sc2.setGrade(c2);
        sc3.setGrade(c);
        sc4.setGrade(python);

        Student student = new Student();
        student.setS_id(s_id);
        student.setS_name(s_name);
        student.setS_sex(s_sex);
        student.setS_age(s_age);

        studentDao.insertStudent(student);
        scDao.insertGrade(sc1);
        scDao.insertGrade(sc2);
        scDao.insertGrade(sc3);
        scDao.insertGrade(sc4);
    }
}
