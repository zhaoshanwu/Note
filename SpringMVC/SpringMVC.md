# Spring MVC
是一个基于MVC的web框架，属于Spring的一个模块  
## MVC
M-模型：一个或多个JavaBean对象，用于存储数据(实体模型由JavaBean类创建)和处理业务逻辑(业务模型，由一般的Java类创建)  
V-视图：一个或多个JSP页面，像控制器提交数据和为模型提供数据显示，JSP页面主要是使用HTML标记和JavaBean标记来显示数据  
C-控制器：一个或多个Servlet对象，根据视图提交的请求进行控制，即将请求转发给处理业务逻辑的JavaBean，并将处理结果存放到实体类型JavaBean中，输出给视图显示  
## Spring MVC接口
共有四个Spring MVC接口：  
DispatcherServlet：SpringMVC所有的请求都经过DispatcherServelt来统一分发，在DispatcherServlet将请求分发给Controller之前都需要借助Spring MVC提供的Handler Mapping定位到具体的Controller  
HandlerMapping：负责完成客户请求到Controller映射  
Controller：处理用户请求，类似Java Servlet，一旦Controller处理完用户请求，将返回ModelAndView对象给DispatcherServlet前端控制器，ModelAndView中包含了模型和视图  
ViewResolver：视图解析器，在web应用中负责查找View对象，从而将响应结果渲染给客户  
# 第一个Spring MVC程序
1、创建maven程序(webapp类型的)  
2、修改pom.xml添加依赖(为了部署增加jdk版本)
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.sw</groupId>
  <artifactId>springmvcstudy</artifactId>
  <packaging>war</packaging>
  <version>0.0.1-SNAPSHOT</version>
  <name>springmvcstudy Maven Webapp</name>
  <url>http://maven.apache.org</url>
  <dependencies>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.2.7.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>5.2.7.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.2.7.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>servlet-api</artifactId>
      <version>2.5</version>
      <scope>provided</scope>
    </dependency>

    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.0</version>
      <scope>provided</scope>
    </dependency>
    
  </dependencies>
  <build>
    <finalName>springmvcstudy</finalName>
   
  </build>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
