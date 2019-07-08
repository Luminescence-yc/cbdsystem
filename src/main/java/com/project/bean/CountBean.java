package com.project.bean;

import lombok.Data;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: CountBean
 * @Description: 统计Bean
 * @date 2019年05月30日 15:11
 */
@Data
public class CountBean {

    /**交易总次数*/
    private int dealNum;

    /**交易总支出*/
    private double expenditure;

    /**交易收入*/
    private double generalIncome;
}
