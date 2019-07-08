package com.project.configuration;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yangcheng
 * @ClassName:
 * @Description:
 * @date 2019年06月17日 10:20
 */
@Component
public class QuartzScheduler {
    public QuartzScheduler() throws Exception {
        //创建JobDetail实例
        JobDetail jobDetail= JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "j166")
                .usingJobData("name", "lovo")
                .build();
        //创建Trigger触发器
        Trigger trigger= TriggerBuilder.newTrigger()
                .withIdentity("trigger1","j166")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                //.withSchedule(CalendarIntervalScheduleBuilder)
                .build();
        //创建Scheduler实例
        Scheduler scheduler= StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
        Thread.currentThread().sleep(30000);
        scheduler.shutdown();
    }
}
