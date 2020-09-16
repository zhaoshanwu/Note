# MyBatis
## 简介
MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。
<br/>
简而言之，这是个和数据库交互的框架，而且细节不用我们管，只需要吩咐它具体干嘛就行
## 优缺点
### 优点
<ul>
    <li>简单易学：本身很小且简单，没有第三方依赖，最简单安装只要两个jar文件+配置几个sql映射文件，易于学习易于使用，通过文档和源代码，可以比较完全的掌握它的设计思路和实现</li>
    <li>灵活：mybatis不会对应用程序或者数据库的现有合计强加任何影响，sql写在xml中，便于统一管理和优化，通过sql基本可以实现我们不适用数据访问框架可以实现的所有功能，或许更多</li>
    <li>解除sql与程序代码的耦合：通过提供DAL层，将业务逻辑和数据访问逻辑发呢里，使系统的设计更清晰，更易维护，更易单元测试，sql和代码的分离，提高了可维护性</li>
    <li>提供映射标签，支持对象与数据库的orm字段关系映射</li>
    <li>提供对象关系映射标签，支持对象关系组建维护</li>
    <li>提供xml标签，支持编写动态SQL</li>
</ul>

### 缺点
<ul>
    <li>编写sql语句时工作量很大，尤其是字段多、关联表多时。</li>
    <li>sql语句依赖于数据库，导致数据库一直移植性差，不能更换数据库</li>
    <li>框架还是比较简陋，功能尚有缺失，虽然简化了数据绑定代码，但是整个底层数据库查询实际还是要自己写的，工作量比较大，而且不太容易适应快速数据库修改</li>
    <li>二级缓存机制不佳</li>
</ul>

## mybaits的安装
想要使用mybatis需要导入mybatis的jar包或依赖
### 添加依赖(推荐)
如果创建的是maven项目就可以导入mybatis的依赖来使用mybatis
```
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.5</version>
</dependency>
```
### 导入jar包
如果创建的是普通的Java项目可以通过导入jar包来使用mybaits
## 第一个简单的mybatis工程
本案例使用maven工具来创建mybatis工程
<br/>
**见案例MyBatisElementary**
<ol>
    <li>添加要使用到的依赖：pom.xml</li>
    <li>创建所需要的包：dao、entity、test</li>
    <li>创建mybatis配置文件：SqlMapConfig.xml</li>
    <li>创建mapper映射文件：UserDao.xml</li>
</ol>

### 1、添加要使用到的依赖
需要添加的依赖为：
```
<dependencies>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.4.5</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.21</version>
    </dependency>
</dependencies>
```
### 2、创建所需要的包
1、在entity包下创建实体类
<br/>
创建User实体类，包括属性、setget方法、toString方法
<br/>
2、在dao包下创建接口
<br/>
创建UserDao接口类，包括查询所有信息等方法
<br/>
3、在test包下创建测试类
<br/>
包括测试接口中所有方法的测试方法
### 3、创建配置文件
1、创建用来配置mybatis相关信息的配置文件：SqlMapConfig.xml(文件名可自定义)
<br/>
2、创建配置sql操作的配置文件：UserDao.xml
### 4、测试
**运行测试类MybatisTest中的测试方法**
## 使用原生接口
在刚才的案例中，执行一个查询所经过的步骤为：
```
graph TD
    A(MybatisTest的findAll方法) -->B(UserDao接口的findAll方法)
    B --> C(UserDao配置文件的findAll方法)
    C--> D(查询到的信息存储在实体类中)
```
也可以跳过第二步UserDao接口的findAll方法，直接使用UserDao配置文件的findAll方法，这就是使用**原生接口**
<br/>
区别就是在SqlMapConfig中配置的时候不是指向UserDao接口，而是直接指向UserDao映射文件
## 实体类
功能类似于运货的箱子，每个箱子都有不同的名字，里面也有不同的东西，会有通道可以知道里面有什么，也可以改变里面的东西。
<br/>
实体类包括属性、无参构造方法(不写也有默认项)、有参构造方法(可有可无)、方法(set、get方法，toString方法，一般方法)
### 作用
实体类可以在数据传输过程中对数据进行封装，能够存储、传输、管理、操作数据
### 在mybatis中的作用
一般对应数据库中的一个表(根据查询结果确定实体类的属性)
<br/>
如果实体类对应数据库中的一张表，那么一般满足以下几个条件：
<ul>
    <li>实体类名，尽量和数据库中的表名一一对应</li>
    <li>实体类中的属性对应数据库表中的字段，相关的命名最好也一一对应</li>
    <li>实体类内方法主要有，setter、getter方法，用于设置、获取数据</li>
    <li>实体类属性一般为private类型，方法为public类型</li>
    <li>实体类应该有无参、有参构造方法</li>
