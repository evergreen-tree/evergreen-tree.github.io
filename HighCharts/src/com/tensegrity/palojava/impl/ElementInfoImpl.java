package com.tensegrity.palojava.impl;

import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.ElementInfo;


public class ElementInfoImpl implements ElementInfo {
	
	private final String id;	
	private final DimensionInfo dimension;
	
	private int type;
	private String name;
	private int position;
	private int level;
	private int indent;
	private int depth;
	private String[] parentIds;
	private String[] childrenIds;
	private double[] weights;
	
	public ElementInfoImpl(DimensionInfo dimension, String id) {
		this.id = id;
		this.dimension = dimension;
		this.parentIds = new String[0];
	}

	public final synchronized String[] getChildren() {
		return childrenIds;
	}

	public final synchronized int getChildrenCount() {
		return childrenIds.length;
	}

	public final synchronized int getDepth() {
		return depth;
	}

	public final DimensionInfo getDimension() {
		return dimension;
	}

	public final synchronized int getIndent() {
		return indent;
	}
	
	public final synchronized int getLevel() {
		return level;
	}

	public final synchronized String getName() {
		return name;
	}

	public final synchronized int getParentCount() {
		return parentIds.length;
	}

	public final synchronized String[] getParents() {
		return parentIds;
	}

	public final synchronized int getPosition() {
		return position;
	}

	public final synchronized double[] getWeights() {
		return weights;
	}

	public final synchronized void setChildren(String[] children,
			double[] weights) {
		childrenIds = children;
		this.weights = weights;
	}
		
	public final synchronized void setDepth(int depth) {
		this.depth = depth;
	}

	public final synchronized void setIndent(int indent) {
		this.indent = indent;
	}
	
	public final synchronized void setLevel(int newLevel) {
		level = newLevel;
	}

	public final synchronized void setName(String name) {
		this.name = name;
	}

	public final synchronized void setParents(String[] parents) {
		parentIds = parents;
	}

//	public final synchronized void addParent(String parentId) {
//		parentIds.add(parentId);
//	}
	
//	public final synchronized void removeParent(String parentId) {
//		parentIds.remove(parentId);
//	}
	
	public final synchronized void setPosition(int newPosition) {
		this.position = newPosition;
	}

	public final synchronized void setType(int type) {
		this.type = type;
	}

	public final String getId() {
		return id;
	}

	public final synchronized int getType() {
		return type;
	}

	public boolean canBeModified() {
		return true;
	}

	public boolean canCreateChildren() {
		return true;
	}
}