</properties>
</project>
```
3、修改web.xml文件配置servlet
```
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com//dtd/web-app_2_3.dtd" >
 <web-app>
 	<display-name>Archetype Created Web Application</display-name>
 	
 	<!-- 处理中文乱码的过滤器 -->
 	<filter>
 		<filter-name>encodingFilter</filter-name>
 		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
 		<init-param>
 			<param-name>encoding</param-name>
 			<param-value>UTF-8</param-value>
 		</init-param>
 	</filter>
 	<filter-mapping>
 		<filter-name>encodingFilter</filter-name>
 		<url-pattern>*</url-pattern>
 	</filter-mapping>
 	
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
        <init-param>
        	<param-name>contextConfigLocation</param-name>
        	<param-value>classpath:springmvc.xml</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```
servlet-name标签里的为servlet的名字  
4、在resources中创建springmvc.xml  
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd ">

       <!-- 自动扫描 -->
       <context:component-scan base-package="com.sw"></context:component-scan>
       
       <!-- 配置视图解析器 -->
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
       	<property name="prefix" value="/"></property>
       	<property name="suffix" value=".jsp"></property>
       </bean>
</beans>
```
5、创建类HelloHandler  
springmvc-servlet.xml说明了/index的访问交给IndexController类处理  
IndexController实现接口Controller，提供方法handleRequest处理这个请求  
Spring MVC通过ModelAndView对象把模型和视图结合在一起  
```
package com.sw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器
 *
 */

@Controller
public class HelloHandler {

	@RequestMapping("/index")
	public String index() {
		System.out.println("index....");
		return "index";
	}
}

```
6、创建index.jsp
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 
<h1>Hello sprign MVC</h1>
```
# Spring MVC的核心组件
DispatcherServlet:前置控制器，是真个流程控制的核心，控制其他组件的执行，进行统一调度，降低组件之间的耦合性，相当于总指挥。  
Handler：处理提，完成具体的业务逻辑，相当于Servlet。  
HandlerMapping：DispatcherServlet接收到请求后，通过HandlerMapping将不同的请求映射到不通的Handler。  
HandlerInterceptor：处理器拦截器，是一个接口，如果需要完成一些拦截处理，可以实现该接口。  
HandlerExecutionChain：处理器执行链，包括两部分呢：Handler和HandlerInterceptor(系统有者一个默认的HandlerInterceptor，如果需要额外设置拦截，可以添加拦截器)  
HandlerAdapter:处理器适配器，Handler执行业务方法之前，需要进行一系列的操作，包括表单数据的验证、数据类型的转换、将表单数据封装到JavaBean等，这些操作都是由HandlerApater组成，开发者只需要将注意力集中在业务逻辑的处理上，DispatcherServlet通过handlerAdapter执行不同的Handler  
ModelAndView：装载了模型数据和视图信息，作为Handler的处理结果，返回给DispathcerServlet  
ViewResolver：视图解析器，DispatcherServlet通过它将逻辑视图解析为物理视图,最终将渲染的结果响应给客户端。  

## 工作流程
1、客户端请求被DisptacherServlet接收  
2、根据HandlerMapping 映射到Handler  
3、生成Handler和HandlerInterceptor  
4、Handler和HandlerInterceptor以HandlerExecutionChain的形式一并返回给DisptacherServlet  
5、Dispatcher Servet通过HandlerAdapter调用Handler的方法完成业务逻辑处理  
6、DispatcherServlet将获取的ModelAndView对象传给ViewResolver视图解析器，将逻辑视图转换为物理视图View  
7、ViewResovler返回一个View给DispatcherServlet  
8、DispatcherServlet根据View进行视图渲染(将模型数据Model填充到视图View中)  
9、DispatcherServlet将渲染后的结果响应给客户端  

但是真正需要开发的只有Handler、View。
## 注解
在上面的创建第一个项目中，传统的控制器不仅需要在配置文件中进行部署映射，而且只能编写一个处理方法，但是基于注解的控制器可以没有这些问题  
### Controller注解
@Controller表示IndexController的实例是一个控制器  
会将该类交给loC容器来管理(需要解和springmvc.xml的自动扫描配置使用)，同时是使其成为一个控制器，可以接受客户端请求  
在方法handleRequest前加上@RequestMapping("/index")表示路径/index会映射到这个方法上  
而且不让IndexController实现Controller接口
```
package controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class IndexController {
    @RequestMapping("/index")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "Hello Spring MVC");
        return mav;
    }
}
```
因为使用了注解方式，所以不需要再配置映射，只需要在springmvc.xml添加
```
<context:component-scan base-package="com.sw" />
```
用来表示从包com.sw下扫描有@Controller注解的类  
### RequestMapping注解
Spring MVC通过@RequestMapping注解将URL请求与业务方法进行映射，在Handler的类定义处或者方法定义处都可以添加@Requestmapping，
#### 属性
value:指定URL请求的实际地址，是@Requestmapping的默认值，可省略  
method:指定请求的method类型，GET、POST、PUT、DELET。表示只能由指定的类型访问  
或者可以直接使用注解进行规范
```
@Controller
public class HelloHandler {

	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() {
		System.out.println("index....");
		return "index";
	}
	
	@GetMapping("/index")
	public String index2() {
		System.out.println("index....");
		return "index";
	}
}
```
上面的代码就表示只能由get方式请求，否则报405错误  
params：指定请求中必须包含某些参数，否则
```
@Controller
public class HelloHandler {

	@RequestMapping(value="/index",method=RequestMethod.GET,params= {"name","id"})
	public String index(String name,int id) {
		System.out.println("index....");
		return "index";
	}
}
```
上面代码表示要访问此方法必须携带name和id两个参数，否则报400错误  
参数绑定，在形参列表中通过添加@RequestParam注解完成HTTP请求参数与业务方法形参的映射
```
@Controller
public class HelloHandler {

	@RequestMapping(value="/index",method=RequestMethod.GET,params= {"name","id"})
	public String index(@RequestParam("name") String str,@RequestParam("id") int age) {
		System.out.println("index....");
		return "index";
	}
}
```
#### 方法级别注解
```
@Controller
public class IndexController {
    @RequestMapping("/index")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "Hello Spring MVC");
        return mav;
    }
}
```
可以通过下面的URL进行访问handleRequest方法
```
http://localhost:8080/项目名/index
```
#### 类级别注解
```
@Controller
@RequestMapping("/page")
public class IndexController {
    @RequestMapping("/index")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "Hello Spring MVC");
        return mav;
    }
}
```
这种情况下，控制器类中的所有方法都将映射为类级别的请求，访问时要加上类级别注解，类似这里的/page
可以通过下面的URL进行访问handleRequest方法
```
http://localhost:8080/项目名/page/index
```
#### REST
Spring MVC也支持RESTful风格的URL  
传统风格：http://localhost:8080/hello/index?name=zhangsan&id=10  
REST：http://localhost:8080/hello/index/zhangsan/10  
如果要通过URL来传递参数，就必须要加@PathVariable注解
```
@RequestMapping("rest/{name}/{id}")
public String rest(@PathVariable("name") String name,@PathVariable("id") int id) {
	return "index";
}
```
通过@PathVariable注解完成请求参数与形参的映射
## 映射Cookie
Spring MVC通过映射可以直接从业务方法中获取Cookie的值
```
@RequestMapping("/cookie")
	public String cookie(@CookieValue(value="JSESIONID") Stirng sessionId) {
		
		return "index";
	}
