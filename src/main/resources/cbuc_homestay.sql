/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : cbuc_homestay

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-05-23 00:04:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ms_apply
-- ----------------------------
DROP TABLE IF EXISTS `ms_apply`;
CREATE TABLE `ms_apply` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `OPEN_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请人ID',
  `MNAME` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商家名称',
  `MPHONE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商家号码',
  `MCARDNO` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商家身份证号',
  `MADDR` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商家地址',
  `MLICENSE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商家营业执照图片URL',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `REMARK` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '申请说明',
  `AUDIT_STATUS` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'WA' COMMENT '状态    WA：待审核   SA：审核通过   FA：审核不通过',
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'E' COMMENT '状态    E：生效中   D：已失效',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_apply
-- ----------------------------
INSERT INTO `ms_apply` VALUES ('1', 'oyKmX5FJURuSFYR96-2ausZkkguE', '北墘4号小屋', '17689400062', '350322199711133834', '福建省福州市台江区洋中路', 'https://cbucbm.club/4a40cd81', '2020-05-18 23:32:31', '', 'FA', 'D');
INSERT INTO `ms_apply` VALUES ('3', 'oyKmX5FJURuSFYR96-2ausZkkguE', '11', '17689400062', '350322199711133834', '福建省福州市台江区驱蚊器群', 'https://cbucbm.club/3123d520', '2020-05-22 22:51:32', '申请入驻', 'WA', 'E');

-- ----------------------------
-- Table structure for ms_audit_log
-- ----------------------------
DROP TABLE IF EXISTS `ms_audit_log`;
CREATE TABLE `ms_audit_log` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PARENT_ID` bigint DEFAULT NULL COMMENT '受审ID',
  `TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '受审类型     MERCHANT：商家   BULLETIN：公告   NEWS：资讯   COMMENT：评论   ROOM：房间',
  `AUDIT_STATUS` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核状态',
  `AUDIT_REMARK` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核说明',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',
  `STATUS` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'E' COMMENT '状态    E：生效    D：失效',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_audit_log
-- ----------------------------
INSERT INTO `ms_audit_log` VALUES ('1', '13', 'ROOM', 'SA', '允许通过！', '2020-05-22 23:07:13', 'E');
INSERT INTO `ms_audit_log` VALUES ('4', '12', 'ROOM', 'SA', '允许通过！', '2020-05-22 23:07:22', 'E');
INSERT INTO `ms_audit_log` VALUES ('7', '9', 'ROOM', 'SA', '允许通过！', '2020-05-22 23:11:43', 'E');
INSERT INTO `ms_audit_log` VALUES ('9', '8', 'ROOM', 'SA', '允许通过！', '2020-05-22 23:13:11', 'E');
INSERT INTO `ms_audit_log` VALUES ('10', '7', 'ROOM', 'SA', '允许通过！', '2020-05-22 23:13:43', 'E');
INSERT INTO `ms_audit_log` VALUES ('11', '6', 'ROOM', 'SA', '允许通过！', '2020-05-22 23:13:48', 'E');
INSERT INTO `ms_audit_log` VALUES ('12', '5', 'ROOM', 'SA', '允许通过！', '2020-05-22 23:13:53', 'E');
INSERT INTO `ms_audit_log` VALUES ('13', '4', 'ROOM', 'SA', '允许通过！', '2020-05-22 23:13:57', 'E');
INSERT INTO `ms_audit_log` VALUES ('14', '3', 'ROOM', 'SA', '允许通过！', '2020-05-22 23:14:05', 'E');
INSERT INTO `ms_audit_log` VALUES ('15', '2', 'ROOM', 'SA', '允许通过！', '2020-05-22 23:14:09', 'E');
INSERT INTO `ms_audit_log` VALUES ('16', '1', 'ROOM', 'SA', '允许通过！', '2020-05-22 23:14:15', 'E');

-- ----------------------------
-- Table structure for ms_bulletin
-- ----------------------------
DROP TABLE IF EXISTS `ms_bulletin`;
CREATE TABLE `ms_bulletin` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PUBLISH_ID` bigint DEFAULT NULL,
  `CONTENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公告内容',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'E' COMMENT '状态     E：生效中    D：已失效',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_bulletin
-- ----------------------------
INSERT INTO `ms_bulletin` VALUES ('1', '1', '因疫情影响，北墘暂停接待旅游团队，请各旅行社和游客提前调整游览线路！', '2020-04-08 10:16:10', '2020-04-08 10:18:19', 'E');

-- ----------------------------
-- Table structure for ms_comment
-- ----------------------------
DROP TABLE IF EXISTS `ms_comment`;
CREATE TABLE `ms_comment` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `OID` bigint DEFAULT NULL COMMENT '订单ID',
  `RID` bigint DEFAULT NULL COMMENT '房源ID',
  `commentor` bigint DEFAULT NULL COMMENT '评论者',
  `TYPE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '评论类型    1：一级评论   2：二级评论',
  `CONTENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评论内容',
  `LIKE_COUNT` int DEFAULT '0' COMMENT '点赞数',
  `COMMENT_COUNT` int DEFAULT '0' COMMENT '评论数',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'E' COMMENT '评论状态   E：生效    D：失效',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_comment
-- ----------------------------

-- ----------------------------
-- Table structure for ms_favorite
-- ----------------------------
DROP TABLE IF EXISTS `ms_favorite`;
CREATE TABLE `ms_favorite` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `RID` bigint DEFAULT NULL COMMENT '房间ID',
  `OPEN_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户标识ID',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `STATUS` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'E' COMMENT '状态  E：生效 D：失效',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_favorite
-- ----------------------------
INSERT INTO `ms_favorite` VALUES ('1', '1', 'oyKmX5FJURuSFYR96-2ausZkkguE', '2020-05-21 20:17:29', '2020-05-22 22:41:02', 'D');
INSERT INTO `ms_favorite` VALUES ('2', '7', 'oyKmX5FJURuSFYR96-2ausZkkguE', '2020-05-21 20:17:34', '2020-05-21 20:18:08', 'D');
INSERT INTO `ms_favorite` VALUES ('3', '13', 'oyKmX5FJURuSFYR96-2ausZkkguE', '2020-05-22 19:44:29', '2020-05-22 19:44:29', 'E');

-- ----------------------------
-- Table structure for ms_image
-- ----------------------------
DROP TABLE IF EXISTS `ms_image`;
CREATE TABLE `ms_image` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PARENT_ID` bigint DEFAULT NULL COMMENT '所属ID',
  `ORIGIN` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片来源    ROOM ：房间图片    NEWS ：资讯图片   USER：用户头像    LICENSE：营业执照',
  `URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片地址',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'E' COMMENT '图片状态      D：已失效   E：生效中',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_image
