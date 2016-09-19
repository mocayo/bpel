package com.lm.analyser;

import org.apache.ode.bpel.compiler.bom.Activity;

import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;
import com.lm.model.Pair;

public class WaitAnalysis extends ActivityAnalysis {

	public WaitAnalysis(Activity activity,ICFGModel model) {
		super(activity,model);
	}
	
	@Override
	public Pair<ICFGNode,ICFGNode> doAnalysis(String s) {
		return null;
	}

}
