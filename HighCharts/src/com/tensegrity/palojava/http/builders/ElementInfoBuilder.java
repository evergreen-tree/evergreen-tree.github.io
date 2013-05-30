package com.tensegrity.palojava.http.builders;

import com.tensegrity.palojava.DimensionInfo;
import com.tensegrity.palojava.ElementInfo;
import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.impl.ElementInfoImpl;


public class ElementInfoBuilder {
	
	ElementInfoBuilder() {
		//package visibility only...
	}

	public final ElementInfo create(PaloInfo parent,String[] response) {
		if(response.length<12) {
			throw new PaloException("Not enough information to create ElementInfo!!");
		}
		try {
			String id = response[0];
			
			ElementInfoImpl info = 
				new ElementInfoImpl((DimensionInfo)parent,id);
			update(info,response);
			return info;
		}catch(RuntimeException e) {
			throw new PaloException(e.getLocalizedMessage(),e);
		}
	}

	public final void update(ElementInfoImpl element, String[] response) {
		if(response.length<12) {
			throw new PaloException("Not enough information to update ElementInfo!!");
		}

		String name = response[1];
		int position = Integer.parseInt(response[2]);
		int level = Integer.parseInt(response[3]);
		int indent = Integer.parseInt(response[4]);
		int depth = Integer.parseInt(response[5]);
		int type = Integer.parseInt(response[6]);
//		int parentCount = Integer.parseInt(response[7]);
		String[] parentIds = BuilderUtils.getIDs(response[8]);
		int childrenCount = Integer.parseInt(response[9]);
		String[] childrenIds;
		double[] weights;
		if (childrenCount == 0) {
			childrenIds = new String[0];
			weights = new double[0];
		} else {
			childrenIds = BuilderUtils.getIDs(response[10]);
			weights = BuilderUtils.getWeights(response[11]);
		}
		element.setName(name);
		element.setType(type);
		element.setPosition(position);
		element.setLevel(level);
		element.setIndent(indent);
		element.setDepth(depth);
//		element.setParentCount(parentCount);
		element.setParents(parentIds);
//		element.setChildrenCount(childrenCount);
		element.setChildren(childrenIds,weights);
	}
}
