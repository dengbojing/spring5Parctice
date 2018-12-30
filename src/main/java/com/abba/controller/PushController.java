package com.abba.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.PushBuilder;

/**
 * @author dengbojing
 */
@Controller
public class PushController {

    @RequestMapping("/push")
    public String push( PushBuilder pushBuilder){
        if(null != pushBuilder){
            pushBuilder.path("resource/js/test.js").push();
        }
        return "message";
    }

}
