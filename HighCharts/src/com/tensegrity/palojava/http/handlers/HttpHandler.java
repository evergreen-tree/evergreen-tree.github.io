/*
 * (c) 2007 Tensegrity Software GmbH
 * All rights reserved
 */
package com.tensegrity.palojava.http.handlers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URLEncoder;

import com.tensegrity.palojava.PaloException;
import com.tensegrity.palojava.PaloInfo;
import com.tensegrity.palojava.http.HttpConnection;
import com.tensegrity.palojava.http.HttpParser;

/**
 * <code></code>
 * TODO DOCUMENT ME
 * 
 * @author ArndHouben
 * @version $Id$
 */
class HttpHandler {
	
	private static final String CHARACTER_ENCODING="UTF-8";
	private static final String ID_PATH_DELIM = ",";
	private static final String PATH_DELIM = ":";
	private static final int PARAMETER_THRESHOLD = 1024;	//1kBytes to split request
	
	private static final String GET = "GET ";
	private static final String POST = "POST ";
	private static final String HTTP_VERSION = " HTTP/1.1";
	private static final String LINE_END = "\r\n";
	private static final String SID = "&sid=";
	
	//parameter-PREFIXES:
	/** the cube parameter prefix, value: <code>&cube=</code> */
	protected static final String CUBE_PREFIX = "&cube=";
	protected static final String SYSTEM_PREFIX = "#_";
	protected static final String SYSTEM_POSTFIX = "_";
	
	protected static final String OK = "1";	//ok response

	
	protected HttpConnection connection;
	
	final synchronized void use(HttpConnection connection) {
		this.connection = connection;
	}
	
	
	/**
	 * Requests the given query. By default the http 'GET' method is used and
	 * the current session id is appended. 
	 * @param query the http query
	 * @return the decoded server response
	 * @throws IOException if an I/O exception occurs
	 */
	protected final String[][] request(String query)
			throws ConnectException, IOException {
		return request(query, false, false);
	}
	
	/**
	 * Requests the given query.
	 * @param query the http query
	 * @param doPost set to true to use the http 'POST' method instead of the
	 * http 'GET' method
	 * @param skipSid set to true if the current session id is not required for
	 * the request
	 * @return the decoded server response
	 * @throws IOException if an I/O exception occurs
	 */
	protected final synchronized String[][] request(String query, boolean doPost,
			boolean skipSid) throws ConnectException, IOException {
		//check if we have to perform a post request:
		if(query.getBytes().length > PARAMETER_THRESHOLD)
			doPost = true;
		
		//check if we have sid:	
		String sid = connection.getSID();
		if(!skipSid && (sid==null || sid.equals("")))
			throw new PaloException("No session id defined!!");		
		StringBuffer request = new StringBuffer();
		if(doPost)
			request.append(POST);
		else
			request.append(GET);		
		request.append(query);
		if(!skipSid) {
			request.append(SID);
			request.append(sid);
		}
		request.append(HTTP_VERSION);
		request.append(LINE_END);
		String[] response = connection.send(request.toString());
		return parse(response);
	}

	/**
	 * Encodes the given parameter value as csv . A numeric value is represented 
	 * as string and a string value is quoted.
	 * @param paramValue the parameter value to encode
	 * @return the csv encoded parameter value string
	 */
	protected final String encode(String paramValue) {
		try {
			return URLEncoder.encode(paramValue,CHARACTER_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return paramValue;
	}
	
	
	/**
	 * Encodes the given values. The result is a colon separated list of url 
	 * encoded values
	 * @param paramValue
	 * @return
	 */
	protected final String encode(Object[] paramValue) {
		StringBuffer res = new StringBuffer();
		int max = paramValue.length-1;		
		for(int i=0;i<paramValue.length;i++) {
			res.append(encode(paramValue[i].toString())); //,true));
			if(i<max)
				res.append(":");
		}
		return res.toString();
	}

	/**
	 * Returns an id string 
	 * @param infos
	 * @return
	 */
	protected final String getIdString(PaloInfo[] infos) {
		return getIdString(infos, ID_PATH_DELIM);
	}

	protected final String getIdString(PaloInfo[] infos,String idDelimeter) {
		StringBuffer idStr = new StringBuffer();
		int lastId = infos.length - 1;
		for(int i=0;i<infos.length;++i) {
			idStr.append(infos[i].getId());
			if(i<lastId)
				idStr.append(idDelimeter);
		}
		return idStr.toString();
	}

	protected final String getIdString(String[] ids) {
		StringBuffer idStr = new StringBuffer();
		int lastId = ids.length - 1;
		for(int i=0;i<ids.length;++i) {
			idStr.append(ids[i]);
			if(i<lastId)
				idStr.append(ID_PATH_DELIM);
		}
		return idStr.toString();
	}
	
	/**
	 * Returns coordinate strings defined by the given coords parameters
	 * @param coords
	 * @return
	 */
	protected final String getPaths(PaloInfo[][] coords) {
		return getPaths(coords, PATH_DELIM);		
	}

	protected final String getPaths(PaloInfo[][] coords,String pathDelim) {
		StringBuffer pathStr = new StringBuffer();
		int lastCoordinate = coords.length - 1;
		for(int i=0;i<coords.length;++i) {
			pathStr.append(getIdString(coords[i]));
			if(i<lastCoordinate)
				pathStr.append(pathDelim);
		}
		return pathStr.toString();		
	}

	protected final String getWeightString(double[] weights) {
		StringBuffer weightStr = new StringBuffer();
		int lastWeight = weights.length - 1;
		for(int i=0;i<weights.length;++i) {
			weightStr.append(weights[i]);
			if(i<lastWeight)
				weightStr.append(ID_PATH_DELIM);
		}
		return weightStr.toString();
	}
	
	//--------------------------------------------------------------------------
	// PRIVATE METHODS
	//
	private final String[][] parse(String[] response) {
		if(response == null)
			return new String[0][];
		
		String[][] res = new String[response.length][];
		for(int i=0;i<response.length;++i) {
			String resp = response[i];
			res[i] = HttpParser.parseLine(resp,';');
			HttpParser.checkResponse(res[i]);
		}
		return res;
	}

}