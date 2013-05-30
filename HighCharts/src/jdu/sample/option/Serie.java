package jdu.sample.option;

public class Serie {
	public String name;
	float[] data;

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

	public Serie(String name, float[] data) {
		super();
		this.name = name;
		this.data = data;
	}
}
