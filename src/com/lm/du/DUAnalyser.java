package com.lm.du;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.lm.model.Block;
import com.lm.model.Define;
import com.lm.model.ICFGEdge;
import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;
import com.lm.model.ICFGVariable;

public class DUAnalyser {
	private ICFGModel model;

	public DUAnalyser(ICFGModel model) {
		this.model = model;
	}

	public void analyse() {
		System.out.println("生成的节点:");
		for(ICFGNode node:this.model.getNodeList())System.out.println(node);
		System.out.println("\n生成的边:");
		for(ICFGEdge edge:this.model.getEdgeSet())System.out.println(edge);
		Set<String> pairs = new TreeSet<String>();
		DataFlowAnalyser df = new DataFlowAnalyser(this.model);
		List<Block> blocks = df.analyse();
		System.out.println("\n各节点入口的到达定值:");
		for(Block block:blocks){
			System.out.println("\n节点 " + block.name + " 入口的到达定值:");
			if(block.in.isEmpty())System.out.println("[]");
			for(Iterator<Define> it=block.in.iterator();it.hasNext();){
				System.out.print(it.next() + " ");
				if(it.hasNext())System.out.print(it.next());
				if(it.hasNext())System.out.print(it.next());
				System.out.println();
			}
			if(block.getNode()!=null){
				Set<ICFGVariable> readVars = block.getNode().getReadVar();
				Set<Define> in = block.in;
				for(Define define:in){
					if(!readVars.isEmpty()){
						for(ICFGVariable readVar:readVars){
							if(readVar.getName().equals(define.getVarName())){
								pairs.add(readVar.getName() + ":(" 
										+ define.getNodeName() + ", " + block.name + ")");
							}
						}
					}
				}
			}
		}
		System.out.println("有效定义使用对:");
		for(String pair:pairs)System.out.println(pair);
	}
}
