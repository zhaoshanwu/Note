package test;

import com.sw.entity.Account;
import com.sw.service.AccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 测试配置
 */
public class AccountServiceTest {

    private ApplicationContext ac;
    private AccountService as;

    @Before
    public void init() {
        // 获取容器
        ac = new ClassPathXmlApplicationContext("beans.xml");
        // 得到业务层对象
        as = ac.getBean("accountService", AccountService.class);
    }

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
