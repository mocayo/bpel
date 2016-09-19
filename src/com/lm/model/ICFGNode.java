package com.lm.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ICFGNode {
	
	private int id;
	private String name;
	private String condition;
	private ICFGCondition cond = new ICFGCondition();
	
	private List<ICFGEdge> outEdges = new ArrayList<ICFGEdge>();
	private List<ICFGEdge> inEdges = new ArrayList<ICFGEdge>();
	
	private List<ICFGLink> emitLinks = new ArrayList<ICFGLink>();
	private List<ICFGLink> inLinks = new ArrayList<ICFGLink>();
	
	private Set<ICFGVariable> readVar = new HashSet<ICFGVariable>();
	private Set<ICFGVariable> writeVar = new HashSet<ICFGVariable>();
	
	private List<String> in = new ArrayList<String>();
	private List<String> out = new ArrayList<String>();
	private List<String> gen = new ArrayList<String>();
	private List<String> kill = new ArrayList<String>();
	
	public boolean isBasic = true;
	
	public ICFGNode(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name + "_" + this.id;
	}
	
	//添加ReadVariable
	public void addReadVariable(ICFGVariable var){
		if(var != null)
			this.readVar.add(var);
	}
	
	//添加WriteVariable
	public void addWriteVariable(ICFGVariable var){
		if(var != null)
			this.writeVar.add(var);
	}
	
	//添加节点的入边&出边
	public void addInEdge(ICFGEdge edge){
		if(edge!=null)
			this.inEdges.add(edge);
	}
	public void addInEdge(List<ICFGEdge> edges){
		if(edges!=null)
			this.inEdges.addAll(edges);
	}
	public void addOutEdge(ICFGEdge edge){
		if(edge!=null)
			this.outEdges.add(edge);
	}
	public void addOutEdge(List<ICFGEdge> edges){
		if(edges!=null)
			this.outEdges.addAll(edges);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public ICFGCondition getCond() {
		return cond;
	}

	public void setCond(ICFGCondition cond) {
		this.cond = cond;
	}

	public List<ICFGEdge> getOutEdges() {
		return outEdges;
	}

	public void setOutEdges(List<ICFGEdge> outEdges) {
		this.outEdges = outEdges;
	}

	public List<ICFGEdge> getInEdges() {
		return inEdges;
	}

	public void setInEdges(List<ICFGEdge> inEdges) {
		this.inEdges = inEdges;
	}

	public List<ICFGLink> getEmitLinks() {
		return emitLinks;
	}

	public void setEmitLinks(List<ICFGLink> emitLinks) {
		this.emitLinks = emitLinks;
	}

	public List<ICFGLink> getInLinks() {
		return inLinks;
	}

	public void setInLinks(List<ICFGLink> inLinks) {
		this.inLinks = inLinks;
	}

	public Set<ICFGVariable> getReadVar() {
		return readVar;
	}

	public void setReadVar(Set<ICFGVariable> readVar) {
		this.readVar = readVar;
	}

	public Set<ICFGVariable> getWriteVar() {
		return writeVar;
	}

	public void setWriteVar(Set<ICFGVariable> writeVar) {
		this.writeVar = writeVar;
	}

	public List<String> getIn() {
		return in;
	}

	public void setIn(List<String> in) {
		this.in = in;
	}

	public List<String> getOut() {
		return out;
	}

	public void setOut(List<String> out) {
		this.out = out;
	}

	public List<String> getGen() {
		return gen;
	}

	public void setGen(List<String> gen) {
		this.gen = gen;
	}

	public List<String> getKill() {
		return kill;
	}

	public void setKill(List<String> kill) {
		this.kill = kill;
	}

}
