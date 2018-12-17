package com.abba.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.PushBuilder;

@Controller
public class PushController {

    @RequestMapping("/push")
    public String push( PushBuilder pushBuilder){
        if(null != pushBuilder){
            pushBuilder.path("resource/js/test.js").push();
        }
        return "message";
    }

    @RequestMapping("/talk")
    @ResponseBody
    public String talk(String message){
        System.out.println("msg:"+message);
        return "weqew";
    }
}
