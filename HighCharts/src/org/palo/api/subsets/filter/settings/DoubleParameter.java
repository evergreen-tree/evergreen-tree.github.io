/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

/**
 * <code>DecimalParameter</code>
 * <p> 
 * An implementation of the {@link Parameter} interface for {@link Double}
 * values.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: DoubleParameter.java,v 1.2 2008/03/06 18:11:58 ArndHouben Exp $
 **/
public class DoubleParameter extends AbstractParameter {

	private final String name;
	private Double value;
	
	/**
	 * Creates a new unnamed <code>DoubleParemeter</code> instance
	 */
	public DoubleParameter() {
		this(null);
	}
	
	/**
	 * Creates a new <code>DoubleParemeter</code> instance with the required
	 * name.
	 * @param name the parameter name. Passing <code>null</code> is valid.
	 */
	public DoubleParameter(String name) {
		this.name = name;
		value = new Double(0);
	}
	
	public final String getName() {
		return name;
	}

	public final Double getValue() {
		return value;	
	}
	
	public final void setValue(double value) {
		this.value = value;
		markDirty();
	}
	
	/**
	 * Sets the parameter value. Specifying <code>null</code> is allowed.
	 * @param value new parameter value
	 */
	public final void setValue(Double value) {
		this.value = value;
		markDirty();
	}

}
