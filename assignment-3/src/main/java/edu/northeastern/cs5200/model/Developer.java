package edu.northeastern.cs5200.model;
import java.util.Collection;
import java.util.Date;
public class Developer extends Person{
	private String developer_key;
	// developer id, first name, last name, developer key
	// 1 instance
	public Developer(int id, String first_name, String last_name, String developer_key) {
        super(id, first_name, last_name);
        this.developer_key = developer_key;
    }
	// 2 instance
	public Developer(int id, String first_name, String last_name, String username, String password,
            String email, Date dob, String developer_key) {
			super(id, first_name, last_name, username, password, email, dob);
			this.developer_key = developer_key;
	}
	//
	public Developer(int id, String firstName, String lastName, String username, String password,
            String email, Date dob, Collection<Phone> phones, Collection<Address> addresses,String developerKey) {
			super(id, firstName, lastName, username, password, email, dob, phones, addresses);
			this.developer_key = developerKey;
}
	public String getDeveloperKey() {
        return developer_key;
    }

    public void setDeveloperKey(String developer_key) {
        this.developer_key = developer_key;
    }
    
	
}
