--// items table
-- Migration SQL that makes the change goes here.

CREATE TABLE items
(
	type_id BIGINT NOT NULL,
	group_id BIGINT NOT NULL REFERENCES item_groups(group_id), 
	type_name character varying(150) NOT NULL DEFAULT '',
	CONSTRAINT PK_ITEMS PRIMARY KEY (type_id) USING INDEX TABLESPACE pg_default	
)
WITH (
	OIDS = FALSE
)
;

ALTER TABLE items OWNER TO zkill;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE items;