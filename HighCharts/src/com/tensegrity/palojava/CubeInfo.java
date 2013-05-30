/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava;

import java.math.BigInteger;

/**
 * The <code>CubeInfo</code> interface is a representation of the palo
 * <code>Cube</code> object.
 * 
 * @author ArndHouben
 * @version $Id: CubeInfo.java,v 1.4 2008/01/23 16:41:53 ArndHouben Exp $
 */
public interface CubeInfo extends PaloInfo, PaloConstants {

	public static final int STATUS_UNLOADED = 0;
	public static final int STATUS_LOADED = 1;
	public static final int STATUS_CHANGED = 2;

//	public static final int CUBETYPE_NORMAL = 1;
//	public static final int CUBETYPE_SYSTEM = 2;
//	public static final int	CUBETYPE_ATTRIBUTE = 4;
//	public static final int CUBETYPE_USERINFO = 8;
	
	/**
	 * Returns the cube name
	 * @return cube name
	 */
	public String getName();
	/**
	 * Returns the <code>Database</code> representation which contains this
	 * cube
	 * @return <code>Database</code> representation
	 */
	public DatabaseInfo getDatabase();
	
	/**
	 * Returns the identifiers for the <code>Dimension</code>s which build up
	 * this cube
	 * @return the <code>Dimension</code> ids
	 */
	public String[] getDimensions();
	/**
	 * Returns the number of all cells this cube has
	 * @return number of all cube cells
	 */
	public BigInteger getCellCount();
	/**
	 * Returns the number of <code>Dimension</code>s this cube consists of
	 * @return number of <code>Dimension</code>s
	 */
	public int getDimensionCount();
	/**
	 * Returns the number of filled cells
	 * @return number of filled cells
	 */
	public BigInteger getFilledCellCount();
	/**
	 * Returns the cube status which is one of the defined constants
	 * @return cube status
	 */
	public int getStatus();
	/**
	 * Returns the cube token. The cube token is changed whenever 
	 * something has changed within the cube, e.g. an <code>Element</code>
	 * was deleted
	 * @return cube token
	 */
	public int getToken();

}
