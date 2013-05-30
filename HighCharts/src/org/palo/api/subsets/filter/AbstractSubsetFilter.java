/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter;

import java.util.HashMap;

import org.palo.api.Dimension;
import org.palo.api.Hierarchy;
import org.palo.api.subsets.Subset2;
import org.palo.api.subsets.SubsetFilter;

/**
 * <code>AbstractSubsetFilter</code>
 * <p>
 * API internal abstract implementation of the <code>SubsetFilter</code> 
 * interface. This implementation manages the affective filters for each subset
 * filter.
 * </p>
 * 
 * @author ArndHouben
 * @version $Id: AbstractSubsetFilter.java,v 1.6 2008/05/08 10:13:14 ArndHouben Exp $
 */
abstract class AbstractSubsetFilter implements SubsetFilter {

	protected final HashMap<Integer, EffectiveFilter> effectiveFilters =
		new HashMap<Integer, EffectiveFilter>();

	protected final Hierarchy hierarchy;
	private Subset2 subset;
	
	AbstractSubsetFilter(Dimension dimension) {
		this.hierarchy = dimension.getDefaultHierarchy();
	}
	
	AbstractSubsetFilter(Hierarchy hierarchy) {
		this.hierarchy = hierarchy;
	}
	
	public final void add(EffectiveFilter filter) {
		effectiveFilters.put(new Integer(filter.getType()),filter);
	}

	public final void remove(EffectiveFilter filter) {
		effectiveFilters.remove(new Integer(filter.getType()));
	}
	
	public final void reset() {
		effectiveFilters.clear();
		getSettings().reset();
	}
	
	public final Subset2 getSubset() {
		return subset;
	}
	
	public final void bind(Subset2 subset) {
		this.subset = subset;
		getSettings().bind(subset);
		markDirty();
	}
	
	public final void unbind() {
		this.subset = null;
		getSettings().unbind();
	}
	
	protected final void markDirty() {
		if(subset != null)
			subset.modified();
	}
	
	public final Dimension getDimension() {
		return hierarchy.getDimension();
	}
	
	public final Hierarchy getHierarchy() {
		return hierarchy;
	}
	
	public final void adapt(SubsetFilter from) {
		if(!(from instanceof AbstractSubsetFilter))
			return;
		
		AbstractSubsetFilter fromFilter = (AbstractSubsetFilter)from;		
		reset();
		
		effectiveFilters.putAll(fromFilter.effectiveFilters);
		//settings:
		getSettings().adapt(fromFilter.getSettings());
	}

}
