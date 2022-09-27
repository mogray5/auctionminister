--// add report metadata
-- Migration SQL that makes the change goes here.

INSERT INTO sy005(REPORTID, REPORTNAME, REPORTACTION, REPORTFILE)
    VALUES 
    (nextval('sy005_seq'), 'Profit & Loss Report', 'rptprofitloss.action', 'amr_profit_loss'),
    (nextval('sy005_seq'), 'Profit & Loss Monthly Report', 'rptprofitlossmonthly.action', 'amr_profit_loss_monthly'),
    (nextval('sy005_seq'), 'Stock Status', 'rptstockstatus.action', 'amr_stock_status'),
    (nextval('sy005_seq'), 'Sales By Customer Report', 'rptsalesbycustomer.action', 'amr_sales_by_customer'),
    (nextval('sy005_seq'), 'Sales By Item Report', 'rptsalesbyitem.action', 'amr_sales_by_item'),
    (nextval('sy005_seq'), 'Open Sales Order Report', 'rptopensalesorders.action', 'amr_open_sales_orders'),
	(nextval('sy005_seq'), 'Inventory Valuation By Month', 'rptivvaluemonthly.action', 'amr_iv_value_by_month'),
	(nextval('sy005_seq'), 'Ending Cash Balance By Month', 'rptcashvaluemonthly.action', 'amr_cash_value_by_month'),
	(nextval('sy005_seq'), 'Purchases By Vendor Report', 'rptpurchasesbyvendor.action', 'amr_po_by_vendor'),
	(nextval('sy005_seq'), 'Purchases By Item Report', 'rptpurchasesbyitem.action', 'amr_po_by_item'),
	(nextval('sy005_seq'), 'Open Purchase Order Report', 'rptopenpurchaseorders.action', 'amr_open_po_orders'),
	(nextval('sy005_seq'), 'Non-Merchandise Charges Report', 'rptnonmerchcharges.action', 'amr_nonmerch_charges');
	

INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pYr' as paramName,
	'Enter Year' as paramFriendlyName,
	1 as paramType,
	1 as paramDefaultType,
	10 as paramLength,
	'2004' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Profit & Loss Report';	

 INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pYr' as paramName,
	'Enter Year' as paramFriendlyName,
	1 as paramType,
	1 as paramDefaultType,
	10 as paramLength,
	'2013' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Profit & Loss Monthly Report';	

  INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pMonth' as paramName,
	'Enter Month' as paramFriendlyName,
	2 as paramType,
	2 as paramDefaultType,
	0 as paramLength,
	'GetDefaults_MonthList' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Profit & Loss Monthly Report';	
 
 INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pStartDate' as paramName,
	'Enter Starting Date (mm/dd/yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	15 as paramLength,
	'' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Sales By Customer Report';	

  INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pEndDate' as paramName,
	'Enter Ending Date (mm/dd/yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	15 as paramLength,
	'' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Sales By Customer Report';	
 
  INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pStartDate' as paramName,
	'Enter Starting Date (mm/dd/yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	15 as paramLength,
	'' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Sales By Item Report';	
 
INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pEndDate' as paramName,
	'Enter Ending Date (mm/dd/yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	15 as paramLength,
	'' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Sales By Item Report';	
 
INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pStartYear' as paramName,
	'Enter Starting Year (yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	10 as paramLength,
	'2013' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Inventory Valuation By Month';	
 
 INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pEndYear' as paramName,
	'Enter Ending Year (yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	10 as paramLength,
	'2013' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Inventory Valuation By Month';	

 INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pStartYear' as paramName,
	'Enter Starting Year (yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	10 as paramLength,
	'2013' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Ending Cash Balance By Month';	
 
 INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pEndYear' as paramName,
	'Enter Ending Year (yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	10 as paramLength,
	'2013' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Ending Cash Balance By Month';	

   INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pStartDate' as paramName,
	'Enter Starting Date (mm/dd/yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	15 as paramLength,
	'' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Purchases By Vendor Report';	
 
INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pEndDate' as paramName,
	'Enter Ending Date (mm/dd/yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	15 as paramLength,
	'' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Purchases By Vendor Report';	
 
    INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pStartDate' as paramName,
	'Enter Starting Date (mm/dd/yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	15 as paramLength,
	'' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Purchases By Item Report';	
 
INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pEndDate' as paramName,
	'Enter Ending Date (mm/dd/yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	15 as paramLength,
	'' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Purchases By Item Report';	
 
     INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pStartDate' as paramName,
	'Enter Starting Date (mm/dd/yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	15 as paramLength,
	'' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Non-Merchandise Charges Report';	
 
INSERT INTO sy004(
        PARAMID, REPORTID, PARAMNAME, PARAMFRIENDLYNAME, PARAMTYPE, 
        PARAMDEFAULTSTYPE, PARAMLENGTH, PARAMDEFAULTS)
select nextval('sy004_seq') as paramid,
	sy005.REPORTID,
	'pEndDate' as paramName,
	'Enter Ending Date (mm/dd/yyyy)' as paramFriendlyName,
	1 as paramType,
	0 as paramDefaultType,
	15 as paramLength,
	'' as paramDefaults
 from sy005 
 where sy005.REPORTNAME = 'Non-Merchandise Charges Report';	
 
 
--//@UNDO
-- SQL to undo the change goes here.

delete from sy004;
delete from sy005;
SELECT setval('sy004_seq', 1);
SELECT setval('sy005_seq', 1);

