package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.project.bean.PersonalBean;
import com.project.bean.PersonalParkingBean;
import com.project.bean.RentParkingBean;
import com.project.bean.SellParkingBean;
import com.project.dao.IRentMessageDao;
import com.project.dao.IRentParkingDao;
import com.project.entity.PersonalEntity;
import com.project.entity.PersonalParkingEntity;
import com.project.entity.RentParkingEntity;
import com.project.entity.SellParkingEntity;
import com.project.service.IRentMessageService;
import com.project.service.IRentParkingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 朱 骞/陈云龙
 * @version v1.0
 * @ClassName: RentParkingServiceImpl
 * @Description: 出租车位业务层实现类
 * @date 2019年06月02日 22:55
 */
@Repository
public class RentParkingServiceImpl implements IRentParkingService {
    @Autowired
    private IRentParkingDao rentParkingDao;
    @Autowired
    private IRentMessageDao iRentMessageDao;
    @Autowired
    private IRentParkingDao iRentParkingDao;

    @Override
    public Page<RentParkingBean> showRentParkingInfo(Map<String, String> map) {

//        Page<RentParkingEntity> page = this.getPageByCondition(map);
//        List<RentParkingEntity> listEntity = new ArrayList<>();
//        try {
//            listEntity = rentParkingDao.showRentParkingInfo(page,map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        List<RentParkingBean> listBean = this.getListBeanToListEntity(listEntity);
//        Page<RentParkingBean> pageBean = new Page<>();
//        BeanUtils.copyProperties(page, pageBean);
//        return pageBean.setRecords(listBean);
        Page<RentParkingEntity> page = new Page<RentParkingEntity>(Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
        Page<RentParkingBean> beanPage = new Page<RentParkingBean>();
        try {
            Page<RentParkingEntity> entityPage = page.setRecords(rentParkingDao.showRentParkingInfo(page, map));
            BeanUtils.copyProperties(entityPage, beanPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    @Override
    public int addRentParking(RentParkingEntity rentParkingEntity) {
        try {
            rentParkingDao.addRentParking(rentParkingEntity);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public IPage<RentParkingBean> getAllRentParkingInformation(int id, int page, int size) {
        Page<RentParkingEntity> pageInfo = new Page<RentParkingEntity>(page, size);
        Page<RentParkingBean> beanPage = new Page<RentParkingBean>();

        Page<RentParkingEntity> entityPage = pageInfo.setRecords(rentParkingDao.getAllRentParkingInformation(id, pageInfo));
        List<RentParkingEntity> records = entityPage.getRecords();
        List<RentParkingBean> list = new ArrayList<>();
        BeanUtils.copyProperties(entityPage, beanPage);
        for (int i = 0; i < records.size(); i++) {
            RentParkingBean rentParkingBean = new RentParkingBean();
            rentParkingBean.setPersonalParkingBean(new PersonalParkingBean());
            BeanUtils.copyProperties(records.get(i), rentParkingBean);
            if (records.get(i).getPersonalParkingEntity() != null) {
                PersonalParkingBean personalParkingBean = rentParkingBean.getPersonalParkingBean();
                PersonalParkingEntity personalParkingEntity = records.get(i).getPersonalParkingEntity();
                rentParkingBean.setAddress(personalParkingEntity.getAddress() + personalParkingEntity.getAreaNum() + personalParkingEntity.getParkingNum());
                BeanUtils.copyProperties(personalParkingEntity, personalParkingBean);
                rentParkingBean.setCountMessage(iRentParkingDao.countRentMessage(rentParkingBean.getId()));
            }
            list.add(rentParkingBean);
        }
        beanPage.setRecords(list);
        return beanPage;
    }

    @Override
    public int deleteRentParking(int id) {
        iRentMessageDao.deleteRentMessageById(id);
        return rentParkingDao.deleteRentParking(id);
    }

    @Override
    public IPage<RentParkingBean> getAllRent(int id, int page, int size) {
        Page<RentParkingEntity> pageInfo = new Page<RentParkingEntity>(page, size);
        Page<RentParkingBean> beanPage = new Page<RentParkingBean>();

        Page<RentParkingEntity> entityPage = pageInfo.setRecords(rentParkingDao.getAllRent(id, pageInfo));
        List<RentParkingEntity> records = entityPage.getRecords();
        List<RentParkingBean> list = new ArrayList<>();
        BeanUtils.copyProperties(entityPage, beanPage);
        for (int i = 0; i < records.size(); i++) {
            RentParkingBean rentParkingBean = new RentParkingBean();
            rentParkingBean.setPersonalParkingBean(new PersonalParkingBean());
            rentParkingBean.setPersonalBean(new PersonalBean());
            BeanUtils.copyProperties(records.get(i), rentParkingBean);
            if (records.get(i).getPersonalParkingEntity() != null) {
                PersonalParkingBean personalParkingBean = rentParkingBean.getPersonalParkingBean();
                PersonalParkingEntity personalParkingEntity = records.get(i).getPersonalParkingEntity();
                rentParkingBean.setRentParkingAddress(personalParkingEntity.getAddress() + personalParkingEntity.getAreaNum() + personalParkingEntity.getParkingNum());
                BeanUtils.copyProperties(personalParkingEntity, personalParkingBean);
            }
            if (records.get(i).getPersonalEntity() != null) {
                PersonalBean personalBean = rentParkingBean.getPersonalBean();
                PersonalEntity personalEntity = records.get(i).getPersonalEntity();
                rentParkingBean.setCredit(personalEntity.getTradeNum() + "/" + personalEntity.getComplainNum());
                BeanUtils.copyProperties(personalEntity, personalBean);
            }
            list.add(rentParkingBean);

        }
        beanPage.setRecords(list);
        return beanPage;
    }

    /**
     * 分页对象转换
     *
     * @param map 参数列表
     * @return 分页对象
     */
    private Page<RentParkingEntity> getPageByCondition(Map<String, String> map) {
//        int current = Integer.parseInt(map.get("current"));
//        int size = Integer.parseInt(map.get("size"));
//        return new Page<>(current, size);
        return new Page<>();
    }

    /**
     * listBean与Entity转换
     *
     * @param listEntity 实体对象集合
     * @return bean对象结合
     */
    private List<RentParkingBean> getListBeanToListEntity(List<RentParkingEntity> listEntity) {
        /*bean集合*/
        List<RentParkingBean> listBean = new ArrayList<>();
        /*遍历entity集合*/
        for (RentParkingEntity rentParkingEntity : listEntity) {
            /*创建sellParkingBean（卖车Bean）*/
            RentParkingBean rentParkingBean = new RentParkingBean();
            /*卖车记录entity转换为bean对象*/
            BeanUtils.copyProperties(rentParkingEntity, rentParkingBean);
            /*获取个人车位对象entity*/
            PersonalParkingEntity parkingEntity = rentParkingEntity.getPersonalParkingEntity();
            PersonalParkingBean parkingBean = null;
            PersonalEntity personalEntity = null;
            /*判断个人车位对象是否为空，再进行entity转换为bean对象*/
            if (null != parkingEntity && null != parkingEntity.getId()) {
                parkingBean = new PersonalParkingBean();
                BeanUtils.copyProperties(parkingEntity, parkingBean);
                /*将转换后的个人车位bean对象封装至卖车bean对象中*/
                rentParkingBean.setPersonalParkingBean(parkingBean);
                /*获取个人车位对象中的用户对象*/
                personalEntity = parkingEntity.getPersonalEntity();
            }
            PersonalBean personalBean = null;
            if (null != personalEntity && null != personalEntity.getId()) {
                personalBean = new PersonalBean();
                BeanUtils.copyProperties(personalEntity, personalBean);
                /*将转换后的个人车位bean对象封装至卖车bean对象中*/
                rentParkingBean.getPersonalParkingBean().setPersonalBean(personalBean);
            }
            /*将封装后的bean对象添加至集合中*/
            listBean.add(rentParkingBean);
        }
        return listBean;
    }
}
