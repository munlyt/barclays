package com.barclays.bms.model;

public class Bag {
	private String baggageId;
	private String source;
	private String destination;

	public Bag(String baggageId, String source, String destination) {
		super();
		this.baggageId = baggageId;
		this.source = source;
		this.destination = destination;
	}

	public String getBaggageId() {
		return baggageId;
	}

	public void setBaggageId(String baggageId) {
		this.baggageId = baggageId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

}
