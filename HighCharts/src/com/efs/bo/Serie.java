package com.efs.bo;

public class Serie {
	public String type;
	public String name;
	float[] data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Serie setName(String name) {
		this.name = name;
		return this;
	}

	public float[] getData() {
		return data;
	}

	public Serie setData(float[] data) {
		this.data = data;
		return this;
	}

	public Serie(String type, String name, float[] data) {
		super();
		this.type = type;
		this.name = name;
		this.data = data;
	}
}
