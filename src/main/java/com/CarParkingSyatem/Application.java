package com.CarParkingSyatem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.CarParkingSyatem.pojo.Car;
import com.CarParkingSyatem.pojo.ParkingLot;
import com.CarParkingSyatem.pojo.Ticket;

public class Application {

	private static int ticketNumber = 0;
	
	private static Map<Integer,Car> slotMap = new HashMap<Integer,Car>();

	public static void main(String[] arg) {
		System.out.println("Staring..");
		Scanner input = new Scanner(System.in);
		// initialising parkinglot with 100 numberof max parking slots from scanner
		// input
		ParkingLot parkingLot = new ParkingLot();
		System.out.println("Enter maximum number of parking slots...");
		parkingLot.setMaxNumberOfCars(input.nextInt());
		// initializing a car with brand name , colour, regnum from scanner input
		for (;;) {
			System.out.println("Welcome to automatic parking system...");
			System.out.println("Please select below option number");
			System.out.println("1. Enter parking lot..");
			System.out.println("2. Exit parking lot...");
			System.out.println("3. Exit Application..");
			int menuCode = input.nextInt();
			if (menuCode == 3) {
				System.out.println("Exit from Application, bye..");
				break;
			}
			else if (menuCode ==2) {
				//Exit Parking lot..
				exitParkingLot(input, parkingLot);
				continue;
			}
			// Entering parking lot
			enterParkingLot(input, parkingLot);
		}

		System.out.println("Completed...");
	}
	
	

	private static void enterParkingLot(Scanner input, ParkingLot parkingLot) {
		System.out.println("Please enter your car details...");
		Car car1 = new Car();
		System.out.println("Enter your car brand..");
		car1.setBrand(input.next());
		System.out.println("Enter your car Color..");
		car1.setColour(input.next());
		System.out.println("Enter your car registration number..");
		car1.setReg_number(input.next());
		System.out.println("Genrating ticket, please wait...");
		// allocating parking slot by checking number of cars already in parking from
		// parkingLot class
		int occupiedSlots = parkingLot.getOccupiedSlots();
		if (occupiedSlots == parkingLot.getMaxNumberOfCars()) {// all the slots in parking lot are filled no chanse
																// to place another car
			System.out.println("Parkinglot full..");
			return;// exit scenario
		}
		// parking lot is not full, generating ticket
		// creating slot in parkinglot
		occupiedSlots++;
		int slotNumber  = getSlotNumber() == -1 ? occupiedSlots: getSlotNumber();
		parkingLot.setSlot_No(slotNumber);
		parkingLot.setOccupiedSlots(occupiedSlots);
		slotMap.put(slotNumber, car1);

		// creating ticket
		ticketNumber++;
		
		Ticket ticket = new Ticket();
		ticket.setCar(car1);
		ticket.setParkingLot(parkingLot);
		ticket.setTicketNo(ticketNumber);

		System.out.println("Here is your parking ticket..");
		System.out.println("Ticket Number :" + ticketNumber);
		System.out.println("Parking Slot Number :" + slotNumber);

	}
	
	private static void exitParkingLot(Scanner input, ParkingLot parkingLot) {
		System.out.println("Please enter your car registration number...");
		String regNumber = input.next();
		//check registration number in map
		for(int slotNumber: slotMap.keySet()) {
			String carReg = slotMap.get(slotNumber).getReg_number(); // fetching registation number from slotmap 
			//matching reg number from map with input
			if(carReg.equals(regNumber)) {
				System.out.println("Removing car "+ carReg);
				//storing value in slotMap as null to indicate slot is empty
				slotMap.put(slotNumber, null);
				int occupiedSlots = parkingLot.getOccupiedSlots()-1;
				parkingLot.setOccupiedSlots(occupiedSlots);
				System.out.println("Thank you,");
				break;
			}
		}
	}
	
	private static int getSlotNumber() {
		int emptySlot = -1; // key to notify no empty slots found
		for(int slotNumber:slotMap.keySet()) {
			//checking empty slots in slot map by checking if any slot number value is null, means slot is empty returnng slot number
			if(slotMap.get(slotNumber) ==null) {
				return slotNumber;
			}
		}
		return emptySlot;
	}
}