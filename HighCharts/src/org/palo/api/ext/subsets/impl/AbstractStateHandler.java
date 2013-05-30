/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.subsets.impl;

import org.palo.api.Subset;
import org.palo.api.SubsetState;
import org.palo.api.ext.subsets.SubsetStateHandler;

/**
 * The <code>AbstractStateHandler</code> simply contains all fields and methods
 * which the various <code>SubsetStateHandler</code> implementations have in
 * common.
 * 
 * @author ArndHouben
 * @version $Id: AbstractStateHandler.java,v 1.3 2007/04/11 16:46:18 ArndHouben Exp $
 */
abstract class AbstractStateHandler implements SubsetStateHandler {

	protected Subset subset;
	protected SubsetState subsetState;
	
	public final synchronized Subset getSubset() {
		return subset;
	}

	public final synchronized SubsetState getSubsetState() {
		return subsetState;
	}

	public final synchronized void use(Subset subset, SubsetState subsetState) {
		this.subset = subset;
		this.subsetState = subsetState;
	}

}
