use movies;

DROP TABLE IF EXISTS `actors`;
CREATE TABLE `actors` (
  `id` int(11) NOT NULL default '0',
  `first_name` varchar(100) default NULL,
  `last_name` varchar(100) default NULL,
  `gender` char(1) default NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_first_name` (`first_name`(15)),
  KEY `idx_last_name` (`last_name`(15))
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `actors` VALUES (519105,'!Nqate','Xqamxebe','M'),(524655,'1st Lt. Donald W.','Zautcke','M'),(475182,'2','Toff','M'),
(370794,'42nd Street','Pete','M'),(389977,'75th','Ranger Regiment','M'),(213427,'99','Hooker','M'),(380131,'99','Posse','M'),(302008,'A','Martinez','M'),(171966,'A Phai','Giang','M');

DROP TABLE IF EXISTS `directors`;
CREATE TABLE `directors` (
  `id` int(11) NOT NULL default '0',
  `first_name` varchar(100) default NULL,
  `last_name` varchar(100) default NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_first_name` (`first_name`(15)),
  KEY `idx_last_name` (`last_name`(15))
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `directors` VALUES (1114,'A.','Aleksandrov'),(3728,'A.','Babes'),(4175,'A.','Balakrishnan'),(4871,'A.','Barr-Smith'),
(6779,'A.','Berry'),(7125,'A.','Bhimsingh'),(7494,'A.','Bistritsky'),(8026,'A.','Bobrov'),(13355,'A.','Champeaux');

DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies` (
  `id` int(11) NOT NULL default '0',
  `name` varchar(100) default NULL,
  `year` int(11) default NULL,
  `rank` float default NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_name` (`name`(10))
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `movies` VALUES (0,'#28',2002,0),(1,'#7 Train: An Immigrant Journey, The',2000,0),(2,'$',1971,6.4),(3,'$1,000 Reward',1913,0),
(4,'$1,000 Reward',1915,0),(5,'$1,000 Reward',1923,0),(6,'$1,000,000 Duck',1971,5),(7,'$1,000,000 Reward, The',1920,0),
(8,'$10,000 Under a Pillow',1921,0);

DROP TABLE IF EXISTS `movies_directors`;
CREATE TABLE `movies_directors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `director_id` int(11) default NULL,
  `movie_id` int(11) default NULL,
  KEY `idx_director_id` (`director_id`),
  KEY `idx_movie_id` (`movie_id`),
  PRIMARY KEY  (`id`),
  FOREIGN KEY (`director_id`) REFERENCES `directors` (`id`),
  FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `movies_directors` VALUES (1,1,378879),(2,2,281325),(3,3,30621),(4,3,304743),(5,4,60570),(6,5,63525),(7,6,118137),
(8,7,39392),(9,7,44137),(10,7,150368),(11,7,256280),(12,7,273324),(13,7,273325),(14,7,273326);

DROP TABLE IF EXISTS `movies_genres`;
CREATE TABLE `movies_genres` (
  `id` int NOT NULL AUTO_INCREMENT,
  `movie_id` int(11) default NULL,
  `genre` varchar(100) default NULL,
  PRIMARY KEY  (`id`),
  FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `movies_genres` VALUES (1,1,'Short'),(2,1,'Documentary'),(3,2,'Crime'),(4,2,'Comedy'),(5,5,'Western'),(6,6,'Family'),
(7,6,'Comedy'),(8,8,'Animation'),(9,8,'Short'),(10,8,'Comedy'),(11,9,'Drama'),(12,10,'Family');

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `actor_id` int(11) default NULL,
  `movie_id` int(11) default NULL,
  `role` varchar(100) default NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_actor_id` (`actor_id`),
  KEY `idx_movie_id` (`movie_id`),
  KEY `idx_role` (`role`(15)),
  FOREIGN KEY (`actor_id`) REFERENCES `actors` (`id`),
  FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `roles` VALUES (1,2,280088,'Stevie'),(2,2,396232,'Various/lyricist'),(3,3,376687,'Gitano 1'),(4,4,336265,'El Cigala'),
(5,5,135644,'Himself'),(6,6,12083,''),(7,7,252053,''),(8,7,402635,''),(9,7,409592,'Staff Humorist (1996)'),(10,8,101866,'');

