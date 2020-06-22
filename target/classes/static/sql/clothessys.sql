/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : magarita

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-03-04 15:34:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for checking
-- ----------------------------
DROP TABLE IF EXISTS `checking`;
CREATE TABLE `checking` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考勤id',
  `morning_check` varchar(255) DEFAULT NULL COMMENT '上午考勤时间',
  `m_state` varchar(255) DEFAULT NULL,
  `evening_check` varchar(255) DEFAULT NULL COMMENT '下午考勤时间',
  `e_state` varchar(255) DEFAULT NULL,
  `e_id` int(11) DEFAULT NULL COMMENT '关联设备id',
  `now_date` varchar(255) DEFAULT NULL COMMENT '考勤日期',
  PRIMARY KEY (`id`),
  KEY `check_ibfk_2` (`e_id`),
  CONSTRAINT `checking_ibfk_2` FOREIGN KEY (`e_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checking
-- ----------------------------
INSERT INTO `checking` VALUES ('2', null, null, '17:13:49', '早退', '46', '2020-02-17');
INSERT INTO `checking` VALUES ('3', '10:02:53', '迟到', null, null, '46', '2020-02-18');
INSERT INTO `checking` VALUES ('4', '10:06:03', '迟到', null, null, '47', '2020-02-18');
INSERT INTO `checking` VALUES ('5', '10:08:23', '迟到', null, null, '9', '2020-02-18');
INSERT INTO `checking` VALUES ('6', '10:09:10', '迟到', '17:33:28', '正常', '41', '2020-02-18');
INSERT INTO `checking` VALUES ('7', '10:09:10', '迟到', '17:13:49', '早退', '46', '2020-02-19');
INSERT INTO `checking` VALUES ('8', '10:53:10', '迟到', null, null, '9', '2020-02-27');
INSERT INTO `checking` VALUES ('9', null, null, '17:09:08', '早退', '47', '2020-03-03');
INSERT INTO `checking` VALUES ('10', null, null, '17:17:17', '早退', '46', '2020-03-03');
INSERT INTO `checking` VALUES ('11', null, null, '17:52:17', '正常', '9', '2020-03-03');

-- ----------------------------
-- Table structure for check_setting
-- ----------------------------
DROP TABLE IF EXISTS `check_setting`;
CREATE TABLE `check_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `morning_check_last` time DEFAULT NULL COMMENT '早上最晚打卡',
  `evening_check_first` time DEFAULT NULL COMMENT '下午最早打卡',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check_setting
-- ----------------------------
INSERT INTO `check_setting` VALUES ('1', '08:30:00', '17:30:00');

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(9) NOT NULL COMMENT '部门名称',
  `comment` varchar(255) NOT NULL COMMENT '部门职能描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of location
-- ----------------------------
INSERT INTO `location` VALUES ('1', '采购部', '负责材料、设备、成品、半成品的采购管理，并对采购的及时性、价格和质量负责。');
INSERT INTO `location` VALUES ('2', '财务部', '负责公司日常财务核算，参与公司的经营管理。\r\n\r\n负责公司日常财务核算，参与公司的经营管理，并根据公司资金运作情况，合理调配资金，确保公司资金正常运转。');
INSERT INTO `location` VALUES ('3', '销售部', '负责对公司产品价值实现过程中各销售环节实行管理、监督、协调、服务。');
INSERT INTO `location` VALUES ('4', '生产部', '根据公司下达的生产计划，组织生产制造、掌控生产信息，协调人、财、物资源配置。');
INSERT INTO `location` VALUES ('5', '人事部', '对单位中各类设备形成的资源（即把人作为资源）进行管理。');
INSERT INTO `location` VALUES ('6', '总经理办公室', '建立公司的经营管理体系并组织实施和改进，为经营管理体系运行提供足够的资源。');
INSERT INTO `location` VALUES ('7', '划水部', '12313213123123123123213');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备id',
  `username` varchar(12) NOT NULL COMMENT '设备登录名',
  `password` varchar(12) NOT NULL COMMENT '设备登录密码',
  `realname` varchar(12) NOT NULL COMMENT '设备姓名',
  `category` enum('设备','控制') DEFAULT NULL COMMENT '设备类别',
  `address` varchar(50) DEFAULT NULL COMMENT '设备居住地址',
  `email` varchar(30) DEFAULT NULL COMMENT '设备邮箱地址',
  `birth` date DEFAULT NULL COMMENT '设备生产日期',
  `nation` varchar(11) DEFAULT NULL COMMENT '设备的民族',
  `bankcard` varchar(19) DEFAULT NULL COMMENT '员工的银行卡号',
  `telphone` varchar(11) DEFAULT NULL COMMENT '员工手机号码',
  `age` int(3) DEFAULT NULL COMMENT '员工年龄',
  `identity` varchar(18) DEFAULT NULL COMMENT '员工身份证号码',
  `education` varchar(25) DEFAULT NULL COMMENT '员工教育背景',
  `experience` varchar(255) DEFAULT NULL COMMENT '员工工作经验',
  `isdel` int(1) NOT NULL DEFAULT '0' COMMENT '员工是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '2020001', '1234567', '郑淼鑫', '控制', '江西省上饶市婺源县', '1642344895@qq.com', '1998-07-23', '汉族', '6222081812002934027', '15270346086', '23', '362334199807234014', '本科毕业', '两年服装企业管理经验', '0');
INSERT INTO `employee` VALUES ('2', '2020002', '1234567', '李玉琼', '设备', '江西省上饶市婺源县', '3559709236@qq.com', '1998-10-14', '汉族', '6464861481315649844', '15797961544', '23', '649454682538456568', '本科毕业', '一年服装材料采购经验', '0');
INSERT INTO `employee` VALUES ('3', '2020003', '1234567', '韩志军', '控制', '江西省上饶市婺源县', '4561257355@qq.com', '1980-02-05', '汉族', '4649113216135184845', '15248359926', '41', '561891651891356131', '初中毕业', '无经验', '0');
INSERT INTO `employee` VALUES ('4', '2020004', '1234567', '邓勇', '控制', '江西省上饶市婺源县', '1649865164@qq.com', '1979-07-25', '汉族', '9846489531561319811', '46194123191', '42', '435537353327338678', '初中毕业', '无经验', '0');
INSERT INTO `employee` VALUES ('5', '2020005', '1234567', '徐光清', '控制', '江西省上饶市婺源县', '8165481511@qq.com', '1985-07-23', '汉族', '9843982896512356256', '94629129489', '36', '573752753753737537', '初中毕业', '无经验', '0');
INSERT INTO `employee` VALUES ('6', '2020006', '1234567', '陈子刚', '控制', '江西省上饶市婺源县', '8496164981@qq.com', '1988-10-26', '汉族', '6348463189841265316', '49613194131', '33', '946319848431031631', '初中毕业', '无经验', '0');
INSERT INTO `employee` VALUES ('7', '2020007', '1234567', '黄嘉伟', '控制', '江西省上饶市婺源县', '9449832566@qq.com', '1978-11-29', '汉族', '4681189413186533186', '76876873876', '43', '573737373434335437', '高中毕业', '无经验', '0');
INSERT INTO `employee` VALUES ('8', '2020008', '1234567', '邓华杰', '控制', '江西省上饶市婺源县', '9846118416@qq.com', '1980-11-26', '汉族', '9461351861351684153', '12452755787', '41', '767867375354242786', '高中毕业', '无经验', '0');
INSERT INTO `employee` VALUES ('9', '2020009', '123123', '王国祥', '控制', '江西省上饶市婺源县', '4984613189@qq.com', '1984-02-03', '汉族', '8949661896651006626', '15832841351', '40', '786873533576876873', '初中毕业', '两年财务经验', '0');
INSERT INTO `employee` VALUES ('10', '2020010', '1234567', '肖桂芳', '设备', '江西省上饶市婺源县', '8984135186@qq.com', '1994-02-04', '汉族', '7634537888768384373', '15354353543', '28', '442424538678335737', '高中毕业', '五年财务管理经验', '0');
INSERT INTO `employee` VALUES ('11', '2020011', '1234567', '杨芳', '设备', '江西省上饶市婺源县', '4948515488@qq.com', '1985-02-04', '汉族', '2986289613168476161', '76737537375', '37', '738635437572423753', '初中毕业', '两年财务经验', '0');
INSERT INTO `employee` VALUES ('12', '2020012', '1234567', '刘祝君', '设备', '江西省上饶市婺源县', '4961189615@qq.com', '1994-02-04', '汉族', '7678354875737378378', '78638738738', '28', '537537823575737378', '初中毕业', '三年财务经验', '0');
INSERT INTO `employee` VALUES ('13', '2020013', '1234567', '马红梅', '设备', '江西省上饶市婺源县', '4981651898@qq.com', '1994-10-18', '汉族', '7868375678643537537', '57657343738', '28', '768768787687387387', '大专毕业', '三年财务经验', '0');
INSERT INTO `employee` VALUES ('14', '2020014', '1234567', '吴晓峰', '控制', '江西省上饶市婺源县', '9841619841@qq.com', '1989-10-06', '汉族', '6373788686438796873', '78375378376', '33', '756378634578673578', '高中毕业', '三年财务经验', '0');
INSERT INTO `employee` VALUES ('15', '2020015', '1234567', '罗玉玲', '设备', '江西省上饶市婺源县', '9464984154@qq.com', '1984-06-20', '汉族', '8673753737386398867', '73833876345', '37', '786734786738752438', '本科毕业', '十年服装销售经验', '0');
INSERT INTO `employee` VALUES ('16', '2020016', '1234567', '陈宇', '控制', '江西省上饶市婺源县', '8418944613@qq.com', '1994-11-01', '汉族', '9865189164915649816', '78673578367', '27', '196189156324625621', '高中毕业', '五年服装销售经验', '0');
INSERT INTO `employee` VALUES ('17', '2020017', '1234567', '肖淑琼', '设备', '江西省上饶市婺源县', '9494814966@qq.com', '1995-11-29', '汉族', '9841649818948416165', '73733533543', '26', '189166518916168494', '高中毕业', '五年服装销售经验', '0');
INSERT INTO `employee` VALUES ('18', '2020018', '1234567', '陈志刚', '控制', '江西省上饶市婺源县', '9498489499@qq.com', '1999-02-04', '汉族', '7863875438768739876', '78634535738', '22', '412421424146464364', '高中毕业', '五年服装销售经验', '0');
INSERT INTO `employee` VALUES ('19', '2020019', '1234567', '高福军', '控制', '江西省上饶市婺源县', '9491416189@qq.com', '1994-05-09', '汉族', '9461894625849625899', '12357687678', '27', '463186532563516321', '高中毕业', '五年服装销售经验', '0');
INSERT INTO `employee` VALUES ('20', '2020020', '1234567', '易小平', '控制', '江西省上饶市婺源县', '9846689464@qq.com', '1992-07-30', '汉族', '7863378373783783738', '73837838738', '29', '123157375783873833', '大专毕业', '八年制衣经验', '0');
INSERT INTO `employee` VALUES ('21', '2020021', '1234567', '李碧华', '设备', '江西省上饶市婺源县', '9862893189@qq.com', '1995-10-26', '汉族', '7637388738378387373', '37838737383', '26', '537687687378387387', '高中毕业', '四年制衣经验', '0');
INSERT INTO `employee` VALUES ('22', '2020022', '1234567', '崔存丽', '设备', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('23', '2020023', '1234567', '周建华', '控制', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('24', '2020024', '1234567', '韩中山', '控制', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('25', '2020025', '1234567', '王江国', '控制', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('26', '2020026', '1234567', '邓力夫', '控制', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('27', '2020027', '1234567', '彭雅琪', '设备', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('28', '2020028', '1234567', '刘强', '控制', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('29', '2020029', '1234567', '周诗雨', '设备', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('30', '2020030', '1234567', '张文涛', '设备', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('31', '2020031', '1234567', '夏圆圆', '设备', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('32', '2020032', '1234567', '宋雨萌', '设备', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('33', '2020033', '1234567', '王俊文', '控制', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('34', '2020034', '1234567', '李一洋', '设备', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('35', '2020035', '1234567', '黄丹丹', '设备', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('36', '2020036', '1234567', '易佳明', '控制', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('37', '2020037', '1234567', '宋子豪', '控制', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('38', '2020038', '1234567', '魏星', '控制', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('39', '2020039', '1234567', '赵元柱', '控制', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('40', '2020040', '1234567', '王梦媛', '设备', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '三年制衣经验', '0');
INSERT INTO `employee` VALUES ('41', '2020041', '1234567', '吴柳琴', '设备', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '五年服装招聘经验', '0');
INSERT INTO `employee` VALUES ('42', '2020042', '1234567', '刘强', '控制', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '两年服装招聘经验', '0');
INSERT INTO `employee` VALUES ('43', '2020043', '1234567', '徐武', '控制', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '两年服装招聘经验', '0');
INSERT INTO `employee` VALUES ('44', '2020044', '1234567', '赵艳艳', '设备', '江西省上饶市婺源县', '9564567865@qq.com', '1994-03-23', '汉族', '2395848392019285584', '15270341234', '27', '362334212234956704', '高中毕业', '两年服装招聘经验', '0');
INSERT INTO `employee` VALUES ('45', '2020045', '1234567', '王国营', '控制', '江西省上饶市鄱阳县', '9564567865@qq.com', '1998-07-22', '汉族', '2395848392019285584', '15797961544', '23', '362334199807234014', '大学毕业', '两年服装招聘经验', '0');
INSERT INTO `employee` VALUES ('46', '2020046', '123123', '王', '设备', '江西省', '1231312313', '2020-02-11', 'ad', '2222222222222222222', '22222222222', '22', '222222222222222222', '222222', '1231', '1');
INSERT INTO `employee` VALUES ('47', '2020047', '1234567', '郑海琴', '设备', '江西省', '123@qq.com', '2020-01-31', '123123', '12312312', '123123123', '23', '123123', '123', '123', '0');

-- ----------------------------
-- Table structure for memo
-- ----------------------------
DROP TABLE IF EXISTS `memo`;
CREATE TABLE `memo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '备忘录id',
  `content` varchar(255) NOT NULL COMMENT '备忘录内容',
  `time` datetime DEFAULT NULL COMMENT '备忘录创建时间',
  `e_id` int(11) DEFAULT NULL COMMENT '备忘录关联员工id',
  PRIMARY KEY (`id`),
  KEY `e_id` (`e_id`),
  CONSTRAINT `memo_ibfk_1` FOREIGN KEY (`e_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of memo
-- ----------------------------
INSERT INTO `memo` VALUES ('2', '今天整理企业发展前景报告', '2020-02-08 07:36:00', '1');
INSERT INTO `memo` VALUES ('4', '去问', '2020-02-11 05:27:22', '41');
INSERT INTO `memo` VALUES ('5', '1', '2020-02-27 13:02:02', '20');
INSERT INTO `memo` VALUES ('7', '1232131', '2020-03-03 09:51:49', '9');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '信箱id',
  `from_id` int(11) DEFAULT NULL COMMENT '信箱发起设备',
  `to_id` int(11) DEFAULT NULL COMMENT '信箱接收设备',
  `content` varchar(255) DEFAULT NULL COMMENT '信箱内容',
  `start_time` datetime DEFAULT NULL COMMENT '信箱发送时间',
  `end_time` datetime DEFAULT NULL COMMENT '信箱处理时间',
  `status` enum('同意','未处理','驳回') DEFAULT '未处理' COMMENT '信箱是否接收',
  PRIMARY KEY (`id`),
  KEY `message_ibfk_1` (`from_id`),
  KEY `message_ibfk_2` (`to_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('7', '1', '2', '驱蚊器翁', '2020-02-10 13:27:30', '2020-02-11 03:25:34', '同意');
INSERT INTO `message` VALUES ('9', '2', '1', 'ad', '2020-02-12 22:47:38', '2020-02-10 15:32:44', '同意');
INSERT INTO `message` VALUES ('10', '1', '2', 'add多对多', '2020-02-03 11:14:42', '2020-02-11 03:14:53', '驳回');
INSERT INTO `message` VALUES ('11', '1', '9', '111999', '2020-02-11 11:26:52', '2020-02-11 03:35:11', '驳回');
INSERT INTO `message` VALUES ('12', '2', '9', '222999', '2020-02-10 11:27:20', '2020-02-11 03:35:13', '同意');
INSERT INTO `message` VALUES ('13', '9', '2', '999222', '2020-02-18 11:27:41', '2020-02-19 11:27:45', '驳回');
INSERT INTO `message` VALUES ('14', '41', '1', '41410101', '2020-02-11 03:36:22', null, '未处理');
INSERT INTO `message` VALUES ('15', '1', '41', '01014141', '2020-02-17 11:37:30', '2020-02-11 03:38:51', '同意');
INSERT INTO `message` VALUES ('16', '2', '41', '02024141', '2020-02-17 11:37:53', null, '未处理');
INSERT INTO `message` VALUES ('17', '41', '1', '41410101', '2020-02-11 11:38:28', '2020-02-10 11:38:32', '同意');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(11) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL,
  `e_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `d_id` (`d_id`),
  KEY `e_id` (`e_id`),
  CONSTRAINT `position_ibfk_1` FOREIGN KEY (`d_id`) REFERENCES `location` (`id`),
  CONSTRAINT `position_ibfk_2` FOREIGN KEY (`e_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', '总经理', '30000', '6', '1');
INSERT INTO `position` VALUES ('2', '主要物料1号采购员', '3000', '1', '3');
INSERT INTO `position` VALUES ('3', '主要物料2号采购员', '3000', '1', '4');
INSERT INTO `position` VALUES ('4', '一般物料1号采购员', '3000', '1', '5');
INSERT INTO `position` VALUES ('5', '一般物料2号采购员', '3000', '1', '6');
INSERT INTO `position` VALUES ('6', '机器设备1号采购员', '3000', '1', '7');
INSERT INTO `position` VALUES ('7', '机器设备2号采购员', '3000', '1', '8');
INSERT INTO `position` VALUES ('8', '采购经理', '4500', '1', '2');
INSERT INTO `position` VALUES ('9', '财务经理', '4500', '2', '10');
INSERT INTO `position` VALUES ('10', '税务会计', '3000', '2', null);
INSERT INTO `position` VALUES ('11', '总账会计', '3000', '2', '11');
INSERT INTO `position` VALUES ('12', '成本会计', '3000', '2', '12');
INSERT INTO `position` VALUES ('13', '文件核算会计', '3000', '2', '13');
INSERT INTO `position` VALUES ('14', '出纳会计', '3000', '2', '14');
INSERT INTO `position` VALUES ('15', '销售经理', '4500', '3', '15');
INSERT INTO `position` VALUES ('16', '销售秘书', '3000', '3', '16');
INSERT INTO `position` VALUES ('17', '省外1号销售员', '3000', '3', '17');
INSERT INTO `position` VALUES ('18', '省外2号销售员', '3000', '3', '18');
INSERT INTO `position` VALUES ('19', '省内销售员', '3000', '3', '19');
INSERT INTO `position` VALUES ('20', '生产经理', '4500', '4', '20');
INSERT INTO `position` VALUES ('21', '生产A区1号生产员', '3000', '4', '21');
INSERT INTO `position` VALUES ('22', '生产A区2号生产员', '3000', '4', '22');
INSERT INTO `position` VALUES ('23', '生产A区3号生产员', '3000', '4', '23');
INSERT INTO `position` VALUES ('24', '生产A区4号生产员', '3000', '4', '24');
INSERT INTO `position` VALUES ('25', '生产B区1号生产员', '3000', '4', '25');
INSERT INTO `position` VALUES ('26', '生产B区2号生产员', '3000', '4', '26');
INSERT INTO `position` VALUES ('27', '生产B区3号生产员', '3000', '4', '27');
INSERT INTO `position` VALUES ('28', '生产B区4号生产员', '3000', '4', '28');
INSERT INTO `position` VALUES ('29', '生产B区5号生产员', '3000', '4', '29');
INSERT INTO `position` VALUES ('30', '生产C区1号生产员', '3000', '4', '30');
INSERT INTO `position` VALUES ('31', '生产C区2号生产员', '3000', '4', '31');
INSERT INTO `position` VALUES ('32', '生产C区3号生产员', '3000', '4', '32');
INSERT INTO `position` VALUES ('33', '生产C区4号生产员', '3000', '4', '33');
INSERT INTO `position` VALUES ('34', '生产C区5号生产员', '3000', '4', '34');
INSERT INTO `position` VALUES ('35', '生产D区1号生产员', '3000', '4', '35');
INSERT INTO `position` VALUES ('36', '生产D区2号生产员', '3000', '4', '36');
INSERT INTO `position` VALUES ('37', '生产D区3号生产员', '3000', '4', '37');
INSERT INTO `position` VALUES ('38', '生产D区4号生产员', '3000', '4', '38');
INSERT INTO `position` VALUES ('39', '生产D区5号生产员', '3000', '4', '39');
INSERT INTO `position` VALUES ('40', '生产A区5号生产员', '3000', '4', '40');
INSERT INTO `position` VALUES ('41', '人事经理', '4500', '5', '41');
INSERT INTO `position` VALUES ('42', '人事助理', '3000', '5', '42');
INSERT INTO `position` VALUES ('43', '人事1号专员', '3000', '5', '43');
INSERT INTO `position` VALUES ('44', '人事2号专员', '3000', '5', '44');
INSERT INTO `position` VALUES ('45', '人事3号专员', '3000', '5', '45');
INSERT INTO `position` VALUES ('46', '划水部1号员工', '3500', '7', '46');
INSERT INTO `position` VALUES ('47', '划水部经理', '4500', '7', '47');
INSERT INTO `position` VALUES ('48', '划水部2号员工', '3500', '7', '9');

-- ----------------------------
-- Table structure for position_change
-- ----------------------------
DROP TABLE IF EXISTS `position_change`;
CREATE TABLE `position_change` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备权限变更id',
  `start_time` datetime DEFAULT NULL COMMENT '设备权限变更发起时间',
  `effect_time` datetime DEFAULT NULL COMMENT '设备权限变更生效时间',
  `emp_reason` varchar(255) DEFAULT NULL COMMENT '设备权限变动原因',
  `type` enum('部署变动','下线') DEFAULT NULL,
  `dm_comment` varchar(255) DEFAULT NULL COMMENT '部门经理意见',
  `dm_status` enum('同意','驳回') DEFAULT NULL,
  `gm_comment` varchar(255) DEFAULT NULL COMMENT '总经理意见',
  `gm_status` enum('驳回','同意') DEFAULT NULL,
  `e_id` int(11) DEFAULT NULL COMMENT '关联employee表id',
  `p_id` int(11) DEFAULT NULL COMMENT '所在的部署',
  `t_id` int(11) DEFAULT NULL COMMENT '要调去的部署',
  PRIMARY KEY (`id`),
  KEY `e_id` (`e_id`),
  KEY `p_id` (`p_id`),
  KEY `t_id` (`t_id`),
  CONSTRAINT `position_change_ibfk_1` FOREIGN KEY (`e_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `position_change_ibfk_2` FOREIGN KEY (`p_id`) REFERENCES `position` (`id`),
  CONSTRAINT `position_change_ibfk_3` FOREIGN KEY (`t_id`) REFERENCES `position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position_change
-- ----------------------------
INSERT INTO `position_change` VALUES ('32', '2020-03-03 09:54:06', '2020-03-03 10:08:58', '正苗欣欣', '部署变动', '同意', '同意', '同意', '同意', '9', '10', '48');

-- ----------------------------
-- Table structure for request
-- ----------------------------
DROP TABLE IF EXISTS `request`;
CREATE TABLE `request` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '请假申请id',
  `start_time` datetime DEFAULT NULL COMMENT '假期开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '假期结束时间',
  `reason` varchar(255) DEFAULT NULL COMMENT '请假原因',
  `status` enum('同意','未处理','驳回') DEFAULT '未处理',
  `e_id` int(11) DEFAULT NULL COMMENT '管理员工id',
  PRIMARY KEY (`id`),
  KEY `e_id` (`e_id`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`e_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of request
-- ----------------------------
INSERT INTO `request` VALUES ('1', '2020-02-10 01:13:43', '2020-02-10 13:13:51', '回家', '同意', '42');
INSERT INTO `request` VALUES ('2', '2020-02-04 13:14:16', '2020-02-12 13:14:21', '吃饭', '驳回', '44');
INSERT INTO `request` VALUES ('3', '2020-02-02 14:05:09', '2020-02-11 14:05:06', '12313', '驳回', '21');
INSERT INTO `request` VALUES ('4', '2020-02-20 14:05:28', '2020-03-04 14:05:24', '第三方士大夫', '同意', '22');
INSERT INTO `request` VALUES ('5', '2020-02-18 08:52:29', '2020-02-19 11:52:36', '123123', '同意', '41');
INSERT INTO `request` VALUES ('6', '2020-02-09 08:52:29', '2020-02-09 11:52:36', '123', '同意', '20');
INSERT INTO `request` VALUES ('7', '2020-02-16 19:03:03', '2020-02-27 14:07:45', '去问', '驳回', '9');
INSERT INTO `request` VALUES ('8', '2020-03-03 00:17:56', '2020-03-04 09:18:09', 'huijia', '同意', '46');
