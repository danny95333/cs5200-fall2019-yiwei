package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.model.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Connection;
public class RoleDao implements RoleImpl {
    PreparedStatement pStatement = null;
    
	private static RoleDao instance;
	private RoleDao() {};
	public static RoleDao getInstance() {
		if (instance == null) {
			instance = new RoleDao();
		}
		return instance;
	}
	// assignWebsiteRole
	private final String INSERT_WEBSITE_ROLE = "INSERT INTO WebsiteRole (role, developer_id, website_id) VALUES (?, ?, ?)";
    // assignPageRole
	private final String INSERT_PAGE_ROLE = "INSERT INTO PageRole (role, developer_id, page_id) VALUES (?, ?, ?)";
    // deleteWebsiteRole
	private final String DELETE_WEBSITE_ROLE = "DELETE FROM WebsiteRole WHERE role=? AND developer_id=? AND website_id=?";
    // deletePageRole
	private final String DELETE_PAGE_ROLE = "DELETE FROM PageRole WHERE role=? AND developerId=? AND page_id=?";
	
	//
	public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
        try {
        	pStatement = Connection.getConnection().prepareStatement(INSERT_WEBSITE_ROLE);
            pStatement.setString(1, Role.fromInt(roleId).toString().toLowerCase());
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, websiteId);
            pStatement.executeUpdate();
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
	}
	//
	public void assignPageRole(int developerId, int pageId, int roleId) {
        try {
        	pStatement = Connection.getConnection().prepareStatement(INSERT_PAGE_ROLE);
            pStatement.setString(1, Role.fromInt(roleId).toString().toLowerCase());
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, pageId);
            pStatement.executeUpdate();
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
	}
	//
	public void deleteWebsiteRole(int developerId, int websiteId, int roleId) {
        try {
        	pStatement = Connection.getConnection().prepareStatement(DELETE_WEBSITE_ROLE);
            pStatement.setString(1, Role.fromInt(roleId).toString().toLowerCase());
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, websiteId);
            pStatement.executeUpdate();
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
  	}
	//
	public void deletePageRole(int developerId, int pageId, int roleId) {
        try {
        	pStatement = Connection.getConnection().prepareStatement(DELETE_PAGE_ROLE);
            pStatement.setString(1, Role.fromInt(roleId).toString().toLowerCase());
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, pageId);
            pStatement.executeUpdate();
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
  	}










}
		