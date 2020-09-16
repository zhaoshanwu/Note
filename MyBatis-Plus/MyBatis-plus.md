# MyBatis plus
## MyBatis plus简介
MyBatis-Plus是MyBatis的增强工具包，只做增强不做改变，能够简化开发，提高生产率
### 特性
<ul>
    <li>无侵入：MyBatis-Plus在MyBatis的基础上进行扩展，只做增强不改变</li>
    <li>损耗小：启动就会自动注入基本CURD，性能基本无损耗，直接面向对象操作</li>
    <li>强大的CRUD操作：内置通用Mapper、通用Service，仅仅通过少量配置即可实现单表大部分CRUD操作，更有着强大的条件构造器，满足各类使用需求</li>
    <li>支持Lambda形式调用：通过Lambda表达式，方便的编写各类查询条件</li>
    <li>支持主键自动生成，支持多大4中逐渐策略，能够自由配置</li>
    <li>支持ActiveRecord模式：支持ActiveRecord形式调用，实体类秩序继承Model类即可进行强大的CURD操作</li>
    <li>支持自定义全局通用操作：支持全局通用方法注入</li>
    <li>内置代码生成器：采用代码或者Maven插件可快速生成Mapper、Model、Service、Controller层代码，支持模板引擎</li>
    <li>内置分页插件：基于MyBatis物理分页，开发者无需关心具体操作，配置好插件后，写分页等同于普通List查询，分页插件支持多种数据库</li>
    <li>内置性能分析插件：可输出SQL语句以及其执行时间，建议开发测试时启用该功能</li>
    <li>内置全局拦截插件：提供全表delete、update操作只能分析阻断，也可自定义拦截规则，预防误操作</li>
</ul>

## 集成MyBatis-Plus
### 步骤
<ul>
    <li>1、创建maven工程，添加相关依赖----见工程MyBatisPlusElementary</li>
    <li>2、创建实体类，内含属性、getset方法、toString()----见实体类User</li>
    <li>3、创建MyBatis配置文件和Spring配置文件----见配置文件SqlMapConfig.xml和springconfig.xml</li>
    <li>4、测试数据库配置是否成功----见测试类Test</li>
    <li>5、集成MyBatis-Plus----见springconfig.xml</li>
</ul>

#### 集成MyBatis-Plus
MyBatis-Plus的集成较为简单，对于Spring，只需要将MyBatis自带的MyBatisSqlSessionFactoryBean修改为MyBatis-Plus自带的即可，标签内的内容都无需改动
## 第一个MyBatis-Plus工程
### 通用CRUD
见测试文件**CRUDTest**
#### 问题
已经有person表和UPerson实体类，如果实现CRUD操作
#### 实现方式
##### 基于MyBatis：  
需要编写PersonDao接口，编写CRUD方法  
提供PersonMapper.xml映射文件，编写每个方法对应的SQL语句  
##### 基于MyBatis-Plus：
需要编写PersonDao接口，继承BaseMapper接口，使用内部提供的方法  
不需要提供PersonMapper.xml映射文件
#### BaseMapper<T>接口
BaseMapper<T> : 泛型指定的就是当前Mapper接口所操作的实体类类型，例如：BaseMapper<User>  
查看BaseMapper源码就能发现，该接口已经定义好了很多个增删改查的方法，能够直接进行使用  
**案例中举例了几个常用的方法，测试类中也有相应测试函数**
#### 主键策略
在MyBatis-Plus中有四种主键策略：
<table>
    <tr>
        <th>值</th>
        <th>描述</th>
    </tr>
    <tr>
        <td>IdType.AUTO</td>
        <td>数据库ID自增</td>
    </tr>
    <tr>
        <td>IdType.INPUT</td>
        <td>用户输入ID</td>
    </tr>
    <tr>
        <td>IdType.ID_WORKER</td>
        <td>全局唯一ID，内容为空自动填充(默认配置)</td>
    </tr>
    <tr>
        <td>IdType.UUID</td>
        <td>全局唯一ID，内容为空自动填充</td>
    </tr>
</table>

