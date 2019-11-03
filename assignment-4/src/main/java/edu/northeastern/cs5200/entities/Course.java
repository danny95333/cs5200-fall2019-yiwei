package edu.northeastern.cs5200.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String label;
    // relation
    @ManyToOne
    private Faculty author;
	public Faculty getAuthor() {
		return author;
	}
	public void setAuthor(Faculty author) {
		this.author = author;
		if(!author.getAuthoredCourses().contains(this)) {
			author.getAuthoredCourses().add(this);
		}
	}
	
	
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Section> sections;
	public List<Section> getSections() {
		if(sections == null) {
			sections = new ArrayList<>();
		}
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public void addSection(Section section) {
		if (!sections.contains(section)) {
			sections.add(section);
		}
		if(section.getCourse() != this) {
			section.setCourse(this);
		}
	}
	
	
	// instance
	public Course() {}

    public Course(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
}
