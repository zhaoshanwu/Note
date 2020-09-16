package com.sw.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * 该类是一个配置类，作用和bean.xml一样
 * spring的新注解
 * Configuration
 *      作用：指定当前类是一个配置类
 *      细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，可以不写，但并不是绝对的,如果不是作为参数而是通过扫描包得到就必须要写
 * ComponentScan
 *      作用：通过注解指定spring在创建容器的时候要扫描的包
 *      属性：
 *          value：和basePackages的作用时一样的
 *      使用该注解相当于配置：
 *             <context:component-scan base-package="com.sw"></context:component-scan>
 * Bean
 *      作用：把当前方法的返回值作为bean对象存入spring的ioc容器中
 *      属性：
 *          name：用于指定bean的id，当不写时，默认为方法名
 *      细节：
 *          当使用注解配置方法时，如果方法有参数，spring框架会去容器里查找有没有可用的bean对象
 *          查找的方式和Autowired注解一样
 * Import
 *      作用：用于导入其他的配置类
 *      属性：
 *          value：用于指定其他配置类的字节码，
 *                  当使用Import注解后，有该注解的配置类就是父配置类，导入的都是子配置类
 * PropertySource
 *      作用：指定properties文件的位置
 *      属性：
 *          value：指定文件的名称和路径
 *              classpath表示在类路径下
 */

@Configuration
@ComponentScan(basePackages="com.sw")
@Import(jdbcConfig.class)
@PropertySource("classpath:jdbc.properties")
public class SpringConfiguration {
//    /**
//     * 用于创建一个QueryRunner对象
//     */
//    @Bean(name="runner")
//    @Scope("prototype")
//    public QueryRunner createQueryRunner(DataSource dataSource) {
//        return new QueryRunner(dataSource);
//    }
//
//    /**
//     * 创建数据源对象
//     */
//    @Bean(name="dataSource")
//    public DataSource createDataSource() {
//        try {
//            ComboPooledDataSource ds = new ComboPooledDataSource();
//            ds.setDriverClass("com.mysql.cj.jdbc.Driver");
//            ds.setJdbcUrl("jdbc:mysql://localhost:3306/mybatis?3useUnicode=true&amp;characterEncoding=utf8");
//            ds.setUser("root");
//            ds.setPassword("sw1111");
//            return ds;
//        }catch(Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