```
## 使用JavaBean绑定参数
Spring MVC会根据请求参数名和JavaBean属性名进行自动匹配，自动为对象填充属性值，同时支持级联属性
user.java
```
public class User {

	private long id;
	private String name;
	private Address address;
}
```
Address.java
```
public class Address {

	private String value;
}
```
register.jsp
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/hello/save" method="post">
		用户id：<input type="text" name="id"><br/>
		用户名：<input type="text" name="name">
		用户地址：<input type="text" name="address.value">
		<input type="submit" value="注册"/>
	</form>
</body>
</html>
```
处理方法
```
@RequestMapping(value="/save",method= RequestMethod.POST)
public String save(User user) {
	return "index";
}
```
## 请求处理方法
### 参数类型
如果需要在请求处理方法中使用Servlet API类型，那么可以将这些类型作为请求处理方法的参数类型
```
public class IndexController {
    @RequestMapping("/login")
    public String login(HttpSession session,HttpServletRequest request) {
        session.setAttribute("skey", "session范围的值");
        session.setAttribute("rkey", "request范围的值");
        return "login";
    }
}
```
除了Servlet API参数类型外，还要输入输出流、表单实体类、注解类型等等  
其中有个重要的是Model类型，该类型是一个包含Map的Spring框架类型，在每次调用请求处理方法时Spring MVC都创建Model对象  
```
public class IndexController {
    @RequestMapping("/register")
    public String register(Model model) {
        /*在视图中可以使用EL表达式${success}取出model中的值*/
        model.addAttribute("success", "注册成功");
        return "register";
    }
}
```
### 返回类型
最常见的就是代表逻辑视图名称的Spring类型，比如前面的return "register";  
还要ModelAndView、Model、View等类型也可以作为返回类型  
## 获取参数
### 通过实体Bean接收请求参数
```
@RequestMapping("/login")
    public String login(UserForm user, HttpSession session, Model model) {
        if ("zhangsan".equals(user.getUname())
                && "123456".equals(user.getUpass())) {
            session.setAttribute("u", user);
            logger.info("成功");
            return "main"; // 登录成功，跳转到 main.jsp
        } else {
            logger.info("失败");
            model.addAttribute("messageError", "用户名或密码错误");
            return "login";
        }
    }
```
将实体类对象user作为参数给方法
### 通过处理方法的形参接收请求参数
```
public String register(String uname,String upass,Model model) {
    if ("zhangsan".equals(uname)
            && "123456".equals(upass)) {
        logger.info("成功");
        return "login"; // 注册成功，跳转到 login.jsp
    } else {
        logger.info("失败");
        // 在register.jsp页面上可以使用EL表达式取出model的uname值
        model.addAttribute("uname", uname);
        return "register"; // 返回 register.jsp
    }
}
```
### 通过HttpServletRequest接收请求参数
```
public String register(HttpServletRequest request,Model model) {
    String uname = request.getParameter("uname");
    String upass = request.getParameter("upass");
    if ("zhangsan".equals(uname)
            && "123456".equals(upass)) {
        logger.info("成功");
        return "login"; // 注册成功，跳转到 login.jsp
    } else {
        logger.info("失败");
        // 在register.jsp页面上可以使用EL表达式取出model的uname值
        model.addAttribute("uname", uname);
        return "register"; // 返回 register.jsp
    }
}
```
### 通过@PathVariable接收URL里的请求参数
```
 public String register(@PathVariable String uname,@PathVariable String upass,Model model) {
        if ("zhangsan".equals(uname)
                && "123456".equals(upass)) {
            logger.info("成功");
            return "login"; // 注册成功，跳转到 login.jsp
        } else {
            // 在register.jsp页面上可以使用EL表达式取出model的uname值
            model.addAttribute("uname", uname);
            return "register"; // 返回 register.jsp
        }
    }
```
访问路径为
```
http://localhost：8080/springmvc/user/register/zhangsan/123456
```
其中uname为zhangsan，upass为123456
### 通过@RequestParam接收请求参数
```
public String register(@RequestParam String uname,
    @RequestParam String upass, Model model) {
    if ("zhangsan".equals(uname) && "123456".equals(upass)) {
        logger.info("成功");
        return "login"; // 注册成功，跳转到 login.jsp
    } else {
        // 在register.jsp页面上可以使用EL表达式取出model的uname值
        model.addAttribute("uname", uname);
        return "register"; // 返回 register.jsp
    }
}
```
该方法与通过处理方法的形参接受请求参数的区别是：当请求参数与接收参数名不一致的时候，该方法会报404错误，而“通过处理方法的形参接受请求参数”不会
### 通过@ModelAttribute接收请求参数
@ModelAttribute 注解放在处理方法的形参上时，用于将多个请求参数封装到一个实体对象，从而简化数据绑定流程，而且自动暴露为模型数据
```
public String register(@ModelAttribute("user") UserForm user) {
    if ("zhangsan".equals(uname) && "123456".equals(upass)) {
        logger.info("成功");
        return "login"; // 注册成功，跳转到 login.jsp
    } else {
        logger.info("失败");
        // 使用@ModelAttribute("user")与model.addAttribute("user",user)的功能相同
        //register.jsp页面上可以使用EL表达式${user.uname}取出ModelAttribute的uname值
        return "register"; // 返回 register.jsp
    }
}
```
## @ModelAttribute的使用
可实现两种功能：  
### 1、绑定请求参数到实体对象  
```
@RequestMapping("/register")
public String register(@ModelAttribute("user") UserForm user) {
    if ("zhangsan".equals(uname) && "123456".equals(upass)) {
        logger.info("成功");
        return "login";
    } else {
        logger.info("失败");
        return "register";
}
```
'@ModelAttribute("user") UserForm user'语句的功能有两个：  
1、将请求参数的输入封装到user对象  
2、创建User实例  
以“user”为键值存储在Model对象中就像'model.addAttribute("user",user)'一样，  如果没有指定键值，就是'@ModelAttribute UserForm user'，那么创建实例的时候就会以userForm为键值存储在Model对象中，就是'model.addAttribute("userFrom",user)'
### 2、注解一个非请求处理方法
被@ModelAttribute注解的方法会在每次调用该控制器类的请求处理方法前被调用，可以被用来控制登录权限
```
public class BaseController {
    @ModelAttribute
    public void isLogin(HttpSession session) throws Exception {
        if (session.getAttribute("user") == null) {
            throw new Exception("没有权限");
        }
    }
}
```
```
@RequestMapping("/admin")
public class ModelAttributeController {
    @RequestMapping("/add")
    public String add() {
        return "addSuccess";
    }
    @RequestMapping("/update")
    public String update() {
        return "updateSuccess";
    }
    @RequestMapping("/delete")
    public String delete() {
        return "deleteSuccess";
    }
}
```
每次执行下面的处理方法的时候，都会提前执行一下上面的isLogin方法
## 转发与重定向
Spring MVC默认以转发的形式响应jsp
```
@Controller
@RequestMapping("/index")
public class IndexController {
    //转发
    @RequestMapping("/login")
    public String login() {
        
        return "forward:/index.jsp";
    }
    //重定向
    @RequestMapping("/isLogin")
    public String isLogin() {
        return "redirect:/index.jsp";
    }
}
```
## 数据绑定
数据绑定：在后端的业务方法中直接获取客户端HTTP请求中的参数，将请求参数映射到业务方法的形参中，Spring MVC中数据绑定的工作是由HandlerAdapter来完成的  
### 基本数据类型
```
	@RequestMapping("/baseType")
	//如果不加@ResponseBody，返回的则是id.jsp，加上后就表示本来是什么就输出什么，源代码只有一个id值不再加sjp
	@ResponseBody
	public String baseType(int id) {
		return id+"";
	}
```
@ResponseBody表示SpringMVC会直接将业务方法的返回值响应给客户端，如果不加这个注释，就会传递给DispatcherServlet，然后由DisptacherServlet调用ViewResolver对返回值进行解析，加上.jsp再运行
### 包装类
```
@RequestMapping("/packageType")
	@ResponseBody
	public String packageType(Integer id) {
		return id+"";
	}
```
包装类可以接受null，当HTTP请求没有参数的时候，用包装类定义形参的数据类型，程序不会抛出异常
```
	@RequestMapping("/packageType")
	@ResponseBody
	public String packageType(@RequestParam(value="num",required=false,defaultValue="0")Integer id) {
		return id+"";
	}
```
@RequestParam  
value="num":将HTTP请求中名为num的参数赋给id    
required：设置num是否为必填项，true为必填，false为非必填，默认true   
defaultValue="0"：如果不输入num的值，默认为0  
## 数组
```
@RequestMapping("/array")
@ResponseBody
public String array(String[] name) {
	String str = Arrays.toString(name);
	return str;
}
```
## @RestController和@Controller
@RestController表示该控制器会直接将业务方法的返回值相应给客户端，不进行视图解析  
@Controller表示该控制器的每一个二业务方法的返回值都交给视图解析器进行解析  
所以说，如果只需要将数据相应给客户端，而不进行视图解析，那么只需要再对应的业务方法定义出添加@ResponseBody  
如果整个控制器都是不进行视图解析，那么在类前面可以将@Controller换成@RestController  
## List
Spring MVC不支持List类型的直接转换，需要对List集合进行包装  
集合封装类
```
public class UserList {

	private List<User> users;

	public Object getUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}
```
addList.jsp
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/data/list" method="post">
	用户1：<input type="text" name="users[0].id"/>
	用户2：<input type="text" name="users[1].id">
