package com.project.cbdsystem;

import com.project.dao.IPersonalParkingDao;
import com.project.entity.PersonalParkingEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:个人车位
 * @Description:个人车位Dao层测试类
 * @date 2019年05月31日 14:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonalParkingDaoTest {
    @Autowired
    private IPersonalParkingDao iPersonalParkingDao;

    @Test
    public void findPersonalParkingInfoTest() {
//        Map<String,String> map = new HashMap<String, String>();
//        map.put("status","处理");
//        map.put("startApplyDate","2018-11-14");
//        map.put("endApplyDate","2018-11-14");
//        List<PersonalParkingEntity> list=iPersonalParkingDao.findPersonalParkingInfo(page,map);
//        System.out.println(list);
    }

    @Test
    public void addPersonalParkingTest() {
        PersonalParkingEntity personalParkingEntity = new PersonalParkingEntity();
        personalParkingEntity.setPersonalId(3);
        personalParkingEntity.setPropertyNum("789");
        personalParkingEntity.setAddress("酒馆");
        personalParkingEntity.setAreaNum("100");
        personalParkingEntity.setParkingNum("100");
        personalParkingEntity.setPropertyImage("5.jpg");
        personalParkingEntity.setStatus("出售中");
        personalParkingEntity.setApplyDate("2019-05-31");
        iPersonalParkingDao.addPersonalParking(personalParkingEntity);
    }

    @Test
    public void deletePersonalParkingTest() {
        iPersonalParkingDao.deletePersonalParking(20);
    }

    @Test
    public void findPersonalParkingByPersonalIdTest() {
//        List<PersonalParkingEntity> list=iPersonalParkingDao.findPersonalParkingByPersonalId(4);
//        System.out.println(list);
    }

    @Test
    public void updatePersonalParkingByPersonalIdTest() {
        iPersonalParkingDao.updatePersonalParkingByPersonalId(11, "审核未通过");
    }

    @Test
    public void findPersonalParkingByAddressTest() {
        iPersonalParkingDao.findPersonalParkingByAddress("1");
    }

    @Test
    public void getPersonalParkingAndPersonalByPersonalParkingId() {
        int personalParkingId = 1;
        PersonalParkingEntity parkingEntity = iPersonalParkingDao
                .getPersonalParkingAndPersonalByPersonalParkingId(personalParkingId);
        System.out.println(parkingEntity);
    }

}
