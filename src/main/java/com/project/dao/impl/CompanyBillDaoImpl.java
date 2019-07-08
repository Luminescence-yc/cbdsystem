package com.project.dao.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.ICompanyBillDao;
import com.project.entity.CompanyBillEntity;
import com.project.entity.CountEntity;
import com.project.mapper.CompanyBillEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: ICompanyBillDaoImpl
 * @Description: 企业账单 持久实现类
 * @date 2019年06月01日 11:22
 */
@ClassType(describe = "企业账单")
@Component
public class CompanyBillDaoImpl implements ICompanyBillDao {

    @Autowired
    private CompanyBillEntityMapper companyBillEntityMapper;

    @Override

    public List<CompanyBillEntity> findCbdBillInfoByCondition(Page<CompanyBillEntity> page,Map<String, String> condition) throws Exception {
        return companyBillEntityMapper.findCbdBillInfoByCondition(page,condition);
    }

    @Override
    public int addCompanyBill(CompanyBillEntity companyBillEntity) throws Exception {
        return companyBillEntityMapper.addCompanyBill(companyBillEntity);
    }

    @Override
    public CountEntity countCompany(int companyId) throws Exception {
        return companyBillEntityMapper.countAdmin(companyId);
    }
}
