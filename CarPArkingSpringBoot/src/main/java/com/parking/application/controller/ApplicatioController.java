package com.parking.application.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.application.classes.Car;
import com.parking.application.classes.Slot;
import com.parking.application.services.ParkingLot;

@RestController
//will create the rest service like Json
public class ApplicatioController
{

	private ParkingLot parkingLot = new ParkingLot(100);
	
	
	@PostMapping("/ParkTheCar")
	public ResponseEntity<Object> parkTheCar(@RequestBody Car car)
	{
		System.out.println("Token Creation...");
		return new ResponseEntity<>(parkingLot.parkTheCar(car),HttpStatus.OK);
	}
	
	
	@GetMapping("/UnParkCar")
	public String testToUnParkTheCar(@RequestParam("tokenNumber") String tokenNumber)
	{
		return 
		parkingLot.unParkTheCar(tokenNumber);
		
	}
	
	
	@GetMapping("/serchCar")
	public String searchCarNumber(@RequestParam("carNumber") String carNumber)
	{
		return
		parkingLot.searchCarNumber(carNumber);	
	}
	
	
	@GetMapping("/totalnoOfslots")
	public ArrayList<Slot> totalNumberOfSlots(@RequestParam("totalSlots") String totalSlots)
	{
		return
		parkingLot.initializeSlot(5);
	}
	
	
	@GetMapping("/showAllcars")
	public ResponseEntity<Object> showAllcars()
	{
		parkingLot.listAllCars();
		return new ResponseEntity<>(parkingLot.listAllCars(),HttpStatus.OK);
	}
	

	

}
