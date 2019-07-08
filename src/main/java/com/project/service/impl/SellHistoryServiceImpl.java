package com.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.PersonalBean;
import com.project.bean.SellHistoryBean;
import com.project.dao.ISellHistoryDao;
import com.project.entity.SellHistoryEntity;
import com.project.service.ISellHistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: SellHistoryServiceImpl
 * @Description: 出售历史交易记录 业务实现接口
 * @date 2019年06月03日 14:26
 */
@Service
public class SellHistoryServiceImpl implements ISellHistoryService {

    @Autowired
    private ISellHistoryDao iSellHistoryDao;

    @Override
    public Page<SellHistoryBean> showSellHistoryPageAllByPersonalId(int personalId, int page, int size) {
        Page<SellHistoryEntity> entityPage = new Page<SellHistoryEntity>(page,size);
        Page<SellHistoryBean> beanPage = new Page<SellHistoryBean>();

        try {
            Page<SellHistoryEntity> pageHireEntity = entityPage.setRecords(iSellHistoryDao.findSellHistoryByPersonalId(personalId,entityPage));
            List<SellHistoryEntity> records = pageHireEntity.getRecords();
            List<SellHistoryBean> list = new ArrayList<>();
            BeanUtils.copyProperties(pageHireEntity,beanPage);
            for (int i = 0; i < records.size(); i++) {
                SellHistoryBean sellHistoryBean = new SellHistoryBean();
                PersonalBean seller=new PersonalBean();
                PersonalBean buyer=new PersonalBean();
                BeanUtils.copyProperties(records.get(i),sellHistoryBean);
                BeanUtils.copyProperties(records.get(i).getSellPersonal(),seller);
                BeanUtils.copyProperties(records.get(i).getBuyPersonal(),buyer);
                sellHistoryBean.setSellPersonal(seller);
                sellHistoryBean.setBuyPersonal(buyer);
                list.add(sellHistoryBean);
            }
            beanPage.setRecords(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }
}
