--// create sy005 table
-- Migration SQL that makes the change goes here.

CREATE SEQUENCE sy005_seq
   INCREMENT 1
   START 1;
ALTER TABLE sy005_seq OWNER TO auctionminister;

CREATE TABLE sy005 
(
  REPORTID SMALLINT NOT NULL DEFAULT nextval('sy005_seq'::regclass),
  REPORTNAME varchar(100) NOT NULL DEFAULT '',
  REPORTACTION varchar(50) NOT NULL DEFAULT '',
  REPORTFILE varchar(100) NOT NULL DEFAULT '',
  CONSTRAINT PK_SY005 PRIMARY KEY (REPORTID) USING INDEX TABLESPACE pg_default,
  CONSTRAINT UK_SY005_1 UNIQUE (REPORTNAME) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;

ALTER TABLE sy005 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE sy005;
DROP SEQUENCE sy005_seq;