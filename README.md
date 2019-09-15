#麻将社区

##资料
[Spring 文档](https://spring.io/guides)

[Spring web文档](https://spring.io/guides/gs/serving-web-content/#scratch)

[ES社区](https://elasticsearch.cn/explore)

[Bootstrap 文档](https://v3.bootcss.com/css/#buttons)

[Github OAuth](https://developer.github.com/apps/building-oauth-apps/)

[Thymleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)

##工具
[Git](https://www.git-scm.com/download/)

[Lombok](https://projectlombok.org/setup/maven):使用的是@Data功能

Flyway

H2数据库

#笔记
###登录
1. 用户点击登录按钮后，流程如下
    + 社区调用GitHub的授权页面
    + GitHub授权后回调社区的redirect_uri并携带code
    + 客户端用code来申请令牌token
    + GitHub确认无误后发放令牌
    + 客户端用令牌向GitHub申请用户信息
    + GitHub确认无误返回信息
    + 存入数据库，更新登录状态
2. okhttp
3.  + 首先用HttpServletRequest来获取一个session并赋值：            
        request.getSession().setAttribute("user",user);

    + 用thymleaf判断并获取session中的用户信息：th:if="${session.user == null}"
4. 为了不用每次启动服务就得登录一次，存入数据库h2:嵌入项目
5.使用Flyway来执行对数据库的操作
---
###sql脚本
```sql
CREATE CACHED TABLE PUBLIC.USER(
    ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_E41C3789_7736_469A_BF3D_AD65F4FA1911) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_E41C3789_7736_469A_BF3D_AD65F4FA1911,
    ACCOUNT_ID VARCHAR(100),
    NAME VARCHAR(50),
    TOKEN CHAR,
    GMT_CREATE BIGINT,
    GMT_MODIFIED BIGINT
)
```


