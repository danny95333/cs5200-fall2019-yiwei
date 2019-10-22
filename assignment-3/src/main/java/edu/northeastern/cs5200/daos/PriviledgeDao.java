package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.model.Priviledge;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Connection;
public class PriviledgeDao {
	   PreparedStatement pStatement = null;
	    
		private static PriviledgeDao instance;
		private PriviledgeDao() {};
		public static PriviledgeDao getInstance() {
			if (instance == null) {
				instance = new PriviledgeDao();
			}
			return instance;
		}
		// assignWebsitePrivilege
		private final String INSERT_WEBSITE_PRIVILEGE = "INSERT INTO WebsitePrivilege (Privilege, developer_id, website_id) VALUES (?, ?, ?)";
	    // assignPagePriviledge
		private final String INSERT_PAGE_PRIVILEGE = "INSERT INTO PagePrivilege (Privilege, developer_id, page_id) VALUES (?, ?, ?)";
	    // deleteWebsitePriviledge
		private final String DELETE_WEBSITE_PRIVILEGE = "DELETE FROM WebsitePrivilege WHERE Privilege=? AND developer_id=? AND website_id=?";
	    // deletePagePriviledge
		private final String DELETE_PAGE_PRIVILEGE = "DELETE FROM PagePrivilege WHERE Privilege=? AND developer_id=? AND page_id=?";

		//
		public void assignWebsitePrivilege(int developerId, int websiteId, String priviledge) {
        try {
        	pStatement = Connection.getConnection().prepareStatement(INSERT_WEBSITE_PRIVILEGE);
            pStatement.setString(1, Priviledge.fromString(priviledge).toString().toLowerCase());
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
		public void assignPagePrivilege(int developerId, int pageId, String priviledge) {
	        try {
	        	pStatement = Connection.getConnection().prepareStatement(INSERT_PAGE_PRIVILEGE);
	            pStatement.setString(1, Priviledge.fromString(priviledge).toString().toLowerCase());
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
	    public void deleteWebsitePrivilege(int developerId, int websiteId, String priviledge) {
	        try {
	        	pStatement = Connection.getConnection().prepareStatement(DELETE_WEBSITE_PRIVILEGE);
	            pStatement.setString(1, Priviledge.fromString(priviledge).toString().toLowerCase());
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
	    public void deletePagePrivilege(int developerId, int pageId, String priviledge) {
        try {
        	pStatement = Connection.getConnection().prepareStatement(DELETE_PAGE_PRIVILEGE);
            pStatement.setString(1, Priviledge.fromString(priviledge).toString().toLowerCase());
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
