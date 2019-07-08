package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: CbdBillEntity
 * @Description: 平台账单实体类
 * @date 2019年05月30日 10:43
 */
@Data
@TableName("t_cbdbill")
public class CbdBillEntity {

    /**平台账单主键ID*/
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**交易日期*/
    private String tradeDate;

    /**交易时间*/
    private String tradeTime;

    /**支出*/
    private Double expand=0.0;

    /**收入*/
    private Double income=0.0;

    /**交易备注*/
    private String remark;

}