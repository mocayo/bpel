package com.lm.du;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lm.model.Block;
import com.lm.model.Define;
import com.lm.model.ICFGEdge;
import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;
import com.lm.model.ICFGVariable;

public class DataFlowAnalyser {
	private List<Block> blocks;
	private Set<ICFGVariable> vars = new HashSet<ICFGVariable>();
	private Set<ICFGEdge> edges = new HashSet<ICFGEdge>(); 
	
	public DataFlowAnalyser(ICFGModel model){
		this.blocks = model.blocks;
		this.vars = model.getVarSet();
		this.edges = model.getEdgeSet();
	}
	
	public List<Block> findPres(String blockname){
		List<Block> pres = new ArrayList<Block>();
		for(ICFGEdge edge:this.edges){
			if(edge.getTarget().toString().equals(blockname)){
				for(Block block:this.blocks){
					if(block.name.equals(edge.getSource().toString())){
						pres.add(block);
					}
				}
			}
		}
		return pres;
	}
	
	public void reach(List<Block> blocks){
		boolean arechange = true;
		while(arechange){
			arechange = false;
			boolean[] change = new boolean[blocks.size()];
			for(int i=0;i<blocks.size();i++){
				Block b = blocks.get(i);
				//in
				if(!b.pres.isEmpty()){
					for(Block ppre : b.pres){
						b.in.addAll(ppre.out);
					}
				}
				Set<Define> tmpIn = new HashSet<Define>(b.in);
				Set<Define> tmpKill = new HashSet<Define>(b.kill);
				Set<Define> tmpOut = new HashSet<Define>();
				//out = gen + (in-kill)
				tmpOut.addAll(b.gen);
				tmpOut.addAll(tmpIn);
				
				//in - kill
				for(Define din:tmpIn){
					for(Define dkill:tmpKill){
						if(din.equals(dkill)){
							tmpOut.remove(din);
						}
					}
				}
				if(tmpOut.size() == b.out.size()) change[i] = false;
				else change[i] = true;
				b.out = tmpOut;
			}
			
			for(boolean c: change)
				arechange = arechange || c;
		}
	}
	
	
	public Map<String,HashSet<Define>> total(){
		Map<String,HashSet<Define>> totalDefine = new HashMap<String,HashSet<Define>>();
		for(ICFGVariable var:this.vars){
			HashSet<Define> define = new HashSet<Define>();
			for(ICFGNode node:var.getWriteNode()){
				Define d = new Define(node.toString(),var.getName());
				define.add(d);
			}
			totalDefine.put(var.getName(), define);
		}
		return totalDefine;
	}
	
