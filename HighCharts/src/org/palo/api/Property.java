/*
 * (c) Tensegrity Software 2007. All rights reserved.
 */
package org.palo.api;

/**
 * A <code>Property</code> object can be attached to several classes to provide
 * application specific additional information. The developer can specify
 * arbitrary key/value pairs via this class.
 * 
 * @author Philipp Bouillon
 * @version $Id: Property.java,v 1.2 2008/01/11 15:51:01 PhilippBouillon Exp $
 * @deprecated Use Property2 instead.
 */
public class Property {
	/**
	 * The id of this property.
	 */
	private String id;
	
	/**
	 * The value of this property.
	 */
	private String value;
	
	/**
	 * Creates a new key/value pair with the given data.
	 * 
	 * @param id the id of the new property.
	 * @param value the value of the new property.
	 */
	public Property(String id, String value) {
		this.id = id;
		this.value = value;
	}
	
	/**
	 * Returns the id of this property object.
	 * @return the id of this property object.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Returns the value of this property object.
	 * @return the value of this property object.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets a new value for this property.
	 * 
	 * @param newValue the new value for this property.
	 */
	public void setValue(String newValue) {
		value = newValue;
	}
}
