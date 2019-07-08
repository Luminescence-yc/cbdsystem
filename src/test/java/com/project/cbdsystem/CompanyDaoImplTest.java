package com.project.cbdsystem;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.dao.ICompanyDao;
import com.project.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : CompanyDaoImplTest
 * @Date ：2019/6/1 14:40
 * @Desc ：类的介绍：
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyDaoImplTest {
    @Autowired
    private ICompanyDao companyDao;

    @Test
    public void findCompanyByCondition() {
        Map map = new HashMap();
        map.put("floor",8);
        map.put("contactPerson",44);
        map.put("tel","13578963214");
        Page<CompanyEntity> page = new Page<CompanyEntity>(1,10);
        try {
            Page<CompanyEntity> page1=page.setRecords(companyDao.findCompanyByCondition(page,map));
            System.out.println(page1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void findCompanyById() {
        CompanyEntity com=companyDao.findCompanyById(1);
        System.out.println(com);
    }

    @Test
    public void addCompany() {
        CompanyEntity com=new CompanyEntity();
        UserEntity user = new  UserEntity();
        user.setId(22);
        user.setUsername("测试");
        com.setUserEntity(user);
        com.setFloor("1213");
        com.setCompanyName("测试");
        com.setContactPerson("测试");
        com.setTel("测试");
        int in=companyDao.addCompany(com);
        System.out.println(in+"=============");
    }

    @Test
    public void updateCompany() {
        CompanyEntity com=new CompanyEntity();
        com.setId(41);
        com.setTel("测试呀");
        com.setContactPerson("测试");
        int in=companyDao.updateCompany(com);
        System.out.println(in);
    }
    @Test
    public void deleteCompany() {
        companyDao.deleteCompany(41);
    }

}
