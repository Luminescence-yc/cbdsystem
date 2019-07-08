package com.project.dao.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.IRentHistoryDao;
import com.project.entity.RentHistoryEntity;
import com.project.mapper.RentHistoryEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: IRentHistoryDaoImpl
 * @Description: 出租、租赁 持久实现类
 * @date 2019年06月01日 14:29
 */
@ClassType(describe = "出租、租赁记录")
@Component
public class RentHistoryDaoImpl implements IRentHistoryDao {

    @Autowired
    private RentHistoryEntityMapper rentHistoryEntityMapper;

    @Override
    public List<RentHistoryEntity> showRentOutHistoryByRentOutPersonalId(int personalId, Page<RentHistoryEntity> page) throws Exception {
        return rentHistoryEntityMapper.showRentOutHistoryByRentOutPersonalId(personalId,page);
    }

    @Override
    public List<RentHistoryEntity> showLeaseHistoryByLeasePersonalId(int personalId,Page<RentHistoryEntity> page) throws Exception {
        return rentHistoryEntityMapper.showLeaseHistoryByLeasePersonalId(personalId,page);
    }

    @Override
    public int addRentHistory(RentHistoryEntity rentHistoryEntity,int rid,int hid) throws Exception {
        return rentHistoryEntityMapper.addRentHistory(rentHistoryEntity,rid,hid);
    }

    @Override
    public RentHistoryEntity findByRentPersonalId(int id) throws Exception {
        return rentHistoryEntityMapper.findByPersonalId(id);
    }

    @Override
    public RentHistoryEntity findByHirePersonalId(int id) throws Exception {
        return rentHistoryEntityMapper.findByHirePersonalId(id);
    }
}
