/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.io;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * <code>SubsetReaderErrorHandler</code>
 * Implementation of the {@link ErrorHandler} interface which handles all errors
 * and warnings which occur during parsing of subset xml definition 
 *
 * @author ArndHouben
 * @version $Id: SubsetReaderErrorHandler.java,v 1.5 2008/05/15 10:55:14 ArndHouben Exp $
 **/
class SubsetReaderErrorHandler implements ErrorHandler {

	private boolean isValid = true;
	
	public final void error(SAXParseException exception) throws SAXException {
		//PR 7075: we ignore wrong values for parameter values...
		//since we cannot ask exception what went wrong we have to rely on
		//message parsing :(
		String msg = exception.getMessage();
		int valIndex = msg.indexOf("''"); 	//search for empty values...
		if(valIndex > 0) {
			if((msg.indexOf("'boolean'", valIndex) > 0) 
				|| (msg.indexOf("'integer'", valIndex) > 0) 
				|| (msg.indexOf("'decimal'", valIndex) > 0)
				|| (msg.indexOf("'string'", valIndex) > 0)
				|| (msg.indexOf("'value'", valIndex) > 0))
				return; //* ignore *
		}
		
		isValid = false;
		System.err.println("SUBSET READER ERROR_MSG: " + exception.getMessage());
	}

	public final void fatalError(SAXParseException exception) throws SAXException {
		isValid = false;
//		System.err.println("SUBSET READER FATAL ERROR_MSG: " 
//				+ exception.getMessage());
	}

	public final void warning(SAXParseException exception) throws SAXException {
		System.err.println("SUBSET READER WARNING_MSG: " + exception.getMessage());
	}
	
	/**
	 * Checks if any errors or fatal errors occurred during subset parsing 
	 * @return <code>true</code> if any errors occurred during parsing, 
	 * <code>false</code> otherwise
	 */
	final boolean hasErrors() {
		return !isValid;
	}
}
