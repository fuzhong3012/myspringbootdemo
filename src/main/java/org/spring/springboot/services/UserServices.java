package org.spring.springboot.services;

import com.github.pagehelper.PageInfo;
import org.spring.springboot.entity.User;

import java.util.List;

public interface UserServices {

    List<User> list();

    PageInfo<User> listByPage(int pageNum, int pageSize);

}
