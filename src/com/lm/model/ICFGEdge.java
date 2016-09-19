package com.lm.model;


public class ICFGEdge {
	private ICFGNode source;
	private ICFGNode target;
	private Type type;
	
	public enum Type{
		SEQUENCE,CONCURRENCY,CHOICE,LINK;
	}

	public ICFGEdge(ICFGNode source,ICFGNode target,Type type){
		this.source = source;
		this.target = target;
		this.type = type;
	}
	
	@Override
	public String toString(){
		return "(" + this.source.getName() + "_" + this.source.getId() + "," + this.target.getName() +"_" + this.target.getId() + "," + this.type.toString() + ")";
	}
	
	public ICFGNode getSource() {
		return source;
	}

	public void setSource(ICFGNode source) {
		this.source = source;
	}

	public ICFGNode getTarget() {
		return target;
	}

	public void setTarget(ICFGNode target) {
		this.target = target;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}


}
