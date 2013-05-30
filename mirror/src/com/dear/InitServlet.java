package com.dear;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dear.database.HsqlDBConnectionGenerater;
import com.dear.document.IndexBuilderController;
import com.dear.util.FileLocationUtil;
import com.dispacher.config.GlobalConfig;
import com.dispacher.log.LogFactory;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		LogFactory.getDefaultLog().log("init the application with location start!");
		String location = FileLocationUtil.getDocFileLocation();
		IndexBuilderController.setDocLocation(location);
		String index = FileLocationUtil.getIndexFileLocation();
		IndexBuilderController.setIndexLocation(index);
		try {
			prepareDatabase();
		} catch (SQLException e) {
			throw new RuntimeException("Error while init the Derby. ", e);
		}
	}

	private void prepareDatabase() throws SQLException {
		if (!HsqlDBConnectionGenerater.isDatabaseExists()) {
			HsqlDBConnectionGenerater.initDatabase();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
	}

}
