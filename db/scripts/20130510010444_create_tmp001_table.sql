--// create tmp001 table
-- Migration SQL that makes the change goes here.

CREATE SEQUENCE tmp001_seq
   INCREMENT 1
   START 1;
ALTER TABLE tmp001_seq OWNER TO auctionminister;


CREATE TABLE tmp001 
(
  USERID SMALLINT NOT NULL DEFAULT 0,
  BATCHID NUMERIC(20) NOT NULL DEFAULT 0,
  TRXINDEX NUMERIC(20) NOT NULL DEFAULT nextval('tmp001_seq'::regclass),
  AMINCLUDE INTEGER NOT NULL DEFAULT 1,
  AMTYPE INTEGER NOT NULL DEFAULT 1,
  SONUMBER NUMERIC(20) NOT NULL DEFAULT 0,
  PONUMBER NUMERIC(20) NOT NULL DEFAULT 0,
  CUSTOMERID INTEGER NOT NULL DEFAULT 0,
  VENDORID INTEGER NOT NULL DEFAULT 0,
  ITEMID varchar(50) NOT NULL DEFAULT '',
  AMSTATUS SMALLINT NOT NULL DEFAULT 1,
  AMMSG varchar(100) NOT NULL DEFAULT '',
  TMPDATE date NOT NULL,
  TMPNAME varchar(100) NOT NULL DEFAULT '',
  TMPTYPE varchar(100) NOT NULL DEFAULT '',
  TMPSUBJECT varchar(255) NOT NULL DEFAULT '',
  TMPGROSS DECIMAL NOT NULL DEFAULT 0.00,
  TMPFEE DECIMAL NOT NULL DEFAULT 0.00,
  TMPTOEMAIL varchar(100) NOT NULL DEFAULT '',
  TMPSHIPADDRESS varchar(255) NOT NULL DEFAULT '',
  TMPITEMTITLE varchar(255) NOT NULL DEFAULT '',
  TMPITEMID varchar(100) NOT NULL DEFAULT '',
  TMPSHIPAMT DECIMAL NOT NULL DEFAULT 0.00,
  TMPINSURANCEAMT DECIMAL NOT NULL DEFAULT 0.00,
  TMPTAXAMT DECIMAL NOT NULL DEFAULT 0.00,
  TMPBUYERID varchar(100) NOT NULL DEFAULT '',
  TMPITEMURL varchar(255) NOT NULL DEFAULT '',
  TMPREFTXNID varchar(100) NOT NULL DEFAULT '',
  TMPPRICE DECIMAL DEFAULT NULL,
  TMPQUANTITY DECIMAL DEFAULT NULL,
  CONSTRAINT PK_TMP001 PRIMARY KEY (TRXINDEX) USING INDEX TABLESPACE pg_default,
  CONSTRAINT uk_TMP001_1 UNIQUE (TMPREFTXNID )
) 
WITH (
  OIDS = FALSE
)
;

CREATE INDEX idx_tmp001_1 ON tmp001 (USERID);
CREATE INDEX idx_tmp001_2 ON tmp001 (BATCHID);

ALTER TABLE tmp001 OWNER TO auctionminister;

--//@UNDO
-- SQL to undo the change goes here.

DROP INDEX idx_tmp001_1;
DROP INDEX idx_tmp001_2;
DROP TABLE tmp001;
DROP SEQUENCE tmp001_seq;