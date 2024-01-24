package com.tsdv.tvm.db.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.tsdv.tvm.db.dbms.DBManager;
import com.tsdv.tvm.db.dbms.InMemoryDBManager;

/**
 * Class for managing the station
 */
public class StationDBManager {
    private final DBManager dbManager;
    public static final String TABLE_NAME = "Station";
    
    private static StationDBManager stationDBManager;
    
    private StationDBManager() {
        dbManager = InMemoryDBManager.instance();
    }
    
    public static StationDBManager instance() {
    	if (stationDBManager == null) {
    		stationDBManager = new StationDBManager();
    		
    		stationDBManager.clean();
    		stationDBManager.init();
    	}
    	return stationDBManager;
    }

    public void init() {
        addStation(new Station("Kipling", 0));
        addStation(new Station("Islington", 4));
        addStation(new Station("Royal York", 7));
        addStation(new Station("Old Mill", 18));
        addStation(new Station("Jane", 21));
        addStation(new Station("Runnymede", 30));
        addStation(new Station("High Park", 32));
        addStation(new Station("Keele", 46));
        addStation(new Station("Dundas West", 49));
        addStation(new Station("Lansdowne", 55));
        addStation(new Station("Dufferine", 79));
        addStation(new Station("Ossington", 81));
        
		/*
		 * addStation(new Station("Christie", 145)); addStation(new Station("Barthurst",
		 * 152)); addStation(new Station("Spadina", 160)); addStation(new
		 * Station("St George", 166)); addStation(new Station("Bay", 174));
		 * addStation(new Station("Bloog Yonge", 179)); addStation(new
		 * Station("Sherbourne", 191)); addStation(new Station("Castie Frank", 199));
		 * addStation(new Station("Broadview", 213)); addStation(new Station("Chester",
		 * 220)); addStation(new Station("Pape", 229)); addStation(new
		 * Station("Donlands", 238)); addStation(new Station("Greenwood", 246));
		 * addStation(new Station("Coxwell", 254)); addStation(new Station("Woodbine",
		 * 265)); addStation(new Station("Main Street", 278)); addStation(new
		 * Station("Victoria Park", 297)); addStation(new Station("Warden", 333));
		 * addStation(new Station("Kennedy", 374));
		 */
    }

    public void clean() {
        List<Properties> rows = dbManager.get(TABLE_NAME);
        for (Properties row : rows) {
            dbManager.delete(TABLE_NAME, row);
        }
    }

    private void addStation(Station station) {
        Properties stationProperties = new Properties();

        stationProperties.put(
                Station.VARIABLE_NAME_NAME,
                station.name);

        stationProperties.put(
                Station.VARIABLE_NAME_STATION_POSITION_ON_SUBWAY_LINE,
                String.valueOf(station.stationPositionOnSubwayLine));

        dbManager.insert(TABLE_NAME, stationProperties);
    }


    /**
     * Get all stations
     * @return list of stations on databases
     */
    public List<Station> getAllStations() {
        List<Station> allStations = new ArrayList<>();
        List<Properties> rows = dbManager.get(TABLE_NAME);
        for (Properties row : rows) {
            double stationPlace = Double.parseDouble(
                    row.getProperty(Station.VARIABLE_NAME_STATION_POSITION_ON_SUBWAY_LINE));
            String name = row.getProperty(Station.VARIABLE_NAME_NAME);
            allStations.add(new Station(name, stationPlace));
        }
        return allStations;
    }
}