</ul>

实体类中的属性与数据库表中的属性要求一致，但还是会存在特殊原因导致属性名称不一致的情况，那么就需要采取一定的解决措施，该问题会在编写mapper配置文件的时候解决。
<br/>
因为实体类中的属性一般为私有，所以直接user.id是无法获取内部的属性的，这也就需要通过get、set方法来对属性进行操作
<br/>
**在mybatis中实体类一般写出属性、get、set方法、toString方法就能够满足使用需要了**
## Dao接口
开发常用的是代理实现自定义接口，该接口存放的是调用sql语句的方法
<br/>
该方法会在mapper配置文件中通过标签里的属性进行绑定，而mapper配置文件是通过在mybatis配置文件中进行绑定

## MyBatis配置文件
见MyBatisSQL案例
### 设置：settings
这是MyBaits中很重要的一个配置，可以通过设置来管理MyBatis的行为
<br/>
**详细设置参考官网**
```
// 参考样式
<settings>
    <setting name="" value=""/>
</settings>
```
### 类型别名
每次都需要输入Java类的全限制类名(包名+类名)
```
<select id="findAll" resultType="com.sw.entity.User">
    select * from user
</select>
```
这样写就比较繁琐，MyBatis也想到了这一点，所以有了类型别名
```
<typeAliases>
    <typeAlias alias="user" type="com.sw.entity.User"/>
</typeAliases>
```
设置好类型别名后就不用每次都输入全限制类名，只需要输入取的别名user即可(大小写不敏感)
```
<typeAliases>
    <typeAlias alias="user" type="user"/>
</typeAliases>
```
但是每个实体类都要写一遍就比较繁琐
```
<typeAliases>
    <package name="com.sw.entity"/>
</typeAliases>
```
规定好包名就可以一次性将一个包里面的Java类都取上别名，别名就是**类名的首字母小写**
<br/>
当然，也可以不按照规定取别名，那么可以给类添加注解另取别名：
```
@Alias("author")
public class Author {
    ...
}
```
### 环境配置
MyBatis可以配置多个环境，这样可以让MyBatis能够应用于多种数据库中，不过，就算配置再多的环境，用的时候也只能选择一个
```
<!-- 配置环境 -->
    <!-- default：默认使用的环境id-->
    <environments default="mysql">
        <!-- 配置mysql的环境 -->
        <!-- 这个id是该环境的id -->
        <environment id="mysql">
            <!-- 选择什么事务管理器 -->
            <transactionManager type="JDBC"/>
            <!-- 配置数据源 -->
            <!-- type：数据源的配置 -->
            <dataSource type="POOLED">
                <!-- 配置连接数据库的基本信息 -->
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
```
#### 事务管理器
MyBatis中有两种事务管理器：**JDBC**和**MANAGED**
##### JDBC
这个配置直接使用了 JDBC 的提交和回滚设施，它依赖从数据源获得的连接来管理事务作用域。
##### MANAGED
这个配置几乎没做什么。它从不提交或回滚一个连接，而是让容器来管理事务的整个生命周期（比如 JEE 应用服务器的上下文）。 默认情况下它会关闭连接。然而一些容器并不希望连接被关闭，因此需要将 closeConnection 属性设置为 false 来阻止默认的关闭行为。
#### 数据源
dataSource 元素使用标准的 JDBC 数据源接口来配置 JDBC 连接对象的资源。
<br/>
有三种可用的数据源：UNPOOLED、POOLED和JNDI
##### UNPOOLED
 这个数据源的实现会每次请求时打开和关闭连接。虽然有点慢，但对那些数据库连接可用性要求不高的简单应用程序来说，是一个很好的选择。 性能表现则依赖于使用的数据库，对某些数据库来说，使用连接池并不重要，这个配置就很适合这种情形。  
 **详情查看官网**  
 <br/>
 UNPOOLED数据源只需要配置以下五种属性：
 <ul>
    <li>driver: JDBC驱动的Java类全限定名</li>
    <li>url:数据库的JDBC URL地址</li>
    <li>username:数据库的用户名</li>
    <li>password:数据库的密码</li>
    <li>defaultTransactionIsolationLevel:默认的连接事务隔离级别</li>
 </ul>

