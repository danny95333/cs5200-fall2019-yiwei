package edu.northeastern.cs5200;
import edu.northeastern.cs5200.Daos.*;
import edu.northeastern.cs5200.entities.*;
import edu.northeastern.cs5200.repositories.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountTest {
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
	// Validates uses - write a test that validates the total number of users
	@Test
	public void countAllUsers() {
		List<Person> users = uniDao.findAllUsers();
		System.out.println(users.size());
	}
	// Validates faculty - write a test that validates the total number of faculty
	@Test
	public void countAllfaculties() {
		List<Faculty> faculties = uniDao.findAllFaculty();
		System.out.println(faculties.size());
	}
	// Validates students - write a test that validates the total number of students
	@Test
	public void countAllStudents() {
		List<Student> students = uniDao.findAllStudents();
		System.out.println(students.size());
	}
	// Validates courses - write a test that validates the total number of courses
	@Test
	public void countAllCourses() {
		List<Course> courses = uniDao.findAllCourses();
		System.out.println(courses.size());
	}
	// Validates sections - write a test that validates the total number of sections
	@Test
	public void countAllSections() {
		List<Section> sections = uniDao.findAllSections();
		System.out.println(sections.size());
	}
	// Validates Course authorship - write a test that validates the total number of courses authored by each faculty
	@Test
	public void countCorrespondingCourses() {
		List<Faculty> faculties = uniDao.findAllFaculty();
		for (Faculty fac:faculties) {
			List<Course> courses = uniDao.findCourseCorrespondingAuthor(fac);
			System.out.print(fac.getUsername() + "'s course number is: " + courses.size());
		}
	}
	//Validates Section per Course - write a test that validates the total number of sections per each course
	@Test
	public void countSectionsPerCourse() {
		List<Course> courses = uniDao.findAllCourses();
		for (Course c:courses) {
			List<Section> sections = uniDao.findSectionCorrespondingCourse(c);
			System.out.print(c.getLabel() + "'s section number is: " + sections.size());
		}
	}
	// Validates Section enrollments - write a test that validates the total number of students in each section
	@Test
	public void countStudentsPerSection() {
		List<Section> sections = uniDao.findAllSections();
		for (Section sec:sections) {
			List<Student> students = uniDao.findStudentsInSection(sec);
			System.out.print(sec.getTitle() + "'s students number is: " + students.size());
		}
	}
	// Validates student enrollments - write a test that validates the total number of sections for each student
	public void countSectionForEachStudent() {
		List<Student> students = uniDao.findAllStudents();
		for (Student sdu:students) {
			List<Section> sections = uniDao.findSectionsForStudent(sdu);
			System.out.print(sdu.getFirstName() + " enrolled in sections' number is: " + sections.size());
		}
	}
	// Validates Section seats - write a test that validates the number of section seats
	@Test
	public void countSeatsPerSection() {
		List<Section> sections = uniDao.findAllSections();
		for (Section sec:sections) {
			int numOfSeats = sec.getSeats();
			System.out.print(sec.getTitle() + "'s seats number is: " + numOfSeats);
		}
	}
	
	
	
	
	
	
}
