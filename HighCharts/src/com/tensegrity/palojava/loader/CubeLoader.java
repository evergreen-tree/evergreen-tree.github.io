/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.loader;

import com.tensegrity.palojava.CubeInfo;
import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.PaloInfo;

/**
 * <p><code>CubeInfoLoader</code></p>
 * This abstract base class manages the loading of {@link CubeInfo} objects.
 *
 * @author ArndHouben
 * @version $Id: CubeLoader.java,v 1.4 2008/01/23 16:41:53 ArndHouben Exp $
 **/
public abstract class CubeLoader extends PaloInfoLoader {

	protected final DatabaseInfo database;
	
	/**
	 * Creates a new loader instance.
	 * @param paloConnection
	 * @param database
	 */
	public CubeLoader(DbConnection paloConnection, DatabaseInfo database) {
		super(paloConnection);
		this.database = database;
	}

	/**
	 * Returns the identifiers of all cubes currently known to the palo 
	 * server.
	 * @return ids of all known palo cubes
	 */
	public abstract String[] getAllCubeIds();
	public abstract String[] getCubeIds(int typeMask);
	
	public abstract String[] getCubeIds(DimensionInfo dimension);
//	public abstract String[] getCubeIds(DimensionInfo dimension, int type);
	
	/**
	 * Loads the <code>CubeInfo</code> object by its name
	 * @param name the name of the <code>CubeInfo</code> to load
	 * @return the loaded <code>CubeInfo</code> instance
	 */
	public abstract CubeInfo loadByName(String name);
	
//	/**
//	 * Creates a new {@link CubeInfo} instance with the given name and dimensions
//	 * @param name the name of the new cube
//	 * @param dimensions the dimensions which build the new cube
//	 * @return a new <code>CubeInfo</code> object
//	 */
//	public final CubeInfo create(String name, DimensionInfo[] dimensions) {
//		CubeInfo cube = paloConnection.addCube(database,name,dimensions);
//		loaded(cube);
//		return cube;
//	}

	/**
	 * Creates a new {@link CubeInfo} instance with the given name, dimensions,
	 * and type.
	 * @param name the name of the new cube
	 * @param dimensions the dimensions which build the new cube
	 * @param the type of the new cube (either user info or normal)
	 * @return a new <code>CubeInfo</code> object
	 */
	public final CubeInfo create(String name, DimensionInfo[] dimensions, int type) {
		CubeInfo cube = paloConnection.addCube(database,name,dimensions,type);
		loaded(cube);
		return cube;
	}

	/**
	 * Deletes the given <code>CubeInfo</code> instance from the palo 
	 * server as well as from the internal used cache.
	 * @param cube the <code>CubeInfo</code> instance to delete
	 * @return <code>true</code> if deletion was successful, <code>false</code>
	 * otherwise
	 */
	public final boolean  delete(CubeInfo cube) {
		if(paloConnection.delete(cube)) {
			removed(cube);
			return true;
		} 
		return false;
	}

	/**
	 * Loads the <code>CubeInfo</code> object which corresponds to the given
	 * id
	 * @param id the identifier of the <code>CubeInfo</code> object to load
	 * @return the loaded <code>CubeInfo</code> object
	 */	
	public final CubeInfo load(String id) {
		PaloInfo cube = loadedInfo.get(id);
		if (cube == null) {
			cube = paloConnection.getCube(database, id);
			loaded(cube);
		}
		return (CubeInfo)cube;
	}
	
	/**
	 * Loads the <code>CubeInfo</code> object at the specified index
	 * @param index the index of the <code>CubeInfo</code> object to load
	 * @return the loaded <code>CubeInfo</code> object
	 */	
	public final CubeInfo load(int index) {
		String[] cubeIds = getAllCubeIds();
		if(index<0 || index > cubeIds.length-1)
			return null;
		return load(cubeIds[index]);
	}
}