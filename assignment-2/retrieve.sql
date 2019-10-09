-- retrieve developers
-- a
select * from person where `id` in (select person_id from developer);
-- b
select * from person where `id` = 34;
-- c, need join developer and web_role and select web_id from website
select * from person where id in (select per.person_id from 
(select * from developer dev join website_role web_r on dev.person_id = web_r.developer_id) per 
where per.role != 'owner' and per.website_id in (select id from website where name = 'Twitter'));
-- d all developers who are page reviewers of pages with less than 300000 visits (charlie, bob)
select * from person where id in (select per.person_id from
(select * from developer dev join page_role pg_r on dev.person_id = pg_r.developer_id) per
where per.role = 'reviewer' and per.page_id in (select id from `page` where views < 300000));
-- e need join developer and page_role
-- per_role = writer
-- page_id in page where website id where in website id = 'CNET'
-- page_id from widget where heading
-- title is home in `page`
select * from person where id in (select per.person_id from
(select * from developer dev join page_role pg_r on dev.person_id = pg_r.developer_id) per
where per.role = 'writer' and 
	  per.page_id in (select id from `page` where website_id in (select id from website where `name` = 'CNET') and
							 id in (select page_id from widget where dtype = 'heading') and
							 title = 'Home'));
					
-- 2
-- a
select * from website where visits = (select min(visits) from website);
-- b
select `name` from website where id = 678;
-- c all websites with videos reviewed by bob
select * from website where id in (select a.website_id from (select website_id, page_id, developer_id, role
from page_role pg_r join `page` pg on pg_r.page_id = pg.id) a where 
a.page_id in (select page_id from widget where dtype = 'youtube')
and a.developer_id = (select id from person where username = 'bob')
and a.role = 'reviewer'); 
-- d all websites where alice is an owner
select * from website where id in (select id from
(select web.id, role, developer_id from website web join website_role web_r on web.id = web_r.website_id) a 
where a.role = 'owner' and a.developer_id = (select id from person where `username` = 'alice'));
-- e all websites where charlie is an admin and get more than 6000000 visits
select * from website where visits > 6000000 and
id in (select a.id from
(select web.id, role,developer_id from website web join website_role web_r on web.id = web_r.website_id) a
where a.role = 'admin' and a.developer_id = (select id from person where `username` = 'charlie'));

-- 3
-- a page with the most number of views
select * from `page` where views = (select max(views) from `page`);
-- b title of a page whose id is 234
select title from `page` where id = 234;
-- c all pages where alice is an editor (About)
select * from `page` where id in (select a.id from
(select pg.id,role,developer_id from `page` pg join page_role pg_r on pg.id = pg_r.page_id) a
where a.role = 'editor' and a.developer_id = (select id from person where username = 'alice'));
-- d the total number of pageviews in CNET
select sum(views) from `page` where website_id = (select id from website where `name` = 'CNET');
-- e the average number of page views in the Web site Wikipedia
select avg(views) from `page` where  website_id = (select id from website where `name` = 'Wikipedia');

-- 4
-- a all widgets in CNET's Home page
select * from widget where page_id = (select id from `page` where title = 'Home' and 
website_id = (select id from website where `name` = 'CNET'));
-- b all youtube widgets in CNN
select * from widget where dtype = 'youtube' and 
page_id in (select id from `page` where website_id = (select id from website where `name` = 'CNN'));
-- c all image widgets on pages reviewed by Alice
select * from widget where dtype = 'image' and
page_id in (select a.id from
(select pg.id, role, developer_id from `page` pg join page_role pg_r on pg.id = pg_r.page_id) a
where a.role = 'reviewer' and a.developer_id = (select id from person where username = 'Alice'));
-- d how many widgets are in Wikipedia
select count(id) from widget where page_id in 
(select id from `page` where website_id = (select id from website where `name` = 'Wikipedia'));

-- 5
-- a Retrieve the names of all the websites where Bob has DELETE privileges.
-- Answer: Twitter, Wikipedia, CNET, Gizmodo (where Bob has either owner or admin roles).
select `name` from website where id in(select website_id from website_priviledge where priviledge = 'delete' and
developer_id = (select id from person where `username`='bob'));
-- Retrieve the names of all the pages where Charlie has CREATE privileges.
-- Answer: Home, Preferences (where Charlie has Writer role)
select `name` from `page` where id in (select page_id from page_priviledge where priviledge = 'create' and
developer_id = (select id from person where `username`='charlie'));

