package com.CarParkingSyatem.pojo;

public class Ticket
{
	private int TicketNo=0;
	
	private ParkingLot parkingLot;
	
	private Car car;

	
	public int getTicketNo() {
		return TicketNo;
	}

	public void setTicketNo(int ticketNo) {
		TicketNo = ticketNo;
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	
	
	
	
	
	

}
