package com.project.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.ISellParkingDao;
import com.project.entity.SellParkingEntity;
import com.project.mapper.SellParkingEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName: SellParkingDaoImpl
 * @Description: 出售车位 持久层实现类
 * @date 2019年05月31日 12:37
 */
@ClassType(describe = "出售车位信息")
@Repository
public class SellParkingDaoImpl implements ISellParkingDao {
    @Autowired
    private SellParkingEntityMapper sellParkingEntityMapper;

    @Override
    public List<SellParkingEntity> findSellParkingEntityInfo(Map<String, String> condition, Page<SellParkingEntity> page) {
        return sellParkingEntityMapper.selectSellParkingByCondition(condition, page);
    }

    @Override
    public int addSellParking(SellParkingEntity sellParkingEntity) {
        return sellParkingEntityMapper.addSellParking(sellParkingEntity);
    }

    @Override
    public List<SellParkingEntity> getSellParkingByPersonalId
            (int personalId, Page<SellParkingEntity> page) throws Exception {
        return sellParkingEntityMapper.selectSellParkingByUserId(personalId, page);
    }

    @Override
    public int deleteSellParkingById(int id) throws Exception {
        return sellParkingEntityMapper.deleteById(id);
    }

    @Override
    public List<SellParkingEntity> getBuyParkingByPersonalId
            (int personalId, Page<SellParkingEntity> page) throws Exception {
        return sellParkingEntityMapper.selectBuyParkingByUserId(personalId, page);
    }

    @Override
    public SellParkingEntity findSellParkingByParkingId(int parkingId) throws Exception {
        return sellParkingEntityMapper.findSellParkingByParkingId(parkingId);
    }

    @Override
    public int updateSellStatusById(int id, String status) throws Exception {
        return sellParkingEntityMapper.updateSellStatusById(id, status);
    }
}
