/*
 * (c) 2007 Tensegrity Software GmbH
 * All rights reserved
 */
package com.tensegrity.palojava.http.handlers;

import com.tensegrity.palojava.http.HttpConnection;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author ArndHouben
 * @version $Id: HandlerRegistry.java,v 1.1 2007/04/11 16:45:38 ArndHouben Exp $
 */
public class HandlerRegistry {

	//we can have multiple connections but only one instance from each handler!
	private final HttpConnection connection;
	
	public HandlerRegistry(HttpConnection connection) {
		this.connection = connection;
	}
	
	public final ServerHandler getServerHandler() {
		return ServerHandler.getInstance(connection);
	}
	
	public final CellHandler getCellHandler() {
		return CellHandler.getInstance(connection);
	}
	
	public final CubeHandler getCubeHandler() {
		return CubeHandler.getInstance(connection);
	}
	
	public final DatabaseHandler getDatabaseHandler() {
		return DatabaseHandler.getInstance(connection);
	}
	
	public final DimensionHandler getDimensionHandler() {
		return DimensionHandler.getInstance(connection);
	}
	
	public final ElementHandler getElementHandler() {
		return ElementHandler.getInstance(connection);
	}
	
	public final RuleHandler getRuleHandler() {
		return RuleHandler.getInstance(connection);
	}
	
}
