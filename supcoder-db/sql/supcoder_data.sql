INSERT INTO `user` (`username`,
                    `password`,
                    `email`,
                    `name`,
                    `avatar`,
                    `signature`,
                    `title`,
                    `group`,
                    `notify_count`,
                    `unread_count`,
                    `country`,
                    `level`,
                    `role_ids`,
                    `address`,
                    `phone`,
                    `add_time`,
                    `update_time`,
                    `last_login_time`,
                    `last_login_ip`,
                    `deleted`)
VALUES ('admin',
        'admin', -- 实际应用中应使用加密后的密码
        'supcoder.lee@gmail.com',
        'Administrator',
        'https://example.com/avatar.png',
        'Welcome to Supcoder Hub!',
        'Admin',
        'AdminGroup',
        0,
        0,
        'China',
        '9',
        '[2]',
        'Beijing, China',
        '1234567890',
        NOW(),
        NOW(),
        NOW(),
        '127.0.0.1',
        0);


LOCK TABLES `role` WRITE;
INSERT INTO `role`
VALUES (1, '超级管理员', '所有模块的权限', 1, '2019-01-01 00:00:00', '2019-01-01 00:00:00', 0),
       (2, '商场管理员', '只有商场模块的操作权限', 1, '2019-01-01 00:00:00', '2019-01-07 15:15:12', 0),
       (3, '推广管理员', '只有推广模块的操作权限', 1, '2019-01-01 00:00:00', '2019-01-07 15:15:24', 0);
UNLOCK TABLES;


LOCK TABLES `permission` WRITE;

INSERT INTO `permission`
VALUES (1, 1, '*', '2019-01-01 00:00:00', '2019-01-01 00:00:00', 0),
       (2, 2, 'admin:category:read', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0),
       (3, 2, 'admin:category:update', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0),
       (4, 2, 'admin:category:delete', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0),
       (5, 2, 'admin:category:create', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0),
       (6, 2, 'admin:category:list', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0),
       (7, 2, 'admin:brand:create', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0),
       (8, 2, 'admin:brand:list', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0),
       (9, 2, 'admin:brand:delete', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0),
       (10, 2, 'admin:brand:read', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0),
       (11, 2, 'admin:brand:update', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0),
       (12, 3, 'admin:ad:list', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (13, 3, 'admin:ad:delete', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (14, 3, 'admin:ad:create', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (15, 3, 'admin:ad:update', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (16, 3, 'admin:ad:read', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (17, 3, 'admin:groupon:list', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (18, 3, 'admin:groupon:update', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (19, 3, 'admin:groupon:create', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (20, 3, 'admin:groupon:read', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (21, 3, 'admin:groupon:delete', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (22, 3, 'admin:topic:create', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (23, 3, 'admin:topic:read', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (24, 3, 'admin:topic:list', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (25, 3, 'admin:topic:delete', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (26, 3, 'admin:topic:update', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (27, 3, 'admin:coupon:list', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (28, 3, 'admin:coupon:delete', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (29, 3, 'admin:coupon:read', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (30, 3, 'admin:coupon:create', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0),
       (31, 3, 'admin:coupon:update', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
UNLOCK TABLES;


INSERT INTO `lottery_daletou` (`issue_number`, `red_balls`, `blue_balls`, `draw_date`, `jackpot_amount`, `first_prize`, `second_prize`, `third_prize`, `fourth_prize`, `fifth_prize`, `sixth_prize`, `seventh_prize`, `add_time`, `update_time`, `deleted`)
VALUES
    ('2023123', '01, 23, 34, 45, 56', '02, 07', '2023-10-01 18:30:00', 123456789.00, 1, 5, 20, 100, 500, 1000, 2000, NOW(), NOW(), 0),
    ('2023124', '03, 15, 22, 37, 49', '04, 09', '2023-10-08 18:30:00', 134567890.00, 2, 6, 25, 110, 550, 1100, 2100, NOW(), NOW(), 0),
    ('2023125', '05, 18, 26, 39, 51', '05, 10', '2023-10-15 18:30:00', 145678901.00, 0, 7, 30, 120, 600, 1200, 2200, NOW(), NOW(), 0);

INSERT INTO `lottery_shuangseqiu` (`issue_number`, `red_balls`, `blue_balls`, `draw_date`, `jackpot_amount`, `first_prize`, `second_prize`, `third_prize`, `fourth_prize`, `fifth_prize`, `sixth_prize`, `seventh_prize`, `add_time`, `update_time`, `deleted`)
VALUES
    ('2023123', '02, 14, 27, 33, 41, 53', '03, 11', '2023-10-02 18:30:00', 156789012.00, 1, 5, 20, 100, 500, 1000, 2000, NOW(), NOW(), 0),
    ('2023124', '04, 16, 28, 34, 42, 54', '04, 12', '2023-10-09 18:30:00', 167890123.00, 2, 6, 25, 110, 550, 1100, 2100, NOW(), NOW(), 0),
    ('2023125', '06, 17, 29, 35, 43, 55', '05, 13', '2023-10-16 18:30:00', 178901234.00, 0, 7, 30, 120, 600, 1200, 2200, NOW(), NOW(), 0);

INSERT INTO `lottery_type` (`name`, `description`, `enabled`, `latest_issue_number`, `latest_draw_result`, `add_time`, `update_time`, `deleted`)
VALUES
    ('dlt', '大乐透彩票', 1, '2023125', '05, 18, 26, 39, 51; 05, 10', NOW(), NOW(), 0),
    ('ssq', '双色球彩票', 1, '2023125', '06, 17, 29, 35, 43, 55; 05, 13', NOW(), NOW(), 0);



INSERT INTO `api_keys` (`access_key`, `secret_key`, `user_id`, `status`, `creation_date`, `last_used_date`, `service`, `permissions`) VALUES
                                                                                                                                          ('AK123', 'SK123', 1, 'active', '2024-12-12 10:00:00', NULL, 'lottery', 'read,write'),
                                                                                                                                          ('AK456', 'SK456', 2, 'inactive', '2024-12-12 11:00:00', NULL, 'lottery', 'read'),
                                                                                                                                          ('AK789', 'SK789', 3, 'active', '2024-12-12 12:00:00', NULL, 'lottery', 'write');


INSERT INTO `api_call_logs` (`key_id`, `user_id`, `service`, `request_method`, `request_url`, `request_date`, `response_status`) VALUES
                                                                                                                                     (1, 1, 'lottery', 'GET', '/api/service1/resource1', '2024-12-12 12:00:00', 200),
                                                                                                                                     (2, 2, 'lottery', 'POST', '/api/service2/resource2', '2024-12-12 13:00:00', 201),
                                                                                                                                     (3, 3, 'lottery', 'PUT', '/api/service3/resource3', '2024-12-12 14:00:00', 204);