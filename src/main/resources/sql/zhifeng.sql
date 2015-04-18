/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhifeng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-04-02 15:24:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zf_address
-- ----------------------------
DROP TABLE IF EXISTS `zf_address`;
CREATE TABLE `zf_address` (
  `addressid` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `district` varchar(255) DEFAULT NULL COMMENT '县/区',
  `community` varchar(255) DEFAULT NULL COMMENT '乡镇/小区街道',
  `detail` varchar(255) DEFAULT NULL COMMENT '详细信息',
  `userid` int(11) DEFAULT NULL COMMENT '外键, 关联用户表',
  `type` int(11) DEFAULT NULL COMMENT '0关联user表，1关联agency表',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(5) DEFAULT '0' COMMENT '逻辑删除，0代表可用，1代表逻辑删除',
  PRIMARY KEY (`addressid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

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
  `companypicture` varchar(128) DEFAULT NULL COMMENT '营业执照照片',
  `companynum` varchar(20) DEFAULT NULL COMMENT '公司编号',
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
  `selfintroduce` varchar(100) DEFAULT NULL COMMENT '自我简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zf_agencyemployee
-- ----------------------------
DROP TABLE IF EXISTS `zf_agencyemployee`;
CREATE TABLE `zf_agencyemployee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `phone` varchar(20) NOT NULL COMMENT '手机',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `realname` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `idcard` varchar(20) DEFAULT NULL COMMENT '身份证',
  `idpicture` varchar(128) DEFAULT NULL COMMENT '身份证照片',
  `agencyid` int(11) DEFAULT NULL COMMENT '所属中介机构',
  `skillid` varchar(40) DEFAULT NULL COMMENT '擅长领域，逗号隔开',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  `head` varchar(128) DEFAULT NULL COMMENT '头像',
  `status` int(11) DEFAULT NULL COMMENT '用户状态',
  `introduce` varchar(100) DEFAULT NULL COMMENT '简介',
  `position` varchar(20) DEFAULT NULL COMMENT '职位职能',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(5) DEFAULT '0' COMMENT '逻辑删除，0代表可用，1代表逻辑删除',
  `salt` varchar(40) NOT NULL,
  `worktime` int(11) DEFAULT NULL,
  `identity` int(11) NOT NULL COMMENT '身份 1:律师 2:评估师 3:司法鉴定人4:商标代理人 5:专利代理',
  `weight` int(11) DEFAULT NULL COMMENT '权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zf_apply
-- ----------------------------
DROP TABLE IF EXISTS `zf_apply`;
CREATE TABLE `zf_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `needid` int(11) DEFAULT NULL COMMENT '对应的需求id',
  `userid` int(11) DEFAULT NULL COMMENT '对应的用户id',
  `employeeid` int(11) DEFAULT NULL COMMENT '对应的从业人员的id',
  `content` varchar(400) DEFAULT NULL COMMENT '申请的时候发送的信息',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zf_industryc
-- ----------------------------
DROP TABLE IF EXISTS `zf_industryc`;
CREATE TABLE `zf_industryc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `parentid` int(11) DEFAULT NULL COMMENT '父分类',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(5) DEFAULT '0' COMMENT '逻辑删除，0代表可用，1代表逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zf_order
-- ----------------------------
DROP TABLE IF EXISTS `zf_order`;
CREATE TABLE `zf_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `needid` int(11) DEFAULT NULL COMMENT '与需求表或者其他表关联的id',
  `agencyemployeeid` int(11) DEFAULT NULL COMMENT '中介机构从业人员的用户id',
  `status` int(11) NOT NULL COMMENT '状态 0没有中介机构接受 1已经被接受 2处理完毕 3处理失败',
  `type` varchar(255) DEFAULT NULL COMMENT '咨询类别',
  `servicerank` varchar(255) DEFAULT NULL COMMENT '律师服务评价',
  `timerank` varchar(255) DEFAULT NULL COMMENT '服务时间评价',
  `judge` varchar(255) DEFAULT NULL COMMENT '对订单的评价',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(5) DEFAULT '0' COMMENT '逻辑删除，0代表可用，1代表逻辑删除',
  `finishtime` datetime DEFAULT NULL COMMENT '订单完成时间',
  `accepttime` datetime DEFAULT NULL COMMENT '中介机构接受预定的时间',
  `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
  `userid` int(11) DEFAULT NULL COMMENT '发起订单的用户id',
  `ordertype` int(11) DEFAULT NULL COMMENT '0代表需求，1代表其他',
  `rank` int(11) DEFAULT NULL COMMENT '对整体服务的评价 1-5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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

-- ----------------------------
-- Table structure for zf_skillc
-- ----------------------------
DROP TABLE IF EXISTS `zf_skillc`;
CREATE TABLE `zf_skillc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `enable` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zf_store
-- ----------------------------
DROP TABLE IF EXISTS `zf_store`;
CREATE TABLE `zf_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '0代表个人关注中介机构，1代表个人关注中介机构从业人员，2代表中介机构人员关注中介机构，3代表中介机构人员关注中介机构人员',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `anencyid` int(11) DEFAULT NULL COMMENT '被收藏的中介机构或中介员工',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(5) DEFAULT '0' COMMENT '逻辑删除，0代表可用，1代表逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zf_user
-- ----------------------------
DROP TABLE IF EXISTS `zf_user`;
CREATE TABLE `zf_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) NOT NULL COMMENT '普通用户代表用户名，企业用户代表企业名称',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0为普通用户，1为企业用户',
  `status` int(11) DEFAULT '0' COMMENT '用户状态 0等待认证，1认证成功，2冻结  是否使用待定',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `salt` varchar(40) NOT NULL,
  `address` int(10) DEFAULT NULL COMMENT '地址，与address表关联',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `postcode` varchar(20) DEFAULT NULL COMMENT '邮编',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `realname` varchar(20) DEFAULT NULL COMMENT '普通用户代表真实姓名，企业用户代表联系人的真实姓名',
  `number` varchar(20) DEFAULT NULL COMMENT '普通用户代表身份证号，企业用户代表企业编号',
  `picture` varchar(128) DEFAULT NULL COMMENT '普通用户代表身份证照片，企业用户代表营业执照照片',
  `head` varchar(128) DEFAULT NULL COMMENT '头像照片',
  `introduction` varchar(100) DEFAULT NULL COMMENT '简介',
  `park` varchar(20) DEFAULT NULL,
  `industry` varchar(20) DEFAULT NULL COMMENT '行业',
  `attention` varchar(20) DEFAULT NULL COMMENT '所关注从技术或行业',
  `enabled` tinyint(5) DEFAULT '0' COMMENT '逻辑删除，0代表可用，1代表逻辑删除',
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `createtime` datetime DEFAULT NULL,
  `selfintroduce` varchar(200) DEFAULT NULL COMMENT '个性签名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
