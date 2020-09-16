# Maven简介
Apache Maven是一个软件项目管理和综合工具。基于项目对象模型（POM）的概念，Maven可以从一个中心资料片管理项目构建，报告和文件。
## 使用背景
创建一个java项目需要用到很多第三方的类库，项目较小还可以逐个引入，但是如果项目过大，所需要的类包数量令人惊讶，而且jar包之间的关系错综复杂，相互之间也有引用的情况，而jar引用出错也会使项目编译失败。  
所以，为了减少程序员花费在导入类库的时间，Maven这个帮助程序员构建项目的工具就开始发挥它的作用。
## 功能
> 下载管理jar包，只需要指明类包的坐标和版本，Maven自动下载导入到项目

> 管理依赖，Maven的一个核心特性

> 打包部署，将项目生成可执行的jar包或者将生成war包部署到服务器


# Maven的安装配置
1、安装jdk并配置环境变量  
2、官网下载maven压缩包解压到本地(需记住路径)  
3、进行maven的配置：  
> M2_HOME/MAVEN_HOME:解压路径  
> 更新PATH：添加%M2_HOME%\bin
 
验证：mvn -version:查看版本号
## 仓库
### 本地仓库
Maven的本地仓库是在第一次执行Maven命令后创建的，Maven第一次查找的也是本地仓库，如果本地仓库没有再去中央仓库查找。
#### 更新Maven的本地库路径
默认情况下，每个用户在用户目录下后有一个路径名为```.m2/respository```的仓库目录，如果要修改该默认位置，就需要另外进行配置  
打开```Maven解压后路径\conf\settings.xml```  
右键编辑打开  
找到下列代码块添加Maven要修改的本地库路径
```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <!-- localRepository
   | The path to the local repository maven will use to store artifacts.
   |
   | Default: ${user.home}/.m2/repository
  <localRepository>/path/to/local/repo</localRepository>
  --><localRepository>E:\apache-maven-3.6.3\repository</localRepository>
```
等到第一次运行Maven命令时，Maven就会将文件下载到指定的路径中。
保存关闭
## Maven中央仓库
可以查找所需要依赖的坐标信息  
https://search.maven.org/
## 添加Java.net储存库
如果Maven在中央仓库也找不到依赖的文件，那么它会停止构建过程并输出错误信息到控制台，为了防止这种情况，Maven提供了远程仓库的概念，远程仓库是开发人员自己定制的仓库，可以在项目中配置某个远程仓库进行使用  
在pom.xml中进行声明  
```
<repositories>
    <repository>
        <id>company</id>
        <name>Company Repository</name>
        <url>http://repository.company.com/maven2/</url>
    </repository>
</repositories>
```
该案例声明了一个id为```company```，名为```Company Repository```的仓库，每个仓库的id必须是唯一的，Maven自带的中央仓库id为```central```,所以如果其他仓库也用这个id，那么就会覆盖中央仓库，url指向了远程仓库的地址，一般该url基于http协议。  

声明后，Maven的依赖库查询顺序更改为：  
1、在Maven本地资源库中搜索，没有结果进入第二步  
2、在Maven中央存储库中搜索，没有结果进入第三步  
3、在java.new Maven的远程存储库搜索，没有结果提示错误信息  
## 添加JBoss Maven存储库
也在pom.xml中进行声明
```
<repositories>
    <repository>
    	<id>JBoss repository</id>
    	<url>http://repository.jboss.org/nexus/content/groups/public/</url>
    </repository>
</repositories>
```
# Maven介绍
## 工作原理
根据案例了解maven的工作原理：  
假设想使用Log4j作为项目日志，那么需要导入Log4j的依赖
### 传统方式
1、访问官网  
2、下载Log4j的jar库  
3、复制jar到项目类路径  
4、手动将其包含到项目的依赖  
5、所有的管理需要一切由自己做
### Maven方式
1、获取Log4j的Maven坐标  
2、maven会自动下载Log4j的版本库  
3、声明Maven的坐标转换成pom.xml文件  
4、当Maven编译或创建，Log4j的jar会自动下载，并放到Maven本地存储库  
5、查找导入等步骤由Maven管理  
## 定制库
有些情况需要自己导入jar库：  
1、要使用的jar不存在于Maven的中心存储库中  
2、有一个自定义的jar需要在maven项目中用到  
比如：  
kaptcha是一个流行的第三方java库，用来生成验证码，但它不在Maven的中央仓库中  
### 1、mvn安装
下载kaptcha的jar，放到一个地方  
然后运行命令：
```
mvn install:install-file-Dfile=c:\kaptcha-{version}.jar-DgroupId=com.google.code-DartifactId=kaptcha-Dversion={version} -Dpackaging=jar
```
例如：
```
mvn install:install-file -Dfile=c:\kaptcha-2.3.jar -DgroupId=com.google.code
```
### 2、声明
在pom.xml中声明kaptcha的坐标  
```
<dependency>
    <groupId>com.google.code</groupId>
    <artifactId>kaptcha</artifactId>
    <version>2.3</version>
</dependency>
```
## 构建生命周期
Maven强大的一个重要原因是它有一个十分完善的生命周期模型。  
这个生命周期可以从两个方面来理解：
<ul>
    <li>顾名思义，运行Maven的每个步骤都由它来定义的，这种预定义的默认行为使得我们使用Maven变得简单。</li>
    <li>这个模型是一种标准，在不同的项目中，使用Maven的接口是一样的，这样就不用仔细理解每个项目的构建了。</li>
