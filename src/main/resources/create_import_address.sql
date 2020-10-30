Create Database If Not Exists mallxidb Character Set UTF8;
USE mallxidb;
CREATE TABLE if not exists `address` (
  `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `userid` varchar(64) DEFAULT "",
  `label` varchar(64) DEFAULT "",
  `receivername` varchar(20) DEFAULT "",
  `receiverphone` varchar(20) DEFAULT "",
  `receiverprovince` varchar(20) DEFAULT "",
  `receivercity` varchar(20) DEFAULT "",
  `receiverdistrict` varchar(20) DEFAULT "",
  `receiveraddress` varchar(200) DEFAULT "",
  `createtime` varchar(32) NOT NULL DEFAULT "",
  `updatetime` varchar(32) NOT NULL DEFAULT ""
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
