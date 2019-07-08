package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.MessageBean;
import com.project.dao.IMessageDao;
import com.project.entity.MessageEntity;
import com.project.service.IMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangcheng
 * @ClassName:
 * @Description:
 * @date 2019年06月03日 10:17
 */
@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private IMessageDao iMessageDao;
    @Override
    public int addMessage(MessageEntity messageEntity) {
        try {
            return iMessageDao.addMessage(messageEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public IPage<MessageBean> findMssageByPersonalid(int id, int page, int size) {
        Page<MessageEntity> pages=new Page<>(page,size);
        Page<MessageBean> beanPage=new Page<>();
        try {
            Page<MessageEntity> entityPage = pages.setRecords(iMessageDao.findMssageByPersonalid(pages, id));
            List<MessageEntity> records = entityPage.getRecords();
            List<MessageBean> list=new ArrayList<>();
            for (int i = 0; i < records.size(); i++) {
                MessageBean messageBean=new MessageBean();
                BeanUtils.copyProperties(records.get(i), messageBean);
                list.add(messageBean);
                BeanUtils.copyProperties(entityPage, beanPage);
                beanPage.setRecords(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }
}
