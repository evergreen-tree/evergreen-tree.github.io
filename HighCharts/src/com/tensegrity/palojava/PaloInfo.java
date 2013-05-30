/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava;

/**
 * <code>PaloInfo</code> 
 * <p>
 * A general description of a palo object. Each palo object consists of an 
 * identifier and is of a certain type. All subclasses define constants which 
 * specify valid types.
 * </p> 
 * <p>
 * The id is only unique within the scope of the parent of this palo object, 
 * e.g. an element id is only unique within its dimension but not database wide.
 * Furthermore note that an id could be reused that is, in case of a deletion
 * of a palo object its corresponding id could later be assigned to a newly 
 * created one!
 * </p>
 * <p><i>A remark:</i>
 * There are two main reasons for the existence of <code>PaloInfo</code> objects.
 * The first one is to keep the <code>com.tensegrity.palojava</code> and the 
 * <code>org.palo.api</code> packages independent. The second and more important 
 * reason is the legacy support for palo server 1.0. The info objects build up 
 * an abstraction layer which fits to the new palo server but can be implemented
 * more or less by the legacy server too. (Not all information provided by the
 * various info objects are available with legacy server) However, if support 
 * for legacy is officially stopped this layer can be removed and hence, there 
 * is no need for the info objects anymore.
 * </p> 
 * @author ArndHouben
 * @version $Id: PaloInfo.java,v 1.3 2007/11/30 08:39:39 PhilippBouillon Exp $
 */
public interface PaloInfo extends PaloInfoWritable {

	/**
	 * Returns the type of this info object. Use the defined constants within 
	 * each specialized subclass for type interpretation. 
	 * @return type of info object
	 */
	public int getType();
	
	/**
	 * Returns the unique identifier of this info object. 
	 * <p><b>Important note:</b> for a correct usage note that the returned id 
	 * is only unique within the parent scope of this palo object. Furthermore
	 * in case of deletion the id is reused, i.e. if a palo object is removed 
	 * its id could be given to a newly created palo object afterwards!!
	 * </p>
	 * @return id of the info object.
	 */
	public String getId();
}
