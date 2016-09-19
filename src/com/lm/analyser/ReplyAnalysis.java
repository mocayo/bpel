package com.lm.analyser;

import java.util.List;

import org.apache.ode.bpel.compiler.bom.Activity;
import org.apache.ode.bpel.compiler.bom.LinkSource;
import org.apache.ode.bpel.compiler.bom.LinkTarget;
import org.apache.ode.bpel.compiler.bom.ReplyActivity;

import com.lm.model.Block;
import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;
import com.lm.model.ICFGVariable;
import com.lm.model.Pair;

public class ReplyAnalysis extends ActivityAnalysis {

	public ReplyAnalysis(Activity activity,ICFGModel model) {
		super(activity,model);
	}

	@Override
	public Pair<ICFGNode,ICFGNode> doAnalysis(String s) {
		ReplyActivity rplAct = (ReplyActivity)this._activity;
		String name  = rplAct.getName();
		if (name == null || name.equals(""))
			name = "reply";
		if (s != "")
			name = name + s;
		ICFGNode node = new ICFGNode(name);
		this._model.addNode(node);
		this._model.getNodeList().add(node);

		Block block = new Block(node);
		if(this._model.isFlow){
			
		}else{
			this._model.blocks.add(block);
		}
		
		//node.jc = ivkAct.getJoinCondition();
		
		List<LinkSource> sources = rplAct.getLinkSources();
		List<LinkTarget> targets = rplAct.getLinkTargets();
		// ·ÖÎö <sources> & <targets>
		LinkAnalysis.processSrcTarg(sources, targets, node, node, rplAct, this._model);
		
		String rVarName = rplAct.getVariable();
		ICFGVariable rVar = this._model.findVar(rVarName);
		if(rVar!=null){
			rVar.addReadNode(node);
			node.addReadVariable(rVar);
		}
		
		return new Pair<ICFGNode,ICFGNode>(node,node);
	}

}
