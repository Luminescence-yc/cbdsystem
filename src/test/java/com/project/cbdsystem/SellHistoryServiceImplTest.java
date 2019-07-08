package com.project.cbdsystem;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.SellHistoryBean;
import com.project.service.ISellHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: SellHistoryServiceImplTest
 * @Description: 出售历史交易记录 单元测试类
 * @date 2019年06月03日 14:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SellHistoryServiceImplTest {

    @Autowired
    private ISellHistoryService iSellHistoryService;

    @Test
    public void showSellHistoryPageAllByPersonalId(){
        Page<SellHistoryBean> page = iSellHistoryService.showSellHistoryPageAllByPersonalId(1,1,5);
        System.out.println(page);
    }
}
