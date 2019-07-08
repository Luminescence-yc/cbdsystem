package com.project.configuration;

import com.project.test.WebSocketController;
import com.project.entity.OnlineUserEntity;
import com.project.service.IOnlineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yangcheng
 * @ClassName:性能统计
 * @Description:性能统计配置类
 * @date 2019年06月04日 15:27
 */
@Component
public class ScheduledService {
    @Autowired
    private IOnlineUserService iOnlineUserService;
    @Scheduled(cron = "0 0/1 * * * ?")
    public void countPeopleNum(){
        //System.out.println("111");
        OnlineUserEntity onlineUserEntity=new OnlineUserEntity();
        onlineUserEntity.setOnlineUserNum(WebSocketController.onlineCount);
        iOnlineUserService.addOnlineUser(onlineUserEntity);
    }
}
