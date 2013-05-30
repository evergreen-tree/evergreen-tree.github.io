package com.efs.bo.serie;

import com.efs.bo.PieData;

public class PieSerie implements ISerie{
	public String type;
	public String name;
	PieData[] data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public PieSerie setName(String name) {
		this.name = name;
		return this;
	}

	public PieSerie(String type, String name, PieData[] data) {
		super();
		this.type = type;
		this.name = name;
		this.data = data;
	}

	public PieData[] getData() {
		return data;
	}

	public void setData(PieData[] data) {
		this.data = data;
	}
}
