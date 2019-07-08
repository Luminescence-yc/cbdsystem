//package com.project.cbdsystem;
//
//import com.project.dao.IRentHistoryDao;
//import com.project.entity.RentHistoryEntity;
//import com.project.mapper.RentHistoryEntityMapper;
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
// * @ClassName : RentHistoryDaoImplTest
// * @Date ：2019/6/1 18:35
// * @Desc ：类的介绍：
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class RentHistoryDaoImplTest {
//    @Autowired
//    private IRentHistoryDao rentHistoryDao;
//
//    @Test
//    public void findRentHistoryByRentPersonalId( ) throws Exception {
//        List list= rentHistoryDao.findRentHistoryByRentPersonalId(1);
//        System.out.println(list);
//    }
//
//    @Test
//    public void findRentHistoryByHirePersonalId( ) throws Exception {
//        List list=rentHistoryDao.findRentHistoryByHirePersonalId(1);
//        System.out.println(list);
//    }
//
//    @Test
//    public void addRentHistory( ) throws Exception {
//        RentHistoryEntity rent = new RentHistoryEntity();
//        rent.setAddress("测试");
//        rent.setAreaNum("10");
//        rent.setHirePersonalId(1);
//        rent.setHirePersonalId(2);
//        rent.setRentPersonalId(1);
//        rent.setParkingNum("100");
//        rent.setRentEndTime(new Date(System.currentTimeMillis()));
//        rent.setRentStartTime(new Date(System.currentTimeMillis()));
//        rentHistoryDao.addRentHistory(rent);
//    }
//}