##### POOLED
这种数据源的实现利用“池”的概念将JDBC连接对象组织起来，避免了创建新的连接实例时所必需的初始化和认证时间.这种处理方式很流行，能使并发 Web 应用快速响应请求。**详情查看官网**
##### JNDI
这个数据源实现是为了能在如EJB或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个JNDI上下文的数据源引用。**详情查看官网**
### 映射器
接下来就要定义SQL语句了，所以要告诉MyBatis应该去哪里寻找这些定义，可以使用相对于类路径的资源引用、完全限定资源定位符或类名和包名，这三种方法都可以
#### 使用相对于类路径的资源引用
```
<mappers>
    <mapper resource="dao/UserDao.xml"/>
    <mapper resource="dao/AccountDao.xml"/>
    <mapper resource="dao/RoleDao.xml"/>
    <mapper resource="dao/StudentDao.xml"/>
</mappers>
```
##### 指定包名
如果映射文件较多，一个个指定就会很繁琐，所以MyBatis支持指定包名，MyBatis会前往这个包引用包内的映射文件
```
<mappers>
    <package name="com.sw.dao"/>
</mappers>
```
#### 使用完全限定资源定位符
```
<mappers>
  <mapper url="file:///dao/UserDao.xml"/>
  <mapper url="file:///dao/AccountDao.xml"/>
  <mapper url="file:///dao/RoleDao.xml"/>
  <mapper url="file:///dao/StudentDao.xml"/>
</mappers>
```
#### 使用映射器接口实现类的完全限定类名
```
<mappers>
    <mapper resource="dao.UserDao"/>
    <mapper resource="dao.AccountDao"/>
    <mapper resource="dao.RoleDao"/>
    <mapper resource="dao.StudentDao"/>
</mappers>
```
## XML映射器
MyBatis真正强大的就是它的语句映射，MyBatis的语句映射能够省去大量的代码，让用户更专注于SQL代码   
SQL映射文件只有很少的几个顶级元素：
<ul>
    <li>cache ： 该命名空间的缓存配置</li>
    <li>cache-ref ：引用其他命名空间的缓存配置</li>
    <li>resultMap ：描述如何从数据库结果机中加载对象，是最复杂也是最强大的元素</li>
    <li>sql ：可被其他语句引用的可重用语句块</li>
    <li>select ：映射查询语句</li>
    <li>insert ：映射插入语句</li>
    <li>update ：映射更新语句</li>
    <li>delete ：映射删除语句</li>
</ul>

### 映射语句
<ul>
    <li>select:映射查询语句</li>
    <li>insert:映射插入语句</li>
    <li>update:映射更新语句</li>
    <li>delete:映射删除语句</li>
</ul>

分别有很多属性，**详情查看官方文档**
### parameterType
parameterType指定sql语句的输入类型，可省略
<br/>
1、用不着输入就不写，比如查询所有
```
<select id="findAll" resultType="com.sw.entity.User">
    select * from user
</select>
```
2、基本数据类型
#### 小知识
java的8个基本数据类型：
<ul>
    <li>byte(位)</li>
    <li>short(短整数)</li>
    <li>int(整数)</li>
    <li>long(长整数)</li>
    <li>float(单精度)</li>
    <li>double(双精度)</li>
    <li>char(字符)</li>
    <li>boolean(布尔值)</li>
</ul>

