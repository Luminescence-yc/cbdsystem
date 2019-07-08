package com.project.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.IRentParkingDao;
import com.project.entity.PersonalEntity;
import com.project.entity.RentParkingEntity;
import com.project.mapper.RentParkingEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 朱 骞/陈云龙
 * @version v1.0
 * @ClassName: RentParkingDaoImpl
 * @Description: 出租车位信息持久层实现类
 * @date 2019年05月31日 15:10
 */
@ClassType(describe = "出租车位信息")
@Repository
public class RentParkingDaoImpl implements IRentParkingDao {
    @Autowired
    private RentParkingEntityMapper rentParkingEntityMapper;

    @Override
    public List<RentParkingEntity> showRentParkingInfo(Page<RentParkingEntity> page, Map<String, String> map) {
        return rentParkingEntityMapper.selectRentParkingInfo(page, map);
    }

    @Override
    public int addRentParking(RentParkingEntity rentParkingEntity) {
        return rentParkingEntityMapper.insert(rentParkingEntity);
    }

    @Override
    public List<RentParkingEntity> getAllRentParkingInformation(int id, Page<RentParkingEntity> page) {
        return rentParkingEntityMapper.getAllRentParkingInformation(id, page);
    }


    @Override
    public int deleteRentParking(int id) {
        return rentParkingEntityMapper.deleteById(id);
    }

    @Override
    public List<RentParkingEntity> getAllRent(int id, Page<RentParkingEntity> page) {

        return rentParkingEntityMapper.getAllRent(id, page);
    }

    @Override
    public int countRentMessage(int id) {
        return rentParkingEntityMapper.countRentMessage(id);
    }

    @Override
    public RentParkingEntity findRentParkingByParkingId(QueryWrapper<RentParkingEntity> queryWrapper) throws Exception {
        return rentParkingEntityMapper.selectOne(queryWrapper);
    }

    @Override
    public RentParkingEntity findRentParkingById(int id) throws Exception {
        return rentParkingEntityMapper.findById(id);
    }
}
