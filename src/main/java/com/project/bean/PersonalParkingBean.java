package com.project.bean;


import com.project.entity.PersonalEntity;
import lombok.Data;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:请求响应时间bean
 * @Description:个人车位封装前端需要的数据
 * @date 2019年05月31日 9:04
 */
@Data
public class PersonalParkingBean {

    /**
     * 个人车位主键id
     */
    private Integer id;

    /**
     * 个人用户对象
     */
    private PersonalBean personalBean;
    /**
     * 个人用户外键Id
     */
    private Integer personalId;
    /**
     * 车位产权编号
     */
    private String propertyNum;
    /**
     * 车位所在小区地址
     */
    private String address;
    /**
     * 区域编号
     */
    private String areaNum;
    /**
     * 车位号码
     */
    private String parkingNum;
    /**
     * 产权复印件
     */
    private String propertyImage;
    /**
     * 车位状态
     */
    private String status;
    /**
     * 申请时间
     */
    private String applyDate;
}
