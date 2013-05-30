/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava.http.builders;

/**
 * <code></code>
 * TODO DOCUMENT ME
 *
 * @author Arnd Houben
 * @version $Id: BuilderUtils.java,v 1.1 2007/04/11 16:45:38 ArndHouben Exp $
 */
public class BuilderUtils {

	static final String[] getIDs(String idStr) {
		String[] ids = idStr.split(",");
		if(ids.length==1 && ids[0].equals(""))
			return new String[0];
		return ids;
	}

	static final double[] getWeights(String weightStr) {
		String[] weights = weightStr.split(",");
		if(weights.length == 1 && weights[0].equals(""))
			return new double[0];
		double[] w = new double[weights.length];
		for(int i=0;i<w.length;++i) {
			w[i] = Double.parseDouble(weights[i]);
		}
		return w;
	}

}
