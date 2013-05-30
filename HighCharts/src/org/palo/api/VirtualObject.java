/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api;

/**
 * A <code>VirtualObject</code> classifies all palo object which are virtual, 
 * i.e. their creation based on virtual definitions like 
 * <code>{@link VirtualCubeDefinition}</code> or 
 * <code>{@link VirtualDimensionDefinition}</code>.
 * 
 * @author ArndHouben
 * @version $Id: VirtualObject.java,v 1.1 2007/07/02 10:55:06 ArndHouben Exp $
 * @deprecated Please do not use!! This class is for internal usage only!
 **/
public interface VirtualObject {

	/**
	 * Returns the definition this palo object is based on, e.g. a virtual
	 * dimension will return a <code>{@link VirtualDimensionDefinition}</code>.
	 * If no definition exists <code>null</code> is returned
	 * @return a virtual definition or <code>null</code>
	 */
	Object getVirtualDefinition();
}
