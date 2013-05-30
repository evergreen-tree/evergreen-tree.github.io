package com.tensegrity.palo.xmla.loader;

import com.tensegrity.palo.xmla.XMLAConnection;
import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.HierarchyInfo;
import com.tensegrity.palojava.loader.HierarchyLoader;

/**
 * <code>HttpHierarchyInfoLoader</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: XMLAHierarchyLoader.java,v 1.5 2008/04/11 09:12:33 PhilippBouillon Exp $
 **/
public class XMLAHierarchyLoader extends HierarchyLoader {
	private boolean hiersLoaded;
	
	public XMLAHierarchyLoader(DbConnection paloConnection, DimensionInfo dimension) {
		super(paloConnection, dimension);
		hiersLoaded = false;
	}
	
	public String[] getAllHierarchyIds() {
		if (!hiersLoaded) {
			reload();
		}
		return getLoadedIds();
	}

	public int getHierarchyCount() {
		if (!hiersLoaded) {
			reload();
		}
		return getLoaded().size();
	}

	protected final void reload() {
		reset();
//		if (true || ((XMLAConnection) paloConnection).usedByWPalo()) {
//			loaded(dimension.getDefaultHierarchy());
//			hiersLoaded = true;
//			return;
//		}
		HierarchyInfo[] hierInfos;
		hierInfos = paloConnection.getHierarchies(dimension);
		for (HierarchyInfo hierInfo : hierInfos) {
			loaded(hierInfo);
		}
		hiersLoaded = true;
	}
}