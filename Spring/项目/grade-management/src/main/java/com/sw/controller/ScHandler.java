package com.sw.controller;

import com.sw.entity.Sc;
import com.sw.service.CourseService;
import com.sw.service.Method;
import com.sw.service.ScService;
import com.sw.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    /**
     * 根据学号查询学生成绩
     */
    @RequestMapping("/findGrade")
    public String findGrade(int active, String s_id,HttpSession session) {
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
                session.setAttribute("s_name", s_name);
                session.setAttribute("grade", grade);
                session.setAttribute("c_id", c_id);
                session.setAttribute("c_name", c_name);
            } else {
                session.setAttribute("s_name", null);
                session.setAttribute("grade", null);
                session.setAttribute("c_id", null);
                session.setAttribute("c_name", null);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        session.setAttribute("active", active);
        return "redirect:/gather.jsp";
    }

    /**
     * 查询课程的最大成绩、最小成绩和平均分
     */
    @RequestMapping("/MaxMin")
    public String MaxMin(int active,String c_id,HttpSession session) {
        try {
            if(method.ExistCid(c_id)) {
                int max,min;
                double average;
                String c_name = null;
                c_name = courseService.NameById(c_id);
                max = scService.ByCourseMax(c_id);
                min = scService.ByCourseMin(c_id);
                average = scService.ByCourseAvg(c_id);

                session.setAttribute("c_name2", c_name);
                session.setAttribute("max", max);
                session.setAttribute("min", min);
                session.setAttribute("average", average);
            } else {
                session.setAttribute("c_name2", null);
                session.setAttribute("max", null);
                session.setAttribute("min", null);
                session.setAttribute("average", null);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        session.setAttribute("active", active);
        return "redirect:/gather.jsp";
    }

    /**
     * 查询学生的最好排名和最差排名
     */
    @RequestMapping("/Ranking")
    public String Ranking(String s_id,int active,HttpSession session) {
        try {
            if(method.ExistSid(s_id)) {
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

                session.setAttribute("max2", rank[0][0]);
                session.setAttribute("min2", rank[3][0]);
                session.setAttribute("c_max", method.GetCname(rank[0][1]));
                session.setAttribute("c_min", method.GetCname(rank[3][1]));
            } else {
                session.setAttribute("max2", null);
                session.setAttribute("min2", null);
                session.setAttribute("c_max", null);
                session.setAttribute("c_min", null);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        session.setAttribute("active", active);
        return "redirect:/gather.jsp";
    }

    /**
     * 查询学生该科成绩与该科平均分之差，为学生该科成绩-该科平均成绩
     */
    @RequestMapping("/Minus")
    public String Minus(String s_id,String c_id,int active,HttpSession session) {
        try {
            if(method.ExistSid(s_id) && method.ExistCid(c_id)) {
                double minus = scService.ByGradeMinus(s_id,c_id);
                String c_name = courseService.NameById(c_id);

                session.setAttribute("minus", minus);
            } else {
                session.setAttribute("minus", null);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        session.setAttribute("active", active);
        return "redirect:/gather.jsp";
    }

    /**
     * 录入学生成绩
     * 如果录入的时候没有该学生信息会添加学生信息后再录入
     */
    @RequestMapping("/InsertGrade")
    public String InsertGrade(int active,String s_id,String s_name,String s_sex,Integer s_age,Integer java,Integer c2,Integer c,Integer python, HttpSession session) {
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
                scService.insertSG(s_id,s_name,s_sex,s_age,java,c2,c,python);
            } catch (Exception e) {
                logger.error(e);
            }
        }
        session.setAttribute("active", active);
        return "redirect:/gather.jsp";
    }

    /**
     * 维护学生成绩，-1表示不更改
     */
    @RequestMapping("/UpdateGrade")
    public String UpdateGrade(int active,String s_id,Integer java,Integer c2,Integer c,Integer python, HttpSession session) {
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
        session.setAttribute("active", active);
        return "redirect:/gather.jsp";
    }
}
