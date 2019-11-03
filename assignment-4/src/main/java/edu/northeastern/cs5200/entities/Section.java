package edu.northeastern.cs5200.entities;

import javax.persistence.*;
import java.util.List;

public class Section {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String title;
	private int seats;
	// relation
	
	@OneToMany(mappedBy = "section", fetch = FetchType.EAGER)
	private List<Enrollment> enrollments;
	public List<Enrollment> getEnrollments() {
        return enrollments;
    }
	public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
	public void addEnrollments(Enrollment enrollment) {
		this.enrollments.add(enrollment);
        if (enrollment.getSection() != this) {
            enrollment.setSection(this);
        }
	}
	
	@ManyToOne()
    private Course course;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
		if(!course.getSections().contains(this)) {
			course.getSections().add(this);
		}
	}
	
	
	// instance
	public Section() {}

    public Section(String title, int seats) {
        this.title = title;
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
