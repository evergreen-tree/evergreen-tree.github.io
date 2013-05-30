/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter;

import java.util.Set;

import org.palo.api.Element;

/**
 * <code>RestrictiveFilter</code>
 * <p>
 * A restrictive filter is used to filter out subset elements.
 * </p>
 * 
 * @author ArndHouben
 * @version $Id: RestrictiveFilter.java,v 1.2 2007/12/07 18:07:24 ArndHouben Exp $
 */
public interface RestrictiveFilter extends org.palo.api.subsets.SubsetFilter {

	/**
	 * Filters the given list of {@link Element}s. 
	 * @param elements current list of subset elements
	 */
	public void filter(Set<Element> elements);
}
