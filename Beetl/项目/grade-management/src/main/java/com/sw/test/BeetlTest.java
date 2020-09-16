package com.sw.test;

import com.sw.entity.Student;
import com.sw.service.StudentService;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:springconfig.xml")
public class BeetlTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void TestBeetl() throws IOException {
        List<Student> students = studentService.findAll();
//        FileResourceLoader resourceLoader = new FileResourceLoader("src/main/webapp/template");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("springconfig.xml");
        BeetlGroupUtilConfiguration config = (BeetlGroupUtilConfiguration) ac.getBean("beetlConfig");
        GroupTemplate gt = config.getGroupTemplate();
//        Configuration cfg = Configuration.defaultConfiguration();
//        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);


        Template t = gt.getTemplate("/test.btl");
        t.binding("students",students);
        String str = t.render();
        System.out.println(str);
    }
}
