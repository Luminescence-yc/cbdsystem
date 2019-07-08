package com.project.test;

import com.project.mapper.CompanyEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 刘再桦
 * @version v1.0
 * @ClassName
 * @Description
 * @date 2019年05月31日 9:44
 */

public class CompanyTest {

    @Autowired
    private CompanyEntityMapper companyEntityMapper;

    public void findCompanyById(){

    }
    public void findCompanyByCondition(){

    }
    public void  addCompany(){

    }
    public void updateCompany(){

    }
    public void deleteCompany(){

    }
    public void findAddressCbdParking(){

    }
    public void pageCompanyContract(){

    }

    public static void main(String[] args) {
    CompanyTest companyTest = new CompanyTest();
        companyTest.findCompanyById();
    }
}
