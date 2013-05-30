package com.efs.bo;

public class Title {

	String text;
	int x; // center

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Title(String text, int x) {
		super();
		this.text = text;
		this.x = x;
	}
}
