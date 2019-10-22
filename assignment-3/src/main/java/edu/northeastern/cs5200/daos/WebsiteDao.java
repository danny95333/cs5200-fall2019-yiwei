package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.model.Website;
import edu.northeastern.cs5200.model.Role;
public class WebsiteDao implements WebsiteImpl{

	private static WebsiteDao instance = null;
	private WebsiteDao() {
	}

	public static WebsiteDao getInstance() {
		if (instance == null) {
			instance = new WebsiteDao();
		}
		return instance;
	}
	private PreparedStatement pStatement = null;
    private PreparedStatement pStatement1 = null;
    private PreparedStatement pStatement2 = null;
	// createWebsiteForDeveloper
    private final String INSERT_WEBSITE = "INSERT INTO Website (id, `name`, description, created, updated, visits) VALUES (?, ?, ?, ?, ?, ?)";
    private final String INSERT_WEBSITE_ROLE = "INSERT INTO WebsiteRole (role, developer_id, website_id) VALUES (?, ?, ?)";
    //findAllWebsites
    private final String FIND_ALL_WEBSITES = "SELECT * FROM Website";
    //findWebsitesForDeveloper
    private final String FIND_WEBSITES_FOR_DEVELOPER = "SELECT * FROM Website web JOIN WebsiteRole webr ON web.id = webr.website_id WHERE developer_id = ?";
    //findWebsiteById
    private final String FIND_WEBSITES_BY_ID = "SELECT * FROM Website WHERE id=?";
    //updateWebsite
    private final String UPDATE_WEBSITE = "UPDATE Website SET `name`=?, description=?, created=?, updated=?, visits=? WHERE id=?";
    //deleteWebsite
    private final String DELETE_WEBSITE_ROLE = "DELETE FROM WebsiteRole WHERE website_id=?";
    private final String DELETE_PRIVILEDGE = "DELETE FROM website_priviledge where website_id = ?";
    private final String DELETE_WEBSITE = "DELETE FROM Website WHERE id=?";
    //
    public void createWebsiteForDeveloper(int developerId, Website website) {
		try {
			// create website
			pStatement = Connection.getConnection().prepareStatement(INSERT_WEBSITE);
    		pStatement.setInt(1, website.getId());
            pStatement.setString(2, website.getName());
            pStatement.setString(3, website.getDescription());
            java.util.Date created = website.getCreated();
            java.sql.Date createdSql;
            // solve sql data not applicable for java Date
            if (created != null) {
                createdSql = new java.sql.Date(created.getTime());
            } else createdSql = null;
            pStatement.setDate(4, createdSql);
            java.util.Date updated = website.getUpdated();
            java.sql.Date updatedSql;
            if (updated != null) {
                updatedSql = new java.sql.Date(updated.getTime());
            } else updatedSql = null;
            pStatement.setDate(5, updatedSql);
            pStatement.setInt(6, website.getVisits());
            pStatement.executeUpdate();
            // create role
            pStatement1 = Connection.getConnection().prepareStatement(INSERT_WEBSITE_ROLE);
            pStatement1.setString(1, Role.OWNER.toString().toLowerCase());
            pStatement1.setInt(2, developerId);
            pStatement1.setInt(3, website.getId());
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
    // collection for find all
    public Collection<Website> findAllWebsites() {
        ArrayList<Website> allWebsites = new ArrayList<Website>();
        try{
        	pStatement = Connection.getConnection().prepareStatement(FIND_ALL_WEBSITES);
        	ResultSet res = pStatement.executeQuery();
            while(res.next()) {
            	Integer id = res.getInt("id");
            	String name = res.getString("name");
				String description = res.getString("description");
				Date created = res.getDate("created");
				Date updated = res.getDate("updated");
				Integer visits = res.getInt("visits");
				Website web = new Website(id, name, description, created, updated, visits);
				allWebsites.add(web);
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
		return allWebsites;
	}
    
    // find website by developer id
    public Collection<Website> findWebsitesForDeveloper(int developerId) {
        ArrayList<Website> webs = new ArrayList<Website>();
        try{
        	pStatement = Connection.getConnection().prepareStatement(FIND_WEBSITES_FOR_DEVELOPER);
        	pStatement.setInt(1, developerId);
			ResultSet res = pStatement.executeQuery();
			while(res.next()) {
				Integer id = res.getInt("id");
				String name = res.getString("name");
				String description = res.getString("description");
				Date created = res.getDate("created");
				Date updated = res.getDate("updated");
				Integer visits = res.getInt("visits");
				
				Website w = new Website(id, name, description, created, updated, visits);
				webs.add(w);
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
		return webs;
	}
	
    // find website by id, not collections
    public Website findWebsiteById(int websiteId) {
		Website web = null;
		try {
			pStatement = Connection.getConnection().prepareStatement(FIND_WEBSITES_BY_ID);
        	pStatement.setInt(1, websiteId);
			ResultSet res = pStatement.executeQuery();
			while(res.next()) {
				Integer id = res.getInt("id");
				String name = res.getString("name");
				String description = res.getString("description");
				Date created = res.getDate("created");
				Date updated = res.getDate("updated");
				Integer visits = res.getInt("visits");
				web = new Website(id, name, description, created, updated, visits);
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
		return web;
	}
	
    // update website
    public int updateWebsite(int websiteId, Website website) {
		try {
			pStatement = Connection.getConnection().prepareStatement(UPDATE_WEBSITE);
            pStatement.setString(1, website.getName());
            pStatement.setString(2, website.getDescription());
            java.util.Date created = website.getCreated();
            // solve sql data not applicable for java Date
            java.sql.Date createdSql;
            if (created != null) {
                createdSql = new java.sql.Date(created.getTime());
            } else 
            	createdSql = null;
            pStatement.setDate(3, createdSql);
            java.util.Date updated = website.getUpdated();
            // solve sql data not applicable for java Date
            java.sql.Date updatedSql;
            if (updated != null) {
                updatedSql = new java.sql.Date(updated.getTime());
            } else 
            	updatedSql = null;
            pStatement.setDate(4, updatedSql);
            pStatement.setInt(5, website.getVisits());
            pStatement.setInt(6, websiteId);
            int res = pStatement.executeUpdate();
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
  
	
	// delete website
    public int deleteWebsite(int websiteId) {
		try {
			// delete web role first
			pStatement = Connection.getConnection().prepareStatement(DELETE_WEBSITE_ROLE);
			pStatement.setInt(1, websiteId);
			pStatement.executeUpdate();
			// delete website priviledge
			pStatement1 = Connection.getConnection().prepareStatement(DELETE_PRIVILEDGE);
			pStatement1.setInt(1, websiteId);
			pStatement1.executeUpdate();
			// delete website
			pStatement2 = Connection.getConnection().prepareStatement(DELETE_WEBSITE);
			pStatement2.setInt(1, websiteId);
			int res = pStatement2.executeUpdate();
			return res;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pStatement2.close();
				Connection.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
			
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
