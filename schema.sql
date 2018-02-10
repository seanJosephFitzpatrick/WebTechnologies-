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

DROP TABLE IF EXISTS `directors`;
CREATE TABLE `directors` (
  `id` int(11) NOT NULL default '0',
  `first_name` varchar(100) default NULL,
  `last_name` varchar(100) default NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_first_name` (`first_name`(15)),
  KEY `idx_last_name` (`last_name`(15))
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies` (
  `id` int(11) NOT NULL default '0',
  `name` varchar(100) default NULL,
  `year` int(11) default NULL,
  `rank` float default NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_name` (`name`(10))
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `movies_directors`;
CREATE TABLE `movies_directors` (
  id int NOT NULL AUTO_INCREMENT,
  `director_id` int(11) default NULL,
  `movie_id` int(11) default NULL,
  KEY `idx_director_id` (`director_id`),
  KEY `idx_movie_id` (`movie_id`),
  PRIMARY KEY  (`id`),
  FOREIGN KEY (`director_id`) REFERENCES `directors` (`id`),
  FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `movies_genres`;
CREATE TABLE `movies_genres` (
  id int NOT NULL AUTO_INCREMENT,
  `movie_id` int(11) default NULL,
  `genre` varchar(100) default NULL,
  PRIMARY KEY  (`id`),
  FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  id int NOT NULL AUTO_INCREMENT,
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

