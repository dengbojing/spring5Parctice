package com.abba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dengbojing
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping
    public String index(){
        return "index";
    }
}
