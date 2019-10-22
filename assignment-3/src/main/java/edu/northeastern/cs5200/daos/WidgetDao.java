package edu.northeastern.cs5200.daos;
import edu.northeastern.cs5200.model.Widget;
import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.model.Page;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
public class WidgetDao implements WidgetImpl{
	private static WidgetDao instance = null;
	private WidgetDao() {
	}

	public static WidgetDao getInstance() {
		if (instance == null) {
			instance = new WidgetDao();
		}
		return instance;
	}
	private PreparedStatement pStatement = null;
    //createWidgetForPage
    private final String INSERT_WIDGET = "INSERT INTO Widget (id, `name`, width, height, css_class, css_style, text, `order`, page_id) VALUES (?, ?, ?, ?, ?, ? ,?, ?, ?)";
    //findAllWidgets
    private final String FIND_ALL_WIDGETS = "SELECT * FROM Widget";
    //findWidgetById
    private final String FIND_WIDGET_BY_ID = "SELECT * FROM Widget WHERE id=?";
    // findWidgetsForPage
    private final String FIND_WIDGETS_FOR_PAGE = "SELECT * FROM Widget WHERE page_id=?";
    // updateWidget
    private final String UPDATE_WIDGET = "UPDATE Widget SET `name`=?, width=?, height=?, css_class=?, css_style=?, text=?, `order`=?, page_id=? WHERE id=?";
    // deleteWidget
    private final String DELETE_WIDGET = "DELETE FROM Widget WHERE id=?";
    public void createWidgetForPage(int pageId, Widget widget) {
        try {
        	pStatement = Connection.getConnection().prepareStatement(INSERT_WIDGET);
            pStatement.setInt(1, widget.getId());
            pStatement.setString(2, widget.getName());
            pStatement.setInt(3, widget.getWidth());
            pStatement.setInt(4, widget.getHeight());
            pStatement.setString(5, widget.getCssClass());
            pStatement.setString(6, widget.getCssStyle());
            pStatement.setString(7, widget.getText());
            pStatement.setInt(8, widget.getOrder());
            pStatement.setInt(9, pageId);
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
    // findAllWidgets
    public Collection<Widget> findAllWidgets() {
		ArrayList<Widget> widgets = new ArrayList<>();
		try {
			pStatement = Connection.getConnection().prepareStatement(FIND_ALL_WIDGETS);
			ResultSet res = pStatement.executeQuery();
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				Integer width = (Integer)res.getObject("width");
				Integer height = (Integer)res.getObject("height");
				String cssClass = res.getString("css_class");
				String cssStyle = res.getString("css_style");
				String text = res.getString("text");
				Integer order = res.getInt("order");
				int pageId = res.getInt("page_id");
				Widget widget = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
				// add page
				PageDao dao = PageDao.getInstance();
                Page page = dao.findPageById(pageId);
                widget.setPage(page);
                widgets.add(widget);
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
		return widgets;
	}
    
    //
    public Widget findWidgetById(int widgetId) {
		Widget wid = null;
		try {
			pStatement = Connection.getConnection().prepareStatement(FIND_WIDGET_BY_ID);
			pStatement.setInt(1, widgetId);
			ResultSet res = pStatement.executeQuery();
			while(res.next()) {
				String name = res.getString("name");
				Integer width = (Integer)res.getObject("width");
				Integer height = (Integer)res.getObject("height");
				String cssClass = res.getString("css_class");
				String cssStyle = res.getString("css_style");
				String text = res.getString("text");
				Integer order = res.getInt("order");
				int id = res.getInt("id");
				int pageId = res.getInt("page_id");
				wid = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
				// add page
				PageDao dao = PageDao.getInstance();
                Page page = dao.findPageById(pageId);
                wid.setPage(page);
                return wid;
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
			
			return null;
		}
    
    //
    public Collection<Widget> findWidgetsForPage(int pageId) {
    	Collection<Widget> widgets = new ArrayList<>();
		try {
			pStatement = Connection.getConnection().prepareStatement(FIND_WIDGETS_FOR_PAGE);
			pStatement.setInt(1, pageId);
			ResultSet res = pStatement.executeQuery();
			while(res.next()) {
				String name = res.getString("name");
				Integer width = (Integer)res.getObject("width");
				Integer height = (Integer)res.getObject("height");
				String cssClass = res.getString("css_class");
				String cssStyle = res.getString("css_style");
				String text = res.getString("text");
				Integer order = res.getInt("order");
				int id = res.getInt("id");	
				Widget widget = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
                PageDao dao = PageDao.getInstance();
                Page page = dao.findPageById(pageId);
                widget.setPage(page);
                widgets.add(widget);
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
		return widgets;
	}
    //updateWidget
    public int updateWidget(int widgetId, Widget widget) {
		try {
			pStatement = Connection.getConnection().prepareStatement(UPDATE_WIDGET);
			pStatement.setString(1, widget.getName());
			pStatement.setInt(2, widget.getWidth());
	        pStatement.setInt(3, widget.getHeight());
	        pStatement.setString(4, widget.getCssClass());
	        pStatement.setString(5, widget.getCssStyle());
	        pStatement.setString(6, widget.getText());
	        pStatement.setInt(7, widget.getOrder());
	        pStatement.setInt(8, widget.getPage().getId());
	        pStatement.setInt(9, widgetId);
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
		
    //delete
    public int deleteWidget(int widgetId) {
		try {
			pStatement = Connection.getConnection().prepareStatement(DELETE_WIDGET);
		
			pStatement.setInt(1,widgetId);
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
