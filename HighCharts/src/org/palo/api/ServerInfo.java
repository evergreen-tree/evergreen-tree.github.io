package org.palo.api;

/**
 * <code>ServerInfo</code>
 * This object provides information about the currently used server. Please
 * note that not all server provide all information. If this is the case 
 * <code>null</code> is returned.
 *
 * @author ArndHouben
 * @version $Id: ServerInfo.java,v 1.2 2008/02/28 17:59:02 ArndHouben Exp $
 **/
public interface ServerInfo {
	
	//predefined properties:
	String SECURITY_INFO_PROPERTY = "SecurityInfoProperty";
	String BUILD_NUMBER_PROPERTY = "BuildNumberProperty";
	String MINOR_VERSION_PROPERTY = "MinorVersionProperty";
	String MAJOR_VERSION_PROPERTY = "MajorVersionProperty";
	String DESCRIPTION_PROPERTY = "DescriptionProperty";
	
	/**
	 * Returns the server name or <code>null</code> if server does
	 * not provide it
	 * @return server name or <code>null</code>
	 */
	String getName();
	/**
	 * Returns the server version name or <code>null</code> if server does
	 * not provide it
	 * @return server version or <code>null</code>
	 */
	String getVersion();
	/**
	 * Returns the server type or <code>null</code> if server does
	 * not provide it. 
	 * @return server type or <code>null</code>
	 */
	String getType();

	/**
	 * Returns all property ids known to the current used server.
	 * @return all property ids known to the current used server
	 */
	String [] getPropertyIds();	
	/**
	 * Returns the value for the property which is specified by the given id.
	 * If the server does not know this property <code>null</code> is returned.
	 * @param id the property identifier
	 * @return the property value or <code>null</code> if no such property was
	 * defined
	 */
	String getProperty(String id);
}
