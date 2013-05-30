package com.tensegrity.palo.xmla;

import com.tensegrity.palo.xmla.builders.BuilderRegistry;
import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.ElementInfo;
import com.tensegrity.palojava.VariableInfo;

public class XMLAVariableInfo implements VariableInfo {	
	private final String [] VAR_TYPE_STRING = 
		{"Unknown", "Member", "Numeric", "Hierarchy"};
	private final String [] VAR_PROC_TYPE_STRING = 
		{"Unknown", "User input"};
	private final String [] VAR_SELECTION_TYPE_STRING =
		{"Unknown", "Value", "Interval", "Complex"};
	private final String [] VAR_INPUT_TYPE_STRING =
		{"Optional", "Mandatory", "Mandatory not initial", "Unknown"};
	
	private String name;
	private String id;
	private String uid;
	private String ordinal;
	private int    variableType;
	private String dataType;
	private String characterMaxLength;
	private int    variableProcessingType;
	private int    variableSelectionType;
	private int    variableInputType;
	private String referenceDimension;
	private String referenceHierarchy;
	private String defaultLow;
	private String defaultLowCap;
	private String defaultHigh;
	private String defaultHighCap;
	private String description;
	private DimensionInfo elementDimension;
	private boolean elementsSet;
	private ElementInfo value;
	private ElementInfo from;
	private ElementInfo to;
	private ElementInfo [] selectedElements;
	private boolean hideConsolidations;
	private String textValue;
	
	public XMLAVariableInfo() {
		elementsSet = false;
		elementDimension = null;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setId(String newId) {
		id = newId;
	}
	
	public void setUId(String newUid) {
		uid = newUid;
	}

	public void setOrdinal(String newOrdinal) {
		ordinal = newOrdinal;
	}

	public void setType(int newVariableType) {
		variableType = newVariableType;
	}

	public void setDataType(String newDataType) {
		dataType = newDataType;
	}

	public void setCharacterMaximumLength(String newCharMax) {
		characterMaxLength = newCharMax;
	}

	public void setVariableProcessingType(int newVariableProc) {
		variableProcessingType = newVariableProc;
	}

	public void setSelectionType(int newVarSelectionType) {
		variableSelectionType = newVarSelectionType;
	}

	public void setInputType(int newVarInputType) {
		variableInputType = newVarInputType;
	}

	public void setReferenceDimension(String newReferenceDimension) {
		referenceDimension = newReferenceDimension;
	}

	public void setReferenceHierarchy(String newReferenceHierarchy) {
		referenceHierarchy = newReferenceHierarchy;
	}

	public void setDefaultLow(String newDefaultLow) {
		defaultLow = newDefaultLow;
	}

	public void setDefaultLowCap(String newDefaultLowCap) {
		defaultLowCap = newDefaultLowCap;
	}

	public void setDefaultHigh(String newDefaultHigh) {
		defaultHigh = newDefaultHigh;
	}

	public void setDefaultHighCap(String newDefaultHighCap) {
		defaultHighCap = newDefaultHighCap;
	}

	public void setDescription(String newDescription) {
		description = newDescription;
	}

	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getUId() {
		return uid;
	}

	public String getOrdinal() {
		return ordinal;
	}

	public String getDataType() {
		return dataType;
	}

	public String getCharacterMaximumLength() {
		return characterMaxLength;
	}

	public int getCharacterProcessingType() {
		return variableProcessingType;
	}

	public int getSelectionType() {
		return variableSelectionType;
	}

	public int getInputType() {
		return variableInputType;
	}

	public String getReferenceDimension() {
		return referenceDimension;
	}

	public String getReferenceHierarchy() {
		return referenceHierarchy;
	}

	public String getDefaultLow() {
		return defaultLow;
	}

	public String getDefaultLowCap() {
		return defaultLowCap;
	}

	public String getDefaultHigh() {
		return defaultHigh;
	}

	public String getDefaultHighCap() {
		return defaultHighCap;
	}

	public String getDescription() {
		return description;
	}
	
	public boolean areElementsSet() {
		return elementsSet;
	}
	
	public void loadVariableElements(XMLAClient client, XMLAConnection con, XMLADatabaseInfo database) {		
		BuilderRegistry.getInstance().getVariableInfoBuilder().
			requestVarElements(this, con, database, client);		
		elementsSet = true;
	}

	public void setElementDimension(DimensionInfo newDimension) {
		elementDimension = newDimension;		
	}
	
	public DimensionInfo getElementDimension() {
		return elementDimension;
	}
	
	public String toString() {
		return "XMLA-Variable:            " + id + "\n" +
		       "Name:                     " + name + "\n" +
		       "UId:                      " + uid + "\n" +
		       "Ordinal:                  " + ordinal + "\n" +
		       "Type:                     " + VAR_TYPE_STRING[variableType] + "\n" +
		       "Datatype:                 " + dataType + "\n" +
		       "Character Maximum Length: " + characterMaxLength + "\n" +
		       "Processing Type:          " + VAR_PROC_TYPE_STRING[variableProcessingType] + "\n" +
		       "Selection Type:           " + VAR_SELECTION_TYPE_STRING[variableSelectionType] + "\n" +
		       "Entry Type:               " + VAR_INPUT_TYPE_STRING[variableInputType] + "\n" +
		       "Reference Dimension:      " + referenceDimension + "\n" +
		       "Reference Hierarchy:      " + referenceHierarchy + "\n" +
		       "Default Low:              " + defaultLow + "\n" +
		       "Default Low Cap:          " + defaultLowCap + "\n" +
		       "Default High:             " + defaultHigh + "\n" +
		       "Default High Cap:         " + defaultHighCap + "\n" +
		       "Description:              " + description + "\n" +
		       "------------------------------------------------------------\n";		       
	}

	public int getType() {
		return variableType;
	}

	public ElementInfo[] getInterval() {
		return new ElementInfo [] {from, to};
	}

	public ElementInfo getValue() {
		return value;
	}

	public void setInterval(ElementInfo from, ElementInfo to) {
		this.from = from;
		this.to = to;
	}
	
	public void setInterval(String fromId, String toId) {
		from = 
			((XMLADimensionInfo) elementDimension).getMemberByIdInternal(fromId);
		to = 
			((XMLADimensionInfo) elementDimension).getMemberByIdInternal(toId);		
	}

	public void setValue(ElementInfo element) {
		value = element;		
	}
	
	public void setValue(String elementId) {
		value = 
			((XMLADimensionInfo) elementDimension).getMemberByIdInternal(elementId);
	}
	
	public void setHideConsolidations(boolean newHide) {
		hideConsolidations = newHide;
	}
	
	public boolean getHideConsolidations() {
		return hideConsolidations;
	}

	public String getText() {
		if (textValue == null) {
			return "";
		}
		return textValue;
	}

	public void setText(String newText) {
		textValue = newText;
	}

	public ElementInfo[] getSelectedElements() {		
		return selectedElements;
	}

	public void setSelectedElements(String[] elementIds) {
		if (elementIds == null) {
			selectedElements = null;
			return;
		}
		int counter = 0;
		selectedElements = new ElementInfo[elementIds.length];
		for (String id: elementIds) {
			selectedElements[counter++] =
				((XMLADimensionInfo) elementDimension).getMemberByIdInternal(id);
		}
	}

	public boolean canBeModified() {
		return false;
	}

	public boolean canCreateChildren() {
		return false;
	}
}
