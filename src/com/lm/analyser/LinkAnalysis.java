package com.lm.analyser;

import java.util.List;

import org.apache.ode.bpel.compiler.bom.Activity;
import org.apache.ode.bpel.compiler.bom.LinkSource;
import org.apache.ode.bpel.compiler.bom.LinkTarget;

import com.lm.model.ICFGEdge;
import com.lm.model.ICFGLink;
import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;

public class LinkAnalysis {
	
	/************************************************************************************
	 * processSrcTarg:	�����е�<sources> & <targets>
	 * @param sources	List<LinkSource>
	 * @param targets   List<LinkTarget>
	 * @param act the activity be analyzed
	 ************************************************************************************/
	public static void processSrcTarg(List<LinkSource> sources, List<LinkTarget> targets, ICFGNode from,ICFGNode to, Activity act,ICFGModel model){
		
		// ���<sources>�Ĵ���
		if (sources != null && !sources.isEmpty()) {	
			for(LinkSource lnk_src:sources){
				String linkName = lnk_src.getLinkName();
				ICFGLink link = model.findLink(linkName);
				if(link==null){
					ICFGLink newLink = new ICFGLink(linkName);
					newLink.setSource(from);
					from.getEmitLinks().add(newLink);
					model.addLink(newLink);
				}
			}
		}		
		
		// ���<targets>�Ĵ���
		if (targets != null && !targets.isEmpty()) {
			for(LinkTarget lnk_tgt:targets){
				String linkName = lnk_tgt.getLinkName();
				ICFGLink link = model.findLink(linkName);
				if(link==null){
					
				}else{
					ICFGNode src = link.getSource();
					link.setTarget(to);
					ICFGEdge edge = new ICFGEdge(src,to,ICFGEdge.Type.LINK);
					model.addEdge(edge);
				}
			}
		}
		
	}
}
