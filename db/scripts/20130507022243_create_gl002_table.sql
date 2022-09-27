--// create gl002 table
-- Migration SQL that makes the change goes here.

CREATE TABLE gl002 
(
  ACCTTYPE SMALLINT NOT NULL DEFAULT 0,
  ACCTTYPEDESC varchar(255) NOT NULL DEFAULT '',
  CONSTRAINT PK_GL002 PRIMARY KEY (ACCTTYPE) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;

ALTER TABLE gl002 OWNER TO auctionminister;


--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE gl002;