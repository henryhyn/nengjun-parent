DROP TABLE IF EXISTS `poi_plant`;

CREATE TABLE `poi_plant` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT,
  `name`     VARCHAR(64)      DEFAULT NULL,
  `alt_name` VARCHAR(64)      DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `poi_plant` VALUES (1, '玫瑰花', '爱情花');

DROP TABLE IF EXISTS `poi_article`;

CREATE TABLE `poi_article` (
  `id`          INT(11)  NOT NULL AUTO_INCREMENT,
  `status`      INT(11)  NOT NULL DEFAULT 0
  COMMENT '状态: 0, 初始态; 1, 在线发布; -1 下线隐藏',
  `title`       VARCHAR(512)      DEFAULT NULL,
  `summary`     VARCHAR(255)      DEFAULT NULL,
  `cover`       VARCHAR(255)      DEFAULT NULL,
  `md_content`  LONGTEXT,
  `content`     LONGTEXT,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_on_update_time` (`update_time`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `poi_article` (title, summary, content) VALUES ('开始写文章啦', '', '<h2>这是我的第一篇文章!</h2>');
INSERT INTO `poi_article` (title, summary, content) VALUES ('继续写哦', '', '<h2>这是我的第二篇文章!</h2>');

DROP TABLE IF EXISTS `poi_country`;

CREATE TABLE poi_country (
  id          INT PRIMARY KEY AUTO_INCREMENT,
  countryname VARCHAR(32),
  countrycode VARCHAR(2)
);

INSERT INTO poi_country (id, countryname, countrycode) VALUES (1, 'Angola', 'AO');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (2, 'Afghanistan', 'AF');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (3, 'Albania', 'AL');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (4, 'Algeria', 'DZ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (5, 'Andorra', 'AD');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (6, 'Anguilla', 'AI');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (7, 'Antigua and Barbuda', 'AG');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (8, 'Argentina', 'AR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (9, 'Armenia', 'AM');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (10, 'Australia', 'AU');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (11, 'Austria', 'AT');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (12, 'Azerbaijan', 'AZ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (13, 'Bahamas', 'BS');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (14, 'Bahrain', 'BH');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (15, 'Bangladesh', 'BD');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (16, 'Barbados', 'BB');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (17, 'Belarus', 'BY');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (18, 'Belgium', 'BE');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (19, 'Belize', 'BZ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (20, 'Benin', 'BJ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (21, 'Bermuda Is.', 'BM');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (22, 'Bolivia', 'BO');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (23, 'Botswana', 'BW');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (24, 'Brazil', 'BR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (25, 'Brunei', 'BN');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (26, 'Bulgaria', 'BG');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (27, 'Burkina-faso', 'BF');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (28, 'Burma', 'MM');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (29, 'Burundi', 'BI');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (30, 'Cameroon', 'CM');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (31, 'Canada', 'CA');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (32, 'Central African Republic', 'CF');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (33, 'Chad', 'TD');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (34, 'Chile', 'CL');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (35, 'China', 'CN');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (36, 'Colombia', 'CO');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (37, 'Congo', 'CG');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (38, 'Cook Is.', 'CK');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (39, 'Costa Rica', 'CR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (40, 'Cuba', 'CU');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (41, 'Cyprus', 'CY');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (42, 'Czech Republic', 'CZ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (43, 'Denmark', 'DK');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (44, 'Djibouti', 'DJ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (45, 'Dominica Rep.', 'DO');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (46, 'Ecuador', 'EC');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (47, 'Egypt', 'EG');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (48, 'EI Salvador', 'SV');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (49, 'Estonia', 'EE');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (50, 'Ethiopia', 'ET');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (51, 'Fiji', 'FJ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (52, 'Finland', 'FI');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (53, 'France', 'FR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (54, 'French Guiana', 'GF');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (55, 'Gabon', 'GA');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (56, 'Gambia', 'GM');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (57, 'Georgia', 'GE');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (58, 'Germany', 'DE');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (59, 'Ghana', 'GH');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (60, 'Gibraltar', 'GI');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (61, 'Greece', 'GR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (62, 'Grenada', 'GD');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (63, 'Guam', 'GU');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (64, 'Guatemala', 'GT');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (65, 'Guinea', 'GN');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (66, 'Guyana', 'GY');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (67, 'Haiti', 'HT');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (68, 'Honduras', 'HN');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (69, 'Hongkong', 'HK');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (70, 'Hungary', 'HU');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (71, 'Iceland', 'IS');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (72, 'India', 'IN');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (73, 'Indonesia', 'ID');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (74, 'Iran', 'IR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (75, 'Iraq', 'IQ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (76, 'Ireland', 'IE');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (77, 'Israel', 'IL');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (78, 'Italy', 'IT');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (79, 'Jamaica', 'JM');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (80, 'Japan', 'JP');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (81, 'Jordan', 'JO');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (82, 'Kampuchea (Cambodia )', 'KH');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (83, 'Kazakstan', 'KZ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (84, 'Kenya', 'KE');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (85, 'Korea', 'KR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (86, 'Kuwait', 'KW');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (87, 'Kyrgyzstan', 'KG');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (88, 'Laos', 'LA');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (89, 'Latvia', 'LV');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (90, 'Lebanon', 'LB');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (91, 'Lesotho', 'LS');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (92, 'Liberia', 'LR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (93, 'Libya', 'LY');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (94, 'Liechtenstein', 'LI');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (95, 'Lithuania', 'LT');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (96, 'Luxembourg', 'LU');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (97, 'Macao', 'MO');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (98, 'Madagascar', 'MG');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (99, 'Malawi', 'MW');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (100, 'Malaysia', 'MY');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (101, 'Maldives', 'MV');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (102, 'Mali', 'ML');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (103, 'Malta', 'MT');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (104, 'Mauritius', 'MU');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (105, 'Mexico', 'MX');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (106, 'Moldova, Republic of', 'MD');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (107, 'Monaco', 'MC');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (108, 'Mongolia', 'MN');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (109, 'Montserrat Is', 'MS');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (110, 'Morocco', 'MA');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (111, 'Mozambique', 'MZ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (112, 'Namibia', 'NA');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (113, 'Nauru', 'NR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (114, 'Nepal', 'NP');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (115, 'Netherlands', 'NL');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (116, 'New Zealand', 'NZ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (117, 'Nicaragua', 'NI');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (118, 'Niger', 'NE');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (119, 'Nigeria', 'NG');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (120, 'North Korea', 'KP');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (121, 'Norway', 'NO');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (122, 'Oman', 'OM');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (123, 'Pakistan', 'PK');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (124, 'Panama', 'PA');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (125, 'Papua New Cuinea', 'PG');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (126, 'Paraguay', 'PY');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (127, 'Peru', 'PE');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (128, 'Philippines', 'PH');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (129, 'Poland', 'PL');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (130, 'French Polynesia', 'PF');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (131, 'Portugal', 'PT');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (132, 'Puerto Rico', 'PR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (133, 'Qatar', 'QA');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (134, 'Romania', 'RO');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (135, 'Russia', 'RU');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (136, 'Saint Lueia', 'LC');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (137, 'Saint Vincent', 'VC');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (138, 'San Marino', 'SM');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (139, 'Sao Tome and Principe', 'ST');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (140, 'Saudi Arabia', 'SA');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (141, 'Senegal', 'SN');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (142, 'Seychelles', 'SC');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (143, 'Sierra Leone', 'SL');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (144, 'Singapore', 'SG');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (145, 'Slovakia', 'SK');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (146, 'Slovenia', 'SI');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (147, 'Solomon Is', 'SB');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (148, 'Somali', 'SO');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (149, 'South Africa', 'ZA');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (150, 'Spain', 'ES');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (151, 'Sri Lanka', 'LK');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (152, 'St.Lucia', 'LC');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (153, 'St.Vincent', 'VC');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (154, 'Sudan', 'SD');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (155, 'Suriname', 'SR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (156, 'Swaziland', 'SZ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (157, 'Sweden', 'SE');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (158, 'Switzerland', 'CH');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (159, 'Syria', 'SY');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (160, 'Taiwan', 'TW');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (161, 'Tajikstan', 'TJ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (162, 'Tanzania', 'TZ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (163, 'Thailand', 'TH');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (164, 'Togo', 'TG');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (165, 'Tonga', 'TO');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (166, 'Trinidad and Tobago', 'TT');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (167, 'Tunisia', 'TN');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (168, 'Turkey', 'TR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (169, 'Turkmenistan', 'TM');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (170, 'Uganda', 'UG');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (171, 'Ukraine', 'UA');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (172, 'United Arab Emirates', 'AE');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (173, 'United Kiongdom', 'GB');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (174, 'United States of America', 'US');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (175, 'Uruguay', 'UY');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (176, 'Uzbekistan', 'UZ');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (177, 'Venezuela', 'VE');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (178, 'Vietnam', 'VN');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (179, 'Yemen', 'YE');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (180, 'Yugoslavia', 'YU');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (181, 'Zimbabwe', 'ZW');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (182, 'Zaire', 'ZR');
INSERT INTO poi_country (id, countryname, countrycode) VALUES (183, 'Zambia', 'ZM');

DROP TABLE IF EXISTS `poi_picture`;

CREATE TABLE `poi_picture` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `picture_key` VARCHAR(255)     DEFAULT NULL,
  `fsize`       INT(11) NOT NULL,
  `mime_type`   VARCHAR(255)     DEFAULT NULL,
  `width`       INT(11) NOT NULL,
  `height`      INT(11) NOT NULL,
  `color_model` VARCHAR(255)     DEFAULT NULL,
  `make_time`   VARCHAR(255)     DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `poi_picture` VALUES (1, 'FuyDovSaSxq_hJBTXF1smfq9kYEg', 29472, 'image/jpeg', 440, 330, 'ycbcr', NULL);
INSERT INTO `poi_picture` VALUES (2, 'Flk-wACsCjHFMnyIfiU4plIemilJ', 18682, 'image/jpeg', 420, 323, 'ycbcr', NULL);

DROP TABLE IF EXISTS `poi_shop`;

CREATE TABLE `poi_shop` (
  `id`          INT(11)  NOT NULL AUTO_INCREMENT,
  `status`      INT(11)  NOT NULL DEFAULT 1
  COMMENT '状态: 0, 初始态; 1, 在线发布; -1 下线隐藏',
  `shop_name`   VARCHAR(512)      DEFAULT NULL,
  `branch_name` VARCHAR(255)      DEFAULT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_on_update_time` (`update_time`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `poi_shop` (shop_name, branch_name) VALUES ('中山公园', '');
INSERT INTO `poi_shop` (shop_name, branch_name) VALUES ('龙之梦', '中山公园');
INSERT INTO `poi_shop` (shop_name, branch_name) VALUES ('龙之梦', '虹口足球场');

DROP TABLE IF EXISTS `poi_tag`;

CREATE TABLE `poi_tag` (
  `id`           INT(11)  NOT NULL AUTO_INCREMENT,
  `status`       INT(11)  NOT NULL DEFAULT 1
  COMMENT '状态: 0, 初始态; 1, 在线发布; -1 下线隐藏',
  `shop_id`      INT(11)           DEFAULT NULL,
  `tag_type`     VARCHAR(8)        DEFAULT NULL,
  `tag_sub_type` VARCHAR(8)        DEFAULT NULL,
  `summary`      VARCHAR(32)       DEFAULT NULL,
  `content`      TEXT              DEFAULT NULL,
  `md_content`   TEXT              DEFAULT NULL,
  `create_time`  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time`  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_on_update_time` (`update_time`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `poi_tag` (shop_id, tag_type, tag_sub_type, summary, content)
VALUES (1, '名人出没', '名人光顾', '邓超一家人曾一起来游玩', '<p>看这是他们来游玩的照片</p>')
  , (2, '权威推荐', '媒体推荐', '知名公众号91聚乐曾报道', '<p>看看报道原文吧</p>');

DROP TABLE IF EXISTS `poi_activity`;

CREATE TABLE `poi_activity` (
  `id`          INT(11)  NOT NULL  AUTO_INCREMENT,
  `status`      INT(11)  NOT NULL  DEFAULT 1
  COMMENT '状态: 0, 初始态; 1, 在线发布; -1 下线隐藏',
  `cover`       VARCHAR(255)       DEFAULT NULL,
  `title`       VARCHAR(512)       DEFAULT NULL,
  `category_id` INT(11)            DEFAULT NULL,
  `longitude`   DOUBLE             DEFAULT NULL,
  `latitude`    DOUBLE             DEFAULT NULL,
  `address`     VARCHAR(255)       DEFAULT NULL,
  `crossroad`   VARCHAR(255)       DEFAULT NULL,
  `fee`         DOUBLE             DEFAULT NULL,
  `time`        DOUBLE             DEFAULT NULL,
  `length`      DOUBLE             DEFAULT NULL,
  `route`       VARCHAR(255)       DEFAULT NULL,
  `route_info`  TEXT               DEFAULT NULL,
  `summary`     VARCHAR(64)        DEFAULT NULL,
  `content`     TEXT               DEFAULT NULL,
  `md_content`  TEXT               DEFAULT NULL,
  `create_time` DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_on_update_time` (`update_time`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `poi_activity` (cover, title, category_id, address, crossroad, fee, summary, content, md_content)
VALUES ('http://oud35cwi4.bkt.clouddn.com/FuyDovSaSxq_hJBTXF1smfq9kYEg', '【城市漫步】初秋, 领略风情万种', 1, '江苏路地铁站 4 号口', '江苏路', 0,
        '苏州河畔的微风, 凌空SOHO的灯光...', '<p>poiTag#1</p>', 'poiTag#1'),
  ('http://oud35cwi4.bkt.clouddn.com/Flk-wACsCjHFMnyIfiU4plIemilJ', '【暴走毅行】大海, 我还欠你一个约定', 2, '滴水湖', NULL, 90, NULL,
   NULL, NULL);

DROP TABLE IF EXISTS `poi_todo`;

CREATE TABLE `poi_todo` (
  `id`          INT(11)  NOT NULL AUTO_INCREMENT,
  `status`      INT(11)  NOT NULL DEFAULT '0'
  COMMENT '状态: 0, 初始态',
  `importance`  TINYINT(4)        DEFAULT NULL,
  `emergency`   TINYINT(4)        DEFAULT NULL,
  `difficulty`  TINYINT(4)        DEFAULT NULL,
  `name`        VARCHAR(64)       DEFAULT NULL,
  `description` VARCHAR(512)      DEFAULT NULL,
  `begin_time`  DATETIME          DEFAULT NULL,
  `end_time`    DATETIME          DEFAULT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_on_update_time` (`update_time`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `poi_todo` (name, description) VALUES ('分页查询', '完善 MyBatis 分页查询插件');

DROP TABLE IF EXISTS `poi_user`;

CREATE TABLE `poi_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(32) NOT NULL,
  `phone1` varchar(16) DEFAULT NULL,
  `phone2` varchar(16) DEFAULT NULL,
  `nick_name` varchar(64) DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `language` varchar(32) DEFAULT NULL,
  `city` varchar(32) DEFAULT NULL,
  `province` varchar(32) DEFAULT NULL,
  `country` varchar(32) DEFAULT NULL,
  `avatar_url` varchar(512) DEFAULT NULL,
  `session_key` varchar(32) DEFAULT NULL,
  `expires_time` bigint(20) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_on_openid` (`openid`),
  KEY `idx_on_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
