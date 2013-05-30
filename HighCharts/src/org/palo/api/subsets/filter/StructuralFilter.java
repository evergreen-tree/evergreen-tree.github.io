/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter;

import java.util.List;
import java.util.Set;

import org.palo.api.Element;
import org.palo.api.ElementNode;
import org.palo.api.subsets.SubsetFilter;

/**
 * <code>StructuralFilter</code>
 * <p>
 * A structural filter is used to influence the order of subset elements.
 * </p>
 * 
 * @author ArndHouben
 * @version $Id: StructuralFilter.java,v 1.3 2008/03/04 15:22:10 ArndHouben Exp $
 */
public interface StructuralFilter extends SubsetFilter {

	/**
	 * Filters the given element hierarchy represented by 
	 * <code>ElementNode</code>s. The given list contains only the root nodes.
	 * The second parameter contains all currently used elements.
	 * @param hierarchy the <code>ElementNode</code> hierarchy to filter
	 * @param elements the currently used <code>Element</code>s
	 * @return the new, filtered hierarchy
	 */
	public void filter(List<ElementNode> nodes, Set<Element> elements);
}
