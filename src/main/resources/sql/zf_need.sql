/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhifeng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-03-19 13:51:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zf_need
-- ----------------------------
DROP TABLE IF EXISTS `zf_need`;
CREATE TABLE `zf_need` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL COMMENT '需求类别',
  `userid` int(255) DEFAULT NULL COMMENT '发布人id',
  `title` varchar(255) DEFAULT NULL COMMENT '项目名称，标题',
  `address` int(255) DEFAULT NULL COMMENT '地址',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `contacts` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机',
  `fax` varchar(255) DEFAULT NULL COMMENT '传真',
  `postcode` varchar(255) DEFAULT NULL COMMENT '邮编',
  `detail` varchar(255) DEFAULT NULL COMMENT '详情',
  `price` decimal(10,0) DEFAULT NULL COMMENT '经济指标',
  `resultform` varchar(255) DEFAULT NULL COMMENT '成果形式',
  `finishtime` datetime DEFAULT NULL COMMENT '要求完成时间',
  `endtime` datetime DEFAULT NULL COMMENT '招标结束时间',
  `ownership` varchar(255) DEFAULT NULL COMMENT '成果所有权归属',
  `fund` varchar(255) DEFAULT NULL COMMENT '提供资金',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键词',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(5) DEFAULT '0' COMMENT '逻辑删除，0代表可用，1代表逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
