package com.project.bean;

import lombok.Data;

import java.sql.Date;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: SellHistoryBean
 * @Description: 出售历史记录Bean
 * @date 2019年05月30日 23:39
 */
@Data
public class SellHistoryBean {

    /**出售历史记录主键 ID*/
    private Integer id;

    /**关联购买个人用户外键 ID*/
    private Integer buyPersonalId;

    /**关联出售个人用户外键 ID*/
    private Integer sellPersonalId;

    /**关联购买个人用户外键 ID*/
    private PersonalBean buyPersonal;

    /**关联出售个人用户外键 ID*/
    private PersonalBean sellPersonal;

    /**出售车位信息*/
    private String parkingMessage;

    /**成交价格*/
    private String externalPrice;

    /**交易时间*/
    private Date sellDate;
}