package com.tsdv.tvm.ui;

import com.tsdv.tvm.util.Constant;

/**
 * @author TrangNTT Jan 2024
 */

public class Buy24hTicketScreen extends BuyFareCertificateScreen {

	
	public Buy24hTicketScreen(MainScreen mainScreen){
		super(mainScreen);
	}
	
	@Override
	public void display(){
		System.out.println("-----------------");
		System.out.println("BUY 24H TICKET");
		
		//to be modified if you have a controller class
		displayFare();
		
		acceptInputs();
		
		System.out.println("Need to write more code to proceed next actions...");
	}
	
	private void displayFare() {
		//Need to be modified if you get from DB
		System.out.println("Price for a 24h ticket is " + Constant.TWENTY_FOUR_HOUR_TICKET_PRICE);
	}
	
	/**
	 * 
	 * @return true when completing all inputs, false when cancel at the middle
	 */
	@Override
	protected boolean acceptInputs() {
		if (!acceptNumberOfFareCertificates(Constant.MAX_NUMBER_OF_TICKETS))
			return false;
		
		return true;
	}
}
