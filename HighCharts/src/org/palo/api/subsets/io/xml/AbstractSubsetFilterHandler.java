/*
 * (c) Tensegrity Software 2008
 * All rights reserved
 */
package org.palo.api.subsets.io.xml;


/**
 * <code>AbstractSubsetFilterHandler</code>
 * An abstract implementation of the {@link SubsetFilterHandler} interface.
 * This class just defines a variable for storing the current subset version to
 * use.
 *
 * @author ArndHouben
 * @version $Id: AbstractSubsetFilterHandler.java,v 1.1 2008/05/08 10:12:20 ArndHouben Exp $
 **/
abstract class AbstractSubsetFilterHandler implements SubsetFilterHandler {

	protected String subsetVersion;
	
	public void setSubsetVersion(String version) {
		subsetVersion = version;
	}
	
}