-- ----------------------------
INSERT INTO `ms_image` VALUES ('1', '1', 'LICENSE', '/upfiles/img/2019-12-31/fee2038f-96dd-4eb7-84f6-12c06b641c99.jpg', '2020-01-11 11:11:42', '2020-01-11 11:11:42', 'E');
INSERT INTO `ms_image` VALUES ('2', '2', 'LICENSE', '/upfiles/img/2019-12-31/fee2038f-96dd-4eb7-84f6-12c06b641c99.jpg', '2020-01-11 11:11:42', '2020-01-11 11:11:42', 'E');
INSERT INTO `ms_image` VALUES ('3', '3', 'LICENSE', '/upfiles/img/2019-12-31/fee2038f-96dd-4eb7-84f6-12c06b641c99.jpg', '2020-01-11 11:11:42', '2020-01-11 11:11:42', 'E');
INSERT INTO `ms_image` VALUES ('4', '4', 'LICENSE', '/upfiles/img/2019-12-31/fee2038f-96dd-4eb7-84f6-12c06b641c99.jpg', '2020-01-11 11:11:42', '2020-01-11 23:10:01', 'E');
INSERT INTO `ms_image` VALUES ('9', '12', 'ROOM', 'https://cbucbm.club/812bbd78-62d3-44bc-836c-9ee27ba4866a', '2020-02-13 16:11:34', '2020-02-13 19:53:12', 'D');
INSERT INTO `ms_image` VALUES ('10', '12', 'ROOM', 'https://cbucbm.club/db9734a9-45c9-477b-abe1-dd8ec08d7410', '2020-02-13 16:11:35', '2020-02-13 19:55:00', 'D');
INSERT INTO `ms_image` VALUES ('11', '12', 'ROOM', 'https://cbucbm.club/503882ac-04fe-4431-b76b-004442389d0b', '2020-02-13 20:17:00', '2020-02-13 20:26:11', 'E');
INSERT INTO `ms_image` VALUES ('12', '12', 'ROOM', 'https://cbucbm.club/c5a9d64b-9142-4423-81c2-b3a8cb10204e', '2020-02-13 20:24:31', '2020-02-13 21:01:30', 'D');
INSERT INTO `ms_image` VALUES ('13', '12', 'ROOM', 'https://cbucbm.club/021c6d53-8e27-4104-921e-ff2647a4cc3f', '2020-02-13 20:24:31', '2020-02-13 21:00:11', 'D');
INSERT INTO `ms_image` VALUES ('14', '12', 'ROOM', 'https://cbucbm.club/3026511d-3b48-44a2-b73f-00b1b23f21f9', '2020-02-13 21:03:13', '2020-02-13 21:05:10', 'D');
INSERT INTO `ms_image` VALUES ('15', '12', 'ROOM', 'https://cbucbm.club/ad349b24-7a5c-49d5-be97-81800cb15bae', '2020-02-13 21:08:57', '2020-02-13 21:08:57', 'E');
INSERT INTO `ms_image` VALUES ('16', '12', 'ROOM', 'https://cbucbm.club/1e9fb52e-2c23-4668-921a-5d73e978ce15', '2020-02-13 21:08:57', '2020-02-28 11:58:59', 'D');
INSERT INTO `ms_image` VALUES ('23', '9', 'ROOM', 'https://cbucbm.club/79f8483e-7619-4275-b8b2-ed269e6c2139', '2020-02-13 21:29:03', '2020-02-13 21:29:03', 'E');
INSERT INTO `ms_image` VALUES ('24', '9', 'ROOM', 'https://cbucbm.club/9b2dc1d6-df8d-44fa-9826-c85a9b5f9f13', '2020-02-13 21:29:03', '2020-02-13 21:29:03', 'E');
INSERT INTO `ms_image` VALUES ('25', '9', 'ROOM', 'https://cbucbm.club/224cab39-c048-486c-b377-2de64cf256c5', '2020-02-13 21:29:03', '2020-02-13 21:29:03', 'E');
INSERT INTO `ms_image` VALUES ('26', '8', 'ROOM', 'https://cbucbm.club/c0d41fa5-5364-4cb5-a7e2-67dd39c1012e', '2020-02-13 21:29:52', '2020-02-13 21:29:52', 'E');
INSERT INTO `ms_image` VALUES ('27', '8', 'ROOM', 'https://cbucbm.club/5d0ecce1-df54-493c-a5ca-96f803399dbd', '2020-02-13 21:29:52', '2020-02-13 21:29:52', 'E');
INSERT INTO `ms_image` VALUES ('28', '8', 'ROOM', 'https://cbucbm.club/ab2e4f3c-4707-4ff6-a3dd-d27adfff9ffc', '2020-02-13 21:29:52', '2020-02-13 21:29:52', 'E');
INSERT INTO `ms_image` VALUES ('29', '7', 'ROOM', 'https://cbucbm.club/c36b3e85-5368-4b15-8c62-2c38702cd0b8', '2020-02-13 21:30:16', '2020-02-13 21:30:16', 'E');
INSERT INTO `ms_image` VALUES ('30', '7', 'ROOM', 'https://cbucbm.club/3928009d-9bc0-4c7c-b817-09c644840953', '2020-02-13 21:30:16', '2020-02-13 21:30:16', 'E');
INSERT INTO `ms_image` VALUES ('31', '7', 'ROOM', 'https://cbucbm.club/25367c82-9430-4f75-8b07-6edbcdc4b8eb', '2020-02-13 21:30:16', '2020-02-13 21:30:16', 'E');
INSERT INTO `ms_image` VALUES ('32', '6', 'ROOM', 'https://cbucbm.club/8dd6782a-14f3-494c-8aac-3923c3730bf3', '2020-02-13 21:31:06', '2020-02-13 21:31:06', 'E');
INSERT INTO `ms_image` VALUES ('33', '6', 'ROOM', 'https://cbucbm.club/2a62aec5-3504-4aa9-b2d4-59eb9831bd1b', '2020-02-13 21:31:06', '2020-02-13 21:31:06', 'E');
INSERT INTO `ms_image` VALUES ('34', '6', 'ROOM', 'https://cbucbm.club/f398cb6a-5bf7-4446-b465-c3e6cdc6c805', '2020-02-13 21:31:06', '2020-02-13 21:31:06', 'E');
INSERT INTO `ms_image` VALUES ('35', '5', 'ROOM', 'https://cbucbm.club/416f0da5-56a0-48fb-b13e-1ab5837f98cd', '2020-02-13 21:32:06', '2020-02-13 21:32:06', 'E');
INSERT INTO `ms_image` VALUES ('36', '5', 'ROOM', 'https://cbucbm.club/24070904-7d2d-4928-8175-1f27f9856731', '2020-02-13 21:32:06', '2020-02-13 21:32:06', 'E');
INSERT INTO `ms_image` VALUES ('37', '5', 'ROOM', 'https://cbucbm.club/e2aeded8-4d2a-46f6-8bee-eb1bfb194fe6', '2020-02-13 21:32:07', '2020-02-13 21:32:07', 'E');
INSERT INTO `ms_image` VALUES ('38', '3', 'ROOM', 'https://cbucbm.club/f0511545-1d51-408b-846e-5f29533754c1', '2020-02-13 21:32:58', '2020-02-13 21:32:58', 'E');
INSERT INTO `ms_image` VALUES ('39', '3', 'ROOM', 'https://cbucbm.club/8a7ccef9-f595-446a-ae76-8c5dc1a2d4e7', '2020-02-13 21:32:58', '2020-02-13 21:32:58', 'E');
INSERT INTO `ms_image` VALUES ('40', '3', 'ROOM', 'https://cbucbm.club/ba155bfd-69da-426c-82a7-0cc6444569bb', '2020-02-13 21:32:59', '2020-02-13 21:32:59', 'E');
INSERT INTO `ms_image` VALUES ('41', '2', 'ROOM', 'https://cbucbm.club/84bbdac8-2f08-41b6-9005-ceaa813c43fb', '2020-02-13 21:34:07', '2020-02-13 21:34:07', 'E');
INSERT INTO `ms_image` VALUES ('42', '2', 'ROOM', 'https://cbucbm.club/5308b1dc-17ee-4a80-93e0-363e829de6c7', '2020-02-13 21:34:07', '2020-02-13 21:34:07', 'E');
INSERT INTO `ms_image` VALUES ('43', '2', 'ROOM', 'https://cbucbm.club/6a06e61a-d74c-45aa-b652-dd675e9f6acd', '2020-02-13 21:34:08', '2020-02-13 21:34:08', 'E');
INSERT INTO `ms_image` VALUES ('44', '1', 'ROOM', 'https://cbucbm.club/f13a3d60-85e3-40a4-85b6-bde787016db8', '2020-02-13 21:34:48', '2020-02-13 21:34:48', 'E');
INSERT INTO `ms_image` VALUES ('45', '1', 'ROOM', 'https://cbucbm.club/a10f53c0-863a-49f3-9a90-6a1eb44917e8', '2020-02-13 21:34:48', '2020-02-13 21:34:48', 'E');
INSERT INTO `ms_image` VALUES ('46', '1', 'ROOM', 'https://cbucbm.club/ef7f3f2f-4a5a-40d6-aa02-599101bc4dde', '2020-02-13 21:34:49', '2020-02-13 21:34:49', 'E');
INSERT INTO `ms_image` VALUES ('47', '4', 'ROOM', 'https://cbucbm.club/0227646e-35ce-4ab3-bc81-7e3525986b36', '2020-02-13 23:23:56', '2020-02-13 23:23:56', 'E');
INSERT INTO `ms_image` VALUES ('48', '4', 'ROOM', 'https://cbucbm.club/660b005a-d50c-4915-aaa4-052dabf678a5', '2020-02-13 23:23:57', '2020-02-13 23:23:57', 'E');
INSERT INTO `ms_image` VALUES ('49', '4', 'ROOM', 'https://cbucbm.club/571474e3-807c-46dd-b47c-d4ce6f9ad7e4', '2020-02-13 23:23:58', '2020-02-13 23:23:58', 'E');
INSERT INTO `ms_image` VALUES ('50', '4', 'ROOM', 'https://cbucbm.club/9877924c-2eef-422d-8166-c7e151b249dc', '2020-02-13 23:23:58', '2020-02-13 23:24:20', 'D');
INSERT INTO `ms_image` VALUES ('51', '4', 'ROOM', 'https://cbucbm.club/93512915-fa21-441d-b73c-b9f8d0ac1793', '2020-02-13 23:23:59', '2020-02-13 23:24:23', 'D');
INSERT INTO `ms_image` VALUES ('52', '4', 'ROOM', 'https://cbucbm.club/aa351ba6-d33d-46f2-8450-53141e6def18', '2020-02-13 23:23:59', '2020-02-13 23:24:24', 'D');
INSERT INTO `ms_image` VALUES ('53', '13', 'ROOM', 'https://cbucbm.club/5dae7244-8267-4fd3-bbdf-1396f32b99d0', '2020-02-27 17:46:31', '2020-02-27 17:46:31', 'E');
INSERT INTO `ms_image` VALUES ('54', '13', 'ROOM', 'https://cbucbm.club/532f1995-1cea-413c-9e28-31a2884e89b9', '2020-02-27 17:46:31', '2020-02-27 17:46:31', 'E');
INSERT INTO `ms_image` VALUES ('55', '13', 'ROOM', 'https://cbucbm.club/569e8e61-6897-4ab7-b108-6d88f00b9f47', '2020-02-27 17:46:32', '2020-02-27 17:46:32', 'E');
INSERT INTO `ms_image` VALUES ('56', '12', 'ROOM', 'https://cbucbm.club/9029e72e-bc11-4190-a2f1-3af62375ec28', '2020-02-28 12:36:48', '2020-02-28 12:57:39', 'D');
INSERT INTO `ms_image` VALUES ('58', '12', 'ROOM', 'https://cbucbm.club/55d75e34-0dfe-4a0a-936b-b7e662796a27', '2020-02-28 12:57:45', '2020-02-28 12:57:45', 'E');
INSERT INTO `ms_image` VALUES ('59', '16', 'ROOM', 'https://cbucbm.club/aee46b45-12af-4175-b6f5-a9e9c220c5d4', '2020-02-28 14:46:25', '2020-02-28 14:46:25', 'E');
INSERT INTO `ms_image` VALUES ('60', '16', 'ROOM', 'https://cbucbm.club/664e955e-d045-486c-9136-3c5e256989ab', '2020-02-28 14:46:25', '2020-02-28 14:46:25', 'E');
INSERT INTO `ms_image` VALUES ('61', '16', 'ROOM', 'https://cbucbm.club/0c8de041-a53e-42ed-bc58-dbad0ecd766b', '2020-02-28 14:46:25', '2020-02-28 14:46:25', 'E');
INSERT INTO `ms_image` VALUES ('62', '18', 'ROOM', 'https://cbucbm.club/b87f4908', '2020-05-22 23:57:02', '2020-05-22 23:57:02', 'E');

