package com.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CompanyBillBean;
import com.project.dao.ICompanyBillDao;
import com.project.entity.CompanyBillEntity;
import com.project.entity.CountEntity;
import com.project.service.ICompanyBillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: CompanyBillServiceImpl
 * @Description: 企业账单 业务实现类
 * @date 2019年06月03日 0:14
 */
@Service
public class CompanyBillServiceImpl implements ICompanyBillService {

    @Autowired
    private ICompanyBillDao iCompanyBillDao;

    @Override
    public Page<CompanyBillBean> showCompanyBillPageByCondition(Map<String, String> condition) {
        Page<CompanyBillEntity> page = new Page<CompanyBillEntity>(Integer.parseInt(condition.get("page")),Integer.parseInt(condition.get("size")));
        Page<CompanyBillBean> beanPage = new Page<CompanyBillBean>();

        try {
            Page<CompanyBillEntity> entityPage = page.setRecords(iCompanyBillDao.findCbdBillInfoByCondition(page,condition));
            List<CompanyBillEntity> records = entityPage.getRecords();
            List<CompanyBillBean> list = new ArrayList<>();
            BeanUtils.copyProperties(entityPage,beanPage);

            for(int i = 0;i < records.size();i++){
                CompanyBillBean companyBillBean = new CompanyBillBean();
                BeanUtils.copyProperties(records.get(i),companyBillBean);
                list.add(companyBillBean);
            }
            beanPage.setRecords(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    @Override
    public CountEntity companyCount(int companyId){
        CountEntity countEntity = null;
        try {
            countEntity = iCompanyBillDao.countCompany(companyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countEntity;
    }
}
