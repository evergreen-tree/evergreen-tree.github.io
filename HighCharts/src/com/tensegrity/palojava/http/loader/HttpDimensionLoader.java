/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.http.loader;

import java.util.Collection;

import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.loader.DimensionLoader;

/**
 * <code>HttpDimensionInfoLoader</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: HttpDimensionLoader.java,v 1.5 2008/04/17 18:11:50 ArndHouben Exp $
 **/
public class HttpDimensionLoader extends DimensionLoader {

	public HttpDimensionLoader(DbConnection paloConnection,
			DatabaseInfo database) {
		super(paloConnection, database);
	}


	public String[] getAllDimensionIds() {
		if(!loaded) {			
			reload();
			loaded = true;
		}
		return getLoadedIds();
	}

	public String [] getDimensionIds(int typeMask) {
		DimensionInfo [] dims = paloConnection.getDimensions(database, typeMask);
		String [] ids = new String[dims.length];
		int counter = 0;		
		for (DimensionInfo dim : dims) {
			loaded(dim);
			ids[counter++] = dim.getId();
		}
		return ids;		
	}
	
	public DimensionInfo loadByName(String name) {
		//first check if we have it loaded already
		DimensionInfo dimInfo = findDimension(name);
		if(dimInfo == null) {
			//if not, we have to ask server...
			reload();
			dimInfo = findDimension(name);
		}
		return dimInfo;
	}

	protected final void reload() {
		reset();
		DimensionInfo[] dimInfos = paloConnection.getDimensions(database);
		for (DimensionInfo dimInfo : dimInfos) {
			loaded(dimInfo);
		}
	}

	private final DimensionInfo findDimension(String name) {
		Collection<PaloInfo> infos = getLoaded();
		for (PaloInfo info : infos) {
			if (info instanceof DimensionInfo) {
				DimensionInfo dimInfo = (DimensionInfo) info;
				//PALO IS NOT CASESENSETIVE...
				if (dimInfo.getName().equalsIgnoreCase(name))
					return dimInfo;
			}
		}
		return null;
	}

}
