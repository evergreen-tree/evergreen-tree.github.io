/**
 * 
 */
package com.tensegrity.palojava.impl;

import java.util.LinkedHashSet;

import com.tensegrity.palojava.PropertyInfo;

/**
 * @author PhilippBouillon
 *
 */
public class PropertyInfoImpl implements PropertyInfo {
	// Property types:
	public static final int 
		TYPE_NUMERIC = 1,
		TYPE_STRING = 2,
		TYPE_BOOLEAN = 3;

	private final LinkedHashSet <PropertyInfo> children;
	private String id;
	private String value;
	private PropertyInfo parent;
	private int type;
	private boolean readOnly;
	
	public PropertyInfoImpl(String id, String value, PropertyInfo parent,
			                int type, boolean readOnly) {
		children = new LinkedHashSet <PropertyInfo> ();
		this.id = id;
		this.value = value;
		this.parent = parent;
		this.type = type;
		this.readOnly = readOnly;
	}
	
	/* (non-Javadoc)
	 * @see com.tensegrity.palojava.PropertyInfo#addChild(com.tensegrity.palojava.PropertyInfo)
	 */
	public void addChild(PropertyInfo child) {
		children.add(child);
	}

	/* (non-Javadoc)
	 * @see com.tensegrity.palojava.PropertyInfo#clearChildren()
	 */
	public void clearChildren() {
		children.clear();
	}

	/* (non-Javadoc)
	 * @see com.tensegrity.palojava.PropertyInfo#getChildCount()
	 */
	public int getChildCount() {
		return children.size();
	}

	/* (non-Javadoc)
	 * @see com.tensegrity.palojava.PropertyInfo#getChildren()
	 */
	public PropertyInfo[] getChildren() {
		return children.toArray(new PropertyInfo[0]);
	}

	/* (non-Javadoc)
	 * @see com.tensegrity.palojava.PropertyInfo#getParent()
	 */
	public PropertyInfo getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see com.tensegrity.palojava.PropertyInfo#getValue()
	 */
	public String getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see com.tensegrity.palojava.PropertyInfo#removeChild(com.tensegrity.palojava.PropertyInfo)
	 */
	public void removeChild(PropertyInfo child) {
		if (!readOnly) {
			children.remove(child);
		}
	}

	/* (non-Javadoc)
	 * @see com.tensegrity.palojava.PropertyInfo#setValue(java.lang.String)
	 */
	public void setValue(String newValue) {
		if (!readOnly) {
			value = newValue;
		}
	}

	/* (non-Javadoc)
	 * @see com.tensegrity.palojava.PaloInfo#getId()
	 */
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.tensegrity.palojava.PaloInfo#getType()
	 */
	public int getType() {
		return type;
	}
	
	public boolean isReadOnly() {
		return readOnly;
	}

	public PropertyInfo getChild(String id) {
		for (PropertyInfo info: children) {
			if (info.getId().equals(id)) {
				return info;
			}
		}
		return null;
	}

	public boolean canBeModified() {
		return true;
	}

	public boolean canCreateChildren() {
		return true;
	}
}
