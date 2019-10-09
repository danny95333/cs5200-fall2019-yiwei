SET SQL_SAFE_UPDATES=0;
-- 1 Update Charlie's primary phone number to 333-444-5555
update phone set phone = '333-444-5555' where `primary` = 1 and 
person_id = (select id from person where `username` = 'charlie');
-- 2 Update the relative order of widget head345 on the page so that 
-- it's new order is 3. Note that the other widget's order needs to update as well
-- other two widget order should be 1,2

update widget set `order` = 3 where name = 'head345';
update widget set `order` = `order`-1 where `name` != 'head345' and page_id = 
(select id from `page` where title = 'Contact');
 
-- 3 Update page - Append 'CNET - ' to the beginning of all CNET's page titles
update `page` set title = concat('CNET - ', title)
where website_id = (select id from website where `name` = 'CNET');
-- 4 Swap Charlie's and Bob's role in CNET's Home page
select @placeholder_1 := role from page_role where page_id in 
(select id from `page` where (title = 'CNET - Home') and website_id = (select id from website where `name` = 'CNET'))
and developer_id = (select id from person where `username` = 'charlie');
select @placeholder_2 := role from page_role where page_id in 
(select id from `page` where (title = 'CNET - Home') and website_id = (select id from website where `name` = 'CNET'))
and developer_id = (select id from person where `username` = 'bob');
-- swap
update page_role set role = @placeholder_1 where page_id in 
(select id from `page` where (title = 'CNET - Home') and website_id = (select id from website where `name` = 'CNET'))
and developer_id = (select id from person where `username` = 'bob');
update page_role set role = @placeholder_2 where page_id in 
(select id from `page` where (title = 'CNET - Home') and website_id = (select id from website where `name` = 'CNET'))
and developer_id = (select id from person where `username` = 'charlie');