	public List<Block> analyse(){
		Map<String,HashSet<Define>> totalDefine = total();
		for(Block block:blocks){
			for(Define d:block.gen){
				String varName = d.getVarName();
				Set<Define> total = totalDefine.get(varName);
				for(Define d2:total){
					if(block.getNode()!=null && 
							d2.getNodeName().equals(block.getNode().toString())){}
					else block.kill.add(d2);
				}
			}
			
			block.pres = findPres(block.name);
		}
		reach(blocks);
		return blocks;
	}
	
/*	public List<Block> analyse(){
		Map<String,HashSet<Define>> totalDefine = total();
//		System.out.println(totalDefine);
//		System.out.println("开始迭代分析所有的block");
		for(Block block:blocks){
			if(block instanceof FlowBlock){
//				int i = 0;
				Set<Define> totalOut = new HashSet<Define>();
				Set<Define> totalGen = new HashSet<Define>();
				for(List<Block> bs:((FlowBlock)block).curs){
//					System.out.println("分析" + ((FlowBlock)block).curAct.get(i));
					
					Set<Define> totalKill = new HashSet<Define>();
					//计算所有基本块的kill集合
					for(Block b:bs){
						totalGen.addAll(b.gen);
						for(Define d:b.gen){
							String varName = d.getVarName();
							Set<Define> total = totalDefine.get(varName);
							for(Define d2:total){
								if(b.getNode()!=null && d2.getNodeName().equals(b.getNode().toString())){}
								else b.kill.add(d2);
							}
							totalKill.addAll(b.kill);
						}
					}
					block.kill.addAll(totalKill);
					reach(bs);
					for(Block b:bs){
						totalOut.addAll(b.out);
					}
//					i++;
				}
				block.gen.addAll(totalGen);
				block.out.addAll(totalOut);
//				System.out.println("shy + " + block.kill);
				for(Define out:totalOut){
					for(Define kill:block.kill){
						if(out.equals(kill)){
//							System.out.println("mm123");
//							System.out.println(block.out.contains(kill));
						}
					}
				}
			}
			
//			System.out.println(block);
//			System.out.println("gen :" + block.gen);
			for(Define d:block.gen){
				String varName = d.getVarName();
				Set<Define> total = totalDefine.get(varName);
				for(Define d2:total){
					if(block.getNode()!=null && d2.getNodeName().equals(block.getNode().toString())){}
					else block.kill.add(d2);
				}
			}
//			System.out.println("kill:" + block.kill);
//			System.out.println("==========");
			
		}
		reach(blocks);
		return blocks;
	}
*/	
	/*public static void test(){
		List<Block> bs = new ArrayList<Block>();
		Block b1 = new Block("B1");
		Block b2 = new Block("B2");
		Block b3 = new Block("B3");
		Block b4 = new Block("B4");
		Block exit = new Block("EXIT");
		
		b1.bgen.add("d1");
		b1.bgen.add("d2");
		b1.bgen.add("d3");
		b1.bkill.add("d4");
		b1.bkill.add("d5");
		b1.bkill.add("d6");
		b1.bkill.add("d7");
		
		b2.bgen.add("d4");
		b2.bgen.add("d5");
		b2.bkill.add("d1");
		b2.bkill.add("d2");
		b2.bkill.add("d7");
		b2.bpres.add(b1);
		b2.bpres.add(b4);
		
		b3.bgen.add("d6");
		b3.bkill.add("d3");
		b3.bpres.add(b2);
		
		b4.bgen.add("d7");
		b4.bkill.add("d1");
		b4.bkill.add("d4");
		b4.bpres.add(b2);
		b4.bpres.add(b3);
		
		exit.bpres.add(b4);
		
		bs.add(b1);
		bs.add(b2);
		bs.add(b3);
		bs.add(b4);
		bs.add(exit);
		
		for(int i=0;i<bs.size()-1;i++){
			Block b = bs.get(i);
			System.out.println(b.name);
			System.out.println("gen:");
			for(String s1:b.bgen){
				System.out.println(s1);
			}
			System.out.println("kill:");
			for(String s2:b.bkill){
				System.out.println(s2);
			}
			System.out.println("pre:");
			System.out.println(b.bpres);
			System.out.println("=====================");
		}
		System.out.println("-----------------");
		
		//开始迭代
		boolean arechange = true;
		int ct = 0;
		while(arechange){
			arechange = false;
			//防止死循环
			ct++;
			System.out.println("迭代次数  = " + ct);
			if(ct>5)return;
			boolean[] change = new boolean[bs.size()-1];
			//除了EXIT
			for(int i=0;i<bs.size()-1;i++){
				Block b = bs.get(i);
				System.out.println("block:" + b);
				//in
				List<Block> pres = b.bpres;
				if(pres.size()==0){}
				else{
					System.out.println("pres:" + pres);
					for(Block pre:pres){
						b.bin.addAll(pre.bout);
					}
				}
				b.bin = unique(b.bin);
				//out
				//in-kill
				List<String> tmpIn = new ArrayList<String>();
				tmpIn.addAll(b.bin);
				System.out.println("in  :" + b.bin);
				List<String> tmpKill = new ArrayList<String>();
				tmpKill.addAll(b.bkill);
				List<String> tmpOut = new ArrayList<String>();
				tmpOut.addAll(b.bgen);
				tmpIn.removeAll(tmpKill);
				tmpOut.addAll(tmpIn);
				
				if(unique(tmpOut).size()==b.bout.size())change[i] = false;
				else change[i] = true;
				
				b.bout = unique(tmpOut);
				System.out.println("out :" + b.bout);
				System.out.println(Arrays.toString(change));
				System.out.println("=============================");
			}
			
			for(boolean c: change)
				arechange = arechange || c;
		}
		
	}*/
	
	/*public static void main(String[] args) {
		DataFlowAnalyser.test();
	}*/
	
	public static List<String> unique(List<String> list){
		List<String> re = new ArrayList<String>();
		for(String ele:list){
			if(!re.contains(ele))
				re.add(ele);
		}
		return re;
	}
}
