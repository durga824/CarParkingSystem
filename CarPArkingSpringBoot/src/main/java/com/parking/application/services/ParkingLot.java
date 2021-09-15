package com.parking.application.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.parking.application.classes.Car;
import com.parking.application.classes.Slot;
import com.parking.application.classes.Token;


public class ParkingLot {
	private int totalNumberOfSlots;
	ArrayList<Slot> availableSlotList;
	private List<Token> tokenForLot;
	private List<Token> historyOfParking;
	private ArrayList<Slot> totalSlots;

	public ParkingLot(int totalNumberOfSlots){
		this.totalNumberOfSlots = totalNumberOfSlots;
		this.tokenForLot = new ArrayList<>();
		this.historyOfParking = new ArrayList<>();

		this.availableSlotList = initializeSlot(totalNumberOfSlots);
	}
	public ArrayList<Slot> initializeSlot(int totalNumberOfSlot){

		this.totalSlots = new ArrayList<Slot>() {};
		for (int i=1; i<= totalNumberOfSlot; i++) {
			Slot getSlotAssignment = new Slot(i);
			totalSlots.add(getSlotAssignment);
		}
		return totalSlots;

	}
	public Token parkTheCar(Car car){

		if(isSlotAvailable()){
			Slot availableSlot = getTheNextFreeSlot();
			Token parkingToken = new Token(String.valueOf(System.currentTimeMillis()),availableSlot,car);
			this.tokenForLot.add(parkingToken);
			return parkingToken;
		}else {
			return null;
		}
	}

	public String unParkTheCar(String token){
		for(Token tokenInLot:tokenForLot){
			if(tokenInLot.getTokenNumber().equals(token)){
				tokenForLot.remove(tokenInLot);
				Slot slot = tokenInLot.getSlotDetails();
				int slotNumber = slot.getSlotNumber();
				return removeCarFromSlot(tokenInLot,slotNumber);
			}
			return "No token found";
		}
		return null;
	}

	private String removeCarFromSlot(Token token, int slotNumber) {
		for (Slot removeEntry:availableSlotList){

			if(removeEntry.getSlotNumber() == slotNumber){
				removeEntry.makeSlotFree();
				Token historyToken = token.updateCheckOutTime();
				historyOfParking.add(historyToken);
				return "Car entry removed";
			}

		}
		return null;
	}

	private Slot getTheNextFreeSlot() {
		for(Slot slot : availableSlotList){
			if(slot.isSlotFree()){
				slot.makeSlotOccupied();
				return slot;
			}
		}
		return null;
	}
	public String searchCarNumber(String carNumber){
		for(Token tokenSearch:tokenForLot){
			String carDetails = tokenSearch.getCarDetails().getCarNumber();
			if(carDetails.equalsIgnoreCase(carNumber)){
				return "Token Number: " +tokenSearch.getTokenNumber()+"\nSlot Number: " +tokenSearch.getSlotDetails().getSlotNumber()+"\nCar Color: " +tokenSearch.getCarDetails().getCarColor();
			}
		}
		return "There is no any car";
	}
	private boolean isSlotAvailable() {
		boolean isSlotAvailable = false;

		for(Slot slot:availableSlotList){
			if(slot.isSlotFree()){
				isSlotAvailable = true;
				break;
			}
		}
		return isSlotAvailable;
	}
	public String listAllCars(){
		for(Token tokenSearch:tokenForLot){
			String carDetails = tokenSearch.getCarDetails().getCarNumber();
			return "\nToken Number: " +tokenSearch.getTokenNumber()+"\nSlot Number: " +tokenSearch.getSlotDetails().getSlotNumber()+"\nCar Color: " +tokenSearch.getCarDetails().getCarColor();
		}
		return "No any car so far";
	}
}
