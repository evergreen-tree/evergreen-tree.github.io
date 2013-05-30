/*
 * (c) Tensegrity Software 2007. All rights reserved.
 */
package org.palo.api.impl;

import java.util.Collection;
import java.util.Map;

import org.palo.api.CubeView;
import org.palo.api.Database;
import org.palo.api.Subset;
import org.palo.api.persistence.PaloPersistenceException;

/**
 * <p>
 * Defines an abstract controller class to encapsulate the creating and deleting
 * of palo extension objects, like {@link Subset} and {@link CubeView}.
 * </p> 
 * <p>
 * This class is part of the used palo creation pattern which hides the 
 * implementation details of the {@link Subset} and {@link CubeView} interfaces.
 * This means that no implementing class should be accessibly outside the 
 * <code>org.palo.api.impl</code> package and therefore this class cannot be an 
 * interface.
 * </p>
 * 
 * @author ArndHouben
 * @version $Id: AbstractController.java,v 1.6 2007/10/23 11:46:11 ArndHouben Exp $
 */
public abstract class AbstractController {

	/**
	 * Returns an instance of the given class parameter.
	 * @param clObject
	 * @param args constructor parameters used to create a class instance 
	 * @return
	 */
	protected abstract Object create(Class clObject, Object[] args);
	
	/**
	 * Deletes the given extension object 
	 * @param obj
	 */
	protected abstract boolean delete(Object obj);
	
	/**
	 * Loads all extension objects from the given database which are assigned to
	 * the palo object specified by the given id
	 */
	protected abstract Object load(Database db, String id)
			throws PaloPersistenceException;
	
	protected abstract void load(Database db, Map id2id, Map views)
			throws PaloPersistenceException;
	
	protected abstract void init(Database db);

}
