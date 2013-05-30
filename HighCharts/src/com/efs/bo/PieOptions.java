package com.efs.bo;

import java.util.List;

import com.efs.bo.serie.ISerie;

public class PieOptions {
	Chart chart;
	Title title;
	Tooltip tooltip;
	PlotOptions plotOptions;
	List<? extends ISerie> series;

	public PlotOptions getPlotOptions() {
		return plotOptions;
	}

	public void setPlotOptions(PlotOptions plotOptions) {
		this.plotOptions = plotOptions;
	}

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


	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}


	public List<? extends ISerie> getSeries() {
		return series;
	}

	public void setSeries(List<? extends ISerie> series) {
		this.series = series;
	}
}
