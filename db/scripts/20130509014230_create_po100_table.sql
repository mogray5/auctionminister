--// create po100 table
-- Migration SQL that makes the change goes here.

CREATE SEQUENCE po100_seq
   INCREMENT 1
   START 1;
ALTER TABLE po100_seq OWNER TO auctionminister;

CREATE TABLE po100 
(
  USERID SMALLINT NOT NULL DEFAULT 0,
  DOCID NUMERIC(20) NOT NULL DEFAULT nextval('po100_seq'::regclass),
  BATCHID varchar(50) NOT NULL DEFAULT '',
  BATCHDATE date NOT NULL,
  CONSTRAINT PK_PO100 PRIMARY KEY (DOCID) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;

ALTER TABLE po100 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE po100;
DROP SEQUENCE po100_seq;
