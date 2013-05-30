/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.ui.table.impl;

import org.palo.api.ext.ui.Format;
import org.palo.api.ext.ui.table.TableFormat;

/**
 * <code>DefaultTableFormat</code>
 * A default implementation of the <code>{@link TableFormat}</code> interface.
 *
 * @author ArndHouben
 * @version $Id: DefaultTableFormat.java,v 1.1 2007/06/29 15:22:33 ArndHouben Exp $
 **/
class DefaultTableFormat implements TableFormat {

	private Format cellFormat;
	private Format headerFormat;
	
	DefaultTableFormat() {		
	}

	
	final void setCellFormat(Format cellFormat) {
		this.cellFormat = cellFormat;
	}


	final void setHeaderFormat(Format headerFormat) {
		this.headerFormat = headerFormat;
	}


//	final void setPriority(int priority) {
//		this.priority = priority;
//	}
//

	public Format getCellFormat() {
		return cellFormat;
	}

	public Format getHeaderFormat() {
		return headerFormat;
	}

//	public int getPriority() {
//		return priority;
//	}
}
