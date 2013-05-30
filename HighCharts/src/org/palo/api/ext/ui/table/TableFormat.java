/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.ui.table;

import org.palo.api.ext.ui.Format;

/**
 * The <code>TableFormat</code> contains format information to use within a table 
 * for visualizing palo elements and values.
 *
 * @author ArndHouben
 * @version $Id: TableFormat.java,v 1.1 2007/06/29 15:22:43 ArndHouben Exp $
 **/
public interface TableFormat {

	/**
	 * Returns the format to use for the header
	 * @return
	 */
	public Format getHeaderFormat();
	
	/**
	 * Returns the format to use for displaying the cell
	 * @return
	 */
	public Format getCellFormat();

}
