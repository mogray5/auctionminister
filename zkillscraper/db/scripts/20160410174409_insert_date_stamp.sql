--// insert date stamp
-- Migration SQL that makes the change goes here.

alter table kills add insert_date timestamp without time zone default (now() at time zone 'utc');


create index idx_kills_insert_date ON kills (insert_date);

--//@UNDO
-- SQL to undo the change goes here.

alter table kills drop insert_date;

drop index idx_kills_insert_date;
