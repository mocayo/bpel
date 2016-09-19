package com.lm.analyser;

public class ActivityID {
	private static int id = 0;
	
	public static int getID(){
		return id++;
	}
}
