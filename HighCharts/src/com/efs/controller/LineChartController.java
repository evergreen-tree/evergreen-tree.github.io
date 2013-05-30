package com.efs.controller;

import java.util.ArrayList;
import java.util.List;

import org.palo.api.Cube;
import org.palo.api.CubeView;
import org.palo.api.Dimension;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.efs.bo.Chart;
import com.efs.bo.Legend;
import com.efs.bo.Options;
import com.efs.bo.PlotLines;
import com.efs.bo.Subtitle;
import com.efs.bo.Title;
import com.efs.bo.Tooltip;
import com.efs.bo.YAxis;
import com.efs.bo.YAxisTitle;
import com.efs.bo.serie.ISerie;

@Controller
@RequestMapping(value = "/chart")
public class LineChartController extends AbstractChartController {
	@RequestMapping(value = "/line")
	public String line() {
		System.out.println("Go to Line chart: Passing through...");
		return "lineChart";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getLineChart")
	@ResponseBody
	public Options getLineChart() {
		CubeView cubeView = foundCubeViewByName("LineDemo");
		Cube cube = cubeView.getCube();
		Dimension dimensionx = getAxisXDimension(cubeView);
		Dimension dimensiony = getAxisYDimension(cubeView);
		List<? extends ISerie> serieList = new ArrayList<ISerie>();
		// just fetch out these element with depth = 1
		if (seriesMap.get(SERIES_OF_LINE_CHART) != null) {
			serieList = seriesMap.get(SERIES_OF_LINE_CHART);
		} else {
			serieList = loadSeriesData("line", cube, dimensionx, dimensiony);
			seriesMap.put(SERIES_OF_LINE_CHART, serieList);
		}
		Options options = buildLineOptions(cubeView, dimensionx, dimensiony);
		options.setSeries(serieList);
		return options;
	}

	private Options buildLineOptions(CubeView cubeView, Dimension dimensionx, Dimension dimensiony) {
		Options options = new Options();
		options.setChart(new Chart("container", "line", 130, 25));
		options.setLegend(new Legend("vertical", "right", "top", -10, 100, 0));
		options.setTitle(new Title(cubeView.getName(), -20));

		// add category
		addCategory(options, dimensionx);
		List<PlotLines> plotLines = new ArrayList<PlotLines>();
		plotLines.add(new PlotLines(0, 1, "#808080"));
		options.setyAxis(new YAxis(new YAxisTitle(dimensiony.getName()), plotLines));
		options.setSubtitle(new Subtitle(cubeView.getName(), -20));
		String formatterString = "function() { " + "return '<b>'+ this.series.name +'</b><br/>'+ "
				+ "this.x +': '+ this.y +'E';}";
		options.setTooltip(new Tooltip(formatterString, "", 0));
		return options;
	}
}
