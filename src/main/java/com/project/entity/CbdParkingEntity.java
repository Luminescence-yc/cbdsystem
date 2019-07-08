package com.project.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Date;
/**
 * @ClassName:CbdParkingEntity
 * @Description:cbd车位管理实体类
 * @author 李杰郊
 * @version v1.0
 * @date 2019年-05月-30日 10:00
 */
@Data
@TableName(value = "t_cbdparking")
public class CbdParkingEntity {
    /**车位编号*/
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**租户合约对象*/
    @TableField(exist = false)
    private CompanyContractEntity companycontractEntity;
    /**外部合约对象*/
    @TableField(exist = false)
    private ExternalContractEntity externalcontractEntity;
    /**企业用户对象*/
    @TableField(exist = false)
    private CompanyEntity companyEntity;
    /**车位小区地址*/
    private String address;
    /**区域编号*/
    private String areaNum;
    /**车位号码*/
    private String parkingNum;
    /**最长可租日期*/
    private String finalDate;
    /**车位状态*/
    private String status;
}