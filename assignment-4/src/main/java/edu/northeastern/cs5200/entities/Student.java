package edu.northeastern.cs5200.entities;

import javax.persistence.*;
import java.util.List;
@Entity
public class Student extends Person {
    private int gradYear;
    private long scholarship;
    
    //relation
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<Enrollment> enrollments ;
    public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	public void addEnrollments(Enrollment enrollment) {
		this.enrollments.add(enrollment);
        if (enrollment.getStudent() != this) {
            enrollment.setStudent(this);
        }
	}
	// instance
	public Student() {}

    public Student(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    public Student(String username, String password, String firstName, String lastName, int gradYear, long scholarship) {
        super(username, password, firstName, lastName);
        this.gradYear = gradYear;
        this.scholarship = scholarship;
    }
    
    public int getGradYear() {
        return gradYear;
    }

    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }

    public long getScholarship() {
        return scholarship;
    }

    public void setScholarship(long scholarship) {
        this.scholarship = scholarship;
    }
}
