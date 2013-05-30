/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

import org.palo.api.subsets.Subset2;

/**
 * <code>Parameter</code>
 * <p>
 * A parameter assigns a name to a value
 * </p>
 *
 * @author ArndHouben
 * @version $Id: Parameter.java,v 1.2 2008/03/06 18:11:58 ArndHouben Exp $
 **/
public interface Parameter {
	
	
	/**
	 * Returns the parameter name. Note that the name can be <code>null</code>
	 * @return parameter name or <code>null</code> if none was set
	 */
	public String getName();
	
	/**
	 * Returns the parameter value
	 * @return parameter value or <code>null</code> if none was set
	 */
	public Object getValue();
	
	/**
	 * <p>Binds this parameter to the given {@link Subset2}</p>
	 * <b>NOTE: PLEASE DON'T USE! INTERNAL METHOD </b>
	 * @param subset 
	 */
	public void bind(Subset2 subset);
	/**
	 * <p>Releases this parameter from a previously binded {@link Subset2}</p>
	 * <b>NOTE: PLEASE DON'T USE! INTERNAL METHOD </b>
	 */
	public void unbind();
	
}
