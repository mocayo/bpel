package com.lm.test;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
	
	public static List<String> addOperators(String num, int target) {
	    List<String> res = new ArrayList<>();
	    StringBuilder sb = new StringBuilder();
	    dfs(res, sb, num, 0, target, 0, 0);
	    return res;

	}
	
	public static void dfs(List<String> res, StringBuilder sb, String num, int pos, int target, long prev, long multi) { 
	    if(pos == num.length()) {
	        if(target == prev) 
	        	res.add(sb.toString());
	        return;
	    }
	    for(int i = pos; i < num.length(); i++) {
	        if(num.charAt(pos) == '0' && i != pos) 
	        	break;
	        long curr = Long.parseLong(num.substring(pos, i + 1));
	        int len = sb.length();
	        if(pos == 0) {
	            dfs(res, sb.append(curr), num, i + 1, target, curr, curr); 
	            sb.setLength(len);
	        } 
	        else {
	            dfs(res, sb.append("+").append(curr), num, i + 1, target, prev + curr, curr); 
	            sb.setLength(len);
	            dfs(res, sb.append("-").append(curr), num, i + 1, target, prev - curr, -curr); 
	            sb.setLength(len);
	            dfs(res, sb.append("*").append(curr), num, i + 1, target, prev - multi + multi * curr, multi * curr); 
	            sb.setLength(len);
	        }
	    }
	}
	
	public static void main(String[] args) {
		String num = "232";
		int target = 8;
		
		List<String> result = addOperators(num, target);
		
		for(String s : result) {
			System.out.println(s);
		}

	}

}
