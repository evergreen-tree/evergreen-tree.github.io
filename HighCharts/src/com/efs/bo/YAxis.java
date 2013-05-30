package com.efs.bo;

import java.util.List;



public class YAxis {

	YAxisTitle title;
	List<PlotLines> plotLines;

	public YAxisTitle getTitle() {
		return title;
	}

	public void setTitle(YAxisTitle title) {
		this.title = title;
	}

	public List<PlotLines> getPlotLines() {
		return plotLines;
	}

	public void setPlotLines(List<PlotLines> plotLines) {
		this.plotLines = plotLines;
	}

	public YAxis(YAxisTitle title, List<PlotLines> plotLines) {
		super();
		this.title = title;
		this.plotLines = plotLines;
	}

}
