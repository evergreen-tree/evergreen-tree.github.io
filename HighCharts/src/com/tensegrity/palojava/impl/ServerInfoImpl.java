/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.impl;

import com.tensegrity.palojava.ServerInfo;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author Arnd Houben
 * @version $Id: ServerInfoImpl.java,v 1.5 2008/05/05 15:58:14 ArndHouben Exp $
 */
public class ServerInfoImpl implements ServerInfo {

	private final boolean isLegacy;
	private final int bugfixVersion;
	private final int buildNumber;
	private final int majorNumber;
	private final int minorNumber;
	private final int httpsPort;
	private final int encryption;
	private final String name;
	private final String serverType;
//	private final String version;
	
	public ServerInfoImpl(int buildNumber, int bugfixNumber, int majorNumber,
			int minorNumber, int httpsPort, int encryption, boolean isLegacy) {
		this.isLegacy = isLegacy;
		this.buildNumber = buildNumber;
		this.bugfixVersion = bugfixNumber;
		this.majorNumber = majorNumber;
		this.minorNumber = minorNumber;
		this.httpsPort = httpsPort;
		this.encryption = encryption;
		this.name = "PaloServer";
		this.serverType = "Palo";
//		this.version = majorNumber + "." + minorNumber;
	}
	
	public int getBugfixVersion() {
		return bugfixVersion;
	}

	public int getBuildNumber() {
		return buildNumber;
	}

	public int getMajor() {
		return majorNumber;
	}

	public int getMinor() {
		return minorNumber;
	}

	public boolean isLegacy() {
		return isLegacy;
	}

	public String getId() {
		return Integer.toString(buildNumber);
	}

	public int getType() {
		//we are palo server...
		return 2;	//TODO Connection.TYPE_HTTP should be defined in this package...
	}

	public boolean canBeModified() {
		return false;
	}

	public boolean canCreateChildren() {
		return false;
	}

	public int getEncryption() {
		return encryption;
	}

	public int getHttpsPort() {
		return httpsPort;
	}

	public String getName() {
		return name;
	}

	public String getServerType() {
		return serverType;
	}

	public String getVersion() {
		StringBuffer vStr = new StringBuffer(majorNumber);
		vStr.append(".");
		vStr.append(minorNumber);
		return vStr.toString(); //version;
	}

	public String[] getProperties() {
		return new String[0];
	}
}
