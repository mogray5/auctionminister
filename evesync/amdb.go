/*
 * Copyright (c) 2013 Wayne Gray All rights reserved
 * 
 * This file is part of Auction Minister.
 * 
 * Auction Minister is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Auction Minister is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Auction Minister.  If not, see <http://www.gnu.org/licenses/>.
 */
package main

import (
	"database/sql"
	"database/sql/driver"
	"log"
	"strconv"
	"time"
)

type Amdb struct {
	Db *sql.DB
}

func (c *Amdb) Open() error {

	var err error

	c.Db, err = sql.Open("postgres", "user="+*esDbUser+
		" password="+*esDbPwd+
		" dbname="+*esDbName+
		" host="+*esDbHost+
		" sslmode=disable")

	return err

}

func (c *Amdb) Close() {
	c.Db.Close()
}

func (c Amdb) GetNewBatchId() (uint64, error) {

	qry := `INSERT INTO tmp002 (USERID, UPDT)
		SELECT u.userid, current_timestamp
		FROM sy001 u WHERE u.username=$1`

	_, err := c.Db.Exec(qry, *esAmUser)

	if err != nil {
		log.Fatal("Generate new batch prepare error: ", err)
	}

	var batchId uint64

	err = c.Db.QueryRow("SELECT batchid from tmp002 where updt = (select max(updt) from tmp002)").Scan(&batchId)

	if err != nil {
		log.Fatal("Generate new batch query error: ", err)
	}

	return batchId, nil

}

func (c Amdb) GetKeyList() (*sql.Rows, error) {

	return c.Db.Query("select keyid, vcode from sy006")

}

func (c Amdb) GetAuthQueryString(rows *sql.Rows) (string, error) {

	var keyid uint64
	var vcode string

	if err := rows.Scan(&keyid, &vcode); err != nil {
		return "", err
	}

	log.Println("keyid is: ", keyid)

	return "keyID=" + strconv.FormatUint(keyid, 10) + "&vCode=" + vcode, nil

}

func (c Amdb) InsertWalletTransaction(row WalletRow, batch uint64) (driver.Result, error) {

	qry := `INSERT INTO tmp001(
		USERID, BATCHID, ITEMID,TMPDATE, TMPNAME, TMPTYPE,
		TMPSUBJECT, TMPPRICE, TMPQUANTITY, TMPTOEMAIL, TMPSHIPADDRESS,
		TMPITEMTITLE, TMPITEMID, TMPSHIPAMT, TMPINSURANCEAMT,
		TMPTAXAMT, TMPBUYERID, TMPITEMURL, TMPREFTXNID, AMINCLUDE,
		AMTYPE)
		SELECT u.userid, $1, $2, $3, $4, $5,
		$6, $7, $8, $9, $10, $11, $12, $13,
		$14, $15, $16, $17, $18, $19, $20 
		FROM sy001 u WHERE u.username=$21`

	var aminclude, amtype int

	if row.TxType == "sell" {
		aminclude = 1
		amtype = 0
	} else if row.TxType == "buy" {
		aminclude = 1
		amtype = 1
	} else {
		aminclude = 0
		amtype = 7
	}

	t, price, qty, err := c.ParseWalletRow(row)

	if err != nil {
		return nil, err
	}

	return c.Db.Exec(qry,
		batch,           //BATCHID
		row.TypeName,    //ITEMID
		t,               //TMPDATE		*
		row.ClientName,  //TMPNAME
		row.TxType,      //TMPTYPE
		"",              //TMPSUBJECT
		price,           //TMPPRICE		*
		qty,             //TMPQUANTITY		*		
		"",              //TMPTOEMAIL
		row.StationName, //TMPSHIPADDRESS
		"",              //TMPITEMTITLE
		row.TypeName,    //TMPITEMID
		0,               //TMPSHIPAMT
		0,               //TMPINSURANCEAMT
		0,               //TMPTAXAMT
		row.ClientId,    //TMPBUYERID
		"",              //TMPITEMURL
		row.TxId,        //TMPREFTXNID
		aminclude,       //AMINCLUDE
		amtype,          //AMTYPE
		*esAmUser)

}

