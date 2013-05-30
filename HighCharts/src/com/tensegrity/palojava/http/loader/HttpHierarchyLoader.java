/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.http.loader;

import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.HierarchyInfo;
import com.tensegrity.palojava.loader.HierarchyLoader;

/**
 * <code>HttpHierarchyInfoLoader</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: HttpHierarchyLoader.java,v 1.4 2008/04/17 18:11:50 ArndHouben Exp $
 **/
public class HttpHierarchyLoader extends HierarchyLoader {
	
	public HttpHierarchyLoader(DbConnection paloConnection,
			DimensionInfo dimension) {
		super(paloConnection, dimension);
	}
	
	public String[] getAllHierarchyIds() {
		if(!loaded) {			
			reload();
			loaded = true;
		}
		return getLoadedIds();
	}

	public int getHierarchyCount() {
		reload();
		return getLoaded().size();
	}

	protected final void reload() {
		reset();
		HierarchyInfo[] hierInfos = paloConnection.getHierarchies(dimension);
		for (HierarchyInfo hierInfo : hierInfos) {
			loaded(hierInfo);
		}
	}

}
