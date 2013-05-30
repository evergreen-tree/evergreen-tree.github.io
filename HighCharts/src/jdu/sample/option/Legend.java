package jdu.sample.option;

public class Legend {
	String layout;
	String align;
	String verticalAlign;
	int x;
	int y;
	int borderWidth;

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public Legend(String layout, String align, String verticalAlign, int x,
			int y, int borderWidth) {
		super();
		this.layout = layout;
		this.align = align;
		this.verticalAlign = verticalAlign;
		this.x = x;
		this.y = y;
		this.borderWidth = borderWidth;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getVerticalAlign() {
		return verticalAlign;
	}

	public void setVerticalAlign(String verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}
}