</ul>

### 相互独立的生命周期
Maven由三套相互独立的生命周期：  
<ul>
    <li>Clean(清洁)&nbsp;&nbsp;在进行真正的构建之前进行一些清理工作</li>
    <li>default(默认)&nbsp;&nbsp;构建的核心部分，编译、测试、打包、部署等</li>
    <li>site(站点)&nbsp;&nbsp;生成项目报告，站点，发布站点</li>
</ul>
相互独立：可以仅用clean来清理工作目录，也可以仅用site来生成站点，也可以直接运行这三套生命周期。 

#### Clean(清洁)
Clean生命周期一共包含了三个阶段：  
<ul>
    <li>pre-clean  执行一些需要在clean之前完成的工作</li>
    <li>clean 移除所有上一次构建生成的文件</li>
    <li>post-clean 执行一些需要在clean之后立即完成的工作</li>
</ul>
mvn clean中的clean就是上面的clean阶段，在一个生命周期中，如果运行某个阶段，那么这个阶段之前的所有阶段都会被运行，也就如果mvn clean，那么pre-clean也会运行，如果运行post-clean，那么就是这三个阶段都会运行，这样可以大大简化命令行的输入。  

#### Default(默认)
这个生命周期是Maven中最重要的生命周期，绝大部分工作都在这个生命周期中发生。  
一共有二十三个阶段。

#### Site(站点)
<ul>
    <li>pre-site 执行一些需要在生成站点文档之前完成的工作</li>
    <li>site 生成项目的站点文档</li>
    <li>post-site 执行一些需要在生成站点文档之后完成的工作，并且为部署做准备</li>
    <li>site-deploy 将生成的站点文档部署到特定的服务器上</li>
</ul>
这里经常用到的是site阶段和site-deploy阶段，用以生成和发布Maven站点。 


## Maven插件
Maven是一个执行插件的框架，每一个任务实际上是由插件完成的，Maven的插件通常用于：  
<ul>
    <li>创建jar文件</li>
    <li>创建war文件</li>
    <li>编译代码文件</li>
    <li>进行代码单元测试</li>
    <li>创建项目文档</li>
    <li>创建项目报告</li>
</ul>

一个插件通常提供了一组目标，可使用以下语法来执行：
```
mvn [plugin-name]:[goal-name]
```
### 插件类型
Maven提供两种类型的插件：  
<table>
    <th>类型</th>
    <th>描述</th>
    <tr>
        <td>构建插件</td>
        <td>在生成过程中执行，并在 pom.xml 中的<build/> 元素进行配置</td>
    </tr>
    <tr>
        <td>报告插件</td>
        <td>在网站生成期间执行，在 pom.xml 中的 <reporting/> 元素进行配置</td>
    </tr>
</table>
常见的插件列表：  
<table>
    <th>插件</th>
    <th>描述</th>
    <tr>
        <td>clean</td>
        <td>编译后的清理目标，删除目标目录</td>
    </tr>
    <tr>
        <td>comliler</td>
        <td>编译Java源文件</td>
    </tr>
    <tr>
        <td>surefile</td>
        <td>运行JUnit单元测试，创建测试报告</td>
    </tr>
    <tr>
        <td>jar</td>
        <td>从当前项目构建jar文件</td>
    </tr>
    <tr>
        <td>war</td>
        <td>从当前项目构建war文件</td>
    </tr>
    <tr>
        <td>javadoc</td>
        <td>产生用域该项目的javadoc</td>
    </tr>
    <tr>
        <td>antrun</th>
        <td>从构建所述的任何阶段与性能一组Ant任务</td>
    </tr>
</table>

