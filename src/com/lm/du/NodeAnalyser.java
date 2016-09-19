package com.lm.du;

import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;

public class NodeAnalyser {
	private ICFGModel model;

	public NodeAnalyser(ICFGModel model) {
		this.model = model;
	}

	public void analyse() {
		for (ICFGNode node : this.model.getNodeSet()) {
			// System.out.println("read " + node.getReadVar().isEmpty());
			// System.out.println("write " + node.getWriteVar().isEmpty());
			// if(!node.getReadVar().isEmpty()){
			// System.out.println(Arrays.toString(node.getReadVar().toArray()));
			// }
			System.out.println(node.getId() + "-->" + node.getName());
			// System.out.println("=========================================");
		}
	}
}
