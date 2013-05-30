package com.tensegrity.palo.xmla.parsers;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.tensegrity.palo.xmla.XMLAClient;
import com.tensegrity.palo.xmla.XMLAConnection;
import com.tensegrity.palo.xmla.XMLADatabaseInfo;
import com.tensegrity.palo.xmla.XMLAProperties;
import com.tensegrity.palo.xmla.XMLARestrictions;

public class XMLADatabaseRequestor extends AbstractXMLARequestor {
	public static String ITEM_CATALOG_NAME  = "CATALOG_NAME";
	public static String ITEM_DESCRIPTION   = "DESCRIPTION";
	public static String ITEM_ROLES         = "ROLES";
	public static String ITEM_DATE_MODIFIED = "DATE_MODIFIED";
	
	private String restrictionName;
	private final ArrayList <XMLADatabaseInfo> databaseInfos = 
		new ArrayList<XMLADatabaseInfo>();
	
	private final XMLAConnection connection;
	
	public XMLADatabaseRequestor(XMLAConnection connection) {
		activateItem(ITEM_CATALOG_NAME);
		this.connection = connection;
	}
	
	public void setCatalogNameRestriction(String catalogName) {
		restrictionName = catalogName;
	}
			
	public boolean requestDatabases(XMLAClient xmlaClient) {
		databaseInfos.clear();
		try {
    	    XMLARestrictions rest = new XMLARestrictions();
    	    XMLAProperties   prop = new XMLAProperties();

	        String connectionName = xmlaClient.getConnections()[0].getName();
    	    prop.setDataSourceInfo(connectionName);
    		if (restrictionName != null && 
    				restrictionName.trim().length() != 0) {
    			rest.setCatalog(restrictionName);
    		}
    		
	        Document catalogResult = 
	        	xmlaClient.getCatalogList(rest, prop);
	        NodeList catalogList = catalogResult.getElementsByTagName("row");	        
			if (catalogList == null || catalogList.getLength() == 0) {
				return false;
			}
			parseXMLANodeList(catalogList, connectionName, xmlaClient);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	protected void parseResult(HashMap <String, String> result, 
			String connectionName, XMLAClient xmlaClient) {
		String databaseName = result.get(ITEM_CATALOG_NAME);
		if (xmlaClient.isSAP()) {
			if (databaseName.equals("$INFOCUBE")) {
				return;
			}
		}
		
		XMLADatabaseInfo databaseInfo = new XMLADatabaseInfo(connection, databaseName);

//		XMLACubeRequestor cubeReq = 
//			new XMLACubeRequestor(connection, databaseInfo);
//		cubeReq.setCatalogNameRestriction(databaseName);
//		cubeReq.setCatalogNameProperty(databaseName);
//		XMLACubeInfo [] cubes = cubeReq.requestCubes(xmlaClient);
		databaseInfo.setCubeCount(1); //cubes.length);
		
//		XMLAHierarchyRequestor hierReq =
//			new XMLAHierarchyRequestor(null, databaseInfo);
//		hierReq.setCatalogNameRestriction(databaseName);
//		XMLAHierarchyInfo [] hierarchies = hierReq.requestHierarchies(xmlaClient);
		databaseInfo.setDimensionCount(1); //hierarchies.length);
		
		databaseInfos.add(databaseInfo);	        			
	}
	
	public XMLADatabaseInfo [] getDatabaseInfos() {
		return databaseInfos.toArray(new XMLADatabaseInfo[0]);
	}
}
