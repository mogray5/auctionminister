--// create fe001 table
-- Migration SQL that makes the change goes here.

CREATE TABLE fe001 
(
  TRANDATE date NOT NULL,
  REFNUM varchar(45) NOT NULL,
  REFTYPE varchar(45) DEFAULT NULL,
  OWNER1 varchar(45) DEFAULT NULL,
  OWNER2 varchar(45) DEFAULT NULL,
  ARGNAME1 varchar(100) DEFAULT NULL,
  AMOUNT decimal(15,2) NOT NULL DEFAULT '0.00',
  BALANCE decimal(15,2) NOT NULL DEFAULT '0.00',
  USERID SMALLINT DEFAULT NULL
) 
WITH (
  OIDS = FALSE
)
;

ALTER TABLE fe001 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE fe001;
