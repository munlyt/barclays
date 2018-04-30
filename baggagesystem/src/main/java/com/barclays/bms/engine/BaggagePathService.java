package com.barclays.bms.engine;

import java.util.List;

import com.barclays.bms.exception.BusinessException;
import com.barclays.bms.model.BaggageNode;

public interface BaggagePathService {
	/**
	 * This method returns the shortest path and duration for given source and
	 * destination
	 * 
	 * @param baggageNodes
	 * @param source
	 * @param destination
	 * @return
	 * @throws BusinessException
	 */
	public Path getShortestPath(List<BaggageNode> baggageNodes, String source, String destination)
			throws BusinessException;
}
