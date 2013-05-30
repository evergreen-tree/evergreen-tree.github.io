/*
 * (c) Tensegrity Software 2006. All rights reserved.
 */
package org.palo.api;

import org.palo.api.subsets.Subset2;

/**
 * <p>
 * <code>Subset<code>s define a sort of {@link Element} filter for a 
 * {@link Dimension}, i.e. they determine those elements which should be
 * shown within a gui.
 * </p>
 * <p>
 * So each subset has a corresponding source dimension. Furthermore it is
 * possible to add several {@link SubsetState}s to store and restore more
 * than one state.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: Subset.java,v 1.8 2008/04/15 08:20:57 PhilippBouillon Exp $
 * @deprecated this subset definition is no longer supported. 
 * Please use {@link Subset2} instead
 */
public interface Subset extends NamedEntity {

	/**
	 * The unique subset id.
	 * @return the subset id
	 */
	public String getId();
	
	/**
	 * Sets the subset name
	 * @param name the subset name
	 */
	public void setName(String name);
	
	/**
	 * Returns the name of the subset
	 * @return the subset name
	 */
	public String getName();
	
	/**
	 * Sets an optional description for the subset.
	 * @param description the subset description
	 */
	public void setDescription(String description);	
	/**
	 * Returns the subset description or null if no description was set.
	 * @return the subset description or null
	 */	
	public String getDescription();
	
	/**
	 * Sets an optional alias for element name display of the subset.
	 * @return attribute to be used for the alias
	 */
	public Attribute getAlias();

	/**
	 * Gets the alias for element name display of the subset.
	 * @param alias the attribute for the current alias.
	 */
	public void setAlias(Attribute alias);
	
	/**
	 * Returns the corresponding <code>Dimension</code> instance
	 * @return the dimension to which this subset applies
	 * @deprecated use {@link Subset#getHierarchy} instead.
	 */
	public Dimension getDimension();
	
	/**
	 * Returns the corresponding <code>Hierarchy</code> instance
	 * @return the hierarchy to which this subset applies
	 */
	public Hierarchy getHierarchy();
	
	/**
	 * Adds the given {@link SubsetState} to the list of all used states
	 * @param state a subset state 
	 */
	public void addState(SubsetState state);
	
	/**
	 * Removes the given {@link SubsetState} from the list of all used states
	 * @param state a subset state
	 */
	public void removeState(SubsetState state);
	
	/**
	 * Returns all available {@link SubsetState}s for this subset.
	 * @return an array of <code>SubsetState</code>s
	 */
	public SubsetState[] getStates();
	
	/**
	 * Note returns null if no SubsetState with given id was added before!
	 * @param stateId a valid state id
	 * @return the <code>SubsetState</code> with given id or null if no such
	 * state exists
	 */
	public SubsetState getState(String stateId);
	
	/**
	 * Returns the last used {@link SubsetState}
	 * @return the active <code>SubsetState</code>
	 */
	public SubsetState getActiveState();
	
	/**
	 * Sets the last used state. Note that the given state is also added to
	 * the list of all states if it was not present before...
	 * @param activeState the used <code>SubsetState</code>
	 */
	public void setActiveState(SubsetState activeState);
	
	/**
	 * Saves the current subset state
	 */
	public void save();
	
	
//	/**
//	 * Returns the raw definition of this subset, i.e. internally each subset
//	 * is serialized as xml code. This method provides access to this internal
//	 * xml representation.
//	 * 
//	 * @return the internal used xml representation
//	 */
//	public String getRawDefinition();
	
}