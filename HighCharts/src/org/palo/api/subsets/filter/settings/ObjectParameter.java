/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

/**
 * <code>GeneralParameter</code>
 * <p>
 * An implementation of the {@link Parameter} interface to store a general
 * value.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: ObjectParameter.java,v 1.3 2008/03/06 18:11:58 ArndHouben Exp $
 **/
public class ObjectParameter extends AbstractParameter {

	private String name;
	private Object value;
	
	/**
	 * Creates a new parameter instance with no name
	 */
	public ObjectParameter() {
		this(null);
	}
	
	/**
	 * Creates a new parameter instance with the given name
	 * @param name the parameter name
	 */
	public ObjectParameter(String name) {
		this.name = name;
	}
	
	public final String getName() {
		return name;
	}

	public final Object getValue() {
		return value;
	}

	/**
	 * Sets the parameter name. Specifying <code>null</code> is allowed.
	 * @param name new parameter name
	 */
	public final void setName(String name) {
		this.name = name;		
	}

	/**
	 * Sets the parameter value. Specifying <code>null</code> is allowed.
	 * @param value new parameter value
	 */
	public final void setValue(Object value) {
		this.value = value;
		markDirty();
	}
}
