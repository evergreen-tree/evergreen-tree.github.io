/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api;

/**
 * The <code>Rule</code> interface represents an enterprise rule definition for
 * a palo {@link <code>Cube</code>}. It is characterized by its id and a raw
 * rule definition. Furthermore a rule corresponds to only one cube.
 *  
 * 
 * @author ArndHouben
 * @version $Id: Rule.java,v 1.7 2007/08/29 14:04:15 ArndHouben Exp $
 */
public interface Rule {

	/**
	 * Returns the unique identifier for this rule instance
	 * @return the rule id
	 */
	String getId();
	
	/**
	 * Returns the <code>Cube</code> which is affected by this rule 
	 * @return the affected cube
	 */
	Cube getCube();

	/**
	 * Sets a definition for this rule. 
	 * <b>NOTE:</b> this will update current rule and therefore performs a 
	 * server request!
	 * @param definition new rule definition
	 */
	void setDefinition(String definition);

	/**
	 * Returns the rule definition, i.e. its textual representation
	 * @return the rule definition
	 */
	String getDefinition();

//	/**
//	 * Sets the functions used by this rule <b>NOTE:</b> this will update 
//	 * current rule and therefore performs a server request!
//	 * @param functions comma separated list of function names
//	 */
//	void setFunctions(String functions);
//	
//	/**
//	 * Returns a comma separated list of function names used by this rule. If 
//	 * no functions are used this method returns <code>null</code>
//	 * @return function names or <code>null</code>
//	 */
//	String getFunctions();
	
	
	/**
	 * Sets an optional comment for this rule. <b>NOTE:</b> this will update 
	 * current rule and therefore performs a server request!
	 * @param comment a rule comment
	 */
	void setComment(String comment);
	
	/**
	 * Returns an optional comment for this rule or <code>null</code> if none
	 * was set.
	 * @return the rule comment or <code>null</code> if none was set
	 */
	String getComment();
		
	/**
	 * Sets a new external identifier string to use inside rule definition 
	 * instead of definition name.
	 * <b>NOTE:</b> this will update current rule and therefore performs a 
	 * server request!
	 * @param externalId the identifier to use
	 */
	void setExternalIdentifier(String externalId);
	
	/**
	 * Sets a new external identifier string and use it inside rule definition
	 * <b>NOTE:</b> this will update current rule and therefore performs a 
	 * server request!
	 * @param externalId the identifier to use
	 * @param useIt set to <code>true</code> if new identifier should be used
	 * in rule defintion, to <code>false</code> otherwise
	 */
	void setExternalIdentifier(String externalId, boolean useIt);
	
	/**
	 * Returns the optional external identifier or <code>null</code> if none
	 * was set
	 * @return external identifier or <code>null</code>
	 */
	String getExternalIdentifier();
	
	/**
	 * En- or disables the usage of a specified external identifier. If no 
	 * identifier was specified calling this method has no effect.
	 * <b>NOTE:</b> this will update current rule and therefore performs a 
	 * server request!
	 */
	void useExternalIdentifier(boolean useIt);
	
	/**
	 * Updates this rule with the given parameters.
	 * <b>NOTE:</b> this will update current rule and therefore performs a 
	 * @param definition
	 * @param externalIdentifier
	 * @param useIt
	 * @param comment
	 */
	void update(String definition, String externalIdentifier, boolean useIt, String comment);
}
