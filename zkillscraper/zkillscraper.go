package main

import (
	"encoding/json"
	"database/sql/driver"
	"flag"
	"log"
	"os"
	"io/ioutil"
	_ "github.com/lib/pq"
)

type Killmail struct {
	KillID uint64
	SolarSystemID uint64
	KillTime string
	MoonID uint64
	Victim strVictim
	Attackers []Attacker
	Items []Item
	Position Position
	Zkb Zkb
}

type strVictim struct {
	ShipTypeID uint32
	CharacterID uint64
	CharacterName string
	CorporationID uint64
	CorporationName string
	AllianceID uint64
	AllianceName string
	FactionID uint32
	FactionName string
	DamageTaken uint32
}

type Attacker struct {
	CharacterID uint64
	CharacterName string
	CorporationID uint64
	CorporationName string
	AllianceID uint64
	AllianceName string
	FactionID uint32
	FactionName string
	SecurityStatus float32
	DamageDone uint32
	FinalBlow uint64
	WeaponTypeID uint64
	ShipTypeID uint32
}

type Item struct {
	TypeID uint64
	Flag uint32
	QtyDropped uint32
	QtyDestroyed uint32
	Singleton uint32
}

type Position struct {
	Y float64
	X float64
	Z float64
}

type Zkb struct {
	LocationID uint64
	Hash string
	TotalValue float64
	Points uint32
}

var esDbHost = flag.String("dbhost", "localhost", "Host location of database.")
var esDbPort = flag.String("dbport", "5432", "Port database is listening on.")
var esDbUser = flag.String("dbuser", "zkill", "User to connect to database.")
var esDbPwd = flag.String("dbpwd", "xxxx", "Password to connect to database.")
var esDbName = flag.String("dbname", "zkilldb", "Name of database to store kill data.")

func main() {
	
	log.Println("Starting zkillscraper")
	
	flag.Parse()
	
	bytes, err := ioutil.ReadAll(os.Stdin)
	
	if err != nil {
		log.Fatal("Error reading Stdin: ", err)
	}
	
	var mails []Killmail
	
	err = json.Unmarshal(bytes, &mails)
	
	if err != nil {
		log.Fatal("Cannot parse killmails: ", err)
	}
	
	log.Println("Kill mail count is: ", len(mails))
	
	SaveMails(&mails)
	
}

func SaveMails(mails *[]Killmail)  {
	
	if len(*mails) > 0 {
	
		db := Zkilldb{}

		err := db.Open()
	
		if err != nil {
			log.Fatal("Database connection error:  ", err)
		}

		defer db.Close()
	
		var dResult driver.Result
	
		for _,killRow := range *mails {
			
			dResult, err = db.InsertKill(killRow)
			
			if err != nil {
				log.Println("Kill not saved: ", err)
			} else if dResult != nil {
				
				for _, attacker := range killRow.Attackers {
					
					dResult, err = db.InsertAttacker(attacker, killRow.KillID)
					
					if err != nil {
						log.Println("Error saving attacker: ", err)
					}
					
				} 
				
				for _, item := range killRow.Items {
					
					dResult, err = db.InsertDroppedItem(item, killRow.KillID)
					
					if err != nil {
						log.Println("Error saving dropped item: ", err)
					}
				}
				
				log.Println("Logged kill: ", killRow.KillID)
			}
			
			
		}
	
	}
	
}