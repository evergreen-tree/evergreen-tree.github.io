/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.ui.table.impl;

import org.palo.api.ext.ui.table.TableFormat;

/**
 * <code>TableFormatterPersistence</code>
 * This class is used to read <code>{@link TableFormat}</code> instances from
 * xml definition.  
 *
 * @author ArndHouben
 * @version $Id: TableFormatPersistence.java,v 1.1 2007/06/29 15:22:33 ArndHouben Exp $
 **/
public class TableFormatPersistence {

	private static final TableFormatPersistence instance = new TableFormatPersistence();
	public static final TableFormatPersistence getInstance() {
		return instance;
	}
	
	private TableFormatPersistence() {		
	}
	
	public TableFormat read(String xmlStr) {
		return TableFormatReader.getInstance().fromXML(xmlStr);
	}
}
