package com.tsdv.tvm.ui;

import java.util.List;

import com.tsdv.tvm.controller.StationController;
import com.tsdv.tvm.db.orm.Station;
import com.tsdv.tvm.util.Constant;

/**
 * @author TrangNTT Jan 2024
 */

public class BuyOneWayTicketScreen extends BuyFareCertificateScreen {
	List<Station> stations;
	
	int startStation;
	int endStation;
	
	public BuyOneWayTicketScreen(MainScreen mainScreen){
		super(mainScreen);
	}
	
	@Override
	public void display(){
		System.out.println("-----------------");
		System.out.println("BUY ONE-WAY TICKET");
		
		//to be modified if you want
		displayStations();
		
		if (acceptInputs() == false)
			return;
		
		//
		System.out.println("Need to write more code to proceed next actions...");
	}

	private void displayStations(){
		StationController controller = new StationController();
		stations = controller.getAllStationsWithDistance();
		
		int i = 1;
		for (Station station : stations) {
			System.out.println(i + ". " + station.toString());
			i++;
		}
	}
	
	/**
	 * 
	 * @return true when completing all inputs, false when cancel at the middle
	 */
	protected boolean acceptInputs() {
		startStation = -1;
		endStation = -1;
		numberOfFareCertificates = -1;
		
		while (startStation <= 0 || startStation > stations.size()) {
			if (startStation == 0) {
				mainScreen.display();
				return false;
			}
				
			System.out.print("Enter the number of start station (0 for Main Screen):  ");
			startStation = console.nextInt();
		}
		
		while (endStation <= 0 || endStation > stations.size() || startStation == endStation) {
			if (endStation == 0) {
				mainScreen.display();
				return false;
			}
			
			System.out.print("Enter the number of end station (0 for Main Screen):  ");
			endStation = console.nextInt();
		}
		
		if (!acceptNumberOfFareCertificates(Constant.MAX_NUMBER_OF_TICKETS))
			return false;
		
		return true;
	}
}
