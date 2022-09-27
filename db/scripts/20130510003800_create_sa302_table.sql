--// create sa302 table
-- Migration SQL that makes the change goes here.

CREATE TABLE sa302 
(
  SONUMBER NUMERIC(20) NOT NULL DEFAULT 0,
  ITEMID varchar(50) NOT NULL DEFAULT '',
  SALEPRICE DECIMAL NOT NULL DEFAULT 0.00,
  QTYSOLD BIGINT NOT NULL DEFAULT 0,
  EVETXNID varchar(100) DEFAULT '',
 CONSTRAINT PK_SA302 PRIMARY KEY (SONUMBER,ITEMID) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;

ALTER TABLE sa302 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE sa302;
