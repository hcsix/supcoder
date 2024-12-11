DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`              bigint(20)   NOT NULL AUTO_INCREMENT,
    `username`        varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
    `password`        varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
    `email`           varchar(255) NOT NULL DEFAULT '' COMMENT '电子邮件',
    `name`            varchar(255)          DEFAULT NULL COMMENT '姓名',
    `avatar`          varchar(255)          DEFAULT NULL COMMENT '头像URL',
    `signature`       varchar(255)          DEFAULT NULL COMMENT '签名',
    `title`           varchar(255)          DEFAULT NULL COMMENT '职位',
    `group`           varchar(255)          DEFAULT NULL COMMENT '组',
    `notify_count`    int                   DEFAULT NULL COMMENT '通知数量',
    `unread_count`    int                   DEFAULT NULL COMMENT '未读消息数量',
    `country`         varchar(255)          DEFAULT NULL COMMENT '国家',
    `level`           varchar(255)          DEFAULT NULL COMMENT '等级',
    `role_ids`        varchar(127)          DEFAULT '[]' COMMENT '角色列表',
    `address`         varchar(255)          DEFAULT NULL COMMENT '地址',
    `phone`           varchar(255)          DEFAULT NULL COMMENT '电话',
    `add_time`        datetime              DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime              DEFAULT NULL COMMENT '更新时间',
    `last_login_time` datetime              DEFAULT NULL COMMENT '最近一次登录时间',
    `last_login_ip`   varchar(63)           DEFAULT '' COMMENT '最近一次登录IP地址',
    `deleted`         tinyint(1)            DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';


DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `role_id`     int(11)     DEFAULT NULL COMMENT '角色ID',
    `permission`  varchar(63) DEFAULT NULL COMMENT '权限',
    `add_time`    datetime    DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
    `deleted`     tinyint(1)  DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 32
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限表';



DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT,
    `name`        varchar(63) NOT NULL COMMENT '角色名称',
    `desc`        varchar(1023) DEFAULT NULL COMMENT '角色描述',
    `enabled`     tinyint(1)    DEFAULT '1' COMMENT '是否启用',
    `add_time`    datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '更新时间',
    `deleted`     tinyint(1)    DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';


DROP TABLE IF EXISTS `lottery_type`;
CREATE TABLE `lottery_type`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT,
    `name`        varchar(63) NOT NULL COMMENT '彩票名称',
    `description` varchar(255) DEFAULT NULL COMMENT '彩票描述',
    `enabled`     tinyint(1)    DEFAULT '1' COMMENT '是否启用',
    `latest_issue_number` varchar(20) DEFAULT NULL COMMENT '最新开奖期数',
    `latest_draw_result`  varchar(255) DEFAULT NULL COMMENT '最新开奖结果',
    `add_time`    datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '更新时间',
    `deleted`     tinyint(1)    DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='彩票类型表';

DROP TABLE IF EXISTS `lottery_daletou`;
CREATE TABLE `lottery_daletou`
(
    `id`               bigint(20)   NOT NULL AUTO_INCREMENT,
    `issue_number`     varchar(20)  NOT NULL COMMENT '期号',
    `red_balls`        varchar(255) NOT NULL COMMENT '红球号码（逗号分隔）',
    `blue_balls`       varchar(255) NOT NULL COMMENT '蓝球号码（逗号分隔）',
    `draw_date`        datetime     NOT NULL COMMENT '开奖日期',
    `jackpot_amount`   decimal(15, 2) DEFAULT NULL COMMENT '当期奖池金额',
    `first_prize`      int          DEFAULT NULL COMMENT '一等奖人数',
    `second_prize`     int          DEFAULT NULL COMMENT '二等奖人数',
    `third_prize`      int          DEFAULT NULL COMMENT '三等奖人数',
    `fourth_prize`     int          DEFAULT NULL COMMENT '四等奖人数',
    `fifth_prize`      int          DEFAULT NULL COMMENT '五等奖人数',
    `sixth_prize`      int          DEFAULT NULL COMMENT '六等奖人数',
    `seventh_prize`    int          DEFAULT NULL COMMENT '七等奖人数',
    `add_time`         datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '更新时间',
    `deleted`          tinyint(1)   DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `issue_number_UNIQUE` (`issue_number`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='大乐透开奖结果表';




DROP TABLE IF EXISTS `lottery_shuangseqiu`;
CREATE TABLE `lottery_shuangseqiu`
(
    `id`               bigint(20)   NOT NULL AUTO_INCREMENT,
    `issue_number`     varchar(20)  NOT NULL COMMENT '期号',
    `red_balls`        varchar(255) NOT NULL COMMENT '红球号码（逗号分隔）',
    `blue_balls`       varchar(255) NOT NULL COMMENT '蓝球号码（逗号分隔）',
    `draw_date`        datetime     NOT NULL COMMENT '开奖日期',
    `jackpot_amount`   decimal(15, 2) DEFAULT NULL COMMENT '当期奖池金额',
    `first_prize`      int          DEFAULT NULL COMMENT '一等奖人数',
    `second_prize`     int          DEFAULT NULL COMMENT '二等奖人数',
    `third_prize`      int          DEFAULT NULL COMMENT '三等奖人数',
    `fourth_prize`     int          DEFAULT NULL COMMENT '四等奖人数',
    `fifth_prize`      int          DEFAULT NULL COMMENT '五等奖人数',
    `sixth_prize`      int          DEFAULT NULL COMMENT '六等奖人数',
    `seventh_prize`    int          DEFAULT NULL COMMENT '七等奖人数',
    `add_time`         datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime     DEFAULT NULL COMMENT '更新时间',
    `deleted`          tinyint(1)   DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `issue_number_UNIQUE` (`issue_number`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='双色球开奖结果表';
