package org.spring.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.entity.User;
import org.spring.springboot.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    //记录器
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices userServices;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/list")
    public String list (){
        // 查询缓存
        String listStr = redisTemplate.opsForValue().get("list:users");
        List<User> list = JSON.parseArray(listStr, User.class);
        // 判断是否为空
        if (list == null || list.size() == 0) {
            list = userServices.list();
            redisTemplate.opsForValue().set("list:users", JSON.toJSONString(list));
        }
        return list.toString();
    }

    @RequestMapping("/listByPage")
    public String listByPage (int pageNum, int pageSize){

        PageInfo<User> pageInfo = userServices.listByPage(pageNum, pageSize);

        List<User> list = pageInfo.getList();
        System.out.println(list.toString());
        User user = list.get(0);
        System.out.println(user);

        return pageInfo.toString();
    }

    @RequestMapping("/logger")
    public String logger (){
        //日志的级别
        //由低到高   trace<debug<info<warn<error
        //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        //trace 跟踪 debug调试 info 信息 warn 警告 error 错误
        logger.trace("这是trace日志...");
        logger.debug("这是debug日志...");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");

        return "logger is ok";
    }



}
