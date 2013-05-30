/*
 * (c) 2007 Tensegrity Software GmbH
 * All rights reserved
 */
package com.tensegrity.palojava.impl;

import com.tensegrity.palojava.CellInfo;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author ArndHouben
 * @version $Id: CellInfoImpl.java,v 1.9 2008/02/22 09:58:48 ArndHouben Exp $
 */
public class CellInfoImpl implements CellInfo {

	private final int type;
	private final boolean exists;
	private final Object value;
	private String[] coordinate;
	private String rule;
	
	public CellInfoImpl(int type, boolean exists, Object value,
			String[] coordinate) {
		this.exists = exists;
		this.value = value;
		this.type = type;
		this.coordinate = coordinate != null ? coordinate.clone() : null;

//		if (value != null && value.toString().equals("0.0")) {
//			this.value = new String("");
//			this.type = TYPE_STRING;
//		} else {
//			this.value = value;
//			this.type = type;
//		}
//		this.coordinate = coordinate != null ? coordinate.clone() : null;
	}
	
	public final boolean exists() {
		return exists;
	}

	public final int getType() {
		return type;
	}

	public final Object getValue() {
		return value;
	}
	
	public final String getId() {
		return null;
	}
	
	public final String[] getCoordinate() {
		return coordinate;
	}
	
	public final void setCoordinate(String[] coord) {
		coordinate = coord != null ? coord.clone() : null;
	}

	public final String toString() {
		return value.toString();
	}

	public final boolean canBeModified() {
		return true;
	}

	public final boolean canCreateChildren() {
		return false;
	}

	public final String getRule() {
		return rule;
	}
	
	public final void setRule(String id) {
		this.rule = id;
	}
}
