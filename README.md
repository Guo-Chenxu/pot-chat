# 阅读达人小程序后端

## 项目功能

类似于豆瓣图书, 用户可以查找图书并评论 (只能看到该书籍的简介, 无法阅读具体内容), 相比于豆瓣图书增加设定读书计划和打卡功能,
打卡领取积分, 积分兑换物品

## 模块划分

### 用户模块

-   [x] 检查是否注册
-   [x] 注册 (使用邮箱注册)
-   [x] 登录
-   [x] 发送验证码 (邮箱)

### 书籍模块

-   [x] 申请添加图书 (用户提交申请, 由管理员进行添加)
-   [x] 图书列表 (和筛选一个接口)
-   [x] 查找筛选 (书名 _(模糊匹配)_, 作者 _(模糊匹配)_, 分类, isbn)
-   [x] 智能推荐
-   [x] 展示图书详情
-   [x] 给图书点赞

### 评论模块

-   [x] 用户评论图书
-   [x] 展示图书评论
-   [x] 对于评论点赞
-   [x] 对评论进行评论 (todo 应当有回复提醒, 但是比较复杂, 暂时不做)
-   [x] 用户已有的所有评论

### 计划模块

-   [x] 设定阅读计划 (前端注意校验 天数*每天阅读页数 >= 书籍总页数)
-   [x] 获取用户的阅读计划
-   [x] 查看阅读计划详情
-   [x] 智能推荐阅读计划
-   [x] 删除计划

### 打卡模块

-   [x] 用户打卡 (只有一个打卡, 单一任务完成点击打卡后即视为用户今日已打卡)
-   [x] 查看打卡记录 (按月返回打卡数据)
-   [x] 查看打卡排行榜 (每月 1 号更新, 展示当月打卡天数排名) (每月一号上个月排行榜落盘持久化, 同时将上个月的排行榜过期时间设为60天, 支持查看之前月份的排行榜)

### 积分模块

-   [x] 积分增加 (暂时只有用户打卡后积分自动增加, 没有给前端提供接口)
-   [x] 用户查看积分余额
-   [x] 积分兑换商品 (接口限流)

### 商品模块

-   [x] 商品列表

## 接口文档

本地启动项目后, swagger 文档地址:
http://localhost:39240/api/readMaster/swagger-ui/index.html

服务器 swagger 文档地址:
http://43.138.48.26:39240/api/readMaster/swagger-ui/index.html

apifox文档地址：
https://apifox.com/apidoc/shared-cb656c9f-a020-47fa-8ee7-8b3143b5c184
