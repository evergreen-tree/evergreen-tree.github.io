/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.http.loader;

import java.util.Collection;

import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.loader.DatabaseLoader;

/**
 * <code>HttpDatabaseLoader</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: HttpDatabaseLoader.java,v 1.3 2008/04/17 18:11:50 ArndHouben Exp $
 **/
public class HttpDatabaseLoader extends DatabaseLoader {

	public HttpDatabaseLoader(DbConnection paloConnection) {
		super(paloConnection);
	}

	public int getDatabaseCount() {
		reload();
		return getLoaded().size();
	}

	public final String[] getAllDatabaseIds() {
		if(!loaded) {			
			reload();
			loaded = true;
		}
		return getLoadedIds();
	}

	public final DatabaseInfo loadByName(String name) {
		//first check if we have it loaded already
		DatabaseInfo dbInfo = findDatabase(name);
		if(dbInfo == null) {
			//if not, we have to ask server...
			reload();
			dbInfo = findDatabase(name);
		}
		return dbInfo;
	}
	
	protected final void reload() {
		reset();
		DatabaseInfo[] dbInfos = paloConnection.getDatabases();
		for (DatabaseInfo dbInfo : dbInfos) {
			loaded(dbInfo);
		}
	}
	
	private final DatabaseInfo findDatabase(String name) {
		Collection<PaloInfo> infos = getLoaded();
		for(PaloInfo info : infos) {
			if (info instanceof DatabaseInfo) {
				DatabaseInfo dbInfo = (DatabaseInfo) info;
				//PALO IS NOT CASESENSETIVE...
				if (dbInfo.getName().equalsIgnoreCase(name))
					return dbInfo;
			}
		}
		return null;
	}
}
