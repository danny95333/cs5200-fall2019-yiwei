create view developer_roles_and_privileges as
select per.`first_name`, per.`last_name`, per.`username`, per.`email`,
	   web.`name` as `website_name`, web.`visits` as `website_visits`, web.`updated` as `website_updated_date`, -- in case update duplicate
	   web_r.`role` as `website_role`, web_p.`priviledge`as `website_priviledge`, 
	   pg.`title` as `page_title`, pg.`views`, pg.`updated`as `page_updated_date`,-- in case update duplicate
       pg_r.`role` as `page_role`, pg_p.`priviledge` as `page_priviledge`
from
	   `person`per join `developer` dev on per.id = dev.person_id
				   join `website_role`web_r on dev.person_id = web_r.developer_id
                   join `website`web on web_r.website_id = web.id
                   join `website_priviledge` web_p on web_p.developer_id = dev.person_id and web_p.website_id = web.id
				   left join `page` pg on web.id = pg.website_id
                   left join `page_role` pg_r on pg.id = pg_r.page_id and dev.person_id = pg_r.developer_id
                   left join `page_priviledge` pg_p on pg.id = pg_p.page_id and dev.person_id = pg_p.developer_id;
				
