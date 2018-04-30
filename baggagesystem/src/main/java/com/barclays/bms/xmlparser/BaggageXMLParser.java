package com.barclays.bms.xmlparser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.barclays.bms.exception.BusinessException;
import com.barclays.bms.exception.ErrorToken;
import com.barclays.bms.model.Bag;
import com.barclays.bms.model.BaggageNode;
import com.barclays.bms.model.BaggageInfo;
import com.barclays.bms.model.Flight;
import com.barclays.bms.utils.Constants;

public class BaggageXMLParser {
	private static final Logger LOGGER = Logger.getLogger(BaggageXMLParser.class.getName());
	private BaggageInfo baggageInfo;

	/**
	 * This method is used to read data from baggage.xml file and translates the
	 * information in BaggageInfo class
	 * 
	 * @return BaggageInfo object
	 * @throws BusinessException
	 *             if some nodes are missing
	 */
	public BaggageInfo parseBaggageXML(String fileName) throws BusinessException {
		try {
			ClassLoader classLoader = BaggageXMLParser.class.getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			baggageInfo = new BaggageInfo();
			if (doc.hasChildNodes()) {
				findNode(doc.getChildNodes());
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}
		return baggageInfo;
	}

	/**
	 * This method is used for scanning the nodes in XML file
	 * 
	 * @param nodeList
	 * @throws BusinessException
	 */
	private void findNode(NodeList nodeList) throws BusinessException {

		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				if (tempNode.hasChildNodes()) {
					readDataFromNodeElements(tempNode);
				}
				LOGGER.debug("Reading date from Node Name =" + tempNode.getNodeName() + " [Completed]");
			}

		}

	}

	/**
	 * This method reads the data from the XML file and translates into baggage
	 * info object
	 * 
	 * @param tempNode
	 * @throws BusinessException
	 */
	private void readDataFromNodeElements(Node tempNode) throws BusinessException {
		Element element = (Element) tempNode;
		switch (tempNode.getNodeName()) {
		case Constants.ELEMENT_NODE_BAGGAGE:
			String source = readElementValue(element, Constants.NODE_SOURCE);
			String destination = readElementValue(element, Constants.NODE_DESTINATION);
			double duration = Double.parseDouble(readElementValue(element, Constants.NODE_DURATION));
			baggageInfo.getBaggageNodes().add(new BaggageNode(source, destination, duration));
			break;
		case Constants.ELEMENT_NODE_FLIGHT:
			String flightNo = readElementValue(element, Constants.NODE_FLIGHTNO);
			String gate = readElementValue(element, Constants.NODE_GATE);
			String destinationCity = readElementValue(element, Constants.NODE_DESTINATION);
			String departureTime = readElementValue(element, Constants.NODE_DEPARTURETIME);
			baggageInfo.getFlights().add(new Flight(flightNo, gate, destinationCity, departureTime));
			break;
		case Constants.ELEMENT_NODE_BAG:
			String baggageId = readElementValue(element, Constants.NODE_BAGGAGEID);
			String bagSource = readElementValue(element, Constants.NODE_SOURCE);
			String bagDestination = readElementValue(element, Constants.NODE_DESTINATION);
			baggageInfo.getBags().add(new Bag(baggageId, bagSource, bagDestination));
			break;
		default:
			findNode(tempNode.getChildNodes());
		}
	}

	/**
	 * Reads data from the Element
	 * 
	 * @param element
	 * @param nodeName
	 * @return
	 * @throws BusinessException
	 */
	private String readElementValue(Element element, String nodeName) throws BusinessException {
		if (element.getElementsByTagName(nodeName) == null || element.getElementsByTagName(nodeName).item(0) == null) {
			throw new BusinessException(ErrorToken.ERROR_001.getCode(),
					String.format(ErrorToken.ERROR_001.getMessage(), nodeName));
		}
		return element.getElementsByTagName(nodeName).item(0).getTextContent();
	}

}
