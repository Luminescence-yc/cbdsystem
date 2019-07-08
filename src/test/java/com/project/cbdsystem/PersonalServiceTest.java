package com.project.cbdsystem;

import com.project.bean.PersonalBean;
import com.project.dao.IPersonalDao;
import com.project.entity.PersonalEntity;
import com.project.service.IPersonalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 费宇
 * @version v1.0
 * @ClassName: PersonalServiceTest
 * @Description: 个人用户业务层测试类
 * @date 2019年06月3日 10:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonalServiceTest {
    @Autowired
    private IPersonalDao personalDao;

    @Autowired
    private IPersonalService personalService;

    @Test
    public void findPersonalByUsernameTest() {
        String username = "测试fy1234";
        int i = personalService.findPersonalByUsername(username);
        System.out.println(i);
    }


    @Test
    public void registerUserAndPersonal() {
        PersonalBean personalBean = new PersonalBean();
        personalBean.setRelName("测试fy123");
        personalBean.setAddress("测试fy123");
        personalBean.setTel("测试fy123");
        personalBean.setIdCard("测试fy123");
        personalBean.setJobDescription("测试fy123");
        personalBean.setEmail("测试fy123");
//        personalBean.setComplainNum(0);
//        personalBean.setTradeNum(0);

        personalBean.setUsername("测试fy123");
        personalBean.setPassword("测试fy123");
        personalBean.setSalt("测试fy123");

        int i = personalService.registerUserAndPersonal(personalBean);
        System.out.println(i);
    }

    @Test
    public void addPersonal() throws Exception {
        PersonalEntity personalEntity = new PersonalEntity();
        personalEntity.setRelName("测试fy");
        personalEntity.setAddress("测试fy");
        personalEntity.setTel("测试fy");
        personalEntity.setIdCard("测试fy");
        personalEntity.setJobDescription("测试fy");
        personalEntity.setEmail("测试fy");
        personalEntity.setComplainNum(0);
        personalEntity.setTradeNum(0);
        personalEntity.setUserId(25);
        personalEntity.setUsername("测试fy");
        int i = personalDao.addPersonal(personalEntity);
        System.out.println(i);
    }


}
