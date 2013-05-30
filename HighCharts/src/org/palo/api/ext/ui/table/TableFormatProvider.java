/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.ui.table;

import java.util.HashMap;
import java.util.HashSet;

import org.palo.api.Attribute;
import org.palo.api.Dimension;
import org.palo.api.Element;
import org.palo.api.Hierarchy;
import org.palo.api.ext.ui.table.impl.TableFormatPersistence;

/**
 * The <code>TableFormatterProvider</code> class provides methods to check the 
 * existence and/or receive <code>TableFormat</code>s for <code>{@link Element}</code>s 
 * 
 *
 * @author ArndHouben
 * @version $Id: TableFormatProvider.java,v 1.5 2008/04/21 10:39:49 PhilippBouillon Exp $
 **/
public class TableFormatProvider {

	//--------------------------------------------------------------------------
	// FACTORY 
	//
	private static final TableFormatProvider instance = new TableFormatProvider();
	
	private final HashMap <Element, TableFormat> formats = 
		new HashMap<Element, TableFormat>();
	private final HashSet <Element> formatSet =
		new HashSet<Element>();
	
	/**
	 * Returns the sole instance of this <code>TableFormatProvider</code>
	 * @return the <code>TableFormatProvider</code> instance
	 */
	public static final TableFormatProvider getInstance() {
		return instance;
	}
	
	//--------------------------------------------------------------------------
	// INSTANCE
	//
	private TableFormatProvider() {
	}
	
	/**
	 * Reads in the defined <code>TableFormat</code> for the given 
	 * <code>{@link Element}</code>. If no format was defined 
	 * <code>null</code> is returned.
	 * @param element the <code>{@link Element}</code> to get
	 * <code>TableFormat</code> for
	 * @return <code>TableFormat</code>, or <code>null</code> if none was defined
	 */
	public final TableFormat getFormat(Element element) {
		if (!formatSet.contains(element)) {
			formatSet.add(element);
			if(hasFormat(element)) {
				String xmlStr = getFormatDefinition(element);
				// read in format...
				TableFormatPersistence persister = TableFormatPersistence.getInstance();
				TableFormat f = persister.read(xmlStr);
				formats.put(element, f);
				return f;
			}
		} else {
			return formats.get(element);
		}
		return null;
	}
	
	/**
	 * Checks if the given <code>{@link Element}</code> has a 
	 * <code>TableFormat</code>. 
	 * @param element the <code>{@link Element}</code> to check
	 * <code>TableFormat</code> existence for 
	 * @return <code>true</code> if given element has a defined 
	 * <code>TableFormat</code>, <code>false</code> otherwise
	 */
	public final boolean hasFormat(Element element) {
		if(element == null)
			return false;
		Hierarchy hierarchy = element.getHierarchy();
		if(hierarchy.getDimension().getDatabase().getConnection().isLegacy())
			return false;
		if(hierarchy.isAttributeHierarchy() ||
		   hierarchy.getDimension().isSystemDimension())
			return false;
		
		return getFormatDefinition(element).length()>0;
	}
	
	
	//--------------------------------------------------------------------------
	// PRIVATE METHODS
	//
	private final String getFormatDefinition(Element element) {
		Attribute attribute = getFormatAttribute(element);
		if(attribute != null) {
			//check its value:
			return attribute.getValue(element).toString();
		}
		return "";
		
	}
	
	private final Attribute getFormatAttribute(Element element) {
		return getFormatAttribute(element.getHierarchy());
	}
	
	private final Attribute getFormatAttribute(Hierarchy hierarchy) {
		try {
			Attribute[] attributes = hierarchy.getAttributes();
			for (int i = 0; i < attributes.length; i++) {
				if ("format".equalsIgnoreCase(attributes[i].getName()))
					return attributes[i];
			}
		} catch (Exception ex) {
//			System.err.println(
//					"failed to receive format attribute for dimension '"
//					+ dimension.getName() + "'!");
			/* ignore */
		}
		return null;
	}
}
