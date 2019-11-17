const mongoose = require('mongoose');
const studentModel = require('/data/models/student.model.server');
const questionModel = require('/data/models/question.model.server');
const answerModel = require('/data/models/answer.model.server');
// start
//removes all the data from the database. Note that you might need to remove documents in a particular order
truncateDatabase = () =>
    studentModel.remove()
    	.then(() => questionModel.remove())
    	.then(() => answerModel.remove());
//populateDatabase()  - populates the database with test data as described in a later section
populateDatabase = () => {
	// student
	studentModel.create({
        _id: 123,
        firstName: "Alice",
        lastName: "Wonderland",
        username: "alice",
        password: "alice",
        gradYear: 2020,
        scholarship: 15000});
    studentModel.create({
        _id: 234,
        firstName: "Bob",
        lastName: "Hope",
        username: "bob",
        password: "bob",
        gradYear: 2021,
        scholarship: 12000});
    // Questions
    questionModel.create({
        _id: 321,
        question : "Is the following schema valid?",
        points : 10,
        questionType : "TRUE_FALSE",
        trueFalse : {isTrue: false}});
    questionModel.create({
        _id: 432,
        question : "DAO stands for Dynamic Access Object.",
        points : 10,
        questionType : "TRUE_FALSE",
        trueFalse : {isTrue: false}});
    questionModel.create({
        _id: 543,
        question : "What does JPA stand for?",
        points : 10,
        questionType : "MULTIPLE_CHOICE",
        multipleChoice : {
            choices: "Java Persistence API,Java Persisted Application,JavaScript Persistence API,JSON Persistent Associations",
            correct: 1}});
    questionModel.create({
        _id: 654,
        question : "What does ORM stand for?",
        points : 10,
        questionType : "MULTIPLE_CHOICE",
        multipleChoice : {
            choices: "Object Relational Model,Object Relative Markup,Object Reflexive Model,Object Relational Mapping",
            correct: 4}});
    // Answers
    // alice, is the 
    answerModel.create({
        _id: 123,
        trueFalseAnswer: true,
        student: 123,
        question: 321});
    // alice, dao
    answerModel.create({
        _id: 234,
        trueFalseAnswer: false,
        student: 123,
        question: 432});
    // alice jpa
    answerModel.create({
        _id: 345,
        multipleChoiceAnswer: 1,
        student: 123,
        question: 543});
    // alice orm
    answerModel.create({
        _id: 456,
        multipleChoiceAnswer: 2,
        student: 123,
        question: 654});
    // bob, is the 
    answerModel.create({
        _id: 567,
        trueFalseAnswer: false,
        student: 234,
        question: 321});
    // bob, dao
    answerModel.create({
        _id: 678,
        trueFalseAnswer: true,
        student: 234,
        question: 432});
    // bob jpa
    answerModel.create({
        _id: 789,
        multipleChoiceAnswer: 3,
        student: 234,
        question: 543});
    // bob orm
    answerModel.create({
        _id: 890,
        multipleChoiceAnswer: 4,
        student: 234,
        question: 654});
    // finish populate
};
// createStudent(student)  - inserts a student document
createStudent = (student) => studentModel.create(student);
// deleteStudent(id)  - removes student whose ID is id. Delete does not cascade
// make id unique for student(not question id), we can not use 'id'
deleteStudent = (studentId) => studentModel.deleteOne({_id: studentId}).then(() => answerModel.remove({student: studentId}));
// createQuestion(question)  - inserts a question document
createQuestion = (question) => questionModel.create(question);
//deleteQuestion(id)  - removes question whose ID is id. Delete does not cascade
deleteQuestion = (questionId) => questionModel.remove({_id: questionId}).then(() => answerModel.remove({question: questionId}));
// answerQuestion(studentId, questionId, answer)  - inserts an answer by student student for question question
answerQuestion = (studentId, questionId, answer) => {
    answer.student = studentId;
    answer.question = questionId;
    answerModel.create(answer)};
// deleteAnswer(id)  - removes answer whose ID is id
deleteAnswer = (answerId) => answerModel.remove({_id: answerId});

// find method
// findAllStudents()  - retrieves all students
findAllStudents = () => studentModel.find();
// findStudentById(id)  - retrieves a single student document whose ID is id
findStudentById = (studentId) => studentModel.findById(studentId);
// findAllQuestions()  - retrieves all questions
findAllQuestions = () => questionModel.find();
// findQuestionById(id)  - retrieves a single question document whose ID is id
findQuestionById = (questionId) => questionModel.findById(questionId);
// findAllAnswers()  - retrieves all the answers
findAllAnswers = () => answerModel.find();
// findAnswerById(id)  - retrieves a single answer document whose ID is id
findAnswerById = (answerId) => answerModel.findById(answerId);
// findAnswersByStudent(studentId)  - retrieves all the answers for a student whose ID is studentId
findAnswersByStudent = (studentId) => answerModel.find({student: studentId});
// findAnswersByQuestion(questionId)  - retrieves all the answers for a question whose ID is questionId
findAnswersByQuestion = (questionId) => answerModel.find({question: questionId});

// help func for test(delete answer)
countAnswersByStudent = (studentId) => answerModel.countDocuments({student: studentId}, (count) => console.log(count));

module.exports = {truncateDatabase,populateDatabase,createStudent,deleteStudent,createQuestion,deleteQuestion,answerQuestion,deleteAnswer,
    findAllStudents,findStudentById,findAllQuestions,findQuestionById,findAllAnswers,findAnswerById,findAnswersByStudent,findAnswersByQuestion};
