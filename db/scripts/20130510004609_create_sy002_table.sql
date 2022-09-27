--// create sy002 table
-- Migration SQL that makes the change goes here.

CREATE TABLE sy002 
(
  USERID SMALLINT NOT NULL DEFAULT 0,
  LOGINTIME timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT PK_SY002 PRIMARY KEY (USERID) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;

ALTER TABLE sy002 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE sy002;