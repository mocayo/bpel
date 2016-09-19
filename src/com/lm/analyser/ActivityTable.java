package com.lm.analyser;

import java.util.ArrayList;
import java.util.List;

public class ActivityTable {

	public class ActivityItem {
		String activity;
		int id;
		
		public ActivityItem(String act,int id){
			this.activity = act;
			this.id = id;
		}
		
		@Override
		public String toString() {
			return activity + "-" + id;
		}

	}
	
	public static List<ActivityItem> acts = new ArrayList<ActivityItem>();
	
	public static void main(String[] args) {
		ActivityTable at = new ActivityTable();
		ActivityItem ai = at.new ActivityItem("receive",1);
		
		ActivityTable.acts.add(ai);
		System.out.println(ActivityTable.acts);
	}
}
