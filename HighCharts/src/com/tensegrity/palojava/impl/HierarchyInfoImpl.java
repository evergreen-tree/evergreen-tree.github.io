package com.tensegrity.palojava.impl;

import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.HierarchyInfo;

public class HierarchyInfoImpl implements HierarchyInfo {
	private DimensionInfo dimension;
	
	public HierarchyInfoImpl(DimensionInfo dimensionInfo) {
		this.dimension = dimensionInfo;		
	}
	
	public int getDimensionCount() {
		return 1;
	}

	public String getName() {
		return dimension.getName();
	}

	public String getId() {
		return dimension.getId();
	}

	public int getType() {
		return dimension.getType();
	}
	
	public void rename(String name) {
	}

	public boolean canBeModified() {
		return true;
	}

	public boolean canCreateChildren() {
		return true;
	}

	public DimensionInfo getDimension() {
		return dimension;
	}

	public int getElementCount() {
		return dimension.getElementCount();
	}

	public int getMaxDepth() {
		return dimension.getMaxDepth();
	}

	public int getMaxLevel() {
		return dimension.getMaxLevel();
	}
}
