/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.subsets.states;

import org.palo.api.impl.subsets.AbstractSubsetState;


/**
 * The <code>RegExState</code> determines the visible elements by a regular
 * expression.
 * 
 * @author ArndHouben
 * @version $Id: RegExState.java,v 1.3 2007/04/17 08:20:17 ArndHouben Exp $
 */
public class RegExState  extends AbstractSubsetState {

	public static final String ID = "regularexpression"; //$NON-NLS-1$

	public RegExState(String initialRegex) {
		super(ID);
		setExpression(initialRegex);
	}

}
