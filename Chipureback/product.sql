/*
 Navicat Premium Data Transfer

 Source Server         : 01
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : localhost:3306
 Source Schema         : chipure

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 30/06/2023 15:09:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_category` int(11) NOT NULL,
  `product_intro` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_picture` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_price` double NOT NULL,
  `product_sales` int(11) NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `FK_product_category`(`product_category`) USING BTREE,
  CONSTRAINT `FK_product_category` FOREIGN KEY (`product_category`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'CSGO', 1, '第一人称射击', '/public/gameimg/fate.jpg', 59.99, 56);
INSERT INTO `product` VALUES (2, 'Dying Light', 4, '以丧尸横行的末世为背景的开放世界第一人称动作生存游戏。在一个被神秘病毒摧残过后的城市里漫游。收集物资，制作武器并迎战成群的感染者。', 'public/imgs/0.jpg', 109, 48);
INSERT INTO `product` VALUES (3, 'Grand Theft Auto V', 2, '一个初涉江湖的街头新丁、一个洗手多年的银行劫匪和一个丧心病狂的杀人狂魔，误打误撞中深陷犯罪集团、美国政府和娱乐产业之间盘根错杂的恐怖困境。他们必须齐心协力，接连完成九死一生的惊天劫案，才能在这个冷血无情的城市中苟延残喘。不要相信任何人，尤其是你的同伙！PC 版Grand Theft Auto V 能够以超越 4K 的最高分辨率和 60 帧每秒的帧率，为您呈现屡获殊荣、令人痴迷的游戏世界——洛桑托斯市和布雷恩郡。 ', 'public/imgs/1.jpg', 68.02, 70);
INSERT INTO `product` VALUES (4, 'Destiny 2', 3, '进入命运2的免费游戏世界来探索我们太阳系的隐秘并体验第一人称射击战斗。解锁强大的元素技能并收集独特的装备来个性化你的外观和游戏风格。独自或与其他朋友一起体验命运2的影片故事，进行合作任务，或与其他守护者在PvP模式中进行竞争。今天免费下载，在星空中书写你的传奇。', 'public/imgs/2.jpg', 209, 60);
INSERT INTO `product` VALUES (5, 'ARK: Survival Evolved', 1, '由虚幻4引擎打造的一款多人在线生存竞技网游，在一个超高自由度的开放世界里，可以体验采集、制造、打猎、收获、建造、研究以及驯服恐龙等超多自由内容，感受酷热白昼、冰冷夜晚的动态天气系统以及饥饿口渴等现实中的生存挑战，还要面对其它生存者的威胁，合作生存还是竞技厮杀，由你决定！', 'public/imgs/3.jpg', 70, 63);
INSERT INTO `product` VALUES (6, '霍格沃茨之遗', 4, '《霍格沃茨之遗》是一款基于《哈利·波特》系列书籍设定的开放世界动作角色扮演游戏。在旅程中，你将造访那些熟悉的和陌生的地点，发现奇妙的野兽，自定义你的角色并制造魔药，掌握施放咒语的技巧，升级天赋并成为你所向往的巫师。', 'public/imgs/4.jpg', 298, 25);
INSERT INTO `product` VALUES (7, '火山的女儿', 1, '《火山的女儿》是一款多结局养成游戏。妻子逝去后，你就成了她唯一的守护者。你需要悉心安排她的生活，陪伴她成长。在家庭之外，人魔之间的矛盾愈演愈烈，在魔族首领“鸮姬”卷土重来之际，火山国需要更多的光明的力量。女儿将成为一名怎样的女性？火山国的未来又将走向何方？你的每个选择都很重要。', 'public/imgs/5.jpg', 29.75, 45);
INSERT INTO `product` VALUES (8, 'Elden Ring', 3, '艾尔登法环是以正统黑暗奇幻世界为舞台的动作RPG游戏。 走进辽阔的场景与地下迷宫探索未知，挑战困难重重的险境，享受克服困境时的成就感吧。 不仅如此，登场角色之间的利害关系谱成的群像剧，更是不容错过。', 'public/imgs/6.jpg', 298, 77);
INSERT INTO `product` VALUES (9, 'Stardew Valley', 1, '你继承了你爷爷在星露谷留下的老旧农场。带着爷爷留下的残旧工具和几枚硬币开始了你的新生活。你能适应这小镇上的生活并且将这个杂草丛生的老旧农场变成一个繁荣的家吗？', 'public/imgs/7.jpg', 31.68, 32);
INSERT INTO `product` VALUES (10, 'Assassin\'s Creed? Odyssey', 2, '在《Assassin\'s Creed? Odyssey》当中主宰自己的命运。 从一名流浪者蜕变成传奇人物，踏上这趟奥德赛之旅，找出你过往的秘密并改变古希腊的命运。', 'public/imgs/8.jpg', 59.6, 42);
INSERT INTO `product` VALUES (11, 'Dying Light 2 Stay Human', 2, '病毒获胜了，文明退回了黑暗时代。作为人类最后的堡垒之一，“都市”正处在崩溃的边缘。运用你的敏捷和战斗技巧活下去，并重塑这个世界。你的选择意义重大。', 'public/imgs/9.jpg', 99, 26);
INSERT INTO `product` VALUES (12, 'The Elder Scrolls V: Skyrim Special Edition', 4, 'Winner of more than 200 Game of the Year Awards, Skyrim Special Edition brings the epic fantasy to life in stunning detail. The Special Edition includes the critically acclaimed game and add-ons with all-new features like remastered art and effects, volumetric god rays, dynamic depth of field, screen-space reflections, and more. Skyrim Special Edition also brings the full power of mods to the PC and consoles. New quests, environments, characters, dialogue, armor, weapons and more – with Mods, there are no limits to what you can experience.', 'public/imgs/10.jpg', 41.25, 73);
INSERT INTO `product` VALUES (13, 'Red Dead Redemption 2', 5, '亚瑟·摩根和范德林德帮众是一群逃亡在外的亡命之徒。联邦侦探和全国顶尖的赏金猎人在他们的身后穷追不舍，亚瑟一行人必须在广袤蛮荒的美国腹地上四处劫掠、挣扎求生。而帮派内部的矛盾分化日渐加深，摆在亚瑟面前的将是他无法避免的抉择：究竟是选择自己的理想，还是选择效忠于抚养了自己的帮派。', 'public/imgs/11.jpg', 92.07, 87);
INSERT INTO `product` VALUES (14, '巫师 3：狂猎', 3, '您是利维亚的杰洛特，收钱办事的怪物杀手。您可以在眼前这片怪物横行、饱受战火摧残的土地上尽情探索。您手上的委托？追踪预言之子——希里，一件足以改变世界面貌的活生生的武器。', 'public/imgs/12.jpg', 38.1, 20);
INSERT INTO `product` VALUES (15, 'Watch_Dogs? 2', 2, '欢迎来到技术革命的起源地：旧金山。扮演年轻的聪明黑客马可仕（Marcus），加入最恶名昭彰的黑客团体 DedSec。你的目标：执行史上最大规模的黑客行动。', 'public/imgs/13.jpg', 44.7, 25);

SET FOREIGN_KEY_CHECKS = 1;
