--// create sa100 table
-- Migration SQL that makes the change goes here.

CREATE SEQUENCE sa100_seq
   INCREMENT 1
   START 1;
ALTER TABLE sa100_seq OWNER TO auctionminister;

CREATE TABLE sa100 
(
  USERID SMALLINT NOT NULL DEFAULT 0,
  DOCID  NUMERIC(20) NOT NULL DEFAULT nextval('sa100_seq'::regclass),
  BATCHID varchar(50) NOT NULL DEFAULT '',
  BATCHDATE date NOT NULL,
  CONSTRAINT PK_SA100 PRIMARY KEY (DOCID) USING INDEX TABLESPACE pg_default,
  CONSTRAINT UK_SA100_1 UNIQUE (USERID,BATCHID) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;

ALTER TABLE sa100 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE sa100;
DROP SEQUENCE sa100_seq;