package com.project.cbdsystem;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.ContractBean;
import com.project.bean.PersonalParkingBean;
import com.project.entity.PersonalParkingEntity;
import com.project.service.IPersonalParkingService;
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
 * @ClassName:个人车位业务层实现类的测试类
 * @Description:个人车位业务层实现类的测试类
 * @date 2019年06月03日 9:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonalParkingServiceImplTest {
    @Autowired
    private IPersonalParkingService iPersonalParkingService;
    @Test
    public void showPersonalParkingInfoTest(){
        Map<String,String> map=new HashMap<>();
        map.put("page", "1");
        map.put("size", "5");
        map.put("status", "处理");
        map.put("startApplyDate", "2018-11-14");
        map.put("endApplyDate", "2019-06-03");
        IPage<PersonalParkingBean> page = iPersonalParkingService.showPersonalParkingInfo(map);
        System.out.println(page);
    }
    @Test
    public void addPersonalParkingTest(){
        PersonalParkingEntity personalParkingEntity=new PersonalParkingEntity();
        personalParkingEntity.setPersonalId(9);
        personalParkingEntity.setPropertyNum("2615");
        personalParkingEntity.setAddress("万达");
        personalParkingEntity.setAreaNum("1234");
        personalParkingEntity.setParkingNum("1234");
        personalParkingEntity.setPropertyImage("j.jpg");
        personalParkingEntity.setStatus("出售中");
        personalParkingEntity.setApplyDate("2019-05-31");
        iPersonalParkingService.addPersonalParking(personalParkingEntity);
    }
    @Test
    public void updatePersonalParkingStatusTest(){
        iPersonalParkingService.updatePersonalParkingStatus(20,"未发布");
    }
}
