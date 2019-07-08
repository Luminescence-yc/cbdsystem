package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:ExternalContractEntity
 * @Description:cbd车位管理外部合约实体类
 * @date 2019年-05月-30日 10:00
 */
@Data
@TableName(value = "t_externalcontract")
public class ExternalContractEntity {
    /**
     * 外部合约编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 合同编号
     */
    private String contractNum;
    /**
     * 企业名称
     */
    private String companyName;
    /**
     * 企业联系人
     */
    private String contactPerson;
    /**
     * 企业联系电话
     */
    private String contactTel;
    /**
     * 企业所属详细地址
     */
    private String companyAddress;
    /**
     * 合同开始日期
     */
    private String  contractStartDate;
    /**
     * 合同结束日期
     */
    private String contractEndDate;
    /**
     * 出售价格
     */
    private Double externalPrice;
    /**
     * 外部合约复印件
     */
    private String contractPicture;
    /**
     * 车位地址
     */
    private String parkingAddress;
    /**
     * 原合同编号
     */
    private String oldContractNum;
}