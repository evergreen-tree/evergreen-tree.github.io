/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.exceptions;

/**
 * <code>PaloIoException</code>
 * Signals that an exception during the saving or loading of palo subsets
 * or views has occurred. 
 *
 * @author ArndHouben
 * @version $Id: PaloIOException.java,v 1.2 2008/01/07 16:31:43 ArndHouben Exp $
 **/
public class PaloIOException extends Exception {

	private static final long serialVersionUID = 3563657088381734321L;
	
	private Object data;
	
    /**
     * Constructs an {@code PaloIOException} with the specified 
     * message.
     * @param message a detailed exception message 
     */
    public PaloIOException(String message) {
		super(message);
	}

    /**
     * Constructs an {@code PaloIOException} with the specified 
     * message and cause.
     * @param message a detailed exception message 
     * @param cause the cause for this exception
     */
    public PaloIOException(String message, Throwable cause) {
		super(message, cause);
	}

    /**
     * Constructs an {@code PaloIOException} with the specified 
     * cause.
     * @param cause the cause for this exception
     */
    public PaloIOException(Throwable cause) {
		super(cause);
	}

    public final void setData(Object data) {
    	this.data = data;
    }
    
    public final Object getData() {
    	return data;
    }
    
}
