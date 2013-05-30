package com.efs.bo;

public class Subtitle {

	String text;
	int x;

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

	public Subtitle(String text, int x) {
		super();
		this.text = text;
		this.x = x;
	}
}
