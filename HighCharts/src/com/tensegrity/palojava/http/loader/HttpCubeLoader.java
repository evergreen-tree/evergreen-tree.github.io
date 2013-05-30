/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.http.loader;

import java.util.Collection;

import com.tensegrity.palojava.CubeInfo;
import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.loader.CubeLoader;

/**
 * <code>HttpCubeInfoLoader</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: HttpCubeLoader.java,v 1.6 2008/04/17 18:11:50 ArndHouben Exp $
 **/
public class HttpCubeLoader extends CubeLoader {

	public HttpCubeLoader(DbConnection paloConnection, DatabaseInfo database) {
		super(paloConnection, database);		
	}

	public String[] getAllCubeIds() {
		if(!loaded) {			
			reload();
			loaded = true;
		}
		return getLoadedIds();
	}
	
	public String[] getCubeIds(int typeMask) {
		CubeInfo[] cubes = paloConnection.getCubes(database, typeMask);
		String[] ids = new String[cubes.length];
		int counter = 0;		
		for (CubeInfo cube : cubes) {
			loaded(cube);
			ids[counter++] = cube.getId();
		}
		return ids;
	}

	public CubeInfo loadByName(String name) {
		//first check if we have it loaded already
		CubeInfo cube = findCube(name);
		if(cube == null) {
			//if not, we have to ask server...
			reload();
			cube = findCube(name);
		}
		return cube;
	}

	protected final void reload() {
		reset();
		CubeInfo[] cubes = paloConnection.getCubes(database);
		for (CubeInfo cube : cubes) {
			loaded(cube);
		}
	}

	private final CubeInfo findCube(String name) {
		Collection<PaloInfo> infos = getLoaded();
		for (PaloInfo info : infos) {
			if (info instanceof CubeInfo) {
				CubeInfo cube = (CubeInfo) info;
				//PALO IS NOT CASESENSETIVE...
				if (cube.getName().equalsIgnoreCase(name))
					return cube;
			}
		}
		return null;
	}

	public String[] getCubeIds(DimensionInfo dimension) {
		CubeInfo[] cubes = paloConnection.getCubes(dimension);
		String[] cubeIds  = new String[cubes.length];
		int index = 0;
		for (CubeInfo cube : cubes) {
			loaded(cube);
			cubeIds[index++] = cube.getId();
		}
		return cubeIds;
	}

//	public String[] getCubeIds(DimensionInfo dimension, int type) {
//		CubeInfo[] cubes = paloConnection.getCubes(dimension,type);
//		String[] cubeIds  = new String[cubes.length];
//		int index = 0;
//		for (CubeInfo cube : cubes) {
//			loaded(cube);
//			cubeIds[index++] = cube.getId();
//		}
//		return cubeIds;
//	}

}
