--// add expense account
-- Migration SQL that makes the change goes here.


INSERT INTO gl001(
	ACTINDEX, ACCOUNTID, ACCOUNTDESC, ACCTTYPE)
	    VALUES 
		(18, '4090', 'Planet Interaction Fees', 7);


--//@UNDO
-- SQL to undo the change goes here.

DELETE FROM gl001ACTINDEX
WHERE ACTINDEX in (18);
