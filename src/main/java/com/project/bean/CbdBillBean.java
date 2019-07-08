package com.project.bean;

import lombok.Data;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: CbdBillBean
 * @Description: 企业账单Bean
 * @date 2019年05月31日 11:06
 */
@Data
public class CbdBillBean {

    /**平台账单主键ID*/
    private Integer id;

    /**交易日期*/
    private String tradeDate;

    /**交易时间*/
    private String tradeTime;

    /**支出*/
    private Double expand;

    /**收入*/
    private Double income;

    /**交易备注*/
    private String remark;

}
