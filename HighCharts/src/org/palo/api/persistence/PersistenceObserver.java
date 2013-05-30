/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.persistence;

/**
 * A <code>PersistenceObserver</code> is used to monitor the loading and saving
 * of persistence palo objects like <code>{@link Subset}</code>s and 
 * <code>{@link CubeView}</code>s. Currently the API tries to load or save a 
 * persistence palo object completely and calls one of the defined callback 
 * methods afterwards.
 *
 * @author ArndHouben
 * @version $Id: PersistenceObserver.java,v 1.4 2007/05/18 12:13:49 ArndHouben Exp $
 **/
public interface PersistenceObserver {
	
	/**
	 * Called when the loading of the palo object failed
	 * @param sourceId the id of failed palo object
	 * @param errors additional information about the reasons
	 */
	void loadFailed(String sourceId, PersistenceError[] errors);
	
	/**
	 * Called when the palo object could not be loaded completely. 
	 * @param source an instance of the loaded palo object
	 * @param errors additional information about the reasons
	 */
	void loadIncomplete(Object source, PersistenceError[] errors);
	
	/**
	 * Called when the palo object could be loaded successfully
	 * @param source instance of loaded palo object
	 */
	void loadComplete(Object source);

	/**
	 * Called when the saving of the given palo object failed
	 * @param source palo object which could not be saved
	 * @param errors additional information about the reasons
	 */
	void saveFailed(Object source, PersistenceError[] errors);
	
	/**
	 * Called when the palo object could not be saved completely
	 * @param source palo object which could not be completely saved
	 * @param errors additional information about the reasons
	 */
	void saveIncomplete(Object source, PersistenceError[] errors);
	
	/**
	 * Called when the saving of the given palo object was successful
	 * @param source palo object which was saved
	 */
	void saveComplete(Object source);

}
