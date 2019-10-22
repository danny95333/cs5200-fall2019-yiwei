package edu.northeastern.cs5200.model;
public enum Role {
    OWNER(1), 
    ADMIN(2), 
    WRITER(3), 
    EDITOR(4), 
    REVIEWER(5);
    private int role_id;
    Role(int Role_id) {
        this.role_id = Role_id;

    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int Role_id) {
        this.role_id = Role_id;
    }

    public static Role fromInt(int Role_id) {
        switch (Role_id) {
            case 1:
                return Role.OWNER;
            case 2:
                return Role.ADMIN;
            case 3:
                return Role.WRITER;
            case 4:
                return Role.EDITOR;
            case 5:
                return Role.REVIEWER;
            default:
                return null;
        }
    }

}