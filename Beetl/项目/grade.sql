/*
Navicat MySQL Data Transfer

Source Server         : java web
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : grade

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2020-09-14 14:46:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `c_id` varchar(30) NOT NULL,
  `c_name` varchar(50) NOT NULL,
  `c_teacher` varchar(50) NOT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('101', 'java', '赵大');
INSERT INTO `course` VALUES ('102', 'c++', '钱二');
INSERT INTO `course` VALUES ('103', 'c语言', '孙三');
INSERT INTO `course` VALUES ('104', 'python', '周五');

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `s_id` varchar(30) NOT NULL,
  `c_id` varchar(30) NOT NULL,
  `grade` int(10) DEFAULT NULL,
  PRIMARY KEY (`s_id`,`c_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `sc_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`),
  CONSTRAINT `sc_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `course` (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('001', '101', '99');
INSERT INTO `sc` VALUES ('001', '102', '60');
INSERT INTO `sc` VALUES ('001', '103', '75');
INSERT INTO `sc` VALUES ('001', '104', '85');
INSERT INTO `sc` VALUES ('002', '101', '85');
INSERT INTO `sc` VALUES ('002', '102', '74');
INSERT INTO `sc` VALUES ('002', '103', '75');
INSERT INTO `sc` VALUES ('002', '104', '86');
INSERT INTO `sc` VALUES ('003', '101', '68');
INSERT INTO `sc` VALUES ('003', '102', '98');
INSERT INTO `sc` VALUES ('003', '103', '58');
INSERT INTO `sc` VALUES ('003', '104', '87');
INSERT INTO `sc` VALUES ('005', '101', '69');
INSERT INTO `sc` VALUES ('005', '102', '84');
INSERT INTO `sc` VALUES ('005', '103', '66');
INSERT INTO `sc` VALUES ('005', '104', '88');
INSERT INTO `sc` VALUES ('006', '101', '68');
INSERT INTO `sc` VALUES ('006', '102', '87');
INSERT INTO `sc` VALUES ('006', '103', '87');
INSERT INTO `sc` VALUES ('006', '104', '74');
INSERT INTO `sc` VALUES ('007', '101', '98');
INSERT INTO `sc` VALUES ('007', '102', '96');
INSERT INTO `sc` VALUES ('007', '103', '67');
INSERT INTO `sc` VALUES ('007', '104', '99');
INSERT INTO `sc` VALUES ('008', '101', '50');
INSERT INTO `sc` VALUES ('010', '101', '50');
INSERT INTO `sc` VALUES ('010', '102', '50');
INSERT INTO `sc` VALUES ('010', '103', '50');
INSERT INTO `sc` VALUES ('010', '104', '50');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `s_id` varchar(30) NOT NULL,
  `s_name` varchar(50) NOT NULL,
  `s_sex` varchar(30) NOT NULL,
  `s_age` int(10) NOT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('001', '张三', '男', '17');
INSERT INTO `student` VALUES ('002', '李四', '男', '20');
INSERT INTO `student` VALUES ('003', '王五', '男', '19');
INSERT INTO `student` VALUES ('005', '巳蛇', '男', '18');
INSERT INTO `student` VALUES ('006', '寅虎', '男', '23');
INSERT INTO `student` VALUES ('007', '卯兔', '女', '16');
INSERT INTO `student` VALUES ('008', '李四', '男', '22');
INSERT INTO `student` VALUES ('009', '辰龙', '男', '20');
INSERT INTO `student` VALUES ('010', '王五', '男', '20');
