DROP TABLE IF EXISTS `plays_in` CASCADE;
DROP TABLE IF EXISTS `has_composed` CASCADE;
DROP TABLE IF EXISTS `performance` CASCADE;
DROP TABLE IF EXISTS `concert` CASCADE;
DROP TABLE IF EXISTS `performer` CASCADE;
DROP TABLE IF EXISTS `composer` CASCADE;
DROP TABLE IF EXISTS `band` CASCADE;
DROP TABLE IF EXISTS `musician` CASCADE;
DROP TABLE IF EXISTS `composition` CASCADE;
DROP TABLE IF EXISTS `place` CASCADE;

CREATE TABLE `place` (
	`place_id`	INTEGER NOT NULL,
	`place_town`	TEXT,
	`place_country`	TEXT,
	PRIMARY KEY(`place_id`)
);


CREATE TABLE `musician` (
	`musician_id`	INTEGER NOT NULL,
	`musician_name`	TEXT,
	`born_on`	DATE default NULL,
	`died_on`	DATE DEFAULT NULL,
	`born_in`	INTEGER,
	`living_in`	INTEGER,
	PRIMARY KEY(`musician_id`),
	FOREIGN KEY(`born_in`) REFERENCES `place`(`place_id`),
	FOREIGN KEY(`living_in`) REFERENCES `place`(`place_id`)
);

CREATE TABLE `performer` (
	`performer_id`	INTEGER NOT NULL,
	`performer_is`	INTEGER,
	`instrument`	TEXT,
	`performer_type`	TEXT,
	PRIMARY KEY(`performer_id`),
	FOREIGN KEY(`performer_is`) REFERENCES `musician`(`musician_id`)
);


CREATE TABLE `concert` (
	`concert_id`	INTEGER NOT NULL,
	`concert_venue`	TEXT,
	`concert_in`	INTEGER,
	`concert_date`	DATE DEFAULT NULL,
	`concert_organiser`	INTEGER,
	PRIMARY KEY(`concert_id`),
	FOREIGN KEY(`concert_in`) REFERENCES `place`(`place_id`),
	FOREIGN KEY(`concert_organiser`) REFERENCES `musician`(`musician_id`)
);
CREATE TABLE  `composition` (
	`composition_id`	INTEGER NOT NULL,
	`composition_date`	DATE DEFAULT NULL,
	`composition_title`	TEXT,
	`composed_in`	INTEGER,
	PRIMARY KEY(`composition_id`),
	FOREIGN KEY(`composed_in`) REFERENCES `place`(`place_id`)
);

CREATE TABLE `composer` (
	`composer_id`	INTEGER NOT NULL,
	`composer_is`	INTEGER,
	`composer_type`	TEXT,
	PRIMARY KEY(`composer_id`),
	FOREIGN KEY(`composer_is`) REFERENCES `musician`(`musician_id`)
);

CREATE TABLE `has_composed` (
	`composer_id`	INTEGER NOT NULL,
	`composition_id`	INTEGER,
	FOREIGN KEY(`composition_id`) REFERENCES `composition`(`composition_id`),
	FOREIGN KEY(`composer_id`) REFERENCES `composer`(`composer_id`),
	PRIMARY KEY(`composer_id`,`composition_id`)
);


CREATE TABLE `band` (
	`band_id`	INTEGER NOT NULL,
	`band_name`	TEXT,
	`band_home`	INTEGER,
	`band_type`	TEXT,
	`band_start_date`	DATE DEFAULT NULL,
	`band_contact`	INTEGER,
	PRIMARY KEY(`band_id`),
	FOREIGN KEY(`band_contact`) REFERENCES `musician`(`musician_id`),
	FOREIGN KEY(`band_home`) REFERENCES `place`(`place_id`)
);


CREATE TABLE `performance` (
	`performance_id`	INTEGER NOT NULL,
	`gave`	INTEGER,
	`performed`	INTEGER,
	`conducted_by`	INTEGER,
	`performed_in`	INTEGER,
	FOREIGN KEY(`performed`) REFERENCES `composition`(`composition_id`),
	FOREIGN KEY(`performed_in`) REFERENCES `concert`(`concert_id`),
	PRIMARY KEY(`performance_id`),
	FOREIGN KEY(`gave`) REFERENCES `band`(`band_id`),
	FOREIGN KEY(`conducted_by`) REFERENCES `musician`(`musician_id`)
);

CREATE TABLE `plays_in` (
	`player_id`	INTEGER NOT NULL,
	`band_id`	INTEGER,
	FOREIGN KEY(`player_id`) REFERENCES `performer`(`performer_id`),
	FOREIGN KEY(`band_id`) REFERENCES `band`(`band_id`),
	PRIMARY KEY(`player_id`,`band_id`)
);
