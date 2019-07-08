package com.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CompanyBillBean;
import com.project.entity.CountEntity;

import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: ICompanyBillService
 * @Description: 企业账单 业务接口
 * @date 2019年05月31日 11:03
 */
public interface ICompanyBillService {

    /**
     * 查询-企业账单，动态查询并分页
     * @param condition 查询条件，如果查询所有，则条件为空。以起始终止时间为条件查询
     * @return 分页对象,返回对象需要封装为Bean对象
     */
     Page<CompanyBillBean> showCompanyBillPageByCondition(Map<String, String> condition);

    /**
     * 统计企业的交易总次数、交易总支出、交易总收入
     * @param companyId 企业用户ID
     * @return 交易统计Bean,包含交易总笔数，支出金额统计，收入金额总计
     */
    CountEntity companyCount(int companyId);

}
