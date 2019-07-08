package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:CompanyContractEntity
 * @Description:cbd车位管理租户合约实体类
 * @date 2019年-05月-30日 10:00
 */
@Data
@TableName(value = "t_companycontract")
public class CompanyContractEntity {
    /**
     * 租户合约编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 企业对象
     */
    @TableField(exist = false)
    private CompanyEntity companyEntity;
    /**
     * 关联企业外键
     */
    private Integer companyId;
    /**
     * 合同编号
     */
    private String contractNum;
    /**
     * 租借开始日期
     */
    private String startDate;
    /**
     * 合同结束日期
     */
    private String endDate;
    /**
     * 成交价格
     */
    private double companyPrice;
    /**
     * 企业合同复印件
     */
    private String contractPicture;
    /**
     * 原合同编号
     */
    private String oldContractNum;
}