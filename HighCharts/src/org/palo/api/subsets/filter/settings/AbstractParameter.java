/*
 * (c) Tensegrity Software 2008
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

import org.palo.api.subsets.Subset2;

/**
 * <code>AbstractParameter</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: AbstractParameter.java,v 1.1 2008/03/06 18:11:58 ArndHouben Exp $
 **/
abstract class AbstractParameter implements Parameter {

	private Subset2 subset;
	
	public final void bind(Subset2 subset) {
		this.subset = subset;
		markDirty();
	}

	public final void unbind() {
		subset = null;
	}

	protected final void markDirty() {
		if(subset != null)
			subset.modified();
	}
}
