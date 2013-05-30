/*
 * (c) Tensegrity Software 2008
 * All rights reserved
 */
package com.tensegrity.palojava.http.builders;

import com.tensegrity.palojava.LockInfo;
import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.impl.LockInfoImpl;

/**
 * <code>LockInfoBuilder</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: LockInfoBuilder.java,v 1.1 2008/03/04 08:50:53 ArndHouben Exp $
 **/
public class LockInfoBuilder {

	LockInfoBuilder() {
		//package visibility only...
	}
	
	public final LockInfo create(PaloInfo parent,String[] response) {
		if(response.length<4) {
			throw new PaloException("Not enough information to create LockInfo!!");
		}
		try {
			String id = response[0];
			String user = response[2];
			LockInfoImpl lock = new LockInfoImpl(id,user);
			setArea(lock, response[1]);
			if(response[3].length()>0)
				lock.setSteps(Integer.parseInt(response[3]));
			return lock;
		}catch(RuntimeException e) {
			throw new PaloException(e.getLocalizedMessage(),e);
		}
	}
	
	private final void setArea(LockInfoImpl lock, String response) {
		String[] elements = response.split(",");
		String[][] area = new String[elements.length][];
		for(int i=0;i<area.length;++i) {
			area[i] = elements[i].split(":");
		}
		lock.setArea(area);
	}
}
