/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets;

import org.palo.api.Subset;

/**
 * <code>SubsetStorageHandler</code>
 * <p>A simple interface for handling storage related subset functionalities.</p>
 * 
 * @author ArndHouben
 * @version $Id: SubsetStorageHandler.java,v 1.4 2007/12/06 14:42:40 ArndHouben Exp $
 **/
public interface SubsetStorageHandler {
	
	/**
	 * Checks if current user is allowed to read any <code>Subset2</code>s of 
	 * given type. The subset type must be one of the predefined subset type
	 * constants.
	 * @param subsetType a valid subset type constant 
	 * @return <code>true</code> if user is allowed to read subsets of given
	 * type, <code>false</code> otherwise
	 */
	public boolean canRead(int subsetType);

	/**
	 * Checks if current user is allowed to write any <code>Subset2</code>s of 
	 * given type. The subset type must be one of the predefined subset type
	 * constants.
	 * @param subsetType a valid subset type constant 
	 * @return <code>true</code> if user is allowed to write subsets of given
	 * type, <code>false</code> otherwise
	 */
	public boolean canWrite(int subsetType);
	
	/**
	 * Tries to convert the given legacy subsets into new <code>Subset2</code>s
	 * of given type. 
	 * @param legacySubsets
	 * @param type
	 * @param remove
	 */
	public void convert(Subset[] legacySubsets, int type, boolean remove);
	
	/**
	 * Resets this storage handler. This will clear all internal used caches
	 * too.
	 */
	public void reset();
	
}
