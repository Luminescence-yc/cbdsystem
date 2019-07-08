package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName 出租车位留言
 * @Description 实体类
 * @date 2019年5月30日 10：47
 */
@Data
@TableName("t_rentMessage")
public class RentMessageEntity {
    /**
     * 出租车位留言主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 个人用户对象
     */
    @TableField(exist = false)
    private PersonalEntity personalEntity;
    /**
     * 租车用户id
     */
    private int personalId;
    /**
     * 出租车位对象
     */
    @TableField(exist = false)
    private RentParkingEntity rentParkingEntity;
    /**
     * 出租车位Id
     */
    private int rentId;
    /**
     * 出租者用户对象
     */
    @TableField(exist = false)
    private PersonalEntity rentPersonalEntity;
    /**
     * 出租者用户Id
     */
    private int rentPersonalId;
    /**
     * 出租车位留言
     */
    private String rentMessage;
}