package org.spring.springboot.services.impl;

import org.spring.springboot.entity.City;
import org.spring.springboot.mapper.CityMapper;
import org.spring.springboot.services.ICityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fuzhong
 * @since 2020-04-13
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

}
