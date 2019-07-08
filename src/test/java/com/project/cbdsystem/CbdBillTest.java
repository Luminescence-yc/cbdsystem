//package com.project.cbdsystem;
//
//import com.project.dao.ICbdBillDao;
//import com.project.dao.IRentHistoryDao;
//import com.project.entity.CbdBillEntity;
//import com.project.entity.RentHistoryEntity;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author 刘 洪
// * @version v1.0
// * @ClassName: CbdBillTest
// * @Description: 平台账单 测试类
// * @date 2019年05月31日 15:08
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class CbdBillTest {
//    @Autowired
//    private ICbdBillDao iCbdBillDao;
//
//    @Autowired
//    private IRentHistoryDao iRentHistoryDao;
//
//    @Test
//    public void  addAdminBill(){
//
//        CbdBillEntity cbdBillEntity = new CbdBillEntity();
//        cbdBillEntity.setTradeDate("2019-12-01");
//        cbdBillEntity.setTradeTime("10:22:11");
//        cbdBillEntity.setIncome(12000.1);
//        cbdBillEntity.setExpand(16000.2);
//        cbdBillEntity.setRemark("贵");
//
//        try {
//            iCbdBillDao.addAdminBill(cbdBillEntity);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void findCbdBillInfoByCondition(){
//       // Map<String,String> map = new HashMap<String, String>();
//       //// map.put("startApplyDate","2018-11-14");
//       // List<CbdBillEntity> list= null;
//       // try {
//       //     list = iCbdBillDao.findCbdBillInfoByCondition(map);
//       // } catch (Exception e) {
//       //     e.printStackTrace();
//       // }
//       // System.out.println(list);
//    }
//
//
//
//    @Test
//    public void countAdmin(){
//        try {
//            System.out.println(iCbdBillDao.countAdmin());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void findRentHistoryByRentPersonalId(){
//        List<RentHistoryEntity> list = null;
//        try {
//            list = iRentHistoryDao.findRentHistoryByRentPersonalId(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(list);
//
//    }
//
//    @Test
//    public void findRentHistoryByHirePersonalId(){
//        List<RentHistoryEntity> list = null;
//        try {
//            list = iRentHistoryDao.findRentHistoryByHirePersonalId(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(list);
//
//    }
//
//    @Test
//    public void addRentHistory(){
//        RentHistoryEntity rentHistoryEntity = new RentHistoryEntity();
//        rentHistoryEntity.setRentPersonalId(5);
//        rentHistoryEntity.setHirePersonalId(2);
//        rentHistoryEntity.setAddress("大观苑二期");
//        rentHistoryEntity.setAreaNum("C区");
//        rentHistoryEntity.setParkingNum("C-E127");
//       // rentHistoryEntity.setRentStartTime("2019-01-01 00:00:00");
//       // rentHistoryEntity.setRentEndTime("2019-10-28 00:00:00");
//        try {
//            iRentHistoryDao.addRentHistory(rentHistoryEntity);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
