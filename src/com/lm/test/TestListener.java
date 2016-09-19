package com.lm.test;

import java.util.Timer;

public class TestListener {

	public static void main(String[] args) {
		Timer timer = new Timer("test timer", true);
		TestTask task = new TestTask();
		timer.schedule(task, 0, 1000 * 5);
	}
}
