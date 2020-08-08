CREATE DATABASE IF NOT EXISTS `eims` DEFAULT CHARACTER SET utf8;

USE `eims`;


-- 员工表
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee`
(
  `e_id`              int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `e_account`        varchar(20) DEFAULT NULL COMMENT '工号',
  `e_password`        varchar(30) DEFAULT NULL COMMENT '密码',

  `e_name`            varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `e_birthday`         date DEFAULT NULL COMMENT '出生日期',
  `e_sex`          tinyint DEFAULT NULL COMMENT '性别，1男，0女',
  `e_phone`           varchar(11) DEFAULT NULL COMMENT '电话号码',
  `e_email`           varchar(30) DEFAULT NULL COMMENT '邮箱',
  `e_role_id`         int(20)     DEFAULT NULL COMMENT '角色ID',

  PRIMARY KEY (`e_id`),
  UNIQUE KEY `e_account` (`e_account`),
  UNIQUE KEY `e_phone` (`e_phone`),
  UNIQUE KEY `e_email` (`e_email`),
  KEY `e_role_id` (`e_role_id`)
  ) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- 考勤记录表
DROP TABLE IF EXISTS `t_attendance`;
CREATE TABLE `t_attendance` (
  `a_id`      int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `e_id`      int(20) NOT NULL COMMENT '工号',
  `a_type`    smallint(2) NOT NULL COMMENT '考勤类型',
  `a_time`    varchar(20) NOT NULL COMMENT '打卡时间',
  PRIMARY KEY (`a_id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='考勤记录表';


-- 角色表
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `r_id`   int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `r_name` varchar(10) NOT NULL COMMENT '角色名称',
  `r_desc` varchar(100) NOT NULL COMMENT '角色描述',
  PRIMARY KEY (`r_id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='角色表';
