/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhifeng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-03-26 16:19:57
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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_address
-- ----------------------------
INSERT INTO `zf_address` VALUES ('1', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('2', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('3', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('4', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('5', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('6', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('7', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('8', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('9', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('10', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('11', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('12', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('13', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('14', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('15', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('16', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('17', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('18', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('19', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('20', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('21', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('22', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('23', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('24', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('25', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('26', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('27', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('28', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('29', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('30', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('31', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('32', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('33', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('34', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('35', '江苏省', '盐城市', 'dededede', 'dedededede', 'dedededede', '2', '1', null, '2015-03-17 16:30:39', '0');
INSERT INTO `zf_address` VALUES ('36', '0', '请选择', '请选择', null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('37', '北京市', '东城区', '东城区', null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('38', '北京市', '东城区', '请选择', null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('39', '浙江省', '台州市', '温岭市', null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('40', '请选择', null, null, null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('41', '北京市', '西城区', '西城区', null, '五千万', null, null, null, '2015-03-26 14:11:33', '0');
INSERT INTO `zf_address` VALUES ('42', '福建省', '厦门市', '湖里区', null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('43', '天津市', '和平区', '和平区', null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('44', '天津市', '河东区', '河东区', null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('45', '北京市', '东城区', '东城区', null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('46', '请选择', null, null, null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('47', '天津市', '南开区', '南开区', null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('48', '浙江省', '舟山市', '普陀区', null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('49', '天津市', '河东区', '河东区', null, null, null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('50', '上海市', '宝山区', '宝山区', null, 'asda是', null, null, null, '2015-03-26 14:11:27', '0');
INSERT INTO `zf_address` VALUES ('51', '北京市', '东城区', '东城区', null, '阿斯顿', null, null, null, '2015-03-26 14:11:30', '0');
INSERT INTO `zf_address` VALUES ('52', null, null, null, null, '啊沙发上', null, null, null, null, '0');
INSERT INTO `zf_address` VALUES ('53', null, null, null, null, '', '29', null, null, null, '0');
INSERT INTO `zf_address` VALUES ('54', null, null, null, null, 'qwsqwf', '29', null, null, null, '0');

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
-- Records of zf_agency
-- ----------------------------
INSERT INTO `zf_agency` VALUES ('2', '知识产权评估公司', '金杜培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '1', 'www.baidu.com', '知识产权代理,知识产权诉讼,商标诉讼,地理标志诉讼,知识产权评估', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:37:37', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('3', '知识产权代理公司,知识产权评估公司', '天涯代理公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王海', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '2', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:22:42', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('4', '知识产权培训公司', '阳光培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '3', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:22:44', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('5', '知识产权律师事务所,知识产权评估公司', '世贸律师事务所', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '4', 'www.baidu.com', '知识产权代理,知识产权诉讼,知识产权评估', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:37:39', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('6', '知识产权评估公司,知识产权培训公司', '哈哈培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '5', 'www.baidu.com', '知识产权代理,知识产权诉讼,知识产权评估', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:37:19', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('7', '知识产权评估公司', '民热评估公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '6', 'www.baidu.com', '知识产权代理,知识产权诉讼,知识产权培训,地理标志诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:22:50', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('8', '知识产权评估公司,知识产权代理公司', '恒生评估公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '7', 'www.baidu.com', '知识产权代理,知识产权诉讼,反不正当竞争诉讼,知识产权数据服务', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:38:14', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('9', '知识产权评估公司', '月亮湾事务所', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '8', 'www.baidu.com', '知识产权代理,知识产权诉讼,,知识产权评估', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:37:21', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('10', '知识产权评估公司,知识产权代理公司', '海草事务所', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '9', 'www.baidu.com', '知识产权代理,知识产权诉讼,知识产权数据服务,反不正当竞争诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:59:30', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('11', '知识产权评估公司,知识产权培训公司', '云崖培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '10', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:22:58', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('12', '知识产权评估公司,知识产权培训公司', '云崖培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '11', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:22:59', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('13', '知识产权评估公司,知识产权培训公司', '云崖培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '12', 'www.baidu.com', '知识产权代理,知识产权诉讼,专利诉讼,知识产权评估', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:37:23', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('14', '知识产权评估公司,知识产权培训公司', '云崖培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '13', 'www.baidu.com', '知识产权代理,知识产权诉讼,商标诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:23:00', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('15', '知识产权评估公司,知识产权培训公司', '云崖培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '14', 'www.baidu.com', '知识产权代理,知识产权诉讼,反不正当竞争诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:59:33', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('16', '知识产权评估公司,知识产权培训公司', '趣味培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '15', 'www.baidu.com', '知识产权代理,知识产权诉讼,域名争议诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:23:03', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('17', '知识产权评估公司,知识产权培训公司', '的培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '16', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:23:04', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('18', '知识产权评估公司,知识产权培训公司', '阿德福培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '17', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:23:05', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('19', '知识产权评估公司,知识产权培训公司', '得得培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '18', 'www.baidu.com', '知识产权代理,知识产权诉讼,知识产权评估,知识产权数据服务', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:38:11', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('20', '知识产权评估公司,知识产权培训公司', '去企鹅培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '19', 'www.baidu.com', '知识产权代理,知识产权诉讼,专利诉讼,反不正当竞争诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:59:35', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('21', '知识产权评估公司,知识产权培训公司', '发人人培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '20', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:23:12', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('22', '知识产权评估公司,知识产权培训公司', '好友培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '21', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:23:14', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('23', '律师事务所', '韩语培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '23', 'www.baidu.com', '知识产权代理,知识产权诉讼,地理标志诉讼,知识产权评估,知识产权数据服务', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:38:24', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('24', '知识产权评估公司,知识产权培训公司', '据培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '22', 'www.baidu.com', '知识产权代理,知识产权诉讼,知识产权数据服务,反不正当竞争诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:59:36', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('25', '知识产权评估公司,知识产权培训公司', 'ujjhy培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '24', 'www.baidu.com', '知识产权代理,知识产权诉讼,专利诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:23:36', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('26', '知识产权评估公司,知识产权培训公司', '的钱培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '25', 'www.baidu.com', '知识产权代理,知识产权诉讼,知识产权数据服务', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:38:26', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('27', '知识产权评估公司,知识产权培训公司', '的却是培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '26', 'www.baidu.com', '知识产权代理,知识产权诉讼,知识产权评估', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:37:29', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('28', '知识产权评估公司,知识产权培训公司', 'vfs培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '27', 'www.baidu.com', '知识产权代理,知识产权诉讼,知识产权数据服务', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:38:21', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('29', '知识产权评估公司,知识产权培训公司', 'vfbg培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '28', 'www.baidu.com', '知识产权代理,知识产权诉讼,反不正当竞争诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:59:39', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('30', '律师事务所,知识产权培训公司', '阿道夫培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '29', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:23:47', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('31', '知识产权评估公司,律师事务所', '122培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '30', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:23:50', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('32', '知识产权评估公司,知识产权培训公司', '235培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '31', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:23:52', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('33', '律师事务所,知识产权培训公司', '咕通咕通培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '32', 'www.baidu.com', '知识产权代理,知识产权诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:23:54', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);
INSERT INTO `zf_agency` VALUES ('34', '知识产权评估公司,知识产权培训公司', '广告贴培训公司', '0', '18551035924', '112', '213017', 'dedeq', '545286400@qq.com', '王勇', '320911199106174933', 'images/jindu.png', 'images/jindu.png', '知识产权评估公司', 'changzhou', '33', 'www.baidu.com', '知识产权代理,知识产权诉讼,知识产权数据服务,反不正当竞争诉讼', 'images/user-images/jindu.png', '2015-03-16 15:50:54', '2015-03-19 13:59:43', '0', null, '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', '我们都爱共产党', 'changzhou', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_agencyemployee
-- ----------------------------
INSERT INTO `zf_agencyemployee` VALUES ('6', 'dedede', '111', '18632456729', '545286400@qq.com', '王琳', '320911199106174933', 'images/jindu.png', '2', '5', '常州', 'images/user-images/jindu.png', '0', '我爱中国人', '一级律师', '2015-03-16 15:56:11', '2015-03-19 15:28:34', '0', '01ad0a0e58f1b4192790371eb6b0ed56', '4', '1', '10');
INSERT INTO `zf_agencyemployee` VALUES ('7', 'cloudhaha456', '18551035124', '77d1f38728ebb354aeef3e0f7899c729', '545286400@qq.com', '虚妄', '320911199106174933', 'images/jindu.png', '2', '10', '南京', 'images/user-images/jindu.png', '0', '我爱中国人', '知识产权代理人', '2015-03-16 15:56:14', '2015-03-23 13:19:21', '0', '01ad0a0e58f1b4192790371eb6b0ed56', '15', '2', '9');

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_need
-- ----------------------------
INSERT INTO `zf_need` VALUES ('29', '律师', '29', '哈啦啦哈哈', '41', null, null, null, null, null, '123', '123', null, null, null, null, null, null, null, '2015-03-22 13:56:24', '2015-03-26 15:12:15', '0');
INSERT INTO `zf_need` VALUES ('30', '法务商标', '29', '啦啦啦1', '50', null, null, null, null, null, '123', '431', null, null, null, null, null, null, null, '2015-03-24 14:00:06', '2015-03-26 15:12:20', '0');
INSERT INTO `zf_need` VALUES ('31', '商标', '29', '13', '51', null, '我是王旭', null, null, null, '12', '1211', null, null, null, null, null, null, null, '2015-03-23 14:48:21', '2015-03-26 14:05:11', '0');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_order
-- ----------------------------
INSERT INTO `zf_order` VALUES ('1', '29', '6', '0', '123', null, null, null, null, '2015-03-26 09:18:37', '0', null, null, null, '30', '4');
INSERT INTO `zf_order` VALUES ('31', '31', '6', '1', '312', null, null, null, null, '2015-03-26 09:18:39', '0', null, null, null, '26', '5');
INSERT INTO `zf_order` VALUES ('32', '30', '7', '2', '去问', '123', '4123', '萨芬萨芬', '2015-03-26 09:21:22', '2015-03-26 09:24:06', '0', '2015-03-26 09:23:57', '2015-03-26 09:24:00', '100', '32', '6');

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
-- Records of zf_problem
-- ----------------------------
INSERT INTO `zf_problem` VALUES ('3', '0800', '29', '黄河治理', '0', null, '刘涛', null, null, null, '得得得', '2015-03-26 00:00:00', '2015-04-23 00:00:00', '啊啊啊啊啊啊', 'true', '卖方', '卖方', '的呃呃呃呃呃呃', '啊啊啊啊啊啊啊啊啊啊', '2015-03-23 13:07:34', '2015-03-24 10:25:15', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zf_store
-- ----------------------------
INSERT INTO `zf_store` VALUES ('1', '1', '2', '5', null, null, '0');
INSERT INTO `zf_store` VALUES ('2', '1', '2', '5', null, null, '0');

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

-- ----------------------------
-- Records of zf_user
-- ----------------------------
INSERT INTO `zf_user` VALUES ('20', 'cloudha', '0', '0', '10a3caf7617e15dcb0f83681b962f12c', 'c37976ad34f32a71bb55b6214eec6ba5', null, '123@qq.com', null, '18551035924', null, null, null, null, 'images/user-images/user-pic.png', null, null, null, null, '0', '2015-03-24 16:18:37', null, null);
INSERT INTO `zf_user` VALUES ('21', 'dededede', '1', '0', '131a032b545b5c0bb4e70b881c6c06cc', '6e899d08e7cae9f43d62ac2612aca278', null, '1@qq.com', null, '18551035924', null, null, '', null, 'images/user-images/jindu.png', null, null, 'adedede', null, '0', '2015-03-24 16:40:51', null, null);
INSERT INTO `zf_user` VALUES ('22', 'dede2efr', '1', '0', 'f451b9b25d748bcb10c400613ce32003', 'd12fd7ddf077e81844989638d524e83a', null, null, null, '18551035923', null, null, null, null, 'images/user-images/jindu.png', null, null, '111222', null, '0', '2015-03-16 16:09:37', null, null);
INSERT INTO `zf_user` VALUES ('23', 'dededee', '0', '0', '66c52e9e9c1b2b2fa230b1c37503fe62', 'e82ed0bda24df4588662b2fa0a8684f9', null, '12@qq.com', null, '18551035912', null, null, null, null, 'images/user-images/jindu.png', null, null, null, null, '0', '2015-03-24 16:41:01', null, null);
INSERT INTO `zf_user` VALUES ('24', 'dedede', '0', '0', 'fef10be3532177dc5e63edc9b2d6a74a', '1ce64d7e1024723ab00fac56f5b8abc0', null, null, null, '18521035924', null, null, null, null, 'images/user-images/jindu.png', null, null, null, null, '0', '2015-03-20 14:16:08', null, null);
INSERT INTO `zf_user` VALUES ('25', 'dedeqqq', '0', '0', 'b06dfa23da29cafc19138ed69ca085c7', 'e4f41bcd620762fdc391251843ad419f', null, null, null, '18551235924', null, null, null, null, 'images/user-images/jindu.png', null, null, null, null, '0', '2015-03-20 14:16:09', null, null);
INSERT INTO `zf_user` VALUES ('26', 'defrgaaa', '0', '0', 'e0f0ba5f9e01c6d869a490267391d5f5', '09e4c90e4b9808b67fc320d819bfd27d', null, null, null, '18522235924', null, null, null, null, 'images/user-images/jindu.png', null, null, null, null, '0', '2015-03-20 14:16:09', null, null);
INSERT INTO `zf_user` VALUES ('27', 'dergtt', '0', '0', 'c91c509dece94e713ec437e941e7861c', 'e05100a4b0e8f215b9a64570c22830f3', null, null, null, '18552125924', null, null, null, null, 'images/user-images/jindu.png', null, '园区一', null, null, '0', '2015-03-20 14:16:09', null, null);
INSERT INTO `zf_user` VALUES ('28', 'dedededea', '1', '0', '19b9402442e9be7734f918f17064a8f7', 'f478739b285ef4307b0e9dbd85fc5750', null, null, null, '18551032124', null, null, null, null, 'images/user-images/jindu.png', null, '园区一', '1dede', null, '0', '2015-03-20 14:16:10', null, null);
INSERT INTO `zf_user` VALUES ('29', '我是王旭', '0', '0', '015aea821ab79af31e5150e95b3e6c0a', 'cea556fb3f219fa9b52d3fa254c1e6c9', '54', '1@qq.com', null, '13584567035', null, null, null, null, 'images/user-images/jindu.png', null, null, null, null, '0', '2015-03-24 16:40:25', null, null);
INSERT INTO `zf_user` VALUES ('30', '常州奥迈信息技', '1', '0', '8adc7186a0595685ddc93af417154ff7', '298e55018b228d8c5ab632d982699773', null, null, null, '18511115924', null, null, null, null, 'images/user-images/jindu.png', null, null, '能源行业', null, '0', '2015-03-20 14:16:11', null, null);
INSERT INTO `zf_user` VALUES ('31', '我我们啊的熬啊', '1', '0', '9a41005131fc7d94e70efeeac1b03eed', '18125889c202d5171fe132690ed89145', null, null, null, '18551035914', null, null, null, null, 'images/user-images/jindu.png', null, null, '我么得得', null, '0', '2015-03-20 14:16:11', null, null);
INSERT INTO `zf_user` VALUES ('32', '刘涛', '0', '0', '5356e370f8a21bedf5421fca63daee7f', 'faf99440ffdd05671a8400061b9240cb', null, null, null, '18551031924', null, null, null, null, 'images/user-images/jindu.png', null, '园区一', null, null, '0', '2015-03-23 13:16:15', null, null);
INSERT INTO `zf_user` VALUES ('33', '刘涛啊', '0', '0', 'ebffb241a1479cf0587cd93febd6c42e', '2923fbc1c0fcb0bb26f46e603e105ae5', null, null, null, '18111035924', null, null, null, null, 'images/user-images/jindu.png', null, '园区一', null, null, '0', '2015-03-23 13:16:16', null, null);
INSERT INTO `zf_user` VALUES ('34', '123123', '0', '0', 'qweqweqwqes', '1212', '1', null, null, '13584567035', null, null, null, null, 'images/user-images/jindu.png', null, null, null, null, '0', '2015-03-26 09:14:49', '2015-03-23 14:08:30', null);
