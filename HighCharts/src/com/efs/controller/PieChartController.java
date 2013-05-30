package com.efs.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.palo.api.Cube;
import org.palo.api.CubeView;
import org.palo.api.Dimension;
import org.palo.api.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.efs.bo.Chart;
import com.efs.bo.DataLabels;
import com.efs.bo.Pie;
import com.efs.bo.PieData;
import com.efs.bo.PieOptions;
import com.efs.bo.PlotOptions;
import com.efs.bo.Title;
import com.efs.bo.Tooltip;
import com.efs.bo.serie.ISerie;
import com.efs.bo.serie.PieSerie;
import com.efs.constants.Constants;
import com.efs.context.ChartContext;
import com.efs.database.DataBaseUtil;

@Controller
@RequestMapping(value = "/chart")
public class PieChartController extends AbstractChartController {
	@RequestMapping(value = "/pie")
	public String pie() {
		System.out.println("Go to pie chart: Passing through...");
		return "pieChart";
	}

	@RequestMapping(value = "/getPieChart")
	@ResponseBody
	public PieOptions getPieChart(@RequestParam("depth") String depth) {
		ChartContext.setAttribute(Constants.CHART_LEVEL_KEY, depth);
		CubeView cubeView = foundCubeViewByName("LineDemo");
		Cube cube = cubeView.getCube();
		Dimension dimensionx = getAxisXDimension(cubeView);
		Dimension dimensiony = getAxisYDimension(cubeView);

		List<? extends ISerie> pieSerieList = fetchDataFromCache(cube, dimensionx);
		PieOptions options = buildPieOptions(cubeView, dimensionx, dimensiony);

		options.setSeries(pieSerieList);
		return options;
	}

	/**
	 * fake data for pie, should be overwritten later
	 * 
	 * @return
	 */
	private List<? extends ISerie> loadPieSeriesData(Cube cube, Dimension dimensionx) {
		String level = ChartContext.getAttribute(Constants.CHART_LEVEL_KEY);
		if (level == null) {
			level = Constants.CHART_LEVEL_ONE;
		}
		int levelInt = Integer.valueOf(level);
		List<PieSerie> pieSerieList = new ArrayList<PieSerie>();
		PieData[] data = new PieData[dimensionx.getElementCount()];
		for (int i = 0; i < dimensionx.getElementCount(); i++) {
			Element ele = dimensionx.getElementAt(i);
			if (ele.getDepth() != levelInt) {
				continue;
			}
			String strData = DataBaseUtil.quickValue(cube, new Element[] { ele });
			if (strData.trim().length() == 0) {
				continue;
			}
			data[i] = new PieData(ele.getName(), Float.valueOf(strData), false, false);
		}
		pieSerieList.add(new PieSerie("pie", "test pie", clearNullData(data)));
		return pieSerieList;
	}

	private PieOptions buildPieOptions(CubeView cubeView, Dimension dimensionx, Dimension dimensiony) {
		PieOptions pieOptions = new PieOptions();
		pieOptions.setChart(new Chart("container", null, null, false));
		pieOptions.getChart().setType("pie");
		// options.setTitle(new Title(cubeView.getName(), -20));
		pieOptions.setTitle(new Title("Pie Charts", -20));
		// add category
		// addCategory(options, dimensionx);
		DataLabels dataLabels = new DataLabels();
		dataLabels.setEnabled(true);
		dataLabels
				.setFormatter("function() {return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage ,2)+ ' %';}");
		Pie pie = new Pie();
		pie.setAllowPointSelect(true);
		pie.setDataLabels(dataLabels);
		PlotOptions plotOptions = new PlotOptions();
		plotOptions.setPie(pie);
		pieOptions.setPlotOptions(plotOptions);

		String pointFormatString = "{series.name}: <b>{point.percentage}%</b>";
		pieOptions.setTooltip(new Tooltip("", pointFormatString, 1));
		return pieOptions;
	}

	private PieData[] clearNullData(PieData[] targetData) {
		List<PieData> tmpList = new ArrayList<PieData>();
		for (PieData pieData : targetData) {
			if (pieData != null) {
				tmpList.add(pieData);
			}
		}
		PieData[] tmpData = new PieData[tmpList.size()];
		for (int i = 0; i < tmpList.size(); i++) {
			tmpData[i] = tmpList.get(i);
		}
		return tmpData;
	}

	private List<? extends ISerie> fetchDataFromCache(Cube cube, Dimension dimensionx) {
		String level = ChartContext.getAttribute(Constants.CHART_LEVEL_KEY);
		if (level == null) {
			level = Constants.CHART_LEVEL_ONE;
		}
		List<? extends ISerie> serieList = new ArrayList<ISerie>();
		if (seriesMap.get(SERIES_OF_COLUMN_CHART + level) != null) {
			serieList = seriesMap.get(SERIES_OF_COLUMN_CHART + level);
		} else {
			serieList = loadPieSeriesData(cube, dimensionx);
			seriesMap.put(SERIES_OF_COLUMN_CHART + level, serieList);
		}
		return serieList;
	}

}
