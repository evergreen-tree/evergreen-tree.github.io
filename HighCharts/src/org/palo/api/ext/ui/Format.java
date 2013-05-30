/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.ui;

import java.text.DecimalFormat;


/**
 * <code>Format</code> contains general format settings to use for displaying
 * values and text
 * 
 * @author ArndHouben
 * @version $Id: Format.java,v 1.2 2007/12/21 07:39:59 PhilippBouillon Exp $
 **/
public interface Format {

	/**
	 * Returns the priority of this <code>Format</code>. If two 
	 * <code>Format</code>s are defined for the same object the priority can
	 * be used to decide which one to apply.
	 * @return the format priority
	 */
	public int getPriority();



	/**
	 * Returns the format pattern to use for displaying a numeric value.
	 * Usually this pattern can be used to initialize an instance of {@link DecimalFormat} 
	 * @return number format pattern for numeric values
	 */
	public String getNumberFormatPattern();
	/**
	 * Returns the <code>ColorDescriptor</code> instance which describes the 
	 * background color to use
	 * @return an <code>ColorDescriptor</code> instance 
	 */
	public ColorDescriptor getBackGroundColor();
	
	/**
	 * Returns the <code>FontDescriptor</code> instance to use for displaying 
	 * text and values
	 * @return a <code>FontDescriptor</code> instance
	 */
	public FontDescriptor getFont();
	
	/**
	 * Returns the <code>ColorDescriptor</code>instance which describes the 
	 * font color to use
	 * @return an <code>ColorDescriptor</code> instance 
	 */	
	public ColorDescriptor getFontColor();
}
