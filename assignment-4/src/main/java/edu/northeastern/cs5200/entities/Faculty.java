package edu.northeastern.cs5200.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Faculty extends Person{
	// implement model inheritance
	private String office;
	private Boolean tenured;
	
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	private List<Course> authoredCourses;
	// inheritance
	public List<Course> getAuthoredCourses() {
		if(authoredCourses == null) {
			authoredCourses = new ArrayList<>();
		}
		return authoredCourses;
	}
	public void setAuthoredCourses(List<Course> authoredCourses) {
		this.authoredCourses = authoredCourses;
	}
	public void authoredCourse(Course course) {

		authoredCourses.add(course);
		if(course.getAuthor() != this) {
			course.setAuthor(this);
		}
	}
	// instance
	public Faculty() {}

	public Faculty(String username, String password, String firstName, String lastName) {
	        super(username, password, firstName, lastName);
	    }

	public Faculty(String username, String password, String firstName, String lastName, String office, boolean tenured) {
	        super(username, password, firstName, lastName);
	        this.office = office;
	        this.tenured = tenured;
	    }

	
	public String getOffice() {
	        return office;
	    }

	public void setOffice(String office) {
	        this.office = office;
	    }

	public boolean isTenured() {
	        return tenured;
	    }

	public void setTenured(boolean tenured) {
	        this.tenured = tenured;
	    }
	
	
}
