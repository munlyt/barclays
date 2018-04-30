package com.barclays.bms.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.barclays.bms.engine.BaggagePathService;
import com.barclays.bms.exception.BusinessException;
import com.barclays.bms.model.BaggageInfo;
import com.barclays.bms.utils.Constants;
import com.barclays.bms.xmlparser.BaggageXMLParser;

public class BaggageServiceTest {
	private BaggageService baggageService;
	
	@Mock
	BaggagePathService baggagePathService;
	
	@Mock
	BaggageXMLParser xmlParser;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		baggageService = new BaggageService(baggagePathService, xmlParser);
	}

	@Test
	public void testHandleBaggage() throws BusinessException {
		BaggageInfo baggageInfo = Mockito.mock(BaggageInfo.class);
		Mockito.when(xmlParser.parseBaggageXML(Constants.BAGGAGE_XML)).thenReturn(baggageInfo);
		baggageService.handleBaggage();
	}

}
