/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.impl;

import org.palo.api.Hierarchy;
import org.palo.api.exceptions.PaloIOException;
import org.palo.api.subsets.Subset2;
import org.palo.api.subsets.SubsetStorageHandler;

/**
 * <code>SubsetStorageHandler</code>
 * <p> This API internal class defines a bridge to the palo server to store and 
 * load <code>Subset2</code>s. All defined methods are abstract meaning that
 * this class cannot be instantiated. While the methods are called from within
 * this package their implementations are in another package. This leads to an 
 * API internal restricted access. 
 * </p>
 *
 * @author ArndHouben
 * @version $Id: SubsetStorageHandlerImpl.java,v 1.8 2008/04/24 14:38:10 ArndHouben Exp $
 **/
public abstract class SubsetStorageHandlerImpl implements SubsetStorageHandler {

	/**
	 * Returns the id of the subset specified by the given hierarchy, name and
	 * type. 
	 * @param hierarchy the subset hierarchy
	 * @param name the subset name
	 * @param type the subset type, either local or global. 
	 * @return the subset id
	 */
	protected abstract String getSubsetId(Hierarchy hierarchy, String name, int type);
	/**
	 * Returns all ids of all globally and locally defined subsets for the 
	 * given hierarchy 
	 * @param hierarchy the hierarchy to check for subsets
	 * @return all ids of all defined subsets
	 */
	protected abstract String[] getSubsetIDs(Hierarchy hierarchy);
	/**
	 * Returns all ids of all defined subsets of given type in specified hierarchy.
	 * @param hierarchy the hierarchy to check for subsets
	 * @param type the subset type, i.e. either local or global
	 * @return all ids of all defined subsets
	 */
	protected abstract String[] getSubsetIDs(Hierarchy hierarchy, int type);
	
	/**
	 * Returns the name of the subset which corresponds to the given id
	 * @param id a valid subset id
	 * @return the name of the subset or <code>null</code> if no subset exists
	 * for the given id
	 */
	protected abstract String getSubsetName(String id);
	/**
	 * Returns the names of all globally and locally defined subsets for the
	 * given hierarchy
	 * @param hierarchy the hierarchy to check for subsets
	 * @return all subset names
	 */
	protected abstract String[] getSubsetNames(Hierarchy hierarchy);

	/**
	 * Returns the names of all defined subsets of given type in specified 
	 * hierarchy.
	 * @param hierarchy the hierarchy to check for subsets
	 * @param type the subset type, i.e. either local or global 
	 * @return all subset names
	 */
	protected abstract String[] getSubsetNames(Hierarchy hierarchy, int type);

	/**
	 * Check if the hierarchy has subsets of the given type
	 * @param hierarchy the hierarchy to check for subsets
	 * @param type the subset type, i.e. either local or global
	 * @return <code>true</code> if the hierarchy has subsets of the given type,
	 * <code>false</code> otherwise
	 */
	protected abstract boolean hasSubsets(Hierarchy hierarchy, int type);
	
	/**
	 * Removes the given subset.
	 * @param subset the subset to remove
	 */
	protected abstract void remove(Subset2 subset);
	
	protected abstract void remove(String id, int type, Hierarchy hierarchy);
	
	/**
	 * Saves the given subset.
	 * @param subset the subset to save
	 * @throws PaloIOException if an error occurred during subset saving
	 */
	protected abstract void save(Subset2 subset) throws PaloIOException;
	/**
	 * Renames the given subset.
	 * @param subset the subset to rename
	 * @param newName the new subset name
	 * @throws PaloIOException if an error occurred during subset renaming
	 */
	protected abstract void rename(Subset2 subset, String newName) throws PaloIOException;

	/**
	 * Creates a new subset cell for internal usage and returns the id of a
	 * newly created subset element
	 * @param name the subset name
	 * @param hierarchy the subset hierarchy
	 * @param type the subset type
	 * @return the id of the subset to create
	 * @throws PaloIOException
	 *             if a subset with same name and type already exists for this
	 *             hierarchy
	 */
	protected abstract String newSubsetCell(String name, Hierarchy hierarchy, int type) throws PaloIOException;
	/**
	 * Loads the specified subset.
	 * @param id identifier of the subset to load 
	 * @param hierarchy the subset hierarchy
	 * @param type the subset type, i.e. either local or global
	 * @param handler the subset handler to use for loading
	 * @return the loaded subset
	 * @throws PaloIOException if an error occurred during subset loading
	 */
	protected abstract Subset2 load(String id, Hierarchy hierarchy, int type, SubsetHandlerImpl handler) throws PaloIOException;
	
}
