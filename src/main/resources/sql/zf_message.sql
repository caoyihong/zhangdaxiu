/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhifeng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-04-02 15:26:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zf_message
-- ----------------------------
DROP TABLE IF EXISTS `zf_message`;
CREATE TABLE `zf_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `needid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `employeeid` int(11) DEFAULT NULL,
  `content` varchar(400) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0代表用户发给中介机构从业人员，1代表中介机构从业人员回复个人',
  `isread` tinyint(4) DEFAULT NULL COMMENT '是否已读，0未读，1已读',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
