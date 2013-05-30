/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.impl;

import com.tensegrity.palojava.CubeInfo;
import com.tensegrity.palojava.RuleInfo;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author Arnd Houben
 * @version $Id: RuleImpl.java,v 1.5 2007/11/30 08:39:39 PhilippBouillon Exp $
 */
public class RuleImpl implements RuleInfo {
	
	private final CubeInfo cube;
	private String definition;
	private final String id;
//	private String functions;
	
	//optional fields:
	private String comment;
	private String externalId;
	private boolean useExternalId;
	
	public RuleImpl(CubeInfo cube, String id) {
		this.cube = cube;
		this.id = id;
	}
	
	public final CubeInfo getCube() {
		return cube;
	}

	public final String getDefinition() {
		return definition;
	}
	
//	public final String getFunctions() {
//		return functions;
//	}

	public final String getExternalIdentifier() {
		return externalId;
	}

	public final String getId() {
		return id;
	}

	public final int getType() {
		return UNDEFINED;
	}

	public final String getComment() {
		return comment;
	}

	public final void setComment(String comment) {
		this.comment = comment;
	}

	public final void setDefinition(String definition) {
		this.definition = definition;
	}
	
//	public final void setFunctions(String functions) {
//		this.functions = functions;
//	}
	
	public final void setExternalIdentifier(String externalId) {
		this.externalId = externalId;
	}
	
	public final void useExternalIdentifier(boolean b) {
		useExternalId = b;
	}
	
	public final boolean useExternalIdentifier() {
		return useExternalId;
	}

	public boolean canBeModified() {
		return true;
	}

	public boolean canCreateChildren() {
		return true;
	}
	
}
