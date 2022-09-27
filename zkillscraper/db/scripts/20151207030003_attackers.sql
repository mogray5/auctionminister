--// attackers
-- Migration SQL that makes the change goes here.

CREATE SEQUENCE attackers_seq
   INCREMENT 1
   START 1;
ALTER TABLE attackers_seq OWNER TO zkill;

CREATE TABLE attackers
(
   ID INTEGER NOT NULL DEFAULT nextval('attackers_seq'::regclass),
   KILL_ID BIGINT NOT NULL REFERENCES kills(kill_id),
   CHAR_ID BIGINT NOT NULL,
   CHAR_NAME character varying(100) NOT NULL DEFAULT '',
   CORP_ID BIGINT, 
   CORP_NAME character varying(100) NOT NULL DEFAULT '', 
   ALLIANCE_ID BIGINT,
   ALLIANCE_NAME character varying(100) NOT NULL DEFAULT '',
   FAC_ID BIGINT,
   FAC_NAME character varying(100) NOT NULL DEFAULT '',
   SEC_STATUS  DECIMAL, 
   DAMAGE_DONE INTEGER, 
   FINAL_BLOW INTEGER NOT NULL DEFAULT '0', 
   WEAPON_TYPE BIGINT,
   SHIP_TYPE INTEGER,
   CONSTRAINT PK_ATTACKERS PRIMARY KEY (ID) USING INDEX TABLESPACE pg_default, 
   CONSTRAINT UK_ATTACKERS_1 UNIQUE (KILL_ID, CHAR_ID) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE attackers OWNER TO zkill;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE attackers;
DROP SEQUENCE attackers_seq;