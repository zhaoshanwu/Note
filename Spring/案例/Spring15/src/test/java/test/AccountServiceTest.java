package test;

import com.sw.config.SpringConfiguration;
import com.sw.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {

    @Autowired
    private AccountService as;

    @Test
    public void TestTransfer() {
        as.transfer("张三","李四",100f);
//        as.transfer(1,2,100f);
    }
}
