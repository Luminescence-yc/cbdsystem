package com.project.bean;

import lombok.Data;

import java.util.List;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:ExternalContractBean
 * @Description: 外部合约实体bean
 * @date 2019年05月30日 14:49
 */
@Data
public class ExternalContractBean {
    /**
     * 外部合约编号
     */
    private Integer id;
    /**
     * 合同编号
     */
    private String contractNum;
    /**
     * 企业名称
     */
    private String companyName;
    /**
     * 企业联系人
     */
    private String contactPerson;
    /**
     * 企业联系电话
     */
    private String contactTel;
    /**
     * 车位所属单位详细地址
     */
    private String companyAddress;
    /**
     * 合同生效日期
     */
    private String contractStartDate;
    /**
     * 合同结束日期
     */
    private String contractEndDate;
    /**
     * 成交价格
     */
    private double externalPrice;
    /**
     * 外部合约复印件
     */
    private String contractPicture;
    /**
     * 车位地址
     */
    private String parkingAddress;
    /**
     * 原合同编号
     */
    private String oldContractNum;
    /**
     * 添加的车位集合
     */
    private List<CbdParkingBean> cbdParkingBeans;
    /**
     * 合同复印件
     */
    private String propertyImage;
}
