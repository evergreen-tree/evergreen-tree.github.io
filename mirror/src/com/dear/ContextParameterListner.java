package com.dear;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dear.database.DerbyDBConnectionGenerater;
import com.dear.database.HsqlDBConnectionGenerater;
import com.dear.document.IndexBuilderController;
import com.dear.util.FileLocationUtil;
import com.dispacher.config.GlobalConfig;
import com.dispacher.log.LogFactory;

/**
 * Application Lifecycle Listener implementation class ContextParameterListner
 * 
 */
public class ContextParameterListner implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public ContextParameterListner() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
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
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

}
