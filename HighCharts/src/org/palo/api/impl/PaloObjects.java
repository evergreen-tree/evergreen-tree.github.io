package org.palo.api.impl;

import org.palo.api.Cube;
import org.palo.api.Database;
import org.palo.api.Dimension;
import org.palo.api.PaloConstants;
import org.palo.api.impl.subsets.SubsetPersistence;
import org.palo.api.impl.views.CubeViewManager;


/**
 * A temporary class for distinguish normal palo objects from system palo objects
 * This class is only needed as long as we do not have the new palo api objects.
 * So its for internal usage only!! 
 * @author ArndHouben
 * @deprecated please do not use anymore!! This class will be removed very soon
 * 
 */
class PaloObjects {

	public static final String SYSTEM_PREFIX = "#_";
	public static final String SYSTEM_POSTFIX = "_";
	public static final String SYSTEM_DB = "System";	
	
    public static boolean isSystemDatabase(Database database) {
		String dbName = database.getName();
		return dbName.equals(SYSTEM_DB)
				|| dbName.equals(PaloConstants.PALO_CLIENT_SYSTEM_DATABASE);
	}
    
    public static boolean isSystemCube(Cube cube) {
    	return isSystemCube(cube.getName());
    }
    public static boolean isSystemCube(String cubeName) {
		return cubeName.startsWith(SYSTEM_PREFIX);
	}

	public static boolean isSystemDimension(Dimension dimension) {
		return isSystemDimension(dimension.getName());
	}	
	public static boolean isSystemDimension(String dimName) {
		return dimName.startsWith(SYSTEM_PREFIX)
				&& dimName.endsWith(SYSTEM_POSTFIX);
	}
	
	public static boolean isAttributeDimension(Dimension dimension) {
		return isAttributeDimension(dimension.getName());
	}
	
	public static boolean isAttributeDimension(String dimName) {
		return dimName.startsWith(SYSTEM_PREFIX)
				&& dimName.endsWith(SYSTEM_POSTFIX);
	}
	
	public static boolean isAttributeCube(Cube cube) {
		return isAttributeCube(cube.getName());
	}
	
	public static boolean isAttributeCube(String cubeName) {
		return cubeName.startsWith(SYSTEM_PREFIX);
	}
	
	public static boolean isSubsetCube(Cube cube) {
		return SubsetPersistence.getInstance().isSubsetCube(cube);
	}
	
	public static boolean isViewsCube(Cube cube) {
		return CubeViewManager.getInstance().isViewCube(cube);
	}
	
	public static String getLeafName(String systemName) {
		if(systemName.startsWith(SYSTEM_PREFIX))
			systemName = systemName.substring(2);
		if(systemName.endsWith(SYSTEM_POSTFIX))
			systemName = systemName.substring(0,systemName.length()-1);
		return systemName;
	}
	
	
}