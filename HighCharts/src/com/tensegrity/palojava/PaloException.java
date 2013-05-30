/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava;

/**
 * <code>PaloException</code> is a runtime exception which signals that a
 * communication exception between a palo client and palo server has occurred.
 *
 * @author Stepan Rutz
 * @version $Id: PaloException.java,v 1.5 2007/08/14 19:58:59 ArndHouben Exp $
 */
public class PaloException extends RuntimeException {
	static final long serialVersionUID = 1;

	//optional error fields:
	private String errorCode;
	private String errorMsg;
	private String errorReason;

//	/**
//	 * Default constructor which just calls its superclass
//	 */
//	public PaloException() {
//		super();
//	}
//	
	/**
     * Constructs an instance of <code>PaloException</code> with the 
     * specified detailed message which describes this particular exception. 
     * @param msg a detailed message
     */
	public PaloException(String msg) {
		super(msg);
	}

	/**
     * Constructs an instance of <code>PaloException</code> with the 
     * specified <code>Exception</cause> as the cause. 
     * @param cause the cause
     */
	public PaloException(Exception cause) {
		super(cause);
	}

	/**
     * Constructs an instance of <code>PaloException</code> with the 
     * specified detailed message and a cause.
     * @param msg a detailed message 
     * @param cause the cause
     */
	public PaloException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	/**
     * Constructs a new instance of <code>PaloException</code> with the 
     * specified error code, error message and reason. 
     * @param errorCode an optional error code. Specifying <code>null</code> is legal.
     * @param errorMsg an optional error message. Specifying <code>null</code> is legal.
     * @param errorReason an optional error reason. Specifying <code>null</code> is legal.
     */
	public PaloException(String errorCode, String errorMsg, String errorReason) {
		super(errorCode+"\n"+errorMsg+"\n"+errorReason);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorReason = errorReason;
	}

	/**
     * Constructs a new instance of <code>PaloException</code> with the 
     * specified cause and detailed error code, error message and reason.
     * @param errorCode an optional error code. Specifying <code>null</code> is legal.
     * @param errorMsg an optional error message. Specifying <code>null</code> is legal.
     * @param errorReason an optional error reason. Specifying <code>null</code> is legal.
     * @param cause the cause
     */
	public PaloException(String errorCode, String errorMsg, String errorReason, Throwable cause) {
		super(errorCode+"\n"+errorMsg+"\n"+errorReason, cause);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorReason = errorReason;
	}

	/**
	 * Returns an optional error code or <code>null</code> if none was defined
	 * @return error code or <code>null</code>
	 */
	public final String getErrorCode() {
		return errorCode;
	}
	
	/**
	 * Returns an optional error description. If none was defined then calling 
	 * this method is equal to calling {@link #getMessage()}
	 * @return an additional error description
	 */
	public final String getDescription() {
		if(errorMsg == null)
			errorMsg = getMessage();
		return errorMsg;
	}
	
	/**
	 * Returns an optional description of the error cause. If none was defined
	 * then calling this method is equal to calling {@link #getDescription()}
	 * @return an addtional description of the error reason
	 */
	public final String getReason() {
		if(errorReason == null)
			errorReason = getDescription();
		return errorReason;
	}

}
