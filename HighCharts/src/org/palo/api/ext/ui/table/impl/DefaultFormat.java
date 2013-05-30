/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.ui.table.impl;

import org.palo.api.ext.ui.FontDescriptor;
import org.palo.api.ext.ui.Format;
import org.palo.api.ext.ui.ColorDescriptor;

/**
 * <code>DefaultFormat</code>
 * A default implementation of the <code>{@link Format}</code> interface
 *
 * @author ArndHouben
 * @version $Id: DefaultFormat.java,v 1.1 2007/06/29 15:22:33 ArndHouben Exp $
 **/
class DefaultFormat implements Format {

	private int priority;
	private ColorDescriptor bgColor;
	private ColorDescriptor fontColor;
	private FontDescriptor font;
	private String nrFmtPattern;
	
	
	public final ColorDescriptor getBackGroundColor() {
		return bgColor;
	}

	public final FontDescriptor getFont() {
		return font;
	}

	public final ColorDescriptor getFontColor() {
		return fontColor;
	}
	
	public final String getNumberFormatPattern() {
		return nrFmtPattern;
	}

	public final int getPriority() {
		return priority;
	}


	final void setBgColor(ColorDescriptor bgColor) {
		this.bgColor = bgColor;
	}

	final void setFont(FontDescriptor font) {
		this.font = font;
	}
	
	final void setFontColor(ColorDescriptor fontColor) {
		this.fontColor = fontColor;
	}

	final void setNumberFormatPattern(String nrFmtPattern) {
		this.nrFmtPattern = nrFmtPattern;
	}

	final void setPriority(int priority) {
		this.priority = priority;
	}
}
