package com.tensegrity.palo.xmla;

import java.util.LinkedHashMap;
import java.util.Map;

import com.tensegrity.palo.xmla.parsers.XMLACubeRequestor;
import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.DbConnection;
import com.tensegrity.palojava.PropertyInfo;

public class XMLADatabaseInfo implements DatabaseInfo, XMLAPaloInfo {
	private String name;
	private String id;		
	private int dimensionCount = 0;
	private int cubeCount;
	private final Map cubes;
	private boolean dimensionCountSet = false;
	private boolean cubeCountSet = false;
	private final XMLAConnection connection;
	
	public XMLADatabaseInfo(XMLAConnection connection, String name) {
		this.name = name;
		// TODO Does this work? Are database names unique for every connection?
		// Probably not!
		this.id = name;
		cubes = new LinkedHashMap();
		this.connection = connection;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public int getCubeCount() {
		if (!cubeCountSet) {
			cubeCountSet = true;
			XMLACubeRequestor req = new XMLACubeRequestor(connection, this);
		}
		return cubeCount;
	}

	public int getDimensionCount() {
		return dimensionCount;
	}

	public final void setCubeCount(int newCubeCount) {
		cubeCount = newCubeCount;
	}
	
	public final void setDimensionCount(int newDimensionCount) {
		dimensionCount = newDimensionCount;
	}
	
	public String getName() {
		return name;
	}

	public int getStatus() {
		return STATUS_LOADED;
	}

	public int getToken() {
		return 0;
	}

	public String getId() {
		return id;
	}

	public int getType() {
		return TYPE_NORMAL;
	}
		
	public String toString() {
		return "Database " + name + " [" + id + "]. Cubes: " + getCubeCount() + 
		       ", Dimensions: " + getDimensionCount() + ", Status: " + getStatus() + 
		       ", Token: " + getToken() + ", Type: " + getType(); 
	}
	
	public void addCubeInternal(XMLACubeInfo cube) {		
		cubes.put(cube.getId(), cube);
	}
	
	public XMLACubeInfo getCubeInternal(String cubeName) {
		return (XMLACubeInfo) cubes.get(cubeName);
	}
	
	public int getCubeCountInternal() {
		return cubes.size();
	}
			
	public XMLACubeInfo [] getCubesInternal() {
		return (XMLACubeInfo []) cubes.values().toArray(new XMLACubeInfo[0]);
	}
	
	public boolean isSystem() {
		return false;
	}

	public String[] getAllKnownPropertyIds(DbConnection con) {
		return new String[0];
	}

	public PropertyInfo getProperty(DbConnection con, String id) {
		return null;
	}

	public boolean canBeModified() {
		return false;
	}

	public boolean canCreateChildren() {
		return false;
	}	
}
