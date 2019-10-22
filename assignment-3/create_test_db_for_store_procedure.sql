DROP TABLE IF EXISTS `answer`;
DROP TABLE IF EXISTS `question`;
DROP TABLE IF EXISTS `Module`;

CREATE TABLE `Module`(
`id` varchar(20) NOT NULL DEFAULT '',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `Module` (`id`) VALUES ('Project1');
INSERT INTO `Module` (`id`) VALUES ('Project2');
INSERT INTO `Module` (`id`) VALUES ('Assignment1');
INSERT INTO `Module` (`id`) VALUES ('Assignment2');
INSERT INTO `Module` (`id`) VALUES ('Quiz1');
INSERT INTO `Module` (`id`) VALUES ('Quiz2');
INSERT INTO `Module` (`id`) VALUES ('Exam1');
INSERT INTO `Module` (`id`) VALUES ('Exam2');
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(50) NOT NULL,
	`user_id` int NOT NULL,
  `posted_on` date DEFAULT NULL,
	`length` int DEFAULT 0,
  `views` int DEFAULT 0,
	`endorsed_by_instructor` tinyint(1) DEFAULT 0,
  `module` varchar(20) NOT NULL,
	PRIMARY KEY (`id`),
  CONSTRAINT `question_asked_by_user`
		FOREIGN KEY (`user_id`)
		REFERENCES `User` (`person_id`),
	CONSTRAINT `module_enum`
		FOREIGN KEY (`module`)
		REFERENCES `Module`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(20) NOT NULL,
	`user_id` int NOT NULL,
	`posted_on` date DEFAULT NULL,
	`correctAnswer` tinyint(1) DEFAULT 0,
	`upVotes` int DEFAULT 0,
  `downVotes` int DEFAULT 0,
	`question_id` int NOT NULL,
  PRIMARY KEY (`id`),
	CONSTRAINT answer_user_id_user
		FOREIGN KEY (`user_id`)
		REFERENCES `User`(`person_id`),
	CONSTRAINT answer_of_question
		FOREIGN KEY (question_id)
		REFERENCES question(`id`)
		ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;