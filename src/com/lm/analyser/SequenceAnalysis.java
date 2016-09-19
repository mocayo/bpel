package com.lm.analyser;

import java.util.List;

import org.apache.ode.bpel.compiler.bom.Activity;
import org.apache.ode.bpel.compiler.bom.LinkSource;
import org.apache.ode.bpel.compiler.bom.LinkTarget;
import org.apache.ode.bpel.compiler.bom.SequenceActivity;

import com.lm.model.Block;
import com.lm.model.ICFGEdge;
import com.lm.model.ICFGEdge.Type;
import com.lm.model.ICFGModel;
import com.lm.model.ICFGNode;
import com.lm.model.Pair;

public class SequenceAnalysis extends ActivityAnalysis {

	public SequenceAnalysis(Activity activity,ICFGModel model) {
		super(activity,model);
	}

	@Override
	public Pair<ICFGNode,ICFGNode> doAnalysis(String s) {
		SequenceActivity seqAct = (SequenceActivity) this._activity;

		String seqName = seqAct.getName();
		if (seqName == null || seqName.equals(""))
			seqName = "sequence";
		if (s != "")
			seqName = seqName + s;
		
		//初始化开始和结束节点
		ICFGNode seqStart = new ICFGNode(seqName + "_start");
		ICFGNode seqEnd = new ICFGNode(seqName + "_end");
		seqStart.isBasic = false;
		seqEnd.isBasic = false;
		this._model.addNode(seqStart);
		this._model.getNodeList().add(seqStart);
		this._model.addNode(seqEnd);
		
		Block seq_start = new Block(seqStart);
		Block seq_end = new Block(seqEnd);
		
		if(this._model.isFlow){
			
		}else{
			this._model.blocks.add(seq_start);
		}
		
		// 处理以该<sequence>关联的links
		List<LinkSource> sources = seqAct.getLinkSources();
		List<LinkTarget> targets = seqAct.getLinkTargets();

		// 分析 <sources> & <targets>
		LinkAnalysis.processSrcTarg(sources, targets, seqStart, seqEnd, seqAct,this._model);
		
		List<Activity> activities = seqAct.getActivities();
		ICFGNode front = seqStart;
		
		//分析sequence结构
		for(Activity act: activities){
			Pair<ICFGNode,ICFGNode> result = null;
			//System.out.println("开始解析" + ActivityTypeIndentifier.typeInfo(act) + " Activity");
			ActivityAnalysis analyser = AnalyserFactory.createAnalyser(act, _model);
			if(analyser!=null){
				result = analyser.doAnalysis(s);
				if(result!=null){
					ICFGEdge seqEdge = new ICFGEdge(front,result.getFirst(),Type.SEQUENCE);
					this._model.addEdge(seqEdge);
					front.addOutEdge(seqEdge);
					result.getFirst().addInEdge(seqEdge);
					front = result.getSecond();
				}
			}
		}
		this._model.getNodeList().add(seqEnd);
		
		if(this._model.isFlow){
			
		}else{
			this._model.blocks.add(seq_end);
		}
		//add edges
		ICFGEdge edge = new ICFGEdge(front,seqEnd,Type.SEQUENCE);
		this._model.addEdge(edge);
		front.addOutEdge(edge);
		seqEnd.addInEdge(edge);
		return new Pair<ICFGNode,ICFGNode>(seqStart,seqEnd);
	}

}
