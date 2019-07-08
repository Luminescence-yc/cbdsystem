//package com.project.cbdsystem;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.project.bean.SellParkingBean;
//import com.project.dao.ISellParkingDao;
//import com.project.entity.SellParkingEntity;
//import com.project.mapper.SellParkingEntityMapper;
//import com.project.service.ISellParkingService;
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
// * @author 陈云龙
// * @version v1.0
// * @ClassName:出售车位
// * @Description:出售车位测试类
// * @date 2019年05月31日 10:38
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class SellParkingDaoTest {
//    @Autowired
//    private SellParkingEntityMapper sellParkingEntityMapper;
//
//    @Autowired
//    private ISellParkingDao iSellParkingDao;
//
//    @Autowired
//    private ISellParkingService service;
//
//    @Test
//    public void showSellParkingEntityInfoTest() {
//        Page<SellParkingBean>parkingBeanPage=new Page<>();
//        Map<String, String> condition=new HashMap<>();
//        condition.put("current",1+"");
//        condition.put("size",1+"");
//        condition.put("startTime","2016-05-04");
//        condition.put("endTime","2018-05-04");
//        parkingBeanPage=service.showSellParkingEntityInfo(condition);
//        System.out.println(parkingBeanPage);
//    }
//    @Test
//    public void getSellParkingByPageTest() {
//        Page<SellParkingBean>parkingBeanPage=new Page<>();
//        Map<String, String> condition=new HashMap<>();
//        condition.put("current",1+"");
//        condition.put("size",1+"");
//        condition.put("personalId",1+"");
////        condition.put("startTime","2016-05-04");
////        condition.put("endTime","2018-05-04");
//        parkingBeanPage=service.getBuyParkingByPage(condition);
//        System.out.println(parkingBeanPage);
//    }
//
//
//
//    @Test
//    public void findSellParkingEntityInfoTest(){
//        Map<String,String> map = new HashMap<String, String>();
//        map.put("startTime","2016-05-04");
//        map.put("endTime","2018-05-04");
//        map.put("address",null);
//        map.put("startSellPrice",null);
//        map.put("endSellPrice",null);
//        List<SellParkingEntity> list= null;
//         Page<SellParkingEntity> page=new Page<>(1,1);
//        try {
//            list = sellParkingEntityMapper.selectSellParkingByCondition(map,page);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        page.setRecords(list);
//        System.out.println(list);
//    }
//
//
//
//
//
//
//
//
//
//
//
///*  QueryWrapper<SellParkingEntity> queryWrapper=new QueryWrapper<>();
//
//        if(null!=map.get("startTime")){
//            queryWrapper.ge("releaseTime",map.get("startTime"));
//            System.out.println(map.get("startTime"));
//        }
//        if(null!=map.get("endTime")){
//            queryWrapper.le("releaseTime",map.get("endTime"));
//        }
//        if(null!=map.get("address")){
//            queryWrapper.like("address",map.get("address"));
//        }
//        if(null!=map.get("startSellPrice")){
//            queryWrapper.ge("sellPrice",map.get("startSellPrice"));
//        }
//        if(null!=map.get("endSellPrice")){
//            queryWrapper.le("sellPrice",map.get("endSellPrice"));
//        }*/
//
//
//
//
//}
