--// add column eve001
-- Migration SQL that makes the change goes here.

ALTER TABLE eve001 ADD IGNORE BOOLEAN DEFAULT FALSE;

--//@UNDO
-- SQL to undo the change goes here.

ALTER TABLE eve001 DROP IGNORE;
