package edu.northeastern.cs5200.daos;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import edu.northeastern.cs5200.model.User;
import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.model.Address;
import edu.northeastern.cs5200.model.Developer;
import edu.northeastern.cs5200.model.Phone;
public class UserDao implements UserImpl {

    private static UserDao instance = null;

    private UserDao() {

    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }
    private PreparedStatement pStatement = null;
    private PreparedStatement pStatement1 = null;
 // create
 	private final String CREATE_PERSON = "INSERT INTO person (id, first_name, lastname, username, password, email, dob) VALUES(?,?,?,?,?,?,?)";
 	private final String CREATE_USER = "INSERT INTO `User` (`person_id`, `user_agreement`) VALUES (?, ?)";
 	// find
 	private final String FIND_ALL_USER = "SELECT * FROM `User`";
 	private final String FIND_USER_BY_ID = "SELECT * FROM Person, User WHERE Person.id = User.person_id";
 	private final String FIND_USER_BY_USERNAME = "SELECT * FROM Person, User WHERE Person.username = ? AND Person.id = User.person_id";
 	private final String FIND_USER_BY_CREDENTIALS = "SELECT * FROM User, Person WHERE User.id = developer.person_id AND password = ? AND username = ?";
 	// update
 	private final String UPDATE_PERSON = "UPDATE Person SET first_name = ?, last_name = ?, username = ?, password = ?, email = ?, dob = ? WHERE person.id = ?";
 	private final String UPDATE_USER = "UPDATE User SET user_agreement=? WHERE person_id=?";
 	// delete
 	private final String DELETE_PERSON = "DELETE FROM Person WHERE id=?";
    private final String DELETE_USER = "DELETE FROM User WHERE person_id=?";
    
    public void createUser(User user) {
        try {
 
            pStatement = Connection.getConnection().prepareStatement(CREATE_PERSON);
            // set parameters for pstatments
            pStatement.setInt(1, user.getId());
            pStatement.setString(2, user.getUsername());
            pStatement.setString(3, user.getPassword());
            pStatement.setString(4, user.getFirstName());
            pStatement.setString(5, user.getLastName());
            pStatement.setString(6, user.getEmail());
            // resolve the java data and sql date error
            java.util.Date dob = user.getDob();
            java.sql.Date dobSql;
            if (dob != null) {
                dobSql = new java.sql.Date(dob.getTime());
            } else 
            	dobSql = null;
            pStatement.setDate(7, dobSql);
            pStatement.executeUpdate();
            
            // set params for pstatement2
            pStatement1 = Connection.getConnection().prepareStatement(CREATE_USER);
            pStatement1.setInt(1, user.getId());
            pStatement1.setInt(2, user.getUserAgreement() ? 1 : 0);
            // execute the 
            pStatement1.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    finally {
			try {
				pStatement.close();
				pStatement1.close();
				Connection.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    public Collection<User> findAllUsers() {
    	Collection<User> users = new ArrayList<>();
    	try {
    		pStatement = Connection.getConnection().prepareStatement(FIND_ALL_USER);
    		ResultSet res = pStatement.executeQuery();
    		while(res.next()) {
    			 int id = res.getInt("person_id");
                 String first_name = res.getString("first_name");
                 String last_name = res.getString("last_name");
                 String userName = res.getString("username");
                 String password = res.getString("password");
                 String email = res.getString("email");
                 boolean user_agreement = res.getInt("user_agreement") == 1;
                 java.sql.Date dob = res.getDate("dob");
                 
                 User user = new User(id, first_name, last_name, userName, password, email, dob, user_agreement); 
                 users.add(user);
    		}
    	}catch(ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pStatement.close();
				Connection.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return users;
	}
 // find developer by Id
    public User findUserById(int userId) {
    	User u = null;
    	try {
    		pStatement = Connection.getConnection().prepareStatement(FIND_USER_BY_ID);
    		pStatement.setInt(1, userId);
			ResultSet res = pStatement.executeQuery();
			if (res.next()) {
				boolean user_agreement = res.getInt("user_agreement") == 1;
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				String username = res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				Date dob = res.getDate("dob");
				int id = res.getInt("person_id");

				u = new User(id, firstName, lastName, username, password, email, dob, user_agreement);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pStatement.close();
				Connection.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	}
    public User findUserByUsername(String username) {
    	User u = null;
    	try {
    		pStatement = Connection.getConnection().prepareStatement(FIND_USER_BY_USERNAME);
    		pStatement.setString(1, username);
			ResultSet res = pStatement.executeQuery();
			if (res.next()) {
				Boolean userAgreement = res.getInt("userAgreement") == 1;
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				String password = res.getString("password");
				String email = res.getString("email");
				Date dob = res.getDate("dob");
				int id = res.getInt("person_id");

				u = new User(id, firstName, lastName, username, password, email, dob, userAgreement);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pStatement.close();
				Connection.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	}
    
 // find by credential
    public User findUserByCredentials(String username, String password) {
    	User u = null;
    	try {
    		pStatement = Connection.getConnection().prepareStatement(FIND_USER_BY_CREDENTIALS);
    		pStatement.setString(1, username);
    		pStatement.setString(2, password);
			ResultSet res = pStatement.executeQuery();
			while (res.next()) {
				Boolean userAgreement = res.getInt("userAgreement") == 1;
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				String email = res.getString("email");
				Date dob = res.getDate("dob");
				int id = res.getInt("person_id");

				u = new User(id, firstName, lastName, username, password, email, dob, userAgreement);
    	}
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pStatement.close();
				Connection.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	}
    public int updateUser(int userId, User user) {
		try {
    		pStatement = Connection.getConnection().prepareStatement(UPDATE_PERSON);
    		// update person first
            pStatement.setString(1, user.getFirstName());
            pStatement.setString(2, user.getLastName());
            pStatement.setString(3, user.getUsername());
            pStatement.setString(4, user.getPassword());
            pStatement.setString(5, user.getEmail());
         // resolve the java data and sql date error
            java.util.Date dob = user.getDob();
            java.sql.Date dobSql;
            if (dob != null) {
                dobSql = new java.sql.Date(dob.getTime());
            } else 
            	dobSql = null;
            pStatement.setDate(6, dobSql);
            pStatement.setInt(7, userId);
            pStatement.executeUpdate();
            // update developer
            pStatement1 = Connection.getConnection().prepareStatement(UPDATE_USER);
            pStatement1.setInt(1, user.getUserAgreement() ? 1 : 0);
            pStatement1.setInt(2, userId);
            int res = pStatement1.executeUpdate();
            return res;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pStatement1.close();
				pStatement.close();
				Connection.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
    public int deleteUser(int userId) {
		try {
			// delete developer first
    		pStatement = Connection.getConnection().prepareStatement(DELETE_USER);
    		pStatement.setInt(1, userId);
    		pStatement.executeUpdate();
    		pStatement1 = Connection.getConnection().prepareStatement(DELETE_PERSON);
    		pStatement1.setInt(1, userId);
    		pStatement1.executeUpdate();

			int res = pStatement1.executeUpdate();
			return res;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pStatement1.close();
				Connection.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

}
    
    
    
    
    
    

