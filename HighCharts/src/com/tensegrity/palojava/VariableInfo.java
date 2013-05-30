package com.tensegrity.palojava;

/**
 * 
 * @author PhilippBouillon
 * @deprecated Do not use. Interface is subject to change.
 */
public interface VariableInfo extends PaloInfo {
	public final static int VAR_TYPE_UNKNOWN   = 0;
	public final static int VAR_TYPE_MEMBER    = 1;
	public final static int VAR_TYPE_NUMERIC   = 2;
	public final static int VAR_TYPE_HIERARCHY = 3;
	
	public final static int VAR_PROC_TYPE_UNKNOWN    = 0;
	public final static int VAR_PROC_TYPE_USER_INPUT = 1;
	
	public final static int VAR_SELECTION_TYPE_UNKNOWN  = 0;
	public final static int VAR_SELECTION_TYPE_VALUE    = 1;
	public final static int VAR_SELECTION_TYPE_INTERVAL = 2;
	public final static int VAR_SELECTION_TYPE_COMPLEX  = 3;
		
	public final static int VAR_INPUT_TYPE_OPTIONAL              = 0;
	public final static int VAR_INPUT_TYPE_MANDATORY             = 1;
	public final static int VAR_INPUT_TYPE_MANDATORY_NOT_INITIAL = 2;
	public final static int VAR_INPUT_TYPE_UNKNOWN               = 3;

	String getName();
	DimensionInfo getElementDimension();
	int getSelectionType();
	int getInputType();
	String getDataType();	
	void setValue(ElementInfo element);
	void setValue(String elementId);
	void setInterval(ElementInfo from, ElementInfo to);
	void setInterval(String fromId, String toId);
	String getText();
	void setText(String newText);
	ElementInfo getValue();
	ElementInfo [] getInterval();
	ElementInfo [] getSelectedElements();
	void setSelectedElements(String [] elementIds);
}
