package com.lm.test;

import java.util.Calendar;
import java.util.TimerTask;

public class TestTask extends TimerTask{

	@Override
	public void run() {
		Calendar now = Calendar.getInstance();
//		if(now.get(Calendar.HOUR_OF_DAY)<14){
//			System.out.println("hhh");
//			return;
//		}
//		
		System.out.println("sc");
		System.out.println(now.get(Calendar.HOUR_OF_DAY) + "时" + now.get(Calendar.MINUTE) + "分" + now.get(Calendar.SECOND) + "秒");
		System.out.println("执行。。。");
		System.out.println(this.scheduledExecutionTime());
	}
	
}
