/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava;

/**
 * <code>ServerInfo</code> provides information about the used palo server  
 * 
 * @author ArndHouben
 * @version $Id: ServerInfo.java,v 1.2 2008/02/06 15:09:48 PhilippBouillon Exp $
 */
public interface ServerInfo extends PaloInfo {
    int ENCRYPTION_NONE = 0;
    int ENCRYPTION_OPTIONAL = 1;
    int ENCRYPTION_REQUIRED = 2;
	
	/**
	 * Returns the major number of the server
	 * @return major number
	 */
	int getMajor();
	/**
	 * Returns the minor number of the server
	 * @return minor number
	 */
	int getMinor();
	/**
	 * Returns the bugfix level of current server version
	 * @return bugfix level
	 */
	int getBugfixVersion();
	/**
	 * Returns the build number of the server
	 * @return build number
	 */
	int getBuildNumber();
	/**
	 * Returns <code>true</code> if the used palo server is the legacy server,
	 * i.e. prior to version 1.5, <code>false</code> otherwise.
	 * @return <code>true</code> is used server is legacy, <code>false</code>
	 * otherwise
	 */
	boolean isLegacy();
	
	/**
	 * Returns the https port of this server or 0 if https is not supported.
	 * 
	 * @return the https port of this server or 0 if https is not supported.
	 * @deprecated currently for internal use only. Do not use.
	 */
	int getHttpsPort();
	
	/**
	 * Returns an integer value corresponding to the encryption state of this
	 * server.
	 * 
	 * @return an integer matching one of the constants defined in ServerInfo,
	 * describing the encryption mechanism of this server.
	 * @deprecated currently for internal use only. Do not use.
	 */
	int getEncryption();
	
	String getName();
	String getServerType();
	String getVersion();
	String [] getProperties();
}
