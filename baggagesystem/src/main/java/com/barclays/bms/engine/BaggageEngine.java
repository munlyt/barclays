package com.barclays.bms.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.barclays.bms.exception.BusinessException;

public class BaggageEngine {

	private final List<Edge> edges;
	private Set<Vertex> settledNodes;
	private Set<Vertex> unSettledNodes;
	private Map<Vertex, Vertex> predecessors;
	private Map<Vertex, Double> distance;

	public BaggageEngine(List<Edge> edges) {
		this.edges = edges;
	}

	/**
	 * This method finds the minimal distance and path from a given source
	 * 
	 * @param source
	 * @throws BusinessException
	 */
	public void execute(Vertex source) throws BusinessException {
		settledNodes = new HashSet<>();
		unSettledNodes = new HashSet<>();
		distance = new HashMap<>();
		predecessors = new HashMap<>();
		distance.put(source, 0.0);
		unSettledNodes.add(source);
		while (!unSettledNodes.isEmpty()) {
			Vertex node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	/**
	 * This method is used to find the minimal distances from a given vertex to
	 * its neighboring vertices
	 * 
	 * @param node
	 * @throws BusinessException
	 */
	private void findMinimalDistances(Vertex node) throws BusinessException {
		List<Vertex> adjacentNodes = getNeighboringVertices(node);
		for (Vertex target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
				distance.put(target, getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	/**
	 * This method is used to get distance between source and destination
	 * 
	 * @param node
	 * @param target
	 * @return
	 * @throws BusinessException
	 */
	private double getDistance(Vertex node, Vertex target) throws BusinessException {
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		throw new BusinessException("Source and Destination are same");
	}

	/**
	 * This method is used to find the list of neighboring vertices
	 * 
	 * @param node
	 * @return
	 */
	private List<Vertex> getNeighboringVertices(Vertex node) {
		List<Vertex> neighbors = new ArrayList<>();
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	/**
	 * This method is used to identify the nearest vertex
	 * 
	 * @param vertexes
	 * @return
	 */
	private Vertex getMinimum(Set<Vertex> vertexes) {
		Vertex minimum = null;
		for (Vertex vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	/**
	 * Checks if given vertex is already handled or not
	 * 
	 * @param vertex
	 * @return
	 */
	private boolean isSettled(Vertex vertex) {
		return settledNodes.contains(vertex);
	}

	/**
	 * Returns the shortest distance from the given vertex
	 * 
	 * @param destination
	 * @return
	 */
	private double getShortestDistance(Vertex destination) {
		Double d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/**
	 * This method returns the path from the source to the selected target and
	 * empty if no path exists
	 * 
	 * @param target
	 * @return
	 */
	public Path getPath(Vertex target) {
		Path path = new Path();
		LinkedList<Vertex> vertices = new LinkedList<>();

		Vertex step = target;
		if (distance.get(step) != null) {
			path.setDuration(distance.get(step));
		}

		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		vertices.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			vertices.add(step);
		}
		// Put it into the correct order
		Collections.reverse(vertices);
		path.setPath(vertices);
		return path;
	}

}