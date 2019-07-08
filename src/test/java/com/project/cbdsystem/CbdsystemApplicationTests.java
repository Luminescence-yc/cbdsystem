package com.project.cbdsystem;


import com.project.activemq.Productor;
import com.project.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CbdsystemApplicationTests {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private Productor productor;

    @Test
    public void contextLoads() {
        redisUtil.set("name", "张三");
        Object o = redisUtil.get("name");
    }

    @Test
    public void activemqTest() {
        productor.sendMessage("msg", "hello word");
    }
}
