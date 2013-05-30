package com.tensegrity.palo.xmla.loader;

import java.util.HashSet;
import java.util.Set;

import com.tensegrity.palo.xmla.XMLAConnection;
import com.tensegrity.palo.xmla.XMLAPaloInfo;
import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.PropertyInfo;
import com.tensegrity.palojava.loader.PropertyLoader;

public class XMLAPropertyLoader extends PropertyLoader {
	private final PaloInfo paloObject;
	
	public XMLAPropertyLoader(DbConnection paloConnection) {
		super(paloConnection);
		paloObject = null;
	}
	
	public XMLAPropertyLoader(DbConnection paloConnection, PaloInfo paloObject) {
		super(paloConnection);
		this.paloObject = paloObject;
	}
		
	public String[] getAllPropertyIds() {
		Set <String> allIds = new HashSet <String> ();		
		String [] ids = new String[0];
		if (paloObject == null) {
			ids = ((XMLAConnection) paloConnection).getAllKnownPropertyIds();
		} else {
			if (paloObject instanceof XMLAPaloInfo) {
				ids = ((XMLAPaloInfo) paloObject).getAllKnownPropertyIds(paloConnection);
			}
		}
		allIds.addAll(loadedInfo.keySet());
		for (String id: ids) {
			allIds.add(id);
		}
		
		return allIds.toArray(new String[0]);
	}
	
	public PropertyInfo load(String id) {
		PaloInfo property = loadedInfo.get(id);
		if (property == null) {
			if (paloObject == null) {
				property = paloConnection.getProperty(id);
			} else {
				if (paloObject instanceof XMLAPaloInfo) {
					property = ((XMLAPaloInfo) paloObject).getProperty(paloConnection, id);
				}
			}
			loaded(property);
		}
		return (PropertyInfo) property;
	}	

	protected void reload() {
		// TODO Auto-generated method stub		
	}
}
