package com.sw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexHandler {

    private Integer active;
    private String MainPath = "http://localhost:8080/gm";

    /**
     * 主页面
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/template/test.btl");

        active = 0;
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
