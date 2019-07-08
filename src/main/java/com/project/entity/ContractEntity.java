package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;


/**
 * @author 刘晶晶
 * @version v1.0
 * @ClassName ContractEntity
 * @Description 合同实体类
 * @date 2019年5月30日 9:38
 */
@Data
@TableName("t_contract")
public class ContractEntity {
    /**
     * 合同的主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 出售车位对象
     */
    @TableField(exist = false)
    private SellParkingEntity sellParkingEntity;
    /**
     * 买家个人用户对象
     */
    @TableField(exist = false)
    private PersonalEntity buyer;
    /**
     * 卖家个人用户对象
     */
    @TableField(exist = false)
    private PersonalEntity seller;
    /**
     * 合同的编号
     */
    private String contractNum;
    /**
     * 合同申请日期
     */
    private Date applyDate;
    /**
     * 合同生效日期
     */
    private Date startDate;
    /**
     * 卖家同意
     */
    private int sellerAgree;
    /**
     * 买家同意
     */
    private int buyerAgree;
    /**
     * 合同状态
     */
    private Boolean status;
}