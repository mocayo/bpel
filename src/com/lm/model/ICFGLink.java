package com.lm.model;

public class ICFGLink {

	private String name;
	private ICFGNode source;
	private ICFGNode target;

	public ICFGLink(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
