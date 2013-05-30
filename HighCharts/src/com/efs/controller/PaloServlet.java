package com.efs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palo.api.Cube;
import org.palo.api.CubeView;
import org.palo.api.Database;
import org.palo.api.Dimension;
import org.palo.api.ElementNode;
import org.palo.api.Hierarchy;
import org.palo.api.impl.GetDataFromDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.efs.log.ConsoleLog;

/**
 * Servlet implementation class PaloServlet
 */
@Controller
@RequestMapping(value = "/database")
public class PaloServlet {
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "")
	public String testDatabase(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("preparing the data:");
		Database[] dbs = GetDataFromDatabase.Fetch();

		List<String> dbNameList = new ArrayList<String>();
		List<String> cubeNameList = new ArrayList<String>();
		List<CubeView> cubeViews = new ArrayList<CubeView>();
		for (int i = 0; i < dbs.length; i++) {
			String dbName = dbs[i].getName();
			dbNameList.add(dbName);
			Cube[] cubes = dbs[i].getCubes();
			for (Cube cube : cubes) {
				String cubeName = cube.getName();
				cubeNameList.add(cubeName);
				if (cube.getCubeViewCount() > 0) {
					ConsoleLog.info("got [" + cube.getCubeViewCount() + "] cubeview at [" + cube.getName() + "]");
					for (CubeView cubeView : cube.getCubeViews()) {
						cubeViews.add(cubeView);
					}
				}
			}
		}
		ConsoleLog.info(dbNameList.toString());
		ConsoleLog.info(cubeNameList.toString());
		// dimensions
		List<String> dimensionNameList = new ArrayList<String>();
		for (int x = 0; x < dbs.length; x++) {
			Dimension[] dimensions = dbs[x].getDimensions();
			for (int y = 0; y < dimensions.length; y++) {
				String dimensionName = dimensions[y].getName();
				Cube[] cube = dimensions[y].getCubes();
				ElementNode[] elementNodes = dimensions[y].getAllElementNodes();
				Hierarchy[] hierarchies = dimensions[y].getHierarchies();
				dimensionNameList.add(dimensionName);
			}

		}
		request.setAttribute("dbNameList", dbNameList);
		request.setAttribute("cubeNameList", cubeNameList);
		request.setAttribute("cubeViews", cubeViews);
		request.setAttribute("dimensionNameList", dimensionNameList);
		return "paloData";
	}
}
