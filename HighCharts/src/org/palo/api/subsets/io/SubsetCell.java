/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.io;

import org.palo.api.Element;

/**
 * <code>SubsetCell</code>
 * <p>
 * API internal helper class. Represents a cell in a subset cube.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: SubsetCell.java,v 1.2 2007/12/07 13:00:37 ArndHouben Exp $
 **/
class SubsetCell {
	
	private final String id;
	private String xmlDef;
	private Element[] coordinate;
	
	/**
	 * Creates a new <code>SubsetCell</code> instance with the given id.
	 * By convention the cell id is equal to the element id of corresponding 
	 * subset
	 * @param id the subset element id
	 */
	SubsetCell(String id) { 
		this.id = id;
	}
	
	String getSubsetId() {
		return id;
	}
	
	void setXmlDef(String xmlDef) {
		this.xmlDef = xmlDef;
	}
	
	void setCoordinate(Element[] coordinate) {
		this.coordinate = coordinate;
	}
		
	Element[] getCoordinate() {
		return coordinate;
	}
	
	String getXmlDef() {
		return xmlDef;
	}
}
