package com.tensegrity.palojava.http.builders;

import com.tensegrity.palojava.DatabaseInfo;
import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.impl.DimensionInfoImpl;

public class DimensionInfoBuilder {

//	0  	dimension  	identifier  	Identifier of the dimension
//	1 	name_dimension 	string 	Name of the dimension
//	2 	number_elements 	integer 	Number of elements
//	3 	maximum_level 	integer 	Maximum level of the dimension
//	4 	maximum_indent 	integer 	Maximum indent of the dimension
//	5 	maximum_depth 	integer 	Maximum depth of the dimension
//	6 	type 	integer 	Type of dimension (0=normal, 1=system, 2=attribute)
//	7 	attributes_dimension 	identifier 	Identifier of the attributes dimension of a normal dimension or the identifier of the normal dimension associated to a attributes dimension.
//	8 	attributes_cube 	identifier 	Identifier of the attributes cube. (only for normal dimensions)
//	9 	rights_cube 	identifier 	Identifier of the rights cube. (only for normal dimensions)
//	10 	dimension_token 	integer 	The dimension token of the dimension

	DimensionInfoBuilder() {
		//package visibility only...
	}

	
	public final DimensionInfo create(PaloInfo parent,String[] response) {
		if(response.length<11) {
			throw new PaloException("Not enough information to create DimensionInfo!!");
		}
		try {
			String id = response[0];
			int type = Integer.parseInt(response[6]);
			DimensionInfoImpl info = 
				new DimensionInfoImpl((DatabaseInfo)parent,id,type);
			update(info,response);
//			String name = response[1];
//			int elCount = Integer.parseInt(response[2]);
//			int maxLevel = Integer.parseInt(response[3]);
//			int maxIndent = Integer.parseInt(response[4]);
//			int maxDepth = Integer.parseInt(response[5]);
//			int type = Integer.parseInt(response[6]);
//			String attrDimId = response[7];
//			String attrCubeId = response[8];
//			String rightsCubeId = response[9];
//			int token = Integer.parseInt(response[10]);
//			DimensionInfoImpl info = 
//				new DimensionInfoImpl((DatabaseInfo)parent,id,name,type);
//			info.setElementCount(elCount);
//			info.setMaxLevel(maxLevel);
//			info.setMaxIndent(maxIndent);
//			info.setMaxDepth(maxDepth);
//			info.setAttributeDimension(attrDimId);
//			info.setAttributeCube(attrCubeId);
//			info.setRightsCube(rightsCubeId);
//			info.setToken(token);
			return info;
		}catch(RuntimeException e) {
			throw new PaloException(e.getLocalizedMessage(),e);
		}		
	}
	
	/**
	 * Updates the given dimension with the given response values. Only the
	 * dimension id and its type are not changed. 
	 * @param dimension
	 * @param response
	 */
	public final void update(DimensionInfoImpl dimension, String[] response) {
		if(response.length<11) {
			throw new PaloException("Not enough information to update DimensionInfo!!");
		}
		String name = response[1];
		int elCount = Integer.parseInt(response[2]);
		int maxLevel = Integer.parseInt(response[3]);
		int maxIndent = Integer.parseInt(response[4]);
		int maxDepth = Integer.parseInt(response[5]);
		String attrDimId = response[7];
		String attrCubeId = response[8];
		String rightsCubeId = response[9];
		int token = Integer.parseInt(response[10]);
		//update:
		dimension.setName(name);
		dimension.setElementCount(elCount);
		dimension.setMaxLevel(maxLevel);
		dimension.setMaxIndent(maxIndent);
		dimension.setMaxDepth(maxDepth);
		dimension.setAttributeDimension(attrDimId);
		dimension.setAttributeCube(attrCubeId);
		dimension.setRightsCube(rightsCubeId);
		dimension.setToken(token);
	}
}