比如：通过id查询User
```
<select id="findById" parameterType="int" resultType="com.sw.entity.User">
    select * from user where id=#{id}
</select>
```
3、String类型，通过name查询User
```
<select id="findByName" parameterType="String" resultType="com.sw.entity.User">
        select * from user where user like=#{name}
</select>
```
4、包装类，通过id查询User(案例未有具体代码)
<br/>
对基本数据类型的优化
```
<select id="findById2" parameterType="java.long" resultType="com.southwind.entity.Account">
	select * from t_account where id=#{id};
</select>
```
5、传入多个参数，通过id、name查询user
通过顺序来确定参数，从1开始，不能改顺序
跟版本有关系，高版本不适应
```
<select id="findByIdName" resultType="com.sw.entity.User">
        select * from user where id=#{param1} and username=#{param2};
    </select>
```
6、Java Bean，修改user，需要传入一个user
```
<update id="updateUser" parameterType="com.sw.entity.User">
    update user
    set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday}
    where id=#{id}
</update>
```
### resultType
resultType : 指定sql语句的输出类型，可省略
<br/>
1、也是用不到就不写
```
<update id="updateUser" parameterType="com.sw.entity.User">
        update user
        set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday}
        where id=#{id}
    </update>
```
1、基本数据类型，统计user记录总数
```
<select id="findTotal" resultType="int">
        select count(id) from user
    </select>
```
2、包装类，统计user记录总数
```
<select id="count2" resultType="java.lang.Integer">
	select count(id) from user;
</select>
```
3、Stirng类型，通过id查询user的username
```
<select id="findNameById" resultType="java.lang.String">
	select username from user where id=#{id};
</select>
```
4、Java Bean,查询所有记录，返回user类型
```
<select id="findAll" resultType="com.sw.entity.User">
        select * from user
    </select>
```
### sql
可以用来定义可重用的 SQL 代码片段，以便在其它语句中使用。
```
<sql id="userColumns">
    ${alias}.id,${alias}.username,${alias}.password
</sql>
```
定义后就可以在其他sql语句中使用，可以减少重复代码
```
<select id="selectUsers" resultType="map">
  select
    <include refid="userColumns"><property name="alias" value="t1"/></include>
    <include refid="userColumns"><property name="alias" value="t2"/></include>
  from some_table t1
    cross join some_table t2
</select>
```
### 动态SQL
使用动态SQL可以简化代码的开发，减少开发者的工作量，程序可以自动根据业务参数来决定SQL的组成  
#### if标签
```
<select id="findByUser" parameterType="com.sw.entity.User" resultType="com.sw.entity.User">
    select * from user where
    <if test="id != 0">
        id=#{id}
    </if>
    <if test="username != null">
        and username=#{username}
    </if>
</select>
```
if标签可以自动根据表达式的结果来决定是否将对应的语句添加到SQL中，如果条件不成立则不添加，成立就添加。  
#### where标签
```
    <select id="findByUser2" parameterType="com.sw.entity.User" resultType="com.sw.entity.User">
        select * from user
        <where>
            <if test="id != 0">
                id=#{id}
            </if>
            <if test="username != null">
                and username=#{username}
            </if>
        </where>
    </select>
```
where标签可以自动判断是否要删除语句块中的and关键字，如果检测到where直接跟and拼接，则自动删除and，通常情况下都是if和where结合起来使用  
#### choose、when标签
```
<select id="findByUser3" parameterType="com.sw.entity.User" resultType="com.sw.entity.User">
    select * from user
    <where>
        <choose>
            <when test="id!=0">
                id=#{id}
            </when>
            <when test="username!=null">
                username=#{username}
            </when>
        </choose>
    </where>
</select>
```
#### trim标签
trim标签中的prefix和suffix属性会被用域生成实际的SQL语句，会和标签内部的语句进行拼接，如果语句前后出现了prefixOverrides或者suffixOverridex属性中指定的值，MyBatis框架会自动将其删除
```
<select id="findByUser4" parameterType="com.sw.entity.User" resultType="com.sw.entity.User">
        select * from user
        <trim prefix="where" prefixOverrides="and">
            <if test="id!=0">
                id=#{id}
            </if>
            <if test="username != null">
                and username=#{username}
            </if>
        </trim>
    </select>
```
#### set标签
set标签用于update操作，会自动根据参数选择生成SQL语句。  
```
    <update id="update" parameterType="com.sw.entity.User">
        update user
        <set>
            <if test="username!=null">
                username=#{username},
            </if>
            <if test="birthday!=null">
                birthday=#{birthday},
            </if>
            <if test="sex!=null">
                sex=#{sex}
            </if>
            <if test="address!=null">
                address=#{address}
            </if>
        </set>
        where id=#{id};
    </update>
```
#### foreach标签
foreach标签可以迭代生成一系列值，这个标签主要用于SQL的in语句
```
<select id="findByIds" parameterType="com.southwind.entity.Account" resultType="com.southwind.entity.Account">
	select * from t_account 
	<where>
		<foreach collection="ids" open="id in (" close=")" item="id" separator=",">
			#{id}
		</foreach>
	</where>
</select>
```
### 字符串替换
有时我们想这样查询：查询某个id的用户、查询某个name的用户、查询某个age的用户  
如果是按照刚才的方法我们需要写三个S映射语句，MyBatis有一个方法可以减少这方面的麻烦--字符串替换  
只需要写这一个映射语句便可以完成三种查询
```
<select id="find" resultType="user">
    select * from user where ${column} = #{value}
</select>
```
在接口类
```
List<User> find(@Param("column") String column, @Param("value") String value);
```
${column}会被column参数替换  
不仅可以替换属性名，也可以替换表名
### jdbc.properties
这个文件中存放着连接mybatis所需要的数据库数据
<br/>
使用该文件的好处是可以单独对数据库连接数据进行更改，而不用进入配置文件中进行更改
<br/>
使用方法为在mybatis配置文件中先声明，然后就可以使用${ }进行获取其中的属性
```
<properties resource="jdbc.properties"></properties>

<property name="driver" value="${driver}"/>
```
### 级联查询
利用resultMap就可以进行级联查询：一对一、一对多、多对多查询  
#### 一对一
一个account对应着一个user，可以在查询account的同时查询出该account对应的user  
1、修改Account实体类，添加属性User user
```
private Integer id;
private Integer uid;
private Double money;

public User user;
```
2、修改account映射文件，添加resultMap标签
```
<resultMap type="account" id="accountUserMap">
    <id column="aid" property="id"/>
    <id column="uid" property="uid"/>
    <result column="money" property="money"/>
    <collection property="user" ofType="user">
        <id column="id" property="id"/>
        <result column="username" property="username"/>
        <result column="birthday" property="birthday"/>
        <result column="sex" property="sex"/>
        <result column="address" property="address"/>
    </collection>
</resultMap>

<select id="findAll" resultMap="accountUserMap">
    select a.id as aid,a.money as money,a.uid as uid,u.id as id,u.username as username,u.birthday as birthday,u.sex as sex,u.address as address
    from account a,user u
    where u.id=a.uid;
</select>
```
3、在account映射接口添加方法
```
List<Account> findAll();
```
这样就可以在输出account数据的同时输出其对应的user信息
#### 一对多
一个account对应着一个user，一个user对应着多个account  
那么也同样可以在输出user的同时输出其对应的所有account  
1、修改User实体类，与前面不同的是，因为对应多个account，所以数据类型为List
```
private Integer id;
private String username;
private Date birthday;
private String sex;
private String address;

private List<Account> accounts;
```
2、修改user映射文件，添加resultMap标签
```
<resultMap id="userAccountMap" type="user">
    <id property="id" column="id"/>
    <result property="username" column="username"/>
    <result property="birthday" column="birthday"/>
    <result property="sex" column="sex"/>
    <result property="address" column="address"/>
    <collection property="accounts" ofType="account">
        <id property="id" column="aid"/>
        <result property="uid" column="uid"/>
        <result property="money" column="money"/>
    </collection>
</resultMap>

<select id="findAll" resultMap="userAccountMap">
    select u.id,u.username,u.birthday,u.sex,u.address,a.id as aid,a.uid,a.money
    from user u left join account a on u.id=a.uid
</select>
```
3、在映射接口添加方法
```
List<User> findAll();
```
#### 多对多
一个role对应多个student，一个student也对应多个role，也就是多个role对应多个student  
1、添加实体类Student_Role
```
private Integer sid;
private Integer rid;

private List<Student> students;
private List<Role> roles;
```
2、添加Student_RoleDao.xml映射文件
```
<resultMap id="student_roleMap" type="student_role">
    <id property="sid" column="sid"/>
    <id property="rid" column="rid"/>
    <collection property="students" ofType="student">
        <id property="id" column="id"/>
        <result property="name" column="name"/>
        <result property="birthday" column="birthday"/>
        <result property="sex" column="sex"/>
        <result property="address" column="address"/>
    </collection>
    <collection property="roles" ofType="role">
        <id property="id" column="rid"/>
        <result property="rolename" column="rolename"/>
        <result property="roledesc" column="roledesc"/>
    </collection>
</resultMap>

<select id="findAll" resultMap="student_roleMap">
    select sr.sid,sr.rid,s.name,s.birthday,s.sex,s.address,r.rolename,r.roledesc
    from student_role sr
    left outer join student s on s.id=sr.sid
    left outer join role r on r.id=sr.rid
</select>
```
3、添加映射接口
```
List<Student_Role> findAll();
```
### 缓存
MyBatis内置了一个强大的事务性查询缓存机制，它能够非常方便地配置和定制。  
默认情况下，只启用了本地地绘画缓存，它仅仅对一个会话中的数据进行缓存，如果要启用全局的二级缓存，需要在映射文件中添加：
```
<cache/>
```
这句语句的效果为：
<ul>
    <li>映射语句文件中所有的select语句的结果将会被缓存</li>
    <li>映射语句文件中的所有insert、update和delete语句都会刷新缓存</li>
    <li>缓存会使用最近最少使用算法来清除不需要的缓存</li>
    <li>缓存不会定时刷新</li>
    <li>缓存会保存列表或对象的1024个引用</li>
    <li>缓存会被视为读/写缓存，这意味着获取到的对象不是共享的，可以安全地被调用者修改，不会干扰其他调用者或县城所做的潜在修改</li>
</ul>

缓存只会作用于这个cache标签所在的映射文件的语句