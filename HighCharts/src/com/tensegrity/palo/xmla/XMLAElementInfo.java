package com.tensegrity.palo.xmla;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.tensegrity.palo.xmla.builders.BuilderRegistry;
import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.ElementInfo;
import com.tensegrity.palojava.HierarchyInfo;
import com.tensegrity.palojava.PropertyInfo;
import com.tensegrity.palojava.RuleInfo;
import com.tensegrity.palojava.loader.ElementLoader;

public class XMLAElementInfo implements ElementInfo, XMLAPaloInfo {
	private String id;
	private String name;
	private String uniqueName;
	private String hierarchyUniqueName;
	private Set<XMLAElementInfo> children;
	private String [] parents;
	private int type;
	private int level = -1;
	private int depth = -1;
	private int position = -1;
	private int parentCount;
	private int estimatedChildCount;
	private XMLAHierarchyInfo hierarchy;
	private final XMLADimensionInfo dimension;
	private XMLAElementInfo [] internalParents;
	private final Map kids;
	private boolean calculated;
	private RuleInfo rule = null;
	private String internalName;
	private boolean hasChildren;
	private final XMLAClient xmlaClient;
	private final XMLAConnection xmlaConnection;
	
	public XMLAElementInfo(XMLAHierarchyInfo hierarchy, XMLADimensionInfo dimension, XMLAClient client, XMLAConnection xmlaCon) {
		this.hierarchy = hierarchy;
		this.dimension = dimension;
//		children = new ArrayList();
		children = new LinkedHashSet<XMLAElementInfo>();
		kids = new LinkedHashMap();
		calculated = false;
		xmlaClient = client;
		xmlaConnection = xmlaCon;
	}
	
	public String getHierarchyUniqueName() {
		return hierarchyUniqueName;
	}
	
	public void addChild(XMLAElementInfo kid) {
		children.add(kid);
	}
	
	public void clearChildren() {
		children.clear();
	}
	
	public void setChildren(XMLAElementInfo [] kids) {
		clearChildren();
		children.addAll(Arrays.asList(kids));
	}
	
	public void setId(String id) {
		String cleanedText = id.replaceAll("\\[", "((");
		cleanedText = cleanedText.replaceAll("\\]", "))");
		cleanedText = cleanedText.replaceAll(":", "**");
		cleanedText = cleanedText.replaceAll(",", "(comma)");
		this.id = cleanedText;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}
	
	public String getInternalName() {
		return internalName;		
	}
	
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	
	public void setHierarchyUniqueName(String hierarchyUniqueName) {
		this.hierarchyUniqueName = hierarchyUniqueName;
	}
			
	public void setParentCount(int parentCount) {
		this.parentCount = parentCount;
	}
	
	public void setType(int type) {
		this.type = type;
	}
		
	public String[] getChildren() {
		ElementInfo [] info;		
		ElementLoader loader;
		if (hierarchy == null) {
			hierarchy = (XMLAHierarchyInfo) dimension.getDefaultHierarchy();
		}
		if (hierarchy != null) {
			loader = xmlaConnection.getElementLoader(hierarchy);
		} else {
			loader = xmlaConnection.getElementLoader(dimension);
		}
		if((children.isEmpty() && getChildrenCount() > 0)) {
			info = BuilderRegistry.getInstance().getElementInfoBuilder().
					getChildren(xmlaConnection, xmlaClient, ((XMLADimensionInfo)hierarchy.getDimension()).getCubeId(), this);
			for(ElementInfo _info : info) {
				children.add((XMLAElementInfo)_info);
				loader.loaded(_info);
			}
		} else {
			info = children.toArray(new XMLAElementInfo[0]);
		}
		String [] ids = new String[info.length];
		for (int i = 0; i < info.length; i++) {
			ids[i] = info[i].getId();
		}
		return ids;
	}
	
	public int getChildrenCount() {		
		if(children.isEmpty()) {
			return estimatedChildCount;
		}
		return children.size();
	}

	public boolean hasChildren() {
		return hasChildren;
	}
	
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	
	public final void setEstimatedChildCount(int estimatedChildCount) {
		this.estimatedChildCount = estimatedChildCount;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int newDepth) {
		depth = newDepth;
	}

	public DimensionInfo getDimension() {
		if (hierarchy == null) {
			return dimension;
		}
		return hierarchy.getDimension();
	}
	
	public HierarchyInfo getHierarchy() {
		return hierarchy;
	}

	public int getIndent() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int newLevel) {
		level = newLevel;
	}
	
	public String getName() {
		if (name == null || name.trim().length() == 0) {
			return " ";
		}
		return name;
	}

	public int getParentCount() {
		return parentCount;
	}

	public String[] getParents() {
		if (parents == null) {
			return new String[0];
		}
		return parents;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public double[] getWeights() {
		int n = getChildrenCount();
		if (n == 0) {
			return new double[0];	
		}
		double [] weights = new double[n];
		double wValue = 1.0;// / n;
		for (int i = 0; i < n; i++) {
			weights[i] = wValue;
		}
		return weights;
	}

	public void setParents(String[] parents) {
		this.parents = parents;
	}

	public String getId() {
		return id;
	}

	public int getType() {
//		if(getName().equals("Corvallis"))
//			return ElementInfo.TYPE_CONSOLIDATED;
		if (getChildrenCount() > 0 || estimatedChildCount > 0) {
			return ElementInfo.TYPE_CONSOLIDATED;
		}
		return type;
	}
	
	public String toString() {
		return "Element " + getName() + " [" + getId() + "]: " + getParentCount() + " parent(s), " + getChildrenCount() + " children, Type: " + getType();
	}
	
	public String getUniqueName() {
		return uniqueName;
	}
	
	public void addChildInternal(XMLAElementInfo kid) {
		kids.put(kid.getUniqueName().hashCode(), kid);
	}
	
	public XMLAElementInfo getChildInternal(String elementName) {
		return (XMLAElementInfo) kids.get(elementName);
	}
		
	public XMLAElementInfo [] getChildrenInternal() {
		return (XMLAElementInfo []) kids.values().toArray(new XMLAElementInfo[0]);
	}

	public void setParentInternal(XMLAElementInfo [] pars) {		
		this.parents = new String[pars.length];
		for (int i = 0; i < pars.length; i++) {
			parents[i] = pars[i].getId();
		}
		setParentCount(pars.length);
		internalParents = pars;
	}
	
	public XMLAElementInfo [] getParentsInternal() {
		return internalParents;
	}
	
	public boolean isCalculated() {
		return calculated;
	}
	
	public RuleInfo getRule() {
		return rule;
	}
	
	public void setRule(RuleInfo rule) {
		this.rule = rule;
	}
	
	public void setCalculated(boolean newCalculated) {
		calculated = newCalculated;
	}
	
	public String[] getAllKnownPropertyIds(DbConnection con) {
		return new String[0];
	}

	public PropertyInfo getProperty(DbConnection con, String id) {
		return null;
	}

	public boolean canBeModified() {
		return false;
	}

	public boolean canCreateChildren() {
		return false;
	}
}
