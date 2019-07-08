package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.SellMessageBean;
import com.project.dao.IPersonalDao;
import com.project.dao.ISellMessageDao;
import com.project.entity.MessageEntity;
import com.project.entity.PersonalEntity;
import com.project.entity.SellMessageEntity;
import com.project.service.IMessageService;
import com.project.service.ISellMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.HashMap;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:出售预定留言
 * @Description:出售预定留言业务层实现类
 * @date 2019年06月01日 13:05
 */
@Component
public class SellMessageServiceImpl implements ISellMessageService {
    @Autowired
    private ISellMessageDao iSellMessageDao;
    @Autowired
    private IMessageService messageService;//消息
    @Autowired
    private IPersonalDao personalDao;//个人用户

    @Override
    public int addSellMessage(SellMessageEntity sellMessageEntity) {
        try {

            MessageEntity messageEntity = new MessageEntity();
            //根据登陆用户id查询登陆用户对象，并找出登录用户名字封装给消息内容
            PersonalEntity personalEntity = personalDao.findOnePersonalInformation(sellMessageEntity.getPersonalId());
            //卖车用户
            messageEntity.setPersonalid(sellMessageEntity.getSellPersonalId());
            messageEntity.setMessagetitle("出售 ");
            messageEntity.setContent(personalEntity.getUsername() + "对你发送了购买预定请求");//消息内容（待修改）
            messageEntity.setMessagetime(new Date(System.currentTimeMillis()));//消息时间
            messageEntity.setMessagetype("个人消息");//消息类型
            messageService.addMessage(messageEntity);
            int i = iSellMessageDao.addSellMessage(sellMessageEntity);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteSellMessageByRentId(int id) {
        return iSellMessageDao.deleteSellMessageBySellId(id);
    }

    @Override
    public IPage<SellMessageBean> getSellParkingByAll(int id, int page, int size) {
        Page<SellMessageEntity> pageInfo = new Page<SellMessageEntity>(page, size);
        Page<SellMessageBean> beanPage = new Page<SellMessageBean>();
        try {
            Page<SellMessageEntity> personalEntityPage = pageInfo.setRecords(iSellMessageDao.getSellParkingByAll(id, pageInfo, new HashMap<>()));
            BeanUtils.copyProperties(personalEntityPage, beanPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

}
