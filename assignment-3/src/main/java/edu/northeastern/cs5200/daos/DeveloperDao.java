package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.model.Developer;
import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;
import edu.northeastern.cs5200.Connection;
public class DeveloperDao implements DeveloperImpl{
	
	private static DeveloperDao instance = null;
	private DeveloperDao() {
	}

	public static DeveloperDao getInstance() {
		if (instance == null) {
			instance = new DeveloperDao();
		}
		return instance;
	}
	private PreparedStatement pStatement = null;
    private PreparedStatement pStatement1 = null;
    
    // create
	private final String CREATE_PERSON = "INSERT INTO person (id, first_name, lastname, username, password, email, dob) VALUES(?,?,?,?,?,?,?)";
	private final String CREATE_DEVELOPER = "INSERT INTO developer (id, developer_key) VALUES(?,?)";
	// find
	private final String FIND_ALL_DEVELOPERS = "SELECT * FROM `Developer`";
	private final String FIND_DEVELOPER_BY_ID = "SELECT * FROM Person, Developer WHERE Person.id = Developer.person_id";
	private final String FIND_DEVELOPER_BY_USERNAME = "SELECT * FROM Person, Developer WHERE Person.username = ? AND Person.id = Developer.person_id";
	private final String FIND_DEVELOPER_BY_CREDENTIALS = "SELECT * FROM Developer, Person WHERE Person.id = developer.person_id AND password = ? AND username = ?";
	// update
	private final String UPDATE_PERSON = "UPDATE Person SET first_name = ?, last_name = ?, username = ?, password = ?, email = ?, dob = ? WHERE person.id = ?";
	private final String UPDATE_DEVELOPER = "UPDATE Developer SET developer_key=? WHERE person_id=?";
	// delete
	private final String DELETE_PERSON = "DELETE FROM Person WHERE id=?";
    private final String DELETE_DEVELOPER = "DELETE FROM Developer WHERE person_id=?";
	
    // create developer
    public void createDeveloper(Developer developer) {
        try {
 
            pStatement = Connection.getConnection().prepareStatement(CREATE_PERSON);
            // set parameters for pstatments
            pStatement.setInt(1, developer.getId());
            pStatement.setString(2, developer.getUsername());
            pStatement.setString(3, developer.getPassword());
            pStatement.setString(4, developer.getFirstName());
            pStatement.setString(5, developer.getLastName());
            pStatement.setString(6, developer.getEmail());
            pStatement.setDate(7,null);
            pStatement.executeUpdate();
            
            // set params for pstatement2
            pStatement1 = Connection.getConnection().prepareStatement(CREATE_DEVELOPER);
            pStatement1.setString(1, developer.getDeveloperKey());
            pStatement1.setInt(2, developer.getId());
            // execute the 
            pStatement.executeUpdate();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    // find all 
    public Collection<Developer> findAllDevelopers() {
    	Collection<Developer> allDevelopers = new ArrayList<Developer>();
    	try {
    		pStatement = Connection.getConnection().prepareStatement(FIND_ALL_DEVELOPERS);
    		ResultSet res = pStatement.executeQuery();
    		while(res.next()) {
    			 int id = res.getInt("person_id");
                 String first_name = res.getString("first_name");
                 String last_name = res.getString("last_name");
                 String userName = res.getString("username");
                 String password = res.getString("password");
                 String email = res.getString("email");
                 String developer_key = res.getString("developer_key");
                 java.sql.Date dob = res.getDate("dob");
                 
                 Developer dev = new Developer(id, first_name, last_name, userName, password, email, dob, developer_key);
                 allDevelopers.add(dev);
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
		return allDevelopers;
	}
    
    // find developer by Id
    public Developer findDeveloperById(int developerId) {
    	Developer dev = null;
    	try {
    		pStatement = Connection.getConnection().prepareStatement(FIND_DEVELOPER_BY_ID);
    		pStatement.setInt(1, developerId);
			ResultSet res = pStatement.executeQuery();
			if (res.next()) {
				String developer_key = res.getString("developer_key");
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				String username = res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				Date dob = res.getDate("dob");
				int id = res.getInt("person_id");

				dev = new Developer(id, firstName, lastName, username, password, email, dob, developer_key);
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
		return dev;
	}
    
    // find by username
    public Developer findDeveloperByUsername(String username) {
		Developer dev = null;
		try {
    		pStatement = Connection.getConnection().prepareStatement(FIND_DEVELOPER_BY_USERNAME);
    		pStatement.setString(1, username);
			ResultSet res = pStatement.executeQuery();
			while (res.next()) {
				String developer_key = res.getString("developer_key");
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");;
				String password = res.getString("password");
				String email = res.getString("email");
				Date dob = res.getDate("dob");
				int id = res.getInt("person_id");

				dev =  new Developer(id, firstName, lastName, username, password, email, dob, developer_key);
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
		return dev;
	}
    
    // find by credential
    public Developer findDeveloperByCredentials(String username, String password) {
    	Developer dev = null;
    	try {
    		pStatement = Connection.getConnection().prepareStatement(FIND_DEVELOPER_BY_CREDENTIALS);
    		pStatement.setString(1, username);
    		pStatement.setString(2, password);
			ResultSet res = pStatement.executeQuery();
			while (res.next()) {
				String developerKey = res.getString("developer_key");
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				String email = res.getString("email");
				Date dob = res.getDate("dob");
				int id = res.getInt("person_id");

				dev = new Developer(id, firstName, lastName, username, password, email, dob, developerKey);
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
		return dev;
	}

    // update developer
    public int updateDeveloper(int developerId, Developer developer) {
		try {
    		pStatement = Connection.getConnection().prepareStatement(UPDATE_PERSON);
    		// update person first
            pStatement.setString(1, developer.getFirstName());
            pStatement.setString(2, developer.getLastName());
            pStatement.setString(3, developer.getUsername());
            pStatement.setString(4, developer.getPassword());
            pStatement.setString(5, developer.getEmail());
            java.sql.Date dobSql;
            dobSql = new java.sql.Date(developer.getDob().getTime());
            pStatement.setDate(6, dobSql);
            pStatement.setInt(7, developerId);
            pStatement.executeUpdate();
            // update developer
            pStatement1 = Connection.getConnection().prepareStatement(UPDATE_DEVELOPER);
            pStatement1.setString(1, developer.getDeveloperKey());
            pStatement1.setInt(2, developerId);
            int res = pStatement1.executeUpdate();
            return res;
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
		return -1;
	}
    
    // delete developer
    public int deleteDeveloper(int developerId) {
		try {
			// delete developer first
    		pStatement = Connection.getConnection().prepareStatement(DELETE_DEVELOPER);
    		pStatement.setInt(1, developerId);
    		pStatement.executeUpdate();
    		pStatement1 = Connection.getConnection().prepareStatement(DELETE_PERSON);
    		pStatement1.setInt(1, developerId);
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