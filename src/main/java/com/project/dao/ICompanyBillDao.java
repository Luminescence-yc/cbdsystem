package com.project.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.CompanyBillEntity;
import com.project.entity.CountEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName:
 * @Description:
 * @date 2019年05月31日 11:34
 */
public interface ICompanyBillDao {

    /**
     * 动态查询，企业账单
     * @param condition 查询条件 起始时间(startTime)，结束时间(endTime)
     * @param page 分页对象
     * @return 平台账单对象集合
     * @throws Exception
     */
    List<CompanyBillEntity> findCbdBillInfoByCondition(Page<CompanyBillEntity> page, Map<String, String> condition) throws Exception;

    /**
     * 添加 企业账单
     * @param companyBillEntity 企业账单对象
     * @return 添加成功返回 1
     * @throws Exception
     */
    int addCompanyBill(CompanyBillEntity companyBillEntity)throws Exception;

    /**
     * 统计 企业交易总次数、交易总支出、交易总收入
     * @param companyId 企业ID
     * @return 统计对象
     * @throws Exception
     */
    CountEntity countCompany(int companyId)throws Exception;
}
