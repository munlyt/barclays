package com.barclays.bms.xmlparser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.barclays.bms.exception.BusinessException;
import com.barclays.bms.exception.ErrorToken;
import com.barclays.bms.model.BaggageInfo;
import com.barclays.bms.utils.Constants;

public class BaggageXMLParserTest {
	private BaggageXMLParser xmlParser;

	@Before
	public void setUp() {
		xmlParser = new BaggageXMLParser();
	}

	@Test(expected = Exception.class)
	public void parseInvalidXMLTest() throws BusinessException {
		xmlParser.parseBaggageXML("WRONG FILE NAME");
	}

	@Test
	public void parseBaggageXMLTest() throws BusinessException {
		BaggageInfo baggageInfo = xmlParser.parseBaggageXML(Constants.BAGGAGE_XML);
		Assert.assertNotNull(baggageInfo);
	}

	@Test(expected = BusinessException.class)
	public void parseBaggageTestXMLTest() throws BusinessException {
		BaggageInfo baggageInfo = xmlParser.parseBaggageXML("baggage-test.xml");
		Assert.assertNotNull(baggageInfo);
	}
	
	@Test
	public void validateErrorCodeTest() throws BusinessException {
		try{
			xmlParser.parseBaggageXML("baggage-test.xml");
		}catch(BusinessException ex){
			Assert.assertEquals(ex.getErrorCode(), ErrorToken.ERROR_001.getCode());
		}
	}

}
