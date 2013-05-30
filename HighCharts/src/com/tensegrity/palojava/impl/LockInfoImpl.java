/*
 * (c) Tensegrity Software 2008
 * All rights reserved
 */
package com.tensegrity.palojava.impl;

import com.tensegrity.palojava.LockInfo;

/**
 * <code>LockInfo</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: LockInfoImpl.java,v 1.1 2008/03/04 08:52:16 ArndHouben Exp $
 **/
public class LockInfoImpl implements LockInfo {

	private final String id;	
	private final String user;
	
	private int steps;
	private String[][] area;
	
	public LockInfoImpl(String id, String user) {
		this.id = id;
		this.user = user;		
	}
	
	public final String getId() {
		return id;
	}
	
	public final String getUser() {
		return user;
	}
	
	public final void setSteps(int steps) {
		this.steps = steps;
	}

	public final int getSteps() {
		return steps;
	}

	public final void setArea(String[][] area) {
		this.area = area;
	}
	
	public final String[][] getArea() {
		return area; 
	}
	
	public final int hashCode() {
		int hc = 17;
		hc += 23 * id.hashCode();
		hc += 23 * user.hashCode();
		return hc;
	}
}
