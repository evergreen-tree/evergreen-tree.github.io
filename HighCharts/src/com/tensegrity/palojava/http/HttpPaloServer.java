/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.http;

import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.PaloServer;
import com.tensegrity.palojava.ServerInfo;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author Arnd Houben
 * @version $Id: HttpPaloServer.java,v 1.3 2007/08/14 20:20:55 ArndHouben Exp $
 */
public class HttpPaloServer implements PaloServer {

	private final HttpConnection connection;
	
	public HttpPaloServer(String host, String port, int timeout) {
		connection = new HttpConnection(host,port,timeout);
	}
	
	public final synchronized DbConnection connect() {
		return connection;
	}
	
	public final ServerInfo getInfo() {
		if(!isConnected())
			throw new PaloException("not connected!!");
		return connection.getServerInfo();
	}
	
	public final void disconnect() {
		connection.disconnect();
	}

	public final void ping() {
		throw new PaloException("HttpPaloServer#disconnect(): NOT IMPLEMENTED!!");
		// TODO Auto-generated method stub
		
	}

	public final boolean login(String username, String password) {
		return connection.login(username, password);
	}

	
	//--------------------------------------------------------------------------
	// PRIVATE METHODS
	//
	private final synchronized boolean isConnected() {
		return (connection != null && connection.isConnected());
	}

}
