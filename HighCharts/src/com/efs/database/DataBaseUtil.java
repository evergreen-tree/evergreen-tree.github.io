package com.efs.database;

import java.util.ArrayList;
import java.util.List;

import org.palo.api.Axis;
import org.palo.api.Cube;
import org.palo.api.CubeView;
import org.palo.api.Database;
import org.palo.api.Dimension;
import org.palo.api.Element;
import org.palo.api.impl.GetDataFromDatabase;

import com.efs.bo.serie.ISerie;
import com.efs.bo.serie.LineSerie;
import com.efs.constants.Constants;
import com.efs.context.ChartContext;
import com.efs.log.ConsoleLog;

public class DataBaseUtil {
	/**
	 * 确保所有的element都是存放在同样的cube里面
	 * 
	 * @param eles
	 * @return
	 */
	public static String quickValue(Cube cube, Element[] eles) {
		if (eles.length == 0) {
			throw new RuntimeException("查询数据库使用的Element必须大于1");
		}
		Element[] queryEles = new Element[cube.getDimensionCount()];
		for (int i = 0; i < cube.getDimensionCount(); i++) {
			Dimension dimension = cube.getDimensionAt(i);
			queryEles[i] = dimension.getElementById("0");
			if (queryEles[i] == null) {
				queryEles[i] = dimension.getElementAt(0);
			}
			for (Element ele : eles) {
				if (ele.getDimension().equals(dimension)) {
					queryEles[i] = ele;
				}
			}
		}
		return String.valueOf(cube.getData(queryEles));
	}

	/**
	 * 
	 * @param type
	 * @param cube
	 * @param dimensionx
	 * @param dimensiony
	 * @return
	 */
	public static List<ISerie> loadSeriesData(String type, Cube cube, Dimension dimensionx, Dimension dimensiony) {
		int depth = 1;
		if (ChartContext.getAttribute(Constants.CHART_LEVEL_KEY) != null) {
			depth = Integer.valueOf(ChartContext.getAttribute(Constants.CHART_LEVEL_KEY));
		}
		List<ISerie> series = new ArrayList<ISerie>();
		for (Element element : dimensiony.getElements()) {
			if (element.getDepth() == 1) {
				List<Float> tmpResult = new ArrayList<Float>();
				for (int i = 0; i < dimensionx.getElementCount(); i++) {
					Element tmpEle = dimensionx.getElementAt(i);
					if (tmpEle.getDepth() != depth) {
						continue;
					}
					String strData = DataBaseUtil.quickValue(cube,
							new Element[] { element, dimensionx.getElementAt(i) });
					if (strData.trim().length() > 0) {
						tmpResult.add(Float.valueOf(strData));
					} else {
						tmpResult.add(0.0f);
					}
				}
				float[] result = new float[tmpResult.size()];
				for (int i = 0; i < result.length; i++) {
					result[i] = tmpResult.get(i);
				}
				series.add(new LineSerie(type, element.getName(), result));
			}
		}
		return series;
	}

	/**
	 * find out the cubeView by searching the whole database
	 * 
	 * @param name
	 * @return
	 */
	public static CubeView foundCubeViewByName(String name) {
		CubeView cubeView = null;
		Database[] dbs = GetDataFromDatabase.Fetch();
		for (Database db : dbs) {
			Cube[] cubes = db.getCubes();
			for (Cube cube : cubes) {
				try {
					if (cube.getCubeViewCount() > 0) {
						CubeView[] cubeViews = cube.getCubeViews();
						for (CubeView cv : cubeViews) {
							if (cv.getName().equals(name)) {
								return cv;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ConsoleLog.info("found cubeView : " + cubeView.getName());
		return cubeView;
	}

	/**
	 * 
	 * @param cubeView
	 * @return
	 */
	public static Dimension getAxisYDimension(CubeView cubeView) {
		Axis axis = cubeView.getAxis("rows");
		Dimension[] dimensions = axis.getDimensions();
		if (dimensions.length > 1) {
			return dimensions[1];
		}
		return dimensions[0];
	}
}
