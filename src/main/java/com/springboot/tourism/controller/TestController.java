package com.springboot.tourism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author oneda
 * @version 菜鸟
 */

@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){

        return "hello world";
    }

    @RequestMapping("/login")
    public String login(){

        System.out.println("login!!!");
        return "/page/login.html";
    }

    @RequestMapping("/index")
    public String index(){

        System.out.println("index!!!");
        return "/page/index.html";
    }
}
