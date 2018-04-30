package com.barclays.bms.engine;

import java.util.List;

public class Path {
	private List<Vertex> path;
	private double duration;

	public List<Vertex> getPath() {
		return path;
	}

	public void setPath(List<Vertex> path) {
		this.path = path;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Path [duration=" + duration + ", path=" + path + "]";
	}

}
