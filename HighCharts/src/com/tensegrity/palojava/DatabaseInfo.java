/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava;

/**
 * The <code>DatabaseInfo</code> is a representation of the palo
 * <code>Database</code> object.
 *  
 * @author ArndHouben
 * @version $Id: DatabaseInfo.java,v 1.2 2007/10/21 18:17:28 ArndHouben Exp $
 */
public interface DatabaseInfo extends PaloInfo, PaloConstants {
	
	public static final int STATUS_UNLOADED = 0;
	public static final int STATUS_LOADED = 1;
	public static final int STATUS_CHANGED = 2;
	
	/**
	 * Returns the database name
	 * @return database name
	 */
	public String getName();
	/**
	 * Returns the number of <code>Cube</code>s this database has
	 * @return
	 */
	public int getCubeCount();
	/**
	 * Returns the number of <code>Dimension</code>s this database has
	 * @return
	 */
	public int getDimensionCount();
	/**
	 * Returns the database status which is one of the specified constants
	 * @return database status
	 */
	public int getStatus();
	/**
	 * Returns the database token. The database token is changed whenever 
	 * something has changed within the database, e.g. an <code>Element</code>
	 * was deleted
	 * @return database token
	 */
	public int getToken();

	/**
	 * Checks if this palo info object represents a palo system object 
	 * @return <code>true</code> if this palo info object represents a palo
	 * system object, <code>false</code> otherwise
	 * 
	 */
	public boolean isSystem();
}
