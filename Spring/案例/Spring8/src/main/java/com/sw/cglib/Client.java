package com.sw.cglib;

import com.sw.proxy.Producer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 消费者
 */
public class Client {
    public static void main(String[] args) {
        Producer producer = new Producer();

        /**
         * 动态代理
         *  特点：字节码随用随创建，随用随加载
         *  作用：不修改源码的基础上对方法增强
         *  分类
         *      基于接口的动态代理
         *      基于子类的动态代理
         *  基于子类的动态代理
         *      涉及的类：Enhancer
         *      提供者：第三方cglib库
         *  如何创建代理对象
         *      使用Ehancer类中的create方法
         *  创建代理对象的要求
         *      被代理类不能是最终类（最终类没办法创建子类）
         *  create方法的参数
         *      Class:字节码
         *          它是用来指定被代理对象的字节码
         *      Callback：用于提供增强的代码
         *          它是然我们写如何代理，一般都是写一个该接口的实现类，通常情况下是匿名内部类，但不是必须的
         *          该接口的实现类是谁用谁写
         *          一般写的都是该接口的子接口实现类：MethodInterceptor
         */
        Producer cglibproducer = (Producer)Enhancer.create(producer.getClass(),new MethodInterceptor(){
            /**
             * 执行被代理对象的任何方法都会经过该方法
             * @param o
             * @param method
             * @param args
             *  上面三个和基于接口的动态代理中invoke方法的参数一样
             * @param methodProxy 当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws  Throwable {
                // 提供增强的代码
                Object returnValue = null;
                // 1、获取方法执行的参数
                Float money = (Float) args[0];
                // 2、判断当前方法是不是销售
                if("saleProduct".equals(method.getName())) {
                    returnValue = method.invoke(producer,money*0.8f);
                }
                return returnValue;
            }
        });
        cglibproducer.saleProduct(1200f);
    }
}
