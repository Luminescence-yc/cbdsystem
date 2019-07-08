package com.project.bean;

import com.project.entity.PersonalEntity;
import com.project.entity.RentHistoryEntity;
import com.project.entity.SellHistoryEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author 杨彪
 * @version v1.0
 * @ClassName: ComplaintBean
 * @Description:   投诉受理Bean
 * @date 2019年-05月-30日 10:004
 */
@Data
public class ComplaintBean {
    /**
     *主键
     */
    private int id;
    /**
     *投诉用户
     */
    private PersonalBean personalComplainantBean;
    /**
     * 被投诉用户
     */
    private PersonalBean personalByUpholdingBean;
    /**
     * 出售历史记录对象
     */
    private SellHistoryBean sellhistoryBean;
    /**
     * 出租历史记录对象
     */
    private RentHistoryBean renthistoryBean;
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
    /**
     *投诉用户id
     */
    private int personalComplainantId;
    /**
     * 被投诉用户id
     */
    private int personalByUpholdingId;
    /**
     * 出售历史记录对象id
     */
    private int sellhistoryId;
    /**
     * 出租历史记录对象id
     */
    private int renthistoryId;
}
