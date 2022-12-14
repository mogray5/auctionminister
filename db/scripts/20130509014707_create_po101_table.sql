--// create po101 table
-- Migration SQL that makes the change goes here.

CREATE SEQUENCE po101_seq
   INCREMENT 1
   START 1;
ALTER TABLE po101_seq OWNER TO auctionminister;

CREATE TABLE po101 
(
  DOCID NUMERIC(20) NOT NULL DEFAULT 0,
  PONUMBER NUMERIC(20) NOT NULL DEFAULT nextval('po101_seq'::regclass),
  VENDORID INTEGER NOT NULL DEFAULT 0,
  SAVED SMALLINT NOT NULL DEFAULT 0,
  PODATE date NOT NULL,
  CONSTRAINT PK_PO101 PRIMARY KEY (DOCID,PONUMBER) USING INDEX TABLESPACE pg_default, 
  CONSTRAINT UK_PO101_1 UNIQUE (PONUMBER) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;

ALTER TABLE po101 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE po101;
DROP SEQUENCE po101_seq;
