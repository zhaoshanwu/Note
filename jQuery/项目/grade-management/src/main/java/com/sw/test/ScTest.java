package com.sw.test;

import com.sw.entity.Sc;
import com.sw.service.ScService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:springconfig.xml")
public class ScTest {
    @Autowired
    private ScService scService;

    @Test
    public void testByCourseMax() {
        Integer max = scService.ByCourseMax("101");
        System.out.println(max);
    }

    @Test
    public void testByCourseMin() {
        Integer min = scService.ByCourseMin("101");
        System.out.println(min);
    }

    @Test
    public void testByCourseAvg() {
        Double avg = scService.ByCourseAvg("101");
        System.out.println(avg);
    }

    @Test
    public void testByGrade() {
        List<Sc> scs = scService.ByGrade("001");
        for(Sc sc : scs) {
            System.out.println(sc);
        }
    }

    @Test
    public void testcountRank() {
        Integer rank = scService.countRank("001","101");
        System.out.println(rank);
    }

    @Test
    public void testByGradeMinus() {
        Double minus = scService.ByGradeMinus("001","101");
        System.out.println(minus);
    }

    @Test
    public void testinsertGrade() {
        Sc sc = new Sc();
        sc.setS_id("008");
        sc.setC_id("101");
        sc.setGrade(50);
        scService.insertGrade(sc);
    }

    @Test
    public void testinsertSG() {
        scService.insertSG("010","王五","男",20,50,50,50,50);
    }
}
