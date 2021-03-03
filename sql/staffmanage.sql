/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : staffmanage

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 04/03/2021 00:19:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for assets
-- ----------------------------
DROP TABLE IF EXISTS `assets`;
CREATE TABLE `assets`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dept` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '使用部门',
  `assets_no` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资产编号',
  `assets_type` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资产类型',
  `model` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规格型号',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `unit` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
  `username` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '使用人',
  `start_time` date NULL DEFAULT NULL COMMENT '开始使用时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `ext1` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资产信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of assets
-- ----------------------------
INSERT INTO `assets` VALUES (1, '财务中心', 'ZC0516', '计算机类', '8890', 1, '个', '胡明春', '2021-03-10', '2021-03-03 21:53:41', '2021-03-03 21:54:59', '笔记本', '');

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dept_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `ext1` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '财务中心', '2021-03-03 18:56:55', '2021-03-03 18:56:55', '', '');
INSERT INTO `dept` VALUES (2, '人力资源', '2021-03-03 18:57:01', '2021-03-03 18:57:01', '', '');
INSERT INTO `dept` VALUES (3, '市场部', '2021-03-03 18:57:08', '2021-03-03 18:57:08', '', '');
INSERT INTO `dept` VALUES (4, '信息中心', '2021-03-03 18:57:14', '2021-03-03 18:57:14', '', '');
INSERT INTO `dept` VALUES (5, '开发拓展', '2021-03-03 18:57:22', '2021-03-03 18:57:22', '', '');
INSERT INTO `dept` VALUES (6, '营运中心', '2021-03-03 18:57:40', '2021-03-03 18:57:40', '', '');

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `username` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `ip` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `address` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ext1` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES (79, '胡明春', '胡明春', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 17:28:52', NULL, NULL);
INSERT INTO `login_log` VALUES (80, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 17:32:35', NULL, NULL);
INSERT INTO `login_log` VALUES (81, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 17:32:41', NULL, NULL);
INSERT INTO `login_log` VALUES (82, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 17:32:51', NULL, NULL);
INSERT INTO `login_log` VALUES (83, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 17:34:24', NULL, NULL);
INSERT INTO `login_log` VALUES (84, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 17:36:33', NULL, NULL);
INSERT INTO `login_log` VALUES (85, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 17:37:50', NULL, NULL);
INSERT INTO `login_log` VALUES (86, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 17:38:32', NULL, NULL);
INSERT INTO `login_log` VALUES (87, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 17:38:47', NULL, NULL);
INSERT INTO `login_log` VALUES (88, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 17:41:00', NULL, NULL);
INSERT INTO `login_log` VALUES (89, '周晓霜', '8888', '18878689878', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 18:30:42', NULL, NULL);
INSERT INTO `login_log` VALUES (90, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 18:30:54', NULL, NULL);
INSERT INTO `login_log` VALUES (91, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 21:17:51', NULL, NULL);
INSERT INTO `login_log` VALUES (92, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 22:25:17', NULL, NULL);
INSERT INTO `login_log` VALUES (93, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 22:39:25', NULL, NULL);
INSERT INTO `login_log` VALUES (94, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 22:39:39', NULL, NULL);
INSERT INTO `login_log` VALUES (95, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 23:17:21', NULL, NULL);
INSERT INTO `login_log` VALUES (96, '周晓霜', '8888', '18878689878', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 23:17:37', NULL, NULL);
INSERT INTO `login_log` VALUES (97, '周晓霜', '8888', '18878689878', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 23:18:18', NULL, NULL);
INSERT INTO `login_log` VALUES (98, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 23:19:11', NULL, NULL);
INSERT INTO `login_log` VALUES (99, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-03 23:54:38', NULL, NULL);
INSERT INTO `login_log` VALUES (100, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-04 00:01:34', NULL, NULL);
INSERT INTO `login_log` VALUES (101, '胡明春', 'admin', 'admin', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-04 00:10:55', NULL, NULL);
INSERT INTO `login_log` VALUES (102, '周晓霜', '888888', '18878689878', '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-04 00:11:10', NULL, NULL);
INSERT INTO `login_log` VALUES (103, '周小双', '666666', NULL, '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-04 00:16:59', NULL, NULL);
INSERT INTO `login_log` VALUES (104, '周小双', '666666', NULL, '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-04 00:17:04', NULL, NULL);
INSERT INTO `login_log` VALUES (105, '周小双', '666666', NULL, '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-04 00:17:16', NULL, NULL);
INSERT INTO `login_log` VALUES (106, '周小双', '666666', NULL, '127.0.0.1', '0|0|0|内网IP|内网IP', '2021-03-04 00:18:16', NULL, NULL);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dept` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通知部门',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '消息内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `ext1` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '人力资源,市场部,开发拓展', '这是公共', '1', '2021-03-03 22:09:35', '2021-03-03 22:09:43', 'honey', 'Anding', '', '');
INSERT INTO `notice` VALUES (2, '人力资源,市场部', '这是一条过期公共', '0', '2021-03-03 22:13:39', '2021-03-03 22:13:39', 'honey', 'Anding', '', '');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `staffid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `dept` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门',
  `email` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `idcard` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `nativeplace` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `residence` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '户口所在地',
  `political` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '政治面貌',
  `education` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '全日制最高学历',
  `graduation_date` date NULL DEFAULT NULL COMMENT '毕业时间',
  `first_workdate` date NULL DEFAULT NULL COMMENT '第一次参加工作的时间',
  `company_workdate` date NULL DEFAULT NULL COMMENT '进入本公司工作的时间',
  `contract_starttime` date NULL DEFAULT NULL COMMENT '目前劳动合同开始的时间',
  `contract_endtime` date NULL DEFAULT NULL COMMENT '目前劳动合同结束时间',
  `endtime` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '距离劳动合同结束时间',
  `equipment` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拥有公司资产设备编号',
  `on_job` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否在职 1:是 0:否',
  `is_del` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否删除 1:是 0:否',
  `role` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户角色',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `ext1` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext3` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext4` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `staffid`(`staffid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '人员信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, 'admin', 'd0af8fa1272ef5a152d9e27763eea293', '胡明春', '男', '人力资源', '1541545122@qq.com', 'admin', '532501199010200896', '深圳', '深圳', '党员', '硕士', '2021-03-03', '2021-03-03', '2021-03-03', '2021-03-03', '2021-03-03', '2022-03-03', '1', '1', '0', '2', '2021-03-03 17:25:30', '2021-03-04 00:10:27', NULL, NULL, NULL, NULL);
INSERT INTO `staff` VALUES (2, '888888', '32015ac1a4834e2c299318c6b510e7f0', '周晓霜', '女', '开发拓展', '15464564@qq.com', '18878689878', '535524199010200896', '广东', '广东', '党员', '硕士', '2020-07-23', '2021-03-10', '2021-03-11', '2021-03-17', '2021-03-19', '2021-03-24', '2', '1', '0', '1', '2021-03-03 18:29:24', '2021-03-04 00:11:56', '', '', '', '');
INSERT INTO `staff` VALUES (3, '666666', 'bf90c5691ff6252515d5e863d8209747', '周小双', '男', '财务中心', '1564984684@qq.com', '18784545689', '545523198010286978', '上海', '上海', '党员', '博士', '2021-03-11', NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, '1', '2021-03-04 00:16:46', '2021-03-04 00:19:03', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
