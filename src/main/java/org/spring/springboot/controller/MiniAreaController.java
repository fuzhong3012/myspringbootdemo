package org.spring.springboot.controller;


import org.spring.springboot.entity.MiniAreaEntity;
import org.spring.springboot.services.MiniAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 区域信息表 前端控制器
 * </p>
 *
 * @author fuzhong
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/miniareaentity")
public class MiniAreaController {

    @Autowired
    private MiniAreaService miniAreaService;

    @RequestMapping("/list")
    public String list(){
        List<MiniAreaEntity> list = miniAreaService.list();
        return list.toString();
    }

    @RequestMapping("/getSelectOne")
    public String getSelectOne(int id){
        MiniAreaEntity miniAreaEntity = miniAreaService.getSelectOne(id);
        return miniAreaEntity.toString();
    }

}
