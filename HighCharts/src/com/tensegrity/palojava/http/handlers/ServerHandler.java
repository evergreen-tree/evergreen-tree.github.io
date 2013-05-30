/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava.http.handlers;

import java.io.IOException;

import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.ServerInfo;
import com.tensegrity.palojava.http.HttpConnection;
import com.tensegrity.palojava.http.builders.DatabaseInfoBuilder;
import com.tensegrity.palojava.http.builders.InfoBuilderRegistry;
import com.tensegrity.palojava.http.builders.ServerInfoBuilder;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author ArndHouben
 * @version $Id: ServerHandler.java,v 1.5 2008/02/28 16:34:28 ArndHouben Exp $
 */
public class ServerHandler extends HttpHandler {

	private static final String LOAD = "/server/load?-";
	private static final String LIST_NORMAL_DATABASES = "/server/databases?show_normal=1&show_system=0";
	private static final String LIST_SYSTEM_DATABASES = "/server/databases?show_normal=0&show_system=1";
	private static final String LIST_ALL_DATABASES = "/server/databases?show_normal=1&show_system=1";
	private static final String INFO = "/server/info?-";
	private static final String SAVE = "/server/save?-";
	private static final String SHUTDOWN = "/server/shutdown?-";
	private static final String LOGIN = "/server/login?user=";
	private static final String LOGOUT = "/server/logout?-";	
//	private static final String LOCK_BEGIN = "/event/begin?";
//	private static final String LOCK_END = "/event/end?";
	
	
	//--------------------------------------------------------------------------
	// FACTORY
	//
	private static final ServerHandler instance = new ServerHandler();
	static final ServerHandler getInstance(HttpConnection connection) {
		instance.use(connection);
		return instance;
	}
	
	//--------------------------------------------------------------------------
	// INSTANCE
	//
	private final InfoBuilderRegistry builderReg;
	private ServerHandler() {
		builderReg = InfoBuilderRegistry.getInstance();
	}
	
//	/server/databases  	Shows the list of databases.  	server	
	public final DatabaseInfo[] getDatabases() throws IOException {
		String[][] response = request(LIST_ALL_DATABASES);
		DatabaseInfo[] databases = new DatabaseInfo[response.length];
//		PaloInfoBuilderFactory builder = PaloInfoBuilderFactory.getInstance();
//		for (int i = 0; i < databases.length; ++i) {
//			databases[i] = (DatabaseInfo) builder.create(
//					DatabaseInfo.class,null, response[i]);
//		}
		DatabaseInfoBuilder databaseBuilder = 
				builderReg.getDatabaseBuilder();
		for (int i = 0; i < databases.length; ++i) {
			databases[i] = databaseBuilder.create(null, response[i]);
		}

		return databases;
	}
	public final DatabaseInfo[] getSystemDatabases() throws IOException {
		String[][] response = request(LIST_SYSTEM_DATABASES);
		DatabaseInfo[] databases = new DatabaseInfo[response.length];
//		PaloInfoBuilderFactory builder = PaloInfoBuilderFactory.getInstance();
//		for (int i = 0; i < databases.length; ++i) {
//			databases[i] = (DatabaseInfo) builder.create(
//					DatabaseInfo.class,null, response[i]);
//		}
		DatabaseInfoBuilder databaseBuilder = 
				builderReg.getDatabaseBuilder();
		for (int i = 0; i < databases.length; ++i) {
			databases[i] = databaseBuilder.create(null, response[i]);
		}

		return databases;
	}
	public final DatabaseInfo[] getNormalDatabases() throws IOException {
		String[][] response = request(LIST_NORMAL_DATABASES);
		DatabaseInfo[] databases = new DatabaseInfo[response.length];
//		PaloInfoBuilderFactory builder = PaloInfoBuilderFactory.getInstance();
//		for (int i = 0; i < databases.length; ++i) {
//			databases[i] = (DatabaseInfo) builder.create(
//					DatabaseInfo.class,null, response[i]);
//		}
		DatabaseInfoBuilder databaseBuilder = 
					builderReg.getDatabaseBuilder();
		for (int i = 0; i < databases.length; ++i) {
			databases[i] = databaseBuilder.create(null, response[i]);
		}

		return databases;
	}
	
	
//	/server/info 	Shows information about the server. 	server	
	public final ServerInfo getInfo() throws IOException {
		String[][] response = request(INFO,false,true);
		if(response.length == 0)
			response = new String[][]{new String[]{""}}; 
		ServerInfoBuilder srvBuilder = builderReg.getServerBuilder(); 
		return srvBuilder.create(null, response[0]);
	}

//	/server/load 	Loads the server data (does not load database or cube data). 	server	
	public final boolean load() throws IOException {
		String[][] response = request(LOAD);
		return response[0][0].equals(OK);
	}

//	/server/login 	Login to server by user name and password. 	server	
	public final String[] login(String user, String password) throws IOException {
		StringBuffer query = new StringBuffer();
		query.append(LOGIN);
		query.append(user);
		query.append("&password=");
		query.append(password);
		String[][] response = request(query.toString(),false,true);
		if(response.length==0)
			throw new PaloException("Unknown palo server!");
		return response[0];
	}
	
//	/server/logout 	Logout the current user 	server
	public final boolean logout() throws IOException {
		String[][] response = request(LOGOUT);
		return response[0][0].equals(OK);
	}

/*
 * WE CURRENTLY DON'T SUPPORT GLOBAL SERVER LOCKS, BECAUSE:
 *  - what happens if application crashes after lock request?
 *  	=> will lock release iteself? and if so when?
 *  - what happens if application needs a lock longer than its sid is valid?
 *  - during the lock other request are blocked => timeout exception => not nice 
 */	
//	/**
//	 * Requests a server lock. The given string message is used for logging, 
//	 * i.e. all requests between the lock request and lock release 
//	 * are logged by the palo server with this string as event. 
//	 * @param msg the log message or <code>null</code> if none should be logged
//	 * @return <code>true</code> if requesting the lock was successful, 
//	 * <code>false</code> otherwise
//	 * @throws IOException
//	 */
//	public final boolean requestLock(String msg) throws IOException {
//		StringBuffer query = new StringBuffer();
//		query.append(LOCK_BEGIN);
//		query.append("source=");
//		query.append(connection.getSID());
//		if(msg != null) {
//			query.append("&event=");
////			query.append(printQuoted(msg));
//			query.append(encode(msg));
//		} 
//		String[][] response = request(query.toString());
//		return response[0][0].equals(OK);
//	}
//
//	public final boolean releaseLock() throws IOException {
//		try {
//			StringBuffer query = new StringBuffer();
//			query.append(LOCK_END);
//			query.append("sid=");
//			query.append(connection.getSID());
//			String[][] response = request(query.toString(), false, true);
//			return response[0][0].equals(OK);
//		} catch (PaloException pex) {
//			return false;
//		}
//	}
	
	
//	/server/save 	Saves the server data (does not save database or cube data). 	server
	public final boolean save() throws IOException {
		String[][] response = request(SAVE);
		return response[0][0].equals(OK);
	}

//	/server/shutdown 	Shuts down server (does not save database or cube data). 	-
	public final boolean shutdown() throws IOException {
		String[][] response = request(SHUTDOWN);
		return response[0][0].equals(OK);
	}
}
