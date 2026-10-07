# wechat-food-ordering

> 美食屋 · 微信小程序点餐系统

一个基于 **原生微信小程序 + Spring Boot** 的点餐（外卖/堂食）系统。用户通过微信授权登录后，可浏览菜单、加入购物车、下单支付、查看订单、评价菜品，并通过消费获得积分、用积分兑换菜品。

## 技术栈

| 层次 | 技术 |
| --- | --- |
| 前端 | 原生微信小程序（WXML / WXSS / JS），ES6 Promise 异步请求 |
| 后端 | Spring Boot 4.0.6、Spring MVC、Java 21 |
| 持久层 | MyBatis-Plus 3.5.15（含代码生成器）、MySQL 9.x |
| 工具库 | Gson（JSON 序列化）、Apache HttpClient（调用微信接口）、Lombok |
| 认证 | 微信 `jscode2session` 换取 openid + 服务端 Session/Cookie |

## 项目结构

```
food/
├── food/                          # 微信小程序前端
│   ├── app.js / app.json / app.wxss
│   ├── pages/
│   │   ├── index/                 # 首页：轮播图、广告、分类入口、背景音乐
│   │   ├── list/                  # 菜单列表：分类联动滚动、购物车、小球动画
│   │   ├── order/
│   │   │   ├── checkout/          # 订单确认与支付
│   │   │   ├── detail/            # 订单详情（取餐号/订单号）
│   │   │   ├── list/             # 订单列表（分页 + 下拉刷新 + 触底加载）
│   │   │   └── comment-list/      # 订单评价（星级 + 文字）
│   │   ├── record/                # 我的：头像、积分入口、历史订单
│   │   └── points/                # 积分商城：余额、兑换、积分记录
│   ├── utils/
│   │   ├── fetch.js               # 统一请求封装（Cookie 持久化、错误重试）
│   │   ├── config.js              # 后端 baseUrl 配置
│   │   ├── decodeCookie.js        # Set-Cookie 解析
│   │   └── shopcartAnimate.js     # 加购小球抛物线动画
│   ├── images/  audio/            # 图标、轮播素材、背景音乐
│   └── project.config.json
│
├── food-spring/                   # Spring Boot 后端
│   ├── src/main/java/my/food/
│   │   ├── Food1Application.java  # 启动类
│   │   ├── controller/            # 接口层（Food / User / Points / OrderComment ...）
│   │   ├── service/ + impl/       # 业务层
│   │   ├── mapper/                # MyBatis-Plus Mapper
│   │   ├── entity/ + entity/dto/  # 实体与传输对象
│   │   ├── bean/                  # Result / Param / Promotion 等通用对象
│   │   ├── config/WebConfig.java  # 静态资源映射 + 跨域配置
│   │   └── tool/                  # HttpClientUtils、Tool（单号/取餐码补零）
│   └── src/main/resources/
│       ├── application.properties # 端口、数据源、图片目录、host
│       ├── mapper/*.xml           # 自定义 SQL 映射
│       └── schema-points.sql      # 积分模块建表脚本
│
└── weixin.sql                     # 全量数据库结构与示例数据
```

## 核心功能

### 1. 微信登录与鉴权
- 小程序 `wx.login` 拿到 `js_code`，请求 `/user/login`；
- 后端调用微信 `sns/jscode2session` 接口换取 `openid`（appid/appsecret 存于 `setting` 表），
  若为新用户则自动注册；
- 登录态写入 `HttpSession`，前端把 `JSESSIONID` 存入本地缓存，后续请求通过 Cookie 携带，实现"免重复登录"；
- `app.js` 启动时先调用 `/user/checkLogin` 判断登录状态，失败自动重试。

### 2. 菜单浏览与购物车
- 首页 `/food/index` 返回轮播图、广告位、分类入口（数据来自 `setting` 表）；
- 菜单页 `/food/list` 返回按 `sort` 排序的分类与菜品，前端实现左右分类联动滚动；
- 购物车在本地维护（`cartList`），支持加/减/清空，并带抛物线加购动画；
- 小程序端使用 `wx.createInnerAudioContext` 播放循环背景音乐，支持静音切换。

### 3. 下单与支付
- 提交购物车调用 `/food/createOrder`，后端按配置的满减规则（`setting.promotion`，如满 50 减 10）计算实付金额，写入 `order1` 与 `order_food`；
- 确认页调用 `/food/commentOrder` 保存订单备注，再调用 `/food/pay` 完成支付；
- 支付成功会累加用户累计消费金额 `user.price`，并按订单金额发放积分。

### 4. 订单与评价
- `/food/orderlist` 基于 `last_id + row` 实现游标分页，支持下拉刷新与触底加载更多；
- `/food/record` 返回已支付订单；
- 订单详情展示订单号（`WX` + 补零 ID）与取餐码（`A` + 补零 ID）；
- 评价接口 `/food/submitComment`（星级 1–5 + 文字），`ordercomment` 表按 `order_id` 唯一，
  支持同一订单二次修改；`/food/getOrderListWithComment` 返回订单及其评价。

### 5. 积分体系
- 支付订单后按 `floor(订单金额)` 发放积分，写入 `points_record`；
- `/points/get` 查询积分余额，`/points/history` 查询积分流水；
- `/points/goods` 展示可兑换菜品（下架菜品过滤，兑换所需积分 = 菜品价格取整）；
- `/points/exchange` 校验余额并扣减积分、写入 `points_exchange` 兑换记录，全程事务保证。

## 主要接口

