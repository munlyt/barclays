package com.barclays.bms.model;

public class Flight {
	private String flightNo;
	private String gate;
	private String destination;
	private String departureTime;
	
	public Flight(String flightNo, String gate, String destination, String departureTime) {
		super();
		this.flightNo = flightNo;
		this.gate = gate;
		this.destination = destination;
		this.departureTime = departureTime;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getGate() {
		return gate;
	}

	public void setGate(String gate) {
		this.gate = gate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	@Override
	public String toString() {
		return "Flight [flightNo=" + flightNo + ", gate=" + gate + ", destination=" + destination + ", departureTime="
				+ departureTime + "]";
	}
	
	

}
