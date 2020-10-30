Create Database If Not Exists mallxidb Character Set UTF8;
USE mallxidb;
CREATE TABLE if not exists `good_info` (
  `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `proid` varchar(64) NOT NULL UNIQUE KEY,
  `cateid` varchar(64) NOT NULL DEFAULT "",
  `name` varchar(100) NOT NULL DEFAULT "",
  `subtitle` varchar(200) NOT NULL DEFAULT "",
  `mainimage` varchar(200) NOT NULL DEFAULT "",
  `detailpic1` varchar(200) NOT NULL DEFAULT "",
  `detailpic2` varchar(200) NOT NULL DEFAULT "",
  `detailpic3` varchar(200) NOT NULL DEFAULT "",
  `detailpic4` varchar(200) NOT NULL DEFAULT "",
  `detail` varchar(1000) NOT NULL DEFAULT "",
  `price` decimal(20,2) NOT NULL DEFAULT 0.00,
  `orginprice` decimal(20,2) NOT NULL DEFAULT 0.00,
  `recommend` varchar(32) NOT NULL DEFAULT "0",
  `stock` int(11) NOT NULL DEFAULT 0,
  `status` int(6) DEFAULT 0,
  `updateby` int(16) DEFAULT 0,
  `createtime` varchar(32) NOT NULL DEFAULT "",
  `updatetime` varchar(32) NOT NULL DEFAULT ""
) ENGINE=InnoDB DEFAULT CHARSET=utf8;