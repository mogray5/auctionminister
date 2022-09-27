--// create sy006 table
-- Migration SQL that makes the change goes here.

CREATE TABLE sy006 
(
	KEYID INTEGER NOT NULL DEFAULT 0,  
	VCODE varchar(100) NOT NULL DEFAULT '',
 	CONSTRAINT PK_SY006 PRIMARY KEY (KEYID, VCODE) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;

ALTER TABLE sy006 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE sy006;