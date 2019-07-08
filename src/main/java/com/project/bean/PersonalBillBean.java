package com.project.bean;

import lombok.Data;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: PersonalBillBean
 * @Description: 个人账单Bean
 * @date 2019年05月30日 13:21
 */
@Data
public class PersonalBillBean {

    /**交易日期*/
    private String dealDate;

    /**交易时间*/
    private String dealTime;

    /**支出*/
    private Double expend;

    /**收入*/
    private Double income;

    /**交易备注*/
    private String remark;

}
