/*
 * (c) Tensegrity Software 2008
 * All rights reserved
 */
package org.palo.api.impl;

import org.palo.api.Cell;
import org.palo.api.Lock;

import com.tensegrity.palojava.LockInfo;

/**
 * <code>LockImpl</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: LockImpl.java,v 1.1 2008/03/04 08:53:22 ArndHouben Exp $
 **/
class LockImpl implements Lock {

	private final LockInfo lockInfo;
	
	LockImpl(LockInfo lockInfo) {
		this.lockInfo = lockInfo;
	}
	
	
	public final Cell[] getArea() {
		// TODO Auto-generated method stub
		return null;
	}

	public final String getId() {
		return lockInfo.getId();
	}

	public final int getSteps() {
		return lockInfo.getSteps();
	}
	
	final LockInfo getInfo() {
		return lockInfo;
	}

	public final boolean equals(Object obj) {
		if(obj instanceof Lock) {
			Lock other = (Lock) obj;
			return getId().equals(other.getId());			
		}
		return false;
	}
	
	public final int hashCode() {
		return lockInfo.hashCode();
	}
}
