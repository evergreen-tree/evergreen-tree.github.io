/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava;

/**
 * The <code>ElementInfo</code> is a representation of the palo 
 * <code>Element</code> object.
 *  
 * @author ArndHouben
 * @version $Id: ElementInfo.java,v 1.2 2007/04/13 13:57:35 ArndHouben Exp $
 */
public interface ElementInfo extends PaloInfo {
	//element types:
	public static final int 
		TYPE_NUMERIC = 1,
		TYPE_STRING = 2,
		TYPE_CONSOLIDATED = 4,
		TYPE_RULE = 3; //currently not really used!!
	
	/**
	 * Returns the parent <code>Dimension</code> representation of this element
	 * @return parent <code>Dimension</code>
	 */
	public DimensionInfo getDimension();
	
	/**
	 * Returns the element name
	 * @return element name
	 */
	public String getName();	
//	/**
//	 * Sets the element name
//	 * @param name element name
//	 */
//	public void setName(String name);

	/**
	 * Returns the element position within its dimension
	 * @return element position
	 */
	public int getPosition();
//	/**
//	 * Sets the element position within its dimensions
//	 * @param newPosition new element position
//	 */
//	public void setPosition(int newPosition);
	
	/**
	 * Returns the element level
	 * @return element level
	 */
	public int getLevel();
//	/**
//	 * Sets the element level
//	 * @param newLevel new element level
//	 */
//	public void setLevel(int newLevel);

	/**
	 * Returns the element indent
	 * @return element indent
	 */
	public int getIndent();
//	/**
//	 * Sets the element indet
//	 * @param newIndent new element indent
//	 */
//	public void setIndent(int newIndent);

	/**
	 * Returns the element depth
	 * @return element depth
	 */
	public int getDepth();
//	/**
//	 * Sets the element depth
//	 * @param depth element depth
//	 */
//	public void setDepth(int depth);

//	/**
//	 * Sets the element type. The type must be of the defined constants.
//	 * @param type element type
//	 */
//	public void setType(int type);

	/**
	 * Returns the number of parents this element has
	 * @return number of parents
	 */
	public int getParentCount();
	/**
	 * Returns the ids of the parents this element has
	 * @return parent elements ids
	 */
	public String[] getParents();
	/**
	 * Sets the parent ids this element has
	 * @param parents ids of the parent elements
	 */
	public void setParents(String[] parents);
//	/**
//	 * Adds the given parent id to the list of parents this element has
//	 * @param parent an element id
//	 */
//	public void addParent(String parent);
//	/**
//	 * Removes the specified parent from the list of parents this element has
//	 * @param parent an element id
//	 */
//	public void removeParent(String parent);
	
	/**
	 * Returns the number of children this element has
	 * @return number of children
	 */
	public int getChildrenCount();
	/**
	 * Returns the element ids of the children this element has
	 * @return children element ids
	 */
	public String[] getChildren();
	/**
	 * Returns the weight factors for each children 
	 * @return weight factors
	 */
	public double[] getWeights();
//	/**
//	 * Sets the element ids of the children this element has and their 
//	 * correspondig weight factors 
//	 * @param children element ids of the children
//	 * @param weights weight factors
//	 */
//	public void setChildren(String[] children, double[] weights);	
}
