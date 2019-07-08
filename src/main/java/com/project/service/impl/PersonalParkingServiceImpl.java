package com.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.PersonalBean;
import com.project.bean.PersonalParkingBean;
import com.project.dao.*;
import com.project.entity.PersonalParkingEntity;
import com.project.entity.RentParkingEntity;
import com.project.entity.SellParkingEntity;
import com.project.service.IPersonalParkingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:个人车位
 * @Description:个人车位业务层实现类
 * @date 2019年06月01日 12:11
 */
@Component
public class PersonalParkingServiceImpl implements IPersonalParkingService {
    @Autowired
    private IPersonalParkingDao iPersonalParkingDao;
    @Autowired
    private IRentParkingDao rentParkingDao;
    @Autowired
    private IRentMessageDao rentMessageDao;
    @Autowired
    private ISellParkingDao sellParkingDao;
    @Autowired
    private ISellMessageDao sellMessageDao;

    @Override
    public IPage<PersonalParkingBean> showPersonalParkingInfo(Map<String, String> map) {
        Page<PersonalParkingEntity> page = new Page<PersonalParkingEntity>(Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
        Page<PersonalParkingBean> beanPage = new Page<PersonalParkingBean>();
        try {
            Page<PersonalParkingEntity> entityPage = page.setRecords(iPersonalParkingDao.findPersonalParkingInfo(page, map));
            BeanUtils.copyProperties(entityPage, beanPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    @Override
    public IPage<PersonalParkingBean> showPersonalParkingInfoPersonalId(Map<String, String> map, int personalId) {
        Page<PersonalParkingEntity> page = new Page<PersonalParkingEntity>(Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
        Page<PersonalParkingBean> beanPage = new Page<PersonalParkingBean>();
        try {
            Page<PersonalParkingEntity> entityPage = page.setRecords(iPersonalParkingDao.findPersonalParkingByPersonalId(page, personalId));
            BeanUtils.copyProperties(entityPage, beanPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    @Override
    public int addPersonalParking(PersonalParkingEntity personalParkingEntity) {
        try {
            int i = iPersonalParkingDao.addPersonalParking(personalParkingEntity);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updatePersonalParkingStatus(int id, String status) {
        try {
            int i = iPersonalParkingDao.updatePersonalParkingByPersonalId(id, status);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public PersonalParkingBean getPersonalParkingAndPersonalByPersonalParkingId(int personalParkingId) {
        PersonalParkingBean parkingBean = new PersonalParkingBean();
        PersonalParkingEntity parkingEntity = iPersonalParkingDao.getPersonalParkingAndPersonalByPersonalParkingId(personalParkingId);
        BeanUtils.copyProperties(parkingEntity, parkingBean);
        PersonalBean personalBean = new PersonalBean();
        BeanUtils.copyProperties(parkingEntity.getPersonalEntity(), personalBean);
        parkingBean.setPersonalBean(personalBean);
        return parkingBean;
    }

    @Override
    public int shelvesRentParkingByParkingId(int parkingId) {
        /*创建Mybatis-Plus条件查询构造器*/
        QueryWrapper<RentParkingEntity> queryWrapper = new QueryWrapper<>();
        /*封装查询条件*/
        queryWrapper.eq("parkingId", parkingId);
        /*创建出租车位对象，接收查询对象*/
        RentParkingEntity rentParkingEntity = new RentParkingEntity();
        try {
            /*1、根据个人车位ID，查询出租车位对象*/
            rentParkingEntity = rentParkingDao.findRentParkingByParkingId(queryWrapper);
            /*2、根据出租车位ID，删除出租留言表记录*/
            rentMessageDao.deleteRentMessageByRentId(rentParkingEntity.getId());
            /*3、根据出租车位ID，删除出租车位记录*/
            rentParkingDao.deleteRentParking(rentParkingEntity.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int shelvesSellParkingByParkingId(int parkingId) {

        /*创建出售车位对象，接收查询对象*/
        SellParkingEntity sellParkingEntity = new SellParkingEntity();
        try {
            /*1、根据个人车位ID，查询出售车位对象*/
            sellParkingEntity = sellParkingDao.findSellParkingByParkingId(parkingId);
            /*2、根据出售车位ID，删除出售留言表记录*/
            sellMessageDao.deleteSellMessageBySellId(sellParkingEntity.getId());
            /*3、根据出售车位ID，删除出售车位记录*/
            sellParkingDao.deleteSellParkingById(sellParkingEntity.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int updatePersonalParkingPersonalIdAndStatusByPersonalId(int personalId, int id, String status) {
        return iPersonalParkingDao.updatePersonalParkingPersonalIdAndStatusByPersonalId(personalId, id, status);
    }
}
