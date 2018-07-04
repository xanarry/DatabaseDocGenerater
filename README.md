# 数据库文档生成器

程序从mysql数据库中读取库的信息和表的信息, 选择制定的库生成数据库文档, 数据库定义中的注释请使用comment关键词, 不然无法保存到文档中.

例如如下DDL
```
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
  `ID`             VARCHAR(20) PRIMARY KEY COMMENT '身份证号码',
  `name`           VARCHAR(10)  NOT NULL COMMENT '姓名',
  `loginName`      VARCHAR(20)  NOT NULL UNIQUE COMMENT '登录名',
  `email`          VARCHAR(254) COMMENT '邮箱',
  `phone`          VARCHAR(25) COMMENT '电话'
)DEFAULT charset = "utf8" ENGINE = InnoDB COMMENT='系统中的用户表';
```

将会生成如下文档:
![](https://raw.githubusercontent.com/xanarry/DatabaseDocGenerater/master/doc.png)


如果需要直接解析mysql DDL, 请实现DatabaseOpt借口, 解析后返回制定的数据结构即可
