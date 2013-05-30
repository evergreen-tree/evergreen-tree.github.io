/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

import org.palo.api.subsets.Subset2;



/**
 * <code>FilterSetting</code>
 * <p>
 * A simple filter setting definition. We only require that a filter setting
 * can be reseted and be adapted from another filter setting of same kind.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: FilterSetting.java,v 1.3 2008/03/06 18:11:58 ArndHouben Exp $
 **/
public interface FilterSetting {

	/**
	 * Resets the filter setting to its defaults.
	 */
	public void reset();
	/**
	 * Adapt the filter setting from the given filter setting. After that both
	 * settings are the same.
	 * @param from the filter setting to adapt from
	 */
	public void adapt(FilterSetting from);
	
	/**
	 * <p>Binds this filter settings instance to the given {@link Subset2}</p>
	 * <b>NOTE: PLEASE DON'T USE! INTERNAL METHOD </b>
	 * @param subset 
	 */
	public void bind(Subset2 subset);
	/**
	 * <p>Releases this filter settings instance from a previously binded 
	 * {@link Subset2}</p>
	 * <b>NOTE: PLEASE DON'T USE! INTERNAL METHOD </b>
	 */
	public void unbind();

}
