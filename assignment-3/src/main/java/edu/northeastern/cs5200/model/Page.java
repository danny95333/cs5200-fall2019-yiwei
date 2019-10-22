package edu.northeastern.cs5200.model;
import java.util.Date;
import java.util.Collection;
public class Page {
	private int id;
    private String title;
    private String description;
    private Date created;
    private Date updated;
    private int views;
    // website id
    private Website website;
    
    private Collection<Widget> widgets;
    public Page(int id, String title, String description, Date created, Date updated, int views) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.views = views;
    }
    // id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    // description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // created
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    // updated
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    // views
    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
    // website_id
    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }
    // widget
    public Collection<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(Collection<Widget> widgets) {
        this.widgets = widgets;
    }

    public void addWidgets(Widget widget) {
        this.widgets.add(widget);
    }

    public void removeWidgets(Widget widget) {
        this.widgets.remove(widget);
    }
}
