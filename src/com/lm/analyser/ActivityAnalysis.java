package com.lm.analyser;


import org.apache.ode.bpel.compiler.bom.Activity;

import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;
import com.lm.model.Pair;

public abstract class ActivityAnalysis {
	protected Activity _activity;	// activity to be analyzed
	protected ICFGModel _model;		// model to be build
	
	public ActivityAnalysis(Activity activity,ICFGModel model){
		this._activity = activity;
		this._model = model;
	}
	
	public abstract Pair<ICFGNode, ICFGNode> doAnalysis(String s);
}
