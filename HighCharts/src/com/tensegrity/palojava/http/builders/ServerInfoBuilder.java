/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.http.builders;

import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.ServerInfo;
import com.tensegrity.palojava.impl.ServerInfoImpl;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author Arnd Houben
 * @version $Id: ServerInfoBuilder.java,v 1.2 2008/02/06 15:09:48 PhilippBouillon Exp $
 */
public class ServerInfoBuilder {

	ServerInfoBuilder() {
		//package visibility only...
	}

	public ServerInfo create(PaloInfo parent, String[] response) throws PaloException {
		if(response.length<4)
			throw new PaloException("Not enough information to create ServerInfo!!");
		
		try {
			int major = Integer.parseInt(response[0]);
			int minor = Integer.parseInt(response[1]);
			int bugfix = Integer.parseInt(response[2]);
			int build = Integer.parseInt(response[3]);			
			int encryption = 2; 			
			int httpsPort = 0;
			if (response.length > 4) encryption = Integer.parseInt(response[4]);
			if (response.length > 5) httpsPort = Integer.parseInt(response[5]);
			return new ServerInfoImpl(build,bugfix,major,minor,httpsPort,encryption,false);
		}catch(Exception e) {
			throw new PaloException(e.getLocalizedMessage(),e);
		}
	}

}
