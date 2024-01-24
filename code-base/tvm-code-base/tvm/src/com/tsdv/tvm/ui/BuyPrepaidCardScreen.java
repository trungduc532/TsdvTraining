package com.tsdv.tvm.ui;

import com.tsdv.tvm.util.Constant;

/**
 * @author TrangNTT Jan 2024
 */
public class BuyPrepaidCardScreen  extends BuyFareCertificateScreen{
	private double balance;
	
	public BuyPrepaidCardScreen(MainScreen mainScreen){
		super(mainScreen);
	}
	
	@Override
	public void display(){
		System.out.println("-----------------");
		System.out.println("BUY PREPAID CARD");
		
		if (acceptInputs() == false)
			return;
		
		System.out.println("Need to write more code to proceed next actions...");
	}
	
	/**
	 * 
	 * @return true when completing all inputs, false when cancel at the middle
	 */
	@Override
	protected boolean acceptInputs() {
		//Need to be modified if you get from DB
		System.out.println("Deposit amount in a prepaid card is " + Constant.DEPOSIT_FOR_PREPAID_CARD);
		System.out.println("Default balance in a prepaid card is " + Constant.DEFAULT_BALANCE_FOR_PREPAID_CARD);

		if (!acceptBalance())
			return false;
		
		if (!acceptNumberOfFareCertificates(Constant.MAX_NUMBER_OF_PREPAID_CARDS))
			return false;
		
		return true;
	}

	private boolean acceptBalance() {
		balance = -1;
		while (balance < Constant.MIN_BALANCE_FOR_PREPAID_CARD) {
			if (balance == 0) {
				mainScreen.display();
				return false;
			}
			
			System.out.print("Enter a $2.5-min balance for prepaid cards you want to buy (0 for Main Screen):  ");
			balance = console.nextDouble();
		}

		return true;
	}

}
