package com.project.cbdsystem;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.RentHistoryBean;
import com.project.service.IRentHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: RentHistoryServiceImplTest
 * @Description: 出租、租赁历史交易记录 单元测试
 * @date 2019年06月03日 12:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentHistoryServiceImplTest {

    @Autowired
    private IRentHistoryService iRentHistoryService;

    @Test
    public void showRentHistoryPageAllByHirePersonalId(){
        Page<RentHistoryBean> page = iRentHistoryService.showRentOutHistoryPageAllByRentOutPersonalId(1,1,5);
        System.out.println(page);

    }

    @Test
    public void showRentHistoryPageAllByRentPersonalId(){
        Page<RentHistoryBean> page = iRentHistoryService.showLeaseHistoryPageAllByLeasePersonalId(5,1,5);
        System.out.println(page);

    }

}
