/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50173
Source Host           : 118.89.101.23:3306
Source Database       : drepair

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-09-07 19:27:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员工号',
  `admin_name` varchar(25) NOT NULL COMMENT '管理员姓名',
  `admin_pwd` varchar(25) NOT NULL COMMENT '管理员登录系统的密码',
  `admin_icard` varchar(18) NOT NULL COMMENT '管理员身份证号码',
  `admin_phone` varchar(11) NOT NULL COMMENT '管理员手机号码',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123456', '360123111122223333', '13037232106');

-- ----------------------------
-- Table structure for eval
-- ----------------------------
DROP TABLE IF EXISTS `eval`;
CREATE TABLE `eval` (
  `eval_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价ID标志，自动递增',
  `eval_content` varchar(255) DEFAULT NULL COMMENT '评价内容',
  `eval_score` int(11) DEFAULT NULL COMMENT '评价分数(0、1、2、3)',
  `order_id` int(11) DEFAULT NULL COMMENT '所属订单ID',
  `stu_id` varchar(25) DEFAULT NULL COMMENT '评价所属的学生',
  `hmr_id` int(11) DEFAULT NULL COMMENT '评价所属的宿管',
  `repairer_id` int(11) DEFAULT NULL COMMENT '评价所属的维修员',
  `admin_id` int(11) DEFAULT NULL COMMENT '评价所属的管理员',
  PRIMARY KEY (`eval_id`),
  KEY `order_id` (`order_id`),
  KEY `stu_id` (`stu_id`),
  KEY `hmr_id` (`hmr_id`),
  KEY `repairer_id` (`repairer_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `eval_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `eval_ibfk_2` FOREIGN KEY (`stu_id`) REFERENCES `stu` (`stu_id`),
  CONSTRAINT `eval_ibfk_3` FOREIGN KEY (`hmr_id`) REFERENCES `hmr` (`hmr_id`),
  CONSTRAINT `eval_ibfk_4` FOREIGN KEY (`repairer_id`) REFERENCES `repairer` (`repairer_id`),
  CONSTRAINT `eval_ibfk_5` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hmr
-- ----------------------------
DROP TABLE IF EXISTS `hmr`;
CREATE TABLE `hmr` (
  `hmr_id` int(11) NOT NULL COMMENT '宿管工号',
  `hmr_name` varchar(25) DEFAULT NULL COMMENT '宿管姓名',
  `hmr_pwd` varchar(25) NOT NULL COMMENT '宿管登录系统的密码',
  `hmr_floor` varchar(4) DEFAULT NULL COMMENT '宿管所入住的楼(如A4)',
  `hmr_icard` varchar(18) DEFAULT NULL COMMENT '宿管身份证号码',
  `hmr_phone` varchar(11) NOT NULL COMMENT '宿管手机号码',
  PRIMARY KEY (`hmr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for img
-- ----------------------------
DROP TABLE IF EXISTS `img`;
CREATE TABLE `img` (
  `img_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片ID标志，自动递增',
  `img_url` varchar(255) NOT NULL COMMENT '图片的url',
  `img_datetime` datetime NOT NULL COMMENT '图片的日期时间',
  `order_id` int(11) DEFAULT NULL COMMENT '订单(学生或宿管)',
  PRIMARY KEY (`img_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `img_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报修订单ID标志，自动递增',
  `order_info` text NOT NULL COMMENT '报修订单的内容',
  `stu_id` varchar(25) DEFAULT NULL COMMENT '报修订单所属的学生',
  `order_state` int(11) NOT NULL COMMENT '报修订单状态(01、审核中、02审核通过、03审核不通过、11维修中、12维修完成、13维修失败)',
  `order_start_time` datetime NOT NULL COMMENT '报修订单创建时间',
  `order_over_time` datetime DEFAULT NULL COMMENT '报修订单结束时间',
  `hmr_id` int(11) DEFAULT NULL COMMENT '报修订单所属的宿管',
  `order_room` varchar(25) NOT NULL COMMENT '报修订单所属的楼层和寝室',
  `order_sort` int(11) DEFAULT NULL COMMENT '报修等级(1、不是很急 2、普通 3、很急)',
  `admin_id` int(11) DEFAULT NULL COMMENT '报修负责的管理员',
  `repairer_id` int(11) DEFAULT NULL COMMENT '当前维修员（抢单）',
  PRIMARY KEY (`order_id`),
  KEY `hmr_id` (`hmr_id`),
  KEY `stu_id` (`stu_id`),
  KEY `admin_id` (`admin_id`),
  KEY `repairer_id` (`repairer_id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`hmr_id`) REFERENCES `hmr` (`hmr_id`),
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`stu_id`) REFERENCES `stu` (`stu_id`),
  CONSTRAINT `order_ibfk_4` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `order_ibfk_5` FOREIGN KEY (`repairer_id`) REFERENCES `repairer` (`repairer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for repairer
-- ----------------------------
DROP TABLE IF EXISTS `repairer`;
CREATE TABLE `repairer` (
  `repairer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '维修人工号',
  `repairer_name` varchar(25) NOT NULL COMMENT '维修人姓名',
  `repairer_phone` varchar(11) NOT NULL COMMENT '维修人手机号码',
  `repairer_icard` varchar(18) NOT NULL COMMENT '维修人身份证号码',
  `repairer_pwd` varchar(25) NOT NULL COMMENT '维修人登录系统密码',
  PRIMARY KEY (`repairer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repairer
-- ----------------------------
INSERT INTO `repairer` VALUES ('4', '李师傅', '12345678902', '123321111122223333', '123456');

-- ----------------------------
-- Table structure for sql
-- ----------------------------
DROP TABLE IF EXISTS `sql`;
CREATE TABLE `sql` (
  `sql_id` int(11) NOT NULL AUTO_INCREMENT,
  `sql_path` varchar(255) NOT NULL,
  PRIMARY KEY (`sql_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sql
-- ----------------------------
INSERT INTO `sql` VALUES ('4', '2017_09_05_13_42_07.sql');

-- ----------------------------
-- Table structure for stu
-- ----------------------------
DROP TABLE IF EXISTS `stu`;
CREATE TABLE `stu` (
  `stu_id` varchar(25) NOT NULL COMMENT '学生学号',
  `stu_pwd` varchar(25) NOT NULL COMMENT '学生登录系统的密码',
  `stu_phone` varchar(11) NOT NULL COMMENT '学生手机号码',
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
