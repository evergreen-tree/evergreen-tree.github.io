/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.utils;

import java.util.ArrayList;
import java.util.List;

import org.palo.api.Element;

/**
 * <code>ElementUtilities</code>
 * <p>
 * Some useful methods for dealing with <code>{@link Element}s</code>
 * </p>
 *
 * @author ArndHouben
 * @version $Id: ElementUtilities.java,v 1.2 2007/05/31 12:58:01 ArndHouben Exp $
 **/
public class ElementUtilities {

	/**
	 * Returns all paths of given <code>{@link Element}</code> instance. Since
	 * an element can be used several times the returned path string can contain
	 * several paths too. A path consist of comma separated element ids and each
	 * path is colon separated, e.g. 1,2:3,2 meaning the element with id 2 has
	 * to parents, namely one with id 1 and a second one with id 3. 
	 * @param element <code>{@link Element}</code> instance to determine the 
	 * paths of
	 * @return colon separated paths of comma separated element ids 
	 */
	public static final String getPaths(Element element) {
		ArrayList paths = new ArrayList();
		collectPaths(element, element.getId(), paths);
		StringBuffer pathsStr = new StringBuffer();
		int lastPath = paths.size()-1;
		for(int i=0,n=paths.size();i<n;++i) {
			pathsStr.append(paths.get(i));
			if(i<lastPath)
				pathsStr.append(":");
		}
		return pathsStr.toString();
	}
	
	private static final void collectPaths(Element element, String part,
			List paths) {
		Element[] parents = element.getParents();
		if (parents == null || parents.length == 0) {
			paths.add(part);
			return;
		}

		for (int i = 0; i < parents.length; ++i) {
			StringBuffer newPath = new StringBuffer(part);
			newPath.insert(0, ElementPath.ELEMENT_DELIM);
			newPath.insert(0, parents[i].getId());
			collectPaths(parents[i], newPath.toString(), paths);
		}
	}

}
