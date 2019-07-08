package com.project.dao.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.ISellMessageDao;
import com.project.entity.SellMessageEntity;
import com.project.mapper.SellMessageEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:出售车位预定留言
 * @Description:出售车位预定留言持久层实现类
 * @date 2019年06月01日 10:30
 */
@ClassType(describe = "出售车位预定留言")
@Component
public class SellMessageDaoImpl implements ISellMessageDao {
    @Autowired
    private SellMessageEntityMapper sellMessageEntityMapper;

    @Override
    public int addSellMessage(SellMessageEntity sellMessageEntity) {
        return sellMessageEntityMapper.addSellMessage(sellMessageEntity);
    }

    @Override
    public int deleteSellMessage(int id) {
        return sellMessageEntityMapper.deleteById(id);
    }

    @Override
    public int deleteSellMessageBySellId(int id) {
        return sellMessageEntityMapper.deleteSellMessageBySellId(id);
    }

    @Override
    public int deleteSellMessageByPersonIdAndSellId(int sellId, int personalId) {
        return sellMessageEntityMapper.deleteSellMessageByPersonIdAndSellId(sellId, personalId);
    }

    @Override
    public List<SellMessageEntity> getSellParkingByAll(int id, Page<SellMessageEntity> page, Map<String, String> condition) throws Exception {
        return sellMessageEntityMapper.getSellParkingByAll(id, page, condition);
    }
}
