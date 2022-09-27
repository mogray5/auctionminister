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
	"flag"
	_ "github.com/lib/pq"
	"log"
	"runtime"
	"strconv"
	"strings"
)

var esEveHost = flag.String("evehost", "https://api.eveonline.com", "URL for EVE API without ending slash.")
var esDbHost = flag.String("dbhost", "localhost", "Host location of PostgresSQL database.")
var esDbUser = flag.String("dbuser", "auctionminister", "User to connect to Auction Minister database.")
var esDbPwd = flag.String("dbpwd", "xxxx", "Password to connect to Auction Minister database.")
var esDbName = flag.String("dbname", "amdb", "Name of Auction Minister databaes.")
var esAmUser = flag.String("amuser", "xxxx", "Application user to link to when saving transactions.")
var esImpWallet = flag.Bool("importwallet", true, "Flag to turn on/off wallet imports")
var esImpJournal = flag.Bool("importjournal", true, "Flag to turn on/off wallet imports")
var esImpRefTypes = flag.Bool("importreftypes", true, "Flag to turn on/off ReftType syncing")

func main() {

	log.Println("Starting sync")

	runtime.GOMAXPROCS(runtime.NumCPU())
	flag.Parse()

	db := Amdb{}

	err := db.Open()

	if err != nil {
		log.Fatal("Database connection error:  ", err)
	}

	defer db.Close()

	//Get list of keys
	keyList, keyErr := db.GetKeyList()

	if keyErr != nil {
		log.Fatal("API Key fetch error:  ", keyErr)
	}

	defer keyList.Close()

	for keyList.Next() {

		qsAuth, err := db.GetAuthQueryString(keyList)

		if err != nil {
			log.Fatal("Auth string return error: ", err)
		}

		characters := EveCharacter{}

		characterResult, err := characters.List(qsAuth)

		if err != nil {
			log.Fatal("Get characters error: ", err)
		}

		for charRow := range characterResult.Row {

			if *esImpWallet {

				doWalletImport(qsAuth, &characterResult.Row[charRow], &db)
			}

			if *esImpJournal {

				doJournalImport(qsAuth, &characterResult.Row[charRow], &db)

			}

		}

		if *esImpRefTypes {

			doRefTypeSync(&db)

		}

	}
}

func doWalletImport(qsAuth string, character *CharacterRow, db *Amdb) {

	//Download wallet
	wallet := EveWallet{}
	var batchId uint64
	batchId = 0

	walletResult, err := wallet.ListTransactions(qsAuth, character.Id)

	if err != nil {
		log.Fatal("Wallet get error: ", err)
	}

	//Start a new batch if needed
	if len(walletResult.Row) > 0 && batchId == 0 {

		batchId, err = db.GetNewBatchId()

		if err != nil {
			log.Fatal("Error creating batch: ", err)
		}

	}

	addCount, errorCount, dupCount := 0, 0, 0

	for walletRow := range walletResult.Row {

		_, err := db.InsertWalletTransaction(walletResult.Row[walletRow], batchId)

		if err != nil {
			if !strings.Contains(err.Error(), "duplicate") {
				log.Println("Error adding wallet transaction: ", err)
				errorCount++
			} else {
				dupCount++
			}
		} else {
			addCount++
		}

	}

	LogResults(addCount, errorCount, dupCount)

	_, err = db.MarkDuplicateWalletItems()

	if err != nil {
		log.Println("Error marking wallet duplicates: ", err)
	}

}

func doJournalImport(qsAuth string, character *CharacterRow, db *Amdb) {

	//Download Journal
	wallet := EveWallet{}

	journalResult, err := wallet.ListJournal(qsAuth, character.Id)

	if err != nil {
		log.Fatal("Journal get error: ", err)
	}

	addCount, errorCount, dupCount := 0, 0, 0

	ignoreList, err := db.GetRefTypeIgnoreList()
	defer ignoreList.Close()

	ignoreMap := make(map[uint64]string)

	if err != nil {

		log.Println("Error searching ignore list: ", err)
		log.Println("Ignore transaction feature will not be used.")
	} else {
		//Map ref types that will be ignored
		var refTypeId uint64

		for ignoreList.Next() {
			if err := ignoreList.Scan(&refTypeId); err != nil {
				log.Println("Error creating ignore map: ", err)
				break
			}
			log.Println("Adding reftype to ignore list: ", refTypeId)
			ignoreMap[refTypeId] = "ignore"
			log.Println("Map check: ", ignoreMap[refTypeId], len(ignoreMap))
		}

	}

	for journalRow := range journalResult.Row {

		//log.Println("Ignore comaritor is: ", journalResult.Row[journalRow].RefTypeId)
		//log.Println("Ignore list value: ", ignoreMap[string(journalResult.Row[journalRow].RefTypeId)])
		//log.Println("Ignore list value2: ", ignoreMap[journalResult.Row[journalRow].RefTypeId])
		//log.Println("Ignore list value3: ", len(ignoreMap))

		refCompare, err := strconv.ParseUint(journalResult.Row[journalRow].RefTypeId, 10, 64)

		if err != nil {
			log.Println("Error parsing return reftype: ", err)
		} else {

			if ignoreMap[refCompare] != "ignore" {

				_, err := db.InsertJournalTransaction(journalResult.Row[journalRow])

				if err != nil {
					if !strings.Contains(err.Error(), "duplicate") {
						log.Println("Error adding journal transaction: ", err)
						errorCount++
					} else {
						dupCount++
					}
				} else {
					addCount++
				}
			}
		}
	}

	_, err = db.MarkDuplicateJournalItems()

	if err != nil {
		log.Println("Error marking journal duplicates: ", err)
	}

	LogResults(addCount, errorCount, dupCount)

}

func doRefTypeSync(db *Amdb) {

	shouldSync, err := db.RefTypesNeedSync()

	if err != nil {
		log.Println("Error getting reftype count: ", err)
	}

	if shouldSync {

		refType := EveRefType{}
		refTypeResult, err := refType.List()

		if err != nil {
			log.Fatal("Reftype get error: ", err)
		}

		addCount, errorCount := 0, 0

		for refTypeRow := range refTypeResult.Row {

			_, err := db.InsertRefType(refTypeResult.Row[refTypeRow])

			if err != nil {
				log.Println("Error adding Reftype: ", err)
				errorCount++
			} else {
				addCount++
			}

		}

		LogResults(addCount, errorCount, 0)

	}

}

func LogResults(addCount int, errorCount int, dupCount int) {
	log.Print("---------------------------------------------------------")
	log.Print("RESULTS")
	log.Print("---------------------------------------------------------")
	log.Print("Records Added: ", addCount)
	log.Print("Record Errors: ", errorCount)
	log.Print("Record Duplicates: ", dupCount)
	log.Print("---------------------------------------------------------")

}
