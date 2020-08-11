CREATE DATABASE IF NOT EXISTS `eims` DEFAULT CHARACTER SET utf8;

USE `eims`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_attendance
-- ----------------------------
DROP TABLE IF EXISTS `t_attendance`;
CREATE TABLE `t_attendance` (
  `a_id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `e_id` int(20) NOT NULL COMMENT '工号',
  `a_type` smallint(2) NOT NULL COMMENT '考勤类型',
  `a_time` varchar(20) NOT NULL COMMENT '打卡时间',
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='考勤记录表';

-- ----------------------------
-- Records of t_attendance
-- ----------------------------
INSERT INTO `t_attendance` VALUES (1, 2, 1, '2020-07-21 08:25:23');
INSERT INTO `t_attendance` VALUES (2, 2, 2, '2020-07-21 17:35:23');
INSERT INTO `t_attendance` VALUES (3, 2, 1, '2020-07-21 09:25:23');
INSERT INTO `t_attendance` VALUES (4, 2, 2, '2020-07-21 19:25:23');
INSERT INTO `t_attendance` VALUES (5, 2, 1, '2020-07-21 07:25:23');
INSERT INTO `t_attendance` VALUES (6, 2, 2, '2020-07-21 20:25:23');
INSERT INTO `t_attendance` VALUES (7, 3, 1, '2020-07-21 06:25:23');
INSERT INTO `t_attendance` VALUES (8, 3, 2, '2020-07-21 21:25:23');
INSERT INTO `t_attendance` VALUES (9, 3, 1, '2020-07-21 09:23:23');
INSERT INTO `t_attendance` VALUES (10, 3, 2, '2020-07-21 19:25:23');

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `e_id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `e_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '工号',
  `e_password` char(40) DEFAULT NULL COMMENT '密码',
  `e_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `e_birthday` date DEFAULT NULL COMMENT '出生日期',
  `e_sex` tinyint(4) DEFAULT NULL COMMENT '性别，1男，0女',
  `e_phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `e_email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `e_role_id` int(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`e_id`),
  UNIQUE KEY `t_employee_e_id_uindex` (`e_id`),
  UNIQUE KEY `e_account` (`e_account`),
  UNIQUE KEY `e_phone` (`e_phone`),
  UNIQUE KEY `e_email` (`e_email`),
  KEY `e_role_id` (`e_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES (1, 'admin', '9EF9EE6C825CEAE1D980483E7BDEFE0C', '王总', '1984-07-30', '1', '13695588443', 'admin@abc.com', '0');
INSERT INTO `t_employee` VALUES (2, '1997104', '8DEFEF6341E5DF806875EA292327A734', '曾哲', '1996-06-05', '0', '15065579842', 'zengzhe@163.com', '1');
INSERT INTO `t_employee` VALUES (3, '1996249', '8DEFEF6341E5DF806875EA292327A734', '杨广', '1997-08-25', '0', '13665588443', 'yangguang@163.com', '2');
INSERT INTO `t_employee` VALUES (4, '1994081', '8DEFEF6341E5DF806875EA292327A734', '赵日天', '1994-08-21', '0', '13458761199', 'zhaoritian@abc.com', '2');
INSERT INTO `t_employee` VALUES (5, '1988104', '8DEFEF6341E5DF806875EA292327A734', '王二狗', '1996-01-19', '0', '18406517925', 'wangergou@qq.com', '2');
INSERT INTO `t_employee` VALUES (6, '1998568', '8DEFEF6341E5DF806875EA292327A734', '赵国强', '1998-04-06', '0', '17759462587', 'zhaoguoqiang@qq.com', '2');
INSERT INTO `t_employee` VALUES (7, '1994025', '8DEFEF6341E5DF806875EA292327A734', '猪八戒', '2005-01-27', '0', '15064579854', 'zhubajie@abc.com', '2');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `r_id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `r_name` varchar(10) NOT NULL COMMENT '角色名称',
  `r_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '系统管理员', null);
INSERT INTO `t_role` VALUES (2, '管理员', null);
INSERT INTO `t_role` VALUES (3, '普通员工', null);
