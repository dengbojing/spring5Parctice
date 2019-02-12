package com.abba.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.PushBuilder;

/**
 * for HTTP/2 Push test
 * @author dengbojing
 */
@Controller
public class PushController {

    @RequestMapping("/push")
    public String push( PushBuilder pushBuilder){
        if(null != pushBuilder){
            pushBuilder.path("static/js/test.js").push();
        }
        return "message";
    }

}
