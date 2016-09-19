package com.lm.du;

import com.lm.model.ICFGEdge;
import com.lm.model.ICFGModel;

public class EdgeAnalyser {
	private ICFGModel model;

	public EdgeAnalyser(ICFGModel model) {
		this.model = model;
	}

	public void analyse() {
		for (ICFGEdge edge : this.model.getEdgeSet()) {
			System.out.println(edge);
		}
	}
}
