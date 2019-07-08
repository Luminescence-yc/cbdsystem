//package com.project.cbdsystem;
//
//import com.project.dao.ISellHistoryDao;
//import com.project.entity.SellHistoryEntity;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.Date;
//import java.util.List;
//
///**
// * @ClassName : SellHistoryDaoImplTest
// * @Date ：2019/6/1 18:22
// * @Desc ：类的介绍：
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class SellHistoryDaoImplTest {
//    @Autowired
//    private ISellHistoryDao sellHistoryDao;
//
//    @Test
//    public void findRentHistoryById()  {
//        try {
//            List list=sellHistoryDao.findSellHistoryById(1);
//            System.out.println(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void addSellHistory()  {
//        SellHistoryEntity sell = new SellHistoryEntity();
//        sell.setBuyPersonalId(100);
//        sell.setSellPersonalId(100);
//        sell.setParkingMessage("测试");
//        sell.setExternalPrice("测试");
//        sell.setSellDate(new Date(1231354645));
//        try {
//            sellHistoryDao.addSellHistory(sell);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
