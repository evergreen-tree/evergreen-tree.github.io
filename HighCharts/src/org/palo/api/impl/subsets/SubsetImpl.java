/*
 * (c) Tensegrity Software 2006. All rights reserved.
 */
package org.palo.api.impl.subsets;

import java.util.HashMap;

import org.palo.api.Attribute;
import org.palo.api.Database;
import org.palo.api.Dimension;
import org.palo.api.Hierarchy;
import org.palo.api.Subset;
import org.palo.api.SubsetState;
import org.palo.api.ext.subsets.states.RegExState;

/**
 * A default implementation of the {@link Subset} interface.
 * 
 * @author ArndHouben
 * @version $Id: SubsetImpl.java,v 1.6 2008/04/15 08:20:57 PhilippBouillon Exp $
 */
class SubsetImpl implements Subset {

	private final String id;
	private final Hierarchy srcHierarchy;

	private HashMap states = new HashMap();
	private String name;	
	private String description;
	private Attribute alias;
	private SubsetState activeState;
	

	/**
	 * Creates a new {@link Subset} instance.
	 * 
	 * @param id a unique subset id
	 * @param name the subset name
	 * @param srcDimension the corresponding source {@link Dimension}
	 */
	SubsetImpl(String id, String name, Hierarchy srcHierarchy) {
		this.id = id;
		this.name = name;
		this.srcHierarchy = srcHierarchy;
	}	
	
	void reset() {
		alias = null;
		activeState = null;
		states.clear();
	}
	
	public void addState(SubsetState state) {
		if(!states.containsKey(state.getId()))
			states.put(state.getId(),state);				
	}

	public void removeState(SubsetState state) {
		states.remove(state.getId());
	}
	
	public String getDescription() {
		return description == null ? "" : description;
	}

	public String getId() {
		return id;
	}

	public SubsetState getActiveState() {
		//at default we take regular expressions....
		if(activeState == null) 
			setActiveState(new RegExState(".*"));
		return activeState;
	}

	public SubsetState getState(String id) {
		return (SubsetState)states.get(id);
	}
	
	void setActiveState(String  stateId) {
		SubsetState state = (SubsetState)states.get(stateId);
		setActiveState(state);
	}
	
	public final void setActiveState(SubsetState activeState) {
		if(activeState == null) //ignore unknown states...
			return;
		this.activeState = activeState;
		//exchange state in map:
		states.put(activeState.getId(), activeState);
	}
	public final void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

//	public String getRawDefinition() {
//		return null;
//	}

	public Dimension getDimension() {
		return srcHierarchy.getDimension();
	}
	
	public Hierarchy getHierarchy() {
		return srcHierarchy;
	}

	public SubsetState[] getStates() {
		return (SubsetState[]) states.values().toArray(
				new SubsetState[states.size()]);
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void save() {
		SubsetPersistence.getInstance().save(this);
	}
	
	public void delete() {
		srcHierarchy.removeSubset(this);
	}


	public Attribute getAlias() {
		return alias;
	}

	public void setAlias(Attribute alias) {
		this.alias = alias;
	}

	
    public boolean equals(Object obj) {
		if (!(obj instanceof Subset))
			return false;
		
		Subset other = (Subset) obj;
		if(id.equals(other.getId())) {
			//check parent dimension and database :
			Dimension dim = getDimension();
			Dimension otherDim = other.getDimension();
			String dimName = dim.getName();
			String otherDimName = otherDim.getName();
			String dbName = dim.getDatabase().getName();
			String otherDbName = otherDim.getDatabase().getName();
			return dimName.equals(otherDimName) && dbName.equals(otherDbName);			
		}
		return false;
	}
    
    public int hashCode() {
		int hc = 17;
		Dimension dim = getDimension();
		Database db = dim.getDatabase();
		hc += 31 * db.getName().hashCode();
		hc += 31 * dim.getName().hashCode();
		hc += 31 * id.hashCode();
		return hc;
	}

}