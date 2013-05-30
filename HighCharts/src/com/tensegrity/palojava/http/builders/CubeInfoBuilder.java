package com.tensegrity.palojava.http.builders;

import java.math.BigInteger;

import com.tensegrity.palojava.CubeInfo;
import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.impl.CubeInfoImpl;

public class CubeInfoBuilder {

	CubeInfoBuilder() {
		//package visibility only...
	}

	public final CubeInfo create(PaloInfo parent,String[] response) {
		if(response.length<8)
			throw new PaloException("CubeInfoBuilder: not enough information to create CubeInfo!!");
		
//		try {
			
			String id = response[0];			
			String[] dims = BuilderUtils.getIDs(response[3]);
			int type = Integer.parseInt(response[7]);
			CubeInfoImpl info = new CubeInfoImpl((DatabaseInfo)parent,id,type,dims);
			update(info,response);
			return info;
//		} catch(RuntimeException e) {
//			throw new PaloException(e.getLocalizedMessage(),e);
//		}
	}
	
	public final void update(CubeInfoImpl cube, String[] response) {
		if(response.length<8)
			throw new PaloException("Not enough information to update CubeInfo!!");
		
		try {
			String name = response[1];
			int dimCount = Integer.parseInt(response[2]);
			BigInteger cellCount = new BigInteger(response[4]);
			BigInteger filledCellCount = new BigInteger(response[5]);
//			int cellCount = Integer.parseInt(response[4]);
//			int filledCellCount = Integer.parseInt(response[5]);
			int status = Integer.parseInt(response[6]);
			int token = Integer.parseInt(response[8]);
			//now set the rest:
			cube.setName(name);
			cube.setDimensionCount(dimCount);
			cube.setCellCount(cellCount);
			cube.setFilledCellCount(filledCellCount);
			cube.setStatus(status);
			cube.setToken(token);
		} catch(RuntimeException e) {
			throw new PaloException(e.getLocalizedMessage(),e);
		}
	}
}
