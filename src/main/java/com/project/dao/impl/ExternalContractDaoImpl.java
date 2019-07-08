package com.project.dao.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.IExternalContractDao;
import com.project.entity.CompanyContractEntity;
import com.project.entity.ExternalContractEntity;
import com.project.mapper.ExternalcontractEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:
 * @Description:
 * @date 2019年05月31日 15:15
 */
@ClassType(describe = "外部合约")
@Repository
public class ExternalContractDaoImpl implements IExternalContractDao {
    @Autowired
    private ExternalcontractEntityMapper entityMapper;
    @Override
    public int addExternalContract(ExternalContractEntity contractEntity) throws Exception {
       int result=entityMapper.insert(contractEntity);
        return result;
    }

    @Override
    public ExternalContractEntity findById(int id) throws Exception {
        ExternalContractEntity contractEntity=entityMapper.selectById(id);
        return contractEntity;
    }

    @Override
    public int delete(int id) throws Exception {
        int result=entityMapper.deleteById(id);
        return result;
    }

    @Override
    public List<ExternalContractEntity> findByCondition(Page<ExternalContractEntity> page, Map<String, String> condition) throws Exception {
        List<ExternalContractEntity> list=entityMapper.findByCondition(page,condition);
        return list;
    }

    @Override
    public int update(ExternalContractEntity contractEntity) throws Exception {
        int result=entityMapper.insert(contractEntity);
        return result;
    }
}
