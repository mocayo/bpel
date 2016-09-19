package com.lm.model;

public class Define {

	private String nodeName;
	private String varName;

	public Define(String node, String var) {
		this.nodeName = node;
		this.varName = var;
	}

	@Override
	public String toString() {
		return "(" + this.varName + "," + this.nodeName + ")";
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)return false;
		if (obj == null)return false;
		if (this.getClass() != obj.getClass())return false;
		Define other = (Define) obj;
		if(this.nodeName.equals(other.getNodeName()) && this.varName.equals(other.getVarName()))return true;
		return false;
	}

}
