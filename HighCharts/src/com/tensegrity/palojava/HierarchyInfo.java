package com.tensegrity.palojava;

public interface HierarchyInfo extends PaloInfo {
	DimensionInfo getDimension();
	String getName();
	int getElementCount();
	void rename(String name);
	int getMaxLevel();
	int getMaxDepth();
}
