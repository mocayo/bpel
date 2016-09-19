package com.lm.analyser;

import java.util.ArrayList;
import java.util.List;

import org.apache.ode.bpel.compiler.bom.Variable;

public class VariableTable {
	
	public static List<Variable> variables = new ArrayList<Variable>();
	
	public static List<String> varNames = new ArrayList<String>();
	
	public static boolean contains(String name){
		boolean boo = false;
		if(varNames.contains(name))boo = true;
		return boo;
	}
	
	public static Variable getVarByName(String name){
		if(VariableTable.contains(name)){
			int index = varNames.indexOf(name);
			return variables.get(index);
		}else{
			return null;
		}
	}
}
