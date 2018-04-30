package com.barclays.bms.model;

import java.util.ArrayList;
import java.util.List;

public class BaggageInfo {
	private List<Bag> bags;
	private List<Flight> flights;
	private List<BaggageNode> baggageNodes;

	public List<Bag> getBags() {
		if (bags == null) {
			bags = new ArrayList<>();
		}
		return bags;
	}

	public List<Flight> getFlights() {
		if (flights == null) {
			flights = new ArrayList<>();
		}
		return flights;
	}

	public List<BaggageNode> getBaggageNodes() {
		if (baggageNodes == null) {
			baggageNodes = new ArrayList<>();
		}
		return baggageNodes;
	}
	
	@Override
	public String toString() {
		return "BaggageInfo [bags=" + bags + ", flights=" + flights + ", baggageNodes=" + baggageNodes + "]";
	}

}