</form>
</body>
</html>
```
业务方法
```
@RequestMapping("/list")
	public String list(UserList userlist,HttpServletResponse response) {
		StringBuffer str = new StringBuffer();
		for(User user:userlist.getUsers()) {
			str.append(user);
		}
		return str.toString();
	}
```
处理@ResponseBody中文乱码,在springmvc.xml配置消息转换器
```
<mvc:annotation-driven>
   	<!-- 消息转换器 -->
   	<mvc:message-converters register-defaults="true">
   		<bean class="org.springframework.http.converter.StringHttpMessageConverter">
   			<property name="supporteMediaTypes" value="text/html;charset=UTF-8"></property>
   		</bean>
   	</mvc:message-converters>
</mvc:annotation-driven>
```
## 模型数据解析
jsp四大作用域对应的内置对象：pageContext、request、session、application  
模型数据解析就是指把模型数据绑定到域对象中，然后从域对象传到jsp，再从jsp中解析出来
模型数据的绑定是由ViewResolver来完成，实际开发中先添加模型数据，再交给ViewResolver来绑定  
Spring MVC提供了几种方式添加模型数据：  
<ul>
    <li>Map</li>
    <li>Model</li>
    <li>ModelAndView</li>
    <li>@SessionAttribute</li>
    <li>@ModelAttribute</li>
</ul>

> 将模式数据绑定到request对象

jsp
```
${requestScope.user}
```
### Map
```
@RequestMapping("/map")
	public String map(Map<String,User> map) {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		map.put("user",user);
		return "view";
	}
