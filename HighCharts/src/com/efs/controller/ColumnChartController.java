package com.efs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.palo.api.Cube;
import org.palo.api.CubeView;
import org.palo.api.Dimension;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.efs.bo.Chart;
import com.efs.bo.Legend;
import com.efs.bo.Options;
import com.efs.bo.PlotLines;
import com.efs.bo.Title;
import com.efs.bo.Tooltip;
import com.efs.bo.YAxis;
import com.efs.bo.YAxisTitle;
import com.efs.bo.serie.ISerie;
import com.efs.constants.Constants;
import com.efs.context.ChartContext;

@Controller
@RequestMapping(value = "/chart")
public class ColumnChartController extends AbstractChartController {
	@RequestMapping(value = "/column")
	public String column() {
		System.out.println("Go to column chart: Passing through...");
		return "columnChart";
	}

	@RequestMapping(value = "/secondLevelColumn")
	public String secondLevelColumn(Model model) {
		System.out.println("Go to column chart: Passing through...");
		model.addAttribute("secondLevel", "SecondLevel");
		return "columnChart";
	}

	@RequestMapping(value = "/getColumnChart")
	@ResponseBody
	public Options getColumnChart(@RequestParam("depth") String depth) {
		ChartContext.setAttribute(Constants.CHART_LEVEL_KEY, depth);
		CubeView cubeView = foundCubeViewByName("LineDemo");
		Cube cube = cubeView.getCube();
		Dimension dimensionx = getAxisXDimension(cubeView);
		Dimension dimensiony = getAxisYDimension(cubeView);

		List<? extends ISerie> serieList = fetchDataFromCache(cube, dimensionx, dimensiony);

		Options options = buildColumnOptions(cubeView, dimensionx, dimensiony);
		options.setSeries(serieList);
		return options;
	}

	private Options buildColumnOptions(CubeView cubeView, Dimension dimensionx, Dimension dimensiony) {
		Options options = new Options();
		options.setChart(new Chart("container", "column", 130, 25));
		options.setLegend(new Legend("vertical", "right", "top", -10, 100, 0));
		// options.setTitle(new Title(cubeView.getName(), -20));
		options.setTitle(new Title("Bar/Column Charts", -20));

		// add category
		addCategory(options, dimensionx);
		List<PlotLines> plotLines = new ArrayList<PlotLines>();
		plotLines.add(new PlotLines(0, 1, "#808080"));
		options.setyAxis(new YAxis(new YAxisTitle(dimensiony.getName()), plotLines));
		// options.setSubtitle(new Subtitle(cubeView.getName(), -20));
		String formatterString = "function() { " + "return '<b>'+ this.series.name +'</b><br/>'+ "
				+ "this.x +': '+ this.y +'E';}";
		options.setTooltip(new Tooltip(formatterString, "", 0));
		return options;
	}

	private List<? extends ISerie> fetchDataFromCache(Cube cube, Dimension dimensionx, Dimension dimensiony) {
		String level = ChartContext.getAttribute(Constants.CHART_LEVEL_KEY);
		if (level == null) {
			level = Constants.CHART_LEVEL_ONE;
		}
		List<? extends ISerie> serieList = new ArrayList<ISerie>();
		if (seriesMap.get(SERIES_OF_COLUMN_CHART + level) != null) {
			serieList = seriesMap.get(SERIES_OF_COLUMN_CHART + level);
		} else {
			serieList = loadSeriesData("column", cube, dimensionx, dimensiony);
			seriesMap.put(SERIES_OF_COLUMN_CHART + level, serieList);
		}
		return serieList;
	}
}
