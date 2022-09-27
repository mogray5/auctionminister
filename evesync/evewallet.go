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
	"encoding/xml"
	"io/ioutil"
	"log"
	"net/http"
)

type WalletResult struct {
	XMLName     xml.Name    `xml:"eveapi"`
	CurrentTime string      `xml:"currentTime"`
	Row         []WalletRow `xml:"result>rowset>row"`
}

type WalletRow struct {
	TxDateTime  string `xml:"transactionDateTime,attr"`
	TxId        string `xml:"transactionID,attr"`
	Qty         string `xml:"quantity,attr"`
	TypeName    string `xml:"typeName,attr"`
	TypeId      string `xml:"typeID,attr"`
	Price       string `xml:"price,attr"`
	ClientId    string `xml:"clientID,attr"`
	ClientName  string `xml:"clientName,attr"`
	StationId   string `xml:"stationID,attr"`
	StationName string `xml:"stationName,attr"`
	TxType      string `xml:"transactionType,attr"`
	TxFor       string `xml:"transactionFor,attr"`
	JournalId   string `xml:"journalTransactionID,attr"`
}

type JournalResult struct {
	XMLName     xml.Name     `xml:"eveapi"`
	CurrentTime string       `xml:"currentTime"`
	Row         []JournalRow `xml:"result>rowset>row"`
}

type JournalRow struct {
	TxDateTime    string `xml:"date,attr"`
	RefId         string `xml:"refID,attr"`
	RefTypeId     string `xml:"refTypeID,attr"`
	OwnerName1    string `xml:"ownerName1,attr"`
	OwnerId1      string `xml:"ownerID1,attr"`
	OwnerName2    string `xml:"ownerName2,attr"`
	OwnerId2      string `xml:"ownerID2,attr"`
	ArgName1      string `xml:"argName1,attr"`
	ArgId1        string `xml:"argID1,attr"`
	Amount        string `xml:"amount,attr"`
	Balance       string `xml:"balance,attr"`
	Reason        string `xml:"reason,attr"`
	TaxReceiverId string `xml:"taxReceiverID,attr"`
	TaxAmount     string `xml:"taxAmount,attr"`
}

type EveWallet struct {
}

func (c EveWallet) ListTransactions(qsAuth string, character string) (WalletResult, error) {

	walletResult := WalletResult{}

	url := *esEveHost +
		WALLET_URL + "?" +
		qsAuth + "&characterID=" + character

	log.Println("Calling URL:", url)

	walletResp, err := http.Get(url)

	if err != nil {
		return walletResult, err
	}

	defer walletResp.Body.Close()

	body, err := ioutil.ReadAll(walletResp.Body)

	err = xml.Unmarshal([]byte(body), &walletResult)

	if err != nil {
		return walletResult, err
	}

	return walletResult, nil

}

func (c EveWallet) ListJournal(qsAuth string, character string) (JournalResult, error) {

	journalResult := JournalResult{}

	url := *esEveHost +
		JOURNAL_URL + "?" +
		qsAuth + "&characterID=" + character

	log.Println("Calling URL:", url)

	journalResp, err := http.Get(url)

	if err != nil {
		return journalResult, err
	}

	defer journalResp.Body.Close()

	body, err := ioutil.ReadAll(journalResp.Body)

	err = xml.Unmarshal([]byte(body), &journalResult)

	if err != nil {
		return journalResult, err
	}

	return journalResult, nil

}
