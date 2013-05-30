package com.tensegrity.palo.xmla.loader;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tensegrity.palo.xmla.XMLAClient;
import com.tensegrity.palo.xmla.XMLAConnection;
import com.tensegrity.palo.xmla.XMLADatabaseInfo;
import com.tensegrity.palo.xmla.XMLAProperties;
import com.tensegrity.palo.xmla.XMLARestrictions;
import com.tensegrity.palo.xmla.builders.BuilderRegistry;
import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.loader.DatabaseLoader;

public class XMLADatabaseLoader extends DatabaseLoader {
	private Set <String> databaseIds = null;
	private final XMLAClient xmlaClient;
	
	public XMLADatabaseLoader(XMLAConnection xmlaConnection, XMLAClient xmlaClient) {
		super(xmlaConnection);		
		this.xmlaClient = xmlaClient;
	}

	public int getDatabaseCount() {
		if (databaseIds == null) {
			loadAllDatabaseIds();
		}
		return databaseIds.size();
	}

	public final String[] getAllDatabaseIds() {
		if (databaseIds == null) {
			loadAllDatabaseIds();
		}
		return databaseIds.toArray(new String[0]);
	}

	public DatabaseInfo loadByName(String name) {
		//first check if we have it loaded already
		DatabaseInfo dbInfo = findDatabase(name);
		if (dbInfo == null) {
			//if not, we have to ask server...
			return loadDatabase(name);
		}
		return dbInfo;
	}

	protected void reload() {
		System.out.println("XMLA Database Loader::reload");
	}
	
	private final DatabaseInfo findDatabase(String name) {
		Collection<PaloInfo> infos = getLoaded();
		for(PaloInfo info: infos) {
			if (info instanceof DatabaseInfo) {
				DatabaseInfo dbInfo = (DatabaseInfo) info;
				if (dbInfo.getId().equals(name))
					return dbInfo;
			}
		}
		return null;
	}	
	
	private final void loadAllDatabaseIds() {
		databaseIds = new LinkedHashSet <String> ();
		String connectionName = xmlaClient.getConnections()[0].getName();

		try {
    	    XMLARestrictions rest = new XMLARestrictions();
    	    XMLAProperties   prop = new XMLAProperties();

	        prop.setDataSourceInfo(connectionName);    		
	        Document catalogResult = xmlaClient.getCatalogList(rest, prop);
	        NodeList catalogList = catalogResult.getElementsByTagName("row");	        
	        
			if (catalogList == null || catalogList.getLength() == 0) {
				return;
			}
			
			for (int i = 0; i < catalogList.getLength(); i++) {
				NodeList nlRow = catalogList.item(i).getChildNodes();

				for (int j = 0; j < nlRow.getLength(); j++) {
					if (nlRow.item(j).getNodeType() == Node.ELEMENT_NODE) {
						if (nlRow.item(j).getNodeName().equals("CATALOG_NAME"))  {	
							String text = XMLAClient.getTextFromDOMElement(nlRow.item(j));
							if (xmlaClient.isSAP() && text.equals("$INFOCUBE")) {
								continue;
							}
							databaseIds.add(text);
						} 
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private final XMLADatabaseInfo loadDatabase(String name) {
		XMLADatabaseInfo dbInfo = 
			BuilderRegistry.getInstance().getDatabaseInfoBuilder().
				getDatabaseInfo((XMLAConnection) paloConnection, xmlaClient, name);
		loadedInfo.put(name, dbInfo);
		return dbInfo;
	}
}
