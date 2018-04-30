package com.barclays.bms.model;

public class BaggageNode {
	private String source;
	private String destination;
	private double duration;

	public BaggageNode(String source, String destination, double duration) {
		super();
		this.source = source;
		this.destination = destination;
		this.duration = duration;
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

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

}
