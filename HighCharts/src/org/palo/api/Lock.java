/*
 * (c) Tensegrity Software 2008
 * All rights reserved
 */
package org.palo.api;

/**
 * <code>Lock</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: Lock.java,v 1.1 2008/03/04 08:58:18 ArndHouben Exp $
 * @deprecated PLEASE DON'T USE! SUBJECT TO CHANGE
 **/
public interface Lock {

	public String getId();
	public Cell[] getArea(); 
	public int getSteps();
}
