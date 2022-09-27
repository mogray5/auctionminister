package main

import (
	"database/sql"
	"database/sql/driver"
	"log"
	"strings"
)

type Zkilldb struct {
	Db *sql.DB
}

func (c * Zkilldb) Open() error {
	
	var err error

	c.Db, err = sql.Open("postgres", 
		"user="+*esDbUser+
		" password="+*esDbPwd+
		" dbname="+*esDbName+
		" host="+*esDbHost+
		" port="+*esDbPort+
		" sslmode=disable")
		
		
	return err
}

func (c *Zkilldb) Close() {
	c.Db.Close()
}

func (c Zkilldb) InsertAttacker(row Attacker, kill_id uint64) (driver.Result, error) {

	qry := `INSERT INTO attackers(
		kill_id, char_id, char_name, 
		corp_id, corp_name, 
		alliance_id, alliance_name,
		fac_id, fac_name,
		sec_status, damage_done,
		final_blow, weapon_type, ship_type)
		VALUES($1, $2, $3, $4, $5, $6, $7,
		$8, $9, $10, $11, $12, $13, $14)`
		
	return c.Db.Exec(qry,
		kill_id, 
		row.CharacterID,
		row.CharacterName,
		row.CorporationID,
		row.CorporationName,
		row.AllianceID,
		row.AllianceName,
		row.FactionID,
		row.FactionName,
		row.SecurityStatus,
		row.DamageDone, 
		row.FinalBlow,
		row.WeaponTypeID,
		row.ShipTypeID)
}

func (c Zkilldb) InsertDroppedItem(row Item, kill_id uint64) (driver.Result, error) {

	qry := `INSERT INTO dropped_items(
		kill_id, type_id, flag,
		qty_dropped, qty_destroyed,
		singleton)
		VALUES($1, $2, $3, $4, $5, $6)`
		
	return c.Db.Exec(qry,
		kill_id, 
		row.TypeID,
		row.Flag,
		row.QtyDropped,
		row.QtyDestroyed,
		row.Singleton)
}

func (c Zkilldb) InsertKill(row Killmail) (driver.Result, error) {


	var killID uint64
	killID = 0

	err := c.Db.QueryRow("select kill_id as killID from kills where kill_id = $1", row.KillID).Scan(&killID)

	if err != nil {
		
		if strings.Contains(err.Error(), "no rows") {
			killID = 0
		} else {
			log.Println("Error validating kill: ", err)
			return nil, err
		}
	}

	if killID > 0 {
		log.Println("Kill already logged: ", killID)
		return nil, nil
	}


	qry := `INSERT INTO kills(
		kill_id,  system_id, kill_time,
		moon_id, vic_ship_type,
		vic_char_id, vic_char_name,
		vic_corp_id, vic_corp_name,
		vic_alliance_id, vic_alliance_name,
		vic_fac_id, vic_fac_name,
		vic_damage_taken,
		zkb_location, zkb_hash,
		zkb_value, zkb_points)
		VALUES($1, $2, $3, $4, $5, $6,
		$7, $8, $9, $10, $11, $12,
		$13, $14, $15, $16, $17, $18)`
	
	//log.Println("Faction ID is: ", row.Victim.FactionID)
	//log.Println("Victim Alliance is: ", row.Victim.AllianceName)
		
	return c.Db.Exec(qry,
		row.KillID,
		row.SolarSystemID,
		row.KillTime,
		row.MoonID,
		row.Victim.ShipTypeID,
		row.Victim.CharacterID,
		row.Victim.CharacterName,
		row.Victim.CorporationID,
		row.Victim.CorporationName,
		row.Victim.AllianceID,
		row.Victim.AllianceName,
		row.Victim.FactionID,
		row.Victim.FactionName,
		row.Victim.DamageTaken,
		row.Zkb.LocationID,
		row.Zkb.Hash,
		row.Zkb.TotalValue,
		row.Zkb.Points)
}

/*
func (c Zkilldb) GetCharacter(charId uint64) (*sql.Rows, error) {

	qry := `SELECT ID, CHAR_ID, CHAR_NAME, 
		CORP_ID,CORP_NAME, ALLIANCE_ID, 
		FACTION_ID, SEC_STATUS
		FROM characters
		WHERE char_id = $1`

	return c.Db.Query(qry, charId)
}
*/