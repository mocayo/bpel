package com.lm.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Block {
	
	public String name;
	private ICFGNode node;
	
	public Set<Define> in = new HashSet<Define>();
	public Set<Define> out = new HashSet<Define>();
	public Set<Define> gen = new HashSet<Define>();
	public Set<Define> kill = new HashSet<Define>();
	public List<Block> pres = new ArrayList<Block>();
	
	/*public List<String> bin = new ArrayList<String>();
	public List<String> bout = new ArrayList<String>();
	public List<String> bgen = new ArrayList<String>();
	public List<String> bkill = new ArrayList<String>();
	public List<Block> bpres = new ArrayList<Block>();*/
	
	public Block(String name){
		this.name = name;
	}
	
	public Block(ICFGNode node){
		this.node = node;
		this.name = node.toString();  
	}
	
	@Override
	public String toString(){
		return this.name;
	}

	public ICFGNode getNode() {
		return node;
	}

	public void setNode(ICFGNode node) {
		this.node = node;
	}
	
}
