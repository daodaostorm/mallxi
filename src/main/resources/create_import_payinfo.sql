Create Database If Not Exists mallxidb Character Set UTF8;
USE mallxidb;
CREATE TABLE if not exists `payinfo` (
  `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `payid` varchar(64) NOT NULL DEFAULT "",
  `orderid` varchar(64) NOT NULL DEFAULT "",
  `userid` varchar(64) NOT NULL DEFAULT "",
  `payplatform` int(10) NOT NULL DEFAULT 0,
  `platformnumber` varchar(200) NOT NULL DEFAULT "",
  `platformstatus` varchar(20) NOT NULL DEFAULT "",
  `status` int(10) NOT NULL DEFAULT 0,
  `createtime` varchar(32) NOT NULL DEFAULT "",
  `updatetime` varchar(32) NOT NULL DEFAULT ""
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
