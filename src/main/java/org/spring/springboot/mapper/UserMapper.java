package org.spring.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.spring.springboot.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> list();

}
