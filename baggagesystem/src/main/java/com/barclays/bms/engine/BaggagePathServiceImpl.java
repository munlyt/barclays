package com.barclays.bms.engine;

import java.util.ArrayList;
import java.util.List;

import com.barclays.bms.exception.BusinessException;
import com.barclays.bms.model.BaggageNode;

public class BaggagePathServiceImpl implements BaggagePathService {

	@Override
	public Path getShortestPath(List<BaggageNode> baggageNodes, String source, String destination)
			throws BusinessException {
		List<Edge> edges = getAllEdges(baggageNodes);
		BaggageEngine engine = new BaggageEngine(edges);
		engine.execute(getVertex(source));
		return engine.getPath(getVertex(destination));
	}

	/**
	 * This method is used to retrieve edges from given baggage nodes
	 * 
	 * @param baggageNodes
	 * @return
	 */
	private List<Edge> getAllEdges(List<BaggageNode> baggageNodes) {
		List<Edge> edges = new ArrayList<>();
		for (BaggageNode nodes : baggageNodes) {
			edges.add(new Edge(getVertex(nodes.getSource()) + " - " + getVertex(nodes.getDestination()),
					getVertex(nodes.getSource()), getVertex(nodes.getDestination()), nodes.getDuration()));
		}
		return edges;
	}

	/**
	 * Returns a vertex with given name
	 * 
	 * @param name
	 * @return
	 */
	private Vertex getVertex(String name) {
		return new Vertex(name, name);
	}

}
