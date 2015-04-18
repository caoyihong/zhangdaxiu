/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhifeng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-03-17 09:18:34
*/

SET FOREIGN_KEY_CHECKS=0;

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
