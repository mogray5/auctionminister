--// First migration.
-- Migration SQL that makes the change goes here.

CREATE SEQUENCE cu001_seq
   INCREMENT 1
   START 1;
ALTER TABLE cu001_seq OWNER TO auctionminister;

CREATE TABLE cu001
(
   USERID SMALLINT NOT NULL DEFAULT 0, 
   CUSTOMERID INTEGER NOT NULL DEFAULT nextval('cu001_seq'::regclass), 
   CUSTNAME character varying(100) NOT NULL DEFAULT '', 
   PPBUYERID character varying(75) NOT NULL DEFAULT '', 
   CUSTEMAIL character varying(75) NOT NULL DEFAULT '', 
   CONTACTPHONE character varying(20) NOT NULL DEFAULT '', 
   SHIPPINGADDRESS character varying(255) NOT NULL DEFAULT '', 
   CONSTRAINT PK_CU001 PRIMARY KEY (CUSTOMERID) USING INDEX TABLESPACE pg_default, 
   CONSTRAINT UK_CU001_1 UNIQUE (USERID, CUSTNAME) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE cu001 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE cu001;
DROP SEQUENCE cu001_seq;
