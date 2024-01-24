package com.tsdv.tvm.ui;

import java.util.Scanner;

/**
 * @author TrangNTT Jan 2024
 */

public class MainScreen {
	private Scanner console;
	BuyOneWayTicketScreen onewayTicketScreen;
	Buy24hTicketScreen twentyFourHourTicketScreen;
	BuyPrepaidCardScreen prepaidCardScreen;

	public static void main(String[] args) {
		new MainScreen();
	}
	
	public MainScreen() {
		console = new Scanner(System.in);
		display();
	}
	
	public Scanner getInputScanner() {
		return console;
	}
	
	public void display(){
		int choice = 4;
		while (choice < 1 || choice > 3) {
				choice = displayMenu(console);
		}
		
		switch (choice) {
			case 1: 
				if (onewayTicketScreen == null)
					onewayTicketScreen = new BuyOneWayTicketScreen(this); 
				onewayTicketScreen.display();
				break;
			case 2: 
				if (twentyFourHourTicketScreen == null)
					twentyFourHourTicketScreen = new Buy24hTicketScreen(this); 
				twentyFourHourTicketScreen.display();
				break;
			case 3: 
				if (prepaidCardScreen == null)
					prepaidCardScreen = new BuyPrepaidCardScreen(this); 
				prepaidCardScreen.display();
				break;
		}
	}
	
	public int displayMenu(Scanner console) {
		System.out.println("-----------------");
		System.out.println("BUY TRAVELING CERTIFICATES");
		System.out.println("1. One-way ticket");
		System.out.println("2. Twenty-four-hour ticket");
		System.out.println("3. Prepaid card");
		System.out.print("Your choice: ");
		
		return console.nextInt();
	}
}
