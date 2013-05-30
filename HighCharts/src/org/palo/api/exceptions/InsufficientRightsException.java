/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.exceptions;

import org.palo.api.PaloAPIException;

/**
 * <code>InsufficientRightsException</code>
 * Signals that a certain operation has not enough rights to perform.
 *
 * @author ArndHouben
 * @version $Id: InsufficientRightsException.java,v 1.1 2007/11/25 19:00:29 ArndHouben Exp $
 **/
public class InsufficientRightsException extends PaloAPIException {
	
//	insufficient

    /**
     * Creates an {@code InsufficientRightsException} with the specified message.
     * @param message a detailed exception message 
     */
    public InsufficientRightsException(String message) {
		super(message);
	}

    /**
     * Constructs an {@code InsufficientRightsException} with the specified 
     * message and cause.
     * @param message a detailed exception message 
     * @param cause the cause for this exception
     */
    public InsufficientRightsException(String message, Throwable cause) {
		super(message, cause);
	}

}
