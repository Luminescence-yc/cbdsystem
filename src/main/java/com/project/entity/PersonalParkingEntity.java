package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName 个人车位
 * @Description 实体类
 * @date 2019年5月30日 10：10
 */
@Data
@TableName("t_personalParking")
public class PersonalParkingEntity {
    /**
     * 个人车位主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 个人用户对象
     */
    @TableField(exist = false)
    private PersonalEntity personalEntity;
    /**
     * 个人用户外键Id
     */
    private Integer personalId;
    /**
     * 车位产权编号
     */
    private String propertyNum;
    /**
     * 车位所在小区地址
     */
    private String address;
    /**
     * 区域编号
     */
    private String areaNum;
    /**
     * 车位号码
     */
    private String parkingNum;
    /**
     * 产权复印件
     */
    private String propertyImage;
    /**
     * 车位状态
     */
    private String status = "待审核";
    /**
     * 申请时间
     */
    private String applyDate;

}