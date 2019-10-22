package edu.northeastern.cs5200.model;

public class Widget {
	private int id;
    private String name;
    private int width;
    private int height;
    private String css_class;
    private String css_style;
    private String text;
    private int order;
    // size/html/url/shareble.. in a new sub enumerate
    public Widget() {
		super();
	}
    // page_id
    private Page page;
    // instance with requirement
    public Widget(int id, String name, int width, int height, String css_class, String css_style, String text, int order) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.css_class = css_class;
        this.css_style = css_style;
        this.text = text;
        this.order = order;
    }
    // id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // width
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    // height
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    // css_class
    public String getCssClass() {
        return css_class;
    }

    public void setCssClass(String css_class) {
        this.css_class = css_class;
    }
    // css_style
    public String getCssStyle() {
        return css_style;
    }

    public void setCssStyle(String css_style) {
        this.css_style = css_style;
    }
    // text
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    // order
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    // page
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
