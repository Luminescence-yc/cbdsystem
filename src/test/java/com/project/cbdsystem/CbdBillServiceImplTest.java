package com.project.cbdsystem;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.CbdBillBean;
import com.project.service.ICbdBillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: CbdBillServiceImplTest
 * @Description: 平台账单 单元测试类
 * @date 2019年06月03日 10:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CbdBillServiceImplTest {

    @Autowired
    private ICbdBillService iCbdBillService;

    @Test
    public void showAdminPageByCondition(){

        Map<String,String> map=new HashMap<>();
        map.put("startTime","2019-05-16");
        map.put("endTime","2019-06-06");
        map.put("page","1");
        map.put("size","2");
        IPage<CbdBillBean> page = iCbdBillService.showAdminPageByCondition(map);
        System.out.println(page);

    }

    @Test
    public void countAdminService(){

        try {
            System.out.println(iCbdBillService.adminCount());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
