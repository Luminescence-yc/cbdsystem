package com.project.bean;

import lombok.Data;

import java.sql.Date;


/**
 * @author 刘晶晶
 * @version v1.0
 * @ClassName: ContractBean
 * @Description: 合同实体Bean
 * @date 2019年05月30日 16:37
 */
@Data
public class ContractBean {
    /**
     * 合同的主键ID
     */
    private Integer id;
    /**
     * 出售车位对象
     */
    private SellParkingBean sellParkingBean;
    /**
     * 买家个人用户对象
     */
    private PersonalBean buyer;
    /**
     * 卖家个人用户对象
     */
    private PersonalBean seller ;
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
    /**
     * 出售车位ID
     */
    private int sellId;
    /**
     * 买家的ID
     */
    private int buyPersonalId;
    /**
     * 卖家的ID
     */
    private int sellPersonalId;
}
