package org.spring.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.spring.springboot.entity.City;
import org.spring.springboot.entity.User;
import org.spring.springboot.services.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fuzhong
 * @since 2020-04-13
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityService cityService;

    @RequestMapping("/list")
    public String list(){
        List<City> list = cityService.list();
        return list.toString();
    }

    @RequestMapping("/getOne")
    public String getOne(){
        QueryWrapper<City> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id","12");
        City city = cityService.getOne(queryWrapper);
        return city.toString();
    }

}
