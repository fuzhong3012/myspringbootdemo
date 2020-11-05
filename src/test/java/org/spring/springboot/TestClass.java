package org.spring.springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.entity.User;
import org.spring.springboot.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author : fuzhong
 * @CreateTime : 2020/4/10 10:08
 * @Description :
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClass {

    Logger logger = LoggerFactory.getLogger(TestClass.class);

    @Autowired
    private UserServices userServices;//注入dao接口

    @Test
    public void testFind() {
        logger.debug("logger测试开始");
        //System.out.println("测试开始");
        //简单验证结果集是否正确
        List<User> list = userServices.list();
        System.out.println(list);
        //System.out.println("测试结束");
        logger.debug("logger测试结束");

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

    }
}