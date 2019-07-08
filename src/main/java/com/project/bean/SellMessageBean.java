package com.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.project.entity.PersonalEntity;
import com.project.entity.SellParkingEntity;
import lombok.Data;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:请求响应时间bean
 * @Description:出售车位预定留言封装前端需要的数据
 * @date 2019年05月31日 9:06
 */
@Data
public class SellMessageBean {
    /**
     * 出售车位留言编号
     */
    private Integer id;
    /**
     * 买车个人用户外键
     */
    private Integer personalId;
    /**
     * 个人用户对象
     */
    private PersonalEntity personalEntity;
    /**
     * 卖车个人用户外键
     */
    private Integer sellPersonalId;
    /**
     * 关联出售车位外键
     */
    private SellParkingEntity sellParkingEntity;
    /**
     * 卖车外键
     */
    private int sellId;
    /**
     * 出售车位留言
     */
    private String sellMessage;
}
