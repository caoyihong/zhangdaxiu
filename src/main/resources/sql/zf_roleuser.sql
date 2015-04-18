/*
Navicat MySQL Data Transfer

Source Server         : sharework
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhifeng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-04-01 08:38:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zf_roleuser
-- ----------------------------
DROP TABLE IF EXISTS `zf_roleuser`;
CREATE TABLE `zf_roleuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` int(11) NOT NULL COMMENT '角色id',
  `user` int(11) NOT NULL COMMENT '用户id',
  `usertype` int(11) NOT NULL COMMENT '用户类型',
  `enabled` tinyint(4) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