## 项目模板
Maven使用Archetype为用户提供不同类型的项目模板，它是一个非常大的列表(614个数字)  
Maven使用下面的命令来帮助用户快速开始构建一个新的java项目  
```
mvn archetype:generate
```
Archetype是一个Maven插件，其任务是按照其模板来创造一个项目结构  
可以在编辑软件里进行选择Maven项目模板
## 快照
### 背景
在使用maven的过程中，我们在开发阶段经常要修改公共库并发布，maven的依赖管理是基于版本管理的，对于发布的artifact，如果版本号相同，即使我们内部的镜像服务器上的组件比本地新，maven也不会主动下载，如果我们还用基于正式发布版本来做依赖管理，那么遇到更新的问题就要发布一次新版本，就会比较不符合实际情况，如果我们使用快照版本，这些问题就迎刃而解了。  
快照是一个特殊版本，指出目前开发拷贝，不同于常规版本，Maven每生产一个远程存储库都会检查新的快照版本。  
maven的仓库分为两种，snapshot快照仓库和release发布仓库，快照仓库用于保存开发过程中的不稳定版本 ，release正式仓库用域保存稳定的发行版本。  
只需要在pom文件中的版本后加上-SNAPSHOT即可  
```
<project xmlns="http://maven.apache.org/POM/4.0.0" 
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
   http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>data-service</groupId>
    <artifactId>data-service</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <name>health</name>
    <url>http://maven.apache.org</url>
    <properties>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
</project>
```
所以，我们在开发阶段，可以将公用库的版本设置为快照版本，而被依赖组件则引用快照版本进行开发，在公用库的快照版本更新后，我们也不需要修改pom文件提示版本号来下载新的版本，直接mvn命令执行相关编译、打包命令即可重新下载最新的快照库了，从而也方便了我们进行开发  
## 构建环节
<ul>
    <li>清理：清理以前的编译结果，为重新编译做好准备</li>
    <li>编译：将java源程序编译为字节码文件</li>
    <li>测试：针对项目中的关键点进行测试，确保项目在迭代开发过程中关键点的正确性</li>
    <li>报告：在每一次测试后以标准的格式记录和展示测试结果</li>
    <li>打包：将一个包含诸多文件的工程封装为一个压缩文件用于安装或部署，java工程对应jar包，web工程对于war包</li>
    <li>安装：在Maven环境下特指将打包的结果安装到本地仓库中</li>
    <li>部署：将jre包部署到远程仓库或将war包部署到服务器上运行</li>
</ul>

## Maven依赖管理
参考文档：https://blog.csdn.net/qq_26442553/article/details/78694993
### 1、依赖的基本概念
当Ajar包需要用到Bjar包中的类时，就是A对B有依赖  
配置的基本形式是使用dependency标签指定目标jar包的坐标：
```
<dependencies>
    <dependency>
        <!—坐标 -->
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.10</version>
        <!-- 依赖的范围 -->
        <scope>test</scope>
    </dependency>
</dependencies>
```
### 2、直接依赖和间接依赖
如果A依赖B，B依赖C，那么A和B、B和C都是直接依赖，而A和C是间接依赖  
### 3、依赖的范围
当一个maven工程添加了对某个jar包的依赖后，这个被依赖的jar包可以对应下面几个可选的范围  
1、compile
<ul>
    <li>main目录下的Java代码可以访问这个范围的依赖</li>
    <li>test目录下的java代码可以访问这个范围的依赖</li>
    <li>部署到Tomcat服务器上运行时要放在WEB-INF的lib目录下</li>
    例如：对Hello的依赖，主程序、测试程序和服务器运行时都需要用到
</ul>
2、test
<ul>
    <li>main目录下的Java代码不能访问这个范围的依赖</li>
    <li>test目录下的java代码可以访问这个范围的依赖</li>
    <li>部署到Tomcat服务器上运行时不会放在WEB-INF的lib目录下</li>
    例如：对junit的依赖，仅仅是测试程序部分需要
</ul>
3、provided
<ul>
    <li>main目录下的Java代码可以访问这个范围的依赖</li>
    <li>test目录下的java代码可以访问这个范围的依赖</li>
    <li>部署到Tomcat服务器上运行时不会放在WEB-INF的lib目录下</li>
    例如：servlet-api在服务器上运行时，servlet容器会提供相关API，所以部署的时候不需要
</ul>
4、runtime[了解]
<ul>
    <li>main目录下的Java代码不能访问这个范围的依赖</li>
    <li>test目录下的java代码可以访问这个范围的依赖</li>
    <li>部署到Tomcat服务器上运行时不会放在WEB-INF的lib目录下</li>
    例如：JDBC驱动，只有在测试运行和在服务器运行的时候才决定使用什么样的数据库连接
</ul>

