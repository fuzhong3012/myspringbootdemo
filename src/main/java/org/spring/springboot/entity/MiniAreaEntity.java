package org.spring.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 区域信息表
 * </p>
 *
 * @author fuzhong
 * @since 2020-04-14
 */
@TableName("mini_area")
public class MiniAreaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 省编码
     */
    private String provinceCode;

    /**
     * 省
     */
    private String province;

    /**
     * 市编码
     */
    private String cityCode;

    /**
     * 市
     */
    private String city;

    /**
     * 区编码
     */
    private String areaCode;

    /**
     * 区
     */
    private String area;

    /**
     * 街道编码
     */
    private String streetCode;

    /**
     * 街道
     */
    private String street;

    /**
     * 居委会编码
     */
    private String committeeCode;

    /**
     * 居委会
     */
    private String committee;

    /**
     * 居委会简码
     */
    private String xiangCode;

    /**
     * 审核标记
     */
    private String isCheck;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    public String getCommitteeCode() {
        return committeeCode;
    }

    public void setCommitteeCode(String committeeCode) {
        this.committeeCode = committeeCode;
    }
    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
    }
    public String getXiangCode() {
        return xiangCode;
    }

    public void setXiangCode(String xiangCode) {
        this.xiangCode = xiangCode;
    }
    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "MiniAreaEntity{" +
            "id=" + id +
            ", provinceCode=" + provinceCode +
            ", province=" + province +
            ", cityCode=" + cityCode +
            ", city=" + city +
            ", areaCode=" + areaCode +
            ", area=" + area +
            ", streetCode=" + streetCode +
            ", street=" + street +
            ", committeeCode=" + committeeCode +
            ", committee=" + committee +
            ", xiangCode=" + xiangCode +
            ", isCheck=" + isCheck +
            ", createBy=" + createBy +
            ", createDate=" + createDate +
        "}";
    }
}
