package com.lm.analyser;

import java.util.List;

import org.apache.ode.bpel.compiler.bom.Activity;
import org.apache.ode.bpel.compiler.bom.LinkSource;
import org.apache.ode.bpel.compiler.bom.LinkTarget;
import org.apache.ode.bpel.compiler.bom.ReceiveActivity;

import com.lm.model.Block;
import com.lm.model.Define;
import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;
import com.lm.model.ICFGVariable;
import com.lm.model.Pair;

public class ReceiveAnalysis extends ActivityAnalysis {

	public ReceiveAnalysis(Activity activity,ICFGModel model) {
		super(activity,model);
	}

	@Override
	public Pair<ICFGNode, ICFGNode> doAnalysis(String s) {
		ReceiveActivity recvAct = (ReceiveActivity)this._activity;
		String name  = recvAct.getName();
		if (name == null || name.equals(""))
			name = "receive";
		if (s != "")
			name = name + s;
		ICFGNode node = new ICFGNode(name);
		this._model.addNode(node);
		this._model.getNodeList().add(node);
		
		//node.getGen().add(node.toString());
		//node.jc = ivkAct.getJoinCondition();
		
		List<LinkSource> sources = recvAct.getLinkSources();
		List<LinkTarget> targets = recvAct.getLinkTargets();
		// ·ÖÎö <sources> & <targets>
		LinkAnalysis.processSrcTarg(sources, targets, node, node, recvAct, this._model);
		
		String wVarName = recvAct.getVariable();
		ICFGVariable wVar = this._model.findVar(wVarName);
		/*if(!wVar.getWriteNode().isEmpty()){
			for(ICFGNode in:wVar.getWriteNode())
				node.getKill().add(in.toString());
		}*/
		wVar.addWriteNode(node);
		node.addWriteVariable(wVar);
		
		Block block = new Block(node);
		Define pair = new Define(node.toString(),wVarName);
		block.gen.add(pair);
		if(this._model.isFlow){
			this._model.flowBlocks.add(block);
		}else{
			this._model.blocks.add(block);
		}
		
		return new Pair<ICFGNode,ICFGNode>(node,node);
	}

}
