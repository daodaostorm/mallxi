Create Database If Not Exists mallxidb Character Set UTF8;
USE mallxidb;
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
