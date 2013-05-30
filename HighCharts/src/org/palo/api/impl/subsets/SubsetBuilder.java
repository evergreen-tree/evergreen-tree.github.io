/*
 * (c) Tensegrity Software 2006. All rights reserved.
 */
package org.palo.api.impl.subsets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.palo.api.Attribute;
import org.palo.api.Dimension;
import org.palo.api.Hierarchy;
import org.palo.api.PaloAPIException;
import org.palo.api.Subset;
import org.palo.api.SubsetState;

/**
 * An internally used builder to create {@link Subset}s. The main usage of this
 * builder is during the restore of persistent subsets. Therefore it provides
 * a setter method for each subset field.
 * 
 * @author ArndHouben
 * @version $Id: SubsetBuilder.java,v 1.7 2008/04/15 08:20:57 PhilippBouillon Exp $
 */
class SubsetBuilder {
	
	//required fields:
	private String id;	
	private String name;
	private Attribute alias;
	private String activeStateId;
	private Hierarchy srcHierarchy;
	private Set states = new HashSet();
	//optional fields
	private String description; 
	
	
	final void setId(String id) {
		this.id = id;
	}
	
	final void setName(String name) {
		this.name = name;
	}
	
	final void setDescription(String description) {
		this.description = description;
	}
	
	final void setActiveState(String  activeStateId) {
		this.activeStateId = activeStateId;
	}
	
	final void setSourceHierarchy(Hierarchy srcHierarchy) {
		this.srcHierarchy = srcHierarchy;
	}
	
	final void setAlias(Attribute alias)
	{
		this.alias = alias;
	}
	
	final Hierarchy getSourceHierarchy() {
		return srcHierarchy;
	}
	
	final void addState(SubsetState state) {		
		states.add(state);
	}
	
	final Subset createSubset() {
		if (id == null || name == null || activeStateId == null
				|| srcHierarchy == null)
			throw new PaloAPIException(
					"Cannot create subset, insufficient information");
		// create subset:
		Subset subset = new SubsetImpl(id, name, srcHierarchy);
//		Object[] params = new Object[] { id, name, srcDimension };
//		Subset subset = (Subset) SubsetPersistence.getInstance().create(
//				Subset.class, params);
		// reset subset to perform an update:
		((SubsetImpl) subset).reset();

		// add da states:
		for (Iterator it = states.iterator(); it.hasNext();)
			subset.addState((SubsetState) it.next());
		((SubsetImpl) subset).setActiveState(activeStateId);
		if (description != null)
			subset.setDescription(description);
		if (alias != null)
			subset.setAlias(alias);
		return subset;
	}

}
