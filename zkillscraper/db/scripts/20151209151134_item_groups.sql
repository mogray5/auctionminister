--// item groups
-- Migration SQL that makes the change goes here.

CREATE TABLE item_groups
(
	group_id BIGINT NOT NULL, 
	group_name character varying(150) NOT NULL DEFAULT '',
	CONSTRAINT PK_ITEM_GROUPS PRIMARY KEY (group_id) USING INDEX TABLESPACE pg_default	
)
WITH (
	OIDS = FALSE
)
;

ALTER TABLE item_groups OWNER TO zkill;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE item_groups;