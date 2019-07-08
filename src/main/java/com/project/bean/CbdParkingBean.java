package com.project.bean;

import lombok.Data;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:CbdParkingBean
 * @Description: cbd车位管理实体bean
 * @date 2019年05月30日 14:35
 */
@Data
public class CbdParkingBean {
    /**
     * 车位编号
     */
    private Integer id;
    /**
     * 车位所在地址
     */
    private String address;
    /**
     * 车位区域
     */
    private String areaNum;
    /**
     * 车位起始编号
     */
    private String parkingNum;
    /**最长可租日期*/
    private String finalDate;
    /**
     * 添加车位数量
     */
    private int count;
}
