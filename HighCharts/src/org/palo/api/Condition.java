/*
 * (c) Tensegrity Software 2007. All rights reserved.
 */
package org.palo.api;

/**
 * This interface represents an export condition used for the 
 * <code>ExportContext</code>
 * 
 * @author ArndHouben
 * @version $Id: Condition.java,v 1.1 2007/03/12 16:51:53 ArndHouben Exp $
 */
public interface Condition {

	/** '<code>==</code>' comparator*/
	public static final String EQ = "==";
	/** '<code><</code>' comparator */
	public static final String LT = "<";
	/** '<code>></code>' comparator */
	public static final String GT = ">";
	/** '<code><=</code>' comparator */
	public static final String LTE = "<=";
	/** '<code>>=</code>' comparator */
	public static final String GTE = ">=";
	/** '<code>!=</code>' comparator */
	public static final String NEQ = "!=";

	
	/** 
	 * Sets the numeric value for this condition
	 * @param numeric 
	 */
	void setValue(double value);
	/**
	 * Sets the string value for this condition
	 * @param string
	 */
	void setValue(String value);
		
	/**
	 * Returns the current value for this condition
	 * @return the condition value or null, if none has been set
	 */
	String getValue();
	
}
