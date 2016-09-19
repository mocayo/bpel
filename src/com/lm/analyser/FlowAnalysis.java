package com.lm.analyser;

import java.util.ArrayList;
import java.util.List;

import org.apache.ode.bpel.compiler.bom.Activity;
import org.apache.ode.bpel.compiler.bom.FlowActivity;
import org.apache.ode.bpel.compiler.bom.LinkSource;
import org.apache.ode.bpel.compiler.bom.LinkTarget;

import com.lm.model.Block;
import com.lm.model.ICFGEdge;
import com.lm.model.ICFGEdge.Type;
import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;
import com.lm.model.Pair;

public class FlowAnalysis extends ActivityAnalysis {

	public FlowAnalysis(Activity activity,ICFGModel model) {
		super(activity,model);
	}

	@Override
	public Pair<ICFGNode, ICFGNode> doAnalysis(String s) {
		FlowActivity flowAct = (FlowActivity)this._activity;
		String actName = flowAct.getName();
		List<Activity> activities = flowAct.getActivities();
		List<LinkSource> sources = flowAct.getLinkSources();
		List<LinkTarget> targets = flowAct.getLinkTargets();
		
		if(actName == null || actName.equals(""))
			actName = "flow";	
		if(s!="")
			actName=actName+s;
		ICFGNode f_start = new ICFGNode(actName + "_start");	
		ICFGNode f_end = new ICFGNode(actName + "_end");	
		
		this._model.addNode(f_start);
		this._model.getNodeList().add(f_start);
		this._model.addNode(f_end);		
		
		Block flow_start = new Block(f_start);
		Block flow_end = new Block(f_end);
		this._model.blocks.add(flow_start);
		
		LinkAnalysis.processSrcTarg(sources, targets, f_end, f_start, flowAct, this._model);
		for (Activity act : activities) {
			Pair<ICFGNode, ICFGNode> result = null;
			List<Block> fblock = new ArrayList<Block>();
			this._model.flowBlocks = fblock;
			ActivityAnalysis analyser = AnalyserFactory.createAnalyser(act, this._model);
			
			if(analyser != null){
				result = analyser.doAnalysis(s);
				if (result != null) {
					ICFGEdge currEdge1 = new ICFGEdge(f_start, result.getFirst(), Type.CONCURRENCY);
					ICFGEdge currEdge2 = new ICFGEdge(result.getSecond(),f_end, Type.CONCURRENCY);
					this._model.addEdge(currEdge1);
					this._model.addEdge(currEdge2);
					
					f_start.addOutEdge(currEdge1);
					result.getFirst().addInEdge(currEdge1);
					result.getSecond().addOutEdge(currEdge2);
					f_end.addInEdge(currEdge2);
				}
			}
		}	
		this._model.blocks.add(flow_end);
		this._model.getNodeList().add(f_end);
		if(f_start.getOutEdges().isEmpty()){
			ICFGEdge edge = new ICFGEdge(f_start, f_end, Type.CONCURRENCY);
			this._model.addEdge(edge);
			f_start.addOutEdge(edge);
			f_end.addInEdge(edge);
		}
		
		return new Pair<ICFGNode, ICFGNode>(f_start,f_end);
	}

}