-- ----------------------------
-- Table structure for ms_merchant
-- ----------------------------
DROP TABLE IF EXISTS `ms_merchant`;
CREATE TABLE `ms_merchant` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `AUDIT_ID` bigint DEFAULT NULL COMMENT '申请表ID',
  `MNAME` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商家名称',
  `MPHONE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商家联系电话',
  `MACCOUNT` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商家账号',
  `MPWD` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商家密码',
  `MLEVEL` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商家等级    ADMIN: 超级管理员  GOLD：金牌商家   SLIVER：银牌商家   COPPER：铜牌商家    NORMAL：普通商家',
  `MDESC` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房东介绍',
  `AVATAR_URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像地址',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'E' COMMENT '状态      D：已失效   E：生效中',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_merchant
-- ----------------------------
INSERT INTO `ms_merchant` VALUES ('1', '1', '系统管理员', '13599996688', 'admin', 'admin', 'ADMIN', null, 'https://cbucbm.club/cbuc.png', '2020-01-02 17:07:30', '2020-02-26 16:35:29', 'E');
INSERT INTO `ms_merchant` VALUES ('8', '1', '北墘4号小屋', '17689400062', '123456', '123456789', 'COPPER', '这是房东描述。这是房东描述。这是房东描述。这是房东描述。这是房东描述。这是房东描述。这是房东描述。', 'https://cbucbm.club/60d82ed6', '2020-01-11 23:11:12', '2020-05-18 23:37:21', 'E');

-- ----------------------------
-- Table structure for ms_message
-- ----------------------------
DROP TABLE IF EXISTS `ms_message`;
CREATE TABLE `ms_message` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `SEND_ID` bigint DEFAULT NULL COMMENT '发送方',
  `SEND_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发送方类型      ADMIN：超级管理员   MERCHANT：商户    USER：用户',
  `RECEIVE_ID` bigint DEFAULT NULL COMMENT '通知方',
  `RECEIVE_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '接收方类型        ADMIN：超级管理员   MERCHANT：商户    USER：用户',
  `CONTENT` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通知内容',
  `READ_STATUS` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'WR' COMMENT '读取状态     WR：未读   YR：已读   D：删除',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `STATUS` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'E' COMMENT '状态   E：生效    D：失效',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_message
-- ----------------------------
INSERT INTO `ms_message` VALUES ('3', '1', 'ADMIN', '8', 'MERCHANT', '请看公告！', 'YR', '2020-05-18 23:36:49', '2020-05-22 22:51:48', 'E');
INSERT INTO `ms_message` VALUES ('4', '1', 'ADMIN', '8', 'MERCHANT', '今晚开会！', 'YR', '2020-05-18 23:37:04', '2020-05-22 22:51:46', 'E');
INSERT INTO `ms_message` VALUES ('8', '8', 'MERCHANT', '1', 'ADMIN', '收到！', 'WR', '2020-05-23 00:03:14', '2020-05-23 00:03:18', 'E');

