/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.exceptions;

import org.palo.api.Dimension;
import org.palo.api.PaloAPIException;

/**
 * <code>PaloObjectNotFoundException</code>
 * <p>
 * Indicates that a required palo object like e.g. a <code>{@link Dimension}</code>
 * could not be found or does not exists on palo server.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: PaloObjectNotFoundException.java,v 1.1 2007/08/22 09:48:51 ArndHouben Exp $
 **/
public class PaloObjectNotFoundException extends PaloAPIException {

	private static final long serialVersionUID = -7046454439997280322L;
	
	public PaloObjectNotFoundException(String msg) {
		super(msg);
	}

	
	public PaloObjectNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}

//	TODO any own exception which does not provide more information like the 
//	standard exceptions is a bit useless => provide more information ;)
	

}
