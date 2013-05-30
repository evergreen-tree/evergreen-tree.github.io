package jdu.sample.option;

public class Chart {

	String renderTo;
	String type;
	int marginRight;
	int marginBottom;

	public String getRenderTo() {
		return renderTo;
	}

	public void setRenderTo(String renderTo) {
		this.renderTo = renderTo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMarginRight() {
		return marginRight;
	}

	public void setMarginRight(int marginRight) {
		this.marginRight = marginRight;
	}

	public int getMarginBottom() {
		return marginBottom;
	}

	public void setMarginBottom(int marginBottom) {
		this.marginBottom = marginBottom;
	}

	public Chart(String renderTo, String type, int marginRight, int marginBottom) {
		super();
		this.renderTo = renderTo;
		this.type = type;
		this.marginRight = marginRight;
		this.marginBottom = marginBottom;
	}
}
