/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.subsets.impl;

import org.palo.api.Dimension;
import org.palo.api.DimensionFilter;
import org.palo.api.Hierarchy;
import org.palo.api.HierarchyFilter;
import org.palo.api.ext.subsets.SubsetHandler;
import org.palo.api.ext.subsets.SubsetStateHandler;
import org.palo.api.ext.subsets.states.FlatState;
import org.palo.api.ext.subsets.states.HierarchicalState;
import org.palo.api.ext.subsets.states.RegExState;

/**
 * The <code>SubsetStateHandlerFactory</code> creates the default 
 * <code>SubsetStateHandler</code>s as well as the default 
 * <code>SubsetHandler</code>. 
 * 
 * 
 * @author ArndHouben
 * @version $Id: SubsetStateHandlerFactory.java,v 1.5 2008/04/15 08:20:57 PhilippBouillon Exp $
 */
public class SubsetStateHandlerFactory {

	//--------------------------------------------------------------------------
	// FACTORY
	//
	private static final SubsetStateHandlerFactory instance = new SubsetStateHandlerFactory();
	public static final SubsetStateHandlerFactory getInstance() {
		return instance;
	}
	
	//--------------------------------------------------------------------------
	// INSTANCE
	//
	private final DefaultSubsetHandler handler = new DefaultSubsetHandler();
	private SubsetStateHandlerFactory() {
	}
	
	public final SubsetStateHandler create(String handlerID) {
		if(handlerID.equals(FlatState.ID))
			return new FlatStateHandler();
		else if(handlerID.equals(HierarchicalState.ID))
			return new HierarchicalStateHandler();
		else if(handlerID.equals(RegExState.ID))
			return new RegExStateHandler();
		return null;
	}

	public final SubsetHandler create(SubsetStateHandler stateHandler) {
		Hierarchy hier = stateHandler.getSubset().getHierarchy();		
		if(hier == null)
			return null;
		HierarchyFilter filter = stateHandler.createHierarchyFilter(hier);
		if(filter == null)
			return null;
		
		handler.use(hier, filter,stateHandler.getSubsetState());
		return handler;
	}
}
