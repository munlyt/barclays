package com.barclays.bms.main;

import com.barclays.bms.engine.BaggagePathService;
import com.barclays.bms.engine.BaggagePathServiceImpl;
import com.barclays.bms.service.BaggageService;
import com.barclays.bms.xmlparser.BaggageXMLParser;

public class MainApplication {

	public static void main(String args[]) {
		BaggagePathService baggagePathService = new BaggagePathServiceImpl();
		BaggageXMLParser xmlParser = new BaggageXMLParser();
		BaggageService baggageService = new BaggageService(baggagePathService, xmlParser);
		baggageService.handleBaggage();
	}
}
