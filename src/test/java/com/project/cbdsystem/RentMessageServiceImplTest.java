package com.project.cbdsystem;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.PersonalParkingBean;
import com.project.bean.RentMessageBean;
import com.project.entity.RentMessageEntity;
import com.project.service.IRentMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:租车预定留言业务层实现类的测试类
 * @Description:租车预定留言业务层实现类的测试类
 * @date 2019年06月03日 9:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentMessageServiceImplTest {
    @Autowired
    private IRentMessageService iRentMessageService;
    @Test
    public void addRentMessageTest(){
        RentMessageEntity rentMessageEntity=new RentMessageEntity();
        rentMessageEntity.setPersonalId(4);
        rentMessageEntity.setRentId(7);
        rentMessageEntity.setRentPersonalId(9);
        rentMessageEntity.setRentMessage("哈喽");
        iRentMessageService.addRentMessage(rentMessageEntity);
    }
    @Test
    public void showRentMessageInfoTest(){
        Map<String,String> map=new HashMap<>();
        map.put("page", "1");
        map.put("size", "5");
        map.put("rentPersonalId", "5");
        IPage<RentMessageBean> page = iRentMessageService.showRentMessageInfo(map);
        System.out.println(page);
    }
}
