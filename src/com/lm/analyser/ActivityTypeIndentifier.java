package com.lm.analyser;

import org.apache.ode.bpel.compiler.bom.Activity;

public class ActivityTypeIndentifier{
	
	/***********************************************
	 * 		���������
	 ***********************************************/
	public enum ActivityType{
			
		UNKNOWN,	// δ֪�Ļ���
		
		ASSIGN,		// <assign.../>
		
		COMPENSATE,	// <compensate.../>
		
		COMPENSATESCOPE, // <compensateScope.../>
		
		EMPTY,		// <empty.../>
		
		FLOW,		// <flow.../>
		
		FOREACH,	// <forEach.../>
		
		IF,			// <if.../>
		
		REPEATUNTIL,	// <repeatUntil.../>
		
		REPLY,		// <reply.../>
		
		RETHROW,	// <rethorw.../>
		
		INVOKE,		// <invoke.../>
		
		PICK,		// <pick.../>
		
		RECEIVE,	// <receive.../>
		
		SCOPE,		// <scope.../>
		
		SEQUENCE,	// <sequence.../>
		
		SWITCH,		// <switch.../>
		
		TERMINATE,	// <terminate.../>
		
		THROW,		// <throw.../>
		
		WAIT,		// <wait.../>
		
		WHILE,		// <while.../>		
	};
	
	/*******************************************************
	 * typeInfo: ���ݻ���ͷ��ض�Ӧ��Activity��ö��ֵ
	 *******************************************************/
	public static ActivityType typeInfo(Activity activity){
		
		String info = activity.getType().getLocalPart();	//��û���Ƶı��ز���
		
		if(info.equals("assign"))
			return ActivityType.ASSIGN;
		if(info.equals("compensate"))
			return ActivityType.COMPENSATE;
		if(info.equals("compensateScope"))
			return ActivityType.COMPENSATESCOPE;
		if(info.equals("empty"))
			return ActivityType.EMPTY;
		if(info.equals("flow"))
			return ActivityType.FLOW;
		if(info.equals("forEach"))
			return ActivityType.FOREACH;
		if(info.equals("if"))
			return ActivityType.IF;
		if(info.equals("repeatUntil"))
			return ActivityType.REPEATUNTIL;
		if(info.equals("reply"))
			return ActivityType.REPLY;
		if(info.equals("rethrow"))
			return ActivityType.RETHROW;
		if(info.equals("invoke"))
			return ActivityType.INVOKE;
		if(info.equals("pick"))
			return ActivityType.PICK;
		if(info.equals("receive"))
			return ActivityType.RECEIVE;
		if(info.equals("scope"))
			return ActivityType.SCOPE;
		if(info.equals("sequence"))
			return ActivityType.SEQUENCE;
		if(info.equals("switch"))
			return ActivityType.SWITCH;
		if(info.equals("terminate"))
			return ActivityType.TERMINATE;
		if(info.equals("throw"))
			return ActivityType.THROW;
		if(info.equals("wait"))
			return ActivityType.WAIT;
		if(info.equals("while"))
			return ActivityType.WHILE;
		
		return ActivityType.UNKNOWN;
	}

	/***********************************************************
	 * �жϻ�Ƿ�Ϊѡ��ṹ�Ļ
	 ***********************************************************/
	public static boolean isChoiceActivity(Activity activity){
		
		ActivityType typeinfo = ActivityTypeIndentifier.typeInfo(activity);		
		return typeinfo == ActivityType.IF || typeinfo == ActivityType.SWITCH || typeinfo == ActivityType.PICK;
	}
	
	/*******************************************************
	 *	�жϻ�Ƿ�Ϊ���ϻ
	 ********************************************************/
	public static boolean isCompositeActivity(Activity activity){
		ActivityType typeinfo = ActivityTypeIndentifier.typeInfo(activity);
		return typeinfo == ActivityType.FLOW || typeinfo == ActivityType.SEQUENCE || typeinfo == ActivityType.FOREACH;
	}
}