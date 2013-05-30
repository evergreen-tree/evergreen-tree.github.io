/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

/**
 * <code>StringParameter</code>
 * <p> 
 * An implementation of the {@link Parameter} interface for {@link String}
 * values.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: StringParameter.java,v 1.2 2008/03/06 18:11:58 ArndHouben Exp $
 **/
public class StringParameter extends AbstractParameter {
	
	private final String name;
	private String value;

	/**
	 * Creates a new unnamed <code>StringParemeter</code> instance
	 */
	public StringParameter() {
		this(null);
	}

	/**
	 * Creates a new <code>StringParemeter</code> instance with the required
	 * name.
	 * @param name the parameter name. Passing <code>null</code> is valid.
	 */
	public StringParameter(String name) {
		this.name = name;
		value = new String();
	}
	
	public final String getName() {
		return name;
	}

	public final String getValue() {
		return value;	
	}
	
	/**
	 * Sets the parameter value. Specifying <code>null</code> is allowed.
	 * @param value new parameter value
	 */
	public final void setValue(String value) {
		this.value = value;
		markDirty();
	}
}