```
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

${requestScope.user }
</body>
</html>
```
### Model
```
@RequestMapping("/model")
	public String map(Model model) {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		model.addAttribute("user",user);
		return "view";
	}
```
### ModelAndView
```
	@RequestMapping("/modelAndView")
	public ModelAndView modelAndView() {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user",user);
		modelAndView.setViewName("view");
		return modelAndView;
	}
	
	@RequestMapping("/modelAndView2")
	public ModelAndView modelAndView2() {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user",user);
		View view = new InternalResourceView("/view.jsp");
		modelAndView.setView(view);
		return modelAndView;
	}
	
	@RequestMapping("/modelAndView3")
	public ModelAndView modelAndView3() {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		ModelAndView modelAndView = new ModelAndView("view");
		modelAndView.addObject("user",user);
		return modelAndView;
	}
	
	@RequestMapping("/modelAndView4")
	public ModelAndView modelAndView4() {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		View view = new InternalResourceView("/view.jsp");
		ModelAndView modelAndView = new ModelAndView(view);
		modelAndView.addObject("user",user);
		return modelAndView;
	}
	
	@RequestMapping("/modelAndView5")
	public ModelAndView modelAndView5() {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		Map<String,User> map = new HashMap<>();
		map.put("user",user);
		ModelAndView modelAndView = new ModelAndView("view",map);
		return modelAndView;
	}
	
	@RequestMapping("/modelAndView6")
	public ModelAndView modelAndView6() {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		Map<String,User> map = new HashMap<>();
		map.put("user",user);
		View view = new InternalResourceView("/view.jsp");
		ModelAndView modelAndView = new ModelAndView(view,map);
		return modelAndView;
	}
	
	@RequestMapping("/modelAndView7")
	public ModelAndView modelAndView7() {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		ModelAndView modelAndView = new ModelAndView("view","user",user);
		return modelAndView;
	}
	
	@RequestMapping("/modelAndView8")
	public ModelAndView modelAndView8() {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		View view = new InternalResourceView("/view.jsp");
		ModelAndView modelAndView = new ModelAndView(view,"user",user);
		return modelAndView;
	}
```
### HttpServletRequest
```
@RequestMapping("/request")
	public String request(HttpServletRequest request) {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		request.setAttribute("user",user);
		return "view";
	}
```
### @ModelAttribute
1、定义一个方法，用来专门返回要填充到模型数据中的对象  
```
@ModelAttribute
	public User getUser() {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		return user;
	}
```
或者
```
@ModelAttribute
	public void getUser(Map<String,User> map) {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		map.put("user", user);
	}
```
或者
```
@ModelAttribute
	public void getUser(Model model) {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		model.addAttribute("user", user);
	}
```
有@ModelAttribute注解的方法，在同一控制器的其他方法执行前都会执行该方法  
2、业务方法中无需再处理模型数据，只需返回视图即可
```
@RequestMapping("/modelAttribute")
	public String modelAttribute() {
		return "view";
	}
```
> 将模型数据绑定到session对象

