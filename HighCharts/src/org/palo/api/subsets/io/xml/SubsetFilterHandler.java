/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.subsets.io.xml;

import org.palo.api.Dimension;
import org.palo.api.subsets.SubsetFilter;

/**
 * <code>SubsetFilterHandler</code>
 * <p><b>- API  INTERNAL -</b></p>
 * This interface describes an xml handler for reading <code>SubsetFilter</code>s
 * from xml.
 * 
 * @author ArndHouben
 * @version $Id: SubsetFilterHandler.java,v 1.4 2008/05/08 10:12:20 ArndHouben Exp $
 * @see {@link SubsetXMLHandler}
 **/
public interface SubsetFilterHandler {

	/**
	 * Returns the xml path for this subset filter
	 * @return the xpath
	 */
	public String getXPath();
	/**
	 * Called when given xml path is entered
	 * @param path xml path
	 */
	public void enter(String path);
	
	/**
	 * Called when given xml path is leaved
	 * @param path xml path
	 * @param value the value of this path
	 */
	public void leave(String path, String value);
		
	/**
	 * Creates a new <code>SubsetFilter</code> for the given dimension
	 * @param dimension the dimension to create the filter for
	 * @return the newly created subset filter
	 */
	public SubsetFilter createFilter(Dimension dimension);
	
	/**
	 * Specifies the subset version to use
	 * @param version the subset version
	 */
	public void setSubsetVersion(String version);
}
