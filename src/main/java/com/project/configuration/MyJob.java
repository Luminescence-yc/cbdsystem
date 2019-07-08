package com.project.configuration;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author yangcheng
 * @ClassName:
 * @Description:
 * @date 2019年06月17日 10:00
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //填写执行的任务
        JobDetail jobDetail=jobExecutionContext.getJobDetail();

        //获取任务名字
        String name = jobDetail.getJobDataMap().getString("name");

        //打印任务信息
        System.out.println("my job name is "+name+"at"+new Date());
    }
}
