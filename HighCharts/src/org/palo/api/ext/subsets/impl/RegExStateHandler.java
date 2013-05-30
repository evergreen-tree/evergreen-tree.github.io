/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.subsets.impl;

import org.palo.api.Dimension;
import org.palo.api.DimensionFilter;
import org.palo.api.Element;
import org.palo.api.ElementNode;
import org.palo.api.Hierarchy;
import org.palo.api.HierarchyFilter;
import org.palo.api.ext.subsets.states.RegExState;

/**
 * The <code>RegExStateHandler</code> is a default implementation to handle
 * the {@link RegExState} which uses a regular expression to determine the
 * visible elements.
 * 
 * @author ArndHouben
 * @version $Id: RegExStateHandler.java,v 1.4 2008/04/15 08:20:57 PhilippBouillon Exp $
 */
class RegExStateHandler extends AbstractStateHandler {

	public final boolean isFlat() {
		return false;
	}

	public final DimensionFilter createDimensionFilter(Dimension dimension) {
		return new DimensionFilter() {
			public void init(Dimension dimension) {
			}

			public boolean acceptElement(Element element) {
				if (subsetState.getSearchAttribute() != null) {
					return element.getAttributeValue(
							subsetState.getSearchAttribute()).toString()
							.matches(subsetState.getExpression());
				} else {
					return element.getName().matches(
							subsetState.getExpression());
				}
			}

			public boolean isFlat() {
				return false;
			}

			public ElementNode[] postprocessRootNodes(ElementNode[] rootNodes) {
				return null;
			}
		};
	}

	public HierarchyFilter createHierarchyFilter(Hierarchy hierarchy) {
		return new HierarchyFilter() {
			public void init(Hierarchy hierarchy) {
			}

			public boolean acceptElement(Element element) {
				if (subsetState.getSearchAttribute() != null) {
					return element.getAttributeValue(
							subsetState.getSearchAttribute()).toString()
							.matches(subsetState.getExpression());
				} else {
					return element.getName().matches(
							subsetState.getExpression());
				}
			}

			public boolean isFlat() {
				return false;
			}

			public ElementNode[] postprocessRootNodes(ElementNode[] rootNodes) {
				return null;
			}
		};
	}

}
