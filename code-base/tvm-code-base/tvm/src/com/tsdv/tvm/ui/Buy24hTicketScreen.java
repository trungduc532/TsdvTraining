package com.tsdv.tvm.ui;

import com.tsdv.tvm.util.Constant;

/**
 * @author TrangNTT Jan 2024
 */

public class Buy24hTicketScreen extends BuyFareCertificateScreen {

	private PayOrderScreen payOrderScreen;

	public Buy24hTicketScreen(MainScreen mainScreen){
		super(mainScreen);
	}
	
	@Override
	public void display(){
		System.out.println("-----------------");
		System.out.println("BUY 24H TICKET");
		
		//to be modified if you have a controller class
		displayFare();
		
		if(acceptInputs()) {
			displayTotalAmount();
		}
		int confirmAmount = confirmTotalAmount();

		if (confirmAmount == 1) {
			// Pay order
		}



		//System.out.println("Need to write more code to proceed next actions...");
	}
	
	private void displayFare() {
		//Need to be modified if you get from DB
		System.out.println("Price for a 24h ticket is " + Constant.TWENTY_FOUR_HOUR_TICKET_PRICE + "$");

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

	/**
	 * Display total amount for number of ticket need to buy
	 */
	private void displayTotalAmount() {
		System.out.println("Total amount: " + Constant.TWENTY_FOUR_HOUR_TICKET_PRICE * numberOfFareCertificates + "$");
	}

	/**
	 * Confirm number of ticket and total amount need to pay
	 * @return choice 1: OK, 2: CANCEL
	 */
	public int confirmTotalAmount() {
		System.out.println("Do you want to buy?");
		System.out.println("1. OK");
		System.out.println("2. CANCEL");
		int confirm = -1;

		while (confirm == -1) {
			try	{
				confirm = Integer.parseInt(console.nextLine());
			} catch (Exception e) {
				System.out.println("Only choose 1 or 2");
				confirm = -1;
			}
		}
		return confirm;
	}
}
