--// add month year rows
-- Migration SQL that makes the change goes here.

INSERT INTO gl003 (userid, actindex, yr, creditbalance, debitbalance)
VALUES (1, 18, 2016, 0, 0);

INSERT INTO gl004 (userid, actindex, mth, yr, creditbalance, debitbalance)
VALUES (1, 18, 5, 2016, 0, 0);

--//@UNDO
-- SQL to undo the change goes here.

DELETE FROM gl003 where actindex = 18 and yr = 2016;
DELETE FROM gl004 where actindex = 18 and yr = 2016 and mth=5;
