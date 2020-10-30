Create Database If Not Exists mallxidb Character Set UTF8;
USE mallxidb;
CREATE TABLE if not exists `notice` (
      `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
      `title` varchar(160) NOT NULL DEFAULT "",
	  `createtime` varchar(32) NOT NULL DEFAULT "",
      `updatetime` varchar(32) NOT NULL DEFAULT "",
	  `status` int(6) DEFAULT 0,
	  `updateby` int(16) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