-- ----------------------------
-- Table structure for ms_news
-- ----------------------------
DROP TABLE IF EXISTS `ms_news`;
CREATE TABLE `ms_news` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PUBLISH_ID` bigint DEFAULT NULL COMMENT '发布人ID',
  `TITLE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章标题',
  `SUMMARY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章摘要',
  `CONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '文章内容',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `BEGIN_TIME` datetime DEFAULT NULL COMMENT '开始展示时间',
  `END_TIME` datetime DEFAULT NULL COMMENT '结束展示时间',
  `AUDIT_STATUS` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'WA' COMMENT '审核状态   WA：待审核   SA：审核通过   FA：审核不通过',
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'E' COMMENT '状态    E：生效中   D：已失效',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_news
-- ----------------------------
INSERT INTO `ms_news` VALUES ('1', '8', '走进北墘', '带您领略北墘的民风民俗', '<p style=\"white-space:normal;\">\n	<img src=\"https://cbucbm.club/7e0638a4-6215-4511-bb42-012664ce1334\" alt=\"\" width=\"400\" height=\"200\" title=\"\" align=\"\" />\n</p>\n<div class=\"para\" label-module=\"para\" style=\"white-space:normal;font-size:14px;color:#333333;margin-bottom:15px;text-indent:2em;line-height:24px;zoom:1;font-family:arial, 宋体, sans-serif;background-color:#FFFFFF;\">\n	一、基本村情<span style=\"font-weight:700;\"></span>\n</div>\n<div class=\"para\" label-module=\"para\" style=\"white-space:normal;font-size:14px;color:#333333;margin-bottom:15px;text-indent:2em;line-height:24px;zoom:1;font-family:arial, 宋体, sans-serif;background-color:#FFFFFF;\">\n	北墘村位于屏南县代溪镇东南部，村落面积28平方公里，现全村共609户2290人，从事老酒酿造农户占全村农户99%，村落传统格局保持完整，传统建筑中大部分是古民居，同时种类丰富，有祠堂、庙宇、门楼、炮楼、歇亭、水碓房和廊桥等。2014北墘村被列入第三批中国传统村落，2017年获得中国传统建筑文化旅游目的地称号。2016年以来，北墘村成功举办了三届黄酒文化节，活动举办时期，福建新闻频道、海博TV等栏目都在北墘村开展直播活动，央视也转播了黄酒文化节的黄酒开酿祈福仪式。2019年北墘黄酒文化旅游景区被评为国家3A级景区。随着2019年7月代溪至虎贝二级路及后忠线的通车，北墘至宁德蕉城区自驾车程为1个小时，至福州荆溪高速口自驾车程为2个小时，至屏南甘棠高速口自驾车程为半个小时，至福州长乐机场自驾车程为2.5小时，至古田动车站自驾车程为1个小时。<span style=\"font-family:微软雅黑;font-size:16px;\"></span>\n</div>\n<span style=\"white-space:normal;\"></span><span style=\"white-space:normal;\"></span>\n<p style=\"white-space:normal;\">\n	<img src=\"https://cbucbm.club/7e0638a4-6215-4511-bb42-012664ce1334\" alt=\"\" width=\"400\" height=\"200\" title=\"\" align=\"\" />\n</p>\n<div class=\"para\" label-module=\"para\" style=\"white-space:normal;font-size:14px;color:#333333;margin-bottom:15px;text-indent:2em;line-height:24px;zoom:1;font-family:arial, 宋体, sans-serif;background-color:#FFFFFF;\">\n	一、基本村情<span style=\"font-weight:700;\"></span>\n</div>\n<div class=\"para\" label-module=\"para\" style=\"white-space:normal;font-size:14px;color:#333333;margin-bottom:15px;text-indent:2em;line-height:24px;zoom:1;font-family:arial, 宋体, sans-serif;background-color:#FFFFFF;\">\n	北墘村位于屏南县代溪镇东南部，村落面积28平方公里，现全村共609户2290人，从事老酒酿造农户占全村农户99%，村落传统格局保持完整，传统建筑中大部分是古民居，同时种类丰富，有祠堂、庙宇、门楼、炮楼、歇亭、水碓房和廊桥等。2014北墘村被列入第三批中国传统村落，2017年获得中国传统建筑文化旅游目的地称号。2016年以来，北墘村成功举办了三届黄酒文化节，活动举办时期，福建新闻频道、海博TV等栏目都在北墘村开展直播活动，央视也转播了黄酒文化节的黄酒开酿祈福仪式。2019年北墘黄酒文化旅游景区被评为国家3A级景区。随着2019年7月代溪至虎贝二级路及后忠线的通车，北墘至宁德蕉城区自驾车程为1个小时，至福州荆溪高速口自驾车程为2个小时，至屏南甘棠高速口自驾车程为半个小时，至福州长乐机场自驾车程为2.5小时，至古田动车站自驾车程为1个小时。<span style=\"font-family:微软雅黑;font-size:16px;\"></span>\n</div>\n<span style=\"white-space:normal;\"></span><span style=\"white-space:normal;\"></span>\n<p style=\"white-space:normal;\">\n	<img src=\"https://cbucbm.club/7e0638a4-6215-4511-bb42-012664ce1334\" alt=\"\" width=\"400\" height=\"200\" title=\"\" align=\"\" />\n</p>\n<div class=\"para\" label-module=\"para\" style=\"white-space:normal;font-size:14px;color:#333333;margin-bottom:15px;text-indent:2em;line-height:24px;zoom:1;font-family:arial, 宋体, sans-serif;background-color:#FFFFFF;\">\n	一、基本村情<span style=\"font-weight:700;\"></span>\n</div>\n<div class=\"para\" label-module=\"para\" style=\"white-space:normal;font-size:14px;color:#333333;margin-bottom:15px;text-indent:2em;line-height:24px;zoom:1;font-family:arial, 宋体, sans-serif;background-color:#FFFFFF;\">\n	北墘村位于屏南县代溪镇东南部，村落面积28平方公里，现全村共609户2290人，从事老酒酿造农户占全村农户99%，村落传统格局保持完整，传统建筑中大部分是古民居，同时种类丰富，有祠堂、庙宇、门楼、炮楼、歇亭、水碓房和廊桥等。2014北墘村被列入第三批中国传统村落，2017年获得中国传统建筑文化旅游目的地称号。2016年以来，北墘村成功举办了三届黄酒文化节，活动举办时期，福建新闻频道、海博TV等栏目都在北墘村开展直播活动，央视也转播了黄酒文化节的黄酒开酿祈福仪式。2019年北墘黄酒文化旅游景区被评为国家3A级景区。随着2019年7月代溪至虎贝二级路及后忠线的通车，北墘至宁德蕉城区自驾车程为1个小时，至福州荆溪高速口自驾车程为2个小时，至屏南甘棠高速口自驾车程为半个小时，至福州长乐机场自驾车程为2.5小时，至古田动车站自驾车程为1个小时。\n</div>', '2020-03-09 16:32:22', '2020-03-11 15:47:27', '2020-03-09 16:31:18', '2020-10-10 20:17:58', 'SA', 'E');
INSERT INTO `ms_news` VALUES ('16', '1', '屏南北墘', '屏南北墘，一个让你沉醉不知归处的古村 ', '<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<span style=\"border:0px;margin:0px;padding:0px;\">冬至大如年，阴极而阳生，一个循环的开始。</span>\n</p>\n<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<img src=\"http://img.mp.sohu.com/upload/20170816/33fe004329594ae7b071d222cd7be047_th.png\" style=\"margin:10px auto 0px;padding:0px;display:block;max-width:100%;height:auto;\" width=\"800\" height=\"450\" title=\"\" align=\"\" alt=\"\" />\n</p>\n<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<span style=\"border:0px;margin:0px;padding:0px;\">冬至于屏南，不仅是个节庆，更是酿酒季的开始。</span>\n</p>\n<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<img src=\"http://img.mp.sohu.com/upload/20170816/257963d38d884d38981561aaa73ac9c5_th.png\" style=\"margin:10px auto 0px;padding:0px;display:block;max-width:100%;height:auto;\" width=\"800\" height=\"450\" title=\"\" align=\"\" alt=\"\" />\n</p>\n<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<span style=\"border:0px;margin:0px;padding:0px;\">屏南，黄酒文化之乡，保存着丰厚的红曲，继承与保留千年的黄酒酿造技艺和传统。</span>\n</p>\n<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<img src=\"http://img.mp.sohu.com/upload/20170816/7db969130d834a60b8e8eeff15e8cafd_th.png\" style=\"margin:10px auto 0px;padding:0px;display:block;max-width:100%;height:auto;\" width=\"800\" height=\"450\" title=\"\" align=\"\" alt=\"\" />\n</p>\n<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<span style=\"border:0px;margin:0px;padding:0px;\">北墘村，屏南黄酒传统酿造之乡，让我们看到了传统技艺的传承和其中包含着的先人们的智慧。</span>\n</p>\n<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<img src=\"http://img.mp.sohu.com/upload/20170816/281f809cacbe458ca5dd6dd52362fcaa_th.png\" style=\"margin:10px auto 0px;padding:0px;display:block;max-width:100%;height:auto;\" width=\"800\" height=\"450\" title=\"\" align=\"\" alt=\"\" />\n</p>\n<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<span style=\"border:0px;margin:0px;padding:0px;\">酒依时令而酿造，曲种、醋母代代相传，这些都反映出对自然的崇敬和遵行自然法则行事的天人合一的理念。</span>\n</p>\n<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<img src=\"http://img.mp.sohu.com/upload/20170816/135daadb7b0c4001b1efb6efcc280b7e_th.png\" style=\"margin:10px auto 0px;padding:0px;display:block;max-width:100%;height:auto;\" width=\"800\" height=\"450\" title=\"\" align=\"\" alt=\"\" />\n</p>\n<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<span style=\"border:0px;margin:0px;padding:0px;\">屏南红曲制作和黄酒酿造技艺带着远古的气息而来，后劲又足。</span>\n</p>\n<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<img src=\"http://img.mp.sohu.com/upload/20170816/e8e31ff9a8dc49e9aabbb2de645e4a17_th.png\" style=\"margin:10px auto 0px;padding:0px;display:block;max-width:100%;height:auto;\" width=\"800\" height=\"450\" title=\"\" align=\"\" alt=\"\" />\n</p>\n<p style=\"border:0px;margin-top:0.63em;margin-bottom:1.8em;padding:0px;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;color:#191919;font-family:&quot;PingFang SC&quot;, Arial, 微软雅黑, 宋体, simsun, sans-serif;white-space:normal;background-color:#FFFFFF;\">\n	<span style=\"border:0px;margin:0px;padding:0px;\">陈年饮之，三五碗后，便如登仙境，所有迷离般的快乐都会从这甜甜的酒中迸发，那一丝牵动人心的乡愁，便弥散开来。</span>\n</p>', '2020-03-11 14:30:32', '2020-03-11 14:32:45', '2020-03-11 14:29:23', '2021-03-12 18:16:03', 'SA', 'E');
INSERT INTO `ms_news` VALUES ('21', '8', '123123', '123123', '123123', '2020-04-07 09:30:39', '2020-04-07 09:31:00', '2020-04-07 09:30:20', '2020-04-08 13:17:00', 'WA', 'D');
INSERT INTO `ms_news` VALUES ('22', '1', '屏南北墘村：文旅融合赋能乡村振兴', '', '<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, \" pingfang=\"\" sc\",=\"\" \"droid=\"\" sans\",=\"\" \"hiragino=\"\" sans=\"\" gb\",=\"\" stxihei,=\"\" sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#ffffff;text-decoration-style:initial;text-decoration-color:initial;\"=\"\">\n	&emsp;诗意北墘，黄酒飘香。\n	</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, \" pingfang=\"\" sc\",=\"\" \"droid=\"\" sans\",=\"\" \"hiragino=\"\" sans=\"\" gb\",=\"\" stxihei,=\"\" sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#ffffff;text-decoration-style:initial;text-decoration-color:initial;\"=\"\">\n	&emsp;&emsp;春末夏初，漫步屏南县代溪镇北墘村，仿佛置身于一幅优美的山水画中，古朴的小村里，酒坊林立、酒旗飘扬；水碓房、水车、廊桥、矴步相连，柽树、老柿、花花草草点缀其间……乡村美景伴着醇厚酒香，成为游客心中美好的记忆。\n</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, \" pingfang=\"\" sc\",=\"\" \"droid=\"\" sans\",=\"\" \"hiragino=\"\" sans=\"\" gb\",=\"\" stxihei,=\"\" sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#ffffff;text-decoration-style:initial;text-decoration-color:initial;\"=\"\">\n	&emsp;&emsp;在北墘村拥有700多年历史的“六角井”旁，工匠们正对一幢古民居进行修缮改造。6月份，这幢原本破败不堪的古民居将摇身一变，成为极具特色的中高端民宿，迎接八方游客。“目前，北墘村正在修缮的古民居有两栋，都将在6月份对外营业。”刘冰是<span id=\"stock_013855\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\"><a href=\"https://wap.eastmoney.com/quota/stock/index/013855\" class=\"keytip\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;background-color:transparent;text-decoration:none;color:#3074C2;\">上海复旦</a></span><span id=\"quote_013855\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\"></span>大学共享城市研究室的主任，另一重身份是北墘旅游<span href=\"http://baike.eastmoney.com/item/总体设计\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">总体设计</span>师，去年12月，因缘巧合下他来到北墘村参加黄酒<span href=\"http://baike.eastmoney.com/item/文化\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">文化</span>节，没想到对这个村庄“一见钟情”。\n	</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, \" pingfang=\"\" sc\",=\"\" \"droid=\"\" sans\",=\"\" \"hiragino=\"\" sans=\"\" gb\",=\"\" stxihei,=\"\" sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#ffffff;text-decoration-style:initial;text-decoration-color:initial;\"=\"\">\n	&emsp;&emsp;这里四面青山环抱，一条清澈溪流穿村而过，村落传统格局保持完整，传统建筑中大部分是古民居，同时种类丰富，有祠堂、庙宇、门楼、炮楼、歇亭、水碓房和廊桥等。随着代溪至虎贝的二级路及后忠线在2019年9月份开通，福州荆溪高速口至北墘自驾只需2个小时，宁德蕉城区至北墘自驾只需1小时10分钟，<span href=\"http://baike.eastmoney.com/item/交通\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">交通</span>便利。\n</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, \" pingfang=\"\" sc\",=\"\" \"droid=\"\" sans\",=\"\" \"hiragino=\"\" sans=\"\" gb\",=\"\" stxihei,=\"\" sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#ffffff;text-decoration-style:initial;text-decoration-color:initial;\"=\"\">\n	&emsp;&emsp;便捷的交通条件、优美的自然风光和深厚的人文底蕴，让刘冰决定留在北墘村。今年1月份，北墘村与上海申链文化旅游发展有限<span href=\"http://baike.eastmoney.com/item/公司\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">公司</span>签订合作协议，刘冰则作为总设计师于3月10日带领设计<span href=\"http://baike.eastmoney.com/item/团队\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">团队</span>进驻北墘，扎根于此。\n	</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, \" pingfang=\"\" sc\",=\"\" \"droid=\"\" sans\",=\"\" \"hiragino=\"\" sans=\"\" gb\",=\"\" stxihei,=\"\" sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#ffffff;text-decoration-style:initial;text-decoration-color:initial;\"=\"\">\n	&emsp;&emsp;近两个月以来，刘冰团队走过北墘村的每一个角落，进行村庄历史调查、现场测绘、项目<span href=\"http://baike.eastmoney.com/item/需求\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">需求</span>确认等，为下一步乡村<span href=\"http://baike.eastmoney.com/item/规划\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">规划</span>奠定了基础。为了引导村民加入进来，带动乡村的建设发展，刘冰认领了6栋古民居，进行改造升级，除了正在建设的两栋民宿，他们还改造了一个餐厅，五一期间已经对外营业了。\n</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, \" pingfang=\"\" sc\",=\"\" \"droid=\"\" sans\",=\"\" \"hiragino=\"\" sans=\"\" gb\",=\"\" stxihei,=\"\" sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#ffffff;text-decoration-style:initial;text-decoration-color:initial;\"=\"\">\n	&emsp;&emsp;“北墘村发展的主题<span href=\"http://baike.eastmoney.com/item/定位\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">定位</span>以黄酒<span href=\"http://baike.eastmoney.com/item/产业\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">产业</span>为核心，构建一条产业与文化旅游融合发展的，集影视制作、包装<span href=\"http://baike.eastmoney.com/item/创意\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">创意</span>设计、黄酒技艺培训、<span href=\"http://baike.eastmoney.com/item/商业\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">商业</span>运营和衍生<span href=\"http://baike.eastmoney.com/item/产品\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">产品</span>开发的文化旅游地。”北墘村党支部书记吴孝亩告诉记者，村里成立了景区旅游开发公司<span href=\"http://baike.eastmoney.com/item/租赁\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">租赁</span>修缮古民居，完成了农耕文化展示馆、红色文化展示馆、黄酒文化展示馆、党校、祭酒堂等文化展馆的建设，完成对吴氏宗祠、佛仔厝、炮楼、哨楼、攀龙桥、古井、古树等构成村落历史风貌要素的保护修缮<span href=\"http://baike.eastmoney.com/item/工作\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">工作</span>，不断吸引外来人员到此休闲度假、摄影采风。并先后与福建阳光学院、复旦大学等院校合作，通过院校人才力量助推北墘导览系统、北墘文化收集提炼、北墘特色文创产品研发等方面项目。\n	</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, \" pingfang=\"\" sc\",=\"\" \"droid=\"\" sans\",=\"\" \"hiragino=\"\" sans=\"\" gb\",=\"\" stxihei,=\"\" sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#ffffff;text-decoration-style:initial;text-decoration-color:initial;\"=\"\">\n	&emsp;&emsp;要想让游<span href=\"http://baike.eastmoney.com/item/客流\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">客流</span>连忘返，必须得让他们住得有品位、吃得有美味。为此，北墘村共收储到35栋民房(租期20年)，并对35栋民房进行主体加固修缮，现已外租12栋。部分乡贤也有自己打造民宿的想法，对此，北墘村积极鼓励。在餐饮业方面，北墘村拟通过幸福工程<span href=\"http://baike.eastmoney.com/item/资金\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">资金</span>帮扶的方式帮助村里计生户和贫困户进行<span href=\"http://baike.eastmoney.com/item/合作经营\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">合作经营</span>，鼓励打造北墘9碗糟和药膳系列餐饮。“我们将对本村旅游业进行<span href=\"http://baike.eastmoney.com/item/市场\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">市场</span>规范，如对民宿的价位进行分低中高三个档次统一<span href=\"http://baike.eastmoney.com/item/定价\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">定价</span>规范<span href=\"http://baike.eastmoney.com/item/管理\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">管理</span>、对餐费标准<span href=\"http://baike.eastmoney.com/item/合理化\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">合理化</span>等，让游客吃得放心、住得舒心，让旅游真正成为调整经济结构、实现富民强村的产业。”吴孝亩说。\n</p>', '2020-05-18 19:58:14', '2020-05-18 19:59:15', '2020-05-18 19:57:36', '2021-05-19 23:44:16', 'SA', 'D');
INSERT INTO `ms_news` VALUES ('23', '1', '屏南北墘村：文旅融合赋能乡村振兴', '', '<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, &quot;PingFang SC&quot;, &quot;Droid Sans&quot;, &quot;Hiragino Sans GB&quot;, STXihei, sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#FFFFFF;text-decoration-style:initial;text-decoration-color:initial;\">\n	&emsp;诗意北墘，黄酒飘香。\n</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, &quot;PingFang SC&quot;, &quot;Droid Sans&quot;, &quot;Hiragino Sans GB&quot;, STXihei, sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#FFFFFF;text-decoration-style:initial;text-decoration-color:initial;\">\n	&emsp;&emsp;春末夏初，漫步屏南县代溪镇北墘村，仿佛置身于一幅优美的山水画中，古朴的小村里，酒坊林立、酒旗飘扬；水碓房、水车、廊桥、矴步相连，柽树、老柿、花花草草点缀其间……乡村美景伴着醇厚酒香，成为游客心中美好的记忆。\n</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, &quot;PingFang SC&quot;, &quot;Droid Sans&quot;, &quot;Hiragino Sans GB&quot;, STXihei, sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#FFFFFF;text-decoration-style:initial;text-decoration-color:initial;\">\n	&emsp;&emsp;在北墘村拥有700多年历史的“六角井”旁，工匠们正对一幢古民居进行修缮改造。6月份，这幢原本破败不堪的古民居将摇身一变，成为极具特色的中高端民宿，迎接八方游客。“目前，北墘村正在修缮的古民居有两栋，都将在6月份对外营业。”刘冰是<span id=\"stock_013855\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\"><a href=\"https://wap.eastmoney.com/quota/stock/index/013855\" class=\"keytip\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;background-color:transparent;text-decoration:none;color:#3074C2;\">上海复旦</a></span><span id=\"quote_013855\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\"></span>大学共享城市研究室的主任，另一重身份是北墘旅游<span href=\"http://baike.eastmoney.com/item/总体设计\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">总体设计</span>师，去年12月，因缘巧合下他来到北墘村参加黄酒<span href=\"http://baike.eastmoney.com/item/文化\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">文化</span>节，没想到对这个村庄“一见钟情”。\n</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, &quot;PingFang SC&quot;, &quot;Droid Sans&quot;, &quot;Hiragino Sans GB&quot;, STXihei, sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#FFFFFF;text-decoration-style:initial;text-decoration-color:initial;\">\n	&emsp;&emsp;这里四面青山环抱，一条清澈溪流穿村而过，村落传统格局保持完整，传统建筑中大部分是古民居，同时种类丰富，有祠堂、庙宇、门楼、炮楼、歇亭、水碓房和廊桥等。随着代溪至虎贝的二级路及后忠线在2019年9月份开通，福州荆溪高速口至北墘自驾只需2个小时，宁德蕉城区至北墘自驾只需1小时10分钟，<span href=\"http://baike.eastmoney.com/item/交通\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">交通</span>便利。\n</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, &quot;PingFang SC&quot;, &quot;Droid Sans&quot;, &quot;Hiragino Sans GB&quot;, STXihei, sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#FFFFFF;text-decoration-style:initial;text-decoration-color:initial;\">\n	&emsp;&emsp;便捷的交通条件、优美的自然风光和深厚的人文底蕴，让刘冰决定留在北墘村。今年1月份，北墘村与上海申链文化旅游发展有限<span href=\"http://baike.eastmoney.com/item/公司\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">公司</span>签订合作协议，刘冰则作为总设计师于3月10日带领设计<span href=\"http://baike.eastmoney.com/item/团队\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">团队</span>进驻北墘，扎根于此。\n</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, &quot;PingFang SC&quot;, &quot;Droid Sans&quot;, &quot;Hiragino Sans GB&quot;, STXihei, sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#FFFFFF;text-decoration-style:initial;text-decoration-color:initial;\">\n	&emsp;&emsp;近两个月以来，刘冰团队走过北墘村的每一个角落，进行村庄历史调查、现场测绘、项目<span href=\"http://baike.eastmoney.com/item/需求\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">需求</span>确认等，为下一步乡村<span href=\"http://baike.eastmoney.com/item/规划\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">规划</span>奠定了基础。为了引导村民加入进来，带动乡村的建设发展，刘冰认领了6栋古民居，进行改造升级，除了正在建设的两栋民宿，他们还改造了一个餐厅，五一期间已经对外营业了。\n</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, &quot;PingFang SC&quot;, &quot;Droid Sans&quot;, &quot;Hiragino Sans GB&quot;, STXihei, sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#FFFFFF;text-decoration-style:initial;text-decoration-color:initial;\">\n	&emsp;&emsp;“北墘村发展的主题<span href=\"http://baike.eastmoney.com/item/定位\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">定位</span>以黄酒<span href=\"http://baike.eastmoney.com/item/产业\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">产业</span>为核心，构建一条产业与文化旅游融合发展的，集影视制作、包装<span href=\"http://baike.eastmoney.com/item/创意\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">创意</span>设计、黄酒技艺培训、<span href=\"http://baike.eastmoney.com/item/商业\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">商业</span>运营和衍生<span href=\"http://baike.eastmoney.com/item/产品\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">产品</span>开发的文化旅游地。”北墘村党支部书记吴孝亩告诉记者，村里成立了景区旅游开发公司<span href=\"http://baike.eastmoney.com/item/租赁\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">租赁</span>修缮古民居，完成了农耕文化展示馆、红色文化展示馆、黄酒文化展示馆、党校、祭酒堂等文化展馆的建设，完成对吴氏宗祠、佛仔厝、炮楼、哨楼、攀龙桥、古井、古树等构成村落历史风貌要素的保护修缮<span href=\"http://baike.eastmoney.com/item/工作\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">工作</span>，不断吸引外来人员到此休闲度假、摄影采风。并先后与福建阳光学院、复旦大学等院校合作，通过院校人才力量助推北墘导览系统、北墘文化收集提炼、北墘特色文创产品研发等方面项目。\n</p>\n<p style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;margin:0px 0px 1.5rem;padding:0px;color:#333333;font-family:Arial, Roboto, &quot;PingFang SC&quot;, &quot;Droid Sans&quot;, &quot;Hiragino Sans GB&quot;, STXihei, sans-serif;font-size:22.8px;font-style:normal;font-variant-ligatures:normal;font-variant-caps:normal;font-weight:400;letter-spacing:normal;orphans:2;text-align:start;text-indent:0px;text-transform:none;white-space:normal;widows:2;word-spacing:0px;-webkit-text-stroke-width:0px;background-color:#FFFFFF;text-decoration-style:initial;text-decoration-color:initial;\">\n	&emsp;&emsp;要想让游<span href=\"http://baike.eastmoney.com/item/客流\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">客流</span>连忘返，必须得让他们住得有品位、吃得有美味。为此，北墘村共收储到35栋民房(租期20年)，并对35栋民房进行主体加固修缮，现已外租12栋。部分乡贤也有自己打造民宿的想法，对此，北墘村积极鼓励。在餐饮业方面，北墘村拟通过幸福工程<span href=\"http://baike.eastmoney.com/item/资金\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">资金</span>帮扶的方式帮助村里计生户和贫困户进行<span href=\"http://baike.eastmoney.com/item/合作经营\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">合作经营</span>，鼓励打造北墘9碗糟和药膳系列餐饮。“我们将对本村旅游业进行<span href=\"http://baike.eastmoney.com/item/市场\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">市场</span>规范，如对民宿的价位进行分低中高三个档次统一<span href=\"http://baike.eastmoney.com/item/定价\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">定价</span>规范<span href=\"http://baike.eastmoney.com/item/管理\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">管理</span>、对餐费标准<span href=\"http://baike.eastmoney.com/item/合理化\" class=\"em_stock_key_common\" target=\"_blank\" style=\"-webkit-tap-highlight-color:transparent;overflow-wrap:break-word;word-break:break-all;box-sizing:border-box;\">合理化</span>等，让游客吃得放心、住得舒心，让旅游真正成为调整经济结构、实现富民强村的产业。”吴孝亩说。\n</p>', '2020-05-18 19:59:33', '2020-05-18 19:59:42', '2020-05-18 19:57:36', '2021-05-19 23:44:16', 'SA', 'E');
INSERT INTO `ms_news` VALUES ('24', '8', '测试', '测试', '<p>\n	11111111\n</p>\n<p>\n	<img src=\"https://cbucbm.club/0bb99a9a-cd36-4072-8d43-564d0b73cb58\" alt=\"\" width=\"889\" height=\"500\" title=\"\" align=\"\" />\n</p>', '2020-05-18 20:27:43', '2020-05-18 20:28:18', '2020-05-18 20:27:05', '2020-05-20 00:13:45', 'SA', 'D');

