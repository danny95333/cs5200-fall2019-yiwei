package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.model.Page;
import edu.northeastern.cs5200.model.Website;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
public class PageDao implements PageImpl {
	
	private static PageDao instance = null;

	private PageDao() {
		
	}

	public static PageDao getInstance() {
		if (instance == null) {
			instance = new PageDao();
		}
		return instance;
	}
	private PreparedStatement pStatement = null;
    private PreparedStatement pStatement1 = null;
    
    // createPageForWebsite
    private final String INSERT_PAGE = "INSERT INTO Page (id, title, description, created, updated, views, website_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    // findAllPages
    private final String FIND_ALL_PAGES = "SELECT * FROM Page";
    // findPageById
    private final String FIND_PAGE_BY_ID = "SELECT * FROM Page WHERE id=?";
    // findPagesForWebsite
    private final String FIND_PAGE_FOR_WEBSITE = "SELECT * FROM Page WHERE website_id=?";
    // updatePage
    private final String UPDATE_PAGE = "UPDATE Page SET title=?, description=?, created=?, updated=?, views=?, website_id=? WHERE id=?";
    //  deletePage
    private final String DELETE_PAGE = "DELETE FROM Page WHERE id=?";
	
    // createPageForWebsite
    public void createPageForWebsite(int websiteId, Page page) {
        try {
        	pStatement = Connection.getConnection().prepareStatement(INSERT_PAGE);
        	pStatement.setInt(1, page.getId());
            pStatement.setString(2, page.getTitle());
            pStatement.setString(3, page.getDescription());
            java.util.Date created = page.getCreated();
            // solve sql data not applicable for java Date
            java.sql.Date createdSql;
            if (created != null) {
                createdSql = new java.sql.Date(created.getTime());
            } else 
            	createdSql = null;
            pStatement.setDate(4, createdSql);
            // solve sql data not applicable for java Date
            java.util.Date updated = page.getUpdated();
            java.sql.Date updatedSql;
            if (updated != null) {
                updatedSql = new java.sql.Date(updated.getTime());
            } else 
            	updatedSql = null;
            pStatement.setDate(5, updatedSql);
            pStatement.setInt(6, page.getViews());
            pStatement.setInt(7, websiteId);
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
    
    // findAllPages
	public Collection<Page> findAllPages() {
			
	        ArrayList<Page> Pages = new ArrayList<Page>();
	        
	        try{
	        	pStatement = Connection.getConnection().prepareStatement(FIND_ALL_PAGES);
	        	ResultSet res = pStatement1.executeQuery();
	            while(res.next()) {
	            	Integer id = res.getInt("id");
	            	String title = res.getString("title");
					String description = res.getString("description");
					Date created = res.getDate("created");
					Date updated = res.getDate("updated");
					Integer views = res.getInt("views");
					int websiteId = res.getInt("website_id");
		            Page page = new Page(id, title, description, created, updated, views);
		            // add to website 
		            WebsiteDao dao = WebsiteDao.getInstance();
		            Website website = dao.findWebsiteById(websiteId);
		            page.setWebsite(website);
					Pages.add(page);
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
			return Pages;
		}
		//findPageById
	public Page findPageById(int pageId) {
		Page page = null;
		try {
			pStatement = Connection.getConnection().prepareStatement(FIND_PAGE_BY_ID);
			pStatement.setInt(1, pageId);
			ResultSet res = pStatement.executeQuery();
			while(res.next()) {
				 int id = res.getInt("id");
	                String title = res.getString("title");
	                String description = res.getString("description");
	                Date created = res.getDate("created");
	                Date updated = res.getDate("updated");
	                int views = res.getInt("views");
	                int websiteId = res.getInt("websiteId");
	                //
	                page = new Page(id, title, description, created, updated, views);
	                // update page's website
	                WebsiteDao dao = WebsiteDao.getInstance();
	                Website website = dao.findWebsiteById(websiteId);
	                page.setWebsite(website);
	                return page;
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
		
		return page;
	}
	//findPagesForWebsite
	public Collection<Page> findPagesForWebsite(int websiteId) {
		ArrayList<Page> pages = new ArrayList<>();
		try {
			pStatement = Connection.getConnection().prepareStatement(FIND_PAGE_FOR_WEBSITE);
			pStatement.setInt(1, websiteId);
			ResultSet res = pStatement.executeQuery();
			while(res.next()) {
				int id = res.getInt("id");
				String title = res.getString("title");
				String description = res.getString("description");
				Date created = res.getDate("created");
				Date updated = res.getDate("updated");
				Integer views = res.getInt("views");
				Page page = new Page(id, title, description, created, updated, views);
				// website information
	            WebsiteDao dao = WebsiteDao.getInstance();
	            Website website = dao.findWebsiteById(websiteId);
	            page.setWebsite(website);
	            pages.add(page);
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
		return pages;
	}
	
	// updatePage
	public int updatePage(int pageId, Page page) {
		try {
			pStatement = Connection.getConnection().prepareStatement(UPDATE_PAGE);
			pStatement.setString(1, page.getTitle());
            pStatement.setString(2, page.getDescription());
            java.util.Date created = page.getCreated();
            // solve sql data not applicable for java Date
            java.sql.Date createdSql;
            if (created != null) {
                createdSql = new java.sql.Date(created.getTime());
            } else 
            	createdSql = null;
            pStatement.setDate(3, createdSql);
            // solve sql data not applicable for java Date
            java.util.Date updated = page.getUpdated();
            java.sql.Date updatedSql;
            if (updated != null) {
                updatedSql = new java.sql.Date(updated.getTime());
            } else 
            	updatedSql = null;
            pStatement.setDate(4, updatedSql);
            pStatement.setInt(5, page.getViews());
            pStatement.setInt(6, page.getWebsite().getId());
            pStatement.setInt(7, page.getId());
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
            	
	// DELETE_PAGE
	public int deletePage(int pageId) {
		try {
			// 
			pStatement = Connection.getConnection().prepareStatement(DELETE_PAGE);
			pStatement.setInt(1, pageId);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
