/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava;

/**
 * The <code>DimensionInfo</code> interface is a representation of the palo
 * <code>Dimension</code> object.
 * 
 * @author ArndHouben
 * @version $Id: DimensionInfo.java,v 1.4 2008/03/19 10:04:09 PhilippBouillon Exp $
 */
public interface DimensionInfo extends PaloInfo, PaloConstants {
//	public static final int DIMTYPE_NORMAL = 1;
//	public static final int DIMTYPE_SYSTEM = 2;
//	public static final int	DIMTYPE_ATTRIBUTE = 4;
//	public static final int DIMTYPE_USERINFO = 8;
	
	/**
	 * Returns the <code>Database</code> representation which contains this
	 * dimension
	 * @return <code>Database</code> representation
	 */
	public DatabaseInfo getDatabase();
	/**
	 * Returns the dimension name 
	 * @return dimension name
	 */
	public String getName();
	/**
	 * Sets the dimension name
	 * @param name new dimension name
	 */
	public void setName(String name);
	/**
	 * Returns the id of the corresponding attribute cube
	 * @return attribute cube id
	 */
	public String getAttributeCube();
	/**
	 * Returns the id of the corresponding attribute dimension
	 * @return attribute dimension id
	 */
	public String getAttributeDimension();
	/**
	 * Returns the number of <code>Element</code>s this dimension has
	 * @return number of <code>Element</code>s
	 */
	public int getElementCount();
	/**
	 * Returns the maximum depth of this dimension
	 * @return maximum depth
	 */
	public int getMaxDepth();
	/**
	 * Returns the maximum indent of this dimension
	 * @return maximum indent
	 */
	public int getMaxIndent();
	/**
	 * Returns the maximum level of this dimension
	 * @return maximum level
	 */
	public int getMaxLevel();
	/**
	 * Returns the id of the corresponding rights cube
	 * @return rights cube id
	 */
	public String getRightsCube();
	/**
	 * Returns the dimension token. The dimension token is changed whenever 
	 * something has changed within the dimension, e.g. an <code>Element</code>
	 * was deleted
	 * @return dimension token
	 */
	public int getToken();

	public int getHierarchyCount();
	public HierarchyInfo getDefaultHierarchy();
	public HierarchyInfo [] getHierarchies();	
}
