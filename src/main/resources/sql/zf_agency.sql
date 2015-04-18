/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : zhifeng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-03-24 15:01:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zf_agency
-- ----------------------------
DROP TABLE IF EXISTS `zf_agency`;
CREATE TABLE `zf_agency` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `agencytype` varchar(100) DEFAULT NULL COMMENT '类型(多选用,隔开)：0-律师事务所，1-知识产权代理公司，3-知识产权培训公司，4-知识产权司法鉴定公司',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `status` int(11) DEFAULT NULL COMMENT '用户状态  0等待认证，1认证成功，2冻结  是否使用待定',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `postcode` varchar(20) DEFAULT NULL COMMENT '邮编',
  `fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `realname` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `idcard` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `idpicture` varchar(128) DEFAULT NULL COMMENT '身份证图片',
  `companynum` varchar(20) DEFAULT NULL COMMENT '机构全称',
  `companypicture` varchar(128) DEFAULT NULL COMMENT '营业执照照片',
  `city` varchar(20) DEFAULT NULL COMMENT '所在城市',
  `address` int(20) DEFAULT NULL COMMENT '地址 与address表关联',
  `companyweb` varchar(40) DEFAULT NULL COMMENT '公司网址',
  `service` varchar(100) DEFAULT NULL COMMENT '提供的服务，逗号隔开',
  `head` varchar(128) DEFAULT NULL COMMENT '头像照片',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(5) DEFAULT '0' COMMENT '逻辑删除，0代表可用，1代表逻辑删除',
  `parentid` int(11) DEFAULT NULL COMMENT '空表示总部不为空表示分部',
  `introduce` varchar(400) DEFAULT NULL COMMENT '公司简介',
  `teamintroduce` varchar(400) DEFAULT NULL COMMENT '团队简介',
  `teampurpose` varchar(400) DEFAULT NULL COMMENT '团队宗旨',
  `reservation` varchar(400) DEFAULT NULL COMMENT '预约规则',
  `salt` varchar(40) DEFAULT NULL,
  `selfintroduce` varchar(255) DEFAULT NULL COMMENT '简介，类似于个性签名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
