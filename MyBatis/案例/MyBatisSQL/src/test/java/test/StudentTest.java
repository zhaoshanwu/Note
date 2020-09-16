package test;

import com.sw.dao.StudentDao;
import com.sw.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StudentTest {

    private InputStream in;
    private SqlSession sqlSession;
    private StudentDao studentDao;

    public StudentTest() {
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
        studentDao = sqlSession.getMapper(StudentDao.class); // 代理模式：不修改源码的基础上对已有的方法加强
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
        List<Student> students = studentDao.findAll();
        for(Student student : students) {
            System.out.println(student);
            System.out.println(student.getRoles());
        }
    }
}
