package com.project.cbdsystem;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.RentParkingBean;
import com.project.bean.SellMessageBean;
import com.project.entity.SellMessageEntity;
import com.project.service.ISellMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:卖车预定留言业务层实现类的测试类
 * @Description:卖车预定留言业务层实现类的测试类
 * @date 2019年06月03日 9:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SellMessageServiceImplTest {
    @Autowired
    private ISellMessageService iSellMessageService;
    @Test
    public void addSellMessageTest(){
        SellMessageEntity sellMessageEntity=new SellMessageEntity();
        sellMessageEntity.setPersonalId(2);
        sellMessageEntity.setSellPersonalId(5);
        sellMessageEntity.setSellId(11);
        sellMessageEntity.setSellMessage("你是真滴牛皮1");
        iSellMessageService.addSellMessage(sellMessageEntity);
    }

    @Test
    public void getRentParkingByAllTesy(){
        IPage<SellMessageBean> page =iSellMessageService.getSellParkingByAll(4,1,3);
        int i =1 ;
        System.out.println(page);
    }
}
