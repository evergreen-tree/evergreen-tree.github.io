package com.tensegrity.palo.xmla.parsers;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.tensegrity.palo.xmla.XMLAClient;
import com.tensegrity.palo.xmla.XMLAConnection;
import com.tensegrity.palo.xmla.XMLACubeInfo;
import com.tensegrity.palo.xmla.XMLADatabaseInfo;
import com.tensegrity.palo.xmla.XMLADimensionInfo;
import com.tensegrity.palo.xmla.XMLAHierarchyInfo;
import com.tensegrity.palo.xmla.XMLAProperties;
import com.tensegrity.palo.xmla.XMLARestrictions;
import com.tensegrity.palojava.HierarchyInfo;

public class XMLADimensionRequestor extends AbstractXMLARequestor {
	public static String ITEM_CATALOG_NAME                 = "CATALOG_NAME";
	public static String ITEM_SCHEMA_NAME                  = "SCHEMA_NAME";
	public static String ITEM_CUBE_NAME                    = "CUBE_NAME";
	public static String ITEM_DIMENSION_NAME               = "DIMENSION_NAME";
	public static String ITEM_DIMENSION_UNIQUE_NAME        = "DIMENSION_UNIQUE_NAME";
	public static String ITEM_DIMENSION_GUID               = "DIMENSION_GUID";
	public static String ITEM_DIMENSION_CAPTION            = "DIMENSION_CAPTION";
	public static String ITEM_DIMENSION_ORDINAL            = "DIMENSION_ORDINAL";
	public static String ITEM_DIMENSION_TYPE               = "DIMENSION_TYPE";
	public static String ITEM_DIMENSION_CARDINALITY        = "DIMENSION_CARDINALITY";
	public static String ITEM_DEFAULT_HIERARCHY            = "DEFAULT_HIERARCHY";
	public static String ITEM_DESCRIPTION                  = "DESCRIPTION";
	public static String ITEM_IS_VIRTUAL                   = "IS_VIRTUAL";
	public static String ITEM_IS_READWRITE                 = "IS_READWRITE";
	public static String ITEM_DIMENSION_UNIQUE_SETTINGS    = "DIMENSION_UNIQUE_SETTINGS";
	public static String ITEM_DIMENSION_MASTER_UNIQUE_NAME = "DIMENSION_MASTER_UNIQUE_NAME";
	public static String ITEM_DIMENSION_IS_VISIBLE         = "DIMENSION_IS_VISIBLE";
	
	private String restrictionCatalog;
	private String restrictionSchema;
	private String restrictionCube;
	private String restrictionDimensionName;
	private String restrictionDimensionUniqueName;
	private String restrictionCubeSource;
	private String restrictionDimensionVisibility;
		
	private final ArrayList <XMLADimensionInfo> dimensionInfos = 
		new ArrayList <XMLADimensionInfo>();
	private final XMLACubeInfo cube; 
	private final XMLAConnection connection;
	
	public XMLADimensionRequestor(XMLACubeInfo cube, XMLAConnection con) {
		activateItem(ITEM_CATALOG_NAME);                 
		activateItem(ITEM_SCHEMA_NAME);                  
		activateItem(ITEM_CUBE_NAME);                    
		activateItem(ITEM_DIMENSION_NAME);               
		activateItem(ITEM_DIMENSION_UNIQUE_NAME);        
		activateItem(ITEM_DIMENSION_GUID);               
		activateItem(ITEM_DIMENSION_CAPTION);            
		activateItem(ITEM_DIMENSION_ORDINAL);            
		activateItem(ITEM_DIMENSION_TYPE);               
		activateItem(ITEM_DIMENSION_CARDINALITY);        
		activateItem(ITEM_DEFAULT_HIERARCHY);            
		activateItem(ITEM_DESCRIPTION);                  
		activateItem(ITEM_IS_VIRTUAL);                   
		activateItem(ITEM_IS_READWRITE);
		activateItem(ITEM_DIMENSION_UNIQUE_SETTINGS);    
		activateItem(ITEM_DIMENSION_MASTER_UNIQUE_NAME); 
		activateItem(ITEM_DIMENSION_IS_VISIBLE);         		
		this.cube = cube;
		this.connection = con;
	}
		
