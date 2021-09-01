package com.CarParkingSyatem.pojo;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot
{
	private int maxNumberOfCars;
	
	private int slot_No;
	
	private int occupiedSlots =0; 
	
	

	public int getMaxNumberOfCars() {
		return maxNumberOfCars;
	}

	public void setMaxNumberOfCars(int maxNumberOfCars) {
		this.maxNumberOfCars = maxNumberOfCars;
	}

	public int getSlot_No() {
		return slot_No;
	}

	public void setSlot_No(int slot_No) {
		this.slot_No = slot_No;
	}

	public int getOccupiedSlots() {
		return occupiedSlots;
	}

	public void setOccupiedSlots(int occupiedSlots) {
		this.occupiedSlots = occupiedSlots;
	}

	

	

}
