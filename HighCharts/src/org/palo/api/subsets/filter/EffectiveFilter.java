/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter;

import org.palo.api.subsets.SubsetFilter;

/**
 * <code>EffectiveFilter</code>
 * <p>
 * An affective filter is used to influence other filters.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: EffectiveFilter.java,v 1.1 2008/04/15 08:20:57 PhilippBouillon Exp $
 **/
public interface EffectiveFilter extends SubsetFilter {

	/**
	 * Returns the filter types which are affected by this filter.
	 * @return the affected filter types
	 */
	public int[] getEffectiveFilter();
	
//	/**
//	 * Returns the effective value for the given element and filter type.
//	 * Please note that the value is returned as <code>String</code> because 
//	 * the calling filter has to interpret the returned value correctly.
//	 * @param element the affected element
//	 * @param type the effective filter type
//	 * @return the effective element value as string
//	 */
//	public String getEffectiveValue(Element element, int type);

}
