--// create sa301 table
-- Migration SQL that makes the change goes here.

CREATE TABLE sa301 
(
  DOCID NUMERIC(20) NOT NULL DEFAULT 0,
  SONUMBER NUMERIC(20) NOT NULL DEFAULT 0,
  CUSTOMERID INTEGER NOT NULL DEFAULT 0,
  PAYPALFEE DECIMAL NOT NULL DEFAULT 0.00,
  SODATE date NOT NULL,
  CONSTRAINT PK_SA301 PRIMARY KEY (DOCID,SONUMBER) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;

ALTER TABLE sa301 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE sa301;