| 模块 | 方法 | 路径 | 说明 |
| --- | --- | --- | --- |
| 用户 | GET | `/user/login` | 微信登录（js_code 换 openid） |
| 用户 | GET | `/user/checkLogin` | 校验登录态 |
| 菜品 | GET | `/food/index` | 首页配置（轮播/广告/分类） |
| 菜品 | GET | `/food/list` | 菜单 + 分类 + 满减促销 |
| 订单 | POST | `/food/createOrder` | 创建订单 |
| 订单 | POST | `/food/getOrderById` | 订单详情 |
| 订单 | POST | `/food/commentOrder` | 保存订单备注 |
| 订单 | POST | `/food/pay` | 支付订单 |
| 订单 | GET | `/food/orderlist` | 订单分页列表 |
| 订单 | GET | `/food/record` | 已支付订单 |
| 评价 | POST | `/food/submitComment` | 提交/更新评价 |
| 评价 | GET | `/food/getOrderListWithComment` | 含评价的订单列表 |
| 积分 | GET | `/points/get` | 积分余额 |
| 积分 | GET | `/points/goods` | 可兑换菜品 |
| 积分 | GET | `/points/history` | 积分流水 |
| 积分 | GET | `/points/exchangeHistory` | 兑换记录 |
| 积分 | POST | `/points/exchange` | 积分兑换菜品 |

## 数据库设计

库名 `weixin`，包含以下表（见 [weixin.sql](file:///c:/Users/钟鸿坤/Desktop/study/微信小程序/food/weixin.sql)）：

| 表名 | 说明 |
| --- | --- |
| `user` | 用户（openid、累计消费金额） |
| `admin` | 管理员 |
| `category` | 菜品分类（含排序） |
| `food` | 菜品（分类、价格、图片、上下架状态） |
| `order1` | 订单（金额、数量、支付/取餐状态、备注、时间） |
| `order_food` | 订单-菜品明细 |
| `ordercomment` | 订单评价（星级、内容） |
| `points` | 用户积分（总积分/可用积分） |
| `points_record` | 积分流水（类型、数额、原因） |
| `points_exchange` | 积分兑换记录 |
| `setting` | 系统配置（appid、appsecret、轮播/广告图、满减规则） |

积分相关表亦提供独立脚本：[schema-points.sql](file:///c:/Users/钟鸿坤/Desktop/study/微信小程序/food/food-spring/src/main/resources/schema-points.sql)。

## 本地运行

### 环境要求
- JDK 21、Maven 3.8+
- MySQL 8.0+
- 微信开发者工具

### 后端
1. 创建数据库并导入结构与数据：

   ```sql
   CREATE DATABASE weixin DEFAULT CHARSET utf8mb4;
   ```

   然后执行 [weixin.sql](file:///c:/Users/钟鸿坤/Desktop/study/微信小程序/food/weixin.sql)。

2. 修改 [application.properties](file:///c:/Users/钟鸿坤/Desktop/study/微信小程序/food/food-spring/src/main/resources/application.properties) 中的数据库账号密码、端口，
   以及图片虚拟目录 `defaultImagesDir` / `foodImagesDir` 和 `host`（对外访问地址）。

3. **配置小程序 AppID / AppSecret（必做）**

   仓库中 [weixin.sql](file:///c:/Users/钟鸿坤/Desktop/study/微信小程序/food/weixin.sql) 的 `setting` 表里 `appid` / `appsecret` 均为**空值**，
   克隆下来后必须填入**你自己的**小程序凭据，否则调用微信 `jscode2session` 换取 openid 会失败、**无法登录**：

   ```sql
   UPDATE `setting` SET `value` = '你的小程序AppID'     WHERE `name` = 'appid';
   UPDATE `setting` SET `value` = '你的小程序AppSecret' WHERE `name` = 'appsecret';
   ```

   > **获取方式**：登录[微信公众平台](https://mp.weixin.qq.com) → 「开发管理」→「开发设置」，即可查看 AppID 与 AppSecret（AppSecret 需管理员生成，且仅显示一次，请及时保存）。
   >
   > **安全提示**：AppSecret 属于敏感密钥，请只写入本地数据库，**切勿提交到 Git 仓库**。

4. 启动服务：

   ```bash
   ./mvnw spring-boot:run
   ```

   服务默认运行在 `http://localhost:8080`。

### 前端
1. 用微信开发者工具导入 `food` 目录；
2. **将 [project.config.json](file:///c:/Users/钟鸿坤/Desktop/study/微信小程序/food/food/project.config.json) 中的 `appid` 替换为你自己的小程序 AppID**
   （仓库中默认值为测试号 `touristappid`，不改成你自己的 AppID 将无法完成微信登录）；
3. 在 [config.js](file:///c:/Users/钟鸿坤/Desktop/study/微信小程序/food/food/utils/config.js) 中确认 `baseUrl` 指向后端地址；
4. 本地调试需在开发者工具中勾选「不校验合法域名」。

## 说明与约定

- 静态图片通过 [WebConfig.java](file:///c:/Users/钟鸿坤/Desktop/study/微信小程序/food/food-spring/src/main/java/my/food/config/WebConfig.java) 映射：`/static/**` 与 `/images/**` 分别指向系统配置的磁盘目录，菜品图片返回给前端时会拼接 `host` 前缀；
- 已配置全局 CORS 并 `allowCredentials(true)`，以支持 Cookie 方式携带登录态；
- 接口统一返回 `Result`（`isLogin` / `list` / `message` 等字段）或简单 `Map`（`success` / `message`）。