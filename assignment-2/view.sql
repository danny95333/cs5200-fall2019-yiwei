DROP VIEW IF EXISTS `developer_roles_and_privileges`;

create view developer_roles_and_privileges as
select per.`first_name`, per.`last_name`, per.`username`, per.`email`,
	   web.`name` as `website_name`, web.`visits` as `website_visits`, web.`updated` as `website_updated_date`, -- in case update duplicate
	   web_r.`role` as `website_role`, web_p.`priviledge`as `website_priviledge`, 
	   pg.`title` as `page_title`, pg.`views`, pg.`updated`as `page_updated_date`,-- in case update duplicate
       pg_r.`role` as `page_role`, pg_p.`priviledge` as `page_priviledge`
from
	   `person`per, 
       `website`web, 
       `website_role`web_r, `website_priviledge`web_p,
       `page`pg, 
       `page_role`pg_r, `page_priviledge`pg_p,
       `developer` dev
where per.id in (dev.person_id);