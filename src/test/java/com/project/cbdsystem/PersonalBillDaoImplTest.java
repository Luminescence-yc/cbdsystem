package com.project.cbdsystem;

import com.project.dao.IPersonalBillDao;
import com.project.entity.CountEntity;
import com.project.entity.PersonalBillEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : PersonalBillDaoImplTest
 * @Date ：2019/6/1 17:58
 * @Desc ：类的介绍：个人账单持久层测试类
 * @author：作者：王佳伟
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonalBillDaoImplTest {
    @Autowired
    private IPersonalBillDao personalBillDao;

    @Test
    public void findPersonalBillInfoByCondition() throws Exception {
        //Map map = new HashMap();
        //map.put("startTime","0219-5-21");
        //map.put("endTime",null);
        //List list=personalBillDao.findPersonalBillInfoByCondition(map);
        //System.out.println(list);
    }

    @Test
    public void addUserBill() throws Exception {
        PersonalBillEntity per = new PersonalBillEntity();
        per.setPersonalId(1);
        per.setDealDate("2019-01-01");
        per.setDealTime("10:10:01");
        per.setExpend(100.00);
        per.setIncome(10.00);
        per.setRemark("测试");
        personalBillDao.addUserBill(per);
    }

    @Test
    public void countPersonal() throws Exception {
        CountEntity count=  personalBillDao.countPersonal(1);
        System.out.println(count);
    }

}
