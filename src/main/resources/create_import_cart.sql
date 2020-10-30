Create Database If Not Exists mallxidb Character Set UTF8;
USE mallxidb;
CREATE TABLE if not exists `cart` (
      `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
      `userid` varchar(64) NOT NULL DEFAULT "",
	  `proid` varchar(256) NOT NULL DEFAULT "",
	  `name` varchar(100) NOT NULL DEFAULT "",
	  `mainimage` varchar(200) NOT NULL DEFAULT "",
	  `quantity` int(6) DEFAULT 0,
	  `createtime` varchar(32) NOT NULL DEFAULT "",
      `updatetime` varchar(32) NOT NULL DEFAULT ""
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
