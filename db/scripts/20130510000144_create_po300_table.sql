--// create po300 table
-- Migration SQL that makes the change goes here.

CREATE TABLE po300 
(
  USERID SMALLINT NOT NULL DEFAULT 0,
  DOCID NUMERIC(20) NOT NULL DEFAULT 0,
  BATCHID varchar(50) NOT NULL DEFAULT '',
  BATCHDATE date NOT NULL,
  CONSTRAINT PK_PO300 PRIMARY KEY (DOCID) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;

ALTER TABLE po300 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.


DROP TABLE po300;