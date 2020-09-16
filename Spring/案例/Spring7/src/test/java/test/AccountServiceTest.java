package test;

import com.sw.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:beans.xml")
public class AccountServiceTest {

    @Autowired
//    @Qualifier("proxyAccountService")
    private AccountService as;

    @Test
    public void testTransfer() {
        as.transfer("张三","李四",1000f);
    }
}
