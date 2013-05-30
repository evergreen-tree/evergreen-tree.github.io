package com.efs.bo;

public class Chart {

	String renderTo;
	String type;
	int marginRight;
	int marginBottom;
	
	String plotBackgroundColor;
    String plotBorderWidth;
    boolean plotShadow;

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
	public Chart(String renderTo, String plotBackgroundColor,
			String plotBorderWidth, boolean plotShadow) {
		super();
		this.renderTo = renderTo;
		this.plotBackgroundColor = plotBackgroundColor;
		this.plotBorderWidth = plotBorderWidth;
		this.plotShadow = plotShadow;
	}

	public Chart(String renderTo){
		super();
		this.renderTo = renderTo;
	}

	public String getPlotBackgroundColor() {
		return plotBackgroundColor;
	}

	public void setPlotBackgroundColor(String plotBackgroundColor) {
		this.plotBackgroundColor = plotBackgroundColor;
	}

	public String getPlotBorderWidth() {
		return plotBorderWidth;
	}

	public void setPlotBorderWidth(String plotBorderWidth) {
		this.plotBorderWidth = plotBorderWidth;
	}

	public boolean isPlotShadow() {
		return plotShadow;
	}

	public void setPlotShadow(boolean plotShadow) {
		this.plotShadow = plotShadow;
	}
}
