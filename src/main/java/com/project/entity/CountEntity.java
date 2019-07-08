package com.project.entity;

import lombok.Data;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: CountEntity
 * @Description: 统计 实体类
 * @date 2019年05月30日 23:27
 */
@Data
public class CountEntity {

    /**交易总次数*/
    private int dealNum;

    /**交易总支出*/
    private double expenditure;

    /**交易收入*/
    private double generalIncome;
}
