package com.efs.bo;

public class Pie {
	private boolean allowPointSelect;
	private String cursor = "pointer";
	private DataLabels dataLabels;

	public boolean isAllowPointSelect() {
		return allowPointSelect;
	}

	public void setAllowPointSelect(boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public DataLabels getDataLabels() {
		return dataLabels;
	}

	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}
}
