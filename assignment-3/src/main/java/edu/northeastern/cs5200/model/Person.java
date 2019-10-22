package edu.northeastern.cs5200.model;

import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;
public class Person {
	private int id;
    private String username;
    private String password;
	private String first_name;
    private String last_name;
    private String email;
    private Date dob;
    // for 3rd of instance of developer
    private Collection<Phone> phone = new ArrayList<>();
    private Collection<Address> addresse = new ArrayList<>();
    
    public Person(int id, String first_name, String last_name,
            String username, String password, String email, Date dob) {
	  this.id = id;
	  this.first_name = first_name;
	  this.last_name = last_name;
	  this.username = username;
	  this.password = password;
	  this.email = email;
	  this.dob = dob;
    }
    // create for some of the child instance
    public Person(int id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    // create for developer 3rd instance
    
    public Person (int id, String first_name, String last_name, String username, String password, String email, Date dob, Collection<Phone> phone, Collection<Address> addresse) {
    	this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.addresse = addresse;
    }
	// id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // first name
    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }
    // last name
    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }
    // username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    // email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    // address&phone for developer instance
    public Collection<Phone> getPhones() {
        return phone;
    }

    public void setPhones(Collection<Phone> phone) {
        this.phone = phone;
    }

    public void addPhone(Phone phone) {
        this.phone.add(phone);
    }

    public void removePhone(Phone phone) {
        this.phone.remove(phone);
    }

    public Collection<Address> getAddresses() {
        return addresse;
    }

    public void setAddresses(Collection<Address> addresse) {
        this.addresse = addresse;
    }

    public void addAddress(Address address) {
        this.addresse.add(address);
    }

    public void removeAddress(Address address) {
        this.addresse.remove(address);
    }
    
    
}
