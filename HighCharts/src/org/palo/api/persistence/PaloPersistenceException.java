/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.persistence;


/**
 * <code>PaloPersistenceException</code> defines an exception which is thrown 
 * in case of problems during loading or saving of palo <code>API</code> 
 * persistence objects like <code>CubeView</code>s or <code>Subset</code>s. 
 * <code>{@link PersistenceError}</code>s are used to provide additional 
 * information. Please use {@link #getErrors()} to travers the list of nested
 * errors.
 *
 * @see PersistenceError
 * 
 * @author ArndHouben
 * @version $Id: PaloPersistenceException.java,v 1.4 2007/11/19 11:38:37 PhilippBouillon Exp $
 **/
public class PaloPersistenceException extends Exception {
	
	public static final int TYPE_UNDEFINED = -1;
	public static final int TYPE_LOAD_FAILED = 0;
	public static final int TYPE_LOAD_INCOMPLETE = 1;
	public static final int TYPE_SAVE_FAILED = 2;
	public static final int TYPE_SAVE_INCOMPLETE = 4;
	
	private static final long serialVersionUID = 20070510L;
		
	private final PersistenceError[] errors;
	
	private int type;

	/**
	 * Default constructor
	 * @param errors provide additional information about this exception
	 */
	public PaloPersistenceException(PersistenceError[] errors) {
		this(errors,"");
	}
	
	/**
	 * 
	 * @param errors provide additional information about this exception
	 * @param msg a descriptive error message
	 */
	public PaloPersistenceException(PersistenceError[] errors, String msg) {
		super(msg);
		this.errors = errors;
		boolean failed = false;
		for (PersistenceError error: errors) {
			if (error.getType() == PersistenceError.LOADING_FAILED) {
				failed = true;
				break;
			}
		}
		if (failed) {
			type = TYPE_LOAD_FAILED;
		} else {
			type = TYPE_LOAD_INCOMPLETE;
		}
	}
	
	/**
	 * Returns all registered errors or <code>null</code>
	 * @return registered errors or <code>null</code>
	 */
	public PersistenceError[] getErrors() {
		return errors;
	}
	
	public final void setType(int type) {
		this.type = type;
	}
	
	public final int getType() {
		return type;
	}
}
