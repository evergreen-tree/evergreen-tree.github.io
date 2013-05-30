package org.palo.api;

/**
 * Describes if an object can be modified and if it can add new children to
 * itself. All objects belonging to a palo database can be modified and
 * extended, whereas XMLA objects cannot.
 * 
 * @author PhilippBouillon
 * @deprecated subject to change, please do not use.
 */
public interface Writable {
	/**
	 * Returns true if this object can create child objects, false otherwise.
	 * @return true if this object can create child objects, false otherwise.
	 */
	boolean canCreateChildren();
	
	/**
	 * Returns true if this object can be modified (renamed, deleted, ...),
	 * false otherwise.
	 * @return true if this object can be modified, false otherwise.
	 */
	boolean canBeModified();
}