	public void setCatalogNameRestriction(String catalogName) {
		restrictionCatalog = catalogName;
	}
			
	public void setSchemaNameRestriction(String schemaName) {
		restrictionSchema = schemaName;
	}
	
	public void setCubeNameRestriction(String cubeName) {
		restrictionCube = cubeName;
	}
	
	public void setDimensionNameRestriction(String dimName) {
		restrictionDimensionName = dimName;
	}
	
	public void setDimensionUniqueNameRestriction(String dimName) {
		restrictionDimensionUniqueName = dimName;
	}
	
	public void setCubeSourceRestriction(String cubeName) {
		restrictionCubeSource = cubeName;
	}
	
	public void setDimensionVisibilityRestriction(String dimVisibility) {
		restrictionDimensionVisibility = dimVisibility;
	}
		
	private final XMLARestrictions setRestrictions() {
		XMLARestrictions rest = new XMLARestrictions();

		rest.setCatalog(restrictionCatalog);
		rest.setSchema(restrictionSchema);
		rest.setCubeName(restrictionCube);
		rest.setDimensionName(restrictionDimensionName);
		rest.setDimensionUniqueName(restrictionDimensionUniqueName);
		rest.setCubeSource(restrictionCubeSource);
		rest.setDimensionVisibility(restrictionDimensionVisibility);
		
		return rest;
	}
	
	public XMLADimensionInfo [] requestDimensions(XMLAClient xmlaClient) {
		dimensionInfos.clear();
		
		try {    	    
			XMLARestrictions rest = setRestrictions();
    	    XMLAProperties   prop = new XMLAProperties();

	        String connectionName = xmlaClient.getConnections()[0].getName();
	        prop.setDataSourceInfo(connectionName);
	        prop.setCatalog(cube.getDatabase().getId());
    	    
	        Document result = xmlaClient.getDimensionList(rest, prop);
	        NodeList nl = result.getElementsByTagName("row");
    		    		
			if (nl == null || nl.getLength() == 0) {
				return new XMLADimensionInfo[0];
			}
			parseXMLANodeList(nl, connectionName, xmlaClient);
		} catch (Exception e) {
			e.printStackTrace();
			return new XMLADimensionInfo[0];
		}
		
		return dimensionInfos.toArray(new XMLADimensionInfo[0]);
	}

	protected void parseResult(HashMap <String, String> result, 
			String connectionName, XMLAClient xmlaClient) {
		String name = result.get(ITEM_DIMENSION_CAPTION);
		String id = result.get(ITEM_DIMENSION_UNIQUE_NAME);
		XMLADimensionInfo dimInfo = new XMLADimensionInfo(xmlaClient, name, id, 
				(XMLADatabaseInfo) cube.getDatabase(), cube.getId(), connection);
		dimInfo.setDimensionUniqueName(id);
		try {
			dimInfo.setElementCount(Integer.parseInt(result.get(
					ITEM_DIMENSION_CARDINALITY)));
		} catch (Exception e) {			
		}
		try {
			int type = Integer.parseInt(result.get(ITEM_DIMENSION_TYPE));
			dimInfo.setXmlaType(type == 2 ? XMLADimensionInfo.XMLA_TYPE_MEASURES : 
											XMLADimensionInfo.XMLA_TYPE_NORMAL);
		} catch (Exception e) {			
		}
		
		String ourId = XMLADimensionInfo.getIDString(
				result.get(ITEM_DEFAULT_HIERARCHY),
				cube.getId());
		HierarchyInfo hierarchy =
			connection.getHierarchy(dimInfo, ourId);
		if (hierarchy == null) {
			HierarchyInfo [] allHierarchies = connection.getHierarchies(dimInfo);
			if (allHierarchies != null) {
				for (HierarchyInfo hier: allHierarchies) {
					if (hier != null) {
						hierarchy = hier;
						break;
					}
				}
			}
		}
		dimInfo.setDefaultHierarchy((XMLAHierarchyInfo) hierarchy);
		dimInfo.setHierarchyUniqueName(hierarchy.getId());
		dimensionInfos.add(dimInfo);
	}	
}
