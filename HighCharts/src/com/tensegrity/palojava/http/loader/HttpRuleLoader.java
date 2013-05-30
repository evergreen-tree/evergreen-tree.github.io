/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.http.loader;

import com.tensegrity.palojava.CubeInfo;
import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.RuleInfo;
import com.tensegrity.palojava.loader.RuleLoader;

/**
 * <code>HttpRuleInfoLoader</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: HttpRuleLoader.java,v 1.2 2008/04/17 18:11:50 ArndHouben Exp $
 **/
public class HttpRuleLoader extends RuleLoader {

	public HttpRuleLoader(DbConnection paloConnection, CubeInfo cube) {
		super(paloConnection, cube);		
	}

	public String[] getAllRuleIds() {
		if(!loaded) {			
			reload();
			loaded = true;
		}
		return getLoadedIds();
	}

	protected final void reload() {
		reset();
		RuleInfo[] rules = paloConnection.getRules(cube);
		for (RuleInfo rule : rules) {
			loaded(rule);
		}
	}
}

