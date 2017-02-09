create table fatch_on (
     id bigint(20) NOT NULL COMMENT '逻辑主键',
     fatch_on bigint(8) NULL COMMENT '测试唯一性约束',

     PRIMARY KEY (id),
     unique(fatch_on)
);