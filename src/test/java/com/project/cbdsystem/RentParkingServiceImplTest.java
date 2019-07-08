package com.project.cbdsystem;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.RentParkingBean;
import com.project.entity.RentParkingEntity;
import com.project.entity.SellParkingEntity;
import com.project.mapper.RentParkingEntityMapper;
import com.project.service.IRentParkingService;
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
 * @ClassName:出租车位业务层实现类的测试类
 * @Description:出租车位业务层实现类的测试类
 * @date 2019年06月03日 11:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentParkingServiceImplTest {
    @Autowired
    private IRentParkingService iRentParkingService;

    @Autowired
    private RentParkingEntityMapper rentParkingEntityMapper;

    @Test
    public void showRentParkingInfoTest(){
        Map<String,String> map=new HashMap<>();
        map.put("page", "1");
        map.put("size", "5");
        map.put("beginStartTime", "2018-01-01 12:00:00");
        map.put("overStartTime", null);
        map.put("beginEndTime", null);
        map.put("overEndTime", null);
        map.put("address", null);
        map.put("startRentPrice", "500");
        map.put("endRentPrice", "1000");
        IPage<RentParkingBean> page = iRentParkingService.showRentParkingInfo(map);
        System.out.println(page);
//        Map<String,String> map = new HashMap<String, String>();
//        map.put("beginStartTime","2016-01-01 12:00:00");
//        map.put("overStartTime", "2018-05-12 12:00:00");
//        map.put("beginEndTime", "2019-05-25 12:00:00");
//        map.put("overEndTime", "2025-05-01 12:00:00");
//        map.put("address", "%九%");
//        map.put("startRentPrice", "800");
//        map.put("endRentPrice", "1000");
//        List<RentParkingEntity> list= null;
//        Page<RentParkingEntity> page=new Page<>(1,2);
//        try {
//            list = rentParkingEntityMapper.selectRentParkingInfo(page,map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        page.setRecords(list);
//        System.out.println(list);
    }

    @Test
    public void addRentParkingTest(){
        RentParkingEntity rentParkingEntity=new RentParkingEntity();
        rentParkingEntity.setParkingId(11);
        rentParkingEntity.setStartTime("2017-06-11 12:00:00");
        rentParkingEntity.setEndTime("2019-06-03 12:00:00");
        rentParkingEntity.setRentPrice(1000.0);
        iRentParkingService.addRentParking(rentParkingEntity);
    }
}
