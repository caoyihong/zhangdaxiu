/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : zhifeng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-03-20 15:53:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zf_problem
-- ----------------------------
DROP TABLE IF EXISTS `zf_problem`;
CREATE TABLE `zf_problem` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(255) DEFAULT NULL COMMENT '难题类别',
  `userid` int(11) DEFAULT NULL COMMENT '发布人id',
  `title` varchar(255) DEFAULT NULL COMMENT '难题名称',
  `address` int(11) DEFAULT NULL COMMENT '地址',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `contacts` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(255) DEFAULT NULL COMMENT '传真',
  `postcode` varchar(255) DEFAULT NULL COMMENT '邮编',
  `detail` varchar(255) DEFAULT NULL COMMENT '需求详情',
  `finishtime` datetime DEFAULT NULL COMMENT '要求完成时间',
  `endtime` datetime DEFAULT NULL COMMENT '投标截止时间',
  `economicindicators` varchar(255) DEFAULT NULL COMMENT '主要技术经济指标',
  `resultform` varchar(255) DEFAULT NULL COMMENT '成果形式（归纳）',
  `resultowner` varchar(255) DEFAULT NULL COMMENT '成果所有权归属',
  `fundoffered` varchar(255) DEFAULT NULL COMMENT '提供资金',
  `keywords` varchar(255) DEFAULT NULL COMMENT '关键词：多个以逗号隔开',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enabled` tinyint(5) DEFAULT '0' COMMENT '逻辑删除，0代表可用，1代表逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
