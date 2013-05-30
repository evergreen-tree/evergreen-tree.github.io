package com.efs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.palo.api.Axis;
import org.palo.api.Cube;
import org.palo.api.CubeView;
import org.palo.api.Database;
import org.palo.api.Dimension;
import org.palo.api.Element;
import org.palo.api.impl.GetDataFromDatabase;

import com.efs.bo.Options;
import com.efs.bo.XAxis;
import com.efs.bo.serie.ISerie;
import com.efs.database.DataBaseUtil;

public class AbstractChartController {
	protected Map<String, List<? extends ISerie>> seriesMap = new HashMap<String, List<? extends ISerie>>();
	protected static final String SERIES_OF_LINE_CHART = "SERIES_OF_LINE_CHART";
	protected static final String SERIES_OF_PIE_CHART = "SERIES_OF_PIE_CHART";
	protected static final String SERIES_OF_COLUMN_CHART = "SERIES_OF_COLUMN_CHART";

	protected Dimension getAxisXDimension(CubeView cubeView) {
		Axis axis = cubeView.getAxis("cols");
		Dimension[] dimensions = axis.getDimensions();
		if (dimensions.length > 1) {
			return dimensions[1];
		}
		return dimensions[0];
	}

	protected Dimension getAxisYDimension(CubeView cubeView) {
		Axis axis = cubeView.getAxis("rows");
		Dimension[] dimensions = axis.getDimensions();
		if (dimensions.length > 1) {
			return dimensions[1];
		}
		return dimensions[0];
	}

	/**
	 * 
	 * @param options
	 * @param dimensionx
	 */
	protected void addCategory(Options options, Dimension dimensionx) {
		List<String> categoryList = new ArrayList<String>();
		for (int i = 0; i < dimensionx.getElementCount(); i++) {
			Element ele = dimensionx.getElementAt(i);
			if (ele.getDepth() == 1) {
				categoryList.add(ele.getName());
			}
		}
		String[] categories = new String[categoryList.size()];
		categories = categoryList.toArray(categories);

		options.setxAxis(new XAxis(categories));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	protected CubeView foundCubeViewByName(String name) {
		return DataBaseUtil.foundCubeViewByName(name);
	}

	/**
	 * 
	 * @param type
	 * @param cube
	 * @param dimensionx
	 * @param dimensiony
	 * @return
	 */
	protected List<ISerie> loadSeriesData(String type, Cube cube, Dimension dimensionx, Dimension dimensiony) {
		return DataBaseUtil.loadSeriesData(type, cube, dimensionx, dimensiony);
	}

	protected Cube getCube(String name) {
		Database[] dbs = GetDataFromDatabase.Fetch();
		for (Database db : dbs) {
			Cube[] cubes = db.getCubes();
			for (Cube cube : cubes) {
				if (cube.getName().equals(name)) {
					return cube;
				}
			}
		}
		return null;
	}
}
