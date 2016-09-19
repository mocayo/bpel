package com.lm.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ode.bpel.compiler.bom.Variable;

public class ICFGVariable {
	private String name;
	private String type;
	private String location;
	
	private List<ICFGNode> readNode = new ArrayList<ICFGNode>();
	private List<ICFGNode> writeNode = new ArrayList<ICFGNode>();
	
	private Variable var;
	
	@Override
	public String toString(){
		return "<name= " + name + " ,type= " + type + ">";
	}
	
	public ICFGVariable(Variable var){
		this.var = var;
	}
	
	public void addReadNode(ICFGNode node){
		if(node!=null)
			this.readNode.add(node);
	}
	
	public void addWriteNode(ICFGNode node){
		if(node!=null)
			this.writeNode.add(node);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<ICFGNode> getReadNode() {
		return readNode;
	}

	public void setReadNode(List<ICFGNode> readNode) {
		this.readNode = readNode;
	}

	public List<ICFGNode> getWriteNode() {
		return writeNode;
	}

	public void setWriteNode(List<ICFGNode> writeNode) {
		this.writeNode = writeNode;
	}

	public Variable getVar() {
		return var;
	}

	public void setVar(Variable var) {
		this.var = var;
	}

}
