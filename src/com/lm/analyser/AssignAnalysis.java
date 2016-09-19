package com.lm.analyser;

import java.util.List;

import org.apache.ode.bpel.compiler.bom.Activity;
import org.apache.ode.bpel.compiler.bom.AssignActivity;
import org.apache.ode.bpel.compiler.bom.Copy;
import org.apache.ode.bpel.compiler.bom.LinkSource;
import org.apache.ode.bpel.compiler.bom.LinkTarget;

import com.lm.model.Block;
import com.lm.model.Define;
import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;
import com.lm.model.ICFGVariable;
import com.lm.model.Pair;

public class AssignAnalysis extends ActivityAnalysis {

	public AssignAnalysis(Activity activity, ICFGModel model) {
		super(activity, model);
	}

	@Override
	public Pair<ICFGNode, ICFGNode> doAnalysis(String s) {
		AssignActivity asnAct = (AssignActivity) this._activity;

		String name = asnAct.getName();
		if (name == null || name.equals(""))
			name = "assign";
		if (s != "")
			name = name + s;

		List<LinkSource> sources = asnAct.getLinkSources();
		List<LinkTarget> targets = asnAct.getLinkTargets();

		List<Copy> copies = asnAct.getCopies();

		ICFGNode node = new ICFGNode(name);
		this._model.addNode(node);
		this._model.getNodeList().add(node);

		Block block = new Block(node);

		LinkAnalysis.processSrcTarg(sources, targets, node, node, asnAct, this._model);

		for (Copy copy : copies) {
			if (copy.getFrom().isVariableVal()) {
				String readVarName = copy.getFrom().getAsVariableVal().getVariable();
				ICFGVariable var = this._model.findVar(readVarName);
				var.addReadNode(node);
				node.addReadVariable(var);
			}
			if (copy.getTo().isVariableVal()) {
				String writeVarName = copy.getTo().getAsVariableVal().getVariable();
				ICFGVariable var = this._model.findVar(writeVarName);
				var.addWriteNode(node);
				Define pair = new Define(node.toString(), writeVarName);
				block.gen.add(pair);
				node.addWriteVariable(var);
			}
		}
		this._model.blocks.add(block);

		return new Pair<ICFGNode, ICFGNode>(node, node);
	}

}
