package test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sw.dao.PersonDao;
import com.sw.entity.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class EntityWrapperTest {

    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("springconfig.xml");

    private PersonDao personDao =
            ioc.getBean("personDao", PersonDao.class);

    @Test
    public void testSelect() {
        // 分页查询person表中，年龄为18~50之间性别为的所有用户
        Page<Person> persons = personDao.selectPage(new Page<Person>(1,2),
                new QueryWrapper<Person>()
                .between("age",18,50)
                .eq("sex","男")
                );
        System.out.println(persons.getRecords());
    }
}
