package org.spring.springboot.services;

import org.spring.springboot.entity.MiniAreaEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 区域信息表 服务类
 * </p>
 *
 * @author fuzhong
 * @since 2020-04-14
 */
public interface MiniAreaService extends IService<MiniAreaEntity> {

    MiniAreaEntity getSelectOne(int id);

}
