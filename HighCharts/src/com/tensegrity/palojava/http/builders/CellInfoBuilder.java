/*
 * (c) 2007 Tensegrity Software GmbH
 * All rights reserved
 */
package com.tensegrity.palojava.http.builders;

import com.tensegrity.palojava.CellInfo;
import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.impl.CellInfoImpl;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author ArndHouben
 * @version $Id: CellInfoBuilder.java,v 1.4 2008/03/04 08:50:37 ArndHouben Exp $
 */
public class CellInfoBuilder {

	CellInfoBuilder() {
		//package visibility only...
	}
	
	public CellInfo create(PaloInfo parent,String[] response) {
		if(response.length<3)
			throw new PaloException("Not enough information to create CellInfo!!");
		
//		try {
			int type = Integer.parseInt(response[0]);
			boolean exists = Boolean.getBoolean(response[1]);
			Object value = response[2];
			if(type == CellInfo.TYPE_NUMERIC && !value.equals("")) 
				value = new Double(response[2]);
			if(response.length>3) {
				String[] pathIds = BuilderUtils.getIDs(response[3]);
				return new CellInfoImpl(type,exists,value,pathIds);
			}
			return new CellInfoImpl(type,exists,value,null);
//		}catch(RuntimeException e) {
//			throw new PaloException(e.getLocalizedMessage(),e);
//		}
	}

}
