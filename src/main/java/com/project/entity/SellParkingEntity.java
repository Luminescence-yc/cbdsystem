package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * @author 朱 骞
 * @version v1.0
 * @ClassName:出售车位实体类
 * @Description: 实体类
 * @date 2019年5月30日9：37
 */
@Data
@TableName("t_sellParking")
public class SellParkingEntity {
    /**
     * 出售车位编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 个人车位对象
     */
    @TableField(exist = false)
    private PersonalParkingEntity personalParkingEntity;
    /**
     * 车位id
     */
    private int parkingId;
    /**
     * 发布时间
     */
    private String releaseTime;
    /**
     * 出售价格
     */
    private Double sellPrice;
    /**
     * 出售状态
     */
    private String sellStatus;
    /**
     * 留言人数
     */
    private int countMessage;

}