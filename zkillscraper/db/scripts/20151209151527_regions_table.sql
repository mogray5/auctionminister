--// regions table
-- Migration SQL that makes the change goes here.

CREATE TABLE regions
(
	region_id BIGINT NOT NULL, 
	region_name character varying(150) NOT NULL DEFAULT '',
	CONSTRAINT PK_REGIONS PRIMARY KEY (region_id) USING INDEX TABLESPACE pg_default	
)
WITH (
	OIDS = FALSE
)
;

ALTER TABLE regions OWNER TO zkill;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE regions;