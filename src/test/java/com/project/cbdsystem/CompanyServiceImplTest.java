package com.project.cbdsystem;

import com.project.service.ICompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author yangcheng
 * @ClassName:
 * @Description:
 * @date 2019年06月02日 13:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyServiceImplTest {
    @Autowired
    private ICompanyService iCompanyService;
    @Test
    public void findCompanyByCondition() {
    }

    @Test
    public void findCompanyById() {
    }

    @Test
    public void addCompany() {
    }

    @Test
    public void updateCompany() {
    }

    @Test
    public void deleteCompany() {
    }

    @Test
    public void findAddressCbdParking() {
    }

    @Test
    public void pageCompanyContract() {
    }
}