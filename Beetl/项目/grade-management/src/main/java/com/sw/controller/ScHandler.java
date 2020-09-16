package com.sw.controller;

import com.sw.entity.Sc;
import com.sw.service.CourseService;
import com.sw.service.Method;
import com.sw.service.ScService;
import com.sw.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sc")
public class ScHandler {

    private static final Logger logger = Logger.getLogger(StudentHandler.class);

    @Autowired
    private StudentService studentService;
    @Autowired
    private ScService scService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private Method method;

    private String MainPath = "http://localhost:8080/gm";
    private Integer active;

    /**
     * 根据学号查询学生成绩
     */
    @RequestMapping(value = "/findGrade")
    public ModelAndView findGrade(String s_id) {
        ModelAndView view = new ModelAndView("/template/test.btl");
        List<Sc> gradelist = scService.ByGrade(s_id);
        try {
            if(method.ExistSid(s_id)) {
                Integer[] grade = new Integer[4];
                int i = 0;
                String[] c_id = new String[4];
                int j = 0;
                for(Sc sc:gradelist) {
                    grade[i] = sc.getGrade();
                    i += 1;
                    c_id[j] = sc.getC_id();
                    j += 1;
                }

                String s_name = studentService.NameById(s_id);

                String[] c_name = new String[4];
                c_name[0] = courseService.NameById(c_id[0]);
                c_name[1] = courseService.NameById(c_id[1]);
                c_name[2] = courseService.NameById(c_id[2]);
                c_name[3] = courseService.NameById(c_id[3]);
                view.addObject("s_name", s_name);
                view.addObject("grade", grade);
                view.addObject("c_id", c_id);
                view.addObject("c_name", c_name);
            } else {
                view.addObject("s_name", null);
                view.addObject("grade", null);
                view.addObject("c_id", null);
                view.addObject("c_name", null);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        active = 2;
        view.addObject("active",active);
        view.addObject("MainPath",MainPath);

        view.addObject("students",null);

        view.addObject("student",null);

        view.addObject("c_name2", null);
        view.addObject("max", null);
        view.addObject("min", null);
        view.addObject("average", null);

        view.addObject("maxRanking", null);
        view.addObject("minRanking", null);
        view.addObject("maxC_name", null);
        view.addObject("minC_name", null);

        view.addObject("c_name3", null);
        view.addObject("minus", null);
        return view;
    }

    /**
     * 查询课程的最大成绩、最小成绩和平均分
     */
    @RequestMapping(value = "/findMaxMin")
    public ModelAndView findMaxMin(String c_id) {
        ModelAndView view = new ModelAndView("/template/test.btl");
        try {
            if(method.ExistCid(c_id)) {
                int max,min;
                double average;
                String c_name2 = null;
                c_name2 = courseService.NameById(c_id);
                max = scService.ByCourseMax(c_id);
                min = scService.ByCourseMin(c_id);
                average = scService.ByCourseAvg(c_id);

                view.addObject("c_name2", c_name2);
                view.addObject("max", max);
                view.addObject("min", min);
                view.addObject("average", average);
            } else {
                view.addObject("c_name2", null);
                view.addObject("max", null);
                view.addObject("min", null);
                view.addObject("average", null);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        active = 3;
        view.addObject("active",active);
        view.addObject("MainPath",MainPath);

        view.addObject("students",null);

        view.addObject("student",null);

        view.addObject("s_name", null);
        view.addObject("grade", null);
        view.addObject("c_id", null);
        view.addObject("c_name", null);

        view.addObject("maxRanking", null);
        view.addObject("minRanking", null);
        view.addObject("maxC_name", null);
        view.addObject("minC_name", null);

        view.addObject("c_name3", null);
        view.addObject("minus", null);
        return view;
    }

    /**
     * 查询学生的最好排名和最差排名
     */
    @RequestMapping(value = "/findRanking")
    public ModelAndView Ranking(String s_id) {
        ModelAndView view = new ModelAndView("/template/test.btl");
        try {
            List<Sc> gradelist = scService.ByGrade(s_id);
            if(method.ExistSid(s_id) && gradelist.size() == 4) {
                int rank101,rank102,rank103,rank104;
                int rank[][] = new int[4][2];
                rank101 = scService.countRank(s_id,"101");
                rank102 = scService.countRank(s_id,"102");
                rank103 = scService.countRank(s_id,"103");
                rank104 = scService.countRank(s_id,"104");

                rank[0][0] = rank101;
                rank[0][1] = 101;
                rank[1][0] = rank102;
                rank[1][1] = 102;
                rank[2][0] = rank103;
                rank[2][1] = 103;
                rank[3][0] = rank104;
                rank[3][1] = 104;

                int temp0,temp1;
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++) {
                        if(rank[j][0]>rank[j+1][0]) {
                            temp0=rank[j][0];
                            temp1=rank[j][1];
                            rank[j][0] = rank[j+1][0];
                            rank[j][1] = rank[j+1][1];
                            rank[j+1][0] = temp0;
                            rank[j+1][1] = temp1;
                        }
                    }
                }
                view.addObject("maxRanking", rank[0][0]);
                view.addObject("minRanking", rank[3][0]);
                view.addObject("maxC_name", method.GetCname(rank[0][1]));
                view.addObject("minC_name", method.GetCname(rank[3][1]));
            } else {
                view.addObject("maxRanking", null);
                view.addObject("minRanking", null);
                view.addObject("maxC_name", null);
                view.addObject("minC_name", null);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        active = 4;
        view.addObject("active",active);
        view.addObject("MainPath",MainPath);

        view.addObject("students",null);

        view.addObject("student",null);

        view.addObject("s_name", null);
        view.addObject("grade", null);
        view.addObject("c_id", null);
        view.addObject("c_name", null);

        view.addObject("c_name2", null);
        view.addObject("max", null);
        view.addObject("min", null);
        view.addObject("average", null);

        view.addObject("c_name3", null);
        view.addObject("minus", null);
        return view;
    }


    @RequestMapping(value = "/findMinus")
    public ModelAndView findMinus(String s_id,String c_id) {
        ModelAndView view = new ModelAndView("/template/test.btl");
        try {
            List<Sc> gradelist = scService.ByGrade(s_id);
            if(method.ExistSid(s_id) && method.ExistCid(c_id) && gradelist.size() == 4) {
                double minus = scService.ByGradeMinus(s_id,c_id);
                String c_name = courseService.NameById(c_id);

                view.addObject("minus", minus);
                view.addObject("c_name3", c_name);
            } else {
                view.addObject("minus", null);
                view.addObject("c_name3", null);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        active = 5;
        view.addObject("active",active);
        view.addObject("MainPath",MainPath);

        view.addObject("students",null);

        view.addObject("student",null);

        view.addObject("s_name", null);
        view.addObject("grade", null);
        view.addObject("c_id", null);
        view.addObject("c_name", null);

        view.addObject("c_name2", null);
        view.addObject("max", null);
        view.addObject("min", null);
        view.addObject("average", null);

        view.addObject("maxRanking", null);
        view.addObject("minRanking", null);
        view.addObject("maxC_name", null);
        view.addObject("minC_name", null);
        return view;
    }

    /**
     * 录入学生成绩
     * 如果录入的时候没有该学生信息则添加学生信息后再录入
     */
    @RequestMapping(value = "/InsertGrade")
    public ModelAndView InsertGrade(String s_id,Integer java,Integer c2,Integer c,Integer python) {
        ModelAndView view = new ModelAndView("/template/test.btl");
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

        if (method.ExistSid(s_id)) { // 如果输入的学号存在于数据库，则对该学生进行成绩录入
            try {
                scService.insertGrade(sc1);
                scService.insertGrade(sc2);
                scService.insertGrade(sc3);
                scService.insertGrade(sc4);
            } catch (Exception e) {
                logger.error(e);
            }
        } else { // 如果不存在，则视为新学生，对学生信息及成绩进行录入
            try {
                String s_name = "王五";
                String s_sex = "男";
                Integer s_age = 18;
                scService.insertSG(s_id,s_name,s_sex,s_age,java,c2,c,python);
            } catch (Exception e) {
                logger.error(e);
            }
        }
        active = 7;
        view.addObject("active",active);
        view.addObject("MainPath",MainPath);

        view.addObject("students",null);

        view.addObject("student",null);

        view.addObject("s_name", null);
        view.addObject("grade", null);
        view.addObject("c_id", null);
        view.addObject("c_name", null);

        view.addObject("c_name2", null);
        view.addObject("max", null);
        view.addObject("min", null);
        view.addObject("average", null);

        view.addObject("maxRanking", null);
        view.addObject("minRanking", null);
        view.addObject("maxC_name", null);
        view.addObject("minC_name", null);

        view.addObject("c_name3", null);
        view.addObject("minus", null);
        return view;
    }

    // 维护学生成绩
    @RequestMapping(value = "/UpdateGrade")
    public ModelAndView UpdateGrade(String s_id,Integer java,Integer c2,Integer c,Integer python) {
        ModelAndView view = new ModelAndView("/template/test.btl");
        try {
            if(method.ExistSid(s_id)) {
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

                if(java != -1) {
                    scService.updateGrade(sc1);
                }
                if(c2 != -1) {
                    scService.updateGrade(sc2);
                }
                if(c != -1) {
                    scService.updateGrade(sc3);
                }
                if(python != -1) {
                    scService.updateGrade(sc4);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        active = 8;
        view.addObject("active",active);
        view.addObject("MainPath",MainPath);

        view.addObject("students",null);

        view.addObject("student",null);

        view.addObject("s_name", null);
        view.addObject("grade", null);
        view.addObject("c_id", null);
        view.addObject("c_name", null);

        view.addObject("c_name2", null);
        view.addObject("max", null);
        view.addObject("min", null);
        view.addObject("average", null);

        view.addObject("maxRanking", null);
        view.addObject("minRanking", null);
        view.addObject("maxC_name", null);
        view.addObject("minC_name", null);

        view.addObject("c_name3", null);
        view.addObject("minus", null);
        return view;
    }
}
