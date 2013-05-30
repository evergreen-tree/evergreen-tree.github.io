package com.efs.bo;

public class PlotLines {
	int value;
	int width;
	String color;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public PlotLines(int value, int width, String color) {
		super();
		this.value = value;
		this.width = width;
		this.color = color;
	}
}
