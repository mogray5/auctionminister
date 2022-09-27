--// systems table
-- Migration SQL that makes the change goes here.

CREATE TABLE systems
(
	system_id BIGINT NOT NULL,
	region_id BIGINT NOT NULL REFERENCES regions(region_id),
	const_id BIGINT NOT NULL, 
	system_name character varying(150) NOT NULL DEFAULT '',
	CONSTRAINT PK_SYSTEMS PRIMARY KEY (system_id) USING INDEX TABLESPACE pg_default	
)
WITH (
	OIDS = FALSE
)
;

ALTER TABLE systems OWNER TO zkill;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE systems;