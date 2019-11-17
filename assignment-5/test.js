require('./db')();
var universityDao = require('./daos/university.dao.server');
const assert = require('assert');

// uses DAO’s truncateDatabase() function to remove all the data from the database
var truncateDatabase = function () {
    universityDao.truncateDatabase();
    universityDao.findAllStudents()
        .then(studentsNum => assert(studentsNum.length == 0))
        .catch(err => console.log(err.message));
};
// populateDatabase()  - uses DAO’s populateDatabase() function to populate the database with test data
var populateDatabase = function () {
    universityDao.populateDatabase().catch(err => console.log(err.message));
};
// testStudentsInitialCount()  - uses DAO to validate there are 2 students initially
var testStudentsInitialCount = function () {
	universityDao.findAllStudents()
        .then(studentsNum => assert(studentsNum.length == 2))
        .catch(err => console.log(err.message));
};
// testQuestionsInitialCount()  - uses DAO to validate there are 4 questions initially
var testQuestionsInitialCount = function () {
	universityDao.findAllQuestions()
        .then(questionsNum => assert(questionsNum.length == 4))
        .catch(err => console.log(err.message));
};
// testAnswersInitialCount()  - uses DAO to validate there are 8 answers initially
var testAnswersInitialCount = function () {
	universityDao.findAllAnswers()
        .then(answersNum => assert(answersNum.length == 8))
        .catch(err => console.log(err.message));
};
// testDeleteAnswer()  - uses DAO to remove Bob’s answer for the multiple choice question “What
// does ORM stand for?” and validates the total number of amswers is 7 and Bob’s total number of answers is 3
var testDeleteAnswer = function () {
	universityDao.deleteAnswer(890);
	universityDao.findAllAnswers()
        .then(answersNum => assert(answersNum.length == 7))
        .catch(err => console.log(err.message));
    universityDao.countAnswersByStudent(234);
};
// testDeleteQuestion()  - uses DAO to remove true false question “Is the following schema valid?” and
// validates the total number of questions is 3
var testDeleteQuestion = function () {
	universityDao.deleteQuestion(321);
	universityDao.findAllQuestions()
		.then(questionsNum => assert(questionsNum.length == 3))
		.catch(err => console.log(err.message));
};
// testDeleteStudent()  - uses DAO to remove student Bob and validates the total number of students is 1
var testDeleteStudent = function () {
	universityDao.deleteStudent(234);
universityDao.findAllQuestions()
		.then(studentsNum => assert(studentsNum.length == 1))
		.catch(err => console.log(err.message));
};
