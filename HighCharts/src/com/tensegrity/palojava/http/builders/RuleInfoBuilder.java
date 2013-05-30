/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.http.builders;

import com.tensegrity.palojava.CubeInfo;
import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.RuleInfo;
import com.tensegrity.palojava.impl.RuleImpl;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author Arnd Houben
 * @version $Id: RuleInfoBuilder.java,v 1.5 2007/08/08 13:01:02 ArndHouben Exp $
 */
public class RuleInfoBuilder {

	public final RuleInfo create(PaloInfo parent, String[] response) {
		if(response.length<2)
			throw new PaloException("Not enough information to create RuleInfo");
		CubeInfo cube = (CubeInfo)parent;
		String id = response[0];
		RuleImpl rule = new RuleImpl(cube,id);
		update(rule,response);
		return rule;
	}
	
	public final void update(RuleImpl rule, String[] response) {
		rule.setDefinition(response[1]);
		//set optional parameter if we have...
		if(response.length>2)
			rule.setExternalIdentifier(response[2]);
		if(response.length>3)
			rule.setComment(response[3]);		
	}
	
}
