package com.tensegrity.palo.xmla;

import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.PaloServer;
import com.tensegrity.palojava.ServerInfo;

public class XMLAServer implements PaloServer {
	private final XMLAConnection connection;
	
	public XMLAServer(String host, String port, String user, String pass) {
		connection = new XMLAConnection(host, port, user, pass);
	}

	public DbConnection connect() {
		return connection;
	}

	public void disconnect() {		
		connection.disconnect();
	}

	public ServerInfo getInfo() {
		if (!connection.isConnected()) {
			throw new PaloException("XMLA Server is not connected.");
		}
		return connection.getServerInfo();
	}

	public void ping() throws PaloException {
	}
}
