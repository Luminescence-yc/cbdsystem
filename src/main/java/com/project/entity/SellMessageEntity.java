package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 朱 骞
 * @version v1.0
 * @ClassName:出售车位留言实体类
 * @Description: 实体类
 * @date 2019年5月30日9：37
 */
@Data
@TableName("t_sellMessage")
public class SellMessageEntity {
    /**
     * 出售车位留言编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 买车个人用户外键
     */
    private Integer personalId;
    /**
     * 个人用户对象
     */
    @TableField(exist = false)
    private PersonalEntity personalEntity;
    /**
     * 卖车个人用户外键
     */
    private Integer sellPersonalId;
    /**
     * 关联出售车位外键
     */
    @TableField(exist = false)
    private SellParkingEntity sellParkingEntity;
    /**
     * 卖车外键
     */
    private int sellId;
    /**
     * 出售车位留言
     */
    private String sellMessage;
}