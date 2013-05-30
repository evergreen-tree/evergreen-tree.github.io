/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api;

/**
 * <code>VirtualDimensionDefinition</code>
 * <p>
 * Virtual dimension definitions are used to build up a virtual cube. Each 
 * virtual dimension has a reference to its normal source dimension. 
 * A <code>DimensionFilter</code> determines which <code>Element</code>s
 * from the source <code>Dimension</code> are also visible within the virtual
 * one.
 * </p>
 *
 * @author Stepan Rutz
 * @version $ID$
 * @deprecated please use <code>CubeView</code>s and <code>Axis</code>s to 
 * persist a certain cube state
 */
public interface VirtualDimensionDefinition {
	
	/**
	 * Returns the base source <code>Dimension</code>
	 * @return the base <code>Dimension</code>
	 */
	Dimension getSourceDimension();
	
	/**
	 * Returns the used dimension filter
	 * @return the <code>DimensionFilter</code>
	 */
	DimensionFilter getFilter();
	
	Element[] getElements();
	ElementNode[] getRootElements();
	boolean isFlat();
	
	/**
	 * Returns the name of the currently active subset of this virtual dimension,
	 * or <code>null</code> if no subset is active.
	 * @return name of currently active subset
	 */
	String getActiveSubset();
	
	Hierarchy getActiveHierarchy();
	void setActiveHierarchy(Hierarchy activeHierarchy);
}
