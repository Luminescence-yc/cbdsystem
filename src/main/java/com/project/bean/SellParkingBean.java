package com.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:请求响应时间bean
 * @Description:出售车位封装前端需要的数据
 * @date 2019年05月31日 9:05
 */
@Data
public class SellParkingBean {

    /**
     * 出售车位编号
     */
    private Integer id;
    /**
     * 个人车位对象
     */
    private PersonalParkingBean parkingBean;
    /**
     * 个人车位id
     */
    private Integer parkingId;
    /**
     * 发布时间
     */
    private String releaseTime;
    /**
     * 出售价格
     */
    private Double sellPrice;
    /**
     * 出售状态
     */
    private String sellStatus;
    /**
     * 留言人数
     */
    private int countMessage;

    public SellParkingBean() {
        this.sellStatus = "出售中";
        this.releaseTime = new SimpleDateFormat()
                .format(new Date(System.currentTimeMillis()));
    }
}