@TableId：配置主键策略的注解
<table>
    <tr>
        <th>值</th>
        <th>描述</th>
    </tr>
    <tr>
        <td>value</td>
        <td>字段值(数据库的主键名)，驼峰命名，可省略</td>
    </tr>
    <tr>
        <td>type</td>
        <td>主键ID策略类型</td>
    </tr>
</table>

如果数据库的主键名和实体类的属性名一致可以不用添加value
#### 表名和类名不一致
一般情况下，MyBatis-Plus会在数据库中寻找与类名相似的表名，如果找不到则会报错  
而在一些特殊情况下，类名和表名总会不一样，那么也有相应的解决办法  
@TableName：指定该实体类对应的的表名
<table>
    <tr>
        <th>值</th>
        <th>描述</th>
    </tr>
    <tr>
        <td>value</td>
        <td>表名，默认空</td>
    </tr>
    <tr>
        <td>type</td>
        <td>xml字段映射</td>
    </tr>
</table>


#### 属性名松散绑定
可以在MyBatis-Plus的全局策略配置中进行配置，但是在2.3版本后默认就是true，不用再进行配置
#### 表属性名和实体类属性名不一致
当表中的列名与实体类的属性名不一致时，MyBatis-Plus也有着对应的解决办法  
@TableField：指定该属性名在数据库中对应的列名  
<table>
    <tr>
        <th>值</th>
        <th>描述</th>
    </tr>
    <tr>
        <td>value</td>
        <td>字段值，默认空</td>
    </tr>
    <tr>
        <td>type</td>
        <td>是否是数据库表字段，true：是  false：否</td>
    </tr>
</table>

#### 插入数据时获取主键值
基于MyBatis：需要在Mapper映射文件中通过```useGeneratedKeys```和```keyProperty```(指定要获取的属性)来获取主键值  
基于MyBatis-Plus：主键值默认返回实体类，只需要用get方法获取
### 条件构造器
**EntityWrapper**  
MyBatis-Plus通过EntityWrapper来让用户能够自由地构造查询条件，使用简单便捷。  
**见测试文件EntityWrapperTest**
## 乐观锁@Version
标记乐观锁，就是通过一个version字段来保证数据的安全性，当修改数据的时候，会以version作为条件，当条件成立的时候才会修改成功  
version=1  
线程1：update... set version = 2 where version = 1
线程2：update... set version = 2 where version = 1
如果线程2先执行，那么version = 2，线程1就没法执行，这样就能保证只有一个线程能够成功执行，不会重复修改数据  
> 数据库表添加version字段，默认值为1  
> 实体类添加version成员变量，并且添加@Version  

1、数据库添加version字段，默认1  
2、实体类添加version  
```
@Version
private Integer version;
```
3、构建配置类
```
public class MyBatisPlusConfig{
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor{
        return new OptimisticLockerInterceptor();
    }
}
```
## @EnumValue
通用的枚举类注解，将数据库的字段映射成实体类的枚举类型成员变量  
1、实体类添加值
```
private StatusEnum status;
```
2、application.yml
```
type-enums-package:
    com.southwind.mybatisplus.enums
```
3、实现接口
```
public enum AgeEnum implements IEnum<Integer>{
    ONE(1,"一岁"),
    TWO(2,"两岁"),
    THREE(3,"三岁");
    
    private Integer code;
    private String msg;
    
    AgeEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
    
    @Override
    public Integer getValue(){
        return this.code;
    }
}
```

## @TableLogic
映射逻辑删除  
1、数据表添加Deleted字段  
2、实体类添加注解
```
@TableLogic
private Integer deleted;
```
3、application.yml添加配置
```
global-config:
    db-config:
        logic-not-delete-value: 0
        logic-delete-value: 1
```

## 自定义SQL(多表关联查询)
新建一个实体类，内含两个表的属性
```
	private String sname;
	private String cname;
```
自定义SQL语句
```
public interface UserMapper extends BaseMapper<User>{

	@Select("select s.name c.name from student s,Clesses c where s.cid = c.id and s.id = #{id}")
	List<SC> productList(Integer id);
}
```