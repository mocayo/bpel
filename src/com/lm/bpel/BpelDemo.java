package com.lm.bpel;

import java.io.File;
import java.io.IOException;


import com.lm.analyser.ProcessAnalysis;
import com.lm.parser.BpelParser;

public class BpelDemo {
	
	public static void main(String[] args) {
//		String fileName = "D://purchaseOrderProcess.bpel";
		String fileName = "E://TravelProcess.bpel";
		BpelParser parser = BpelParser.newBpelParser();
		try {
			parser.parseBpelFile(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ProcessAnalysis pAnalysis = new ProcessAnalysis(parser);
		pAnalysis.analysis();
	}
}
