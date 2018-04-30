package com.barclays.bms.service;

import org.junit.Before;
import org.junit.Test;

import com.barclays.bms.engine.BaggagePathService;
import com.barclays.bms.engine.BaggagePathServiceImpl;
import com.barclays.bms.xmlparser.BaggageXMLParser;

public class BaggageServiceTest {
	private BaggageService baggageService;
	
	@Before
	public void setUp() {
		BaggagePathService baggagePathService = new BaggagePathServiceImpl();
		BaggageXMLParser xmlParser = new BaggageXMLParser();
		baggageService = new BaggageService(baggagePathService, xmlParser);
	}

	@Test
	public void testHandleBaggage() {
		baggageService.handleBaggage();
	}

}
