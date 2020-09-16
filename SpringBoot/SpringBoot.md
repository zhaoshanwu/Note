# 简介
## Spring Boot
是一个用来简化Spring应用开发的一个框架，也是整个Spring技术栈的一个大整合。
<br/>
简单来说，Spring Boot将Spring、Spring MVC、Tomcat整合在了一块。简化操作和配置。
### 优势
<ul>
    <li>SpringBoot Starter</li>
    <li>编码变得简单</li>
    <li>自动配置</li>
    <li>部署变得简单</li>
    <li>微服务开发框架</li>
</ul>

#### SpringBoot Starter
SpringBoot将常用的以来分组进行了整合，将其合并到一个依赖中，这样就可以一次性将一组依赖添加到项目中
#### 编码变得简单
SpringBoot采用JavaConfig的方式对Spring进行配置，并且提供了大量的注解，极大的提高了工作效率
#### 自动配置
SpringBoot的自动配置特性利用了Spring对条件化配置的支持，合理地推测应用所需的bean并自动化配置他们
#### 部署变得简单
SpringBoot内置了三种Servlet容器：Tomcat、Jetty、undertow，只需要有一个Java地运行环境就可以执行SpringBoot的项目，而且借助插件可以直接将SpringBoot项目打成jar包
#### 微服务开发框架
SpringBoot就是一个很好的微服务开发框架，可以快速的搭建起一个系统，同时也可以使用SpringCloud来搭建一个分布式的架构

**场景启动器具体内容查看官方文档**
## 微服务
2014年 martin fowler说明了微服务的定义
<br/>
https://martinfowler.com/architecture/
<br/>
---
<br/>
In short, the microservice architectural style is an approach to developing a single application as a suite of small services, each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API. These services are built around business capabilities and independently deployable by fully automated deployment machinery. There is a bare minimum of centralized management of these services, which may be written in different programming languages and use different data storage technologies.

-- James Lewis and Martin Fowler (2014)
<br/>
简而言之，微服务体系结构风格是一种将单个应用程序开发为一组小型服务的方法，每个服务在自己的流程中运行，并与轻量级机制(通常是HTTP资源API)通信。这些服务是围绕业务功能构建的，并且可以通过完全自动化的部署机制独立部署。这些服务可以用不同的编程语言编写，使用不同的数据存储技术，对这些服务进行最基本的集中管理。
<br/>
——詹姆斯·刘易斯和马丁·福勒(2014)
<br/>
---
<br/>
微服务：是一种架构风格
<br/>
一个应用应该是一组小型服务：可以通过HTTP的方式进行互通，
<br/>

**详情查看微服务文档**
<br/>

