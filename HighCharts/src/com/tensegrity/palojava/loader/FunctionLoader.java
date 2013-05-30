/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.loader;

import com.tensegrity.palojava.DbConnection;

/**
 * <p><code>FunctionInfoLoader</code><7p>
 * This abstract base class manages the loading of functions.
 *
 * @author ArndHouben
 * @version $Id: FunctionLoader.java,v 1.1 2007/10/21 18:17:52 ArndHouben Exp $
 **/
public abstract class FunctionLoader extends PaloInfoLoader {

	/**
	 * Creates a new loader instance.
	 * @param paloConnection
	 */
	public FunctionLoader(DbConnection paloConnection) {
		super(paloConnection);
	}

	/**
	 * Returns the xml string which contains all defined functions
	 * @return functions as xml 
	 */
	public abstract String loadAll();
	
}
