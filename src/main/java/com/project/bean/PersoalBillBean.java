package com.project.bean;

import lombok.Data;

import java.sql.Date;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: PersoalBillBean
 * @Description: 个人账单Bean
 * @date 2019年05月30日 13:21
 */
@Data
public class PersoalBillBean {

    /**个人账单主键ID*/
    private Integer id;

    /**关联个人用户外键 ID*/
    private Integer personalid;

    /**交易日期*/
    private Date dealdate;

    /**交易时间*/
    private Date dealtime;

    /**支出*/
    private Double expend;

    /**收入*/
    private Double income;

    /**交易备注*/
    private String remark;

}
