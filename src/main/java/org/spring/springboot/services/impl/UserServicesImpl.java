package org.spring.springboot.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.spring.springboot.entity.User;
import org.spring.springboot.mapper.UserMapper;
import org.spring.springboot.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public PageInfo<User> listByPage(int pageNum, int pageSize) {
        //排序实现: 数据库字段 + " desc" 或 数据库字段 + " asc"
        //PageHelper.startPage(1, 10, "create_time desc");
        //pageNum:当前页数;pageSize:当前页需要显示的数量
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.list();
        //Dao层查询数据库
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }
}
