/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50742 (5.7.42)
 Source Host           : localhost:3306
 Source Schema         : ycep

 Target Server Type    : MySQL
 Target Server Version : 50742 (5.7.42)
 File Encoding         : 65001

 Date: 01/08/2023 09:22:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_local
-- ----------------------------
DROP TABLE IF EXISTS `auth_local`;
CREATE TABLE `auth_local` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `account` (`account`),
  CONSTRAINT `auth_local_ibfk_1` FOREIGN KEY (`account`) REFERENCES `user` (`account`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_local
-- ----------------------------
BEGIN;
INSERT INTO `auth_local` (`id`, `account`, `username`, `password`) VALUES (1, 1, 'root', '$argon2id$v=19$m=4096,t=3,p=1$d5FLws7CQLfST/5CzkqS9w$bSrNnvhRyB5lT6/tSgVOiJ7uVSRQR1a5xd4qZbrLQEk');
INSERT INTO `auth_local` (`id`, `account`, `username`, `password`) VALUES (2, 2, 'abc', '$argon2id$v=19$m=4096,t=3,p=1$gm87yiZJC94Sc7oeVI4YDQ$nDDky3perv9hmq4V13rPJCfgIQ5kNw1esmzPzvzVzkE');
INSERT INTO `auth_local` (`id`, `account`, `username`, `password`) VALUES (3, 3, 'pr', '$argon2id$v=19$m=4096,t=3,p=1$NXLw2uEZLSifb8N+BouhCw$5rmu0gS7KL4UCLP5MybHTY+7os2jaHd0iu7AUvbekwI');
COMMIT;

-- ----------------------------
-- Table structure for auth_oauth
-- ----------------------------
DROP TABLE IF EXISTS `auth_oauth`;
CREATE TABLE `auth_oauth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` int(11) NOT NULL,
  `oauth_name` varchar(255) NOT NULL,
  `oauth_id` varchar(255) NOT NULL,
  `oauth_access_token` varchar(255) NOT NULL,
  `oauth_expires` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account` (`account`),
  CONSTRAINT `auth_oauth_ibfk_1` FOREIGN KEY (`account`) REFERENCES `user` (`account`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_oauth
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`role_id`, `role_name`) VALUES (0, 'admin');
INSERT INTO `role` (`role_id`, `role_name`) VALUES (1, 'user');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `account` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account`) USING BTREE,
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`account`, `role_id`, `phone`, `email`, `sex`, `school`, `description`) VALUES (1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`account`, `role_id`, `phone`, `email`, `sex`, `school`, `description`) VALUES (2, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`account`, `role_id`, `phone`, `email`, `sex`, `school`, `description`) VALUES (3, 1, NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
