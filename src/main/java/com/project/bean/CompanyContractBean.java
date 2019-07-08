package com.project.bean;

import lombok.Data;

import java.util.List;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName: CompanyContractBean
 * @Description: 租户合约实体bean
 * @date 2019年05月30日 14:43
 */
@Data
public class CompanyContractBean {
    /**
     * 租户合约Id
     */
    private Integer id;
    /**
     * 租户合约编号
     */
    private String contractNum;
    /**
     * 企业Bean
     */
    private CompanyBean companyBean;
    /**
     * 地址
     */
    private String address;
    /**
     * 车位编号
     */
    private String parkingNum;
    /**
     * 企业外键Id
     */
    private Integer companyId;
    /**
     * 合同生效日期
     */
    private String startDate;
    /**
     * 合同截止日期
     */
    private String endDate;
    /**
     * 成交价格
     */
    private double companyPrice;
    /**
     * 企业合同复印件
     */
    private String contractPicture;
    /**
     * 原合同编号
     */
    private String oldContractNum;
    /**
     * 修改的车位Id集合
     */
    private List<Integer> cbdParkingId;
}
