--// kills table
-- Migration SQL that makes the change goes here.

CREATE SEQUENCE kills_seq
   INCREMENT 1
   START 1;
ALTER TABLE kills_seq OWNER TO zkill;

CREATE TABLE kills
(
   ID BIGINT NOT NULL DEFAULT nextval('kills_seq'::regclass),
   KILL_ID BIGINT NOT NULL, 
   SYSTEM_ID BIGINT NOT NULL,
   KILL_TIME character varying(50) NOT NULL DEFAULT '',
   MOON_ID BIGINT,
   VIC_SHIP_TYPE INTEGER,
   VIC_CHAR_ID BIGINT,
   VIC_CHAR_NAME character varying(100) NOT NULL DEFAULT '',
   VIC_CORP_ID BIGINT, 
   VIC_CORP_NAME character varying(100) NOT NULL DEFAULT '', 
   VIC_ALLIANCE_ID BIGINT,
   VIC_ALLIANCE_NAME character varying(100) NOT NULL DEFAULT '',
   VIC_FAC_ID BIGINT,
   VIC_FAC_NAME character varying(100) NOT NULL DEFAULT '',
   VIC_DAMAGE_TAKEN BIGINT,
   ZKB_LOCATION BIGINT,
   ZKB_HASH character varying(100),
   ZKB_VALUE DECIMAL,
   ZKB_POINTS INTEGER,
   CONSTRAINT PK_KILLS PRIMARY KEY (ID) USING INDEX TABLESPACE pg_default, 
   CONSTRAINT UK_KILLS_1 UNIQUE (KILL_ID) USING INDEX TABLESPACE pg_default
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE kills OWNER TO zkill;

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE kills;
DROP SEQUENCE kills_seq;
