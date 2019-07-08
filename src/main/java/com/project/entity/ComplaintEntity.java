package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
/**
 * @author 杨彪
 * @version v1.0
 * @ClassName: ComplaintEntity
 * @Description:   投诉受理实体类
 * @date 2019年-05月-30日 10:004
 */
@Data
@TableName("t_complaint")
public class ComplaintEntity {
    /**
     * 投诉主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     *投诉用户
     */

    private PersonalEntity personalComplaint;
    /**
     * 被投诉用户
     */

    private PersonalEntity personalByUpholding;
    /**
     * 出售历史记录对象
     */

    private SellHistoryEntity sellhistoryEntity;
    /**
     * 出租历史记录对象
     */

    private RentHistoryEntity renthistoryEntity;
    /**
     * 投诉理由
     */
    private String complaintReason;
    /**
     * 投诉日期
     */
    private String complaintDate;
    /**
     * 受理状态
     */
    private String status;
}