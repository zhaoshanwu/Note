package com.sw.service.impl;

import com.sw.dao.AccountDao;
import com.sw.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 曾经xml的配置
 *  <bean id="accountService"
 *           class="com.sw.service.impl.AccountServiceImpl"
 *           scope="prototype"
 *           init-method="init"
 *           destroy-method="destroy"
 *     ></bean>
 *
 * 所以注解应分为
 *      用于构建对象的
 *          作用就和xml配置文件里编写一个bean标签实现的功能一样
 *          @Component
 *              作用：用于把当前类对象存入spring容器
 *              属性：
 *                  value：用于指定bean的id，不写的时候，默认位当前雷类名首字母小写
 *          @Controller：一般用于表现层
 *          @Service：一般用于业务层
 *          @Repositroy：一般用于持久层
 *          这三个的作用和属性和component一模一样
 *          是spring框架提供的明确的三层使用的注解，让三层对象更清晰
 *
 *      用于注入数据的
 *          和property标签的作用一样
 *          Autowired:
 *              作用：自动按照类型注入，只要容器中有唯一的bean对象类型和要注入的变量类型匹配，就可以注入成功
 *                   如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错
 *                   如果ioc容器中有多个类型匹配时：就会先把能够匹配的类型找出来，然后匹配id(变量名),id相同匹配成功，没有相同的报错
 *              位置：
 *                  可以在变量上，也可以在方法上
 *              细节：
 *                  在使用注解注入的时候，set方法就不是必须的了
 *          Qualifier：
 *              作用：在按照类型注入的基础上再按照名称注入，在给类成员注入时不能单独使用，在给方法参数注入时可以单独使用
 *              属性：
 *                  value：用于指定注入bean的id
 *          Resource:
 *              作用：直接按照bean的id注入，可独立使用
 *              属性：
 *                  name：用于指定bean的id
 *          以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型不能使用
 *          集合类型的注入只能通过xml实现
 *          value:
 *              作用：用于注入基本类型和String类型的数据
 *              属性：
 *                  value：用于指定数据的值，可以使用spring中SpEL
 *                          SpEL写法：${表达式}   和jsp的表达式类似，如何区分就看写的位置
 *
 *      用于改变作用范围的
 *          和scope标签的作用一样
 *          @Scope
 *              作用：指定bean的范围
 *              属性：
 *                  value：指定范围的值，常用：singleton和prototype
 *      与生命周期相关的
 *          和init-method、destroy-method的作用一样
 *          PreDestroy
 *          PostConstruct
 */

@Service("accountService")
//@Scope("prototype")
public class AccountServiceImpl implements AccountService {

//    @Autowired
//    @Qualifier("accountDao1")
    @Resource(name="accountDao1")
    private AccountDao accountDao;

    public AccountServiceImpl() {
        System.out.println("对象创建了");
    }

    public void saveAccount() {
        accountDao.saveAccount();
        System.out.println("service中的saveAccount方法执行了");
    }

    @PostConstruct
    public void init() {
        System.out.println("对象被初始化");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("对象被销毁");
    }
}
