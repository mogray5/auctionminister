--// add gl metadata
-- Migration SQL that makes the change goes here.

INSERT INTO gl002(
            ACCTTYPE, ACCTTYPEDESC)
    VALUES 
    (1, 'Accounts Receivable'),
    (2, 'Cost of Goods Sold'),
    (3, 'Inventory'),
    (4, 'Income'),
    (5, 'Expense'),
    (7, 'Non-Merch Charges');    
    
INSERT INTO gl001(
            ACTINDEX, ACCOUNTID, ACCOUNTDESC, ACCTTYPE)
    VALUES 
    (1, '1000', 'Cash', 6),
	(2, '1200', 'Inventory', 6),
	(3, '4000', 'Sales of Goods', 2),
	(4, '4300', 'Cost of Sales', 4),
	(5, '4030', 'Freight Charges', 7),
	(6, '4360', 'Freight Insurance Charges', 2),
	(7, '1201', 'Inventory Offset', 6),
	(8, '4330', 'Purchase Returns and Allowances', 2),
	(9, '4020', 'Sales Returns and Allowances', 4),
	(10, '4040', 'Brokers Fee', 7),
	(11, '4041', 'Contract Brokers Fee', 7),
	(12, '4060', 'Transaction Tax', 7),
	(13, '4050', 'Other Costs', 7),
	(14, '4070', 'Manufacturing Fees', 7),
	(15, '4080', 'Blueprint Research', 7);
		
	
--//@UNDO
-- SQL to undo the change goes here.

	delete from gl001;
	delete from gl002;
	