/*
package com.project.cbdsystem;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.PersonalBillBean;
import com.project.service.IPersonalBillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: PersonalBillServiceImplTest
 * @Description: 个人账单 单元测试类
 * @date 2019年06月03日 10:55
 *//*

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonalBillServiceImplTest {

    @Autowired
    private IPersonalBillService iPersonalBillService;



    @Test
    public void PersonalCount(){

//        try {
//            System.out.println(iPersonalBillService.PersonalCount(1));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
//            System.out.println(iPersonalBillService.PersonalCount(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(iPersonalBillService.personalCount(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void showPersonalPageByCondition(){

        Map<String,String> map = new HashMap<>();
        map.put("startTime","2019-05-16");
        map.put("endTime","2019-06-06");
        map.put("page","1");
        map.put("size","2");
        IPage<PersonalBillBean> page = iPersonalBillService.showPersonalPageByCondition(map);
        System.out.println(page);
    }

}
*/
