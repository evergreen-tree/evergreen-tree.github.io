/*
 * (c) Tensegrity Software 2008
 * All rights reserved
 */
package org.palo.api.subsets.filter;

import org.palo.api.Element;
import org.palo.api.subsets.Subset2;

/**
 * <code>IndentComparator</code>
 * TODO DOCUMENT ME
 *
 * @author ArndHouben
 * @version $Id: IndentComparator.java,v 1.1 2008/05/08 10:13:14 ArndHouben Exp $
 **/
class IndentComparator {

	private final Subset2 subset;
	
	IndentComparator(Subset2 subset) {
		this.subset = subset;
	}

	/**
	 * Compares the level of given {@link Element} with the specified one.
	 * The result depends of the indent setting of the currently used subset.
	 * @param el
	 * @param level
	 * @return  a negative integer, zero, or a positive integer as this element
     *		has a less than, equal to, or greater level than the specified one.
	 */
	public int compare(Element el, int level) {
		if(subset == null)
			return el.getLevel();
		
		int elLevel = -1;
		// get indent of subset:
		switch (subset.getIndent()) {
//		case 0: //NONE
//		case 1: //INDENT
//			int indent = el.getDepth() + 1;
//			return compare(indent, level);
		case 2: //LEVEL
			elLevel = el.getLevel();
			break;
		case 3: //DEPTH
			elLevel = el.getDepth();
			break;
		default: //NONE or //INDENT => "0" or "1"
			elLevel = el.getDepth() + 1;
		}
		return compare(elLevel, level);
	}
	
	private final int compare(int l1, int l2) {
		if(l1<l2)
			return -1;
		else if(l1>l2)
			return 1;
		return 0;
	}
}
