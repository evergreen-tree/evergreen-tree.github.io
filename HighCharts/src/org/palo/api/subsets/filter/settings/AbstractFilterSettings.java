/*
 * (c) Tensegrity Software 2008
 * All rights reserved
 */
package org.palo.api.subsets.filter.settings;

import org.palo.api.subsets.Subset2;




/**
 * <code>AbstractFilterSettings</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: AbstractFilterSettings.java,v 1.1 2008/03/06 18:11:58 ArndHouben Exp $
 **/
public abstract class AbstractFilterSettings implements FilterSetting {

	protected Subset2 subset;
	
	public void bind(Subset2 subset) {
		this.subset = subset;
		markDirty();
	}
	public void unbind() {
		subset = null;
	}

	protected final void markDirty() {
		if(subset != null)
			subset.modified();
	}

}
