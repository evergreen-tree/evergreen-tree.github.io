/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package com.tensegrity.palojava;


/**
 * <code>PaloConstants</code> defines some system wide used constants for the
 * palo http server which are used by the {@link DatabaseInfo}, 
 * {@link DimensionInfo} and {@link CubeInfo} 
 * 
 * @author Arnd Houben
 * @version $Id: PaloConstants.java,v 1.3 2008/01/23 14:08:10 PhilippBouillon Exp $
 */
public interface PaloConstants {
	
	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_SYSTEM = 1;
	public static final int TYPE_ATTRIBUTE = 2;
	public static final int TYPE_INFO = 3;
}
