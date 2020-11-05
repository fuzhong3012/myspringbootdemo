package org.spring.springboot.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fuzhong
 * @since 2020-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer provinceId;

    private String cityName;

    private String description;


}
