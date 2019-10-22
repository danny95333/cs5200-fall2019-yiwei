DELIMITER $$
create procedure getUnansweredQuestions() 
BEGIN
	SELECT question.text, COUNT(answer.id) AS AnswerCount FROM question 
	JOIN user ON user.id = question.user_id JOIN answer ON answer.user_id = user.id 
	WHERE answer.correctAnswer = false GROUP BY question.module
	HAVING MAX(AnswerCount);
END
DELIMITER