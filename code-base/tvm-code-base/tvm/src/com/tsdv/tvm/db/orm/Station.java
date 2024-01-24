package com.tsdv.tvm.db.orm;

/**
 * Station fields
 */
public class Station {
    public static final String VARIABLE_NAME_NAME = "name";
    public static final String VARIABLE_NAME_STATION_POSITION_ON_SUBWAY_LINE = "stationPositionOnSubwayLine";
    public static final double BASE_DISTANCE = 5.0;
    public static final double BASE_FARE = 1.9;
    public static final double STEP_DISTANCE = 2.0;
    public static final double STEP_FARE = 0.4;

    String name;
    double stationPositionOnSubwayLine;
    Station previousStation;

    public Station(String name, double stationPositionOnSubwayLine) {
        this.name = name;
        this.stationPositionOnSubwayLine = stationPositionOnSubwayLine;
    }
    
    @Override
    public String toString() {
        return name + ":\t" + stationPositionOnSubwayLine;
    }
    
    public String getStationInfoWithPreviousDistance() {
		  if (previousStation == null) 
			  return name + ":\t" + stationPositionOnSubwayLine + "\t||\t"; 
		  else 
			  return name + ":\t" + stationPositionOnSubwayLine + "\t||\t" 
			  			  + distanceInKm(previousStation);
		 
    }
    
    /**
     * @param anotherStation
     * Calculate the distance between the current station and the argument one
     */
    public double distanceInKm(Station anotherStation) {
        return Math.abs(
                this.stationPositionOnSubwayLine
                        - anotherStation.stationPositionOnSubwayLine);
    }
    
    public void setPreviousStation(Station previousStation) {
    	this.previousStation = previousStation;
    }
    
    /**
     * Calculate the fare between the current station and the argument one
     * @param anotherStation
     * @return Fare between the current station and ${anotherStation}
     */
    public double fare(Station anotherStation) {
    	double fare = 0.0;
    	double distance = distanceInKm(anotherStation);
    	
    	double steps = (distance - BASE_DISTANCE) / STEP_DISTANCE;
    	
    	//round up to
    	long rounded_steps = (steps>0)?
    								(Math.round(steps + 0.5)):0;
    	
    	fare = BASE_FARE + rounded_steps * STEP_FARE;
    	
    	return fare;
    }
}
