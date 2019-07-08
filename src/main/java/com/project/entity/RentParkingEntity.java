package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName 出租车位
 * @Description 实体类
 * @date 2019年5月30日 10：10
 */
@Data
@TableName("t_rentParking")
public class RentParkingEntity {
    /**
     * 出租车位表主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 个人用户对象
     */
    @TableField(exist = false)
    private PersonalEntity personalEntity;

    /**
     * 个人车位对象
     */
    @TableField(exist = false)
    private PersonalParkingEntity personalParkingEntity;
    /**
     * 个人车位外键
     */
    private int parkingId;
    /**
     * 租借开始日期
     */
    private String startTime;
    /**
     * 租借截止日期
     */
    private String endTime;

    /**
     * 出租价格
     */
    private Double rentPrice;
}