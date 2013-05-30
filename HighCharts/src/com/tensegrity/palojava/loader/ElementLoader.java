/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.loader;

import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.ElementInfo;
import com.tensegrity.palojava.HierarchyInfo;
import com.tensegrity.palojava.PaloInfo;

/**
 * <p><code>ElementInfoLoader</code></p>
 * This abstract base class manages the loading of {@link ElementInfo} objects.
 *
 * @author ArndHouben
 * @version $Id: ElementLoader.java,v 1.4 2008/04/15 08:21:00 PhilippBouillon Exp $
 **/
public abstract class ElementLoader extends PaloInfoLoader {

	protected final HierarchyInfo hierarchy;
	
	/**
	 * Creates a new loader instance.
	 * @param paloConnection
	 * @param dimension
	 */
	public ElementLoader(DbConnection paloConnection, HierarchyInfo hierarchy) {
		super(paloConnection);
		this.hierarchy = hierarchy;
	}

	/**
	 * Returns the identifiers of all elements currently known to the palo 
	 * server.
	 * @return ids of all known palo elements
	 */
	public abstract String[] getAllElementIds();
	/**
	 * Loads the <code>ElementInfo</code> object by its name
	 * @param name the name of the <code>ElementInfo</code> to load
	 * @return the loaded <code>ElementInfo</code> instance
	 */
	public abstract ElementInfo loadByName(String name);

	/**
	 * Loads the <code>ElementInfo</code> object at the specified index
	 * @param index the index of the <code>ElementInfo</code> object to load
	 * @return the loaded <code>ElementInfo</code> object
	 */	
	public abstract ElementInfo load(int index);

	public abstract ElementInfo[] getElementsAtDepth(int depth);
	public abstract ElementInfo[] getChildren(ElementInfo parent);
	
	/**
	 * Creates a new {@link ElementInfo} instance with the given name, type,
	 * children and weights
	 * @param name the element name
	 * @param type the element type
	 * @param children the element children
	 * @param weights the children weights  
	 * @return a new <code>ElementInfo</code> object
	 */
	public final ElementInfo create(String name, int type,
			ElementInfo[] children, double[] weights) {
		ElementInfo elInfo = 
			paloConnection.addElement(hierarchy, name,type,children,weights);
		loaded(elInfo);
		return elInfo;
	}

	/**
	 * Deletes the given <code>ElementInfo</code> instance from the palo 
	 * server as well as from the internal used cache.
	 * @param elInfo the <code>ElementInfo</code> instance to delete
	 * @return <code>true</code> if deletion was successful, <code>false</code>
	 * otherwise
	 */
	public final boolean  delete(ElementInfo elInfo) {
		if(paloConnection.delete(elInfo)) {
			removed(elInfo);
			return true;
		} 
		return false;
	}

	/**
	 * Loads the <code>ElementInfo</code> object which corresponds to the given
	 * id
	 * @param id the identifier of the <code>ElementInfo</code> object to load
	 * @return the loaded <code>ElementInfo</code> object
	 */	
	public final ElementInfo load(String id) {
		PaloInfo el = loadedInfo.get(id);
		if (el == null) {
			el = paloConnection.getElement(hierarchy, id);
			loaded(el);
		}
		return (ElementInfo)el;
	}
		
}