# Spring Boot 工程
## 第一个Spring Boot 工程
功能：浏览器发送hello请求，服务器接受请求并处理，响应Hello World字符串
<br/>
### 步骤
1、创建maven工程，在pom.xml中添加依赖
<br/>
2、创建一个主程序，用来启动Spring Boot应用
<br/>
3、编写相关的Controller或Service等
<br/>
4、运行主程序
<br/>
5、简化部署
<br/>
添加spring-boot-maven-plugin插件，就可以直接通过maven的命令```mvc package```将应用部署为可执行的jar包
```
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```
## 探究Hello World
### pom文件
导入了一个父项目
```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.3.RELEASE</version>
</parent>
```
**IDEA鼠标中键点击可详细查看依赖**
<br/>
进入后发现内部还有个parent
```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.3.3.RELEASE</version>
</parent>
```
再次进入后发现内部有很多依赖版本的设置，也就是说SpringBoot的每个版本都对应着这些依赖的相应版本
<br/>
所以可以说在这个父项目```spring-boot-starter-parent```里面管理着SpringBoot应用的所有依赖版本，并且再导入依赖时是不用写版本的(如果没有在里面包含的依赖还是需要写版本号的)
### 导入的依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
spring-boot-starter:spring-boot场景启动器：帮我们导入web模块正常运行所依赖的组件，里面包含了一组starter
```
<dependencies>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter</artifactId>
  <version>2.3.3.RELEASE</version>
  <scope>compile</scope>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-json</artifactId>
  <version>2.3.3.RELEASE</version>
  <scope>compile</scope>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-tomcat</artifactId>
  <version>2.3.3.RELEASE</version>
  <scope>compile</scope>
</dependency>
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-web</artifactId>
  <version>5.2.8.RELEASE</version>
  <scope>compile</scope>
</dependency>
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-webmvc</artifactId>
  <version>5.2.8.RELEASE</version>
  <scope>compile</scope>
</dependency>
</dependencies>
```
这些starter每个starter都对应着一组依赖，这样在```spring-boot-starter-web```中就包含了很多所需要的依赖
<br/>
#### starter
启动器(starter)是一组方便的依赖项描述符，可以包含在应用程序中。通过启动器可以获得所需的所有Spring和相关技术的一站式服务，而无需遍历示例代码和复制粘贴依赖描述符的加载。例如，如果要使用SpringMVC相关的依赖和配置，那么在项目中加入```spring-boot-starter-web```就可以导入相关的依赖和配置，不用另外添加
<br/>
SpringBoot中包含了很多starter可以使用
<br/>
**详情查看SpringBoot官方文档**
<br/>
Spring Boot将所有的功能场景都抽取出来，做成一个个的starters(启动器)，只需要在项目中引入这些starter，相关场景的所有依赖都会导入进来，要用什么功能就导入什么场景的启动器
### 主程序类
类名可自定义
```
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 启动Spring Boot应用(固定写法)
        SpringApplication.run(Application.class,args);
    }
}
```
@SpringBootApplication:标注在某个类上说明这个类是Spring Boot的主配置类，SpringBoot应该运行这个类的main方法来启动Spring Boot应用
#### @SpringBootApplication源码分析
点开@SpringBootApplication-->
```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
```
在这里主要是@SpringBootConfiguration和@EnableAutoConfiguration
##### @SpringBootConfiguration
Controller能够被Spring扫描到，成为bean纳入到Spring容器中，肯定是经过配置的，而这个配置不是用xml进行的，而是由配置类进行配置，@SpringBootConfiguration注解的作用就是将添加此注解的类标注为配置类。
##### @EnableAutoConfiguration
打开@EnableAutoConfiguration-->
```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
```
###### @AutoConfigurationPackage
打开@AutoConfigurationPackage-->
```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage {
```
@Import(AutoConfigurationPackages.Registrar.class)
<br/>
@Import:
<ul>
    <li>1、可以将另一个配置类中的bean导入到被注解@Import的配置类中</li>
    <li>2、可以将bean的定义进行导入</li>
    <li></li>
</ul>

打开Registrar-->
```
static class Registrar implements ImportBeanDefinitionRegistrar, DeterminableImports {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
		register(registry, new PackageImports(metadata).getPackageNames().toArray(new String[0]));
	}

	@Override
	public Set<Object> determineImports(AnnotationMetadata metadata) {
		return Collections.singleton(new PackageImports(metadata));
	}

}
```
方法registerBeanDefinitions()有两个参数：metadata、registry
<br/>
metadata：用来获取标注了@SpringBootApplication的类，可以获取到启动类的信息，当然也包括启动类所在的包
<br/>
registry:负责来对bean做注册的
<br/>
接下来要进行的操作就知道了，metadata获取到启动类所在的包，registry对这个包下面的所有带注解的类进行bean注册，然后将这些bean都添加到Spring容器中
## Spring Boot热部署
在开发过程中，每次修改代码都需要将项目重启，进行重新部署，对于大型应用来说，重启时间长，会花费大量的时间来重启部署
### 依赖于IDE
热部署依赖于IDE开发工具，所以需要对编译器进行配置
#### 在IDEA中的配置
1、通过settings -> Compiler,将Build project automatically打勾，开启自动编译
<br/>
2、ctrl+shift+alt+/ -> Registry，打勾compiler.automake.allow.when.app.running，允许程序运行过程中编译
#### 取消某些文件在热部署时的重启
有时我们想让某些类中的文件进行热部署时不进行重启，比如说js、配置文件等等。就可以在配置文件中添加配置
```
spring.devtools.restart.exclude=static/**,public/**
```
这个配置就表示，在包static和包public下的所有文件在进行热部署时不进行重启
#### 什么时候进行热部署
热部署不是每次更新代码都进行，那样太浪费资源，而是每次打开浏览器切换窗口时进行热部署
## Spring Boot的配置
虽然SpringBoot进行了自动配置，但仍有一些情况下需要的配置与自动配置不同，这时我们还是要将这些不同于自动配置的配置信息进行额外配置
### 配置文件
Spring Boot使用一个全局的配置文件,有两种文件类型(文件名默认，但可改)：
<ul>
    <li>application.properties</li>
    <li>application.yml</li>
