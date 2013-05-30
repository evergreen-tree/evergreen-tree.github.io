/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava;

/**
 * The <code>RuleInfo</code> is a representation of a palo <code>Rule</code> 
 * object. A palo rule belongs to a certain palo <code>Cube</code> and consists 
 * of an identifier and a definition. 
 * 
 * @author Arnd Houben
 * @version $Id: RuleInfo.java,v 1.5 2007/08/08 12:34:13 ArndHouben Exp $
 */
public interface RuleInfo extends PaloInfo {

	/** rules have no type */
	public static final int UNDEFINED = -1;
	
	/**
	 * Returns the rule definition
	 * @return rule definition
	 */
	public String getDefinition();
	
//	/**
//	 * Returns a comma separated list of function names used by this rule
//	 * @return function names
//	 */
//	public String getFunctions();
//	
	/**
	 * Returns the palo <code>Cube</code> representation to which this rule
	 * belongs.
	 * @return <code>Cube</code> representation which contains this rule
	 */
	public CubeInfo getCube();
	
	public String getExternalIdentifier();
	
	public String getComment();
	
	public boolean useExternalIdentifier();
}
