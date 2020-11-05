package org.spring.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.entity.MiniAreaEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 区域信息表 Mapper 接口
 * </p>
 *
 * @author fuzhong
 * @since 2020-04-14
 */
public interface MiniAreaMapper extends BaseMapper<MiniAreaEntity> {

    MiniAreaEntity getSelectOne(@Param("id") int id);

}
