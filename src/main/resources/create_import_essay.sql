Create Database If Not Exists mallxidb Character Set UTF8;
USE mallxidb;
CREATE TABLE if not exists `essayinfo` (
  `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `essayid` varchar(64) NOT NULL UNIQUE KEY,
  `proid` varchar(64) NOT NULL DEFAULT "",
  `name` varchar(100) NOT NULL DEFAULT "",
  `subtitle` varchar(200) NOT NULL DEFAULT "",
  `author` varchar(200) NOT NULL DEFAULT "",
  `detailpic1` varchar(200) NOT NULL DEFAULT "",
  `detailpic2` varchar(200) NOT NULL DEFAULT "",
  `detailpic3` varchar(200) NOT NULL DEFAULT "",
  `detailpic4` varchar(200) NOT NULL DEFAULT "",
  `detailtext1` varchar(1000) NOT NULL DEFAULT "",
  `detailtext2` varchar(1000) NOT NULL DEFAULT "",
  `detailtext3` varchar(1000) NOT NULL DEFAULT "",
  `detailtext4` varchar(1000) NOT NULL DEFAULT "",
  `status` int(6) DEFAULT 0,
  `updateby` int(16) DEFAULT 0,
  `createtime` varchar(32) NOT NULL DEFAULT "",
  `updatetime` varchar(32) NOT NULL DEFAULT ""
) ENGINE=InnoDB DEFAULT CHARSET=utf8;