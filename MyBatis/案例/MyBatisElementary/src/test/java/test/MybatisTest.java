package test;

import com.sw.dao.UserDao;
import com.sw.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    public MybatisTest() {
    }

    @Before
    public void init() throws IOException {
        // 读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); // 工厂模式：解耦
        SqlSessionFactory factory = builder.build(in);
        // 使用工厂生产SqlSession对象
        sqlSession = factory.openSession();
        // 使用SqlSession创建Daojiekoude代理对象
        userDao = sqlSession.getMapper(UserDao.class); // 代理模式：不修改源码的基础上对已有的方法加强
    }

    @After
    public void destroy()  throws IOException {
        // 提交事务
        sqlSession.commit();
        // 释放资源
        in.close();
        sqlSession.close();
    }
    @Test
    public void testFindAll(){
        // 使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("王五");
        user.setAddress("山东");
        user.setSex("男");
        user.setBirthday(new Date());

        // 使用代理对象执行方法
        userDao.saveUser(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(4);
        user.setUsername("王六");
        user.setAddress("河南");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存前" + user);
        // 使用代理对象执行方法
        userDao.updateUser(user);
        System.out.println("保存后" + user);
    }

    @Test
    public void testDelete() {
        // 使用代理对象执行方法
        userDao.deleteUser(3);
    }

    @Test
    public void testFindOne() {
        // 使用代理对象执行方法
        userDao.findById(1);
    }

    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%王%");
        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal() {
        int count = userDao.findTotal();
        System.out.println(count);
    }

//    @Test
//    public void testFindByVo() {
//        QueryVo vo = new QueryVo();
//        User user = new User();
//        user.setUsername("%王%");
//        vo.setUser(user);
//        List<User> users = userDao.findUserByVo(vo);
//        for(User u : users) {
//            System.out.println(u);
//        }
//    }
}
