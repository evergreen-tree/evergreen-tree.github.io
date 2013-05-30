/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palo.xmla.loader;

import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.loader.FunctionLoader;

/**
 * <code>HttpFunctionInfoLoader</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: XMLAFunctionLoader.java,v 1.1 2007/10/22 16:44:39 PhilippBouillon Exp $
 **/
public class XMLAFunctionLoader extends FunctionLoader {
	public XMLAFunctionLoader(DbConnection paloConnection) {
		super(paloConnection);
	}

	public String loadAll() {
		return paloConnection.listFunctions();		
	}

	protected final void reload() {		
	}
}
