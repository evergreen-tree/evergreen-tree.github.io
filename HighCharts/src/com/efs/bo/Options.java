package com.efs.bo;

import java.util.List;

import com.efs.bo.serie.ISerie;



public class Options {
	Chart chart;
	Title title;
	Subtitle subtitle;
	XAxis xAxis;
	YAxis yAxis;
	Tooltip tooltip;
	Legend legend;
	List<? extends ISerie> series;
	
	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Subtitle getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(Subtitle subtitle) {
		this.subtitle = subtitle;
	}

	public XAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(XAxis xAxis) {
		this.xAxis = xAxis;
	}

	public YAxis getyAxis() {
		return yAxis;
	}

	public void setyAxis(YAxis yAxis) {
		this.yAxis = yAxis;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public Legend getLegend() {
		return legend;
	}

	public void setLegend(Legend legend) {
		this.legend = legend;
	}

	public List<? extends ISerie> getSeries() {
		return series;
	}

	public void setSeries(List<? extends ISerie> series) {
		this.series = series;
	}
}
