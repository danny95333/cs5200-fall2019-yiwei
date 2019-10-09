-- 1 Delete Alice's primary address
delete from address where person_id = (select id from person where username = 'alice')
and `primary` = 1;
-- 2 Remove the last widget in the Contact page. The last widget is the one with the highest value in the order field
-- to avoid error for 'specify target...' we need:
select @max_order := max(`order`) from widget;
delete from widget where `order` = @max_order  and 
page_id = (select id from `page` where title = 'Contact');
-- 3 Remove the last updated page in Wikipedia
select @pg:= id from `page` where website_id = (select id from website where `name` = 'Wikipedia') and updated order by updated desc limit 1;
-- in order to delete page, we need to delete role&priviledge
delete from page_role where page_id = @pg;
delete from page_priviledge where page_id = @pg;
delete from `page` where id = @pg;
-- 4 Remove the CNET web site, as well as all related roles and privileges relating developers to the Website and Pages
delete from page_role where page_id in(select id from `page` where website_id = (select id from website where `name` = 'CNET'));
delete from website_role where website_id in (select id from website where `name` = 'CNET');
delete from page_priviledge where page_id in(select id from `page` where website_id = (select id from website where `name` = 'CNET'));
delete from website_priviledge where website_id in (select id from website where `name` = 'CNET');
delete from `page` where website_id = (select id from website where `name` = 'CNET');
delete from website where `name` = 'CNET';

