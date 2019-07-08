package com.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.PersonalBean;
import com.project.bean.PersonalParkingBean;
import com.project.bean.SellParkingBean;
import com.project.dao.IRentMessageDao;
import com.project.dao.ISellParkingDao;
import com.project.entity.PersonalEntity;
import com.project.entity.PersonalParkingEntity;
import com.project.entity.SellParkingEntity;
import com.project.service.ISellParkingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 费宇
 * @version v1.0
 * @ClassName: SellParkingDaoImpl
 * @Description: 出售车位 业务层实现类
 * @date 2019年05月31日 12:37
 */
@Service
public class SellParkingServiceImpl implements ISellParkingService {
    @Autowired
    private ISellParkingDao sellParkingDao;
    @Autowired
    private IRentMessageDao iRentMessageDao;

    @Override
    public Page<SellParkingBean> showSellParkingEntityInfo
            (Map<String, String> condition) {
        Page<SellParkingEntity> page = this.getPageByCondition(condition);
        List<SellParkingEntity> listEntity = new ArrayList<>();
        try {
            listEntity = sellParkingDao.findSellParkingEntityInfo(condition, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SellParkingBean> listBean = this.getListBeanToListEntity(listEntity);
        Page<SellParkingBean> pageBean = new Page<>();
        BeanUtils.copyProperties(page, pageBean);
        return pageBean.setRecords(listBean);
    }

    @Override
    public int addSellParking(SellParkingBean sellParkingBean) {
        SellParkingEntity sellParkingEntity = new SellParkingEntity();
        BeanUtils.copyProperties(sellParkingBean, sellParkingEntity);
        try {
            return sellParkingDao.addSellParking(sellParkingEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Page<SellParkingBean> getSellParkingByPage
            (int personalId, int current, int size) {
        Page<SellParkingEntity> page = new Page<>(current, size);
        List<SellParkingEntity> listEntity = new ArrayList<>();
        try {
            listEntity = sellParkingDao.getSellParkingByPersonalId(personalId, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SellParkingBean> listBean = this.getListBeanToListEntity(listEntity);
        Page<SellParkingBean> pageBean = new Page<>();
        BeanUtils.copyProperties(page, pageBean);
        return pageBean.setRecords(listBean);
    }

    @Override
    public int deleteSellParkingById(int id) {
        try {
            iRentMessageDao.deleteRentMessageById(id);
            return sellParkingDao.deleteSellParkingById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Page<SellParkingBean> getBuyParkingByPage
            (int personalId, int current, int size) {
        Page<SellParkingEntity> page = new Page<>(current, size);
        List<SellParkingEntity> listEntity = new ArrayList<>();
        try {
            listEntity = sellParkingDao.getBuyParkingByPersonalId(personalId, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SellParkingBean> listBean = this.getListBeanToListEntity(listEntity);
        Page<SellParkingBean> pageBean = new Page<>();
        BeanUtils.copyProperties(page, pageBean);
        return pageBean.setRecords(listBean);
    }

    @Override
    public int updateSellStatusById(int id, String status) {
        try {

            int i = sellParkingDao.updateSellStatusById(id, status);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 分页对象转换
     *
     * @param condition 参数列表
     * @return 分页对象
     */
    private Page<SellParkingEntity> getPageByCondition(Map<String, String> condition) {
        int current = Integer.parseInt(condition.get("current"));
        int size = Integer.parseInt(condition.get("size"));
        return new Page<>(current, size);
    }

    /**
     * listBean与Entity转换
     *
     * @param listEntity 实体对象集合
     * @return bean对象结合
     */
    private List<SellParkingBean> getListBeanToListEntity(List<SellParkingEntity> listEntity) {
        /*bean集合*/
        List<SellParkingBean> listBean = new ArrayList<>();
        /*遍历entity集合*/
        for (SellParkingEntity sellParkingEntity : listEntity) {
            /*创建sellParkingBean（卖车Bean）*/
            SellParkingBean sellParkingBean = new SellParkingBean();
            /*卖车记录entity转换为bean对象*/
            BeanUtils.copyProperties(sellParkingEntity, sellParkingBean);
            /*获取个人车位对象entity*/
            PersonalParkingEntity parkingEntity = sellParkingEntity.getPersonalParkingEntity();
            PersonalParkingBean parkingBean = null;
            PersonalEntity personalEntity = null;
            /*判断个人车位对象是否为空，再进行entity转换为bean对象*/
            if (null != parkingEntity && null != parkingEntity.getId()) {
                parkingBean = new PersonalParkingBean();
                BeanUtils.copyProperties(parkingEntity, parkingBean);
                /*将转换后的个人车位bean对象封装至卖车bean对象中*/
                sellParkingBean.setParkingBean(parkingBean);
                /*获取个人车位对象中的用户对象*/
                personalEntity = parkingEntity.getPersonalEntity();
            }
            PersonalBean personalBean = null;
            if (null != personalEntity && null != personalEntity.getId()) {
                personalBean = new PersonalBean();
                BeanUtils.copyProperties(personalEntity, personalBean);
                /*将转换后的个人车位bean对象封装至卖车bean对象中*/
                sellParkingBean.getParkingBean().setPersonalBean(personalBean);
            }
            /*将封装后的bean对象添加至集合中*/
            listBean.add(sellParkingBean);
        }
        return listBean;
    }

}
