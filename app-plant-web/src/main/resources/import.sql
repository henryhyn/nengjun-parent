DROP TABLE IF EXISTS `poi_plant`;

CREATE TABLE `poi_plant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `poi_plant` VALUES (1,'玫瑰花');
