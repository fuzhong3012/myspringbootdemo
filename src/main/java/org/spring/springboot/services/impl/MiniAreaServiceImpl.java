package org.spring.springboot.services.impl;

import org.spring.springboot.entity.MiniAreaEntity;
import org.spring.springboot.mapper.MiniAreaMapper;
import org.spring.springboot.services.MiniAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 区域信息表 服务实现类
 * </p>
 *
 * @author fuzhong
 * @since 2020-04-14
 */
@Service
public class MiniAreaServiceImpl extends ServiceImpl<MiniAreaMapper, MiniAreaEntity> implements MiniAreaService {

    @Autowired
    private MiniAreaMapper miniAreaMapper;

    @Override
    public MiniAreaEntity getSelectOne(int id) {
        return miniAreaMapper.getSelectOne(id);
    }
}
