package com.sw.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 用于记录日志的工具类，里面提供了公共的代码
 */
public class Logger {
    /**
     * 前置通知
     */
    public void beforePrintLog() {
        System.out.println("前置通知Logger类中的beforePrintLog方法开始记录日志了。。。");
    }

    /**
     * 后置通知
     */
    public void aferReturningPrintLog() {
        System.out.println("后置通知Logger类中的aferReturningPrintLog方法开始记录日志了。。。");
    }

    /**
     * 异常通知
     */
    public void afterThrowingPrintLog() {
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志了。。。");
    }
    /**
     * 最终通知
     */
    public void afterPrintLog() {
        System.out.println("最终通知Logger类中的afterPrintLog方法开始记录日志了。。。");
    }

    /**
     * 环绕通知
     * 问题：
     *      配置了环绕通知后，切入点方法没有执行，而通知方法执行了
     * 分析：
     *      通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而这个代码没有
     * 解决：
     *      spring框架为我们提供了一个接口：ProceedingJoinPoint，该接口有一个方法proceed()，该方法相当于明确调用切入点方法
     *      该接口可以作为环绕统治的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用
     * spring中的环绕通知：
     *      它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式
     */
    public Object aroundPringLog(ProceedingJoinPoint pip) {
        Object rtValue = null;
        try{
            Object[] args = pip.getArgs(); //得到方法执行所需的参数
            System.out.println("环绕通知Logger类中的aroundPringLog方法开始记录日志了----前置");
            rtValue = pip.proceed(args); // 明确调用业务层方法
            System.out.println("环绕通知Logger类中的aroundPringLog方法开始记录日志了----后置");
            return rtValue;
        }catch (Throwable t) {
            System.out.println("环绕通知Logger类中的aroundPringLog方法开始记录日志了----异常");
            throw new RuntimeException(t);
        }finally {
            System.out.println("环绕通知Logger类中的aroundPringLog方法开始记录日志了----最终");
        }
    }
}
