/*
Navicat MySQL Data Transfer

Source Server         : java web
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : ssm-user

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2020-09-14 14:45:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `money` double(20,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '1', '5000');
INSERT INTO `account` VALUES ('2', '1', '6000');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(5) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `sex` varchar(30) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '李四', '男', '20');
INSERT INTO `person` VALUES ('2', '张三', '男', '18');
INSERT INTO `person` VALUES ('3', '王五', '男', '89');
INSERT INTO `person` VALUES ('4', '子鼠', '女', '10');
INSERT INTO `person` VALUES ('5', '丑牛', '男', '50');
INSERT INTO `person` VALUES ('6', '寅虎', '女', '35');
INSERT INTO `person` VALUES ('7', '卯兔', '女', '18');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(30) DEFAULT NULL,
  `roledesc` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '学生', '学习');
INSERT INTO `role` VALUES ('2', '老师', '教学');
INSERT INTO `role` VALUES ('3', '门卫', '保安');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '子鼠', '2020-08-21 14:36:19', '男', '邯郸');
INSERT INTO `student` VALUES ('2', '丑牛', '2020-08-21 14:36:39', '男', '聊城');

-- ----------------------------
-- Table structure for student_role
-- ----------------------------
DROP TABLE IF EXISTS `student_role`;
CREATE TABLE `student_role` (
  `sid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`sid`,`rid`),
  KEY `rid` (`rid`),
  CONSTRAINT `rid` FOREIGN KEY (`rid`) REFERENCES `role` (`id`),
  CONSTRAINT `sid` FOREIGN KEY (`sid`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_role
-- ----------------------------
INSERT INTO `student_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `birthday` datetime DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', '2020-08-19 14:49:30', '男', '山东');
INSERT INTO `user` VALUES ('2', '李四', '2020-08-18 14:49:58', '男', '山东');
INSERT INTO `user` VALUES ('5', '赵六', '2020-08-19 19:06:41', '男', '山东');
