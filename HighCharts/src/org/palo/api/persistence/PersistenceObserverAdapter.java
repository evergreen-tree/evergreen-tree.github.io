/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.persistence;

/**
 * A <code>PersistenceObserverAdapter</code> provides empty method 
 * implementations for the <code>{@link PersistenceObserver}</code> interface.
 *
 * @author ArndHouben
 * @version $Id: PersistenceObserverAdapter.java,v 1.1 2007/05/18 10:08:27 ArndHouben Exp $
 **/
public class PersistenceObserverAdapter implements PersistenceObserver {

	public void loadComplete(Object source) {
	}

	public void loadFailed(String sourceId, PersistenceError[] errors) {
	}

	public void loadIncomplete(Object source, PersistenceError[] errors) {
	}

	public void saveComplete(Object source) {
	}

	public void saveFailed(Object source, PersistenceError[] errors) {
	}

	public void saveIncomplete(Object source, PersistenceError[] errors) {
	}

}
