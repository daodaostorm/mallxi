Create Database If Not Exists mallxidb Character Set UTF8;
USE mallxidb;
CREATE TABLE if not exists `clientuser` (
  `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `userid` varchar(64) NOT NULL UNIQUE KEY,
  `username` varchar(50) NOT NULL ,
  `password` varchar(50) NOT NULL ,
  `phone` varchar(20) DEFAULT "",
  `question` varchar(100) DEFAULT NULL DEFAULT "",
  `answer` varchar(100) DEFAULT NULL DEFAULT "",
  `role` int(4) DEFAULT 0,
  `status` int(4) DEFAULT 0,
  `lastlogintime` varchar(32) NOT NULL DEFAULT "",
  `createtime` varchar(32) NOT NULL DEFAULT "",
  `updatetime` varchar(32) NOT NULL DEFAULT "",
  `remark` varchar(256) DEFAULT ""
) ENGINE=InnoDB DEFAULT CHARSET=utf8;