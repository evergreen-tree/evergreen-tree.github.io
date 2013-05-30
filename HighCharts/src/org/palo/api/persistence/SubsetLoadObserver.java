/*
 * (c) Tensegrity Software 2008
 * All rights reserved
 */
package org.palo.api.persistence;

import org.palo.api.Hierarchy;
import org.palo.api.subsets.Subset2;

/**
 * <code>SubsetLoadObserver</code>
 * TODO DOCUMENT ME
 * @deprecated NOT YET OFFICIAL - SUBJECT TO CHANGE
 *
 * @author ArndHouben
 * @version $Id: SubsetLoadObserver.java,v 1.1 2008/04/24 14:29:14 ArndHouben Exp $
 **/
public interface SubsetLoadObserver {

	public void loadComplete(Subset2 subset);
	public void loadFailed(String subId, String subName, int subType, Hierarchy hierarchy);
	
}
