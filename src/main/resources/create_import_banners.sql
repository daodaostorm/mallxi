Create Database If Not Exists mallxidb Character Set UTF8;
USE mallxidb;
CREATE TABLE if not exists `banner_info` (
      `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	  `proid` varchar(64) NOT NULL DEFAULT "",
	  `title` varchar(100) NOT NULL DEFAULT "",
	  `pic_url` varchar(160) DEFAULT NULL,
	  `createtime` varchar(32) NOT NULL DEFAULT "",
      `updatetime` varchar(32) NOT NULL DEFAULT "",
	  `status` int(6) DEFAULT 0,
	  `updateby` int(16) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
