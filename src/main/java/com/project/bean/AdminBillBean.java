package com.project.bean;

import lombok.Data;

import java.sql.Date;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: AdminBillBean
 * @Description: 超级管理员Bean
 * @date 2019年05月30日 14:47
 */
@Data
public class AdminBillBean {


    /**交易日期*/
    private Date tradeDate;

    /**交易时间*/
    private Date tradeTime;

    /**支出*/
    private Double expand;

    /**收入*/
    private Double income;

    /**交易备注*/
    private String remark;


}
