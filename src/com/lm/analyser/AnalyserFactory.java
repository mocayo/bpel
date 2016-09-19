package com.lm.analyser;

import org.apache.ode.bpel.compiler.bom.Activity;

import com.lm.model.ICFGModel;

public class AnalyserFactory {
	
	/********************************************************************************
	 * createAnalyser: to produce a corresponding analyzer for activity 
	 * @param act 	the activity to be analyzed
	 * @param model	the model store the corresponding information of activity 
	 * @return the analyzer for act analysis
	 ********************************************************************************/
	public static ActivityAnalysis createAnalyser(Activity act,ICFGModel model){
		
		ActivityAnalysis analyzer = null;
		
		switch (ActivityTypeIndentifier.typeInfo(act)) {
		
		// basic activity activity analyzer
		case ASSIGN: // analyze <assign>
			analyzer = new AssignAnalysis(act,model);
			break;

		case RECEIVE: // analyze <receive>
			analyzer = new ReceiveAnalysis(act,model);
			break;

		case INVOKE: // analyze <invoke>
			analyzer = new InvokeAnalysis(act,model);
			break;
		
		case REPLY:
			analyzer = new ReplyAnalysis(act,model);
			break;
			
		case EMPTY: // analyze <empty>
			analyzer = new EmptyAnalysis(act,model);
			break;
		
		case WAIT:
			analyzer = new WaitAnalysis(act,model);
			break;
			
		// choice construct analyze
		case IF: // analyze <if>
			analyzer = new IfAnalysis(act,model);
			break;

		case PICK: // analyze <pick>
			analyzer = new PickAnalysis(act,model);
			break;

		case SWITCH: // analyze <switch>
			analyzer = new SwitchAnalysis(act,model);
			break;

		// composite activity analyze
		case SEQUENCE: // analyze <empty>
			analyzer = new SequenceAnalysis(act,model);
			break;
			
		case FLOW:	// analyze <empty>					
			analyzer = new FlowAnalysis(act,model);
			break;
		
		case FOREACH:
			analyzer = new ForEachAnalysis(act,model);
			break;
		
		case SCOPE:
			analyzer = new ScopeAnalysis(act,model);
			break;
			
		// loop activity analyze	
		case WHILE:
			analyzer = new WhileAnalysis(act,model);
			break;
			
		case REPEATUNTIL:
			analyzer = new RepeatUntilAnalysis(act,model);
			break;
			
		default:
			break;
		}
		return analyzer;
	}
}