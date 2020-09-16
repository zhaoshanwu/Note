package com.sw.ui;

import com.sw.dao.AccountDao;
import com.sw.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {
    /**
     * 获取spring的Ioc核心容器，通过id获取对象
     *
     * ApplicationContext的三个常用实现类：
     *      ClassPathXmlApplicationContext:可以加载类路径下的配置文件，要求配置文件必须在类路径下，不在就没法加载
     *      FileSystemXmlApplicationContext:可以加载磁盘任意路径下的配置文件(必须有访问权限)
     *      AnnotationConfigApplicationContext:用于读取注解创建容器
     * 核心容器的两个接口：
     *   ApplicationContext:   只创建一次，单例对象适用    开发中更多的采用这种
     *      它在创建核心容器时，创建对象采取的策略是采用立即加载的方式，也就是说，一读取完配置文件就马上创建出来配置文件中配置的对象
     *   BeanFactory：          使用一次创建一次，多例对象适用
     *      它在创建核心容器时，创建对象采取的策略是采用延迟加载的方式，也就是说，什么时候要根据id获取对象了，什么时候创建这个对象
     *   注：可以通过在bean构造函数添加输出函数，然后用debug来运行的方式证明
     * @param args
     */
    public static void main(String[] args) {
//        AccountService as = new AccountServiceImpl();
        // 获取核心容器对象
//        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        // 根据id获取bean对象
        AccountService as = (AccountService) ac.getBean("accountService");
        AccountDao ad = ac.getBean("accountDao", AccountDao.class);

        System.out.println(as);
        System.out.println(ad);
        as.saveAccount();
        // 没来得及调用销毁方法就销毁了
        ac.close();
    }
}
