package edu.northeastern.cs5200;
import edu.northeastern.cs5200.Daos.*;
import edu.northeastern.cs5200.entities.*;
import edu.northeastern.cs5200.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class InsertTest {
	
	@Autowired
    UniversityDao uniDao;
	@Autowired
	CourseRepo courseRepo;
	@Autowired
	EnrollmentRepo enrollmentRepo;
	@Autowired
	FacultyRepo facultyRepo;
	@Autowired
	PersonRepo personRepo;
	@Autowired
	SectionRepo sectionRepo;
	@Autowired
	StudentRepo studentRepo;
	// Empty the database
	@Test
	public void testTruncate() {
		uniDao.truncateDatabase();
	}
	// Creates faculties, all usernames are the lowercase, password are “password”
	@Test
	public void createFaculty() {
		Faculty alan = new Faculty("alan", "password", "Alan", "Turin", "123A", true);
        Faculty ada = new Faculty("ada", "password", "Ada", "Lovelace", "123B", true);
        uniDao.createFaculty(alan);
        uniDao.createFaculty(ada);
	}
	//Creates students
	@Test
	public void createStudents() {
		Student alice = new Student("alice","password","Alice","Wonderland",2020,12000);
		Student bob = new Student("bob","password","Bob","Hope",2021,23000);
		Student charlie = new Student("charlie","password","Charlie","Brown",2019,21000);
		Student dan = new Student("dan","password","Dan","Craig",2019,0);
		Student edward = new Student("edward","password","Edward","Scissorhands",2022,11000);
		Student frank = new Student("frank","password","Frank","Herbert",2018,0);
		Student gregory = new Student("gregory","password","Gregory","Peck",2023,10000);
		uniDao.createStudent(alice);
		uniDao.createStudent(bob);
		uniDao.createStudent(charlie);
		uniDao.createStudent(dan);
		uniDao.createStudent(edward);
		uniDao.createStudent(frank);
		uniDao.createStudent(gregory);
	}
	//Create courses
	@Test
	public void createCourses() {
		Course c1 = new Course("CS1234");
		Course c2 = new Course("CS2345");
		Course c3 = new Course("CS3456");
		Course c4 = new Course("CS4567");
		uniDao.createCourse(c1);
		uniDao.createCourse(c2);
		uniDao.createCourse(c3);
		uniDao.createCourse(c4);
		Faculty alan = facultyRepo.findPersonByUsername("alan");
		Faculty ada = facultyRepo.findPersonByUsername("ada");
        uniDao.setAuthorForCourse(alan, c1);
        uniDao.setAuthorForCourse(alan, c2);
        uniDao.setAuthorForCourse(ada, c3);
        uniDao.setAuthorForCourse(ada, c4);
	}
	// create sections
	@Test
	public void createSections() {
		Course c1 = courseRepo.findCourseByLabel("CS1234");
        Course c2 = courseRepo.findCourseByLabel("CS2345");
        Course c3 = courseRepo.findCourseByLabel("CS3456");
        Section s1 = new Section("SEC4321", 50);
        Section s2 = new Section("SEC5432", 50);
        Section s3 = new Section("SEC6543", 50);
        Section s4 = new Section("SEC7654", 50);
		uniDao.createSection(s1);
		uniDao.createSection(s2);
		uniDao.createSection(s3);
		uniDao.createSection(s4);
		// add course to sec
		uniDao.addSectionToCourse(s1, c1);
		uniDao.addSectionToCourse(s2, c1);
		uniDao.addSectionToCourse(s3, c2);
		uniDao.addSectionToCourse(s4, c3);
	}
	
	//Enroll students in sections
	@Test
	public void createEnrollment() {
		Student alice = studentRepo.findPersonByUsername("alice");
		Student bob = studentRepo.findPersonByUsername("bob");
		Student charlie = studentRepo.findPersonByUsername("charlie");
		Section s1 = sectionRepo.findSectionByTitle("SEC4321");
		Section s2 = sectionRepo.findSectionByTitle("SEC5432");
		Section s3 = sectionRepo.findSectionByTitle("SEC6543");
		uniDao.enrollStudentInSection(alice, s1);
		uniDao.enrollStudentInSection(alice, s2);
		uniDao.enrollStudentInSection(bob, s2);
		uniDao.enrollStudentInSection(charlie, s3);
	}	
}
