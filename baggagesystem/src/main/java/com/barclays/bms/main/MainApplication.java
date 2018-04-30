package com.barclays.bms.main;

import com.barclays.bms.service.BaggageService;

public class MainApplication {

	public static void main(String args[]) {
		BaggageService baggageService = new BaggageService();
		baggageService.handleBaggage();
	}
}
