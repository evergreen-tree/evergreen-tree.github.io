package com.tensegrity.palojava.impl;

import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.HierarchyInfo;

public class DimensionInfoImpl implements DimensionInfo {
	
	private final String id;
	private final int type;
	private final DatabaseInfo database;
	private String name;
	
	private int elCount;
	private int maxLevel;
	private int maxIndent;
	private int maxDepth;
	private String attrDimId;
	private String attrCubeId;
	private String rightsCubeId;
	private int token;
	private final HierarchyInfo defaultHierarchy;
	
	public DimensionInfoImpl(DatabaseInfo database, String id,
			int type) {
		this.id = id;
		this.type = type;
		this.database = database;
		this.defaultHierarchy = new HierarchyInfoImpl(this);
	}

	public final DatabaseInfo getDatabase() {
		return database;
	}
	
	public final String getId() {
		return id;
	}
	
	public final synchronized String getName() {
		return name;
	}

	public final int getType() {
		return type;
	}
	
	public final synchronized void setName(String name) {
		this.name = name;
	}

	public final String getAttributeCube() {
		return attrCubeId;
	}

	public final void setAttributeCube(String attrCubeId) {
		this.attrCubeId = attrCubeId;
	}

	public final synchronized String getAttributeDimension() {
		return attrDimId;
	}

	public final synchronized void setAttributeDimension(String attrDimId) {
		this.attrDimId = attrDimId;
	}

	public final synchronized int getElementCount() {
		return elCount;
	}

	public final synchronized void setElementCount(int elCount) {
		this.elCount = elCount;
	}

	public final synchronized int getMaxDepth() {
		return maxDepth;
	}

	public final synchronized void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public final synchronized int getMaxIndent() {
		return maxIndent;
	}

	public final synchronized void setMaxIndent(int maxIndent) {
		this.maxIndent = maxIndent;
	}

	public final synchronized int getMaxLevel() {
		return maxLevel;
	}

	public final synchronized void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public final synchronized String getRightsCube() {
		return rightsCubeId;
	}

	public final synchronized void setRightsCube(String rightsCubeId) {
		this.rightsCubeId = rightsCubeId;
	}

	public final synchronized int getToken() {
		return token;
	}

	public final synchronized void setToken(int token) {
		this.token = token;
	}

	public boolean canBeModified() {
		return true;
	}

	public boolean canCreateChildren() {
		return true;
	}

	public HierarchyInfo getActiveHierarchy() {		
		return defaultHierarchy;
	}

	public HierarchyInfo getDefaultHierarchy() {
		return defaultHierarchy;
	}
	
	public void setActiveHierarchy(HierarchyInfo hier) {
		// Nothing to be done for palo.
	}
	
	public HierarchyInfo[] getHierarchies() {
		return new HierarchyInfo [] {defaultHierarchy};
	}

	public int getHierarchyCount() {
		return 1;
	}
}
