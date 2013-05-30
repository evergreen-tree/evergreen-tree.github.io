/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.subsets.states;

import org.palo.api.impl.subsets.AbstractSubsetState;


/**
 * The <code>FlatState</code> interprets the visible elements as flat, i.e.
 * no hierarchy is defined. All elements are "root" elements.
 * 
 * @author ArndHouben
 * @version $Id: FlatState.java,v 1.4 2007/04/17 09:00:03 ArndHouben Exp $
 */
public class FlatState extends AbstractSubsetState {

	public static final String ID = "flat"; //$NON-NLS-1$
	
	public FlatState() {
		super(ID);
		setExpression("");
	}
}
