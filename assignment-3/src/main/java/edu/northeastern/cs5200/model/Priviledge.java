package edu.northeastern.cs5200.model;

public enum Priviledge {
    CREATE("create"),
    DELETE("delete"),
    READ("read"),
    UPDATE("update");
    private String priviledge;
	Priviledge(String priviledge) {
        this.priviledge = priviledge;
    }
    public String getPriviledge() {
        return priviledge;
    }
    public void setPrivilege(String priviledge) {
        this.priviledge = priviledge;
    }
    public static Priviledge fromString(String priviledge) {
        switch (priviledge) {

            case "create":
                return Priviledge.CREATE;
            case "read":
                return Priviledge.READ;
            case "update":
                return Priviledge.UPDATE;
            case "delete":
                return Priviledge.DELETE;
            default:
                return null;
        }
    }
    
    
}