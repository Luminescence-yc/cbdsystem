package com.project.cbdsystem;

import com.project.dao.ICompanyBillDao;
import com.project.entity.CompanyBillEntity;
import com.project.entity.CompanyEntity;
import com.project.entity.CountEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : CompanyBillDaoImplTest
 * @Date ：2019/6/1 16:41
 * @Desc ：类的介绍：
 * @author：作者：王佳伟
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyBillDaoImplTest {
    @Autowired
    private ICompanyBillDao companyBillDao;

    @Test
    public void findCbdBillInfoByCondition(){
        Map map = new HashMap();
//        startTime!=null and endTime == null

        map.put("startTime",null);
        map.put("endTime","2017-7-30");
        //try {
        //    List<CompanyBillEntity> list=companyBillDao.findCbdBillInfoByCondition(map);
        //    System.out.println(list);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
    }

    @Test
    public void addCompanyBill(){
        CompanyBillEntity com = new CompanyBillEntity();
        com.setCompanyId(1);
        com.setTradeDate("2019-09-09");
        com.setTradeTime("20:10:25");
        com.setExpend(200.00);
        com.setIncome(100.00);
        com.setRemark("测试");
        try {
            companyBillDao.addCompanyBill(com);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void countCompany(){
        try {
            CountEntity com= companyBillDao.countCompany(1);
            System.out.println(com);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
