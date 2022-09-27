--// add income acct to ev001
-- Migration SQL that makes the change goes here.

ALTER TABLE eve001 ADD INCACTINDEX INTEGER DEFAULT NULL;

--//@UNDO
-- SQL to undo the change goes here.

ALTER TABLE eve001 DROP INCACTINDEX;