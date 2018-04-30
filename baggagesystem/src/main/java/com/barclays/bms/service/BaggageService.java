package com.barclays.bms.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.barclays.bms.engine.BaggagePathService;
import com.barclays.bms.engine.BaggagePathServiceImpl;
import com.barclays.bms.engine.Path;
import com.barclays.bms.engine.Vertex;
import com.barclays.bms.exception.BusinessException;
import com.barclays.bms.exception.ErrorToken;
import com.barclays.bms.model.Bag;
import com.barclays.bms.model.BaggageInfo;
import com.barclays.bms.model.Flight;
import com.barclays.bms.utils.Constants;
import com.barclays.bms.xmlparser.BaggageXMLParser;

public class BaggageService {
	private static final Logger LOGGER = Logger.getLogger(BaggageService.class.getName());
	private BaggagePathService baggagePathService = new BaggagePathServiceImpl();
	private BaggageXMLParser xmlParser = new BaggageXMLParser();


	public BaggageService(BaggagePathService baggagePathService, BaggageXMLParser xmlParser) {
		super();
		this.baggagePathService = baggagePathService;
		this.xmlParser = xmlParser;
	}

	/**
	 * This class is used to handle Baggage information
	 */
	public void handleBaggage() {
		try {
			// Retrieving baggage info from the XML file
			BaggageInfo baggageInfo = xmlParser.parseBaggageXML(Constants.BAGGAGE_XML);

			/* Finds shortest path for each bags and prints */
			for (Bag bag : baggageInfo.getBags()) {
				Path path = baggagePathService.getShortestPath(baggageInfo.getBaggageNodes(), bag.getSource(),
						getGateInfoForBag(bag.getBaggageId(), bag.getDestination(), baggageInfo.getFlights()));
				printRoutingDetails(bag.getBaggageId(), path);
			}
		} catch (BusinessException ex) {
			LOGGER.error("Code: " + ex.getErrorCode() + "\nError Message: " + ex.getErrorMessage(), ex);
		}
	}

	/**
	 * This method returns the gate info for the baggage from the list of
	 * flights
	 * 
	 * @param bagId
	 * @param bagDestination
	 * @param flights
	 * @return
	 * @throws BusinessException
	 */
	private String getGateInfoForBag(String bagId, String bagDestination, List<Flight> flights)
			throws BusinessException {
		if(Constants.ARRIVAL.equalsIgnoreCase(bagDestination)){
			return Constants.BAGGAGE_CLAIM;
		}
		
		String gate = null;
		for (Flight flight : flights) {
			if (flight.getFlightNo() != null && flight.getFlightNo().equals(bagDestination)) {
				gate = flight.getGate();
				break;
			}
		}
		if (gate == null) {
			throw new BusinessException(ErrorToken.ERROR_002.getCode(),
					String.format(ErrorToken.ERROR_002.getMessage(), bagId));
		}
		return gate;
	}

	/**
	 * This method is used to print the routing details
	 * 
	 * @param baggageId
	 * @param path
	 */
	private void printRoutingDetails(String baggageId, Path path) {
		try {
			if (path == null) {
				throw new BusinessException(ErrorToken.ERROR_003.getCode(),
						String.format(ErrorToken.ERROR_003.getMessage(), baggageId));
			}

			StringBuilder output = new StringBuilder();
			output.append("Baggage ID: " + baggageId).append(" Route: [");
			for (Vertex vertex : path.getPath()) {
				output.append(" " + vertex.getName());
			}
			output.append("]").append(" Duration : " + path.getDuration());
			LOGGER.info(output);

		} catch (BusinessException ex) {
			LOGGER.error("Code: " + ex.getErrorCode() + "\nError Message: " + ex.getErrorMessage(), ex);
		}
	}
}
