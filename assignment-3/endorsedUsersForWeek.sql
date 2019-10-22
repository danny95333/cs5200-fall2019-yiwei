
DELIMITER $$
create procedure endorsedUsersForWeek(IN start_date date, IN end_date date) 
BEGIN
	SELECT user.id, full_name FROM 
	(SELECT user.id, CONCAT(user.first_name, user.last_name) AS full_name 
	FROM question JOIN user on question.user_id = user.id JOIN answer ON user.id = answer.user_id 
	WHERE start_date < posted_on AND posted_on < end_date ORDER BY COUNT(endorsed_by_instructor) DESC LIMIT 5) 
	ORDER BY user.first_name;
END
DELIMITER