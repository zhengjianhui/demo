create table md_archive (
    id bigint(20) NOT NULL COMMENT '逻辑主键',
    subject_type varchar(5) NOT NULL COMMENT '关联主体类型',
    subject_id bigint(20) NOT NULL COMMENT '关联主体id',
    file_name varchar(50) NOT NULL COMMENT '文件名',
    file_type varchar(10) DEFAULT NULL COMMENT '文件类型，后缀',
    url varchar(50) DEFAULT NULL COMMENT '地址',
    doubt_directory tinyint(1) DEFAULT NULL COMMENT '是否是目录',
    parent_id bigint(20) DEFAULT 0 COMMENT '父节点id，0为没有父节点',
    create_date datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    create_staff_id bigint(20) NOT NULL COMMENT '创建人id',
    file_size bigint(20) DEFAULT NULL COMMENT '文件大小',

    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件描述';


create table md_update_archive (
    id bigint(20) NOT NULL COMMENT '逻辑主键',
    url varchar(50) DEFAULT NULL COMMENT '地址',
    last_update_time datetime DEFAULT NULL COMMENT '最后修改时间',
    relation_update_staff_id bigint(20) NOT NULL COMMENT '修改人关联id',
    relation_archive_id bigint(20) NOT NULL COMMENT '关联文件主键',

    PRIMARY KEY (id),
    key idx_archive_id(relation_archive_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件历史';
