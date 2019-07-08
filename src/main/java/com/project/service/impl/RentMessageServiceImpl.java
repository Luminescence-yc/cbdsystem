package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.RentMessageBean;
import com.project.dao.*;
import com.project.entity.*;
import com.project.service.IMessageService;
import com.project.service.IRentMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:出租车位预定留言
 * @Description:出租车位预定留言业务层实现类
 * @date 2019年06月01日 13:07
 */
@Component
public class RentMessageServiceImpl implements IRentMessageService {
    @Autowired
    private IRentMessageDao iRentMessageDao;
    @Autowired
    private IRentParkingDao iRentParkingDao;
    @Autowired
    private IRentHistoryDao iRentHistoryDao;
    @Autowired
    private IPersonalBillDao iPersonalBillDao;
    @Autowired
    private IMessageService messageService;//消息
    @Autowired
    private IPersonalDao personalDao;//个人用户

    @Override
    public int addRentMessage(RentMessageEntity rentMessageEntity) {
        try {
            MessageEntity messageEntity = new MessageEntity();
            //根据登陆用户id查询登陆用户对象，并找出登录用户名字封装给消息内容
            PersonalEntity personalEntity = personalDao.findOnePersonalInformation(rentMessageEntity.getPersonalId());
            //出租车用户
            messageEntity.setPersonalid(rentMessageEntity.getRentId());
            messageEntity.setMessagetitle("出租 ");
            messageEntity.setContent(personalEntity.getUsername() + "对你发送了租凭预定请求");//消息内容（待修改）
            messageEntity.setMessagetime(new java.sql.Date(System.currentTimeMillis()));//消息时间
            messageEntity.setMessagetype("个人消息");//消息类型
            messageService.addMessage(messageEntity);
            int i = iRentMessageDao.addRentMessage(rentMessageEntity);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Page<RentMessageBean> showRentMessageInfo(Map<String, String> map) {
        Page<RentMessageEntity> page = new Page<RentMessageEntity>(Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
        Page<RentMessageBean> beanPage = new Page<RentMessageBean>();
        try {
            Page<RentMessageEntity> entityPage = page.setRecords(iRentMessageDao.showRentMessageInfo(page, map));
            BeanUtils.copyProperties(entityPage, beanPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    @Override
    public int deleteRentMessageByRentId(int messageId, int rentId, int rentPersonId, int hirePersonId) {
        try {
            RentParkingEntity rentParking = iRentParkingDao.findRentParkingById(rentId);
            PersonalParkingEntity parking = rentParking.getPersonalParkingEntity();
            RentHistoryEntity rentHistory = new RentHistoryEntity();
            PersonalBillEntity rentPersonBill = new PersonalBillEntity();
            PersonalBillEntity hirePersonBill = new PersonalBillEntity();
            rentHistory.setAddress(parking.getAddress());
            rentHistory.setAreaNum(parking.getAreaNum());
            rentHistory.setParkingNum(parking.getParkingNum());
            rentHistory.setRentStartTime(rentParking.getStartTime());
            rentHistory.setRentEndTime(rentParking.getEndTime());
            PersonalEntity rentPerson = new PersonalEntity();
            rentPerson.setId(rentPersonId);
            PersonalEntity hirePerson = new PersonalEntity();
            hirePerson.setId(hirePersonId);
            rentHistory.setRentPersonal(rentPerson);
            rentHistory.setHirePersonal(hirePerson);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(new Date());
            rentPersonBill.setPersonalId(rentPersonId);
            rentPersonBill.setDealDate(date.split(" ")[0]);
            rentPersonBill.setDealTime(date.split(" ")[1]);
            rentPersonBill.setIncome(rentParking.getRentPrice());
            rentPersonBill.setRemark("出租了车位" + parking.getAddress() + parking.getAreaNum() + parking.getParkingNum());
            hirePersonBill.setPersonalId(hirePersonId);
            hirePersonBill.setDealDate(date.split(" ")[0]);
            hirePersonBill.setDealTime(date.split(" ")[1]);
            hirePersonBill.setExpend(rentParking.getRentPrice());
            hirePersonBill.setRemark("租赁了车位" + parking.getAddress() + parking.getAreaNum() + parking.getParkingNum());
            iRentHistoryDao.addRentHistory(rentHistory,rentPersonId,hirePersonId);
            iPersonalBillDao.addUserBill(rentPersonBill);
            iPersonalBillDao.addUserBill(hirePersonBill);

        } catch (Exception e) {
            e.printStackTrace();
        }
        iRentMessageDao.deleteRentMessageByRentId(rentId);
        iRentParkingDao.deleteRentParking(rentId);
        return 1;
    }

    @Override
    public IPage<RentMessageBean> getRentParkingByAll(int rentId, int page, int size) {
        Page<RentMessageEntity> pageInfo = new Page<RentMessageEntity>(page, size);
        Page<RentMessageBean> beanPage = new Page<RentMessageBean>();

        try {
            Page<RentMessageEntity> personalEntityPage = pageInfo.setRecords(iRentMessageDao.getRentParkingByAll(rentId, pageInfo, new HashMap<>()));
            BeanUtils.copyProperties(personalEntityPage, beanPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

}
