/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.impl;

import org.palo.api.persistence.PersistenceError;


/**
 * <code>PersistenceErrorImpl</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: PersistenceErrorImpl.java,v 1.3 2007/05/21 12:04:59 ArndHouben Exp $
 **/
public class PersistenceErrorImpl implements PersistenceError {
	
	private final Object src; 
	private final String msg;
	private final String srcId;
	private final Object location;
	private final String cause;
	private final int type;
	private final Object section;
	private final int sectionType;
	
	public PersistenceErrorImpl(String msg, String srcId, Object src, Object location, String cause, int type, Object section, int sectionType) {
		this.msg = msg;
		this.src = src;
		this.srcId = srcId;
		this.cause = cause;
		this.location = location;
		if(!typeIsOk(type))
			type = UNKNOWN_ERROR;
		this.type = type;
		this.section = section;
		this.sectionType = sectionType;
	}

	public final String getSourceId() {
		return srcId;
	}
	
	public final String getCause() {
		return cause;
	}
	
	public final Object getLocation() {
		return location;
	}

	public final String getMessage() {
		return msg;
	}

	public final int getType() {
		return type;
	}

	public final Object getSource() {
		return src;
	}
	
	public final Object getSection() {
		return section;
	}
	
	public int getTargetType() {
		return sectionType;
	}

	
	private final boolean typeIsOk(int type) {
		for(int i=0;i<ALL_ERROR_TYPES.length;++i)
			if(type == ALL_ERROR_TYPES[i])
				return true;
		return false;
	}
}
