/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palo.xmla.loader;

import com.tensegrity.palo.xmla.XMLAClient;
import com.tensegrity.palojava.CubeInfo;
import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.RuleInfo;
import com.tensegrity.palojava.loader.RuleLoader;

/**
 * <code>HttpRuleInfoLoader</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: XMLARuleLoader.java,v 1.1 2007/10/22 16:44:39 PhilippBouillon Exp $
 **/
public class XMLARuleLoader extends RuleLoader {
	private final XMLAClient xmlaClient;
	
	public XMLARuleLoader(DbConnection paloConnection, XMLAClient xmlaClient,
			CubeInfo cube) {
		super(paloConnection, cube);
		this.xmlaClient = xmlaClient;
	}

	public String[] getAllRuleIds() {
		reload();
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

