/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.impl;

import java.util.HashMap;

import org.palo.api.ConnectionContext;
import org.palo.api.Rights;
import org.palo.api.ServerInfo;


/**
 * <code>ConnectionContextImpl</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: ConnectionContextImpl.java,v 1.4 2008/02/28 17:32:15 ArndHouben Exp $
 **/
class ConnectionContextImpl implements ConnectionContext {

	private final ConnectionImpl connection;
	private final RightsImpl rights;
	private final ServerInfoImpl serverInfo;
	private boolean doSupportSubset2;
	
	private final HashMap<String, Object> dataMap = new HashMap<String, Object>();
	
	ConnectionContextImpl(ConnectionImpl connection) {
		this.connection = connection;
		this.rights = new RightsImpl(connection);
		this.serverInfo = new ServerInfoImpl(connection, 
				connection.getConnectionInternal().getServerInfo());
		setContext();
	}

	final void setDoSupportSubset2(boolean b) {
		this.doSupportSubset2 = b;
	}
	
	public final boolean doSupportSubset2() {
		return doSupportSubset2;
	}
	
	
	private final void setContext() {
		com.tensegrity.palojava.ServerInfo srvInfo = 
			connection.getConnectionInternal().getServerInfo();
		setDoSupportSubset2(
				(srvInfo.getMajor() >= 2 && srvInfo.getBuildNumber() > 2400));
	}
	
	public final Rights getRights() {
		return rights;
	}

	public ServerInfo getServerInfo() {
		return serverInfo;
	}
	
	public final void setData(String id, Object data) {
		dataMap.put(id, data);
	}
	
	public final Object getData(String id) {
		return dataMap.get(id);
	}

}
