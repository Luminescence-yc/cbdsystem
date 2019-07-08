package com.project.cbdsystem;

import com.project.bean.AdminBean;
import com.project.bean.RoleBean;

import com.project.service.IAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 庞培波
 * @version 1.0
 * @ClassName
 * @Description
 * @date 2019年05月31日16:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminDaoTest {
    @Autowired
    private IAdminService adminService ;

    @Test
    public void findAdminByAllTest(){
        Map<String, String> condition = new HashMap<>();
        condition.put("page","2");
        condition.put("size","5");
        adminService.findAdminByAll(condition);
    }

    @Test
    public void addAdminTest(){
        AdminBean adminBean = new AdminBean();
        adminBean.setPassword("123");
        adminBean.setRealName("张三");
        adminBean.setUsername("wxc112");
        adminBean.setTel("13659894984");
        RoleBean roleBean = new RoleBean();
        roleBean.setId(3);
        RoleBean roleBean1 = new RoleBean();
        roleBean1.setId(4);
        List<RoleBean> roleBeans = new ArrayList<>();
        roleBeans.add(roleBean);
        roleBeans.add(roleBean1);
        adminBean.setList(roleBeans);
        adminService.addAdmin(adminBean);
    }

    @Test
    public void updateAdminTest(){
        AdminBean adminBean = new AdminBean();
        adminBean.setUserId(42);
        adminBean.setId(7);
        adminBean.setPassword("123");
        adminBean.setTel("13659894981");
        adminService.updateAdmin(adminBean);
    }

    @Test
    public void updateRole(){
        AdminBean adminBean = new AdminBean();
        adminBean.setUserId(42);
        adminBean.setId(7);
        List<RoleBean> roleBeans = new ArrayList<>();
        RoleBean roleBean = new RoleBean();
        roleBean.setId(4);
        RoleBean roleBean1 = new RoleBean();
        roleBean1.setId(5);
        RoleBean roleBean2 = new RoleBean();
        roleBean2.setId(6);
        roleBeans.add(roleBean);
        roleBeans.add(roleBean1);
        roleBeans.add(roleBean2);
        adminBean.setList(roleBeans);
        adminService.updateRole(adminBean);
    }

    @Test
    public void deleteAdminTest(){
        adminService.deleteAdmin(7,42);
    }

    @Test
    public void findAdminById (){
        AdminBean adminBean = adminService.findAdminById(6,12);
        System.out.println(adminBean);
    }





}
