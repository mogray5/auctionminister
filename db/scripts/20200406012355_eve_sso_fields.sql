-- // eve sso fields
-- Migration SQL that makes the change goes here.

ALTER TABLE sy006 ADD CLIENTID varchar(100);
ALTER TABLE sy006 ADD APPNAME varchar(100);
ALTER TABLE sy006 ADD SCOPES varchar(1000);

-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE sy006 DROP CLIENTID;
ALTER TABLE sy006 DROP APPNAME;
ALTER TABLE sy006 SCOPES APPNAME;
