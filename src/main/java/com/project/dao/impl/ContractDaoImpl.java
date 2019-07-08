package com.project.dao.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.IContractDao;
import com.project.entity.ContractEntity;
import com.project.mapper.ContractEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 刘晶晶
 * @version v1.0
 * @ClassName: ContractDaoImpl
 * @Description: 合同持久层实现类
 * @date 2019年05月31日 10:57
 */
@ClassType(describe = "合同")
@Repository
public class ContractDaoImpl implements IContractDao {
    @Autowired
    private ContractEntityMapper contractEntityMapper;

    @Override
    public int addContract(ContractEntity contractEntity) {
        return contractEntityMapper.addContract(contractEntity);
    }

    @Override
    public int updateBuyer(int id, int buyerAgree) {
        return contractEntityMapper.updateBuyer(id, buyerAgree);
    }

    @Override
    public int updateSeller(int id, int sellerAgree) {
        return contractEntityMapper.updateSeller(id, sellerAgree);
    }

    @Override
    public int getContractByBuyerAgreeAndSellerAgree(int id) {
        return contractEntityMapper.getContractByBuyerAgreeAndSellerAgree(id);
    }

    @Override
    public List<ContractEntity> getContractByCondition(Page<ContractEntity> page, Map<String, String> condition) {

        return contractEntityMapper.findByCondition(page, condition);
    }

    @Override
    public int updateStatus(int id, String status) {

        return contractEntityMapper.updateStatus(id, status);
    }

    @Override
    public List<ContractEntity> getNotAcceptContract(Page<ContractEntity> page) {

        return contractEntityMapper.findNoAccept(page);
    }

    @Override
    public ContractEntity getContractBySellId(int sellId) {
        return contractEntityMapper.findContractById(sellId);
    }


    @Override
    public ContractEntity selectContractById(int id) {
        return contractEntityMapper.selectById(id);
    }

    @Override
    public int deleteContractById(int id) {
        return contractEntityMapper.deleteById(id);
    }
}
