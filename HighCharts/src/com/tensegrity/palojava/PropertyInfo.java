/**
 * 
 */
package com.tensegrity.palojava;

/**
 * @author PhilippBouillon
 * 
 */
public interface PropertyInfo extends PaloInfo {
	String getValue();
	void setValue(String newValue);
	
	PropertyInfo getParent();
	int getChildCount();
	PropertyInfo [] getChildren();
	void addChild(PropertyInfo child);
	void removeChild(PropertyInfo child);
	void clearChildren();
	boolean isReadOnly();
	PropertyInfo getChild(String id);
}
