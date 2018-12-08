package com.abba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping
    public String index(){
        System.out.println("1111");
        return "index";
    }
}
