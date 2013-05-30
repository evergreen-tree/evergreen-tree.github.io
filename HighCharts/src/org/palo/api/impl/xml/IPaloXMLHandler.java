/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.impl.xml;

/**
 * <code>IPaloXMLHandler</code>
 * To use in conjunction with <code>{@link BaseXMLHandler}</code> and
 * <code>{@link IPaloStartHandler}</code> and <code>{@link IPaloEndHandler}</code>.
 *
 * @author ArndHouben
 * @version $Id: IPaloXMLHandler.java,v 1.1 2007/05/08 15:50:32 ArndHouben Exp $
 **/
interface IPaloXMLHandler {

	/**
	 * Returns the xml path used to register an xml handler 
	 * @return
	 */
	public String getPath();
}
