package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: PersonalBillEntity
 * @Description: 个人账单实体类
 * @date 2019年05月30日 10:16
 */
@Data
@TableName("t_personalBill")
public class PersonalBillEntity {

    /**个人账单主键ID*/
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**关联个人用户外键 ID*/
    private Integer personalId;

    /**交易日期*/
    private String dealDate;

    /**交易时间*/
    private String dealTime;

    /**支出*/
    private Double expend=0.0;

    /**收入*/
    private Double income=0.0;

    /**交易备注*/
    private String remark;
}