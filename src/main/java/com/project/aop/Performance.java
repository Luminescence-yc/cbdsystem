package com.project.aop;

import com.project.entity.ResponseTimeEntity;
import com.project.service.IResponseTimeService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 罗亚辉
 * @ClassName:性能统计切面
 * @Description:用于统计每个Controller层的方法执行耗时，并保存到数据库
 * @date 2019年05月31日 17:20
 */
@Repository
@Aspect
public class Performance {
    @Autowired
    private IResponseTimeService responseTimeService;
    @Around("execution(* com.project.controller.*.*(..))")
    public Object PerformanceAroundAdvice(ProceedingJoinPoint joinPoint){
        long start=System.currentTimeMillis();
        Object obj=null;
        try {
            obj=joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end=System.currentTimeMillis();
        ResponseTimeEntity responseTimeEntity=new ResponseTimeEntity();
        responseTimeEntity.setResponseTime((int)(end-start));
        //获取执行的方法的包名
        responseTimeEntity.setFunctionPackage(joinPoint.getTarget().getClass().getName());
        //获取执行的方法名
        responseTimeEntity.setFunction(joinPoint.getSignature().getName());
        responseTimeService.addResponseTime(responseTimeEntity);
        return obj;
    }
}
