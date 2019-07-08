package com.project.dao.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.ISellHistoryDao;
import com.project.entity.SellHistoryEntity;
import com.project.mapper.SellHistoryEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: SellHistoryDaoImpl
 * @Description: 出售、购买交易记录 持久实现类
 * @date 2019年06月01日 15:20
 */
@ClassType(describe = "出售购买记录")
@Component
public class SellHistoryDaoImpl implements ISellHistoryDao {

    @Autowired
    private SellHistoryEntityMapper sellHistoryEntityMapper;

    @Override
    public List<SellHistoryEntity> findSellHistoryByPersonalId(int personalId, Page<SellHistoryEntity> page) throws Exception {
        return sellHistoryEntityMapper.findSellHistoryByPersonalId(personalId,page);
    }

    @Override
    public int addSellHistory(SellHistoryEntity sellHistoryEntity) throws Exception {
        return sellHistoryEntityMapper.addSellHistory(sellHistoryEntity);
    }

    @Override
    public SellHistoryEntity findBySellerId(int id) throws Exception{
            return sellHistoryEntityMapper.findSellByPersonalId(id);
    }

    @Override
    public SellHistoryEntity findByBuyerId(int id) throws Exception {
        return sellHistoryEntityMapper.findSellByPersonalId(id);
    }
}
