package test;

import com.sw.entity.Account;
import com.sw.service.AccountService;
import com.sw.config.SpringConfiguration;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 测试配置
 * 1、应用程序的入口
 *      main方法
 * 2、junit单元测试中，没有main方法也能执行
 *      junit集成了一个main方法
 *      该方法就会判断当前测试类中哪些方法有 @Test注解
 *      junit就会让有Test注解的方法执行
 * 3、junit不会管我们有没有采用spring框架
 *      在执行测试方法时，junit不知道是否使用了spring框架
 *      所以也就不会为我们读取配置文件/配置类创建spring核心容器
 * 综上：当测试方法执行的时候，没有Ioc容器，就算写了Autowired注解，也没有办法实现注入
 *
 *
 * Spirng整合junit的配置
 *      1、导入spring整合junit的jar包（坐标）
 *      2、使用junit提供的一个注解把原有的main方法替换掉，替换成Spring提供的
 *          @RunWith
 *      3、告诉spring的运行器，spring和ioc创建是基于xml还是基于注解，并且说明位置
 *          @ContextConfiguration
 *              locations：指定xml文件的位置，加上classpath关键字，表示在类路径下
 *              classes：指定注解类所在的位置
 * 当我们使用spring5.x版本的时候，要求junit的版本必须是4.12及以上
 */

@RunWith(SpringJUnit4ClassRunner.class)
// 因为SpringJUnit4ClassRunner类是spring提供的，所以它会帮我们创建Ioc容器
@ContextConfiguration(classes=SpringConfiguration.class)
// 或者是  @ContextConfiguration(locations=beans.xml)  但本案例没配置beans.xml

public class AccountServiceTest {

    private ApplicationContext ac;
    @Autowired
    private AccountService as = null;

//    @Before
//    public void init() {
//        // 获取容器
////        ac = new ClassPathXmlApplicationContext("beans.xml");
//        ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        // 得到业务层对象
//        as = ac.getBean("accountService",AccountService.class);
//    }

    @After
    public void destroy() {

    }

    @Test
    public void testFindAll() {
        List<Account> accounts = as.findAllAccount();
        for(Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("王五");
        account.setMoney(1234f);
        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        Account account = as.findAccountById(2);
        account.setMoney(20000f);
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        as.deleteAccount(2);
    }
}
