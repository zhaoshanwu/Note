package com.sw.test;

import com.sw.dao.CourseDao;
import com.sw.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:springconfig.xml")
public class CourseTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void testNameById() {
        String c_name = courseService.NameById("101");
        System.out.println(c_name);
    }

    @Test
    public void testfindByIdAll() {
        List<String> ids = courseService.findByIdAll();
        for(String id : ids) {
            System.out.println(id);
        }
    }

    @Test
    public void testcountBy() {
        Integer number = courseService.countBy();
        System.out.println(number);
    }
}
