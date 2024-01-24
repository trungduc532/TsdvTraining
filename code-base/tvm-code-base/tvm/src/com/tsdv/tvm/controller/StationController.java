package com.tsdv.tvm.controller;

import java.util.List;

import com.tsdv.tvm.db.orm.Station;
import com.tsdv.tvm.db.orm.StationDBManager;

public class StationController {
	StationDBManager stationDBManager;
	public StationController() {
		stationDBManager = StationDBManager.instance();
	}
	
	public List<Station> getAllStationsWithDistance() {
		List<Station> stations = stationDBManager.getAllStations();
		Station prevStation = null;
		
		for (Station station : stations) {
			if (prevStation == null)
				prevStation = station;

			station.setPreviousStation(prevStation);
			
			prevStation = station;
		}
		
		return stations;
	}
	
}
