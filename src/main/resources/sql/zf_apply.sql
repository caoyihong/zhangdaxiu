/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhifeng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-03-27 15:16:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zf_apply
-- ----------------------------
DROP TABLE IF EXISTS `zf_apply`;
CREATE TABLE `zf_apply` (
  `id` int(11) NOT NULL,
  `needid` int(11) DEFAULT NULL COMMENT '对应的需求id',
  `userid` int(11) DEFAULT NULL COMMENT '对应的用户id',
  `employeeid` int(11) DEFAULT NULL COMMENT '对应的从业人员的id',
  `content` varchar(400) DEFAULT NULL COMMENT '申请的时候发送的信息',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
