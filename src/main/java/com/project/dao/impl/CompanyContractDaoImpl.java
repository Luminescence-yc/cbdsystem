package com.project.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.bean.CompanyContractBean;
import com.project.dao.ICompanyContractDao;
import com.project.entity.CompanyContractEntity;
import com.project.mapper.CompanycontractEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:
 * @Description:
 * @date 2019年05月31日 14:26
 */
@ClassType(describe = "租户合约")
@Repository
public class CompanyContractDaoImpl implements ICompanyContractDao {
    @Autowired
    private CompanycontractEntityMapper entityMapper;
    @Override
    public int add(CompanyContractEntity contractEntity) throws Exception {
      int result=entityMapper.addCompanycontract(contractEntity);
        return result;
    }

    @Override
    public CompanyContractEntity findById(int id) throws Exception {
        CompanyContractEntity contractEntity=entityMapper.selectByContractId(id);
        return contractEntity;
    }

    @Override
    public int update(CompanyContractEntity contractEntity) throws Exception {
         int result=entityMapper.insert(contractEntity);
        return result;
    }

    @Override
    public int delete(int id) throws Exception {
        int result=entityMapper.deleteById(id);
        return result;
    }

    @Override
    public List<CompanyContractEntity> findByCondition(Page<CompanyContractEntity> page, Map<String, String> condition) throws Exception {

        return entityMapper.findByCondition(page,condition);
    }

    @Override
    public List<CompanyContractEntity> findByStartAndEnd(Page<CompanyContractEntity> page, String startDate, String endDate) throws Exception {
        return entityMapper.findByStartAndEndDate(page,startDate,endDate);
    }

    /**
     * 多条件动态查询
     *
     * @param page      分页
     * @param condition 动态条件集合 开始日期和结束日期     s登录企业用户id
     * @return Exception
     */
    @Override
    public List<CompanyContractBean> findByCompanyIdAndStartAndEndDate(Page<CompanyContractBean> page, Map<String, String> condition) throws Exception {
        return entityMapper.findByCompanyIdAndStartAndEndDate(page,condition);
    }


}