func (c Amdb) ParseWalletRow(row WalletRow) (time.Time, float64, float64, error) {

	var t time.Time
	var price, qty float64

	t, err := time.Parse("2006-01-02 15:04:05", row.TxDateTime)

	if err != nil {
		return t, price, qty, err
	}

	price, err = strconv.ParseFloat(row.Price, 64)

	if err != nil {
		return t, price, qty, err
	}

	qty, err = strconv.ParseFloat(row.Qty, 64)

	return t, price, qty, err

}

func (c Amdb) InsertJournalTransaction(row JournalRow) (driver.Result, error) {

	qry := `INSERT INTO tmp003(
            	trandate, refnum, reftype, owner1, owner2, argname1, amount, 
            	balance, userid, isdup)
		SELECT $1, $2, $3, $4, $5,
		$6, $7, $8, u.userid, $9
		FROM sy001 u WHERE u.username=$10`

	t, amount, balance, refTypeId, err := c.ParseJournalRow(row)

	if err != nil {
		return nil, err
	}

	return c.Db.Exec(qry,
		t,              //trandate	*
		row.RefId,      //refnum
		refTypeId,  	//reftype	*
		row.OwnerName1, //owner1
		row.OwnerName2, //owner2
		row.ArgName1,   //argname1
		amount,         //amount	*
		balance,        //balance	*	
		0,              //isdup
		*esAmUser)

}

func (c Amdb) InsertRefType(row RefTypeRow) (driver.Result, error) {

	qry := `INSERT INTO eve001(
            	reftypeid, reftypename)
		VALUES($1, $2)`

	refTypeId, err := strconv.ParseInt(row.Id, 10, 64)

	if err != nil {
		return nil, err
	}

	return c.Db.Exec(qry,
		refTypeId,      //reftypeid	*
		row.Name)       //reftypename   *

}

func (c Amdb) ParseJournalRow(row JournalRow) (time.Time, float64, float64, int64, error) {

	var t time.Time
	var amount, balance float64
	var refTypeId int64

	t, err := time.Parse("2006-01-02 15:04:05", row.TxDateTime)

	if err != nil {
		return t, amount, balance, refTypeId, err
	}

	amount, err = strconv.ParseFloat(row.Amount, 64)

	if err != nil {
		return t, amount, balance, refTypeId, err
	}

	balance, err = strconv.ParseFloat(row.Balance, 64)

	refTypeId, err = strconv.ParseInt(row.RefTypeId, 10, 64)

	if err != nil {

		return t, amount, balance, refTypeId, err

	}

	return t, amount, balance, refTypeId, err

}

func (c Amdb) MarkDuplicateWalletItems() (driver.Result, error) {

	qry := `UPDATE tmp001
	     	SET AMSTATUS = 3,
	     	AMMSG = 'Duplicate'
	     	WHERE TMPREFTXNID IN (SELECT EVETXNID FROM sa102)
	     	OR TMPREFTXNID IN (SELECT EVETXNID FROM po102)
	     	OR TMPREFTXNID IN (SELECT EVETXNID FROM po302)
	     	OR TMPREFTXNID IN (SELECT EVETXNID FROM sa302)`

	return c.Db.Exec(qry)

}

func (c Amdb) MarkDuplicateJournalItems() (driver.Result, error) {

	qry := `UPDATE tmp003
	     	SET ISDUP = 1
	     	WHERE REFNUM IN
	     	(SELECT REFNUM FROM fe001)`

	return c.Db.Exec(qry)

}

func (c Amdb) RefTypesNeedSync() (bool, error) {

	qry := `SELECT COUNT(1) as RefCount FROM eve001`

	var refCount int32

	err := c.Db.QueryRow(qry).Scan(&refCount)

	if err != nil {
		return false, err
	}

	return refCount==0, nil

}

func (c Amdb) GetRefTypeIgnoreList() (*sql.Rows, error) {

	qry := `SELECT refTypeId
			FROM eve001
			WHERE ignore = true`

	return c.Db.Query(qry)
}
