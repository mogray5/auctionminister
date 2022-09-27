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

type RefTypeResult struct {
	XMLName     xml.Name       `xml:"eveapi"`
	CurrentTime string         `xml:"currentTime"`
	Row         []RefTypeRow `xml:"result>rowset>row"`
}

type RefTypeRow struct {
	Id     string `xml:"refTypeID,attr"`
	Name       string `xml:"refTypeName,attr"`
}

type EveRefType struct {
}


func (c EveRefType) List() (RefTypeResult, error) {

	refTypeResult := RefTypeResult{}

	url := *esEveHost + REFTYPE_URL

	log.Println("Calling URL:", url)

	apiResp, err := http.Get(url)

	if err != nil {
		return refTypeResult, err
	}

	defer apiResp.Body.Close()

	body, err := ioutil.ReadAll(apiResp.Body)

	err = xml.Unmarshal([]byte(body), &refTypeResult)

	if err != nil {
		return refTypeResult, err
	}

	return refTypeResult, nil

}
