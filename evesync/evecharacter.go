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

type CharacterResult struct {
	XMLName     xml.Name       `xml:"eveapi"`
	CurrentTime string         `xml:"currentTime"`
	Row         []CharacterRow `xml:"result>rowset>row"`
}

type CharacterRow struct {
	Name     string `xml:"name,attr"`
	Id       string `xml:"characterID,attr"`
	CorpName string `xml:"corporationName,attr"`
	CorpId   string `xml:"corporationID,attr"`
}

type EveCharacter struct {
}

func (c EveCharacter) List(qsAuth string) (CharacterResult, error) {

	characterResult := CharacterResult{}

	url := *esEveHost + CHARACTER_URL + "?" + qsAuth

	log.Println("Calling URL:", url)

	charResp, err := http.Get(url)

	if err != nil {
		return characterResult, err
	}

	defer charResp.Body.Close()

	body, err := ioutil.ReadAll(charResp.Body)

	err = xml.Unmarshal([]byte(body), &characterResult)

	if err != nil {
		return characterResult, err
	}

	return characterResult, nil

}
