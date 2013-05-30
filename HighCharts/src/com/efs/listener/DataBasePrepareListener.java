package com.efs.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.palo.api.Database;
import org.palo.api.impl.GetDataFromDatabase;

/**
 * Application Lifecycle Listener implementation class DataBasePrepareListener
 * 
 */
public class DataBasePrepareListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public DataBasePrepareListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("init the server by pre-prepare the data.");
		Database[] dbs = GetDataFromDatabase.Fetch();
		System.out.println("database prepared successfully.");
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

}
