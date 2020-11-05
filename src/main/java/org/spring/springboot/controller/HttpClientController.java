package org.spring.springboot.controller;

import org.spring.springboot.http.HttpAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : fuzhong
 * @CreateTime : 2020/4/20 10:14
 * @Description :
 **/
@RestController
public class HttpClientController {

    @Autowired
    private HttpAPIService httpAPIService;

    @RequestMapping("/httpclient")
    public String test() throws Exception {
        String str = httpAPIService.doGet("http://www.baidu.com");
        System.out.println(str);
        return "ok";
    }


}
