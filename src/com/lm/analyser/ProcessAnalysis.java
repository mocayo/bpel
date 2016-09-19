package com.lm.analyser;

import java.util.List;

import org.apache.ode.bpel.compiler.bom.Activity;
import org.apache.ode.bpel.compiler.bom.Process;
import org.apache.ode.bpel.compiler.bom.Variable;

import com.lm.du.DUAnalyser;
import com.lm.model.Block;
import com.lm.model.ICFGEdge;
import com.lm.model.ICFGEdge.Type;
import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;
import com.lm.model.ICFGVariable;
import com.lm.parser.BpelParser;

public class ProcessAnalysis {
	
	private BpelParser parser;
	private ICFGModel icfgModel;
	
	public ProcessAnalysis(BpelParser parser){
		this.parser = parser;
		this.icfgModel = null;
	}
	
	public void analysis(){
		//get process 
		Process process = this.parser.getProcess();
		
		//get name of process
		String procName = process.getName();
		if("".equals(procName))
			procName = "process";
		
		//new icfgModel
		this.icfgModel = new ICFGModel(procName);
		
		//get variables of process
		List<Variable> variables = process.getVariables();
		
		//get declared variable and put into model 
		if(!variables.isEmpty()){
			for(Variable var:variables){
				ICFGVariable variable = new ICFGVariable(var);
				if(var.getKind() == Variable.Kind.MESSAGE){
					String name = var.getName();
					String type = var.getTypeName().getLocalPart();
					String location = var.getTypeName().getNamespaceURI();
					variable.setName(name);
					variable.setType(type);
					variable.setLocation(location);
					this.icfgModel.addVar(variable);
				}
			}
		}
		
		//analyze root activity
		Activity rootAct = process.getRootActivity();
		ActivityAnalysis analyzer = AnalyserFactory.createAnalyser(rootAct,this.icfgModel);
		
//		Pair<ICFGNode,ICFGNode> result = analyzer.doAnalysis("");
		//ENTRY BLOCK
//		Block entry = new Block("ENTRY");
//		this.icfgModel.blocks.add(entry);
		this.icfgModel.getNodeList().add(this.icfgModel.getStartNode());
		Block porc_start = new Block(this.icfgModel.getStartNode());
		this.icfgModel.blocks.add(porc_start);
		

		analyzer.doAnalysis("");
		
		this.icfgModel.getNodeList().add(this.icfgModel.getEndNode());
		Block proc_end = new Block(this.icfgModel.getEndNode());
		this.icfgModel.blocks.add(proc_end);
		
		List<ICFGNode> nodes = this.icfgModel.getNodeList();
		
		if(nodes.size()>2){
			ICFGEdge start_edge = new ICFGEdge(nodes.get(0),nodes.get(1),Type.SEQUENCE);
			ICFGEdge end_edge = new ICFGEdge(nodes.get(nodes.size()-2),nodes.get(nodes.size()-1),Type.SEQUENCE);
			
			this.icfgModel.addEdge(start_edge);
			this.icfgModel.addEdge(end_edge);
		}
		
//		Block exit = new Block("EXIT");
//		this.icfgModel.blocks.add(exit);
		
//		ICFGNode exit = new ICFGNode("EXIT");
//		this.icfgModel.getNodeList().add(exit);
//		this.icfgModel.addNode(exit);
		//ICFGEdge edge = new ICFGEdge(this.icfgModel.getNodeList().get(this.icfgModel.getNodeCounter()-2),exit,ICFGEdge.Type.SEQUENCE);
		//icfgModel.addEdge(edge);
//		ICFGNode process_start = this.icfgModel.getStartNode();
//		ICFGNode process_end = this.icfgModel.getEndNode();
//		process_start.isBasic = false;
//		process_end.isBasic = false;
//		ICFGNode first = result.getFirst();
//		ICFGNode second = result.getSecond();
//		ICFGEdge edge1 = new ICFGEdge(process_start,first,ICFGEdge.Type.SEQUENCE);
//		ICFGEdge edge2 = new ICFGEdge(second,process_end,ICFGEdge.Type.SEQUENCE);
//		icfgModel.addEdge(edge1);
//		icfgModel.addEdge(edge2);
//		process_start.addOutEdge(edge1);
//		first.addInEdge(edge1);
//		process_end.addInEdge(edge2);
//		second.addOutEdge(edge2);
		
//		System.out.println("start node:" + icfgModel.getStartNode());
//		System.out.println("end node:" + icfgModel.getEndNode());
		
//		icfgModel.printVarAccessInfo();
		
		/*DataFlowAnalyser df = new DataFlowAnalyser(this.icfgModel);
		df.analyse();
		
		NodeAnalyser nd = new NodeAnalyser(this.icfgModel);
		nd.analyse();
		
		EdgeAnalyser eg = new EdgeAnalyser(this.icfgModel);
		eg.analyse();*/
		
		DUAnalyser du = new DUAnalyser(this.icfgModel);
		du.analyse();
		
	}

	public BpelParser getParser() {
		return parser;
	}

	public ICFGModel getIcfgModel() {
		return icfgModel;
	}
	
}
