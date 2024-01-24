package com.tsdv.tvm.ui;

import java.util.Scanner;

/**
 * @author TrangNTT Jan 2024
 */

public abstract class BuyFareCertificateScreen {
	MainScreen mainScreen;
	Scanner console;
	int numberOfFareCertificates;

	BuyFareCertificateScreen(MainScreen mainScreen){
		this.mainScreen = mainScreen;
		this.console = mainScreen.getInputScanner();
	}
	
	/**
	 * 
	 * @return true when completing all inputs, false when cancel at the middle
	 */
	public boolean acceptNumberOfFareCertificates(int maxNumber) {
		numberOfFareCertificates = -1;
		
		while (numberOfFareCertificates <= 0 || numberOfFareCertificates > maxNumber) {
			if (numberOfFareCertificates == 0) {
				mainScreen.display();
				return false;
			}
			
			System.out.print("Enter the number of fare certificates (0< <=" + maxNumber
						+ ") you want to buy (0 for Main Screen):  ");
			numberOfFareCertificates = console.nextInt();
		}

		return true;
	}
	
	/**
	 * 
	 * @return true when completing all inputs, false when cancel at the middle
	 */
	abstract protected boolean acceptInputs();
	
	abstract public void display();
}
