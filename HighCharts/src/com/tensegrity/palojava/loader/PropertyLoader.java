/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.loader;

import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.PropertyInfo;

/**
 * <code>VariableInfoLoader</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: PropertyLoader.java,v 1.3 2007/10/29 13:04:34 PhilippBouillon Exp $
 **/
public abstract class PropertyLoader extends PaloInfoLoader {
	public PropertyLoader(DbConnection paloConnection) {
		super(paloConnection);
	}

	/**
	 * Returns the identifiers of all properties currently known to the palo 
	 * server.
	 * @return ids of all known properties
	 */
	public abstract String[] getAllPropertyIds();

	public abstract PropertyInfo load(String id);

//	public final PropertyInfo load(String id) {
//		PaloInfo property = loadedInfo.get(id);
//		if (property == null) {
//			property = paloConnection.getProperty(id);
//			loaded(property);
//		}
//		return (PropertyInfo) property;
//	}
}
