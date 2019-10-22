package edu.northeastern.cs5200;
import java.util.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import edu.northeastern.cs5200.model.*;
import edu.northeastern.cs5200.daos.*;

public class hw_jdbc_wang_yiwei {
	// create developer and user
	public static void main(String[] args) {
		DeveloperDao developerDao =  DeveloperDao.getInstance();
		Developer alice = new Developer(12, "Alice","Wonder", "alice", "alice", "alice@wonder.com",null,"4321rewq");
		developerDao.createDeveloper(alice);
		
		Developer bob = new Developer(23, "Bob","Marley", "bob", "bob","bob@marley.com", null,"5432trew");
		developerDao.createDeveloper(bob);
		
		Developer charles = new Developer(34, "Charles","Garcia", "charlie", "charlie", "chuch@garcia.com", null,"6543ytre");
		developerDao.createDeveloper(charles);
		// create users
		
		UserDao userDao = UserDao.getInstance();
		User dan = new User(45, "Dan","Martin", "dan", "dan", "alice@wonder.com", null);
		userDao.createUser(dan);
		
		User ed = new User(56, "Ed","Karaz", "ed", "ed", "alice@wonder.com", null);
		userDao.createUser(ed);
		
		//create website
		Date date=new Date(new java.util.Date().getTime());
		Website facebook = new Website(123, "Facebook", "an online social media and social networking service",date, date, 1234234);
		Website twitter = new Website(234,"Twitter", "an online news and social networking service",date, date, 4321543);
		Website wikipedia = new Website(345, "Wikipedia", "a free online encyclopedia",date, date, 3456654);
		Website cnn = new Website(456, "CNN", "an American basic cable and satellite television news channel",date,date, 6543345);
		Website cnet = new Website(567, "CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics",date, date, 5433455);
		Website gizmodo = new Website(678, "Gizmodo", "a design, technology, science and science fiction website that also writes articles on politics",date, date, 4322345);
		WebsiteDao websiteDao = WebsiteDao.getInstance();
		websiteDao.createWebsiteForDeveloper(12, facebook);
		websiteDao.createWebsiteForDeveloper(23, twitter);
		websiteDao.createWebsiteForDeveloper(34, wikipedia);
		websiteDao.createWebsiteForDeveloper(12, cnn);
		websiteDao.createWebsiteForDeveloper(23, cnet);
		websiteDao.createWebsiteForDeveloper(34, gizmodo);
		//insert website role
        RoleDao roleDao = RoleDao.getInstance();
        roleDao.assignWebsiteRole(23, 123, 4);
        roleDao.assignWebsiteRole(34, 123, 2);
        roleDao.assignWebsiteRole(34, 234, 4);
        roleDao.assignWebsiteRole(12, 234, 2);
        roleDao.assignWebsiteRole(12, 345, 4);
        roleDao.assignWebsiteRole(23, 345, 2);
        roleDao.assignWebsiteRole(23, 456, 4);
        roleDao.assignWebsiteRole(34, 456, 2);
        roleDao.assignWebsiteRole(34, 567, 4);
        roleDao.assignWebsiteRole(12, 567, 2);
        roleDao.assignWebsiteRole(12, 678, 4);
        roleDao.assignWebsiteRole(23, 678, 2);
      //Create pages
        Calendar start = new GregorianCalendar(2019,9,3);
		Calendar due = new GregorianCalendar(2019,10,20);
		java.util.Date utilDateStart = start.getTime();
		java.util.Date utilDateDue = due.getTime();
		Date sqlDate = new Date(utilDateStart.getTime());
		Date sqlDate1 = new Date(utilDateDue.getTime());
  		Page home = new Page(123, "Home", "Landing Page", sqlDate,sqlDate1, 123434);
  		Page about = new Page(234, "About", "Website Description", sqlDate, sqlDate1, 234545);
		Page contact = new Page(345, "Contact", "Address, Phones and contact info", sqlDate, sqlDate1, 345656);
		Page preference = new Page(456, "Preferences", "Where users can configure their preferences", sqlDate, sqlDate1, 456776);
  		Page profile = new Page(567, "Profile", "Users can configure their personal information", sqlDate, sqlDate1, 567878);
  		PageDao pageDao = PageDao.getInstance();
  		pageDao.createPageForWebsite(567, home);
		pageDao.createPageForWebsite(678, about);
		pageDao.createPageForWebsite(345, contact);
		pageDao.createPageForWebsite(456, preference);
		pageDao.createPageForWebsite(567, profile);
		
		//insert page role
        roleDao.assignPageRole(12, 123, 4);
        roleDao.assignPageRole(23, 123, 5);
        roleDao.assignPageRole(34, 123, 3);
        roleDao.assignPageRole(23, 234, 4);
        roleDao.assignPageRole(34, 234, 5);
        roleDao.assignPageRole(12, 234, 3);
        roleDao.assignPageRole(34, 345, 4);
        roleDao.assignPageRole(12, 345, 5);
        roleDao.assignPageRole(23, 345, 3);
        roleDao.assignPageRole(12, 456, 4);
        roleDao.assignPageRole(23, 456, 5);
        roleDao.assignPageRole(34, 456, 3);
        roleDao.assignPageRole(23, 567, 4);
        roleDao.assignPageRole(34, 567, 5);
        roleDao.assignPageRole(12, 567, 3);
        
        //create widget
        HeadingWidget widget1 = new HeadingWidget(1, "head123", 0, 0, null, null, "Welcome", 0);
        HtmlWidget widget2 = new HtmlWidget(2, "post234", 0, 0, null, null, "<p>Lorem</p>", 0);
        HeadingWidget widget3 = new HeadingWidget(3, "head345", 0, 0, null, null, "Hi", 1);
        HtmlWidget widget4 = new HtmlWidget(4, "intro345", 0, 0, null, null, "<h1>Hi</h1>", 2);
        ImageWidget widget5 = new ImageWidget(5, "image345", 50, 100, null, null, null, 3, "/img/567.png");
        YouTubeWidget widget6 = new YouTubeWidget(6, "video456", 400, 300, null, null, null, 0, "https://youtu.be/h67VX51QXiQ");
        // we need to create func to create instance for HeadingWidget/HtmlWidget/ImageWidget/YoutubeWidget
        WidgetDao widgetDao = WidgetDao.getInstance();
        widgetDao.createWidgetForPage(123, widget1);
        widgetDao.createWidgetForPage(234, widget2);
        widgetDao.createWidgetForPage(345, widget3);
        widgetDao.createWidgetForPage(345, widget4);
        widgetDao.createWidgetForPage(345, widget5);
        widgetDao.createWidgetForPage(456, widget6);
	}
	 private void update() {
		 //Update developer - Update Charlie's primary phone number to 333-444-5555
		 DeveloperDao developerDao = DeveloperDao.getInstance();
		 Developer res = developerDao.findDeveloperByUsername("charlie");
		 if (res != null) {
				int developer_id = res.getId();
				Collection<Phone> phones = res.getPhones();
				if (phones != null) {
					for (Phone phone : phones) {
						if (phone.getPrimary()) {
							phone.setPhone("333-444-5555");
							res.addPhone(phone);
							break;}
					}
				}
				else {
					Phone chaliep = new Phone("333-444-5555", true, res);
		            res.addPhone(chaliep);
					}
		 }
		// Update widget - Update the relative order of widget head345 on the page so that it's new order is 3.
		// Note that the other widget's order needs to update as well
		 WidgetDao widgetDao = WidgetDao.getInstance();
		 Collection<Widget> widgets= widgetDao.findAllWidgets();
		 for (Widget w : widgets) {
			 String name = w.getName();
				int order = w.getOrder();
				int id = w.getId();
				if (name.equals("head345") ) {
					w.setOrder(3);
					widgetDao.updateWidget(id, w);
					continue;
				}
				if (!name.equals("head345") && (order == 1 || order == 2 || order == 3)) {
					w.setOrder(order - 1);
					widgetDao.updateWidget(id, w);
				}		
		 }
		 // Update page - Append 'CNET - ' to the beginning of all CNET's page titles
		 PageDao pageDao = PageDao.getInstance();
		 Collection<Page> pageResult = pageDao.findPagesForWebsite(567);
		 for (Page p : pageResult) {
				p.setTitle("CNET - " + p.getTitle());
				pageDao.updatePage(p.getId(), p);
				}
		// Update roles - Swap Charlie's and Bob's role in CNET's Home page
		 ////////////////
		// swap role for charlie and bob
		RoleDao roleDao = RoleDao.getInstance();
		int pageId = 123;
		Developer c = developerDao.findDeveloperByUsername("charlie");
		int cId = c.getId();
		Developer b = developerDao.findDeveloperByUsername("bob");
		int bId = b.getId();
		roleDao.deletePageRole(cId, pageId, 3);
		roleDao.deletePageRole(bId, pageId, 5);
		roleDao.assignPageRole(cId, pageId, 5);
		roleDao.assignPageRole(bId, pageId, 3); 
	 }
	 private void delete() {
		 DeveloperDao devDao = DeveloperDao.getInstance();
		 //Delete developer - Delete Alice's primary address
		 Developer alice = devDao.findDeveloperByUsername("alice");
		 int developerId = alice.getId();
			Collection<Address> addresses = alice.getAddresses();
			if (addresses == null) {
				return;
			} else {				
				for (Address address : addresses) {
					if (address.getPrimary() == 1) {
						addresses.remove(address);
						break;
					}
				}
			}
			alice.setAddresses(addresses);
			devDao.updateDeveloper(developerId, alice);
        // Delete widget - Remove the last widget in the Contact page. The last widget is the one with the highest
		//	value in the order field
			PageDao pgDao = PageDao.getInstance();
			Collection<Page> pages = pgDao.findAllPages();
			int pageId = 345;
			for(Page p : pages) {
				if (p.getTitle().equals("Contact")) {
					pageId = p.getId();
				}
			}
			int widgetId = 0;
			int max = 0;
			WidgetDao widgetDao = WidgetDao.getInstance();
			Collection<Widget> widgets = widgetDao.findWidgetsForPage(pageId);
			for(Widget wg : widgets) {
				if(wg.getOrder() > max) {
					max = wg.getOrder();
					widgetId = wg.getId();
				}
			}
			widgetDao.deleteWidget(widgetId);
		// Delete page - Remove the last updated page in Wikipedia
			WebsiteDao wbDao = WebsiteDao.getInstance();
			Collection<Website> web = wbDao.findAllWebsites();
			int webId = 0;
			for (Website q : web) {
				if (q.getName().equals("Wikipedia") ) {
					webId = q.getId();
				}
			}
			Collection<Page> page1 = pgDao.findPagesForWebsite(webId);
			Date cur = new Date(0);
			int pg_id = 0;
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
			for (Page p : page1) {
				String s = fmt.format(cur);
				String cur_time = fmt.format(p.getUpdated());
				if (Date.valueOf(cur_time).after(Date.valueOf(s))) {
					pg_id = p.getId();
				}
			}
			pgDao.deletePage(pg_id);
		// Delete website - Remove the CNET web site, as well as all related roles and privileges relating
		//	developers to the Website and Pages
			// in deleteWebsite(), we delete priviledge and role at the same time
			WebsiteDao websiteDao = WebsiteDao.getInstance();
	        websiteDao.deleteWebsite(567);

	 }   
	 
	 
	}// final

