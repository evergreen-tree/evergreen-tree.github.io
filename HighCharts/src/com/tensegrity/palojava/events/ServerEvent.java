/**
 * (c) Copyright 2006 Tensegrity Software
 * All rights reserved.
 */
package com.tensegrity.palojava.events;

/**
 * {@<describe>}
 * <p>
 * TODO: DOCUMENT ME
 * </p>
 * {@</describe>}
 *
 * @author ArndHouben
 * @version $Id: ServerEvent.java,v 1.1 2007/04/11 16:45:38 ArndHouben Exp $
 */
public interface ServerEvent {

	//define the valid types:
	public static final int SERVER_CHANGED = 0;
	public static final int DATABASE_CHANGED = 1;
	public static final int DIMENSION_CHANGED = 2;
	public static final int CUBE_CHANGED = 4;
	public static final int SERVER_DOWN = 8;
	
	/**
	 * Returns one of the defined event types
	 * @return
	 */
	public int getType();
}

