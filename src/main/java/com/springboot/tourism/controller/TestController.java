package com.springboot.tourism.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author oneda
 * @version 菜鸟
 */

@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello(){

        return "hello world";
    }
}
