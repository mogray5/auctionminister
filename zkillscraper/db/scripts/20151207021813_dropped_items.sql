--// dropped items
-- Migration SQL that makes the change goes here.

CREATE SEQUENCE dropped_items_seq
   INCREMENT 1
   START 1;
ALTER TABLE dropped_items_seq OWNER TO zkill;

CREATE TABLE dropped_items
(
   ID BIGINT NOT NULL DEFAULT nextval('dropped_items_seq'::regclass),
   KILL_ID BIGINT NOT NULL REFERENCES kills (kill_id), 
   TYPE_ID BIGINT NOT NULL,
   FLAG BIGINT,
   QTY_DROPPED INTEGER,
   QTY_DESTROYED INTEGER,
   SINGLETON BIGINT,
   CONSTRAINT PK_DROPPED_ITEMS PRIMARY KEY (ID) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE dropped_items OWNER TO zkill;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE dropped_items;
DROP SEQUENCE dropped_items_seq;


