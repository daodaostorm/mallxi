Create Database If Not Exists mallxidb Character Set UTF8;
USE mallxidb;
CREATE TABLE if not exists `users` (
      `id` bigint(16) NOT NULL AUTO_INCREMENT,
      `username` varchar(45) NOT NULL UNIQUE KEY,
      `password` varchar(100) NOT NULL,
	  `image` varchar(200) DEFAULT '',
      `age` varchar(8) DEFAULT '',
      `enabled` varchar(45) NOT NULL DEFAULT '1',
      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
INSERT INTO users(username, password, age, enabled) VALUES ('admin', '12345', '1', 1);

CREATE TABLE if not exists `authorities` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `username` varchar(45) NOT NULL,
      `authority` varchar(45) NOT NULL,
      PRIMARY KEY (`id`),
      UNIQUE KEY `username_UNIQUE` (`username`,`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

INSERT INTO authorities(username, authority) VALUES ('admin','ROLE_USER');
INSERT INTO authorities(username, authority) VALUES ('admin','ROLE_ADMIN');

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

CREATE TABLE if not exists `notice` (
      `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
      `title` varchar(160) NOT NULL DEFAULT "",
	  `createtime` varchar(32) NOT NULL DEFAULT "",
      `updatetime` varchar(32) NOT NULL DEFAULT "",
	  `status` int(6) DEFAULT 0,
	  `updateby` int(16) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

CREATE TABLE if not exists `orders` (
  `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `orderid` varchar(64) NOT NULL COMMENT '订单id',
  `userid` varchar(64) DEFAULT NULL COMMENT '用户id',
  `shoppingid` varchar(64) DEFAULT NULL,
  `fulladdress` varchar(256) DEFAULT NULL,
  `prodid` varchar(64) DEFAULT NULL,
  `prodname` varchar(64) DEFAULT NULL,
  `prodmainpic` varchar(256) DEFAULT NULL,
  `prodnum` int(10) DEFAULT NULL,
  `prodvalue` decimal(20,2) DEFAULT NULL,
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
  `paymenttype` int(4) DEFAULT NULL COMMENT '支付类型,1-在线支付',
  `postage` int(10) DEFAULT NULL COMMENT '运费,单位是元',
  `status` int(10) DEFAULT NULL COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
  `payid` varchar(64) DEFAULT NULL,
  `paymenttime` varchar(32) NOT NULL DEFAULT "",
  `sendtime` varchar(32) NOT NULL DEFAULT "",
  `endtime` varchar(32) NOT NULL DEFAULT "",
  `closetime` varchar(32) NOT NULL DEFAULT "",
  `createtime` varchar(32) NOT NULL DEFAULT "",
  `updatetime` varchar(32) NOT NULL DEFAULT ""
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

CREATE TABLE if not exists `essayinfo` (
  `id` bigint(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `proid` varchar(64) NOT NULL UNIQUE KEY,
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