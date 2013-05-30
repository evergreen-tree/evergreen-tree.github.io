package com.tensegrity.palo.xmla;

import com.tensegrity.palojava.ServerInfo;

public class XMLAServerInfo implements ServerInfo {
	private String id;
	private String name;
	private String description;
	private String url;
	private String authentication;
	
	XMLAServerInfo(String name) {
		this.id = name;
		this.name = name;
	}
	
	public int getBugfixVersion() {
		return 0;
	}

	public int getBuildNumber() {
		return 0;
	}

	public int getMajor() {
		return 4;
	}

	public int getMinor() {
		return 0;
	}

	public boolean isLegacy() {
		return false;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public int getType() {
		return 3;
	}
	
	public String getDescription() {
		return description;	
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public boolean canBeModified() {
		return false;
	}

	public boolean canCreateChildren() {
		return false;
	}

	public int getEncryption() {
		return 0;
	}

	public int getHttpsPort() {
		return 0;
	}

	public String getServerType() {
		return "XMLA";
	}

	public String getVersion() {
		// TODO implement getVersion
		return "0";
	}

	public String [] getProperties() {
		return new String[] {"SecurityInfoProperty", authentication,
				             "DescriptionProperty", getDescription()};
	}
}
