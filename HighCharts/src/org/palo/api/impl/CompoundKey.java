/*
 * (c) Tensegrity Software 2005. All rights reserved.
 */
package org.palo.api.impl;

/**
 * <code>CompoundKey</code>
 *
 * @author Stepan Rutz
 * @version $Id: CompoundKey.java,v 1.4 2007/11/25 19:00:28 ArndHouben Exp $
 */
public class CompoundKey {
	private final Object objs[];

	public CompoundKey(Object objs[]) {
		this.objs = objs;
	}

	//-------------------------------------------------------------------------
	// overrides

	public final int hashCode() {
		int hc = 23;
		for (int i = 0; i < objs.length; ++i) {
			if(objs[i] != null)
				hc += 37 * objs[i].hashCode();
		}
		return hc;
	}

	public final boolean equals(Object obj) {
		if (!(obj instanceof CompoundKey))
			return false;

		CompoundKey other = (CompoundKey) obj;

		if (objs.length != other.objs.length)
			return false;

		for (int i = 0; i < objs.length; ++i) {
			if(objs[i] == null && other.objs[i] != null)
				return false;
				
			if (!objs[i].equals(other.objs[i]))
				return false;
		}

		return true;
	}
}
