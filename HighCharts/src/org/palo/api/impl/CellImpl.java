/*
 * (c) Tensegrity Software 2007. All rights reserved.
 */
package org.palo.api.impl;

import org.palo.api.Cell;
import org.palo.api.Cube;
import org.palo.api.Element;

import com.tensegrity.palojava.CellInfo;

/**
 * {@<describe>}
 * <p>
 * <code>Cell</code> interface implementation
 * </p>
 * {@</describe>}
 *
 * @author ArndHouben
 * @version $Id: CellImpl.java,v 1.9 2008/04/15 08:20:57 PhilippBouillon Exp $
 */
class CellImpl implements Cell {

	private final Cube cube;	
	private final CellInfo cInfo;
	private CompoundKey coordKey;
	private Element[] coordinate;	
	
	CellImpl(Cube cube, CellInfo cInfo) {
		this.cube = cube;
		this.cInfo = cInfo;
		String[] ids = cInfo.getCoordinate();
		// set coordinate:
		coordinate = new Element[ids.length];
		// TODO figure out a way to get the active hierarchy...
		for (int i = 0; i < ids.length; ++i) {
			coordinate[i] = cube.getDimensionAt(i).getDefaultHierarchy().getElementById(ids[i]);
		}
		coordKey = new CompoundKey(ids);
	}
	
	CellImpl(Cube cube, CellInfo cInfo, Element[] coordinate) {
		this(cube, cInfo);
		this.coordinate = coordinate.clone();
		String[] ids = new String[coordinate.length];
		for(int i=0; i<coordinate.length; ++i)
			ids[i] = coordinate[i].getId();
		coordKey = new CompoundKey(ids);
	}
	
	public final Cube getCube() {
		return cube;
	}
	
	public final Element[] getCoordinate() {
		return coordinate;
	}
	
	public final Element[] getPath() {
		return coordinate;
	}

	public String getRuleId() {
		return cInfo.getRule();
	}

	public boolean hasRule() {
		return cInfo.getRule()!=null;
	}

	public final int getType() {
		return cInfo.getType();
	}

	public final Object getValue() {
		return cInfo.getValue();
	}

//FOLLOWING METHODS WILL MAKE CELL REPRESENTATION STALE SINCE ITS VALUE AND	
//SERVER VALUE DOESN'T MATCH AFTERWARDS....
//	public final void setValue(Object value) {
//		cube.setData(coordinate, value);
//	}
//	
//	public final void setValue(Object value, int splashMode) {
//		cube.setDataSplashed(coordinate, value, splashMode);
//	}
//	
//	public final void clear() {
//		Element[][] area = new Element[coordinate.length][];
//		for (int i = 0; i < area.length; ++i) 
//			area[i] = new Element[] { coordinate[i] };
//		cube.clear(area);
//	}
	
	public final boolean isConsolidated() {
		boolean consolidated = false;
		for (Element element : coordinate) {
			switch (element.getType()) {
			case Element.ELEMENTTYPE_CONSOLIDATED:
				consolidated = true;
				break;
			case Element.ELEMENTTYPE_RULE:
			case Element.ELEMENTTYPE_STRING:
				return false;
			}
		}
		return true && consolidated;
	}
	
	public final boolean equals(Object obj) {
		if(obj instanceof Cell) {
			CellImpl other = (CellImpl)obj;
			return cube.equals(other.cube) && coordKey.equals(other.coordKey);
		}
		return false;
	}
	public final int hashCode() {
		int hc = 23;
		hc += 37 * cube.hashCode();
		hc += 37 + coordKey.hashCode();
		return hc;
	}

	// --------------------------------------------------------------------------
	// PACKAGE INTERNAL
	//
	final CellInfo getInfo() {
		return cInfo;
	}

}
