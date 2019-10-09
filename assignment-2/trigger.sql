# create/update/delete website priviledge, after roles are created/updated/deleted
# create the roles
# remove the typo
DELIMITER && 
create trigger `website_roles_created` 
	after insert
    on `website_role`
	for each row
begin
    if new.role = 'owner' or new.role = 'admin' then 
	insert into `website_priviledge` (`priviledge`, `developer_id`, `website_id`)
    values ('create', new.developer_id, new.website_id),
		   ('read', new.developer_id, new.website_id),
		   ('update', new.developer_id, new.website_id),
           ('delete', new.developer_id, new.website_id);
	elseif new.role = 'writer' then
    insert into `website_priviledge`(`priviledge`, `developer_id`, `website_id`)
    values ('create', new.developer_id, new.website_id),
		   ('read', new.developer_id, new.website_id),
		   ('update', new.developer_id, new.website_id);
	elseif new.role = 'editor' then
    insert into `website_priviledge`(`priviledge`, `developer_id`, `website_id`)
    values ('read', new.developer_id, new.website_id),
		   ('update', new.developer_id, new.website_id);
	elseif new.role = 'reviewer' then
    insert into `website_priviledge`(`priviledge`, `developer_id`, `website_id`)
    values ('read', new.developer_id, new.website_id);
    
    end if;
end;

DELIMITER && 
create trigger `website_role_updated`
	after update 
    on `website_role`
	for each row
begin
	-- if role are different for two operation
	if new.role != old.role then
		delete from `website_priviledge` 
        where `developer_id` = old.developer_id AND `website_id` = old.website_id;
		if new.role = 'owner' or 'admin' then
		insert into `website_priviledge`(`priviledge`, `developer_id`, `website_id`) 
		values ('create', new.developer_id, new.website_id),
			   ('read', new.developer_id, new.website_id),
			   ('update', new.developer_id, new.website_id),
			   ('delete', new.developer_id, new.website_id);		
		elseif new.role = 'writer' then
		insert into `website_priviledge`(`priviledge`, `developer_id`, `website_id`)
		values ('create', new.developer_id, new.website_id),
			   ('read', new.developer_id, new.website_id),
			   ('update', new.developer_id, new.website_id);
		elseif new.role = 'editor' then
		insert into `website_priviledge`(`priviledge`, `developer_id`, `website_id`)
		values ('read', new.developer_id, new.website_id),
			   ('update', new.developer_id, new.website_id);
		elseif new.role = 'reviewer' then
		insert into `website_priviledge`(`priviledge`, `developer_id`, `website_id`)
		values ('read', new.developer_id, new.website_id);
		end if;
	else
		update `website_priviledge` set `developer_id` = new.developer_id and `website_id` = new.website_id;
        
	end if;
end;

DELIMITER && 
create trigger `website_role_deleted`
	after delete
    on `website_role`
    for each row
begin
	delete from `website_priviledge` where `developer_id` = old.developer_id and `website_id` = old.website_id;
end;


-- finish website priviledge 
-- state trigger for website role
DELIMITER &&
create trigger `page_role_inserted`
	after insert
    on `page_role`
    for each row
begin
	if new.role = 'owner' or new.role = 'admin' then 
	insert into `page_priviledge` (`priviledge`, `developer_id`, `page_id`)
    values ('create', new.developer_id, new.page_id),
		   ('read', new.developer_id, new.page_id),
		   ('update', new.developer_id, new.page_id),
           ('delete', new.developer_id, new.page_id);
	elseif new.role = 'writer' then
	insert into `page_priviledge`(`priviledge`, `developer_id`, `page_id`)
	values ('create', new.developer_id, new.page_id),
		   ('read', new.developer_id, new.page_id),
		   ('update', new.developer_id, new.page_id);
	elseif new.role = 'editor' then
	insert into `page_priviledge`(`priviledge`, `developer_id`, `page_id`)
	values ('read', new.developer_id, new.page_id),
		   ('update', new.developer_id, new.page_id);
	elseif new.role = 'reviewer' then
	insert into `page_priviledge`(`priviledge`, `developer_id`, `page_id`)
	values ('read', new.developer_id, new.page_id);
    
	end if;
end;

DELIMITER && 
create trigger `page_role_updated`
	after update 
    on `page_role`
	for each row
begin
	-- if role are different for two operation
	if new.role != old.role then
		delete from `page_priviledge` 
        where `developer_id` = old.developer_id AND `page_id` = old.page_id;
		if new.role = 'owner' or 'admin' then
		insert into `page_priviledge`(`priviledge`, `developer_id`, `page_id`) 
		values ('create', new.developer_id, new.page_id),
			   ('read', new.developer_id, new.page_id),
			   ('update', new.developer_id, new.page_id),
			   ('delete', new.developer_id, new.page_id);		
		elseif new.role = 'writer' then
		insert into `page_priviledge`(`priviledge`, `developer_id`, `page_id`)
		values ('create', new.developer_id, new.page_id),
			   ('read', new.developer_id, new.page_id),
			   ('update', new.developer_id, new.page_id);
		elseif new.role = 'editor' then
		insert into `page_priviledge`(`priviledge`, `developer_id`, `page_id`)
		values ('read', new.developer_id, new.page_id),
			   ('update', new.developer_id, new.page_id);
		elseif new.role = 'reviewer' then
		insert into `page_priviledge`(`priviledge`, `developer_id`, `page_id`)
		values ('read', new.developer_id, new.page_id);
		end if;
	else
		update `page_priviledge` set `developer_id` = new.developer_id and `page_id` = new.page_id;
        
	end if;
end;

DELIMITER &&
create trigger `page_role_deleted`
	after delete
    on `page_role`
    for each row
begin
	delete from `page_priviledge` where `developer_id` = old.developer_id and `page_id` = old.page_id;
end;





