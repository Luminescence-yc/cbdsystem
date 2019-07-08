package com.project.cbdsystem;

import com.project.dao.IRentHistoryDao;
import com.project.entity.RentHistoryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentHistoryDaoImplTest222 {
    @Autowired
    private IRentHistoryDao rentHistoryDao;
    @Test
    public void showRentOutHistoryByRentOutPersonalId() {
    }

    @Test
    public void showLeaseHistoryByLeasePersonalId() {
    }

    @Test
    public void addRentHistory() {
    }

    @Test
    public void findByRentPersonalId() {
        try {
            RentHistoryEntity rentHistoryEntity= rentHistoryDao.findByRentPersonalId(1);
            System.out.println(rentHistoryEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByHirePersonalId() {

    }
}