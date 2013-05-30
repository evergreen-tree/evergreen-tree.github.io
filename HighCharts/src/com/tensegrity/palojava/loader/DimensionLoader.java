/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.loader;

import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.PaloInfo;

/**
 * <p><code>DimensionInfoLoader</code></p>
 * This abstract base class manages the loading of {@link DimensionInfo} objects.
 *
 * @author ArndHouben
 * @version $Id: DimensionLoader.java,v 1.4 2008/01/23 16:41:53 ArndHouben Exp $
 **/
public abstract class DimensionLoader extends PaloInfoLoader {

	protected final DatabaseInfo database;
	
	/**
	 * Creates a new loader instance.
	 * @param paloConnection
	 * @param database
	 */
	public DimensionLoader(DbConnection paloConnection, DatabaseInfo database) {
		super(paloConnection);
		this.database = database;
	}

	/**
	 * Returns the identifiers of all dimensions currently known to the palo 
	 * server.
	 * @return ids of all known palo dimensions
	 */
	public abstract String[] getAllDimensionIds();
	public abstract String[] getDimensionIds(int typeMask);
	
	/**
	 * Loads the <code>DimensionInfo</code> object by its name
	 * @param name the name of the <code>DimensionInfo</code> to load
	 * @return the loaded <code>DimensionInfo</code> instance
	 */
	public abstract DimensionInfo loadByName(String name);
	
	
//	/**
//	 * Creates a new {@link DimensionInfo} instance with the given name
//	 * @param name the name of the new dimension
//	 * @return a new <code>DimensionInfo</code> object
//	 */
//	public final DimensionInfo create(String name) {
//		DimensionInfo dimInfo = paloConnection.addDimension(database,name);
//		loaded(dimInfo);
//		return dimInfo;
//	}
	
	public final DimensionInfo create(String name, int type) {
		DimensionInfo dimInfo = paloConnection.addDimension(database,name,type);
		loaded(dimInfo);
		return dimInfo;		
	}

	/**
	 * Deletes the given <code>DimensionInfo</code> instance from the palo 
	 * server as well as from the internal used cache.
	 * @param dimInfo the <code>DimensionInfo</code> instance to delete
	 * @return <code>true</code> if deletion was successful, <code>false</code>
	 * otherwise
	 */
	public final boolean  delete(DimensionInfo dimInfo) {
		if(paloConnection.delete(dimInfo)) {
			removed(dimInfo);
			return true;
		} 
		return false;
	}

	/**
	 * Loads the <code>DimensionInfo</code> object which corresponds to the given
	 * id
	 * @param id the identifier of the <code>DimensionInfo</code> object to load
	 * @return the loaded <code>DimensionInfo</code> object
	 */	
	public final DimensionInfo load(String id) {
		PaloInfo dim = loadedInfo.get(id);
		if (dim == null) {
			dim = paloConnection.getDimension(database, id);
			if (dim == null) {
				return null;
			}
			loaded(dim);
		}
		return (DimensionInfo)dim;
	}
	
	/**
	 * Loads the <code>DimensionInfo</code> object at the specified index
	 * @param index the index of the <code>DimensionInfo</code> object to load
	 * @return the loaded <code>DimensionInfo</code> object
	 */	
	public final DimensionInfo load(int index) {
		String[] dimIds = getAllDimensionIds();
		if(index<0 || index > dimIds.length-1)
			return null;
		return load(dimIds[index]);
	}

}
