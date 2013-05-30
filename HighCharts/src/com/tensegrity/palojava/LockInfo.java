/*
 * (c) Tensegrity Software 2008
 * All rights reserved
 */
package com.tensegrity.palojava;

/**
 * <code>LockInfo</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: LockInfo.java,v 1.1 2008/03/04 08:52:34 ArndHouben Exp $
 **/
public interface LockInfo {

	public String getId();
	public String getUser();
	public String[][] getArea();
	public int getSteps();
}
