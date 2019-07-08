package com.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CbdParkingBean;
import com.project.dao.ICbdParkingDao;
import com.project.entity.CbdParkingEntity;
import com.project.service.ICbdParkingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:CbdParkingServiceImpl
 * @Description:cbd车位管理业务接口实现类
 * @date 2019年-05月-30日 11:00
 */
@Service
@Component
public class CbdParkingServiceImpl implements ICbdParkingService {
    @Autowired
    private ICbdParkingDao cbdParkingDao;

    @Override
    public CbdParkingBean findById(int id) {
        CbdParkingBean cbdParkingBean = null;
        try {
            CbdParkingEntity cbdParkingEntity = cbdParkingDao.findCbdParkingById(id);
            if (cbdParkingEntity != null) {
                cbdParkingBean = new CbdParkingBean();
                cbdParkingBean.setAddress(cbdParkingEntity.getAddress());
                cbdParkingBean.setAreaNum(cbdParkingEntity.getAreaNum());
                cbdParkingBean.setParkingNum(cbdParkingEntity.getParkingNum());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cbdParkingBean;
    }

    @Override
    public Page<CbdParkingBean> findCbdParkingByCondition(Map<String, String> condition) {
        Page<CbdParkingEntity> pages = new Page<CbdParkingEntity>(Integer.parseInt(condition.get("page")), Integer.parseInt(condition.get("size")));
        Page<CbdParkingBean> beanPage = new Page<CbdParkingBean>();
        try {
            Page<CbdParkingEntity> entityPage = pages.setRecords(cbdParkingDao.findCbdParkingByCondition(pages, condition));
            BeanUtils.copyProperties(entityPage, beanPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    @Override
    public int addParking(CbdParkingBean cbdParkingBean) {
        int result = 0;
        try {
            List<Integer> parkingNumList = new ArrayList<>();
            int parkingNum = Integer.parseInt(cbdParkingBean.getParkingNum());
            for (int i = 0; i < cbdParkingBean.getCount(); i++) {
                parkingNumList.add(parkingNum);
                parkingNum++;
            }
            CbdParkingEntity cbdParkingEntity = new CbdParkingEntity();
            BeanUtils.copyProperties(cbdParkingBean, cbdParkingEntity);
            cbdParkingEntity.setStatus("未租赁");
            cbdParkingDao.addParking(cbdParkingEntity, parkingNumList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<String> findAllAddress() {
        return cbdParkingDao.findAllAddress();
    }

    @Override
    public List<String> findAreaByAddress(String address) throws Exception {
        return cbdParkingDao.findAreaByAddress(address);
    }

    @Override
    public Page<CbdParkingBean> findCbdParkingByArea(String address, String area, int page, int size) throws Exception {
        Page<CbdParkingEntity> pages = new Page<CbdParkingEntity>(page, size);
        Page<CbdParkingBean> beanPage = new Page<CbdParkingBean>();
        try {
            Page<CbdParkingEntity> entityPage = pages.setRecords(cbdParkingDao.findCbdParkingByArea(pages, address, area));
            BeanUtils.copyProperties(entityPage, beanPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    /**
     * 通过车位地址查询空余车位
     *
     * @param address 车位地址
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<CbdParkingBean> findByAddress(String address, int page, int size) throws Exception {
        Page<CbdParkingEntity> pages = new Page<CbdParkingEntity>(page, size);
        Page<CbdParkingBean> beanPage = new Page<CbdParkingBean>();
        if (address==null||address.length()==0){
            address=null;
        }
        try {
            Page<CbdParkingEntity> entityPage = pages.setRecords(cbdParkingDao.findByAddress(pages,address));
            BeanUtils.copyProperties(entityPage, beanPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }


}
