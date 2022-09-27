--// add accounts
-- Migration SQL that makes the change goes here.

INSERT INTO gl001(
            ACTINDEX, ACCOUNTID, ACCOUNTDESC, ACCTTYPE)
    VALUES 
	(16, '4021', 'Missions and Bounties', 2),
	(17, '4022', 'Courier Missions', 2);

--//@UNDO
-- SQL to undo the change goes here.


DELETE FROM gl001ACTINDEX
WHERE ACTINDEX in (16,17);