package com.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CbdBillBean;
import com.project.dao.ICbdBillDao;
import com.project.entity.CbdBillEntity;
import com.project.entity.CountEntity;
import com.project.service.ICbdBillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: CbdBillServiceImpl
 * @Description: 平台账单 业务实现类
 * @date 2019年06月02日 23:26
 */
@Service
public class CbdBillServiceImpl implements ICbdBillService {

    @Autowired
    private ICbdBillDao iCbdBillDao;

    @Override
    public Page<CbdBillBean> showAdminPageByCondition(Map<String, String> condition) {
        Page<CbdBillEntity> page = new Page<CbdBillEntity>(Integer.parseInt(condition.get("page")),Integer.parseInt(condition.get("size")));
        Page<CbdBillBean> beanPage = new Page<CbdBillBean>();

        try {
            Page<CbdBillEntity> entityPage = page.setRecords(iCbdBillDao.findCbdBillInfoByCondition(page,condition));
            List<CbdBillEntity> records = entityPage.getRecords();
            List<CbdBillBean> list = new ArrayList<>();
            BeanUtils.copyProperties(entityPage,beanPage);

            for (int i = 0;i < records.size();i++){
                CbdBillBean cbdBillBean = new CbdBillBean();
                BeanUtils.copyProperties(records.get(i),cbdBillBean);
                list.add(cbdBillBean);
            }

            beanPage.setRecords(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    @Override
    public CountEntity adminCount(){
        CountEntity countEntity = null;
        try {
            countEntity =  iCbdBillDao.countAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countEntity;
    }
}
