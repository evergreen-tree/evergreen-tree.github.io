/**
 * (c) Copyright 2006 Tensegrity Software
 * All rights reserved.
 */
package com.tensegrity.palojava.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.tensegrity.palojava.PaloException;

/**
 * <p>
 * Registration of all known error codes which are defined by the palo server
 * </p>
 *
 * @author ArndHouben
 * @version $Id: PaloErrorCodes.java,v 1.1 2007/04/11 16:45:38 ArndHouben Exp $
 */
public final class PaloErrorCodes {
	
	private static final ArrayList codes = new ArrayList();
	static {
		//read in errorCodes.txt
		InputStream is = 
			PaloErrorCodes.class.getResourceAsStream("resources/errorCodes.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		try {
			while((line=reader.readLine())!=null) {
				//everything between = and ',' is an error code
				int start = line.indexOf('=')+1;
				int end = line.indexOf(',');
				if(start<end) {
					String errCode = line.substring(start,end);
					codes.add(errCode.trim());
				}
			}
		} catch (IOException e) {
			throw new PaloException("Could not read in error codes!!",e);
		}
	}
	
	/**
	 * Checks if the given code is an error code
	 * @param code a possible error code
	 * @return true if the given code is an error code, false if not
	 */
	static final boolean contains(String errorCode) {
		return codes.contains(errorCode);
	}
}

