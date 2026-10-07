/*
 Navicat Premium Dump SQL

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 80019 (8.0.19)
 Source Host           : localhost:3306
 Source Schema         : weixin

 Target Server Type    : MySQL
 Target Server Version : 80019 (8.0.19)
 File Encoding         : 65001

 Date: 15/06/2026 20:12:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `sort` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '热销推荐', 1);
INSERT INTO `category` VALUES (2, '特色小吃', 2);
INSERT INTO `category` VALUES (3, '甜粥', 3);
INSERT INTO `category` VALUES (4, '凉菜', 4);
INSERT INTO `category` VALUES (5, '醇香奶茶', 5);
INSERT INTO `category` VALUES (6, '主食', 6);
INSERT INTO `category` VALUES (7, '小炒菜', 7);
INSERT INTO `category` VALUES (8, '现磨咖啡', 8);
INSERT INTO `category` VALUES (9, '鲜果奶茶', 9);

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `category_id` int UNSIGNED NOT NULL DEFAULT 0,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `status` tinyint UNSIGNED NOT NULL DEFAULT 0,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT NULL,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES (1, 1, '奶香糯玉米松饼', 14.00, 'images/1.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (2, 1, '鲜枣馍', 13.00, 'images/2.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (3, 1, '华夫饼', 12.00, 'images/3.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (4, 1, '琥珀珍珠奶茶（中杯）', 10.00, 'images/4.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (5, 1, '香柠咖啡（大杯）', 14.00, 'images/5.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (6, 1, '柠檬椰果养乐多/大杯', 17.00, 'images/6.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (7, 1, '芒果养乐多/大杯', 16.00, 'images/7.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (8, 1, '葡萄柚养乐多/大杯', 18.00, 'images/8.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (9, 1, '绿茶养乐多/大杯', 14.00, 'images/9.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (10, 1, '加10元得冷饮杯', 10.00, 'images/10.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (11, 1, '红茶拿铁/大杯', 14.00, 'images/11.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (12, 1, '珍珠红茶拿铁/大杯', 14.00, 'images/12.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (13, 1, '鲜醇牛奶三兄弟/中杯', 14.00, 'images/13.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (14, 1, '鲜醇草莓欧蕾/中杯', 14.00, 'images/14.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (15, 1, '鲜醇芒果欧蕾/中杯', 12.00, 'images/15.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (16, 1, '铁观音茶拿铁珍珠/大杯', 15.00, 'images/16.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (17, 2, '南瓜荷叶饼', 13.00, 'images/17.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (18, 2, '鲜枣馍', 12.00, 'images/18.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (19, 2, '芝士火腿包', 10.00, 'images/19.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (20, 2, '华夫饼', 10.00, 'images/20.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (21, 3, '红豆薏米紫米粥', 15.00, 'images/21.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (22, 3, '枸杞酒酿玉米羹', 14.00, 'images/22.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (23, 3, '红豆桂圆银耳羹/大份', 14.00, 'images/23.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (24, 3, '红豆桂圆银耳羹/小份', 11.00, 'images/24.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (25, 3, '冬瓜酥肉汤/大份', 14.00, 'images/25.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (26, 3, '冬瓜酥肉汤/小份', 11.00, 'images/26.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (27, 3, '土豆炖豆腐/大份', 14.00, 'images/27.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (28, 3, '土豆炖豆腐/小份', 11.00, 'images/28.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (29, 4, '口水鸡', 12.00, 'images/29.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (30, 5, '鲜芋奶茶/中杯', 11.00, 'images/30.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (31, 5, '珍珠奶茶/大杯', 11.00, 'images/31.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (32, 5, '珍珠奶茶/中杯', 9.00, 'images/32.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (33, 5, '布丁奶茶/大杯', 11.00, 'images/33.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (34, 5, '布丁奶茶/中杯', 9.00, 'images/34.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (35, 5, '奶茶三兄弟/大杯', 13.00, 'images/35.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (36, 5, '奶茶三兄弟/中杯', 11.00, 'images/36.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (37, 5, '双拼奶茶/大杯', 12.00, 'images/37.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (38, 5, '双拼奶茶/中杯', 10.00, 'images/38.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (39, 5, '红豆奶茶/大杯', 12.00, 'images/39.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (40, 5, '红豆奶茶/中杯', 10.00, 'images/40.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (41, 5, 'QQ奶茶/大杯', 12.00, 'images/41.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (42, 5, 'QQ奶茶/中杯', 10.00, 'images/42.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (43, 5, '椰果奶茶/大杯', 11.00, 'images/43.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (44, 5, '椰果奶茶/中杯', 9.00, 'images/44.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (45, 5, '仙草冻奶茶/大杯', 11.00, 'images/45.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (46, 5, '仙草冻奶茶/中杯', 9.00, 'images/46.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (47, 5, '铁观音珍珠奶茶/大杯', 12.00, 'images/47.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (48, 5, '琥珀珍珠奶茶大杯 ', 12.00, 'images/48.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (49, 6, '奶香糯玉米松饼', 10.00, 'images/49.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (50, 6, '红糖酥饼', 10.00, 'images/50.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (51, 7, '耗油青菜小炒/大份', 12.00, 'images/51.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (52, 7, '耗油青菜小炒/小份', 10.00, 'images/52.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (53, 7, '鸡蛋木耳小炒/大份', 12.00, 'images/53.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (54, 7, '鸡蛋木耳小炒/小份', 10.00, 'images/54.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (55, 7, '可乐鸡翅', 11.00, 'images/55.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (56, 7, '油菜沙拉/大份', 11.00, 'images/56.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (57, 7, '油菜沙拉/小份', 9.00, 'images/57.jpg', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (58, 8, '美式咖啡/中杯', 8.00, 'images/58.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (59, 8, '香柠咖啡/大杯', 14.00, 'images/59.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (60, 8, '拿铁咖啡/大杯', 16.00, 'images/60.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (61, 8, '拿铁咖啡/中杯', 13.00, 'images/61.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (62, 8, '卡布奇诺/大杯', 16.00, 'images/62.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (63, 8, '卡布奇诺/中杯', 13.00, 'images/63.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (64, 8, '摩卡咖啡/大杯', 19.00, 'images/64.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (65, 8, '摩卡咖啡/中杯', 16.00, 'images/65.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (66, 8, '珍珠拿铁/大杯', 16.00, 'images/66.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (67, 8, '香草拿铁/大杯', 19.00, 'images/67.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (68, 8, '香草拿铁/中杯', 16.00, 'images/68.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (69, 8, '法式奶霜咖啡/大杯', 15.00, 'images/69.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (70, 8, '海盐焦糖拿铁/大杯', 18.00, 'images/70.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (71, 8, '海盐焦糖拿铁/中杯', 15.00, 'images/71.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (72, 8, '香柠咖啡', 14.00, 'images/72.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (73, 9, '法式奶霜草莓果茶（大杯）', 15.00, 'images/73.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (74, 9, '鲜百香双响炮/大杯', 13.00, 'images/74.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (75, 9, '柠檬霸/大杯', 13.00, 'images/75.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (76, 9, '金桔柠檬汁/中杯', 11.00, 'images/76.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (77, 9, '葡萄柚绿茶/中杯', 11.00, 'images/77.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (78, 9, '草莓果茶/中杯', 10.00, 'images/78.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (79, 9, '鲜柠檬红茶/中杯', 10.00, 'images/79.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (80, 9, '鲜柠檬绿茶/中杯', 10.00, 'images/80.webp', 1, '2024-04-16 09:22:15', NULL, NULL);
INSERT INTO `food` VALUES (81, 9, '鲜百香绿茶/中杯', 10.00, 'images/81.webp', 1, '2024-04-16 09:22:15', NULL, NULL);

-- ----------------------------
-- Table structure for order1
-- ----------------------------
DROP TABLE IF EXISTS `order1`;
CREATE TABLE `order1`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int UNSIGNED NOT NULL DEFAULT 0,
  `price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00,
  `promotion` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00,
  `number` int UNSIGNED NOT NULL DEFAULT 0,
  `is_pay` tinyint UNSIGNED NOT NULL DEFAULT 0,
  `is_taken` tinyint UNSIGNED NOT NULL DEFAULT 0,
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `pay_time` datetime NULL DEFAULT NULL,
  `taken_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order1
-- ----------------------------
INSERT INTO `order1` VALUES (29, 1, 41.00, 0.00, 3, 1, 0, 'fdsfsddd', '2024-04-18 20:43:07', '2024-04-18 20:43:13', NULL);
INSERT INTO `order1` VALUES (30, 1, 39.00, 0.00, 3, 1, 0, 'sdad', '2024-04-18 23:13:02', '2024-04-18 23:13:06', NULL);
INSERT INTO `order1` VALUES (31, 1, 48.00, 0.00, 4, 1, 0, 'dfdsfads', '2024-04-18 23:13:54', '2024-04-18 23:14:01', NULL);
INSERT INTO `order1` VALUES (32, 1, 42.00, 0.00, 4, 1, 0, 'dsfdsf', '2024-04-18 23:14:52', '2024-04-18 23:14:56', NULL);
INSERT INTO `order1` VALUES (33, 1, 24.00, 0.00, 2, 1, 0, 'defwed', '2024-04-18 23:15:15', '2024-04-18 23:15:19', NULL);
INSERT INTO `order1` VALUES (34, 1, 29.00, 0.00, 3, 1, 0, '', '2024-04-18 23:15:31', '2024-04-18 23:15:34', NULL);
INSERT INTO `order1` VALUES (35, 1, 36.00, 0.00, 3, 1, 0, '', '2024-04-18 23:15:47', '2024-04-18 23:15:50', NULL);
INSERT INTO `order1` VALUES (36, 1, 33.00, 0.00, 3, 1, 0, '', '2024-04-18 23:15:56', '2024-04-18 23:15:59', NULL);
INSERT INTO `order1` VALUES (37, 1, 32.00, 0.00, 2, 1, 0, '', '2024-04-18 23:16:10', '2024-04-18 23:16:12', NULL);
INSERT INTO `order1` VALUES (38, 1, 28.00, 0.00, 2, 1, 0, '', '2024-04-18 23:16:19', '2024-04-18 23:16:21', NULL);
INSERT INTO `order1` VALUES (39, 1, 26.00, 0.00, 2, 1, 0, '', '2024-04-18 23:16:52', '2024-04-18 23:16:54', NULL);
INSERT INTO `order1` VALUES (40, 1, 26.00, 0.00, 2, 0, 0, '', '2024-04-18 23:16:52', NULL, NULL);
INSERT INTO `order1` VALUES (41, 1, 40.00, 0.00, 3, 0, 0, '', '2024-04-19 10:10:43', NULL, NULL);
INSERT INTO `order1` VALUES (42, 1, 124.00, 0.00, 10, 1, 0, '', '2024-04-19 10:35:00', '2024-04-19 10:35:40', NULL);
INSERT INTO `order1` VALUES (43, 1, 102.00, 0.00, 9, 0, 0, '', '2024-04-19 10:36:32', NULL, NULL);
INSERT INTO `order1` VALUES (44, 1, 112.00, 0.00, 9, 0, 0, '', '2024-04-19 13:15:37', NULL, NULL);
INSERT INTO `order1` VALUES (45, 1, 69.00, 0.00, 7, 0, 0, '', '2024-04-19 13:18:05', NULL, NULL);
INSERT INTO `order1` VALUES (46, 1, 86.00, 0.00, 7, 0, 0, '', '2024-04-19 13:31:45', NULL, NULL);
INSERT INTO `order1` VALUES (47, 1, 128.00, 0.00, 12, 1, 0, 'qqq', '2024-04-19 13:45:21', '2024-04-19 13:45:33', NULL);
INSERT INTO `order1` VALUES (48, 1, 154.00, 0.00, 14, 0, 0, '', '2024-04-19 14:56:15', NULL, NULL);
INSERT INTO `order1` VALUES (49, 1, 45.00, 0.00, 5, 1, 0, 'gdgf', '2024-04-19 15:06:51', '2024-04-19 15:06:56', NULL);
INSERT INTO `order1` VALUES (50, 2, 28.00, 0.00, 2, 1, 0, '111', '2026-05-25 11:17:57', '2026-05-25 11:18:17', NULL);
INSERT INTO `order1` VALUES (51, 2, 55.00, 0.00, 4, 1, 0, '', '2026-05-25 11:25:15', '2026-05-25 11:26:28', NULL);
INSERT INTO `order1` VALUES (52, 2, 46.00, 0.00, 4, 1, 0, '', '2026-05-25 11:28:21', '2026-05-25 11:28:24', NULL);
INSERT INTO `order1` VALUES (53, 2, 43.00, 0.00, 4, 1, 0, '', '2026-05-25 11:34:53', '2026-05-25 11:34:56', NULL);
INSERT INTO `order1` VALUES (54, 2, 24.00, 0.00, 2, 1, 0, '', '2026-05-25 11:36:42', '2026-05-25 11:50:27', NULL);
INSERT INTO `order1` VALUES (55, 2, 10.00, 0.00, 1, 1, 0, '少冰', '2026-05-25 11:50:37', '2026-05-25 11:50:49', NULL);
INSERT INTO `order1` VALUES (56, 2, 25.00, 0.00, 2, 1, 0, '加肉！！！\n', '2026-05-25 11:51:26', '2026-05-25 11:51:36', NULL);
INSERT INTO `order1` VALUES (57, 2, 28.00, 0.00, 2, 1, 0, '', '2026-05-25 22:31:33', '2026-05-25 22:31:35', NULL);
INSERT INTO `order1` VALUES (58, 2, 18.00, 0.00, 1, 1, 0, '', '2026-05-27 10:52:05', '2026-05-27 10:58:13', NULL);
INSERT INTO `order1` VALUES (59, 2, 50.00, 0.00, 4, 1, 0, '', '2026-05-27 11:19:40', '2026-05-27 11:19:45', NULL);
INSERT INTO `order1` VALUES (60, 2, 28.00, 0.00, 2, 1, 0, '', '2026-05-27 12:31:39', '2026-05-27 12:31:41', NULL);
INSERT INTO `order1` VALUES (61, 2, 34.00, 0.00, 2, 1, 0, '', '2026-06-01 20:49:59', '2026-06-01 20:50:05', NULL);
INSERT INTO `order1` VALUES (62, 2, 14.00, 0.00, 1, 1, 0, '', '2026-06-01 22:34:19', '2026-06-01 22:34:21', NULL);
INSERT INTO `order1` VALUES (63, 2, 42.00, 0.00, 3, 1, 0, '', '2026-06-01 22:39:15', '2026-06-01 22:39:17', NULL);
INSERT INTO `order1` VALUES (64, 2, 14.00, 0.00, 1, 1, 0, '', '2026-06-01 22:51:05', '2026-06-01 22:51:07', NULL);
INSERT INTO `order1` VALUES (65, 2, 50.00, 0.00, 5, 1, 0, '加辣！！！', '2026-06-07 15:24:09', '2026-06-07 15:24:27', NULL);
INSERT INTO `order1` VALUES (66, 2, 14.00, 0.00, 1, 1, 0, '', '2026-06-15 20:01:10', '2026-06-15 20:01:11', NULL);

-- ----------------------------
-- Table structure for order_food
-- ----------------------------
DROP TABLE IF EXISTS `order_food`;
CREATE TABLE `order_food`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` int UNSIGNED NOT NULL DEFAULT 0,
  `food_id` int UNSIGNED NOT NULL DEFAULT 0,
  `number` int UNSIGNED NOT NULL DEFAULT 0,
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 134 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_food
-- ----------------------------
INSERT INTO `order_food` VALUES (1, 6, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (2, 6, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (3, 7, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (4, 7, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (5, 8, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (6, 8, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (7, 9, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (8, 9, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (9, 10, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (10, 10, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (11, 11, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (12, 11, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (13, 12, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (14, 12, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (15, 13, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (16, 13, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (17, 14, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (18, 14, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (19, 15, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (20, 15, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (21, 16, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (22, 16, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (23, 17, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (24, 17, 3, 2, 12.00);
INSERT INTO `order_food` VALUES (25, 18, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (26, 18, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (27, 18, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (28, 19, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (29, 19, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (30, 20, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (31, 20, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (32, 21, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (33, 21, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (34, 22, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (35, 22, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (36, 23, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (37, 23, 2, 2, 13.00);
INSERT INTO `order_food` VALUES (38, 24, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (39, 24, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (40, 25, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (41, 25, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (42, 26, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (43, 26, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (44, 27, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (45, 27, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (46, 28, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (47, 28, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (48, 29, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (49, 29, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (50, 30, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (51, 30, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (52, 30, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (53, 31, 2, 2, 13.00);
INSERT INTO `order_food` VALUES (54, 31, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (55, 31, 4, 1, 10.00);
INSERT INTO `order_food` VALUES (56, 32, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (57, 32, 3, 2, 12.00);
INSERT INTO `order_food` VALUES (58, 33, 3, 2, 12.00);
INSERT INTO `order_food` VALUES (59, 34, 32, 2, 9.00);
INSERT INTO `order_food` VALUES (60, 34, 33, 1, 11.00);
INSERT INTO `order_food` VALUES (61, 35, 53, 3, 12.00);
INSERT INTO `order_food` VALUES (62, 36, 77, 3, 11.00);
INSERT INTO `order_food` VALUES (63, 37, 65, 2, 16.00);
INSERT INTO `order_food` VALUES (64, 38, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (65, 39, 2, 2, 13.00);
INSERT INTO `order_food` VALUES (66, 40, 2, 2, 13.00);
INSERT INTO `order_food` VALUES (67, 41, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (68, 41, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (69, 42, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (70, 42, 2, 2, 13.00);
INSERT INTO `order_food` VALUES (71, 42, 3, 2, 12.00);
INSERT INTO `order_food` VALUES (72, 42, 4, 2, 10.00);
INSERT INTO `order_food` VALUES (73, 42, 8, 2, 18.00);
INSERT INTO `order_food` VALUES (74, 43, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (75, 43, 2, 2, 13.00);
INSERT INTO `order_food` VALUES (76, 43, 4, 2, 10.00);
INSERT INTO `order_food` VALUES (77, 43, 10, 1, 10.00);
INSERT INTO `order_food` VALUES (78, 43, 11, 2, 14.00);
INSERT INTO `order_food` VALUES (79, 44, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (80, 44, 2, 3, 13.00);
INSERT INTO `order_food` VALUES (81, 44, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (82, 44, 80, 1, 10.00);
INSERT INTO `order_food` VALUES (83, 44, 67, 1, 19.00);
INSERT INTO `order_food` VALUES (84, 44, 12, 1, 14.00);
INSERT INTO `order_food` VALUES (85, 45, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (86, 45, 4, 3, 10.00);
INSERT INTO `order_food` VALUES (87, 45, 34, 1, 9.00);
INSERT INTO `order_food` VALUES (88, 45, 15, 1, 12.00);
INSERT INTO `order_food` VALUES (89, 46, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (90, 46, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (91, 46, 6, 1, 17.00);
INSERT INTO `order_food` VALUES (92, 46, 36, 1, 11.00);
INSERT INTO `order_food` VALUES (93, 46, 14, 2, 14.00);
INSERT INTO `order_food` VALUES (94, 47, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (95, 47, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (96, 47, 75, 2, 13.00);
INSERT INTO `order_food` VALUES (97, 47, 20, 2, 10.00);
INSERT INTO `order_food` VALUES (98, 47, 56, 1, 11.00);
INSERT INTO `order_food` VALUES (99, 47, 80, 2, 10.00);
INSERT INTO `order_food` VALUES (100, 47, 38, 2, 10.00);
INSERT INTO `order_food` VALUES (101, 48, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (102, 48, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (103, 48, 17, 1, 13.00);
INSERT INTO `order_food` VALUES (104, 48, 29, 2, 12.00);
INSERT INTO `order_food` VALUES (105, 48, 37, 2, 12.00);
INSERT INTO `order_food` VALUES (106, 48, 58, 2, 8.00);
INSERT INTO `order_food` VALUES (107, 48, 63, 2, 13.00);
INSERT INTO `order_food` VALUES (108, 48, 80, 1, 10.00);
INSERT INTO `order_food` VALUES (109, 48, 81, 1, 10.00);
INSERT INTO `order_food` VALUES (110, 49, 28, 3, 11.00);
INSERT INTO `order_food` VALUES (111, 49, 31, 2, 11.00);
INSERT INTO `order_food` VALUES (112, 50, 5, 2, 14.00);
INSERT INTO `order_food` VALUES (113, 51, 5, 1, 14.00);
INSERT INTO `order_food` VALUES (114, 51, 6, 3, 17.00);
INSERT INTO `order_food` VALUES (115, 52, 1, 4, 14.00);
INSERT INTO `order_food` VALUES (116, 53, 1, 2, 14.00);
INSERT INTO `order_food` VALUES (117, 53, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (118, 53, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (119, 54, 3, 2, 12.00);
INSERT INTO `order_food` VALUES (120, 55, 4, 1, 10.00);
INSERT INTO `order_food` VALUES (121, 56, 2, 1, 13.00);
INSERT INTO `order_food` VALUES (122, 56, 3, 1, 12.00);
INSERT INTO `order_food` VALUES (123, 57, 5, 2, 14.00);
INSERT INTO `order_food` VALUES (124, 58, 8, 1, 18.00);
INSERT INTO `order_food` VALUES (125, 59, 5, 2, 14.00);
INSERT INTO `order_food` VALUES (126, 59, 7, 2, 16.00);
INSERT INTO `order_food` VALUES (127, 60, 13, 2, 14.00);
INSERT INTO `order_food` VALUES (128, 61, 6, 2, 17.00);
INSERT INTO `order_food` VALUES (129, 62, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (130, 63, 1, 3, 14.00);
INSERT INTO `order_food` VALUES (131, 64, 1, 1, 14.00);
INSERT INTO `order_food` VALUES (132, 65, 29, 5, 12.00);
INSERT INTO `order_food` VALUES (133, 66, 1, 1, 14.00);

-- ----------------------------
-- Table structure for ordercomment
-- ----------------------------
DROP TABLE IF EXISTS `ordercomment`;
CREATE TABLE `ordercomment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_id` int NOT NULL COMMENT '订单ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `star` int NOT NULL COMMENT '星级评价（1-5）',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '评价内容',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_id`(`order_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单评价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ordercomment
-- ----------------------------
INSERT INTO `ordercomment` VALUES (1, 61, 2, 5, '1', '2026-06-01 21:57:19', '2026-06-01 21:57:19');
INSERT INTO `ordercomment` VALUES (2, 63, 2, 5, '123', '2026-06-01 22:39:23', '2026-06-01 22:39:23');
INSERT INTO `ordercomment` VALUES (3, 64, 2, 5, '23232', '2026-06-07 00:43:46', '2026-06-15 17:33:32');
INSERT INTO `ordercomment` VALUES (4, 65, 2, 4, '不够辣', '2026-06-07 15:24:36', '2026-06-07 15:24:36');

-- ----------------------------
-- Table structure for points
-- ----------------------------
DROP TABLE IF EXISTS `points`;
CREATE TABLE `points`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `total_points` int NULL DEFAULT 0,
  `available_points` int NULL DEFAULT 0,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of points
-- ----------------------------
INSERT INTO `points` VALUES (1, 2, 940, 898, '2026-06-01 22:33:07', '2026-06-15 20:01:11');
INSERT INTO `points` VALUES (2, 1, 1350, 1350, '2026-06-01 22:36:04', '2026-06-01 22:38:50');

-- ----------------------------
-- Table structure for points_exchange
-- ----------------------------
DROP TABLE IF EXISTS `points_exchange`;
CREATE TABLE `points_exchange`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `food_id` int NOT NULL,
  `points` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_points_exchange_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of points_exchange
-- ----------------------------
INSERT INTO `points_exchange` VALUES (1, 2, 1, 14, '2026-06-01 22:46:46');
INSERT INTO `points_exchange` VALUES (2, 2, 1, 14, '2026-06-07 00:45:49');
INSERT INTO `points_exchange` VALUES (3, 2, 1, 14, '2026-06-15 12:11:49');

-- ----------------------------
-- Table structure for points_record
-- ----------------------------
DROP TABLE IF EXISTS `points_record`;
CREATE TABLE `points_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `change_type` int NOT NULL,
  `points` int NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_points_record_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_points_record_created_at`(`created_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of points_record
-- ----------------------------
INSERT INTO `points_record` VALUES (1, 2, 1, 14, '消费获得积分', '2026-06-01 22:34:21');
INSERT INTO `points_record` VALUES (2, 1, 1, 41, '历史消费补录积分', '2024-04-18 20:43:13');
INSERT INTO `points_record` VALUES (3, 1, 1, 39, '历史消费补录积分', '2024-04-18 23:13:06');
INSERT INTO `points_record` VALUES (4, 1, 1, 48, '历史消费补录积分', '2024-04-18 23:14:01');
INSERT INTO `points_record` VALUES (5, 1, 1, 42, '历史消费补录积分', '2024-04-18 23:14:56');
INSERT INTO `points_record` VALUES (6, 1, 1, 24, '历史消费补录积分', '2024-04-18 23:15:19');
INSERT INTO `points_record` VALUES (7, 1, 1, 29, '历史消费补录积分', '2024-04-18 23:15:34');
INSERT INTO `points_record` VALUES (8, 1, 1, 36, '历史消费补录积分', '2024-04-18 23:15:50');
INSERT INTO `points_record` VALUES (9, 1, 1, 33, '历史消费补录积分', '2024-04-18 23:15:59');
INSERT INTO `points_record` VALUES (10, 1, 1, 32, '历史消费补录积分', '2024-04-18 23:16:12');
INSERT INTO `points_record` VALUES (11, 1, 1, 28, '历史消费补录积分', '2024-04-18 23:16:21');
INSERT INTO `points_record` VALUES (12, 1, 1, 26, '历史消费补录积分', '2024-04-18 23:16:54');
INSERT INTO `points_record` VALUES (13, 1, 1, 124, '历史消费补录积分', '2024-04-19 10:35:40');
INSERT INTO `points_record` VALUES (14, 1, 1, 128, '历史消费补录积分', '2024-04-19 13:45:33');
INSERT INTO `points_record` VALUES (15, 1, 1, 45, '历史消费补录积分', '2024-04-19 15:06:56');
INSERT INTO `points_record` VALUES (16, 2, 1, 28, '历史消费补录积分', '2026-05-25 11:18:17');
INSERT INTO `points_record` VALUES (17, 2, 1, 55, '历史消费补录积分', '2026-05-25 11:26:28');
INSERT INTO `points_record` VALUES (18, 2, 1, 46, '历史消费补录积分', '2026-05-25 11:28:24');
INSERT INTO `points_record` VALUES (19, 2, 1, 43, '历史消费补录积分', '2026-05-25 11:34:56');
INSERT INTO `points_record` VALUES (20, 2, 1, 24, '历史消费补录积分', '2026-05-25 11:50:27');
INSERT INTO `points_record` VALUES (21, 2, 1, 10, '历史消费补录积分', '2026-05-25 11:50:49');
INSERT INTO `points_record` VALUES (22, 2, 1, 25, '历史消费补录积分', '2026-05-25 11:51:36');
INSERT INTO `points_record` VALUES (23, 2, 1, 28, '历史消费补录积分', '2026-05-25 22:31:35');
INSERT INTO `points_record` VALUES (24, 2, 1, 18, '历史消费补录积分', '2026-05-27 10:58:13');
INSERT INTO `points_record` VALUES (25, 2, 1, 50, '历史消费补录积分', '2026-05-27 11:19:45');
INSERT INTO `points_record` VALUES (26, 2, 1, 28, '历史消费补录积分', '2026-05-27 12:31:41');
INSERT INTO `points_record` VALUES (27, 2, 1, 34, '历史消费补录积分', '2026-06-01 20:50:05');
INSERT INTO `points_record` VALUES (28, 2, 1, 14, '历史消费补录积分', '2026-06-01 22:34:21');
INSERT INTO `points_record` VALUES (29, 1, 1, 41, '历史消费补录积分', '2024-04-18 20:43:13');
INSERT INTO `points_record` VALUES (30, 1, 1, 39, '历史消费补录积分', '2024-04-18 23:13:06');
INSERT INTO `points_record` VALUES (31, 1, 1, 48, '历史消费补录积分', '2024-04-18 23:14:01');
INSERT INTO `points_record` VALUES (32, 1, 1, 42, '历史消费补录积分', '2024-04-18 23:14:56');
INSERT INTO `points_record` VALUES (33, 1, 1, 24, '历史消费补录积分', '2024-04-18 23:15:19');
INSERT INTO `points_record` VALUES (34, 1, 1, 29, '历史消费补录积分', '2024-04-18 23:15:34');
INSERT INTO `points_record` VALUES (35, 1, 1, 36, '历史消费补录积分', '2024-04-18 23:15:50');
INSERT INTO `points_record` VALUES (36, 1, 1, 33, '历史消费补录积分', '2024-04-18 23:15:59');
INSERT INTO `points_record` VALUES (37, 1, 1, 32, '历史消费补录积分', '2024-04-18 23:16:12');
INSERT INTO `points_record` VALUES (38, 1, 1, 28, '历史消费补录积分', '2024-04-18 23:16:21');
INSERT INTO `points_record` VALUES (39, 1, 1, 26, '历史消费补录积分', '2024-04-18 23:16:54');
INSERT INTO `points_record` VALUES (40, 1, 1, 124, '历史消费补录积分', '2024-04-19 10:35:40');
INSERT INTO `points_record` VALUES (41, 1, 1, 128, '历史消费补录积分', '2024-04-19 13:45:33');
INSERT INTO `points_record` VALUES (42, 1, 1, 45, '历史消费补录积分', '2024-04-19 15:06:56');
INSERT INTO `points_record` VALUES (43, 2, 1, 28, '历史消费补录积分', '2026-05-25 11:18:17');
INSERT INTO `points_record` VALUES (44, 2, 1, 55, '历史消费补录积分', '2026-05-25 11:26:28');
INSERT INTO `points_record` VALUES (45, 2, 1, 46, '历史消费补录积分', '2026-05-25 11:28:24');
INSERT INTO `points_record` VALUES (46, 2, 1, 43, '历史消费补录积分', '2026-05-25 11:34:56');
INSERT INTO `points_record` VALUES (47, 2, 1, 24, '历史消费补录积分', '2026-05-25 11:50:27');
INSERT INTO `points_record` VALUES (48, 2, 1, 10, '历史消费补录积分', '2026-05-25 11:50:49');
INSERT INTO `points_record` VALUES (49, 2, 1, 25, '历史消费补录积分', '2026-05-25 11:51:36');
INSERT INTO `points_record` VALUES (50, 2, 1, 28, '历史消费补录积分', '2026-05-25 22:31:35');
INSERT INTO `points_record` VALUES (51, 2, 1, 18, '历史消费补录积分', '2026-05-27 10:58:13');
INSERT INTO `points_record` VALUES (52, 2, 1, 50, '历史消费补录积分', '2026-05-27 11:19:45');
INSERT INTO `points_record` VALUES (53, 2, 1, 28, '历史消费补录积分', '2026-05-27 12:31:41');
INSERT INTO `points_record` VALUES (54, 2, 1, 34, '历史消费补录积分', '2026-06-01 20:50:05');
INSERT INTO `points_record` VALUES (55, 2, 1, 14, '历史消费补录积分', '2026-06-01 22:34:21');
INSERT INTO `points_record` VALUES (56, 2, 1, 42, '消费获得积分', '2026-06-01 22:39:17');
INSERT INTO `points_record` VALUES (57, 2, 2, 14, '积分兑换菜品: 奶香糯玉米松饼', '2026-06-01 22:46:46');
INSERT INTO `points_record` VALUES (58, 2, 1, 14, '消费获得积分', '2026-06-01 22:51:07');
INSERT INTO `points_record` VALUES (59, 2, 2, 14, '积分兑换菜品: 奶香糯玉米松饼', '2026-06-07 00:45:49');
INSERT INTO `points_record` VALUES (60, 2, 1, 50, '消费获得积分', '2026-06-07 15:24:27');
INSERT INTO `points_record` VALUES (61, 2, 2, 14, '积分兑换菜品: 奶香糯玉米松饼', '2026-06-15 12:11:49');
INSERT INTO `points_record` VALUES (62, 2, 1, 14, '消费获得积分', '2026-06-15 20:01:11');

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting`  (
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `value` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES ('appid', '');
INSERT INTO `setting` VALUES ('appsecret', '');
INSERT INTO `setting` VALUES ('img_ad', '/static/uploads/default/image_ad.png');
INSERT INTO `setting` VALUES ('img_category', '[\"/static/uploads/default/bottom_1.png\",\"/static/uploads/default/bottom_2.png\",\"/static/uploads/default/bottom_3.png\",\"/static/uploads/default/bottom_1.png\"]');
INSERT INTO `setting` VALUES ('img_swiper', '[\"/static/uploads/default/banner_1.png\",\"/static/uploads/default/banner_2.png\",\"/static/uploads/default/banner_3.png\"]');
INSERT INTO `setting` VALUES ('promotion', '[{\"k\":50,\"v\":10}]');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'demo_openid_0000000000000001', 891.00, '2024-04-17 21:57:34');
INSERT INTO `user` VALUES (2, 'demo_openid_0000000000000002', 774.00, '2026-05-25 10:56:48');

SET FOREIGN_KEY_CHECKS = 1;
