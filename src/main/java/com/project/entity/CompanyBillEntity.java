package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: CompanyBillEntity
 * @Description: 企业账单 实体类
 * @date 2019年05月31日 11:11
 */
@Data
@TableName("t_companybill")
public class CompanyBillEntity {

    /**企业账单主键ID*/
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**关联企业用户外键 ID*/
    private Integer companyId;

    /**交易日期*/
    private String tradeDate;

    /**交易时间*/
    private String tradeTime;

    /**支出*/
    private Double expend=0.0;

    /**收入*/
    private Double income=0.0;

    /**交易备注*/
    private String remark;

}