</ul>
yml：是YAML语言的文件，以数据为中心，比json、xml等更适合作为配置文件

#### 配置文件的位置
四种位置
<ul>
    <li>1、file:./config/</li>
    <li>2、file:./</li>
    <li>3、classpath:/config/</li>
    <li>4、classpath</li>
</ul>
file指的是项目的根路径，如果有父工程，那么就要放在父工程的根路径下
<br/>
classpath指的是resources，专门放配置文件的文件夹
<br/>
如果多个位置有者相同属性不同值的配置，那么就有优先级：按位置从上往下，优先级逐渐减小
<br/>
如果多个位置有者不同属性的配置，那么它们的配置会互补，每个配置都会生效
### YAML(YML)
YML是YAML语言的文件，以数据为中心，比properties、xml等更适合做配置文件，与之相比数据更直接，语句更简洁

见SpringBoot2
#### 基本语法
<ul>
    <li>使用缩进表示层级关系(缩进严格)</li>
    <li>缩进不能使用Tab，只能使用空格</li>
    <li>缩进的空格数目不重要，只要相同层级的元素左侧对齐即可</li>
    <li>大小写敏感</li>
    <li>属性后的冒号与值之间必须有一个空格</li>
</ul>

k:(空格)v  : 表示一对键值对，空格必须有
<br/>
```
server:
    port: 8080
    path: ....
```
空格数目不重要，左对齐就是一个层级的
#### 值的写法
##### 字面量
普通的值(数字、字符串、布尔)
<br/>
k: v   :字面直接写
<br/>
字符串默认不用加单引号或双引号
<br/>
如果加了那么就有特殊的含义
<ul>
    <li>""(双引号)，不会转义字符串里面的特殊字符，特殊字符会作为本身要表示的意思，比如/n就表示换行，输出的时候就会换行</li>
    <li>''(单引号)：会转义特殊字符，特殊字符没有什么含义，只是普通的字符串数据，比如/n就是/n，输出也是/n，不会被理解成换行</li>
</ul>

##### 对象、Map(属性和值)(键值对)
k: v   :在下一行直接写对象的属性和值的关系
```
friends:
    lastname: zhangsan
    age: 20
```
也可以写在一行里
```
firends: {lastName: zhangsan,age: 20)
```
##### 数组(List、Set)
用- 值的方式表示数组中的一个元素
```
pets:
    - cat
    - dog
    - pig
```
也可以写在一行中
```
pets: [cat,dog,pig]
```
#### YML属性绑定
可以将配置文件中的属性绑定到实体类中
<br/>
##### set绑定
见SpringBoot3(官方文档的案例)
<br/>
实体类中必须要有set方法，为了简便，可以使用lombok而不用额外增加get、set代码
##### 构造器绑定
见SpringBoot4实体类AcmeProperties
<br/>
可以不使用set绑定方法，而是用构造函数的绑定方法，这样在类中可以不添加set方法
##### 松散绑定
见SpringBoot4实体类OwnerProperties
<br/>
配置文件中的属性名和实体类中的属性名并不一定要严格一致，配置文件中的属性名可以改变其书写模式，不光是属性名，前缀也可以松散绑定
<table>
    <tr>
        <th>属性文件中配置</th>
        <th>说明</th>
    </tr>
    <tr>
        <td>acme.my-project.person.first-name</td>
        <td>羊肉串模式，推荐使用</td>
    </tr>
    <tr>
        <td>acme.myProject.person.firstName</td>
        <td>标准驼峰模式</td>
    </tr>
    <tr>
        <td>acme.my_project.person.first_name</td>
        <td>下划线模式</td>
    </tr>
    <tr>
        <td>ACME_MYPROJECT_PERSON_FIRSTNAME</td>
        <td>大写下划线，如果使用系统环境时推荐使用</td>
    </tr>
