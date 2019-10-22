package edu.northeastern.cs5200.model;
import java.util.Date;
import java.util.Collection;
public class Website {
	private int id;
    private String name;
    private String description;
    private Date created;
    private Date updated;
    private int visits;
    
    private Collection<Page> pages;
    // instance with requirement
    public Website(int id, String name, String description, Date created, Date updated, int visits) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.visits = visits;
    }
    //id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //created
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    //updated
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    //visits
    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }
    //page
    public Collection<Page> getPages() {
        return pages;
    }

    public void setPages(Collection<Page> pages) {
        this.pages = pages;
    }

    public void addPage(Page page) {
        this.pages.add(page);
    }

    public void removePage(Page page) {
        this.pages.remove(page);
    }
}
