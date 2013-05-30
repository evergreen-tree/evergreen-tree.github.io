/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.impl;

import java.util.HashMap;
import java.util.Map;

import org.palo.api.Cube;
import org.palo.api.Property2;
import org.palo.api.Rule;

import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.PropertyInfo;
import com.tensegrity.palojava.RuleInfo;
import com.tensegrity.palojava.loader.PropertyLoader;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author ArndHouben
 * @version $Id: RuleImpl.java,v 1.8 2007/12/10 15:39:10 ArndHouben Exp $
 */
class RuleImpl implements Rule {

	private final CubeImpl cube;
	private final RuleInfo ruleInfo;
	private final DbConnection dbConnection;
	private final PropertyLoader propertyLoader;
	private final Map <String, Property2Impl> loadedProperties;
	
	RuleImpl(DbConnection dbConnection, CubeImpl cube, RuleInfo ruleInfo) {
		this.cube = cube;
		this.ruleInfo = ruleInfo;
		this.dbConnection = dbConnection;
		this.loadedProperties = new HashMap <String, Property2Impl> ();
		this.propertyLoader = dbConnection.getTypedPropertyLoader(ruleInfo);
	}
	
	public final Cube getCube() {
		return cube;
	}

	public final String getDefinition() {
		return ruleInfo.getDefinition();
	}

//	public final String getFunctions() {
//		return ruleInfo.getFunctions();
//	}


	public final String getId() {
		return ruleInfo.getId();
	}

	public final RuleInfo getInfo() {
		return ruleInfo;
	}

	public final String getComment() {
		return ruleInfo.getComment();
	}

	public final String getExternalIdentifier() {
		return ruleInfo.getExternalIdentifier();
	}

	public void setComment(String comment) {
		dbConnection.update(ruleInfo, ruleInfo.getDefinition(), ruleInfo.getExternalIdentifier(), ruleInfo
				.useExternalIdentifier(), comment);
		cube.fireRuleChanged(this, comment);
	}

	public void setDefinition(String definition) {
		dbConnection.update(ruleInfo, definition, 
				ruleInfo.getExternalIdentifier(), ruleInfo
						.useExternalIdentifier(), ruleInfo.getComment());
		cube.fireRuleChanged(this, definition);
	}

//	public void setFunctions(String functions) {
//		dbConnection.update(ruleInfo, ruleInfo.getDefinition(), functions,
//				ruleInfo.getExternalIdentifier(), ruleInfo
//						.useExternalIdentifier(), ruleInfo.getComment());
//	}


	public void setExternalIdentifier(String externalId) {
		setExternalIdentifier(externalId, false);
	}

	public void setExternalIdentifier(String externalId, boolean useIt) {
		dbConnection.update(ruleInfo, ruleInfo.getDefinition(), externalId, useIt, ruleInfo.getComment());
		cube.fireRuleChanged(this, externalId);
	}

	public void update(String definition, 
			String externalIdentifier, boolean useIt, String comment) {
		dbConnection.update(ruleInfo, definition,
				externalIdentifier, useIt, comment);
		cube.fireRuleChanged(this, null);
	}

	public void useExternalIdentifier(boolean useIt) {
		String extId = ruleInfo.getExternalIdentifier();
		if (extId == null || extId.equals(""))
			return;
		dbConnection.update(ruleInfo, ruleInfo.getDefinition(), ruleInfo.getExternalIdentifier(), useIt,
				ruleInfo.getComment());
	}

	public String[] getAllPropertyIds() {
		return propertyLoader.getAllPropertyIds();
	}

	public Property2 getProperty(String id) {
		PropertyInfo propInfo = propertyLoader.load(id);
		if (propInfo == null) {
			return null;
		}
		Property2 property = loadedProperties.get(propInfo.getId());
		if (property == null) {
			property = createProperty(propInfo);
		}

		return property;
	}
	
	public void addProperty(Property2 property) {
		if (property == null) {
			return;
		}
		Property2Impl _property = (Property2Impl)property;
		propertyLoader.loaded(_property.getPropInfo());
		loadedProperties.put(_property.getId(), _property);
	}
	
	public void removeProperty(String id) {
		Property2 property = getProperty(id); 
		if (property == null) {
			return;
		}
		if (property.isReadOnly()) {
			return;
		}
		loadedProperties.remove(property);
	}

	final void clearCache() {
		for(Property2Impl property : loadedProperties.values())
			property.clearCache();
		loadedProperties.clear();
		propertyLoader.reset();
	}
	private void createProperty(Property2 parent, PropertyInfo kid) {
		Property2 p2Kid = Property2Impl.create(parent, kid);
		parent.addChild(p2Kid);		
		for (PropertyInfo kidd: kid.getChildren()) {
			createProperty(p2Kid, kidd);
		}
	}
	
	private Property2 createProperty(PropertyInfo propInfo) {
		Property2 prop = Property2Impl.create(null, propInfo);
		for (PropertyInfo kid: propInfo.getChildren()) {
			createProperty(prop, kid);
		}
		return prop;
	}
	
}