jsp
```
${sessionScope.user}
```
1、使用原生的Servlet API
```

	@RequestMapping("/session")
	public String session(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = new User();
		user.setId(1);
		user.setName("张三");
		session.setAttribute("user", user);
		return "view";
	}
	
	@RequestMapping("/session2")
	public String session2(HttpSession session) {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		session.setAttribute("user", user);
		return "view";
	}
```
2、@SessionAttribute
可以放到类名前面
```
@SessionAttributes(value = "user")
@SessionAttributes(value = {"user"，"address"})
@SessionAttributes(types = User.class)
@SessionAttributes(types = {User.class,Address.class})
```
对于ViewHandler中的所有业务方法，只要向request中添加了user和addressdeduix ，或者添加了数据类型是User或Address时，Spring MVC都会自动的将该数据添加到session，并且key不变

> 将模型数据绑定到application对象

### application
```
@RequestMapping("/application")
	public String application(HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		User user = new User();
		user.setId(1);
		user.setName("张三");
		application.setAttribute("user", user);
		return "view";
	}
```
## 自定义数据转换器
数据转换器是指将客户端HTTP请求中的参数转换为业务方法中定义的形参，自定义表示开发者可以自主设计转换的方式，HandlerApdter已经提供了通用的转换，String转int等等  
但是在特殊的业务场景下，handlerAdapter无法进行转换，就需要开发者自定义转换器。  
客户端输入String类型的数据自定义转换为Date类型  
1、创建DateConverter转换器，实现Conveter接口
```
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


public class DateConverter implements Converter<String,Date>{

	private String pattern;
	
	public DateConverter(String pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public Date convert(String s) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(s);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}	
		return date;
	}
}
```
2、springmvc.xml配置转换器
```
		<!-- 配置转换器 -->
		<bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
			<property name="converters">
				<list>
					<bean class="com.sw.converter.DateConverter">
						<constructor-arg type="java.lang.String" value="yyyy-MM-dd"></constructor-arg>
					</bean>
				</list>
			</property>
		</bean>
		<!-- 添加id -->
	   <mvc:annotation-driven conversion-service="conversionService">
	   		<!-- 消息转换器 -->
	   		<mvc:message-converters register-defaults="true">
	   			<bean class="org.springframework.http.converter.StringHttpMessageConverter">
	   				<property name="supporteMediaTypes" value="text/html;charset=UTF-8"></property>
	   			</bean>
	   		</mvc:message-converters>
	   </mvc:annotation-driven>
```
3、jsp  
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/converter/date" method="post">
	输入日期：<input type="text" name="date"/>
	<input type="submit" value="提交"/>
</form>
</body>
</html>
```
## REST
REST:资源表现层状态转换，是目前比较主流的一种互联网软件架构，它结构清晰、标准规范、易于扩展、易于理解。   
### 资源(Resource)
网络上的一个实体，或者是网络中存在的一个具体信息等等，就是一个具体的存在，可以用一个URL指向它，每个资源都有一个特定的URL，要获取该资源的时候，只需要访问对应的URL即可
### 表现层(Representation)
资源具体呈现出来的形式，比如文本可以用txt格式表示，也可以用HTML、xml等来表示
### 状态转换(State Transfer)
客户端如果希望操作服务器中的某个资源，就需要通过某种方式让服务端发生状态转换，而这种转换是建立在表现层之上的，所以叫做“表现层状态转换”  
### 特点
1、URL更加简洁  
2、有利于不同系统之间的资源共享，只需要遵守一定的规范，不需要进行其他配置即可实现资源贡献  
### 使用
REST具体操作就是HTTP协议中四个表示操作方式的动词分别对应CRUD基本操作  
GET用来表示获取资源  
POST用来表示新建资源  
PUT用来表示修改资源  
DELETE用来表示删除资源  
handler
```
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sw.entity.Student;
import com.sw.repository.StudentRepository;

@RestController
@RequestMapping("/rest")
public class RESTHandeler {

