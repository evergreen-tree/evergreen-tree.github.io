/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

/**
 * <code>BooleanParameter</code>
 * <p> 
 * An implementation of the {@link Parameter} interface for {@link Boolean}
 * values.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: BooleanParameter.java,v 1.2 2008/03/06 18:11:58 ArndHouben Exp $
 **/
public class BooleanParameter extends AbstractParameter {

	private final String name;
	private Boolean value;
	
	/**
	 * Creates a new unnamed <code>BooleanParemeter</code> instance
	 */
	public BooleanParameter() {
		this(null);
	}
	
	/**
	 * Creates a new <code>BooleanParemeter</code> instance with the required
	 * name.
	 * @param name the parameter name. Passing <code>null</code> is valid.
	 */
	public BooleanParameter(String name) {
		this.name = name;
	}
	
	public final String getName() {
		return name;
	}

	public final Boolean getValue() {
		return value;	
	}
	
	/**
	 * Sets the parameter value. Specifying <code>null</code> is allowed.
	 * @param value new parameter value
	 */
	public final void setValue(boolean value) {
		this.value = value ? Boolean.TRUE : Boolean.FALSE;
		markDirty();
	}

}
