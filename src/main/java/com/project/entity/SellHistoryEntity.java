package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: SellHistoryEntity
 * @Description: 出售历史记录实体类
 * @date 2019年05月30日 10:24
 */
@Data
@TableName("t_sellhistory")
public class SellHistoryEntity {

    /**出售历史记录主键 ID*/
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**关联购买个人用户外键 ID*/
    private PersonalEntity buyPersonal;

    /**关联出售个人用户外键 ID*/
    private PersonalEntity sellPersonal;

    /**出售车位信息*/
    private String parkingMessage;

    /**成交价格*/
    private String externalPrice;

    /**交易时间*/
    private String sellDate;
}