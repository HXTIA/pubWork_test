# HXTIA pub_homework
## 阅读须知
* 仅仅是一个测试架构
* 所用到的测试表，在`test.sql`里


## 目录说明
* [父工程](https://github.com/HXTIA/pubWork_test)
* [后台管理的Api](https://github.com/HXTIA/pubWork_test/tree/main/hxtia-admin-api)
* [公共的配置](https://github.com/HXTIA/pubWork_test/tree/main/hxtia-common)
* [持久层](https://github.com/HXTIA/pubWork_test/tree/main/hxtia-db)
* [Pojo实体类](https://github.com/HXTIA/pubWork_test/tree/main/hxtia-pojo)
* [Wx小程序Api](https://github.com/HXTIA/pubWork_test/tree/main/hxtia-wx-api)

## 项目结构
```text
pub_homework 	# 父工程
├── hxtia-admin-api.src.main 	# 后台管理的Api
│   ├── java.run.hxtia.pubwork
│   │   ├── controllers		# 控制层
│   │   └── services.impl	# 业务层
│   └── resources
├── hxtia-common.src.main	# 通用的一些配置
│   ├── java.run.hxtia.pubwork.common
│   │   ├── baseController	# 基础的controller【集成了】
│   │   ├── cache	# ehcache缓存【稍后会换成redis】
│   │   ├── config	# 一些配置类
│   │   ├── exception	# 统一异常处理
│   │   ├── filter	# 过滤器
│   │   ├── prop	# 读取yml文件的配置类
│   │   ├── shiro	# 集成shiro【权限管理已经集成（若需要，写对应的验证即可）】
│   │   ├── upload	# 文件上传【主要是上传图片（支持多图片编辑）】
│   │   └── utils	# 一些工具类
│   └── resources
├── hxtia-db.src.main	# 持久层
│   ├── java.run.hxtia.pubwork
│   │   ├── config	# 额外配置【MybatisPlus的】
│   │   └── mappers	# 映射文件
│   └── resources.run.hxtia.pubwork.mappers
├── hxtia-pojo.src.main		# Pojo【简单的java对象】
│   ├── java.run.hxtia.pubwork
│   │   ├── mapStruct	# 类型转换
│   │   ├── pojo
│   │   │   ├── dto		# 包装后的类
│   │   │   ├── po		# 数据库类
│   │   │   └── vo		# 和前端打交道的类
│   │   │       ├── request		#请求参数
│   │   │       │   ├── page.base	# 分页使用的类
│   │   │       │   └── save	# 保存的类
│   │   │       ├── response	# 响应的类
│   │   │       └── result		# 包装参数
│   │   └── validator		# 后端校验
│   └── resources	
└── hxtia-wx-api.src.main	# 微信小程序的Api
    ├── java
    └── resources
```
