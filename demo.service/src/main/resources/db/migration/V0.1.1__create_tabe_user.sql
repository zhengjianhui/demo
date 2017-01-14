create table user (
  id bigint(20) not null comment '逻辑主键',
  userName VARCHAR(30) comment '用户名',
  loginName VARCHAR(30) comment '逻辑主键',
  password VARCHAR(60) comment '密码',

  PRIMARY KEY (id)
);

create table role (
  `no` varchar(20) NOT NULL COMMENT '角色号',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `block_id` bigint(20) DEFAULT NULL COMMENT '小区编号',
  `property_id` bigint(20) DEFAULT NULL COMMENT '物业公司编号',
  PRIMARY KEY (`no`)
);

CREATE TABLE `user_authority` (
  `user_id` bigint(20) NOT NULL COMMENT '员工号',
  `role_no` varchar(50) NOT NULL COMMENT '角色号',
  UNIQUE KEY user_id (`user_id`,`role_no`)
);

CREATE TABLE permissions (
  `role_no` varchar(20) NOT NULL COMMENT '角色no',
  `permissions_no` varchar(50) NOT NULL COMMENT '功能no',
  PRIMARY KEY (`role_no`,`permissions_no`)
);


