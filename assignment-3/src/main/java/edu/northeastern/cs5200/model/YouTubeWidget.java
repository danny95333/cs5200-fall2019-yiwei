package edu.northeastern.cs5200.model;

public class YouTubeWidget extends Widget{
	private String url;
    private boolean shareble;
    private boolean expandable;
    
    public YouTubeWidget(int id, String name, int width, int height, String css_class, String css_style, String text, int order, String url) {
        super(id, name, width, height, css_class, css_style, text, order);
        this.url = url;
    }

    public YouTubeWidget(int id, String name, int width, int height, String css_class, String css_style, String text, int order, String url, Boolean shareble, Boolean expandable) {
        super(id, name, width, height, css_class, css_style, text, order);
        this.url = url;
        this.shareble = shareble;
        this.expandable = expandable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getShareble() {
        return shareble;
    }

    public void setShareble(Boolean Shareble) {
    	shareble = Shareble;
    }

    public Boolean getExpandable() {
        return expandable;
    }

    public void setExpandable(Boolean Expandable) {
    	expandable = Expandable;
    }

}
