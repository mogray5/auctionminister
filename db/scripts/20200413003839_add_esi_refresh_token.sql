-- // add esi refresh token
-- Migration SQL that makes the change goes here.

alter table sy001 ADD REFRESHTOKEN VARCHAR DEFAULT NULL;


-- //@UNDO
-- SQL to undo the change goes here.

ALTER TABLE sy001 DROP REFRESHTOKEN;