### 4、依赖的原则：解决jar包冲突
路径最短者优先、路径相同先声明优先
#### 路径最短者优先
如果：
```
本项目-->A.jar-->B.jar-->X.jar
本项目-->C.jar-->X.jar
```
显而易见，同样的X.jar在C.jar中的路径最短，所以使用C.jar里的X.jar
#### 路径相同先声明优先
```
本项目-->A.jar-->X.jar
本项目-->C.jar-->X.jar
```
那么，A.jar和C.jar哪个先在pom.xml中先声明，那么就用哪个jar的X.jar
### 5、依赖的排除
有时候为了确保程序正确可以将有可能重复的间接依赖排除，比如：  
假设当前工程为A，直接依赖B  
B依赖C的1.1.1版本，对于A来说是间接依赖  
当前工程A直接依赖C的1.1.2版本  
而如果只想引入C的1.1.2版本而不引入C的1.1.1版本  
那么就可以加入exclusions配置，就可以在依赖B的时候排除版本为1.1.1的C的间接依赖
```
<dependency>
    <groupId>B</groupId>
    <artifactId>B</artifactId>
    <version>0.0.1</version>
    <!-- 依赖排除 -->
    <exclusions>
       <exclusion>
           <groupId>C</groupId>
           <artifactId>C</artifactId>
       </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>C</groupId>
    <artifactId>C</artifactId>
    <version>1.1.2</version>
</dependency>
```
### 6、统一管理目标jar包的版本
以对Spring的jar包依赖为例，Spring的每一个版本中都包含spring-core、spring-context等jar包，所以我们应该导入版本一致的Spring.jar包，而不是使用着4.0.0的spring-core的同时也使用4.1.1的spring-context   
当我们需要将一些jar包的版本统一升级到4.1.1时，不用一个个手动修改，由统计配置的方式
```
<properties>
    <spring.version>4.1.1.RELEASE</spring.version>
</properties>
......
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-orm</artifactId>
    <version>${spring.version}</version>
</dependency>
```
这样，只需要调整一个数值就能改变很多依赖的版本了。
## 继承
一个Maven项目可以继承另一个Maven项目的依赖，被继承的项目被称为父工程，继承的项目被称为子工程  
### 使用情况
当多个项目或模块所需要的依赖大部分相同时，可以将这些依赖放在一个项目或模块中进行统一管理，然后让其他需要用到这些依赖的工程继承这个父工程
### 创建工程
1、创建父工程  
和创建一般的maven工程相似，只有一点不同，打包方式设置为pom  
在父工程pom.xml中配置依赖
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.sw</groupId>
  <artifactId>parent</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>pom</packaging>
 
  
  <!-- 通过继承管理依赖 -->
  <dependencyManagement>
  	  <dependencies>
 		<dependency>
	      <groupId>junit</groupId>
	      <artifactId>junit</artifactId>
	      <version>3.8.1</version>
	    </dependency>
	    <dependency>
	      <groupId>org.springframework</groupId>
	      <artifactId>spring-beans</artifactId>
	      <version>5.2.8.RELEASE</version>
	    </dependency>
	  </dependencies>
  </dependencyManagement>

	<!-- 聚合 -->
	<modules>
		<module>../son</module>
	</modules>
</project>
```
2、在子工程中引用父工程  
因为在父工程中已经指明版本，所以在子工程中不用指明版本，只需要指明groupId和artifactId即可
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <artifactId>son</artifactId>
  <packaging>jar</packaging>

  <name>son</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

	<!-- 配置父工程 -->
	<parent>
		  <groupId>com.sw</groupId>
		  <artifactId>parent</artifactId>
		  <version>0.0.1-SNAPSHOT</version>
		  <!-- 从当前工程出发查找父工程的pom.xml文件 -->
		  <relativePath>../parent/pom.xml</relativePath>
	</parent>
	
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
    </dependency>
  </dependencies>
</project>
```
## 聚合
### 使用情况
有时一个项目会有很多模块组成，对项目划分模块，能够让项目具有更高的重用性和清晰的功能划分，这时就需要用聚合将多个模块聚合在一起，能够使用同一条命令进行创建
聚合主要是方便开发人员对多模块项目的多个模块
### 创建工程
1、创建主工程
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.sw</groupId>
    <artifactId>packaging</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>module1</module>
        <module>module2</module>
    </modules>
</project>
```
2、在主工程中创建两个模块  
module1:
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>packaging</artifactId>
        <groupId>com.sw</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>module1</artifactId>

</project>
```
module2:
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>packaging</artifactId>
        <groupId>com.sw</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>module2</artifactId>
</project>
```
这是模块之间的聚合配置
```
    <modules>
        <module>module1</module>
        <module>module2</module>
    </modules>
```
如果是若干个工程进行聚合则需要配置路径,所有工程需要在同一目录或者主目录的子目录下
```
    <modules>
        <module>../module1</module>
        <module>../module2</module>
    </modules>
```