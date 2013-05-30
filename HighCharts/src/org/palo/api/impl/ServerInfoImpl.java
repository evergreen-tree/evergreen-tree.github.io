package org.palo.api.impl;

import java.util.HashMap;

import org.palo.api.Connection;
import org.palo.api.Property2;
import org.palo.api.ServerInfo;

//import com.tensegrity.palojava.ServerInfo;

class ServerInfoImpl implements ServerInfo {
	private final ConnectionImpl connection;
	private final HashMap <String, Property2> properties;
	private final com.tensegrity.palojava.ServerInfo serverInfo;
	
	ServerInfoImpl(Connection connection,
			com.tensegrity.palojava.ServerInfo serverInfo) {
		this.connection = (ConnectionImpl) connection;
		this.properties = new HashMap<String, Property2>();
		this.serverInfo = serverInfo;
		
		addProperty(ServerInfo.MAJOR_VERSION_PROPERTY, 
				Integer.toString(serverInfo.getMajor()));
		addProperty(ServerInfo.MINOR_VERSION_PROPERTY, 
				Integer.toString(serverInfo.getMinor()));
		addProperty(ServerInfo.BUILD_NUMBER_PROPERTY, 
				Integer.toString(serverInfo.getBuildNumber()));
		
		String[] props = serverInfo.getProperties();
		for (int i = 0; i < props.length; i += 2) {
			addProperty(props[i], props[i + 1]);
		}
	}
		
	private final void addProperty(String id, String value) {
		properties.put(id, Property2Impl.create(connection, id, value, null, 
									Property2.TYPE_STRING, true));		
	}
	
	public String getName() {
		return serverInfo.getName();
	}

	public String getProperty(String id) {
		if (properties.containsKey(id)) {
			return properties.get(id).getValue();
		}
		return "";
	}

	public String getType() {
		return serverInfo.getServerType();
	}

	public String getVersion() {
		return serverInfo.getVersion();
	}

	public String[] getPropertyIds() {
		return properties.keySet().toArray(new String[0]);
	}
}
