/*
package com.project.cbdsystem;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CompanyBillBean;
import com.project.service.ICompanyBillService;
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
 * @ClassName: CompanyBillServiceImplTest
 * @Description: 企业账单 单元测试类
 * @date 2019年06月03日 10:52
 *//*

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyBillServiceImplTest {

    @Autowired
    private ICompanyBillService iCompanyBillService;

    @Test
    public void adminCount(){

        try {
            System.out.println(iCompanyBillService.companyCount(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void showCompanyBillPageByCondition(){
        Map<String,String> map = new HashMap<>();
        map.put("page","1");
        map.put("size","3");
        map.put("startTime","2019-05-01");
        map.put("endTime","2019-07-15");
        Page<CompanyBillBean> page = iCompanyBillService.showCompanyBillPageByCondition(map);
        System.out.println(map);

    }

}
*/
