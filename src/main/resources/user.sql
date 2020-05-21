/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 21/05/2020 15:22:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `gender` int(11) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zsss', 20, 2, 'zs@qq.com');
INSERT INTO `user` VALUES (2, 'lisi', 23, 1, 'lisi@qq.com');
INSERT INTO `user` VALUES (3, 'haha', 22, 2, 'haha@qq.com');
INSERT INTO `user` VALUES (4, 'asd', 12, 2, 'asd@qq.com');
INSERT INTO `user` VALUES (26, 'adsf', 12, 2, '211@qq.com');
INSERT INTO `user` VALUES (27, 'asdsf', 32, 1, '1231@qq.com');
INSERT INTO `user` VALUES (28, 'asdfaf', 12, 2, '12123@qq.com');
INSERT INTO `user` VALUES (34, '12e431', 41, 1, 'gwew@qq.com');
INSERT INTO `user` VALUES (35, 'wefe', 123, 2, 'as@qq.com');
INSERT INTO `user` VALUES (36, 'cfwec', 32, 1, 'fqfc@qq.com');
INSERT INTO `user` VALUES (37, 'vwd', 23, 1, 'cqwqw@qq.com');
INSERT INTO `user` VALUES (40, '呵呵', 23, 1, 'hehe@qq.com');
INSERT INTO `user` VALUES (41, '呵呵', 23, 1, 'hehe@qq.com');
INSERT INTO `user` VALUES (42, '呵呵', 23, 1, 'hehe@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
