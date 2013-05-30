/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

/**
 * <code>IntegerParameter</code>
 * <p> 
 * An implementation of the {@link Parameter} interface for {@link Integer}
 * values.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: IntegerParameter.java,v 1.2 2008/03/06 18:11:58 ArndHouben Exp $
 **/
public class IntegerParameter extends AbstractParameter {

	private final String name;
	private Integer value;
	
	/**
	 * Creates a new unnamed <code>IntegerParemeter</code> instance
	 */
	public IntegerParameter() {
		this(null);
	}
	
	/**
	 * Creates a new <code>IntegerParemeter</code> instance with the required
	 * name.
	 * @param name the parameter name. Passing <code>null</code> is valid.
	 */
	public IntegerParameter(String name) {
		this.name = name;
		value = new Integer(0);
	}
	
	public final String getName() {
		return name;
	}

	public final Integer getValue() {
		return value;	
	}
	
	/**
	 * Sets the parameter value. Specifying <code>null</code> is allowed.
	 * @param value new parameter value
	 */
	public final void setValue(int value) {
		this.value = value;
		markDirty();
	}
	
	public final void setValue(Integer value) {
		this.value = value;
		markDirty();
	}
}
