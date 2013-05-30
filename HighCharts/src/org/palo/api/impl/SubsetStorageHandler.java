package org.palo.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.palo.api.Connection;
import org.palo.api.Database;
import org.palo.api.Dimension;
import org.palo.api.Hierarchy;
import org.palo.api.PaloAPIException;
import org.palo.api.Subset;
import org.palo.api.impl.subsets.SubsetPersistence;
import org.palo.api.persistence.PaloPersistenceException;

/**
 * A <code>SubsetStorageHandler</code> encapsulates the lazy loading of subsets 
 * <b>NOTE:</b> This class is only temporarely and will be removed when palo 
 * server supports subsets natively!!
 *  
 * @author ArndHouben
 * @version $Id: SubsetStorageHandler.java,v 1.12 2008/04/15 10:27:47 ArndHouben Exp $
 * @deprecated will be removed soon. Please use new Subset2StorageHandler
 */
class SubsetStorageHandler {
	
	private final Database database;
	private Map<String, Subset> loadedSubsets;
	
	SubsetStorageHandler(Database database) {
		this.database = database;		
//		this.loadedSubsets = new HashMap<String, Subset>();
	}
	
	
	final Subset addSubset(Dimension dimension, String id, String name) {
		// TODO What's this? Recursive call??
		return addSubset(dimension.getDefaultHierarchy(), null, name);
	}
	
	final Subset addSubset(Hierarchy hierarchy, String id, String name) {
		// TODO What's this? Recursive call??
		return addSubset(hierarchy, null, name);
	}
	
	final Subset addSubset(Dimension dimension, String name) {
		throw new PaloAPIException("Legacy subsets are not supported anymore! Please use new subsets!");
	}
	
	final Subset addSubset(Hierarchy hierarchy, String name) {
		throw new PaloAPIException("Legacy subsets are not supported anymore! Please use new subsets!");
	}
	
	final void removeSubset(Dimension dimension, Subset subset) {
		if (subset != null && subset.getHierarchy().equals(dimension.getDefaultHierarchy())) {
			// PR 6520: first remove it from db then remove it from cache!! not
			// vice versa...
			SubsetPersistence.getInstance().delete(subset);
			if(loadedSubsets != null)
				loadedSubsets.remove(subset.getId());
		}
	}
	
	final void removeSubset(Hierarchy hierarchy, Subset subset) {
		if (subset != null && subset.getHierarchy().equals(hierarchy)) {
			// PR 6520: first remove it from db then remove it from cache!! not
			// vice versa...
			SubsetPersistence.getInstance().delete(subset);
			loadedSubsets.remove(subset.getId());
		}
	}

	final Subset getSubset(Dimension dimension, String id)
			throws PaloPersistenceException {
		if(loadedSubsets == null)
			reload();
		
		Subset subset = loadedSubsets.get(id);
		if(subset != null && subset.getHierarchy().equals(dimension.getDefaultHierarchy()))
			return subset;
		return null;
	}

	final Subset getSubset(Hierarchy hierarchy, String id)
			throws PaloPersistenceException {
		if (loadedSubsets.isEmpty())
			reload();

		Subset subset = loadedSubsets.get(id);
		if (subset != null && subset.getHierarchy().equals(hierarchy))
			return subset;
		return null;
	}

	final Subset[] getSubsets(Dimension dimension)
			throws PaloPersistenceException {
		if (dimension.getDatabase().getConnection().getType() == Connection.TYPE_XMLA)
			return new Subset[0];
		
		if(loadedSubsets == null)
			reload();
		
		ArrayList<Subset> subsets = new ArrayList<Subset>();
		for(Subset subset : loadedSubsets.values()) {
			if(subset.getHierarchy().equals(dimension.getDefaultHierarchy()))
				subsets.add(subset);
		}
		return subsets.toArray(new Subset[subsets.size()]);
	}
	
	final Subset[] getSubsets(Hierarchy hierarchy)
			throws PaloPersistenceException {
		if (hierarchy.getDimension().getDatabase().getConnection().getType() == Connection.TYPE_XMLA)
			return new Subset[0];

		if (loadedSubsets.isEmpty())
			reload();

		ArrayList<Subset> subsets = new ArrayList<Subset>();
		for (Subset subset : loadedSubsets.values()) {
			if (subset.getHierarchy().equals(hierarchy))
				subsets.add(subset);
		}
		return subsets.toArray(new Subset[subsets.size()]);
	}

	final void reload() {
		if(loadedSubsets == null)
			loadedSubsets = new HashMap<String, Subset>();
		else
			loadedSubsets.clear();
		reloadAll();
	}
	
	final boolean hasSubsets() {
		return SubsetPersistence.getInstance().hasSubsets(database);
	}
	
	/**
	 * Simply reloads all subsets from database
	 */
	private final void reloadAll() {
		Subset[] subsets = SubsetPersistence.getInstance().loadAll(database);
		for(Subset subset : subsets) {
			loadedSubsets.put(subset.getId(), subset);
		}
	}
	
}

