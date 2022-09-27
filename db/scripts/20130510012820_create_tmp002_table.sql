--// create tmp002 table
-- Migration SQL that makes the change goes here.

CREATE SEQUENCE tmp002_seq
   INCREMENT 1
   START 1;
   
ALTER TABLE tmp002_seq OWNER TO auctionminister;

CREATE TABLE tmp002 
(
  USERID SMALLINT NOT NULL DEFAULT 0,
  BATCHID NUMERIC(20) NOT NULL DEFAULT nextval('tmp002_seq'::regclass),
  UPDT date NOT NULL,
  CONSTRAINT PK_TMP002 PRIMARY KEY (BATCHID) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;


ALTER TABLE tmp002 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE tmp002;
DROP SEQUENCE tmp002_seq;

