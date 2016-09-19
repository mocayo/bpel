package com.lm.analyser;

import java.util.List;

import org.apache.ode.bpel.compiler.bom.Activity;
import org.apache.ode.bpel.compiler.bom.InvokeActivity;
import org.apache.ode.bpel.compiler.bom.LinkSource;
import org.apache.ode.bpel.compiler.bom.LinkTarget;

import com.lm.model.Block;
import com.lm.model.Define;
import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;
import com.lm.model.ICFGVariable;
import com.lm.model.Pair;

public class InvokeAnalysis extends ActivityAnalysis {

	public InvokeAnalysis(Activity activity,ICFGModel model) {
		super(activity,model);
	}

	@Override
	public Pair<ICFGNode, ICFGNode> doAnalysis(String s) {
		InvokeActivity ivkAct = (InvokeActivity)this._activity;
		String name  = ivkAct.getName();
		if (name == null || name.equals(""))
			name = "invoke";
		if (s != "")
			name = name + s;
		ICFGNode node = new ICFGNode(name);
		this._model.addNode(node);
		this._model.getNodeList().add(node);
		
		Block block = new Block(node);
		
		//node.jc = ivkAct.getJoinCondition();
		
		List<LinkSource> sources = ivkAct.getLinkSources();
		List<LinkTarget> targets = ivkAct.getLinkTargets();
		// ·ÖÎö <sources> & <targets>
		LinkAnalysis.processSrcTarg(sources, targets, node, node, ivkAct, this._model);
		
		String rVarName = ivkAct.getInputVar();
		String wVarName = ivkAct.getOutputVar();
		//System.out.println("input " + rVarName);
		//System.out.println("output " + wVarName);
		ICFGVariable rVar = this._model.findVar(rVarName);
		ICFGVariable wVar = this._model.findVar(wVarName);
		if(rVar!=null){
			rVar.addReadNode(node);
		}
		if(wVar!=null){
			Define pair = new Define(node.toString(),wVarName);
			block.gen.add(pair);
			wVar.addWriteNode(node);
		}
		node.addReadVariable(rVar);
		node.addWriteVariable(wVar);
		
		if(this._model.isFlow){
			this._model.flowBlocks.add(block);
		}else{
			this._model.blocks.add(block);
		}
		return new Pair<ICFGNode,ICFGNode>(node,node);
	}

}
