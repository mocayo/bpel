package com.lm.test;

public class Lake {
	private String lakeName;
	private double lakeWaterDepth;
	private double lakeArea;
	private String lakeType;
	private String lakeManager;

	public Lake(String lakeName) {
		this.lakeName = lakeName;
	}

	public Lake(String lakeName, String lakeType) {
		this.lakeName = lakeName;
		this.lakeType = lakeType;
	}

	public Lake(String lakeName, double lakeWaterDepth, double lakeArea) {
		this.lakeName = lakeName;
		this.lakeWaterDepth = lakeWaterDepth;
		this.lakeArea = lakeArea;
	}

	@Override
	public String toString() {
		return "名称：" + this.lakeName + "\n水深：" + this.lakeWaterDepth + "m\n面积：" + this.lakeArea + "㎞²";
	}

	public String getLakeName() {
		return lakeName;
	}

	public void setLakeName(String lakeName) {
		this.lakeName = lakeName;
	}

	public double getLakeWaterDepth() {
		return lakeWaterDepth;
	}

	public void setLakeWaterDepth(double lakeWaterDepth) {
		this.lakeWaterDepth = lakeWaterDepth;
	}

	public double getLakeArea() {
		return lakeArea;
	}

	public void setLakeArea(double lakeArea) {
		this.lakeArea = lakeArea;
	}

	public String getLakeType() {
		return lakeType;
	}

	public void setLakeType(String lakeType) {
		this.lakeType = lakeType;
	}

	public String getLakeManager() {
		return lakeManager;
	}

	public void setLakeManager(String lakeManager) {
		this.lakeManager = lakeManager;
	}
	
	public static void main(String[] args) {
		Lake lake1 = new Lake("太湖",80,2445);
		System.out.println(lake1);
		
		Lake lake2 = new Lake("河海东湖");
		System.out.println(lake2);
	}
	
}
