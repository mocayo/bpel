package com.lm.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ICFGModel {

	private static int MODELCOUNTER = 0; // 并发控制流图模型计数器

	public static int getModelCount() {
		return ICFGModel.MODELCOUNTER;
	}

	private int id;
	private String name;
	private ICFGNode startNode;
	private ICFGNode endNode;
	private Set<ICFGNode> nodeSet = new HashSet<ICFGNode>();
	private Set<ICFGEdge> edgeSet = new HashSet<ICFGEdge>();
	private Set<ICFGLink> linkSet = new HashSet<ICFGLink>();
	private Set<ICFGCondition> conditionSet = new HashSet<ICFGCondition>();
	private Set<ICFGVariable> varSet = new HashSet<ICFGVariable>();
	
	private List<ICFGNode> nodeList = new ArrayList<ICFGNode>();
	private List<ICFGEdge> edgeList = new ArrayList<ICFGEdge>();

	private int varCounter = 0;
	private int nodeCounter = 0;
	private int linkCounter = 0;
	
	public List<Block> blocks = new ArrayList<Block>();
	public List<Block> flowBlocks = new ArrayList<Block>();
	public boolean isFlow = false;

	public ICFGModel(String name) {
		this.name = name;
		this.id = ++MODELCOUNTER;

		this.startNode = new ICFGNode(this.getName() + "_start");
		this.endNode = new ICFGNode(this.getName() + "_end");
		this.addNode(startNode);
		this.addNode(endNode);
	}

	public void addVar(ICFGVariable var) {
		if (var != null)
			varSet.add(var);
	}

	public void addPl(ICFGLink link) {
		if (link != null)
			this.linkSet.add(link);
	}

	public void addEdge(ICFGEdge edge) {
		if (edge != null){
			this.edgeSet.add(edge);
		}
	}

	public void addNode(ICFGNode node) {
		node.setId(++nodeCounter);
		nodeSet.add(node);
	}
	
	public void addLink(ICFGLink link){
		if(link!=null){
			++linkCounter;
			linkSet.add(link);
		}
	}

	public ICFGNode findNode(int node_id) {
		for (ICFGNode node : this.nodeSet) {
			if (node.getId() == node_id)
				return node;
		}
		return null;
	}
	
	public ICFGLink findLink(String linkName){
		if(linkName==null) return null;
		for(ICFGLink link:this.linkSet){
			if(link.getName().equals(linkName))
				return link;
		}
		return null;
	}

	public ICFGVariable findVar(String varName) {
		for (ICFGVariable var : this.varSet) {
			if (var.getName().equals(varName))
				return var;
		}
		return null;
	}

	public void printVarAccessInfo() {
		Set<ICFGNode> nodeSet = this.getNodeSet();
		if (!nodeSet.isEmpty()) {
			int size = nodeSet.size();
			for (int id = 1; id <= size; ++id) {
				ICFGNode node = this.findNode(id);
				if (!node.getReadVar().isEmpty() || !node.getWriteVar().isEmpty()) {
					// if node contains data access
					Set<ICFGVariable> readVarSet = node.getReadVar();
					Set<ICFGVariable> writeVarSet = node.getWriteVar();
					System.out.print("\n" + id + ":");
					if (!readVarSet.isEmpty()) {
						System.out.print("\n read access ");
						for (ICFGVariable var : readVarSet)
							System.out.print(var.getName() + " ");
					}
					if (!writeVarSet.isEmpty()) {
						System.out.print("\n write access ");
						for (ICFGVariable var : writeVarSet)
							System.out.print(var.getName() + " ");
					}
					System.out.println();
				}
			}
		}
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

	public ICFGNode getStartNode() {
		return startNode;
	}

	public void setStartNode(ICFGNode startNode) {
		this.startNode = startNode;
	}

	public ICFGNode getEndNode() {
		return endNode;
	}

	public void setEndNode(ICFGNode endNode) {
		this.endNode = endNode;
	}

	public Set<ICFGNode> getNodeSet() {
		return nodeSet;
	}

	public void setNodeSet(Set<ICFGNode> nodeSet) {
		this.nodeSet = nodeSet;
	}

	public Set<ICFGEdge> getEdgeSet() {
		return edgeSet;
	}

	public void setEdgeSet(Set<ICFGEdge> edgeSet) {
		this.edgeSet = edgeSet;
	}

	public Set<ICFGLink> getLinkSet() {
		return linkSet;
	}

	public void setLinkSet(Set<ICFGLink> linkSet) {
		this.linkSet = linkSet;
	}

	public Set<ICFGCondition> getConditionSet() {
		return conditionSet;
	}

	public void setConditionSet(Set<ICFGCondition> conditionSet) {
		this.conditionSet = conditionSet;
	}

	public Set<ICFGVariable> getVarSet() {
		return varSet;
	}

	public void setVarSet(Set<ICFGVariable> varSet) {
		this.varSet = varSet;
	}

	public List<ICFGNode> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<ICFGNode> nodeList) {
		this.nodeList = nodeList;
	}
	
	public List<ICFGEdge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(List<ICFGEdge> edgeList) {
		this.edgeList = edgeList;
	}

	public int getVarCounter() {
		return varCounter;
	}

	public void setVarCounter(int varCounter) {
		this.varCounter = varCounter;
	}

	public int getNodeCounter() {
		return nodeCounter;
	}

	public void setNodeCounter(int nodeCounter) {
		this.nodeCounter = nodeCounter;
	}

	public int getLinkCounter() {
		return linkCounter;
	}

	public void setLinkCounter(int linkCounter) {
		this.linkCounter = linkCounter;
	}

}
