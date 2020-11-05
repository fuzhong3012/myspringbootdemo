package org.spring.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.springboot.entity.User;
import org.spring.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author : fuzhong
 * @CreateTime : 2020/4/13 11:08
 * @Description :
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        /**
         * junit.framework包下的Assert提供了多个断言方法. 主用于比较测试传递进去的两个参数.
         * Assert.assertEquals();及其重载方法:
         * 1. 如果两者一致, 程序继续往下运行.
         * 2. 如果两者不一致, 中断测试方法, 抛出异常信息 AssertionFailedError .
         */
        Assert.assertEquals(18, userList.size());
        userList.forEach(System.out::println);
    }
}
