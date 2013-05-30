/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api;

/**
 * <code>ConnectionContext</code>
 * The context provides methods to access additional information about the
 * currently used connection.
 *
 * @author ArndHouben
 * @version $Id: ConnectionContext.java,v 1.4 2008/02/28 17:31:30 ArndHouben Exp $
 **/
public interface ConnectionContext {

	/** @deprecated Please don't use! For internal usage only */
	public Object getData(String key);
	/** @deprecated Please don't use! For internal usage only */
	public void setData(String key, Object data);

	
	/**
	 * @deprecated PLEASE DON'T USE! SUBJECT TO CHANGE
	 */
	public boolean doSupportSubset2();
	/**
	 * Returns the {@link Rights} instance which is associated with this 
	 * connection.
	 * @return the <code>Right</code> instance associated to this connection
	 */
	public Rights getRights();
	
	/**
     * Returns the {@link ServerInfo} object associated with this connection to 
     * get more information about the underlying server.
     * 
     * @return the <code>ServerInfo</code> object associated with this connection.
     */
    ServerInfo getServerInfo();
}
