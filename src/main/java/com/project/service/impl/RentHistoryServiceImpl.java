package com.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.PersonalBean;
import com.project.bean.RentHistoryBean;
import com.project.dao.IRentHistoryDao;
import com.project.entity.RentHistoryEntity;
import com.project.service.IRentHistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: RentHistoryServiceImpl
 * @Description: 出租历史交易记录 业务实现类
 * @date 2019年06月03日 11:28
 */
@Service
public class RentHistoryServiceImpl implements IRentHistoryService {

    @Autowired
    private IRentHistoryDao iRentHistoryDao;

    @Override
    public Page<RentHistoryBean> showRentOutHistoryPageAllByRentOutPersonalId(int personalId, int page, int size) {
        Page<RentHistoryEntity> entityPage = new Page<RentHistoryEntity>(page,size);
        Page<RentHistoryBean> beanPage = new Page<RentHistoryBean>();

        try {
            Page<RentHistoryEntity> pageHireEntity = entityPage.setRecords(iRentHistoryDao.showRentOutHistoryByRentOutPersonalId(personalId,entityPage));
            List<RentHistoryEntity> records = pageHireEntity.getRecords();
            List<RentHistoryBean> list = new ArrayList<>();
            BeanUtils.copyProperties(pageHireEntity,beanPage);
            for (int i = 0; i < records.size(); i++) {
                RentHistoryBean rentHistoryBean = new RentHistoryBean();
                PersonalBean personalBean=new PersonalBean();
                RentHistoryEntity rentHistoryEntity = records.get(i);
                BeanUtils.copyProperties(rentHistoryEntity,rentHistoryBean);
                BeanUtils.copyProperties(rentHistoryEntity.getHirePersonal(),personalBean);
                rentHistoryBean.setHirePersonal(personalBean);
                rentHistoryBean.setAddress(rentHistoryEntity.getAddress()+
                        rentHistoryEntity.getAreaNum()+rentHistoryEntity.getParkingNum());
                list.add(rentHistoryBean);
            }
            beanPage.setRecords(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    @Override
    public Page<RentHistoryBean> showLeaseHistoryPageAllByLeasePersonalId(int personalId, int page, int size) {
        Page<RentHistoryEntity> entityPage = new Page<RentHistoryEntity>(page,size);
        Page<RentHistoryBean> beanPage = new Page<RentHistoryBean>();

        try {
            Page<RentHistoryEntity> pageHireEntity = entityPage.setRecords(iRentHistoryDao.showLeaseHistoryByLeasePersonalId(personalId,entityPage));
            List<RentHistoryEntity> records = pageHireEntity.getRecords();
            List<RentHistoryBean> list = new ArrayList<>();
            BeanUtils.copyProperties(pageHireEntity,beanPage);
            for (int i = 0; i < records.size(); i++) {
                RentHistoryBean rentHistoryBean = new RentHistoryBean();
                PersonalBean personalBean=new PersonalBean();
                RentHistoryEntity rentHistoryEntity = records.get(i);
                BeanUtils.copyProperties(rentHistoryEntity,rentHistoryBean);
                BeanUtils.copyProperties(rentHistoryEntity.getRentPersonal(),personalBean);
                rentHistoryBean.setRentPersonal(personalBean);
                rentHistoryBean.setAddress(rentHistoryEntity.getAddress()+
                        rentHistoryEntity.getAreaNum()+rentHistoryEntity.getParkingNum());
                list.add(rentHistoryBean);
            }
            beanPage.setRecords(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }
}
