/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.http.loader;

import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.loader.FunctionLoader;
import com.tensegrity.palojava.loader.RuleLoader;

/**
 * <code>HttpFunctionInfoLoader</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: HttpFunctionLoader.java,v 1.1 2007/10/21 18:18:14 ArndHouben Exp $
 **/
public class HttpFunctionLoader extends FunctionLoader {

	public HttpFunctionLoader(DbConnection paloConnection) {
		super(paloConnection);
	}

	public String loadAll() {
		if(RuleLoader.supportsRules(paloConnection))
			return paloConnection.listFunctions();
		return "";
	}

	protected final void reload() {		
	}
}
