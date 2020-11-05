package org.spring.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : fuzhong
 * @CreateTime : 2020/7/7 15:42
 * @Description :
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/hello")
    public String hello (){
        return "hello, demo";
    }


}
