package com.project.cbdsystem;

import com.project.dao.IPersonalDao;
import com.project.entity.PersonalEntity;
import com.project.service.IPersonalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 朱 骞
 * @version v1.0
 * @ClassName: PersonalTest
 * @Description: 个人用户测试类
 * @date 2019年05月31日 10:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonalTest {
    @Autowired
    private IPersonalDao iPersonalDao;
    @Autowired
    private IPersonalService iPersonalService;

    @Test
    public void findOnePersonalInformation() {
        try {
            PersonalEntity personalEntity = iPersonalDao.findOnePersonalInformation(1);
            System.out.println(personalEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updatePersonalInfoById() {

        try {
            PersonalEntity personalEntity = iPersonalDao.findOnePersonalInformation(1);
            personalEntity.setEmail("1");
            personalEntity.setTel("222");
            personalEntity.setJobDescription("333");
            personalEntity.setAddress("555");
//            System.out.println(iPersonalDao.updatePersonalInfoById(personalEntity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getRentParkingByAll() {
//        IPage<PersonalBean> page = iPersonalService.getRentParkingByAll(1, 10);
//        System.out.println(page);
    }

    @Test
    public void getSellParkingByAll() {
        //IPage<PersonalBean> page = iPersonalService.getSellParkingByAll(1, 10);
        //System.out.println(page);
    }

    @Test
    public void updateComplainNumById() throws Exception {
        int i = iPersonalDao.updateComplainNumById(1);
        System.out.println(i);
    }
}
