package com.project.cbdsystem;

import com.project.dao.IRentMessageDao;
import com.project.entity.RentMessageEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:出租车位留言
 * @Description:出租车位留言持久层测试类
 * @date 2019年05月31日 18:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentMessageDaoTest {
    @Autowired
    private IRentMessageDao iRentMessageDao;
    @Test
    public void addRentMessageTest(){
        RentMessageEntity rentMessageEntity=new RentMessageEntity();
        rentMessageEntity.setPersonalId(6);
        rentMessageEntity.setRentId(9);
        rentMessageEntity.setRentPersonalId(13);
        rentMessageEntity.setRentMessage("就很棒");
        iRentMessageDao.addRentMessage(rentMessageEntity);
    }
    @Test
    public void deleteRentMessageByIdTest(){
        iRentMessageDao.deleteRentMessageById(26);
    }
}
