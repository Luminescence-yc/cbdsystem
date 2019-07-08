package com.project.bean;

import lombok.Data;

/**
 * @author 费 宇
 * @version v1.0
 * @ClassName: SellParkingPageBean
 * @Description: 出售车位分页查询参数bean
 * @date 2019年06月05日 9:43
 */
@Data
public class SellParkingPageBean {
    /**
     * 当前页数
     */
    private String current;
    /**
     * 每页显示条数
     */
    private String size;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 代售车位信息（车位地址）
     */
    private String address;
    /**
     * 开始价格
     */
    private String startSellPrice;
    /**
     * 结束价格
     */
    private String endSellPrice;
    /**
     * 出售状态
     */
    private String sellStatus;

}
