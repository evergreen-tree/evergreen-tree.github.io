/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.subsets.states;

import org.palo.api.impl.subsets.AbstractSubsetState;


/**
 * The <code>HierarchicalState</code> interprets the visible elements as a 
 * hierarchy.
 * 
 * @author ArndHouben
 * @version $Id: HierarchicalState.java,v 1.4 2007/04/17 09:00:03 ArndHouben Exp $
 */
public class HierarchicalState extends AbstractSubsetState {

	public static final String ID = "hierarchical"; //$NON-NLS-1$
	
	public HierarchicalState() {
		super(ID);
		setExpression("");
	}

}
