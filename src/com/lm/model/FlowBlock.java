package com.lm.model;

import java.util.ArrayList;
import java.util.List;

public class FlowBlock extends Block{
	
	public List<String> curAct = new ArrayList<String>();
	public List<List<Block>> curs = new ArrayList<List<Block>>();
	
	public FlowBlock(String name){
		super(name);
	}
	
	public FlowBlock(ICFGNode node){
		super(node);
	}
	
	public void print(){
		System.out.println(curAct);
		for(List<Block> cur:curs){
			for(Block b:cur){
				System.out.println("name : " + b.name);
				System.out.println("gen : " + b.gen);
				System.out.println("//===========//");
			}
		}
		System.out.println("//////////////////////////////////////");
	}
}
