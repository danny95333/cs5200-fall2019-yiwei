CREATE TABLE `person` (
	`id` int NOT NULL auto_increment,
    `username` varchar(20) NOT NULL,
    `password` varchar(20) NOT NULL,
    `first_name` varchar (15) NOT NULL,
    `last_name` varchar(15) NOT NULL,
    `email` varchar(50) NOT NULL,
    `dob` date DEFAULT NULL,
    primary key (`id`)
);

CREATE TABLE `developer` (
	`person_id` int NOT NULL,
    `developer_key` varchar(8) NOT NULL,
    primary key (`person_id`),
		CONSTRAINT developer_person_generalization
			foreign key (`person_id`)
				references `person`(`id`)
);

CREATE TABLE `user` (
	`id` int NOT NULL auto_increment,
    `user_agreement` tinyint(1) default 0,
    primary key (`id`),
	CONSTRAINT user_person_generalization
		foreign key (`id`)
            references `person`(`id`)
);

CREATE TABLE `website` (
	`id` int NOT NULL auto_increment,
	`name` varchar(20) NOT NULL,
    `description` varchar(255) NOT NULL,
    `created` date DEFAULT NULL,
    `updated` date DEFAULT NULL,
    `visits` int NOT NULL,
    primary key (`id`)
);

CREATE TABLE `page` (
	`id` int NOT NULL auto_increment,
    `title` varchar(20) NOT NULL,
    `description` varchar(255) NOT NULL,
    `created` date default NULL,
    `updated` date default NULL,
    `views` int NOT NULL,
    `website_id` int NOT NULL, 
    primary key (`id`),
	CONSTRAINT page_website_generalization
		foreign key (`website_id`)
			references `website`(`id`)
				on delete cascade
);

CREATE TABLE `widget` (
	`id` int NOT NULL auto_increment,
    `name` varchar(20) NOT NULL,
    `width` int default NULL,
    `height` int default NULL,
    `css_class` varchar(20) default NULL,
    `css_style` varchar(20) default NULL,
    `text` varchar(255) default NULL,
    `order` int NOT NULL,
    `size` int default 2, -- heading
    `html` varchar(50) default NULL, -- html
	`url` varchar(255) default NULL, -- Youtube
    `shareble` tinyint(1) default NULL, -- Youtube
    `expandable` tinyint(1) default NULL, -- Youtube
    `src` varchar(50) default NULL, -- Image
    `dtype` varchar(10) NOT NULL, -- h/y/I/head
    primary key (`id`),
    `page_id` int NOT NULL,
    CONSTRAINT widget_page_composition
		foreign key(`page_id`)
			references `page`(`id`)
				on delete cascade 
);

CREATE TABLE `address` (
	`id` int NOT NULL auto_increment,
    `street1` varchar(50) NOT NULL,
    `street2` varchar(50) default NULL,
    `city` varchar(20) NOT NULL,
    `state` varchar(20) default NULL, -- no value when do implement of insert
    `zip` varchar(10) NOT NULL,
    `primary` tinyint(1) NOT NULL,
    primary key (`id`),
    `person_id` int NOT NULL,
    CONSTRAINT address_person_composition
		foreign key (`person_id`)
			references `person` (`id`)
				on delete cascade
);

CREATE TABLE `phone` (
	`id` int NOT NULL auto_increment,
    `phone` varchar(15) NOT NULL,
    `primary` tinyint(1) NOT NULL,
    primary key (`id`),
    `person_id` int NOT NULL,
    CONSTRAINT phone_person_composition
		foreign key (`person_id`)
			references `person`(`id`)
				on delete cascade
);

-- Portable Enums for role
CREATE TABLE `role` (
	`id` varchar(20) NOT NULL, -- id == name
    primary key (`id`)
);

INSERT INTO `role`(`id`) 
VALUES('owner'),
	  ('admin'),
      ('writer'),
      ('editor'),
      ('reviewer');

CREATE TABLE `page_role` (
	`id` int NOT NULL auto_increment,
    `role` varchar(10) NOT NULL,
    `developer_id` int NOT NULL,
    `page_id` int NOT NULL,
    primary key (`id`),
    CONSTRAINT page_role_enumerates
		foreign key (`role`)
			references `role`(`id`),
	CONSTRAINT pagerole_developer_association
		foreign key (`developer_id`)
			references `developer`(`person_id`),
	CONSTRAINT pagerole_page_association
		foreign key (`page_id`)
			references `page`(`id`)
);

CREATE TABLE `website_role` (
	`id` int NOT NULL auto_increment,
    `role` varchar(10) NOT NULL,
    `developer_id` int NOT NULL,
    `website_id` int NOT NULL,
    primary key (`id`),
	CONSTRAINT website_role_enumerates
		foreign key (`role`)
			references `role`(`id`),
	CONSTRAINT webrole_developer_association
		foreign key (`developer_id`)
			references `developer`(`person_id`),
	CONSTRAINT webrole_page_association
		foreign key (`website_id`)
			references `website`(`id`)
);

-- Portable Enums for priviledge
CREATE TABLE `priviledge` (
	`id` varchar(20) NOT NULL, -- id == name
    primary key (`id`)
);
INSERT INTO `priviledge`(`id`) 
VALUES('create'),
	  ('read'),
      ('update'),
      ('delete');

CREATE TABLE `page_priviledge` (
	`id` int NOT NULL auto_increment,
    `priviledge` varchar(10) NOT NULL,
    `developer_id` int NOT NULL,
    `page_id` int NOT NULL,
	primary key (`id`),
    CONSTRAINT page_piviledge_enumerates
		foreign key (`priviledge`)
			references `priviledge`(`id`),
	CONSTRAINT pagepriviledge_developer_association
		foreign key (`developer_id`)
			references `developer`(`person_id`),
	CONSTRAINT pagepriviledge_page_association
		foreign key (`page_id`)
			references `page`(`id`)
);

CREATE TABLE `website_priviledge` (
	`id` int NOT NULL auto_increment,
    `priviledge` varchar(10) NOT NULL,
	`developer_id` int NOT NULL,
    `website_id` int NOT NULL,
    primary key (`id`),
	CONSTRAINT website_priviledge_enumerates
		foreign key (`priviledge`)
			references `priviledge`(`id`),
	CONSTRAINT webpriviledge_developer_association
		foreign key (`developer_id`)
			references `developer`(`person_id`),
	CONSTRAINT webpriviledge_page_association
		foreign key (`website_id`)
			references `website`(`id`)
);