-- ----------------------------
-- Table structure for ms_order
-- ----------------------------
DROP TABLE IF EXISTS `ms_order`;
CREATE TABLE `ms_order` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `RID` bigint DEFAULT NULL COMMENT '房间ID',
  `OPEN_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户标识ID',
  `NAME` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '入住用户名称',
  `CARDNO` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户身份证号',
  `PHONE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户号码',
  `ORDER_CODE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单编号',
  `DAY_COUNT` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '居住时长',
  `PRICE` float DEFAULT NULL COMMENT '价格',
  `COMMENT` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单留言',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `PAY_TIME` datetime DEFAULT NULL COMMENT '付款时间',
  `CONFIRM_TIME` datetime DEFAULT NULL COMMENT '收货时间',
  `BEGIN_TIME` datetime DEFAULT NULL COMMENT '入住时间',
  `END_TIME` datetime DEFAULT NULL COMMENT '退房时间',
  `STATUS` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'WP' COMMENT '订单状态    WP：待付款   WR：待评价  YR：已评价  WDD：退款中  SDD：退款成功  FDD：退款失败  D：删除',
  `READ_STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'N' COMMENT '已读状态  Y：已读  N：未读',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_order
-- ----------------------------

-- ----------------------------
-- Table structure for ms_property
-- ----------------------------
DROP TABLE IF EXISTS `ms_property`;
CREATE TABLE `ms_property` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `RID` bigint DEFAULT NULL COMMENT '房间ID',
  `PRO_KEY` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性名',
  `PRO_VALUE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性值',
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'E' COMMENT '状态    E：生效中   D：已失效',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=265 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_property
-- ----------------------------
INSERT INTO `ms_property` VALUES ('1', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('2', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('3', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('4', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('5', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('6', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('7', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('8', '12', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('9', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('10', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('11', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('12', '12', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('13', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('14', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('15', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('16', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('17', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('18', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('19', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('20', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('21', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('22', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('23', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('24', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('25', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('26', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('27', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('28', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('37', '9', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('38', '9', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('39', '9', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('40', '9', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('41', '8', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('42', '8', '张床', '2', 'E');
INSERT INTO `ms_property` VALUES ('43', '8', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('44', '8', '人可住', '3', 'E');
INSERT INTO `ms_property` VALUES ('45', '7', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('46', '7', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('47', '7', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('48', '7', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('49', '6', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('50', '6', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('51', '6', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('52', '6', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('53', '5', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('54', '5', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('55', '5', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('56', '5', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('57', '3', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('58', '3', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('59', '3', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('60', '3', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('61', '2', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('62', '2', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('63', '2', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('64', '2', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('65', '1', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('66', '1', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('67', '1', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('68', '1', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('69', '4', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('70', '4', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('71', '4', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('72', '4', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('73', '4', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('74', '4', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('75', '4', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('76', '4', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('77', '13', '间卧室', '5', 'D');
INSERT INTO `ms_property` VALUES ('78', '13', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('79', '13', '间卫生间', '5', 'D');
INSERT INTO `ms_property` VALUES ('80', '13', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('81', '13', '间卧室', '5', 'D');
INSERT INTO `ms_property` VALUES ('82', '13', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('83', '13', '间卫生间', '5', 'D');
INSERT INTO `ms_property` VALUES ('84', '13', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('85', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('86', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('87', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('88', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('89', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('90', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('91', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('92', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('93', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('94', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('95', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('96', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('97', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('98', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('99', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('100', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('101', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('102', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('103', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('104', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('105', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('106', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('107', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('108', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('109', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('110', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('111', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('112', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('113', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('114', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('115', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('116', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('117', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('118', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('119', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('120', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('121', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('122', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('123', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('124', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('125', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('126', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('127', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('128', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('129', '15', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('130', '15', '张床', '1', 'E');
INSERT INTO `ms_property` VALUES ('131', '15', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('132', '15', '人可住', '1', 'E');
INSERT INTO `ms_property` VALUES ('133', '16', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('134', '16', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('135', '16', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('136', '16', '人可住', '1', 'D');
INSERT INTO `ms_property` VALUES ('137', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('138', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('139', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('140', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('141', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('142', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('143', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('144', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('145', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('146', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('147', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('148', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('149', '16', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('150', '16', '张床', '1', 'E');
INSERT INTO `ms_property` VALUES ('151', '16', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('152', '16', '人可住', '1', 'E');
INSERT INTO `ms_property` VALUES ('153', '1', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('154', '1', '张床', '2', 'E');
INSERT INTO `ms_property` VALUES ('155', '1', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('156', '1', '人可住', '3', 'E');
INSERT INTO `ms_property` VALUES ('157', '2', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('158', '2', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('159', '2', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('160', '2', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('161', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('162', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('163', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('164', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('165', '9', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('166', '9', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('167', '9', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('168', '9', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('169', '7', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('170', '7', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('171', '7', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('172', '7', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('173', '6', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('174', '6', '张床', '2', 'E');
INSERT INTO `ms_property` VALUES ('175', '6', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('176', '6', '人可住', '3', 'E');
INSERT INTO `ms_property` VALUES ('177', '5', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('178', '5', '张床', '2', 'E');
INSERT INTO `ms_property` VALUES ('179', '5', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('180', '5', '人可住', '3', 'E');
INSERT INTO `ms_property` VALUES ('181', '4', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('182', '4', '张床', '1', 'E');
INSERT INTO `ms_property` VALUES ('183', '4', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('184', '4', '人可住', '2', 'E');
INSERT INTO `ms_property` VALUES ('185', '3', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('186', '3', '张床', '2', 'E');
INSERT INTO `ms_property` VALUES ('187', '3', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('188', '3', '人可住', '2', 'E');
INSERT INTO `ms_property` VALUES ('189', '9', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('190', '9', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('191', '9', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('192', '9', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('193', '9', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('194', '9', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('195', '9', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('196', '9', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('197', '9', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('198', '9', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('199', '9', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('200', '9', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('201', '9', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('202', '9', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('203', '9', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('204', '9', '人可住', '3', 'D');
INSERT INTO `ms_property` VALUES ('205', '9', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('206', '9', '张床', '2', 'E');
INSERT INTO `ms_property` VALUES ('207', '9', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('208', '9', '人可住', '3', 'E');
INSERT INTO `ms_property` VALUES ('209', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('210', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('211', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('212', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('213', '12', '间卧室', '1', 'D');
INSERT INTO `ms_property` VALUES ('214', '12', '张床', '2', 'D');
INSERT INTO `ms_property` VALUES ('215', '12', '间卫生间', '1', 'D');
INSERT INTO `ms_property` VALUES ('216', '12', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('217', '12', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('218', '12', '张床', '2', 'E');
INSERT INTO `ms_property` VALUES ('219', '12', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('220', '12', '人可住', '2', 'E');
INSERT INTO `ms_property` VALUES ('221', '13', '间卧室', '5', 'D');
INSERT INTO `ms_property` VALUES ('222', '13', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('223', '13', '间卫生间', '5', 'D');
INSERT INTO `ms_property` VALUES ('224', '13', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('225', '13', '间卧室', '5', 'D');
INSERT INTO `ms_property` VALUES ('226', '13', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('227', '13', '间卫生间', '5', 'D');
INSERT INTO `ms_property` VALUES ('228', '13', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('229', '13', '间卧室', '5', 'D');
INSERT INTO `ms_property` VALUES ('230', '13', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('231', '13', '间卫生间', '5', 'D');
INSERT INTO `ms_property` VALUES ('232', '13', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('233', '13', '间卧室', '5', 'D');
INSERT INTO `ms_property` VALUES ('234', '13', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('235', '13', '间卫生间', '5', 'D');
INSERT INTO `ms_property` VALUES ('236', '13', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('237', '13', '间卧室', '5', 'D');
INSERT INTO `ms_property` VALUES ('238', '13', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('239', '13', '间卫生间', '5', 'D');
INSERT INTO `ms_property` VALUES ('240', '13', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('241', '13', '间卧室', '5', 'D');
INSERT INTO `ms_property` VALUES ('242', '13', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('243', '13', '间卫生间', '5', 'D');
INSERT INTO `ms_property` VALUES ('244', '13', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('245', '13', '间卧室', '5', 'D');
INSERT INTO `ms_property` VALUES ('246', '13', '张床', '1', 'D');
INSERT INTO `ms_property` VALUES ('247', '13', '间卫生间', '5', 'D');
INSERT INTO `ms_property` VALUES ('248', '13', '人可住', '2', 'D');
INSERT INTO `ms_property` VALUES ('249', '13', '间卧室', '5', 'E');
INSERT INTO `ms_property` VALUES ('250', '13', '张床', '1', 'E');
INSERT INTO `ms_property` VALUES ('251', '13', '间卫生间', '5', 'E');
INSERT INTO `ms_property` VALUES ('252', '13', '人可住', '2', 'E');
INSERT INTO `ms_property` VALUES ('253', '7', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('254', '7', '张床', '1', 'E');
INSERT INTO `ms_property` VALUES ('255', '7', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('256', '7', '人可住', '2', 'E');
INSERT INTO `ms_property` VALUES ('257', '2', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('258', '2', '张床', '2', 'E');
INSERT INTO `ms_property` VALUES ('259', '2', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('260', '2', '人可住', '3', 'E');
INSERT INTO `ms_property` VALUES ('261', '18', '间卧室', '1', 'E');
INSERT INTO `ms_property` VALUES ('262', '18', '张床', '1', 'E');
INSERT INTO `ms_property` VALUES ('263', '18', '间卫生间', '1', 'E');
INSERT INTO `ms_property` VALUES ('264', '18', '人可住', '2', 'E');

-- ----------------------------
-- Table structure for ms_roominfo
-- ----------------------------
DROP TABLE IF EXISTS `ms_roominfo`;
CREATE TABLE `ms_roominfo` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `MID` bigint DEFAULT NULL COMMENT '商家ID',
  `TITLE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房间标题',
  `DES` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房间描述',
  `PRICE` float DEFAULT NULL COMMENT '价格',
  `IS_ACTIVE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'N' COMMENT '是否参加活动     Y：是    N：否',
  `SALES` int DEFAULT '0' COMMENT '销售数量',
  `TYPE` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房间类型    大床房，单人间，双人间，三人间',
  `LIKE_COUNT` int DEFAULT '0' COMMENT '收藏数',
  `COMMENT_COUNT` int DEFAULT '0' COMMENT '评论数',
  `BEGIN_TIME` date DEFAULT NULL COMMENT '开始预订时间',
  `END_TIME` date DEFAULT NULL COMMENT '结束预订时间',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `AUDIT_STATUS` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'WA' COMMENT '审核状态  WA：待审核  SA：审核通过 ',
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'FR' COMMENT '房间状态   D：已下架   FR：空闲中   B：已预订',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_roominfo
-- ----------------------------
INSERT INTO `ms_roominfo` VALUES ('1', '8', '傍山屋', '过雨看松色，随山到水源。溪花与禅意，相对亦忘言。禅意之于民宿，可静可动，让人获得精神惬意和心理满足。', '150', 'Y', '1', '双人间', '20', '2', '2020-05-18', '2020-05-19', '2020-01-13 17:19:16', '2020-05-22 23:14:17', 'SA', 'FR');
INSERT INTO `ms_roominfo` VALUES ('2', '8', '北屋', '偶遇民宿，初见即一见钟情。远远望去，颇有古代山水画的意境。蓝天白云，或日出日落，大自然馈赠的美景犹如一幅挂画。民宿与这里的“风、花、雪、月”完美融合，却又出类拔萃。', '125', 'Y', '0', '单人间', '1', '5', '2020-05-01', '2020-05-02', '2020-01-13 17:19:16', '2020-05-22 23:15:17', 'SA', 'FR');
INSERT INTO `ms_roominfo` VALUES ('3', '8', '东屋', '山水边的民宿有着得天独厚的景色。客人静坐在阳台，看窗外落霞与孤鹜齐飞 ，秋水共长天一色，也是引人入胜。', '112', 'N', '0', '双人间', '2', '2', '2020-04-21', '2020-04-22', '2020-01-13 17:19:16', '2020-05-22 23:14:08', 'SA', 'FR');
INSERT INTO `ms_roominfo` VALUES ('4', '8', '西屋', '怀旧风格的民宿，不仅能让客人体验到像童年时光的逍遥自在，在客人玩累的时候，更像温馨舒适的家。', '96', 'N', '0', '双人间', '0', '0', '2020-03-23', '2020-03-24', '2020-01-13 17:19:16', '2020-05-22 23:14:03', 'SA', 'FR');
INSERT INTO `ms_roominfo` VALUES ('5', '8', '南屋', '民宿不是旅馆不是饭店，但是比旅馆更温馨，比饭店更回味。没有奢华的设施，却有别样风景和当地特色，让你发觉诗意生活正如此。', '150', 'N', '0', '双人间', '0', '0', null, null, '2020-01-13 17:19:16', '2020-05-22 23:13:55', 'SA', 'FR');
INSERT INTO `ms_roominfo` VALUES ('6', '8', '东北屋', '从幽溪深涧的陶冶中得到超悟，从摇曳的野花静静观照中，自在恬然的心境与清幽静谧的物象交融为一。愿所有人都能获得乘兴而来，兴尽而返的惬意自得的感受。', '159', 'N', '0', '双人间', '5', '0', null, null, '2020-01-13 17:19:16', '2020-05-22 23:13:49', 'SA', 'FR');
INSERT INTO `ms_roominfo` VALUES ('7', '8', '堡垒屋', '一角斜阳照来，感受一种隐约的侘寂美，连空间都被赋予了一种宁静、质朴的气质。每个房间，展现出的新禅意之美，给住客带来独一无二的私密体验；', '119', 'Y', '0', '单人间', '1', '4', '2020-03-27', '2020-03-28', '2020-01-13 17:19:16', '2020-05-22 23:15:10', 'SA', 'FR');
INSERT INTO `ms_roominfo` VALUES ('8', '8', '西北屋', '非常舒适非常舒适非常舒适非常舒适非常舒适非常舒适非常舒适非常舒适非常舒适非常舒适非常舒适非常舒适非常舒适非常舒适', '118', 'N', '0', '大床房', '8', '0', '2020-03-18', '2020-03-19', '2020-01-13 17:19:16', '2020-05-22 23:13:16', 'SA', 'FR');
INSERT INTO `ms_roominfo` VALUES ('9', '8', '东南屋', '禅意花器、茶壶，餐厅陈列了许多禅意的枯山水元素，窗外望去，也是一番和谐的朴素景象，置身其中，仿佛参与了一幅动态画作，实在是一种享受。', '150', 'Y', '0', '双人间', '3', '0', '2020-02-28', '2020-02-29', '2020-01-13 17:19:16', '2020-05-22 23:11:53', 'SA', 'FR');
INSERT INTO `ms_roominfo` VALUES ('12', '8', '安定坊', '新式建筑与老房子的巧妙结合，宛如天成，精美别致。从紧张忙碌的工作、生活中抽离出来，千里迢迢来民宿遇见有趣的人，一起发呆、冥想、思考人生，宁静、自然，享受当下。', '123', 'Y', '0', '双人间', '0', '1', '2020-02-14', '2020-02-15', '2020-02-13 16:11:33', '2020-05-22 23:08:44', 'SA', 'FR');
INSERT INTO `ms_roominfo` VALUES ('13', '8', '东街房', '很好的房子很舒适', '112', 'Y', '1', '双人间', '1', '1', '2020-05-22', '2020-05-23', '2020-02-27 17:46:29', '2020-05-22 23:14:54', 'SA', 'FR');

-- ----------------------------
-- Table structure for ms_user
-- ----------------------------
DROP TABLE IF EXISTS `ms_user`;
CREATE TABLE `ms_user` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `OPEN_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户OPEN_ID',
  `UNAME` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名称',
  `UPHONE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户号码',
  `AVATAR_URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'E' COMMENT '状态    E：生效中    D：已失效',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of ms_user
-- ----------------------------
INSERT INTO `ms_user` VALUES ('1', '51668402', '测试人员', '13599996688', null, '2020-01-10 09:35:12', '2020-01-13 17:12:40', 'E');
INSERT INTO `ms_user` VALUES ('6', 'oyKmX5FJURuSFYR96-2ausZkkguE', 'Cbuc丶', null, 'https://wx.qlogo.cn/mmopen/vi_32/TH9iaYOowdTZkxG7ravWwykNZ8u7aCkhHX2exSQyPJnrtLMnNBXITcc87OCFWcCMgOTtPfcvQ9u0IOpxibJddtVQ/132', '2020-02-27 11:21:02', '2020-03-27 19:50:25', 'E');
INSERT INTO `ms_user` VALUES ('9', 'oP5_F5NlFMC7mILx9RpJYIokCt1k', 'Cbuc丶', null, 'https://wx.qlogo.cn/mmopen/vi_32/L5HibOulTWpciaR4C72jke4sOpYtPicno1NgaSsic9O1ym2SeBVyUJJaYHYez9tX87lTxniaC5h4oUqKovhb30Sxasg/132', '2020-03-11 21:19:03', '2020-03-27 19:43:22', 'E');
