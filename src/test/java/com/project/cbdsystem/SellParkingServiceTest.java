package com.project.cbdsystem;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.SellParkingBean;
import com.project.service.ISellParkingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 费 宇
 * @version v1.0
 * @ClassName:
 * @Description:
 * @date 2019年06月05日 19:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SellParkingServiceTest {
    @Autowired
    private ISellParkingService sellParkingService;

    @Test
    public void getSellParkingByPage() {
        int personalId = 1;
        int current = 1;
        int size = 1;
        Page<SellParkingBean> page = sellParkingService.getBuyParkingByPage(personalId,current,size);
        System.out.println(page);
    }


    @Test
    public void updateSellStatusByIdTest(){
        sellParkingService.updateSellStatusById(26,"交易中");
    }
}
