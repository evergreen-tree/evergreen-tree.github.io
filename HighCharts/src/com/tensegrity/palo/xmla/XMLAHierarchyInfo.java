package com.tensegrity.palo.xmla;

import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.HierarchyInfo;
import com.tensegrity.palojava.PaloConstants;

public class XMLAHierarchyInfo implements HierarchyInfo {
	private String name;
	private String id;
	private final DimensionInfo dimension;
	private int cardinality;
	
	public XMLAHierarchyInfo(DimensionInfo dim, String name, String uniqueName) {
		dimension = dim;
		this.id = uniqueName;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}

	public int getType() {
		return PaloConstants.TYPE_NORMAL;
	}

	public void rename(String name) {		
	}

	public boolean canBeModified() {
		return false;
	}

	public boolean canCreateChildren() {
		return false;
	}

	public DimensionInfo getDimension() {		
		return dimension;
	}

	public int getElementCount() {
		return cardinality;
	}
	
	public void setCardinality(String card) {
		try {
			cardinality = Integer.parseInt(card);
		} catch (NumberFormatException e) {			
		}
	}

	public int getMaxDepth() {
		return dimension.getMaxDepth();
	}

	public int getMaxLevel() {
		return dimension.getMaxLevel();
	}
}
