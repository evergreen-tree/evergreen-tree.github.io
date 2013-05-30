/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava;

/**
 * The <code>ConnectionInfo</code> interface defines methods which provides
 * detailed information about a connection to palo server. 
 * 
 * @author ArndHouben
 * @version $Id: ConnectionInfo.java,v 1.2 2008/02/18 14:53:59 ArndHouben Exp $
 */
public interface ConnectionInfo {

	/**
	 * The name or ip adress of the host computer which runs the palo server 
	 * @return host name or ip
	 */
	public String getHost();
	
    /**
     * Returns the port number under which the palo server listens
     * @return port number
     */
	public String getPort();

	/**
	 * Returns the login name
	 * @return login name
	 */
	public String getUsername();
    
	/**
	 * Returns the login password
	 * @return login password
	 */
    public String getPassword();
    
    public void setData(String id, Object data);
    public Object getData(String id);

}
