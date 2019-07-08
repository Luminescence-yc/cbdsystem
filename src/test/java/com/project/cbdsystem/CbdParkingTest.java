package com.project.cbdsystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CbdParkingBean;
import com.project.dao.ICbdParkingDao;
import com.project.entity.CbdParkingEntity;
import com.project.entity.SellParkingEntity;
import com.project.service.ICbdParkingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:
 * @Description:
 * @date 2019年05月30日 22:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CbdParkingTest {
    @Autowired
    private ICbdParkingService parkingService;

    @Test
    public void findById() {
        try {
            CbdParkingBean parkingBean = parkingService.findById(2);
            System.out.println(parkingBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findCbdParkingByCondition() {
/*        Map<String, String> condition = new HashMap<String, String>();
        condition.put("areaNum", "F");
        condition.put("address", "11");
        condition.put("page", "2");
        condition.put("size", "4");
        try {
            Page<CbdParkingBean> page = parkingService.findCbdParkingByCondition(condition);
            System.out.println(page.getRecords());

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            Page<CbdParkingBean> pages=   parkingService.findCbdParkingByArea("13","222",1,10);
            System.out.println(pages.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addParking() {
        CbdParkingBean cbdParkingBean = new CbdParkingBean();
        cbdParkingBean.setAddress("红瓦寺");
        cbdParkingBean.setAreaNum("地下二层D区");
        cbdParkingBean.setParkingNum("12");
        cbdParkingBean.setCount(5);
        try {
            parkingService.addParking(cbdParkingBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
