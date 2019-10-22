package edu.northeastern.cs5200.model;

public class Phone {
	private int id;
	private String phone;
    private boolean primary;
    private Person person;

    public Phone() {
    }

    public Phone(String phone, boolean primary, Person person) {
        this.phone = phone;
        this.primary = primary;
        this.person = person;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getPrimary() {
        return this.primary;
    }

    public void setPrimary(boolean Primary) {
    	primary = Primary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
}
