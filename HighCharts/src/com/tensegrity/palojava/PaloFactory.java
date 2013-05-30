/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava;

import com.tensegrity.palojava.http.HttpPaloServer;

/**
 * The <code>PaloFactory</code> class is used to create a {@link PaloServer}
 * instance.
 * 
 * @author Arnd Houben
 * @version $Id: PaloFactory.java,v 1.3 2008/03/26 10:15:44 ArndHouben Exp $
 */
public class PaloFactory {

	//--------------------------------------------------------------------------
	// FACTORY
	//
	/** sole factory instance */
	private static final PaloFactory instance = new PaloFactory();
	/**
	 * Returns the sole factory instance
	 * @return 
	 */
	public static final PaloFactory getInstance() {
		return instance;
	}
	
	//--------------------------------------------------------------------------
	// INSTANCE
	//
	private PaloFactory() {		
	}
	
	/**
	 * Creates a {@link PaloServer} of the given type which is connected to
	 * a palo server on specified host at specified port
	 * @param host the host which runs the palo server
	 * @param port the port on which the palo server listens
	 * @param srvType the server type, either {@link PaloServer#TYPE_LEGACY} or
	 * {@link PaloServer#TYPE_HTTP}
	 * @return the {@link PaloServer} connection
	 */
	public final PaloServer createServerConnection(String host, String port, int srvType, int timeout) {
		switch(srvType) {
		case PaloServer.TYPE_LEGACY:
			throw new PaloException("Legacy server is not supported anymore!");
		case PaloServer.TYPE_HTTP:
			return  new HttpPaloServer(host,port,timeout);
		default: throw new PaloException("Unknown server type!");
		}
	}
}
