package com.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.PersonalBillBean;
import com.project.dao.IPersonalBillDao;
import com.project.entity.CountEntity;
import com.project.entity.PersonalBillEntity;
import com.project.service.IPersonalBillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: PersonalBillServiceImpl
 * @Description: 个人账单 业务实现类
 * @date 2019年06月03日 0:35
 */
@Service
public class PersonalBillServiceImpl implements IPersonalBillService {

    @Autowired
    private IPersonalBillDao iPersonalBillDao;

    @Override
    public Page<PersonalBillBean> showPersonalPageByCondition(Map<String, String> condition) {
        Page<PersonalBillEntity> page = new Page<PersonalBillEntity>(Integer.parseInt(condition.get("page")),Integer.parseInt(condition.get("size")));
        Page<PersonalBillBean> beanPage = new Page<PersonalBillBean>();
        try {
            Page<PersonalBillEntity> entityPage = page.setRecords(iPersonalBillDao.findPersonalBillInfoByCondition(page,condition));
            List<PersonalBillEntity> records = entityPage.getRecords();
            List<PersonalBillBean> list = new ArrayList<>();
            BeanUtils.copyProperties(entityPage, beanPage);

            for (int i = 0;i < records.size();i++){
                PersonalBillBean personalBillBean = new PersonalBillBean();
                BeanUtils.copyProperties(records.get(i),personalBillBean);
                list.add(personalBillBean);
            }

            beanPage.setRecords(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    @Override
    public CountEntity personalCount(int personalId){

        CountEntity countEntity = null;
        try {
            countEntity = iPersonalBillDao.countPersonal(personalId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countEntity;
    }
}
