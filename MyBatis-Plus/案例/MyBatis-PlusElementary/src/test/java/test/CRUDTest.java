package test;

import com.sw.dao.PersonDao;
import com.sw.entity.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CRUDTest {
    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("springconfig.xml");

    private PersonDao personDao =
            ioc.getBean("personDao", PersonDao.class);

    /**
     * 测试数据源和数据库连接信息是否配置成功
     */
    @org.junit.Test
    public void testDataSource() throws SQLException {
        DataSource ds = ioc.getBean("dataSource",DataSource.class);
        System.out.println(ds);

        Connection conn = ds.getConnection();
        System.out.println(conn);
    }

    /**
     * insert()
     * 插入操作
     */
    @org.junit.Test
    public void testInsert() {
        Person person = new Person();
        person.setId(1);
        person.setName("张三");
        person.setSex("男");
        person.setAge(18);
        Integer result = personDao.insert(person);
        System.out.println(result);

        // 获取当前数据在数据库中的主键值
        Integer key = person.getId();
        System.out.println(key);
    }

    /**
     * updateById()
     * 更新操作
     */
    @org.junit.Test
    public void testUpdate() {
        Person person = new Person();
        person.setId(1);
        person.setName("李四");
        person.setSex("男");
        person.setAge(20);

        Integer result = personDao.updateById(person);
        System.out.println(result);
    }

    /**
     * selectById()
     * 根据id查询数据
     */
    @org.junit.Test
    public void testSelect() {
        // 通过id查询
        Person person = personDao.selectById(1);
        System.out.println(person);
        System.out.println("-------------------");
        // 通过多个id查询
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        List<Person> persons = personDao.selectBatchIds(ids);
        System.out.println(persons);
        System.out.println("--------------------");
        // 通过Map封装条件查询
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name","李四");
        map.put("id",1);
        List<Person> persons2 = personDao.selectByMap(map);
        System.out.println(persons2);
    }

    /**
     * deleteById()
     * 根据id删除
     */
    @org.junit.Test
    public void testDelete() {
        // 根据id删除
        Integer result = personDao.deleteById(2);
        System.out.println(result);
        // 根据条件删除
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name","张三");
        map.put("id",2);
        personDao.deleteByMap(map);
        // 根据多id删除
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        Integer result2 = personDao.deleteBatchIds(ids);
        System.out.println(result2);
    }
}