	@Autowired
	private StudentRepository studentRepository;
	
//	@RequestMapping(value="/findAll",method=RequestMethod.GET)
	@GetMapping
	public Collection<Student> findAll(HttpServletResponse response){
		response.setContentType("text/json;charset=UTF-8");
		return studentRepository.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public Student findById(@PathVariable("id") long id) {
		return studentRepository.findById(id);
	}
	
	@PostMapping("/save")
	public void save(@RequestBody Student student) {
		studentRepository.saveOrUpdate(student);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody Student student) {
		studentRepository.saveOrUpdate(student);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public void deleteById(@PathVariable("id") long id) {
		studentRepository.deleteById(id);
	}
}
```
StudentRepository(interface)
```
import java.util.Collection;

import com.sw.entity.Student;

public interface StudentRepository {

	public Collection<Student> findAll();
	public Student findById(long id);
	public void saveOrUpdate(Student student);
	public void deleteById(long id);
}
```
StudentRepositoryImpl
```
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sw.entity.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

	private static Map<Long,Student> studentMap;
	
	static {
		studentMap = new HashMap<>();
		studentMap.put(1L,new Student(1L,"张三",22));
		studentMap.put(2L,new Student(1L,"李四",20));
	}
	
	@Override
	public Collection<Student> findAll() {
		// TODO Auto-generated method stub
		return studentMap.values();
	}

	@Override
	public Student findById(long id) {
		// TODO Auto-generated method stub
		return studentMap.get(id);
	}

	@Override
	public void saveOrUpdate(Student student) {
		// TODO Auto-generated method stub
		studentMap.put(student.getId(),student);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		studentMap.remove(id);
	}
}
```
## 文件上传下载
> 单文件上传

底层是使用Apache fileupdate组件完成上传，Spring MVC对这种方式进行了封装  
pom.xml
```
<!-- 文件上传 -->
	<dependency>
		<groupId>commons-io</groupId>
		<artifactId>commons-io</artifactId>
		<version>2.5</version>
	</dependency>
	<dependency>
		<groupId>commons-fileupload</groupId>
		<artifactId>commons-fileupload</artifactId>
		<version>1.3.3</version>
	</dependency>
```
JSP
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- method和enctype属性是为了防止只上传文件名而无文件,因为get请求只能将文件名传给服务器，enctype如果不设置也是能传文件名 -->
<form action="" method="post" enctype="multipart/form-data">
	<input type="file" name="img" />
	<input type="submit" value="上传" />
</form>
</body>
</html>
```
Handler
```
import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileHandler {

	@PostMapping("/upload")
	public String upload(MultipartFile img,HttpServletRequest request) {
		if(img.getSize()>0) {
			//获取保存上传文件的file路径
			String path = request.getSession().getServletContext().getRealPath("file");
			//获取上传的文件名
			String name = img.getOriginalFilename();
			File file = new File(path,name);
			try {
				img.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "upload";
	}
}
```
springmvc.xml
```
		<!-- 配置文件上传组件 -->
		<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"></bean>
```
> 多文件上传

JSP
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/file/uploads" method="post" enctype="multipart/form-data">
	file1:<input type="file" name="imgs" />
	file2:<input type="file" name="imgs" />
	file3:<input type="file" name="imgs" />
	<input type="submit" value="上传" />
</form>
</body>
</html>
```
Handler
```
	@RequestMapping("/uploads")
	public String uploads(MultipartFile[] imgs,HttpServletRequest request) {
		List<String> files = new ArrayList<>();
		for(MultipartFile img:imgs) {
			if(img.getSize()>0) {
				//获取保存上传文件的file路径
				String path = request.getSession().getServletContext().getRealPath("file");
				//获取上传的文件名
				String name = img.getOriginalFilename();
				File file = new File(path,name);
				try {
					img.transferTo(file);
					//保存上传后的文件路径
					files.add("/file/"+name);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("path",files);
		return "uploads";
	}
```
## 文件下载
JSP
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="/file/download/1">1.png</a>
	<a href="/file/download/3">2.png</a>
</body>
</html>
```
Handler
```
	@GetMapping("/download/{name}")
	public void download(String name,HttpServletRequest request,HttpServletResponse response) {
		if(name != null) {
			String path = request.getSession().getServletContext().getRealPath("file");
			File file = new File(path,name);
			OutputStream outputStream = null;
			if(file.exists()) {
				response.setContentType("application/forc-download");
				response.setHeader("Content-Disposition","attachment;filename="+name);
				try {
					outputStream = response.getOutputStream();
					outputStream.write(FileUtils.readFileToByteArray(file));
					outputStream.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					if(outputStream != null){
						try {
							outputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
```
## 表单标签库
Handler
```
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sw.entity.Student;

@Controller
@RequestMapping("/tag")
public class TagHandler {

	@GetMapping("/get")
	public ModelAndView get() {
		ModelAndView modelAndView = new ModelAndView("show");
		Student student = new Student(1L,"张三",22);
		modelAndView.addObject("student",student);
		return modelAndView;
	}
}
```
JSP
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>学生信息</h1>
<form:form modelAttribute="student">
	学号：<form:input path="id" /><br/>
	姓名：<form:input path="name"/><br/>
	年龄：<form:input path="age" /><br/>
	<input type="submit" value="提交" />
</form:form>
</body>
</html>
```
1、在JSP页面导入Spring   MVC的表单标签库，与导入JSTL标签库的语法相似，前缀prefix能自定义，通常为form  
```
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
```
2、将form表单与模型数据进行绑定，通过modelAttribute属性完成绑定，将modelAttribute的值设置为模型数据对于的key值  
```
modelAndView.addObject("student",student);

<form:form modelAttribute="student">
```
3、将模型数据的值取出来，绑定到不同的标签中，通过设置标签的path属性进行绑定
```
	学号：<form:input path="id" /><br/>
	姓名：<form:input path="name"/><br/>
	年龄：<form:input path="age" /><br/>
```
### 常用的表单标签
#### from
```
<form:from modelAttribute="student">
```
渲染的是HTML中的<form></form>，通过modelAttribute属性绑定具体的模型数据  
#### input
```
<form:input path="id"/>
```
渲染的是HTML中的<input type="text"/>，from标签绑定的是模型数据，input标签绑定的是模型数据中的属性值，通过path属性可以与模型数据中的属性名对应，并且支持级联操作  
```
<from:input path="address.name" />
```
#### password
```
<form:password path="password"/>
```
渲染的是HTML中的<input type="password"/>，通过path属性与模型数据的属性值进行绑定
#### checkbox
```
<form:checkbox path="hobby" value="读书"/>
```
渲染的是HTML中的<input type="checkbox" />,通过path与模型数据的属性值进行绑定，可以绑定boolean、数组和集合  
如果绑定boolean值，若改变了的值为true，则表示该复选框选中，否则不选中  
如果绑定数组或集合，数组/集合中的元素等于checkbox的value值，则选中
```
sutdnet.setHobby(Arrays.asList("读书"，"看电影"));
modelAndView.addObject("student",student);
```
```
<form:checkbox path="hobby" value="摄影"></form:checkbox>摄影<br/>
<form:checkbox path="hobby" value="读书"></form:checkbox>读书<br/>
<form:checkbox path="hobby" value="旅游"></form:checkbox>旅游<br/>
<form:checkbox path="hobby" value="看电影"></form:checkbox>看电影<br/>
```
#### checkboxes
```
<form:checkboxes items=${student.hobby} path="selecHobby"/>
```
渲染的是HTML中的一组<input type="checkbox" />，是对<form:checkbox/>的一种简化，需要结合items和path属性来使用，items绑定北边里的集合或数组，path绑定被选中的集合或数组，可以理解为，items为全部的可选集合，path为默认的选中集合
```
student.setHobby(Arrays.asList("摄影","读书","看电影"));
student.setSelectHobby(Array.asList("摄影","读书");
modelAndView.addObject("studnet",student);
```
```
<form:checkboxes items=${student.hobby} path="selecHobby"/>
```
path可以直接绑定模型数据的属性值，而items则需要通过EL表达式的形式从域对象中获取数据，不能直接写属性名  
#### rabiobutton
```
<from:radiobutton path="radioId" value=0/>
```
渲染的是HTML中的一个<input type="raido" />，绑定的数据与标签的value值相等则为选中，不等为不选中  
```
student.setRadioId(1);
modelAndView.addObject("student",student);
```
```
radiobutton:<form:radiobutton path="radioId" value="1" />radiobutton
```
#### rabiobuttons
```
<form:radiobutton itmes="${studnet.grade}" path="selectGrade"/>
```
渲染的是HTML中的一组<input type="radio"/>，需要结合items和path两个属性来使用，items绑定被遍历的集合或数组，path绑定被选中的值，items为全部的可选类型，path为默认选中的选项，用法与<form:checkboxex/>一致
```
Map<Integer,String> gradeMap = new HashMap<>();
gradeMap.put(1,"一年级");
gradeMap.put(2,"二年级");
gradeMap.put(3,"三年级");
gradeMap.put(4,"四年级");
gradeMap.put(5,"五年级");
student.setGradeMap(gradeMap);
student.setSelectGrade(3);
modelAndView.addObject("student",student);
```
```
<form:radiobutton items="${student.gradeMap}" path="selectGrade"/>
```
