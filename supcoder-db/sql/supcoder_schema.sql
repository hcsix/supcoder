drop database if exists supcoder;
drop user if exists 'supcoder'@'%';
-- 支持emoji：需要mysql数据库参数： character_set_server=utf8mb4
create database supcoder default character set utf8mb4 collate utf8mb4_unicode_ci;
use supcoder;
create user 'supcoder'@'%' identified by 'supcoder123456';
grant all privileges on supcoder.* to 'supcoder'@'%';
flush privileges;