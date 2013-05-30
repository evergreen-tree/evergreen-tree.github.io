package com.efs.bo;

public class Tooltip {

    public Tooltip() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tooltip(String formatter, String pointFormat, int percentageDecimals) {
		super();
		this.pointFormat = pointFormat;
		this.percentageDecimals = percentageDecimals;
	}


    String pointFormat;
	int percentageDecimals ;

	public String getPointFormat() {
		return pointFormat;
	}

	public void setPointFormat(String pointFormat) {
		this.pointFormat = pointFormat;
	}

	public int getPercentageDecimals() {
		return percentageDecimals;
	}

	public void setPercentageDecimals(int percentageDecimals) {
		this.percentageDecimals = percentageDecimals;
	}
}
