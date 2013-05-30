/*
 * (c) Tensegrity Software 2007
 * All rights reserved
 */
package org.palo.api.ext.subsets.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.palo.api.Dimension;
import org.palo.api.DimensionFilter;
import org.palo.api.Element;
import org.palo.api.ElementNode;
import org.palo.api.Hierarchy;
import org.palo.api.HierarchyFilter;
import org.palo.api.ext.subsets.states.HierarchicalState;

/**
 * The <code>HierarchicalStateHandler</code> is a default implementation to 
 * handle the {@link HierarchicalState}.
 * 
 * @author ArndHouben
 * @version $Id: HierarchicalStateHandler.java,v 1.4 2008/04/15 08:20:57 PhilippBouillon Exp $
 */
class HierarchicalStateHandler extends AbstractStateHandler {

	public final boolean isFlat() {
		return false;
	}

	public final DimensionFilter createDimensionFilter(Dimension dimension) {
		return new DimensionFilter() {
			Set elementSet;
			{
				elementSet = new HashSet();
				elementSet.addAll(Arrays.asList(subsetState
						.getVisibleElements()));
			}

			public void init(Dimension dimension) {
			}

			public boolean acceptElement(Element element) {
				return elementSet.contains(element);
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
			Set elementSet;
			{
				elementSet = new HashSet();
				elementSet.addAll(Arrays.asList(subsetState
						.getVisibleElements()));
			}

			public void init(Hierarchy hierarchy) {
			}

			public boolean acceptElement(Element element) {
				return elementSet.contains(element);
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
