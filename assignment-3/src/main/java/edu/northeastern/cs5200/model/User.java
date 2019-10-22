package edu.northeastern.cs5200.model;
import java.util.Date;
public class User extends Person {
	private Boolean user_agreement;
		// when user_agreement need to pass in instance
	    public User(int id, String first_name, String last_name, String username, String password, String email, Date dob, Boolean user_agreement) {
	        super(id, first_name, last_name, username, password, email, dob);
	        this.user_agreement = user_agreement;
	    }
	    // instance with requirement
	    public User(int id, String first_name, String last_name) {
	        super(id, first_name, last_name);
	        this.user_agreement = false;
	    }
	    //
	    public User(int id, String first_name, String last_name, String username, String password, String email, Date dob) {
	        super(id, first_name, last_name, username, password, email, dob);
	        this.user_agreement = false;
	    }
	    public Boolean getUserAgreement() {
	        return user_agreement;
	    }

	    public void setUserAgreement(Boolean user_agreement) {
	        this.user_agreement = user_agreement;
	    }
	}