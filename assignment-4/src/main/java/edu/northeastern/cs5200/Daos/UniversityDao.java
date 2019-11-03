package edu.northeastern.cs5200.Daos;

import java.util.ArrayList;
import java.util.List;
import edu.northeastern.cs5200.entities.*;
import edu.northeastern.cs5200.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

public class UniversityDao {
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
	
	//void truncateDatabase() - removes all the data from the database
	public void truncateDatabase() {
		this.enrollmentRepo.deleteAll();
		this.sectionRepo.deleteAll();
		this.courseRepo.deleteAll();
		this.personRepo.deleteAll(); // don't need stu and fac
	}
	//createFaculty(Faculty faculty)
	public Faculty createFaculty(Faculty faculty) {
		return this.facultyRepo.save(faculty);
	}
	//createStudent(Student student)
	public Student createStudent(Student student) {
		return this.studentRepo.save(student);
	}
	//createCourse(Course course)
	public Course createCourse(Course course) {
		return this.courseRepo.save(course);
	}
	//createSection(Section section)
	public Section createSection(Section section) {
		return this.sectionRepo.save(section);
	}
	//addSectionToCourse(Section section, Course course)??? should be Section not Course
	public Section addSectionToCourse(Section section, Course course) {
		section.setCourse(course);
		sectionRepo.save(section);
		return section;
	}
	// setAuthorForCourse(Faculty faculty, Course course)
	public Course setAuthorForCourse(Faculty faculty, Course course) {
		course.setAuthor(faculty);
		courseRepo.save(course);
		return course;
	}
	//enrollStudentInSection(Student student, Section section)
	public Boolean enrollStudentInSection(Student student, Section section) {
		if (section.getSeats() == 0) {
			return false;
		}
		else {
			// create enrollment
			Enrollment enroll = new Enrollment(student, section);
			student.addEnrollments(enroll);
			section.addEnrollments(enroll);
			int numOfSeats = section.getSeats();
			section.setSeats(numOfSeats-1);
			// studentRepo.save(student);
			enrollmentRepo.save(enroll);
            sectionRepo.save(section);
            return true;
		}
	}
	//List<Person> findAllUsers()
	public List<Person> findAllUsers(){
		List<Person> tmp = (List<Person>) personRepo.findAll();
		return tmp;
	}
	// List<Faculty> findAllFaculty()
	public List<Faculty> findAllFaculty(){
		List<Faculty> tmp = (List<Faculty>) facultyRepo.findAll();
		return tmp;
	}
	// List<Students> findAllStudents()
	public List<Student> findAllStudents(){
		List<Student>tmp = (List<Student>) studentRepo.findAll();
		return tmp;
	}
	//List<Course> findAllCourses()
	public List<Course> findAllCourses(){
		List<Course> tmp = (List<Course>) courseRepo.findAll();
		return tmp;
	}
	//List<Section> findAllSections()
	public List<Section> findAllSections(){
		List<Section> tmp = (List<Section>) sectionRepo.findAll();
		return tmp;
	}
	//List<Course> findCoursesForAuthor(Faculty faculty)
	public List<Course> findCoursesForAuthor(Faculty faculty){
		List<Course> tmp = faculty.getAuthoredCourses();
		return tmp;
	}
	// List<Section> findSectionForCourse(Course course)
	public List<Section> findSectionForCourse(Course course){
		List<Section> tmp = course.getSections();
		return tmp;
	}
	// List<Student> findStudentsInSection(Section section)
	public List<Student> findStudentsInSection(Section section){
		List<Student> students = new ArrayList<>();
		List<Enrollment> enrollments = section.getEnrollments();
		for (Enrollment enrollment : enrollments) {
            students.add(enrollment.getStudent());
        }
		return students;
	}
	// List<Section> findSectionsForStudent(Student student)
	public List<Section> findSectionsForStudent(Student student){
		List<Section> sections = new ArrayList<>();
		List<Enrollment> enrollments = student.getEnrollments();
		for (Enrollment enrollment : enrollments) {
            sections.add(enrollment.getSection());
        }
		return sections;
	}
	// end	
	// help function for test
	public List<Course> findCourseCorrespondingAuthor(Faculty faculty){
		return faculty.getAuthoredCourses();
	}
	public List<Section> findSectionCorrespondingCourse(Course course){
		return course.getSections();
	}
}
