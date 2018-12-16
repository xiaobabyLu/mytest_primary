package com.xiaobaby.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lu Yufeng
 * @date 2018/11/28 上午11:44
 */

@Controller
@RequestMapping(value = "/jsp")
public class JSPController {

    @GetMapping(value = "/index")
    public String index(Model model){
        model.addAttribute("msg","Spring boot 集成jsp");
        return "index";
    }
}
