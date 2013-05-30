/*
 * (c) 2007 Tensegrity Software GmbH
 */
package com.tensegrity.palojava;

/**
 * The <code>PaloServer</code> interface defines general methods to connect with
 * and login into a certain palo server.
 * 
 * @author ArndHouben
 * @version $Id: PaloServer.java,v 1.4 2008/03/26 10:15:44 ArndHouben Exp $
 */
public interface PaloServer {

	/** 
	 * constant for server type legacy
	 * @deprecated legacy server is not supported anymore!
	 */
	public static final int TYPE_LEGACY = 1;
	/** constant for server type http */
	public static final int TYPE_HTTP = 2;
	/** constant for server type XMLA */
	public static final int TYPE_XMLA = 3;
	
	/**
	 * Returns the {@link ServerInfo} object to gather further information about 
	 * this palo server
	 * @return
	 */
	public ServerInfo getInfo();
	
	/**
	 * Connect to this palo server.
	 * @return {@link DbConnection} if connection was successful
	 */
	public DbConnection connect();
	
    /**
     * Disconnects from the palo server
     * @throws PaloException if an communication exception occurs
     */
    public void disconnect();

    /**
     * Tests if the palo server is still reachable
     * @throws PaloException if palo server is not reachable anymore 
     */
    public void ping() throws PaloException;

//    /**
//     * Try to login to palo server with given name and password
//     * @param username login name
//     * @param password login password
//     * @return <code>true</code> if login was successful, <code>false</code>
//     * otherwise
//     */
//    public boolean login(String username, String password);
}
