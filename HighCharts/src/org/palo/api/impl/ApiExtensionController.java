/*
 * (c) Tensegrity Software 2007. All rights reserved.
 */
package org.palo.api.impl;

import java.util.Map;

import org.palo.api.Cube;
import org.palo.api.CubeView;
import org.palo.api.Database;
import org.palo.api.Property;
import org.palo.api.Subset;
import org.palo.api.impl.views.CubeViewManager;
import org.palo.api.persistence.PaloPersistenceException;

/**
 * Provides methods for creating the api extensions like {@link Subset} and
 * {@link CubeView}.
 * <p>
 * This class is part of the used palo creation pattern which hides the 
 * implementation details of the {@link Subset} and {@link CubeView} interfaces.
 * This means that no implementing class should be accessibly outside the 
 * <code>org.palo.api.impl</code> package and therefore this class cannot be an 
 * interface.
 * </p> 
 * 
 * @author ArndHouben
 * @version $Id: ApiExtensionController.java,v 1.12 2007/11/21 14:50:17 ArndHouben Exp $
 */
class ApiExtensionController {
	
	private static final ApiExtensionController instance = 
		new ApiExtensionController();
	
	static final ApiExtensionController getInstance() {
		return instance;
	}
	
	//--------------------------------------------------------------------------
	// INSTANCES
	//
	private final AbstractController viewManager = CubeViewManager.getInstance();
//	private final AbstractController subsetManager = SubsetManager.getInstance();
	
	private ApiExtensionController() {		
	}
	
//	/**
//	 * Initialize this controller for the given <code>Database</code>. This 
//	 * method checks if the used storage dimension exists and if not they will
//	 * be added inclusive any required elements.
//	 * 
//	 * @param database
//	 */
//	final void init(Database database) {
//		subsetManager.init(database);
//	}
	/**
	 * Creates a new cube view instance
	 * @param id
	 * @param name
	 * @param srcCube
	 * @param properties 
	 * @return
	 */
	final CubeView createCubeView(String id, String name, Cube srcCube, Property [] properties) {
		Object [] params;
		if (properties == null) {
			params = new Object [] {id, name, srcCube};
		} else {
			params = new Object[3 + properties.length];
			params[0] = id;
			params[1] = name;
			params[2] = srcCube;
			for (int i = 0; i < properties.length; i++) {
				params[i + 3] = properties[i];
			}
		}
		return (CubeView) viewManager.create(CubeView.class, params);
	}

//	/**
//	 * Create a new subset instance
//	 * @param id
//	 * @param name
//	 * @param srcDimension
//	 * @return
//	 */
//	final Subset createSubset(String id, String name, Dimension srcDimension) {
//		return (Subset) subsetManager.create(
//				Subset.class, 
//				new Object[] { id, name, srcDimension });
//	}
//	
//	final Subset2 createSubset2(String name, Dimension srcDimension) {		
//		return (Subset2) subsetManager.create(Subset2.class, new Object[] {
//				name, srcDimension });
//	}
	
    final CubeView loadView(Database database, String id) throws PaloPersistenceException {
    	return (CubeView)viewManager.load(database,id);
    }
    
    /**
     * Loads all cube views from the provided database and fills the given maps
     * @param database
     * @param cubeId2viewId
     * @param views
     * @throws PaloPersistenceException
     */
    final void loadViews(Database database, Map cubeId2viewId, Map views) throws PaloPersistenceException {
    	viewManager.load(database, cubeId2viewId, views);
    }
    
    
//    /**
//     * Loads all subsets from the given database and fills the given maps
//     * @param database
//     * @param dimId2viewId
//     * @param subsets
//     * @throws PaloPersistenceException
//     */
//    final void loadSubsets(Database database, Map dimId2viewId, Map subsets) throws PaloPersistenceException {
//    	subsetManager.load(database, dimId2viewId, subsets);
//    }
//    
//    final Subset loadSubset(Database database, String id) throws PaloPersistenceException {
//    	return (Subset)subsetManager.load(database,id);
//    }
//    
//    final String[] getAllSubsets(Dimension dimension) {
//    	return ((SubsetManager)subsetManager).getAllSubsets(dimension);
//    }
    
    /**
     * Deletes the given cube view instance
     * @param view
     */
    final void delete(CubeView view) {
    	viewManager.delete(view);
    }
    
//    /**
//     * Deletes the given subset
//     * @param subset
//     */
//    final void delete(Subset subset) {
//    	subsetManager.delete(subset);
//    }
//    
//    final void hasSubsets(Database database) {
//    	subsetManager.hasSubsets(database);
//    }
}
