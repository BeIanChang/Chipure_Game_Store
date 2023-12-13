# 1. 项目简介

本项目采用前后端分离设计，前端基于 Vue + Vue-router + Vuex + Element-ui + Axios ，参考小米商城实现，后端采用Springboot + Mysql 实现。前端包含了10个页面：首页、登录、注册、全部商品、商品详情页、我的收藏、购物车、订单结算页面、我的订单以及错误处理页面。后端采用MVC模式，根据前端需要的数据分模块设计了相应的接口、控制层、数据持久层。

# 2. 环境配置

- Vue 2.6.10
- org.springframework.boot 3.1.1
- Mysql 5.7.41
- Nodejs 16.20.1
- JDK 2.0
- Gradle 8.1.1
- Navicat 16

## 2.1 前端配置

- ### 安装依赖

```shell
cd vue-store-master
npm install
```

- ### 配置后端地址

打开 vue.config.js，将 target 改为你的后端地址

在 /src/Global.js 中同样将 Vue.prototype.$target 改为你的后端地址

- ### 运行

```shell
npm run serve
```

## 2.2 后端配置

- ### 安装依赖

使用 Intellij Idea 打开 Chipureback项目，运行  

```shell
gradle build
```

- ### 配置sql文件

打开/src/main/application.yml，根据自己的mysql设置情况，配置url，username，password 

- ### 配置mysql

在Navicat中连接mysql数据库，运行Gamestore.sql文件

- ### 运行

使用springboot插件运行/src/main/java/com.ianchang.chipure/ChipureApplication  

# 3. 关键技术说明

## 3.1 前端技术

- ### Vue前端

采用Vue.js,可以讲数据和DOM绑定，实现数据驱动的动态更新。Vue.js 支持将用户界面拆分为可重用的组件。 每个组件封装了自己的模板、逻辑和样式，使开发者能够更高效地组织和管理代码。

- ### Vue router

Vue.js 配合 Vue Router 库，提供了客户端路由功能。 你可以定义路由表，根据 URL 的变化加载不同的组件，实现单页应用程序的页面切换和导航。 详见 vue-storemaser/src/router/index.js

- ### Vuex

使用Vuex管理登录状态，实现跨组件同步

- ### Axios

处理前后端交互时，采用Axios来发送和处理http请求，实现数据同步

## 3.2 后端技术

本篇旨在介绍后端采用 Spring Boot 框架、使用 Spring Data JPA 连接数据库，并利用 `@Data` 注解实现 JavaBean，以及使用 `@Entity` 注解实现 Bean 到数据库表的映射的关键技术。

本项目主要使用了以下依赖项：

- Spring Web：用于构建 Web 应用程序，提供了处理 HTTP 请求和响应的功能。
- Spring Data JPA：基于 Spring 对 JPA 的封装，简化了数据库操作的开发。
- Lombok：通过注解自动生成 JavaBean 的样板代码，减少了重复的编写。
- MySQL Driver：用于连接 MySQL 数据库。



### 3.2.1 框架选择

本项目选择了 Spring Boot 作为后端开发框架，它提供了快速搭建基于 Java 的企业级应用的能力，并且对 Spring 框架进行了简化和优化，提供了开箱即用的配置和约定。

### 3.2.2 数据库连接

为了连接数据库，本项目采用了 Spring Data JPA 来进行数据库操作。Spring Data JPA 是 Spring 框架对 JPA (Java Persistence API) 的实现和封装，可以简化与数据库的交互，提供了强大的 CRUD 操作和查询功能。

### 3.2.3 数据库映射

在实体类中使用 `@Entity` 注解来实现 Bean 到数据库表的映射。该注解将实体类标记为 JPA 实体，使其能够与数据库中的表进行映射。通过定义实体类的属性和对应的数据库字段，Spring Data JPA 将负责处理对象和表之间的转换。

在本项目中使用了以下类和技术：

- `@Entity`：注解应用在实体类上，用于表示该类是一个 JPA 实体。
- `@Id`：注解应用在实体类的主键字段上，用于标识主键。
- `@Column`：注解应用在实体类的属性字段上，用于指定数据库表中对应的列名。

### 3.2.4 JavaBean 实现

使用 Lombok 提供的 `@Data` 注解可以简化实体类的编写。`@Data` 注解会自动为实体类生成 getter、setter 方法等，减少了样板代码的编写。

在本项目中使用了以下类和技术：

- `@Data`：注解应用在实体类上，用于自动生成 getter、setter 方法等。

### 3.2.5 Controller 实现 API

通过编写 Controller 类，实现不同 API 接口的逻辑处理。在 Controller 类中使用注解 `@RestController` 标识该类为控制器，并通过 `@RequestMapping` 注解指定请求路径。根据 API 文档中的需求，编写相应的请求处理方法，使用注解 `@PostMapping` 指定请求方式和路径，然后实现具体的业务逻辑。

在本项目中使用了以下类和技术：

- `@RestController`：注解应用在 Controller 类上，用于将其标识为 REST 风格的控制器。
- `@RequestMapping`：注解应用在方法上，用于指定请求的路径和方法。
- `@PostMapping`：注解应用在方法上，用于指定 POST 请求的路径。

### 3.2.6 测试相关注解和技术

为了进行单元测试和集成测试，本项目使用了 Spring Boot 提供的测试框架和相关注解。以下是在测试类中使用的注解和技术的说明：

- `@SpringBootTest`（spring-boot-test 包）：用于标记测试类为 Spring Boot 应用的上下文测试，会加载整个应用程序的配置和依赖项。
- `@AutoConfigureMockMvc`（spring-boot-starter-test 包）：用于自动配置 MockMvc，可以模拟发送 HTTP 请求并验证响应结果。
- `@RunWith(SpringRunner.class)`（junit 包）：指定运行测试的 Runner 类，使用 SpringRunner.class 可以确保测试在 Spring 环境中运行。
- `@Autowired`（spring-boot-test 包）：用于自动注入测试类的依赖项，例如控制器、服务、仓库等。
- `@MockBean`（spring-boot-test 包）：用于创建和注入模拟对象，以替代真实的依赖项进行测试。


