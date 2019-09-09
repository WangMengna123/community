#麻将社区

##资料
[Spring 文档](https://spring.io/guides)
[Spring web文档](https://spring.io/guides/gs/serving-web-content/#scratch)
[ES社区](https://elasticsearch.cn/explore)
[Bootstrap 文档](https://v3.bootcss.com/css/#buttons)
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/)

##工具
[Git](https://www.git-scm.com/download/)

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

