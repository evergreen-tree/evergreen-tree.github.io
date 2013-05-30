/**
 * (c) Copyright 2006 Tensegrity Software
 * All rights reserved.
 */
package com.tensegrity.palojava.events;

/**
 * <p>
 * This listener gets notified whenever structural changes within the palo 
 * server are detected. Structural changes are e.g the deletion or creation
 * of cubes, dimensions, databases or elements. However, the listeners does
 * not get notified on cell value changes.
 * </p>
 *
 * @author ArndHouben
 * @version $Id: ServerListener.java,v 1.1 2007/04/11 16:45:38 ArndHouben Exp $
 */
public interface ServerListener {

	/**
	 * Called whenever the header detects that something within the structure 
	 * of the server changed, e.g. a dimension was added or deleted 
	 * @param event
	 */
	public void serverStructureChanged(ServerEvent event);
}

