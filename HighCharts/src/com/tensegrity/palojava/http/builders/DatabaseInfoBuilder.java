package com.tensegrity.palojava.http.builders;

import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.impl.DatabaseInfoImpl;


public class DatabaseInfoBuilder  {

	DatabaseInfoBuilder() {
		//package visibility only...
	}

	public final DatabaseInfo create(PaloInfo parent,String[] response) {
		if(response.length<6)
			throw new PaloException("Not enough information to create DatabaseInfo!!");
		
//		try {
			String id = response[0];
			int type = Integer.parseInt(response[5]);
			DatabaseInfoImpl info = new DatabaseInfoImpl(id,type);
			update(info,response);
			return info;
//		}catch(RuntimeException e) {
//			throw new PaloException(e.getLocalizedMessage(),e);
//		}
	}
	
	public final void update(DatabaseInfoImpl database, String[] response) {
		if(response.length<6)
			throw new PaloException("Not enough information to update DatabaseInfo!!");
		
		try {
			String name = response[1];
			int dimCount = Integer.parseInt(response[2]);
			int cubeCount = Integer.parseInt(response[3]);
			int status = Integer.parseInt(response[4]);
			int token = Integer.parseInt(response[6]);
			
			database.setName(name);
			database.setDimensionCount(dimCount);
			database.setCubeCount(cubeCount);
			database.setStatus(status);			
			database.setToken(token);
		}catch(RuntimeException e) {
			throw new PaloException(e.getLocalizedMessage(),e);
		}
	}
}
