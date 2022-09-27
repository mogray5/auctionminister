--// create sy003 table
-- Migration SQL that makes the change goes here.

CREATE TABLE sy003 
(
  MTH SMALLINT NOT NULL DEFAULT 0,
  MONTHNAME varchar(25) NOT NULL DEFAULT '',
  CONSTRAINT PK_SY003 PRIMARY KEY (MTH) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;

ALTER TABLE sy003 OWNER TO auctionminister;

INSERT INTO sy003 (MTH, MONTHNAME) VALUES  
	(1,'January'),
	(2,'February'),
	(3,'March'),
	(4,'April'),
	(5,'May'),
	(6,'June'),
	(7,'July'),
	(8,'August'),
	(9,'September'),
	(10,'October'),
	(11,'November'),
	(12,'December');

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE sy003;