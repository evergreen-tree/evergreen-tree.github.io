package com.tensegrity.palojava;

/**
 * Describes if an object can be modified and if it can add new children to
 * itself. All objects belonging to a palo database can be modified and
 * extended, whereas XMLA objects cannot.
 * 
 * @author PhilippBouillon
 * @deprecated subject to change, please do not use.
 */
public interface PaloInfoWritable {
	boolean canCreateChildren();
	boolean canBeModified();
}
