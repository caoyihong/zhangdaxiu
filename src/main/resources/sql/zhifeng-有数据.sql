/*
Navicat MySQL Data Transfer

Source Server         : sharework
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhifeng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-03-18 16:16:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zfarticle
-- ----------------------------
DROP TABLE IF EXISTS `zfarticle`;
CREATE TABLE `zfarticle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zfarticle
-- ----------------------------
INSERT INTO `zfarticle` VALUES ('1', '8', 'tese', 'test1');
INSERT INTO `zfarticle` VALUES ('2', '8', 'tese', 'tet');
INSERT INTO `zfarticle` VALUES ('3', '8', 'dede', 'dede');

-- ----------------------------
-- Table structure for zfuser
-- ----------------------------
DROP TABLE IF EXISTS `zfuser`;
CREATE TABLE `zfuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zfuser
-- ----------------------------
INSERT INTO `zfuser` VALUES ('8', '111', '1234');
INSERT INTO `zfuser` VALUES ('13', '1111', '1234');

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
  `userid` int(11) NOT NULL COMMENT '外键, 关联用户表',
  `type` int(11) DEFAULT NULL COMMENT '0关联user表，1关联agency表',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(5) DEFAULT '0' COMMENT '逻辑删除，0代表可用，1代表逻辑删除',
  PRIMARY KEY (`addressid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_address
-- ----------------------------
INSERT INTO `zf_address` VALUES ('1', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');

-- ----------------------------
-- Table structure for zf_agency
-- ----------------------------
DROP TABLE IF EXISTS `zf_agency`;
CREATE TABLE `zf_agency` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `agencytype` varchar(10) DEFAULT NULL COMMENT '类型(多选用,隔开)：0-律师事务所，1-知识产权代理公司，3-知识产权培训公司，4-知识产权司法鉴定公司',
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
  `companyname` varchar(20) DEFAULT NULL COMMENT '机构全称',
  `companypicture` varchar(128) DEFAULT NULL COMMENT '营业执照照片',
  `organizationtype` varchar(20) DEFAULT NULL COMMENT '机构类型',
  `city` varchar(20) DEFAULT NULL COMMENT '所在城市',
  `address` int(20) DEFAULT NULL COMMENT '地址 与address表关联',
  `companyweb` varchar(40) DEFAULT NULL COMMENT '公司网址',
  `service` varchar(20) DEFAULT NULL COMMENT '提供的服务，逗号隔开',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_agency
-- ----------------------------
INSERT INTO `zf_agency` VALUES ('2', '知识产权评估公司', 'test', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', '金杜律师事务所(北京总部)', 'images/jindu.png', '知识产权评估公司', 'changzhou', '1', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-16 15:51:24', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou');
INSERT INTO `zf_agency` VALUES ('3', '知识产权评估公司', 'test', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', '金杜律师事务所(北京总部)', 'images/jindu.png', '知识产权评估公司', 'changzhou', '1', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-18 10:52:52', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_agencyemployee
-- ----------------------------
INSERT INTO `zf_agencyemployee` VALUES ('6', 'dedede', '111', '123', '545286400@qq.com', '王琳', '320911199106174933', 'images/jindu.png', '2', '15', '常州', 'images/user-images/jindu.png', '0', '我爱中国人', '一级律师', '2015-03-16 15:56:11', '2015-03-18 09:10:29', '0', '1111', '4', '1', '10');
INSERT INTO `zf_agencyemployee` VALUES ('7', 'cloudhaha', '18551035924', '77d1f38728ebb354aeef3e0f7899c729', '545286400@qq.com', '虚妄', '320911199106174933', 'images/jindu.png', '2', '1', '南京', 'images/user-images/jindu.png', '0', '我爱中国人', '一级律师', '2015-03-16 15:56:14', '2015-03-18 15:56:44', '0', '01ad0a0e58f1b4192790371eb6b0ed56', '4', '2', '9');
INSERT INTO `zf_agencyemployee` VALUES ('11', 'cloudhaha', '18551035924', '77d1f38728ebb354aeef3e0f7899c729', '545286400@qq.com', '虚妄', '320911199106174933', 'images/jindu.png', '2', '1', '南京', 'images/user-images/jindu.png', '0', '我爱中国人', '一级律师', '2015-03-16 15:56:14', '2015-03-18 15:57:20', '0', '01ad0a0e58f1b4192790371eb6b0ed56', '4', '2', '9');

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
-- Records of zf_industryc
-- ----------------------------

-- ----------------------------
-- Table structure for zf_need
-- ----------------------------
DROP TABLE IF EXISTS `zf_need`;
CREATE TABLE `zf_need` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '需求类别',
  `name` varchar(255) DEFAULT NULL COMMENT '发布人名称',
  `title` varchar(255) DEFAULT NULL COMMENT '项目名称，标题',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_need
-- ----------------------------

-- ----------------------------
-- Table structure for zf_order
-- ----------------------------
DROP TABLE IF EXISTS `zf_order`;
CREATE TABLE `zf_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '个人用户/企业用户的用户id',
  `agencyemployeeid` int(11) DEFAULT NULL COMMENT '中介机构从业人员的用户id',
  `status` int(11) NOT NULL COMMENT '状态 0没有中介机构接受 1已经被接受 2处理完毕',
  `type` varchar(255) DEFAULT NULL COMMENT '咨询类别',
  `servicerank` varchar(255) DEFAULT NULL COMMENT '律师服务评价',
  `timerank` varchar(255) DEFAULT NULL COMMENT '服务时间评价',
  `judge` varchar(255) DEFAULT NULL COMMENT '对订单的评价',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(5) DEFAULT '0' COMMENT '逻辑删除，0代表可用，1代表逻辑删除',
  `finishtime` datetime DEFAULT NULL COMMENT '订单完成时间',
  `accepttime` datetime DEFAULT NULL COMMENT '中介机构接受预定的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_order
-- ----------------------------
INSERT INTO `zf_order` VALUES ('1', '22', '6', '2', '版权', '满意', '快', '很好', '2015-03-16 15:58:15', '2015-03-18 09:11:51', '0', '2015-03-16 15:58:08', '2015-03-16 15:58:12');
INSERT INTO `zf_order` VALUES ('2', '22', '6', '2', '得得得', '满意', '快', '很好', '2015-03-16 15:58:17', '2015-03-16 16:07:41', '0', '2015-03-16 15:58:10', '2015-03-16 15:58:13');

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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_skillc
-- ----------------------------
INSERT INTO `zf_skillc` VALUES ('1', null, '律师', '2015-03-13 14:13:52', '2015-03-13 14:13:55', null);
INSERT INTO `zf_skillc` VALUES ('2', null, '评估师', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('3', null, '司法鉴定人', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('4', null, '商标代理人', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('5', null, '专利代理', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('6', '1', '专利', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('7', '1', '商标', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('8', '1', '版权', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('9', '1', '软件', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('10', '1', '商业秘密', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('11', '1', '反不当竞争', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('12', '1', '地理标志', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('13', '1', '域名争议', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('14', '2', '知识产权评估', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('15', '3', '司法鉴定', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('16', '4', '代理商标', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('17', '5', '机械', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('18', '5', '光电', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('19', '5', '通信', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('20', '5', '计算机及软件', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('21', '5', '化学', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('22', '5', '生物医药', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);
INSERT INTO `zf_skillc` VALUES ('23', '5', '材料', '2015-03-13 14:13:52', '2015-03-13 14:13:52', null);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_store
-- ----------------------------

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
  `address` varchar(10) DEFAULT NULL COMMENT '地址，与address表关联',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `postcode` varchar(20) DEFAULT NULL COMMENT '邮编',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `realname` varchar(20) DEFAULT NULL COMMENT '普通用户代表真实姓名，企业用户代表联系人的真实姓名',
  `number` varchar(20) DEFAULT NULL COMMENT '普通用户代表身份证号，企业用户代表企业编号',
  `picture` varchar(128) DEFAULT NULL COMMENT '普通用户代表身份证照片，企业用户代表营业执照照片',
  `head` varchar(128) DEFAULT NULL COMMENT '头像照片',
  `introduction` varchar(100) DEFAULT NULL COMMENT '简介',
  `industry` varchar(20) DEFAULT NULL COMMENT '行业',
  `attention` varchar(20) DEFAULT NULL COMMENT '所关注从技术或行业',
  `createtime` datetime DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(5) DEFAULT '0' COMMENT '逻辑删除，0代表可用，1代表逻辑删除',
  `salt` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_user
-- ----------------------------
INSERT INTO `zf_user` VALUES ('20', 'cloudha', '0', '0', '10a3caf7617e15dcb0f83681b962f12c', null, null, null, '18551035924', null, null, null, null, 'images/user-images/jindu.png', null, null, null, null, '2015-03-16 16:10:54', '0', 'c37976ad34f32a71bb55b6214eec6ba5');
INSERT INTO `zf_user` VALUES ('21', 'dededede', '1', '0', '131a032b545b5c0bb4e70b881c6c06cc', null, null, null, '18551035924', null, null, '', null, 'images/user-images/jindu.png', null, 'adedede', null, null, '2015-03-16 16:10:52', '0', '6e899d08e7cae9f43d62ac2612aca278');
INSERT INTO `zf_user` VALUES ('22', 'dede2efr', '1', '0', 'f451b9b25d748bcb10c400613ce32003', null, null, null, '18551035923', null, null, null, null, 'images/user-images/jindu.png', null, '111222', null, null, '2015-03-16 16:09:37', '0', 'd12fd7ddf077e81844989638d524e83a');
INSERT INTO `zf_user` VALUES ('23', 'dededee', '0', '0', '66c52e9e9c1b2b2fa230b1c37503fe62', null, null, null, '18551035912', null, null, null, null, null, null, null, null, null, null, '0', 'e82ed0bda24df4588662b2fa0a8684f9');
INSERT INTO `zf_user` VALUES ('24', 'dedede', '0', '0', 'fef10be3532177dc5e63edc9b2d6a74a', null, null, null, '18521035924', null, null, null, null, null, null, null, null, null, null, '0', '1ce64d7e1024723ab00fac56f5b8abc0');
INSERT INTO `zf_user` VALUES ('25', 'dedeqqq', '0', '0', 'b06dfa23da29cafc19138ed69ca085c7', null, null, null, '18551235924', null, null, null, null, null, null, null, null, null, null, '0', 'e4f41bcd620762fdc391251843ad419f');
