DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT,
    `username`     varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
    `password`     varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
    `email`        varchar(255) NOT NULL DEFAULT '' COMMENT '电子邮件',
    `name`         varchar(255)          DEFAULT NULL COMMENT '姓名',
    `avatar`       varchar(255)          DEFAULT NULL COMMENT '头像URL',
    `signature`    varchar(255)          DEFAULT NULL COMMENT '签名',
    `title`        varchar(255)          DEFAULT NULL COMMENT '职位',
    `group`        varchar(255)          DEFAULT NULL COMMENT '组',
    `notify_count` int                   DEFAULT NULL COMMENT '通知数量',
    `unread_count` int                   DEFAULT NULL COMMENT '未读消息数量',
    `country`      varchar(255)          DEFAULT NULL COMMENT '国家',
    `access`       varchar(255)          DEFAULT NULL COMMENT '访问权限',
    `address`      varchar(255)          DEFAULT NULL COMMENT '地址',
    `phone`        varchar(255)          DEFAULT NULL COMMENT '电话',
    `add_time`     datetime              DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime              DEFAULT NULL COMMENT '更新时间',
    `deleted`      tinyint(1)            DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';