</table>

##### 属性校验
见SpringBoot4实体类OwnerProperties
<br/>
开启校验需要添加校验注解：@Validated
<br/>
然后在实体类要校验的属性上添加校验注解，如果配置文件绑定的值不符合校验，则报错
##### @Value和@ConfigurationProperties
见SpringBoot4实体类ValueProperties
<br/>
@Value是用于单个属性的绑定，而@ConfigurationProperties用于整个类的绑定
<br/>
@Value不推荐使用松散匹配，但仍然能够用一些松散匹配，仅支持驼峰模式和羊肉串模式的切换，配置文件中使用驼峰模式来匹配实体类中的羊肉串模式
<br/>
@Value还支持通过算式直接对属性进行赋值绑定，而@ConfigurationProperties不支持
<br/>
对于复杂类型，@Value也不支持，而@ConfigurationProperties是支持的
##### profile
见SpringBoot5中application.yml未注释内容
<br/>
在yml配置文件中，可以配置多个环境用于不同的运行环境
<br/>
profile是对不同环境提供不同配置功能的支持，可以通过激活、指定参数的方法来快速切换环境
<br/>
怎么选择使用哪个环境：
<br/>
1、在yml中直接配置
```
spring:
  profiles:
    active: dev
```
2、在运行jar文件的时候配置
```
——spring.profiles.active=dev
```
###### 多配置文件
见SpringBoot5中application.yml注释内容与application-div.yml和application.pro.yml
<br/>
有时候一个配置文件中放所有的环境代码量就会很大，所以可以将每个环境放在一个文件里。
### SpringBoot整合Mybatis
见SpringBootdataSource
<br/>
与之前的工程相比另外做的就是
<br/>
1、要添加数据库信息，在application.yml中添加数据库连接信息，其他的就交给SpringBoot就进行自动配置
<br/>
2、添加注释让SpringBoot能够扫描到Mapper文件，可以在Mapper类中添加@Mapper注解，也可以在启动类中添加@MapperScan("com.sw.dao")用于扫描在类com.sw.dao下的Mapper文件
#### 数据表属性与实体类属性名松散绑定
SpringBoot中有解决数据表的属性名和实体类的属性名松散绑定的方法
<br/>
```
mybatis:
  configuration:
    map-underscore-to-camel-case: true
```
默认是false，所以要单独进行配置打开，这样就可以开启松散绑定，就可以让下划线命名的数据表属性名对应起来驼峰命名的实体类属性
#### Myabtis的属性配置
虽然SpringBoot中有Mybatis的自动配置，但是一些属性的配置还是需要手动进行配置，比如mapper文件的路径，Mapper.xml中的类别名等等
```
mybatis:
  mapper-locations: classpath:mapper/*.xml   // 配置mapper文件的路径
  type-aliases-package: com.sw.entity    // 配置类别名
```
#### Mybatis配置文件
SpringBoot仍支持使用Mybatis配置文件，但是不推荐，因为可以在application中进行配置，而且二者不能共存
<br/>
可以通过下列方式将Mybatis配置文件导入到SpringBoot中
```
mybatis:
    config-location: classpath:sqlMapConfig.xml
```





