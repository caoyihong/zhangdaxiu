/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : zhifeng

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-04-02 15:27:14
*/

SET FOREIGN_KEY_CHECKS=0;

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
